create sequence accounts_id_seq
      start 1
      increment 1;

create sequence transactions_id_seq
      start 1
      increment 1;

create sequence users_id_seq
      start 1
      increment 1;

create type currencytype as enum ('BYN', 'EUR', 'USD');

create type transactiontype as enum ('DEPOSIT', 'WITHDRAWAL');

create type doctype as enum ('PASSPORT', 'DRIVER_LICENSE');


create table users
(
      user_id         bigint              not null primary key,
      name            varchar(255)        not null,
      document_type   doctype,
      document_number varchar(255) unique not null,
      created_at      timestamp(6)        not null
);

create table accounts
(
      account_id    bigint         not null primary key,
      user_id       bigint references users,
      balance       numeric(10, 2) not null,
      currency_type currencytype   not null,
      created_at    timestamp(6)   not null
);

create table transactions
(
      transaction_id   bigint          not null primary key,
      account_id       bigint references accounts,
      amount           numeric(10, 2)  not null,
      created_at       timestamp(6)    not null,
      currency_type    currencytype    not null,
      transaction_type transactiontype not null
);


