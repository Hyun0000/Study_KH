[ �������� �ɱ� ��� 01 - �ζ��ι�� (�÷��� ���� �ٿ� �ۼ�) ]

CREATE TABLE emp01(
    EMPNO NUMBER PRIMARY KEY,
    ENAME VARCHAR2(20) NOT NULL,
    job VARCHAR2(9) UNIQUE,
    DEPTNO NUMBER(2) REFERENCES dept01(deptno)
);
- dept01 table�� Primary Key ������ �����ϱ� emp01 table�� ���������� ���̴�.
- ���� dept table�� REFERENCES�� �ɾ��� ���� �ش� table�� Primary Key ���������⿡
table ������ �Ұ����ߴ�.
------------------------------------------------------------------------------------------
[ �������� �ɱ� ��� 02 - �ƿ����� (�÷��� �� �������� �ؿ��ٰ� �߰������� �۾�) ]

CREATE TABLE emp02 (
    empno NUMBER,
    ename VARCHAR2(20) NOT NULL,  -->  NOT NULL�� �ƿ����� ����� ������ �� �ȴ�.
    job VARCHAR2(9),
    deptno NUMBER(2),
        
    PRIMARY KEY (empno),
    UNIQUE (job),
    FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
    - �ܺ�Ű�� ������ �ͼ� ���ٴ� �ǹ��� FOREIGN KEY �� �������� �̸��� ���ߴ�.
);
------------------------------------------------------------------------------------------
- ��������(CONSTRAINTS)�� �̸��� ���� �� �ִ�.

[ �������� �ɱ� ��� 03 �ζ��� + CONSTRAINTS_NAME ]
CREATE TABLE emp03(
    EMPNO NUMBER CONSTRAINTS EMP03_EMPNO_PK PRIMARY KEY,
    ENAME VARCHAR2(20) CONSTRAINTS EMP03_ENAME_NN NOT NULL,
    job VARCHAR2(9) CONSTRAINTS EMP03_JOB_UK UNIQUE,
    DEPTNO NUMBER(2) CONSTRAINTS EMP03_DEPTNO_FK REFERENCES dept01(deptno)
);
------------------------------------------------------------------------------------------
[ �������� �ɱ� ��� 04 �ƿ����� + CONSTRAINTS_NAME]
CREATE TABLE emp04 (
    EMPNO NUMBER,
    ENAME VARCHAR2(20) CONSTRAINTS EMP04_ENAME_NN NOT NULL,
    JOB VARCHAR2(9),
    DEPTNO NUMBER(2),
    CONSTRAINTS EMP04_EMPNO_PK PRIMARY KEY (EMPNO),
    CONSTRAINTS EMP04_JOB_UK UNIQUE (JOB),
    CONSTRAINTS EMP04_DEPTNO_FK FOREIGN KEY (DEPTNO) REFERENCES DEPT01(DEPTNO)
);
------------------------------------------------------------------------------------------
- CHECK
- �÷��� ���� Ư�� ������ ���� (null/not null ��ɰ� ����)
- CHECK�� ���� �����̴�.
ex)
CREATE TABLE test (
     data1 NUMBER,
     data2 NUMBER CHECK (data2 >=1 AND data2 <= 99)
);  -->  data2 column�� ���� ���� 1~99�� ����
------------------------------------------------------------------------------------------
[ �������� �ɱ� ��� 05 �ζ��� + CONSTRAINTS_NAME + CHECK ]
CREATE TABLE emp05(
    EMPNO NUMBER CONSTRAINTS EMP05_EMPNO_PK PRIMARY KEY,
    ENAME VARCHAR2(20) CONSTRAINTS EMP05_ENAME_NN NOT NULL,
    JOB VARCHAR2(9) CONSTRAINTS EMP05_JOB_UK UNIQUE,
    DEPTNO NUMBER(2) CONSTRAINTS EMP05_DEPTNO_FK REFERENCES DEPT01(DEPTNO),
    GENDER NUMBER CONSTRAINTS EMP05_GENDER_CK CHECK (GENDER IN (0,1))
);
- CHECK���� WHERE�� ���� �� �� �ִ°� �� �� �� �ִ�.(between ��)
------------------------------------------------------------------------------------------
[ �������� �ɱ� ��� 06  �ƿ����� + CONSTRAINTS_NAME + CHECK ]
CREATE TABLE emp06 (
    EMPNO NUMBER,
    ENAME VARCHAR2(20) CONSTRAINTS EMP06_ENAME_NN NOT NULL,
    JOB VARCHAR2(9),
    DEPTNO NUMBER(2),
    GENDER NUMBER,
    CONSTRAINTS EMP06_EMPNO_PK PRIMARY KEY (EMPNO),
    CONSTRAINTS EMP06_JOB_UK UNIQUE (JOB),
    CONSTRAINTS EMP06_DEPTNO_FK FOREIGN KEY (DEPTNO) REFERENCES DEPT01(DEPTNO),
    CONSTRAINTS EMP06_GENDER_CK CHECK (GENDER IN (0,1))

- (GENDER IN (0,1)) --> �ش� ���ǽĿ� �̹� GENDER�� �ֱ⿡ ���� column���� ���� �ʿ䰡 ����.
------------------------------------------------------------------------------------------
CREATE TABLE emp06 (
    EMPNO NUMBER,
    ENAME VARCHAR2(20) CONSTRAINTS EMP06_ENAME_NN NOT NULL,
    JOB VARCHAR2(9),
    DEPTNO NUMBER(2),
    GENDER NUMBER,
    CONSTRAINTS EMP06_EMPNO_PK PRIMARY KEY (EMPNO),
    CONSTRAINTS EMP06_JOB_UK UNIQUE (JOB),
    CONSTRAINTS EMP06_DEPTNO_FK FOREIGN KEY (DEPTNO) REFERENCES DEPT01(DEPTNO) ON DELETE CASECADE,
    CONSTRAINTS EMP06_GENDER_CK CHECK (GENDER IN (0,1))
);

REFERENCES(Foreing Key)�κ�
- ON DELETE CASECADE
�θ����̺�(���� �ϴ� ���̺�)���� Primary Key ������ �׿� �ش��ϴ�
�ܷ�Ű�� �ִ� table�� �ش� ���ڵ��� ���뵵 ���� ����

- ON DELETE CASECADE SET NULL
�θ����̺�(���� �ϴ� ���̺�)���� Primary Key ������ �׿� �ش��ϴ�
�ܷ�Ű�� �ִ� table�� �ش� ���ڵ��� ������ NULL�� �����Ѵ�.
------------------------------------------------------------------------------------------
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
ORDER BY 1;