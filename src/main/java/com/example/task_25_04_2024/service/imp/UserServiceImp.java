package com.example.task_25_04_2024.service.imp;

import com.example.task_25_04_2024.dto.UserDto;
import com.example.task_25_04_2024.entity.Account;
import com.example.task_25_04_2024.entity.Transaction;
import com.example.task_25_04_2024.entity.User;
import com.example.task_25_04_2024.entity.enums.DocType;
import com.example.task_25_04_2024.entity.enums.TransactionType;
import com.example.task_25_04_2024.exception.UserRuntimeException;
import com.example.task_25_04_2024.repository.UserRepository;
import com.example.task_25_04_2024.service.UserService;
import com.example.task_25_04_2024.validation.DocTypeValidComponent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
      private final UserRepository repository;

      private final ModelMapper mapper;

      private final DocTypeValidComponent docTypeValid;

      @Autowired
      public UserServiceImp(UserRepository repository, ModelMapper mapper, DocTypeValidComponent docTypeValid) {
            this.repository = repository;
            this.mapper = mapper;
            this.docTypeValid = docTypeValid;
      }

      @Override
      public List<UserDto> findAllUsers() {
            List<User> allUsers = repository.findAll();
            List<UserDto> userDtos = allUsers.stream()
                  .map(user -> mapper.map(user, UserDto.class))
                  .toList();
            return userDtos;
      }

      @Override
      @Transactional(isolation = Isolation.REPEATABLE_READ)
      public UserDto saveUserWithDepositByDocNumber(String documentNumber, Long accountId, DocType docType, BigDecimal amount) {

            User user = getUserOrThrow(
                  repository.findUserByDocumentNumber(documentNumber), documentNumber
            );

            validateDocumentTypesOrThrow(user, docType);

            Account userAccount = getAccountByAccountIdOrThrow(user,accountId);

            increaseAccountBalance(getAccountByAccountIdOrThrow(user,accountId),amount);

            createDepositTransactionAndAddToAccount(userAccount,amount);

            User userFromDb = repository.save(user);

            UserDto userDto = mapper.map(userFromDb, UserDto.class);

            return userDto;
      }

      private User getUserOrThrow(Optional<User> userOptional, String documentNumber) {
            return userOptional.orElseThrow(
                  () -> new UserRuntimeException(500, "User with document number : " + documentNumber + " not found")
            );
      }
      private  void validateDocumentTypesOrThrow(User user, DocType docType) {
            if (!docTypeValid.compareDocumentTypes(user.getDocumentType(), docType)) {
                  throw new UserRuntimeException(500, "Document types from request and BD are different");
            }
      }

      private Account getAccountByAccountIdOrThrow(User user, Long accountId) {
            return user.getAccounts().stream()
                  .filter(account -> account.getId() == accountId)
                  .findFirst()
                  .orElseThrow(() -> new UserRuntimeException(500, "Account not found"));
      }

      private void increaseAccountBalance(Account account, BigDecimal amount) {
            BigDecimal balance = account.getBalance();
            BigDecimal newBalance = balance.add(amount);
            account.setBalance(newBalance);
      }

      private void createDepositTransactionAndAddToAccount(Account account, BigDecimal amount) {
            Transaction newTransaction = new Transaction(
                  account.getId(), TransactionType.DEPOSIT,
                  amount, account.getCurrencyType()
            );
            account.getTransactions().add(newTransaction);
      }
}
