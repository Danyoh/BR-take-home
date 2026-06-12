Compile to bin

`javac -d bin com/acme/account/*.java com/acme/application/*.java`

Run

`java -cp bin com.acme.application.UserInterface`


SQL portion:

CREATE TABLE ACCOUNTS ( account_id NUMBER (10, 0), owner_name VARCHAR2(200) NOT NULL, initial_cash NUMBER(12,2) NOT NULL, created_at DATE );

CREATE TABLE TRANSACTIONS ( txn_id NUMBER(10, 0), account_id NUMBER(10, 0), action VARCHAR2(20), txn_type VARCHAR2(20), amount NUMBER(12,2)), created_at DATE ); Based on the tables above please write the following SQL:

    Seed the tables with 3 rows per table.

INSERT INTO ACCOUNTS ( account_id, owner_name, initial_cash, created_at) VALUES (1, 'Ridhi Bhanot', 100000.00, TO_DATE('2026-01-02', 'YYYY-MM-DD'));
INSERT INTO ACCOUNTS ( account_id, owner_name, initial_cash, created_at) VALUES (2, 'Daniel Nguyen', 1.00, TO_DATE('2026-03-04', 'YYYY-MM-DD'));
INSERT INTO ACCOUNTS ( account_id, owner_name, initial_cash, created_at) VALUES (3, 'Catherine Wong', 1000000.00, TO_DATE('2026-05-06', 'YYYY-MM-DD'));

INSERT INTO TRANSACTIONS ( txn_id, account_id, action, txn_type, amount, created_at) VALUES (101, 1, 'DEPOSIT', 'CASH', 2000.00, TO_DATE('2026-01-02', 'YYYY-MM-DD'));
INSERT INTO TRANSACTIONS ( txn_id, account_id, action, txn_type, amount, created_at) VALUES (102, 2, 'DEPOSIT', 'STOCK', 5.00, TO_DATE('2026-03-05', 'YYYY-MM-DD'));
INSERT INTO TRANSACTIONS ( txn_id, account_id, action, txn_type, amount, created_at) VALUES (103, 3, 'WITHDRAW', 'CASH', 100.00, TO_DATE('2026-05-07', 'YYYY-MM-DD'));
    
    Write a query to list out all the transactions sorted by date with newer transaction appearing first.

SELECT 
    txn_id, 
    account_id, 
    action_txn_type, 
    amount_created_at 
FROM TRANSACTIONS 
ORDER BY created_at DESC;


    Write a query to select the total of each account based on type. One balance for Cash and one balance for stock

SELECT 
   account_id,
   txn_type,
   SUM(CASE WHEN action = 'DEPOSIT' THEN amount ELSE -amount END) AS balance
FROM TRANSACTIONS
GROUP BY account_id, txn_type
ORDER BY account_id, txn_type

(not including initial_cash)
(stock is per unit so not price)
    
    Write a query that would list the client, the account id with the largest sum of cash

SELECT 
    a.owner_name,
    a.account_id,
    SUM(CASE WHEN t.action = 'DEPOSIT' THEN t.amount ELSE -t.amount END) AS cash_balance
FROM ACCOUNTS a
JOIN TRANSACTIONS t ON t.account_id = a.account_id
WHERE t.txn_type = 'CASH'
GROUP BY a.owner_name, a.account_id
ORDER BY cash_balance DESC
FETCH FIRST 1 ROWS ONLY;

