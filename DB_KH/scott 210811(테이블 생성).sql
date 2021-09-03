[ 제약조건 걸기 방법 01 - 인라인방식 (컬럼과 같은 줄에 작성) ]

CREATE TABLE emp01(
    EMPNO NUMBER PRIMARY KEY,
    ENAME VARCHAR2(20) NOT NULL,
    job VARCHAR2(9) UNIQUE,
    DEPTNO NUMBER(2) REFERENCES dept01(deptno)
);
- dept01 table에 Primary Key 설정을 했으니까 emp01 table이 생성가능한 것이다.
- 기존 dept table에 REFERENCES를 걸었을 때는 해당 table에 Primary Key 설정없었기에
table 생성이 불가능했다.
------------------------------------------------------------------------------------------
[ 제약조건 걸기 방법 02 - 아웃라인 (컬럼이 다 정해진후 밑에다가 추가적으로 작업) ]

CREATE TABLE emp02 (
    empno NUMBER,
    ename VARCHAR2(20) NOT NULL,  -->  NOT NULL은 아웃라인 방식이 적용이 안 된다.
    job VARCHAR2(9),
    deptno NUMBER(2),
        
    PRIMARY KEY (empno),
    UNIQUE (job),
    FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
    - 외부키를 가지고 와서 쓴다는 의미의 FOREIGN KEY 로 제약조건 이름을 정했다.
);
------------------------------------------------------------------------------------------
- 제약조건(CONSTRAINTS)에 이름을 정할 수 있다.

[ 제약조건 걸기 방법 03 인라인 + CONSTRAINTS_NAME ]
CREATE TABLE emp03(
    EMPNO NUMBER CONSTRAINTS EMP03_EMPNO_PK PRIMARY KEY,
    ENAME VARCHAR2(20) CONSTRAINTS EMP03_ENAME_NN NOT NULL,
    job VARCHAR2(9) CONSTRAINTS EMP03_JOB_UK UNIQUE,
    DEPTNO NUMBER(2) CONSTRAINTS EMP03_DEPTNO_FK REFERENCES dept01(deptno)
);
------------------------------------------------------------------------------------------
[ 제약조건 걸기 방법 04 아웃라인 + CONSTRAINTS_NAME]
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
- 컬럼의 값을 특정 범위로 제한 (null/not null 기능과 동일)
- CHECK도 제약 조건이다.
ex)
CREATE TABLE test (
     data1 NUMBER,
     data2 NUMBER CHECK (data2 >=1 AND data2 <= 99)
);  -->  data2 column에 들어올 값을 1~99로 제한
------------------------------------------------------------------------------------------
[ 제약조건 걸기 방법 05 인라인 + CONSTRAINTS_NAME + CHECK ]
CREATE TABLE emp05(
    EMPNO NUMBER CONSTRAINTS EMP05_EMPNO_PK PRIMARY KEY,
    ENAME VARCHAR2(20) CONSTRAINTS EMP05_ENAME_NN NOT NULL,
    JOB VARCHAR2(9) CONSTRAINTS EMP05_JOB_UK UNIQUE,
    DEPTNO NUMBER(2) CONSTRAINTS EMP05_DEPTNO_FK REFERENCES DEPT01(DEPTNO),
    GENDER NUMBER CONSTRAINTS EMP05_GENDER_CK CHECK (GENDER IN (0,1))
);
- CHECK에는 WHERE절 옆에 들어갈 수 있는건 다 들어갈 수 있다.(between 등)
------------------------------------------------------------------------------------------
[ 제약조건 걸기 방법 06  아웃라인 + CONSTRAINTS_NAME + CHECK ]
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

- (GENDER IN (0,1)) --> 해당 조건식에 이미 GENDER가 있기에 따로 column명을 적을 필요가 없다.
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

REFERENCES(Foreing Key)부분
- ON DELETE CASECADE
부모테이블(참조 하는 테이블)에서 Primary Key 삭제시 그에 해당하는
외래키가 있는 table의 해당 레코드의 내용도 전부 삭제

- ON DELETE CASECADE SET NULL
부모테이블(참조 하는 테이블)에서 Primary Key 삭제시 그에 해당하는
외래키가 있는 table의 해당 레코드의 내용은 NULL로 수정한다.
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