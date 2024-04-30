package com.example.task_25_04_2024.service.imp;

import com.example.task_25_04_2024.dto.AccountDto;
import com.example.task_25_04_2024.dto.UserDto;
import com.example.task_25_04_2024.entity.Account;
import com.example.task_25_04_2024.entity.Transaction;
import com.example.task_25_04_2024.entity.User;
import com.example.task_25_04_2024.entity.enums.CurrencyType;
import com.example.task_25_04_2024.entity.enums.DocType;
import com.example.task_25_04_2024.entity.enums.TransactionType;
import com.example.task_25_04_2024.repository.UserRepository;
import com.example.task_25_04_2024.service.UserService;
import com.example.task_25_04_2024.validation.DocTypeValidComponent;
import com.example.task_25_04_2024.validation.imp.DocTypeValidComponentImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.*;

class UserServiceImpTest {

      private List<User> allUserList;

      private  BigDecimal user1TransactionAmount = new BigDecimal("1000.00");
      private String user1DocNumber;
      private Long user1AccountID;
      private DocType user1DocType;

      private User user1BeforeDeposit;
      private Optional<User> user1Optional;

      private Transaction user1NewTransaction;

      private Account user1AccountAfterDeposit;
      private User user1AfterDeposit;

      private UserDto userDtoAfterDeposit;

      @BeforeEach
      void initializeDataForTest() {
            initializeUserCollection();
            initializeUser1DocNumber();
            initializeUser1Optional();
            initializeUser1AccountID();
            initializeUser1DocType();
            initializeUser1();
            initializeUser1NewTransaction();
            initializeUser1AccountAfterDeposit();
            initializeUser1AfterDeposit();
            initializeUserDtoAfterDeposit();
      }

      @Test
      void testFindAllUsers() {

            DocTypeValidComponent validComponent = Mockito.mock(DocTypeValidComponentImp.class);
            UserRepository mockRepository = Mockito.mock(UserRepository.class);
            ModelMapper mockMapper = Mockito.mock(ModelMapper.class);

            Mockito.when(mockRepository.findAll()).thenReturn(allUserList);

            UserService service = new UserServiceImp(mockRepository, mockMapper, validComponent);

            List<UserDto> allUsers = service.findAllUsers();

            Assertions.assertEquals(3, allUsers.size());
      }

      @Test
      void testSaveUserWithDepositByDocNumber() {

            DocTypeValidComponent validComponent = Mockito.mock(DocTypeValidComponentImp.class);
            UserRepository mockRepository = Mockito.mock(UserRepository.class);
            ModelMapper mockMapper = Mockito.mock(ModelMapper.class);

            Mockito
                  .when(mockRepository
                        .findUserByDocumentNumber(user1DocNumber)
                  )
                  .thenReturn(user1Optional);
            Mockito
                  .when(validComponent.compareDocumentTypes(user1DocType, user1DocType))
                  .thenReturn(true);
            Mockito
                  .when(mockRepository.save(user1BeforeDeposit))
                  .thenReturn(user1AfterDeposit);
            Mockito
                  .when(mockMapper.map(user1AfterDeposit, UserDto.class))
                  .thenReturn(userDtoAfterDeposit);

            UserService service = new UserServiceImp(mockRepository, mockMapper, validComponent);

            UserDto userDto = service.saveUserWithDepositByDocNumber(
                  user1DocNumber, user1AccountID, user1DocType, user1TransactionAmount

            );
            AccountDto accountDtoAfterDeposit = userDto.getAccounts().stream().findFirst().get();

            Assertions.assertEquals(new BigDecimal("1100.00"), accountDtoAfterDeposit.getBalance());
            Assertions.assertEquals(2, accountDtoAfterDeposit.getTransactions().size());
      }

      private void initializeUserCollection() {
            Set<Transaction> user1Transactions = new HashSet<>(Set.of(
                  new Transaction(1L, 1L, TransactionType.DEPOSIT,
                        new BigDecimal("100.00"), CurrencyType.BYN, new Date())
            ));
            Set<Account> user1Accounts = new HashSet<>(Set.of(
                  new Account(1L, 1L, new BigDecimal("100.00"),
                        CurrencyType.BYN, new Date(), user1Transactions)
            ));
            User user1 = new User(1L, "user1", DocType.DRIVER_LICENSE,
                  "1111111A001PB9", new Date(), user1Accounts);

            Set<Transaction> user2Transactions = new HashSet<>(Set.of(
                  new Transaction(2L, 2L, TransactionType.DEPOSIT,
                        new BigDecimal("200.00"), CurrencyType.EUR, new Date())
            ));
            Set<Account> user2Accounts = new HashSet<>(Set.of(
                  new Account(2L, 2L, new BigDecimal("200.00"),
                        CurrencyType.EUR, new Date(), user2Transactions)
            ));
            User user2 = new User(2L, "user2", DocType.DRIVER_LICENSE,
                  "2222222A001PB9", new Date(), user2Accounts);

            Set<Transaction> user3Transactions = new HashSet<>(Set.of(
                  new Transaction(3L, 3L, TransactionType.DEPOSIT,
                        new BigDecimal("300.00"), CurrencyType.USD, new Date())
            ));
            Set<Account> user3Accounts = new HashSet<>(Set.of(
                  new Account(3L, 3L, new BigDecimal("300.00"),
                        CurrencyType.USD, new Date(), user3Transactions)
            ));
            User user3 = new User(3L, "user3", DocType.DRIVER_LICENSE,
                  "3333333A001PB9", new Date(), user3Accounts
            );
            allUserList = List.of(user1, user2, user3);
      }

      private void initializeUser1DocNumber() {
            user1DocNumber = allUserList.get(0).getDocumentNumber();
      }

      private void initializeUser1Optional() {
            user1Optional = Optional.of(allUserList.get(0));
      }

      private void initializeUser1AccountID() {
            user1AccountID = allUserList.get(0).getAccounts()
                  .stream()
                  .findFirst()
                  .get()
                  .getId();
      }

      private void initializeUser1DocType() {
            user1DocType = allUserList.get(0).getDocumentType();
      }

      private void initializeUser1() {
            user1BeforeDeposit = allUserList.get(0);
      }

      private void initializeUser1NewTransaction() {
            user1NewTransaction =
                  new Transaction(4L, 1L, TransactionType.DEPOSIT,
                        user1TransactionAmount, CurrencyType.BYN, new Date()
                  );
      }

      private void initializeUser1AccountAfterDeposit() {
            Account account = new ArrayList<>(user1BeforeDeposit.getAccounts()).get(0);
            ;
            BigDecimal newBalance = account.getBalance().add(user1TransactionAmount);
            account.setBalance(newBalance);
            account.getTransactions().add(user1NewTransaction);
            user1AccountAfterDeposit = account;
      }

      private void initializeUser1AfterDeposit() {
            user1AfterDeposit = new User(1L, "user1", DocType.DRIVER_LICENSE,
                  "1111111A001PB9",
                  new Date(), new HashSet<>(Set.of(user1AccountAfterDeposit))
            );
      }

      private void initializeUserDtoAfterDeposit() {
            ModelMapper modelMapper = new ModelMapper();
            userDtoAfterDeposit = modelMapper.map(user1AfterDeposit, UserDto.class);
      }

}