/***********************************/
/* CREATE TABLES */
/***********************************/

CREATE TABLE EMPLOYEES
(
    EMPLOYEEID NUMBER NOT NULL,
    USERID NUMBER NOT NULL,
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(30),
    SSN VARCHAR(30),
    BIRTHDATE DATE,
    CONSTRAINT PK_EMPLOYEEID PRIMARY KEY (EMPLOYEEID)

);

CREATE TABLE MANAGERS
(
    MANAGERID NUMBER NOT NULL,
    USERNAME VARCHAR(30),
    PASSWORD VARCHAR(30),
    CONSTRAINT PK_MANAGERID PRIMARY KEY (MANAGERID)
);

CREATE TABLE USERS
(
    USERID  NUMBER NOT NULL,
    USERNAME VARCHAR(30),
    PASSWORD VARCHAR(30),
    CONSTRAINT PK_USERID PRIMARY KEY (USERID)
);


CREATE TABLE REIMBURSEMENTS
(
    RID NUMBER NOT NULL,
    EMPLOYEEID NUMBER NOT NULL,
    VALUE NUMBER,
    REASON VARCHAR(300),
    CONSTRAINT PK_RID PRIMARY KEY (RID)
)

/****************************/
/**** CREATING MANAGERS **********/
/************/
INSERT INTO MANAGERS VALUES(1, 'Manager', 'iam');



/***********************************/
/* CREATE FOREIGN KEYS */
/***********************************/

ALTER TABLE EMPLOYEES ADD CONSTRAINT FK_EMPLOYEEID
FOREIGN KEY (USERID) REFERENCES USERS (USERID) ON DELETE CASCADE ;

ALTER TABLE REIMBURSEMENTS ADD CONSTRAINT FK_REIMBURSEMENTS
FOREIGN KEY (EMPLOYEEID) REFERENCES EMPLOYEES (EMPLOYEEID) ON DELETE CASCADE ;

ALTER TABLE USERS ADD CONSTRAINT uni_user1
UNIQUE (USERNAME);


/**************************************/
/* TRIGGERS */
/************************************/
SAVEPOINT resetSeq;
CREATE SEQUENCE SQ_REIMBURSEMENTS   
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER Upon_InsertReimbursements
BEFORE INSERT ON Reimbursements
FOR EACH ROW
BEGIN
    SELECT  SQ_REIMBURSEMENTS.NEXTVAL
    INTO :NEW.RID
    FROM DUAL;
END;
/

CREATE SEQUENCE SQ_USERS  
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER Upon_InsertUsers
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    IF ( to_char(:NEW.username) = 'Manager') then
        raise_application_error(-200001, 'cannot use Manager username');
    END IF;
    SELECT  SQ_USERS.NEXTVAL
    INTO :NEW.userID
    FROM DUAL;
END;
/

CREATE SEQUENCE SQ_EMPLOYEES  
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER Upon_InsertEmployees
BEFORE INSERT ON Employees
FOR EACH ROW
BEGIN
    SELECT  SQ_EMPLOYEES.NEXTVAL
    INTO :NEW.EMPLOYEEID
    FROM DUAL;
END;
/

SELECT * FROM Users;
SELECT * FROM EMPLOYEES;
SELECT * FROM REIMBURSEMENTS;

SELECT * FROM USERS where username='LyinkA' AND password = 'LyinkB';
SELECT * FROM REIMBURSEMENTS WHERE employeeID = '1';

/*
DELETE FROM Users;
DROP SEQUENCE SQ_USERS;
ROLLBACK TO resetSeq;
TRUNCATE TABLE users;
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'tabnam';
SELECT supplier_id,
CASE
  WHEN supplier_name = 'IBM' and supplier_type = 'Hardware' THEN 'North office'
  WHEN supplier_name = 'IBM' and supplier_type = 'Software' THEN 'South office'
END
FROM suppliers;

SELECT 
CASE
    WHEN username = 'Lyinky' and password = 'Evermoon' THEN 'true'
END
FROM USERS;
*/
/*Uncomment if wish to modify */
/*************************
SELECT * FROM employees;
DROP TABLE users;
ALTER TABLE employees
modify
(
    ssn varchar2(30)
);
*/
/***************/

/* Uncomment to reset database */
/***************************/
/*
ALTER TABLE EMPLOYEES
DROP CONSTRAINT FK_EMPLOYEEID;

ALTER TABLE REIMBURSEMENTS
DROP CONSTRAINT FK_REIMBURSEMENTS;

ALTER TABLE USERS
DROP CONSTRAINT uni_user1;

DROP TABLE USERS;
DROP TABLE EMPLOYEES;
DROP TABLE REIMBURSEMENTS;

DROP SEQUENCE SQ_USERS;
DROP SEQUENCE SQ_REIMBURSEMENTS;
DROP SEQUENCE SQ_EMPLOYEES;
*/
