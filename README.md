Compile to bin

`javac -d bin com/acme/account/*.java com/acme/application/*.java`

Run

`java -cp bin com.acme.application.UserInterface`


SQL portion:

CREATE TABLE ACCOUNTS ( account_id NUMBER (10, 0), owner_name VARCHAR2(200) NOT NULL, initial_cash NUMBER(12,2) NOT NULL, created_at DATE );

CREATE TABLE TRANSACTIONS ( txn_id NUMBER(10, 0), account_id NUMBER(10, 0), action VARCHAR2(20), txn_type VARCHAR2(20), amount NUMBER(12,2)), created_at DATE ); Based on the tables above please write the following SQL:

    Seed the tables with 3 rows per table.

INSERT INTO ACCOUNTS ( account_id, owner_name, initial_cash, created_at) VALUES (1, 'Ridhi Bhanot ', 100000.00, TO_DATE('2026-01-02', 'YYYY-MM-DD'));
INSERT INTO ACCOUNTS ( account_id, owner_name, initial_cash, created_at) VALUES (2, 'Daniel Nguyen', 1.00, TO_DATE('2026-03-04', 'YYYY-MM-DD'));
INSERT INTO ACCOUNTS ( account_id, owner_name, initial_cash, created_at) VALUES (3, 'Catherine Wong', 1000000.00, TO_DATE('2026-05-06', 'YYYY-MM-DD'));

INSERT TABLE TRANSACTIONS ( txn_id, account_id, action, txn_type, amount, created_at DATE) VALUES (101, 1, 'DEPOSIT', 'CASH, 2000.00, TO_DATE('2026-01-02', 'YYYY-MM-DD'));
INSERT TABLE TRANSACTIONS ( txn_id, account_id, action, txn_type, amount, created_at DATE) VALUES (102, 2, 'DEPOSIT', 'STOCK, 5.00, TO_DATE('2026-03-05', 'YYYY-MM-DD'));
INSERT TABLE TRANSACTIONS ( txn_id, account_id, action, txn_type, amount, created_at DATE) VALUES (103, 3, 'WITHDRAW', 'CASH, 100.00, TO_DATE('2026-05-07', 'YYYY-MM-DD'));
    
    Write a query to list out all the transactions sorted by date with newer transaction appearing first.


    Write a query to select the total of each account based on type. One balance for Cash and one balance for stock

    
    Write a query that would list the client, the account id with the largest sum of cash

