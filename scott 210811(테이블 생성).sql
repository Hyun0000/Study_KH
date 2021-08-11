CREATE TABLE test01 (
  empno number(4)
);

CREATE TABLE test02 (
    empno number(4),
    ename varchar2(30),
    sal number(7,2) --�� 7������ 2���� �Ҽ���
 
);

desc test01;
desc test02;

select * from tab; -- view�� �� �� �ִ�.

select table_name from user_tables;
select * from user_tables;

CREATE TABLE test03(
    t1 char(1),
    t2 timestamp
);
----------------------------------------------
create table dept01(
    DEPTNO number(2) primary key,
    DNAME varchar2(60) not null,
    LOC varchar2(90) not null
);

INSERT INTO DEPT01 VALUES (10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT01 VALUES (20,'RESEARCH','DALLAS');
INSERT INTO DEPT01 VALUES (30,'SALES','CHICAGO');
INSERT INTO DEPT01 VALUES (40,'OPERATIONS','BOSTON');
COMMIT;
select * from dept01;
----------------------------------------------------------------------------
-- �������� �ɱ� ���01 �ζ��� (�÷��� ���� �ٿ� �ۼ�)
CREATE TABLE emp01(
    EMPNO NUMBER PRIMARY KEY,
    ENAME VARCHAR2(20) NOT NULL,
    job VARCHAR2(9) UNIQUE,
    DEPTNO NUMBER(2) REFERENCES dept01(deptno)
);
-- dept01 table�� �����̸Ӹ�Ű ������ �����ϱ� emp01 table�� ���������� ���̴�.
-- ����ũ or �����̸Ӹ� Ű�ΰͿ��� ��Ī�� �ȴ�.
-- ���� dept table���� ���������� �ϳ��� �� �ɷ��־��� ������ �׷����̴�.
------------------------------------------------------------------------
-- �������� �ɱ� ���02 �ƿ�����(�÷��� �� �������� �ؿ��ٰ� �߰������� �۾�)
-- �ƿ����� --> not null ���� �Ұ� / foreing key �߿�
CREATE TABLE emp02 (
    empno NUMBER
    , ename VARCHAR2(20)
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    -- �ƿ����� �������� ����(�÷���)
    , PRIMARY KEY (empno)  -->  �������� ����(������ �÷���)
    , UNIQUE (job)  -->  �������� ����(������ �÷���)
    , FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO) -->�길 �� �ٸ���.
    -- �ܺ�Ű�� ������ �ͼ� ���ٴ� �ǹ̷� foreing key�� �������� �̸��� ���ߴ�.
    
    -- �ٵ� ename�� not null�� �� ���°�
    -- nut null�� �ƿ����� ������� ������ �� �ǹǷ� �ζ��� ������� �ۿ� �� ����.
    
);
-- �Ʒ��� ���� ����

CREATE TABLE emp02 (
    empno NUMBER
    , ename VARCHAR2(20) NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    --- �ƿ����� : NOT NULL �ȵ�.
    --- ������������ (�������÷���)
    , PRIMARY KEY (empno)
    , UNIQUE (job)
    , FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
);
------------------------------------------------------------------------
-- constriont_name�� ���� ���� ���� ������?
-- �������� �ɱ� ���03 �ζ��� + constriont_name ����
CREATE TABLE emp03(
    EMPNO NUMBER CONSTRAINTS EMP03_EMPNO_PK PRIMARY KEY,
    ENAME VARCHAR2(20) CONSTRAINTS EMP03_ENAME_NN NOT NULL,
    job VARCHAR2(9) CONSTRAINTS EMP03_JOB_UK UNIQUE,
    DEPTNO NUMBER(2) CONSTRAINTS EMP03_DEPTNO_FK REFERENCES dept01(deptno)
);
--------------------03����
CREATE TABLE emp03(
    empno NUMBER  CONSTRAINTS EMP03_EMPNO_PK    PRIMARY KEY
    , ename VARCHAR2(20)  CONSTRAINTS EMP03_ENAME_NN     NOT NULL
    , job VARCHAR2(9) CONSTRAINTS EMP03_JOB_UK   UNIQUE
    , deptno NUMBER(2)  CONSTRAINTS EMP03_DEPTNO_FK    REFERENCES DEPT01(DEPTNO)
);

------------------------------------------------------------------------
-- �������� �ɱ� ���04 �ƿ����� + constriont_name ����
CREATE TABLE emp04 (
    empno NUMBER
    , ename VARCHAR2(20) CONSTRAINTS EMP04_ENAME_NN NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    --- �ƿ����� : NOT NULL �ȵ�.
    --- ������������ (�������÷���)
    , CONSTRAINTS EMP04_EMPNO_PK PRIMARY KEY (empno)
    , CONSTRAINTS EMP04_JOB_UK UNIQUE (job)
    , CONSTRAINTS EMP04_DEPTNO_FK FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
);
--------------------04����
CREATE TABLE emp04 (
    empno NUMBER
    , ename VARCHAR2(20) NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    --- �ƿ����� : NOT NULL �ȵ�.
    --- ������������ (�������÷���)
    , CONSTRAINTS EMP04_EMPNO_PK    PRIMARY KEY (empno)
    , CONSTRAINTS EMP04_JOB_UK      UNIQUE (job)
    , CONSTRAINTS EMP04_DEPTNO_FK   FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
);



select * from user_CONSTRAINTS;
C
P
U
R
-----------------check-----------------
-- check --> null/not null ��ɰ� ����
-- �������� �ɱ� ���05 �ζ��� + constriont_name ���� +check
-- �� ����
CREATE TABLE emp05 (
    empno NUMBER
    , ename VARCHAR2(20) CONSTRAINTS EMP05_ENAME_NN NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    , gender number check (gender = 0 or gender = 1) -- gender in (0,1)�� ����
    -- where ���� �� �� �ִ°� �� �� �� �ִ�.(between ��)
    , CONSTRAINTS EMP05_EMPNO_PK PRIMARY KEY (empno)
    , CONSTRAINTS EMP05_JOB_UK UNIQUE (job)
    , CONSTRAINTS EMP05_DEPTNO_FK FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
);
----����
CREATE TABLE emp05(
    empno NUMBER  CONSTRAINTS EMP05_EMPNO_PK    PRIMARY KEY
    , ename VARCHAR2(20)  CONSTRAINTS EMP05_ENAME_NN     NOT NULL
    , job VARCHAR2(9) CONSTRAINTS EMP05_JOB_UK   UNIQUE
    , deptno NUMBER(2)  CONSTRAINTS EMP05_DEPTNO_FK    REFERENCES DEPT01(DEPTNO)
    , gender NUMBER CONSTRAINTS EMP05_GENDER_CK     CHECK (gender IN (0,1))
);
---------------------------------@@@@@@@@@@@@@@@
-- �������� �ɱ� ���06 �ζ��� + constriont_name ���� +check
CREATE TABLE emp06 (
    empno NUMBER
    , ename VARCHAR2(20) CONSTRAINTS EMP06_ENAME_NN NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    , gender number check (gender = 0 or gender = 1) -- gender in (0,1)�� ����
    -- where ���� �� �� �ִ°� �� �� �� �ִ�.(between ��)
    , CONSTRAINTS EMP06_EMPNO_PK PRIMARY KEY (empno)
    , CONSTRAINTS EMP06_JOB_UK UNIQUE (job)
    , CONSTRAINTS EMP06_DEPTNO_FK FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
    , CONSTRAINTS EMP06_GENDER_CK CHECK (gender IN (0,1)) -- �ش� ���ǽĿ� �̹� gender�� �ֱ⿡ ���� �÷����� ���� �ʿ䰡 ����.
);
----06����
CREATE TABLE emp06 (
    empno NUMBER
    , ename VARCHAR2(20) NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    , gender NUMBER 
    --- �ƿ����� : NOT NULL �ȵ�.
    --- ������������ (�������÷���)
    , CONSTRAINTS EMP06_EMPNO_PK    PRIMARY KEY (empno)
    , CONSTRAINTS EMP06_JOB_UK      UNIQUE (job)
    , CONSTRAINTS EMP06_DEPTNO_FK   FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
    , CONSTRAINTS EMP06_GENDER_CK   CHECK (gender IN (0,1))
);
--------------------------------------------------------------
CREATE TABLE emp06 (
    empno NUMBER
    , ename VARCHAR2(20) NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    , gender NUMBER 
    --- �ƿ����� : NOT NULL �ȵ�.
    --- ������������ (�������÷���)
    , CONSTRAINTS EMP06_EMPNO_PK    PRIMARY KEY (empno)
    , CONSTRAINTS EMP06_JOB_UK      UNIQUE (job)
    , CONSTRAINTS EMP06_DEPTNO_FK   FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO) ON DELETE CASECADE
    , CONSTRAINTS EMP06_GENDER_CK   CHECK (gender IN (0,1))
);
ON DELETE CASECADE --> dept table���� DEPTNO�ϳ��� ������� �Ǹ� emp���̺� �ִ� ���� �� ��������.
ON DELETE CASECADE SET NULL --> ���� �������� �ش� �μ� ����� �μ���ȣ�� NULL�� ����

----------------------------------------
--������� ��� ��������� ���� ���ϰ� ����ϴ� SQL
select SUBSTR(A.COLUMN_NAME,1,15) COLUMN_NAME,
       DECODE(B.CONSTRAINT_TYPE,
    'P','PRIMARY KEY',
    'U','UNIQUE KEY',
    'C','CHECK OR NOT NULL',
    'R','FOREIGN KEY'
    ) CONSTRAINT_TYPE,
    A.CONSTRAINT_NAME
FROM USER_CONS_COLUMNS A, USER_CONSTRAINTS B
WHERE A.TABLE_NAME = UPPER('&table_name')
    AND A.TABLE_NAME = B.TABLE_NAME
    AND A.CONSTRAINT_NAME = B.CONSTRAINT_NAME
ORDER BY 1; -- ���÷��̵Ǵ� ù��° �� �������� �������� �ρٴٴ� �ǹ�
-- & --> �Է¹޴�â�� ����ش�.
-- table_name --> â�� �ߴ� ����

--------------ALTER TABLE--------------
-- alter --> ����, ���̺� ��� ��������
alter table  dept 
    add �߰��÷��� �ڷ��� ��������
;

alter table  dept 
    modify �����÷��� �����ڷ��� ������������
;

alter table  dept 
    drop �����÷���
;

alter table  dept 
    rename �����÷���
;

-- alter�� �Ϳ� ���������� �� ���� �ִ�(�̋��� �ۼ����� �� �޶�����.)




































































































