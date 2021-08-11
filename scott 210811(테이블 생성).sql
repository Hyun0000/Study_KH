CREATE TABLE test01 (
  empno number(4)
);

CREATE TABLE test02 (
    empno number(4),
    ename varchar2(30),
    sal number(7,2) --총 7숫자중 2개는 소수점
 
);

desc test01;
desc test02;

select * from tab; -- view도 볼 수 있다.

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
-- 제약조건 걸기 방법01 인라인 (컬럼과 같은 줄에 작성)
CREATE TABLE emp01(
    EMPNO NUMBER PRIMARY KEY,
    ENAME VARCHAR2(20) NOT NULL,
    job VARCHAR2(9) UNIQUE,
    DEPTNO NUMBER(2) REFERENCES dept01(deptno)
);
-- dept01 table에 프라이머리키 설정을 했으니까 emp01 table이 생성가능한 것이다.
-- 유니크 or 프라이머리 키인것에만 매칭이 된다.
-- 기존 dept table에는 제약조건이 하나도 안 걸려있었기 때문에 그런것이다.
------------------------------------------------------------------------
-- 제약조건 걸기 방법02 아웃라인(컬럼이 다 정해진후 밑에다가 추가적으로 작업)
-- 아웃라인 --> not null 적용 불가 / foreing key 중요
CREATE TABLE emp02 (
    empno NUMBER
    , ename VARCHAR2(20)
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    -- 아웃라인 제약조건 종류(컬럼명)
    , PRIMARY KEY (empno)  -->  제약조건 종류(적용할 컬럼명)
    , UNIQUE (job)  -->  제약조건 종류(적용할 컬럼명)
    , FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO) -->얘만 좀 다르다.
    -- 외부키를 가지고 와서 쓴다는 의미로 foreing key로 제약조건 이름을 정했다.
    
    -- 근데 ename의 not null은 왜 없는가
    -- nut null은 아웃라인 방식으로 적용이 안 되므로 인라인 방식으로 밖에 못 쓴다.
    
);
-- 아래가 최종 정리

CREATE TABLE emp02 (
    empno NUMBER
    , ename VARCHAR2(20) NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    --- 아웃라인 : NOT NULL 안됨.
    --- 제약조건종류 (적용할컬럼명)
    , PRIMARY KEY (empno)
    , UNIQUE (job)
    , FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
);
------------------------------------------------------------------------
-- constriont_name을 내가 정할 수는 없을까?
-- 제약조건 걸기 방법03 인라인 + constriont_name 지정
CREATE TABLE emp03(
    EMPNO NUMBER CONSTRAINTS EMP03_EMPNO_PK PRIMARY KEY,
    ENAME VARCHAR2(20) CONSTRAINTS EMP03_ENAME_NN NOT NULL,
    job VARCHAR2(9) CONSTRAINTS EMP03_JOB_UK UNIQUE,
    DEPTNO NUMBER(2) CONSTRAINTS EMP03_DEPTNO_FK REFERENCES dept01(deptno)
);
--------------------03강사
CREATE TABLE emp03(
    empno NUMBER  CONSTRAINTS EMP03_EMPNO_PK    PRIMARY KEY
    , ename VARCHAR2(20)  CONSTRAINTS EMP03_ENAME_NN     NOT NULL
    , job VARCHAR2(9) CONSTRAINTS EMP03_JOB_UK   UNIQUE
    , deptno NUMBER(2)  CONSTRAINTS EMP03_DEPTNO_FK    REFERENCES DEPT01(DEPTNO)
);

------------------------------------------------------------------------
-- 제약조건 걸기 방법04 아웃라인 + constriont_name 지정
CREATE TABLE emp04 (
    empno NUMBER
    , ename VARCHAR2(20) CONSTRAINTS EMP04_ENAME_NN NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    --- 아웃라인 : NOT NULL 안됨.
    --- 제약조건종류 (적용할컬럼명)
    , CONSTRAINTS EMP04_EMPNO_PK PRIMARY KEY (empno)
    , CONSTRAINTS EMP04_JOB_UK UNIQUE (job)
    , CONSTRAINTS EMP04_DEPTNO_FK FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
);
--------------------04강사
CREATE TABLE emp04 (
    empno NUMBER
    , ename VARCHAR2(20) NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    --- 아웃라인 : NOT NULL 안됨.
    --- 제약조건종류 (적용할컬럼명)
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
-- check --> null/not null 기능과 동일
-- 제약조건 걸기 방법05 인라인 + constriont_name 지정 +check
-- 잘 못함
CREATE TABLE emp05 (
    empno NUMBER
    , ename VARCHAR2(20) CONSTRAINTS EMP05_ENAME_NN NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    , gender number check (gender = 0 or gender = 1) -- gender in (0,1)도 가능
    -- where 옆에 들어갈 수 있는거 다 들어갈 수 있다.(between 등)
    , CONSTRAINTS EMP05_EMPNO_PK PRIMARY KEY (empno)
    , CONSTRAINTS EMP05_JOB_UK UNIQUE (job)
    , CONSTRAINTS EMP05_DEPTNO_FK FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
);
----강사
CREATE TABLE emp05(
    empno NUMBER  CONSTRAINTS EMP05_EMPNO_PK    PRIMARY KEY
    , ename VARCHAR2(20)  CONSTRAINTS EMP05_ENAME_NN     NOT NULL
    , job VARCHAR2(9) CONSTRAINTS EMP05_JOB_UK   UNIQUE
    , deptno NUMBER(2)  CONSTRAINTS EMP05_DEPTNO_FK    REFERENCES DEPT01(DEPTNO)
    , gender NUMBER CONSTRAINTS EMP05_GENDER_CK     CHECK (gender IN (0,1))
);
---------------------------------@@@@@@@@@@@@@@@
-- 제약조건 걸기 방법06 인라인 + constriont_name 지정 +check
CREATE TABLE emp06 (
    empno NUMBER
    , ename VARCHAR2(20) CONSTRAINTS EMP06_ENAME_NN NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    , gender number check (gender = 0 or gender = 1) -- gender in (0,1)도 가능
    -- where 옆에 들어갈 수 있는거 다 들어갈 수 있다.(between 등)
    , CONSTRAINTS EMP06_EMPNO_PK PRIMARY KEY (empno)
    , CONSTRAINTS EMP06_JOB_UK UNIQUE (job)
    , CONSTRAINTS EMP06_DEPTNO_FK FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO)
    , CONSTRAINTS EMP06_GENDER_CK CHECK (gender IN (0,1)) -- 해당 조건식에 이미 gender가 있기에 따로 컬럼명을 적을 필요가 없다.
);
----06강사
CREATE TABLE emp06 (
    empno NUMBER
    , ename VARCHAR2(20) NOT NULL
    , job VARCHAR2(9)
    , deptno NUMBER(2)
    , gender NUMBER 
    --- 아웃라인 : NOT NULL 안됨.
    --- 제약조건종류 (적용할컬럼명)
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
    --- 아웃라인 : NOT NULL 안됨.
    --- 제약조건종류 (적용할컬럼명)
    , CONSTRAINTS EMP06_EMPNO_PK    PRIMARY KEY (empno)
    , CONSTRAINTS EMP06_JOB_UK      UNIQUE (job)
    , CONSTRAINTS EMP06_DEPTNO_FK   FOREIGN KEY (deptno) REFERENCES DEPT01(DEPTNO) ON DELETE CASECADE
    , CONSTRAINTS EMP06_GENDER_CK   CHECK (gender IN (0,1))
);
ON DELETE CASECADE --> dept table에서 DEPTNO하나가 사라지게 되면 emp테이블에 있는 나도 싹 지워진다.
ON DELETE CASECADE SET NULL --> 내가 지워질때 해당 부서 사원의 부서번호는 NULL로 수정

----------------------------------------
--사용자의 모든 제약사항을 보기 편하게 출력하는 SQL
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
ORDER BY 1; -- 디스플레이되는 첫번째 행 기준으로 오름차순 하곘다는 의미
-- & --> 입력받는창을 띄워준다.
-- table_name --> 창에 뜨는 글자

--------------ALTER TABLE--------------
-- alter --> 유저, 테이블 모두 수정가능
alter table  dept 
    add 추가컬럼명 자료형 제약조건
;

alter table  dept 
    modify 기존컬럼명 변경자료형 변경제약조건
;

alter table  dept 
    drop 기존컬럼명
;

alter table  dept 
    rename 기존컬럼명
;

-- alter할 것에 제약조건이 올 수도 있다(이떄는 작성법이 또 달라진다.)




































































































