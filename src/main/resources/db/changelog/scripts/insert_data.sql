INSERT INTO users (user_id, name, document_number, created_at, document_type)
VALUES /*1*/(nextval('users_id_seq'), 'user1', '1111111A001PB9', '2024-01-29 00:00:39.412820', 'PASSPORT'),
      /*2*/(nextval('users_id_seq'), 'user2', '2222222A002PB9', '2024-02-29 00:00:39.412820', 'DRIVER_LICENSE'),
      /*3*/(nextval('users_id_seq'), 'user3', '3333333A003PB9', '2024-03-29 00:00:39.412820', 'PASSPORT'),
      /*4*/(nextval('users_id_seq'), 'user4', '4444444A004PB9', '2024-04-29 00:00:39.412820', 'DRIVER_LICENSE'),
      /*5*/(nextval('users_id_seq'), 'user5', '5555555A005PB9', '2024-05-29 00:00:39.412820', 'PASSPORT');

INSERT INTO accounts (account_id, user_id, balance, currency_type, created_at)
VALUES /*1*/(nextval('accounts_id_seq'), 1, 0.00, 'BYN', '2024-01-29 00:01:39.412820'),
      /*2*/(nextval('accounts_id_seq'), 1, 0.00, 'EUR', '2024-02-29 00:02:39.412820'),
      /*3*/(nextval('accounts_id_seq'), 1, 0.00, 'USD', '2024-03-29 00:03:39.412820'),
      /*4*/(nextval('accounts_id_seq'), 2, 1000.00, 'BYN', '2024-04-29 00:04:39.412820'),
      /*5*/(nextval('accounts_id_seq'), 2, 1000.00, 'EUR', '2024-05-29 00:05:39.412820'),
      /*6*/(nextval('accounts_id_seq'), 2, 1000.00, 'USD', '2024-06-29 00:06:39.412820'),
      /*7*/(nextval('accounts_id_seq'), 3, 300.00, 'BYN', '2024-07-29 00:07:39.412820'),
      /*8*/(nextval('accounts_id_seq'), 4, 400.00, 'EUR', '2024-08-29 00:08:39.412820'),
      /*9*/(nextval('accounts_id_seq'), 5, 500.00, 'USD', '2024-09-29 00:09:39.412820');

INSERT INTO transactions(transaction_id, account_id, amount, created_at, currency_type, transaction_type)
VALUES /*1*/(nextval('transactions_id_seq'), 4, 1000.00, '2024-10-29 00:01:39.412820', 'BYN', 'DEPOSIT'),
      /*2*/(nextval('transactions_id_seq'), 5, 1000.00, '2024-10-29 00:02:39.412820', 'EUR', 'DEPOSIT'),
      /*3*/(nextval('transactions_id_seq'), 6, 1000.00, '2024-10-29 00:03:39.412820', 'USD', 'DEPOSIT'),
      /*4*/(nextval('transactions_id_seq'), 7, 100.00, '2024-10-29 00:04:39.412820', 'BYN', 'DEPOSIT'),
      /*4*/(nextval('transactions_id_seq'), 7, 100.00, '2024-10-29 00:05:39.412820', 'BYN', 'DEPOSIT'),
      /*4*/(nextval('transactions_id_seq'), 7, 100.00, '2024-10-29 00:06:39.412820', 'BYN', 'DEPOSIT'),
      /*5*/(nextval('transactions_id_seq'), 8, 200.00, '2024-10-29 00:07:39.412820', 'EUR', 'DEPOSIT'),
      /*5*/(nextval('transactions_id_seq'), 8, 200.00, '2024-10-29 00:08:39.412820', 'EUR', 'DEPOSIT'),
      /*6*/(nextval('transactions_id_seq'), 9, 500.00, '2024-10-29 00:09:39.412820', 'USD', 'DEPOSIT');