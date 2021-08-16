------------------------------예외처리-------------------------------------------
declare
    emp_id emp.empno%type;
    emp_name emp.ename%type;
    emp_sal emp.sal%type;
    emp_comm emp.comm%type;
begin
    select empno, ename, sal, nvl(comm, 0) 
    into emp_id, emp_name, emp_sal, emp_comm
    from emp
    where empno = '&empno';
    
    dbms_output.put_line('emp_id : ' || emp_id);
    dbms_output.put_line('emp_name : ' || emp_name);
    dbms_output.put_line('emp_sal : ' || emp_sal);
    
    if (emp_comm = 0)
        then dbms_output.put_line('보너스를 지급받지 않는 사원입니다.');
    elsif (emp_comm = 100)
        then dbms_output.put_line('보너스를 100 받는 사원입니다.');   
    elsif (emp_comm = 200)
        then dbms_output.put_line('보너스를 200 받는 사원입니다.');
    elsif (emp_comm = 300)
        then dbms_output.put_line('보너스를 300 받는 사원입니다.');
    else
        dbms_output.put_line('보너스를 300 이상 받는 사원입니다.');
    end if; 
EXCEPTION    
    when no_data_found
        then dbms_output.put_line('없는 사번 입니다.');
end;
/
------------------------------프로시저 생성---------------------------------------
create or replace procedure emp_procedure
is --declare 대신 is 를 사용한다.
    emp_id emp.empno%type;
    emp_name emp.ename%type;
    emp_sal emp.sal%type;
    emp_comm emp.comm%type;
begin
    select empno, ename, sal, nvl(comm, 0) 
    into emp_id, emp_name, emp_sal, emp_comm
    from emp
    where empno = '&empno';
    
    dbms_output.put_line('emp_id : ' || emp_id);
    dbms_output.put_line('emp_name : ' || emp_name);
    dbms_output.put_line('emp_sal : ' || emp_sal);
    
    if (emp_comm = 0)
        then dbms_output.put_line('보너스를 지급받지 않는 사원입니다.');
    elsif (emp_comm = 100)
        then dbms_output.put_line('보너스를 100 받는 사원입니다.');   
    elsif (emp_comm = 200)
        then dbms_output.put_line('보너스를 200 받는 사원입니다.');
    elsif (emp_comm = 300)
        then dbms_output.put_line('보너스를 300 받는 사원입니다.');
    else
        dbms_output.put_line('보너스를 300 이상 받는 사원입니다.');
    end if; 
EXCEPTION    
    when no_data_found
        then dbms_output.put_line('없는 사번 입니다.');
end;
/
------------프로시저 실행
exec emp_procedure;
----------------------매개변수가 있는 프로시저 생성---------------------------------
CREATE OR REPLACE PROCEDURE PROCEDURE_PARAMETER(EMP_NAME_PARA EMP.ENAME%TYPE)
IS
    EMP_NAME EMP.ENAME%TYPE;
    EMP_SAL EMP.SAL%TYPE;
    EMP_DEPTNO EMP.DEPTNO%TYPE;
BEGIN
    SELECT ENAME, SAL, DEPTNO
    INTO EMP_NAME, EMP_SAL, EMP_DEPTNO
    FROM EMP
    WHERE ENAME = EMP_NAME_PARA;
    
    DBMS_OUTPUT.PUT_LINE('EMP_NAME : ' || EMP_NAME);
    DBMS_OUTPUT.PUT_LINE('EMP_SAL : ' || EMP_SAL);
    DBMS_OUTPUT.PUT_LINE('EMP_DEPTNO : ' || EMP_DEPTNO);
    
EXCEPTION
    WHEN NO_DATA_FOUND
        THEN DBMS_OUTPUT.PUT_LINE('해당하는 사원이 없습니다.');  
END;
/

EXEC PROCEDURE_PARAMETER('SCOTT');
EXEC PROCEDURE_PARAMETER('KING');
EXEC PROCEDURE_PARAMETER('BLAKE');
EXEC PROCEDURE_PARAMETER('ford');
------------------------프로시저 매개변수 IN/OUT/INOUT----------------------------
-- 사원번호를 전달해서 사원이름과 월급, 보직을 리턴하는 프로시져를 생성하자
--1. 프로시저 생성
CREATE OR REPLACE PROCEDURE PROCEDURE_IN_OUT (  -- 이 부분에서 변수를 선언한다.
                                                PARA_EMPNO IN EMP.EMPNO%TYPE,
                                                PARA_ENAME OUT EMP.ENAME%TYPE,
                                                PARA_SAL OUT EMP.SAL%TYPE,
                                                PARA_JOB OUT EMP.JOB%TYPE
                                                )
IS
BEGIN
    -- 사원번호를 조회해서 각 매개변수에 값 대입
    SELECT ENAME, SAL, JOB
    INTO PARA_ENAME, PARA_SAL, PARA_JOB
    FROM EMP
    WHERE EMPNO = PARA_EMPNO;
EXCEPTION
    WHEN NO_DATA_FOUND
        THEN DBMS_OUTPUT.PUT_LINE('해당하는 사원이 없습니다.');
END;
/
--2. 바인드 변수 선언
-- OUT 매개변수를 담아오기위해 '실행'부분에 선언하는 변수를 바인드 변수라고 한다.
VARIABLE VAR_ENAME VARCHAR2(15);
VARIABLE VAR_SAL NUMBER;
VARIABLE VAR_JOB VARCHAR2(15);

--3. 실행
-- :VAR_ENAME, :VAR_SAL, :VAR_JOB --> 프로시저 실행 후 이 변수들에게 결과값들이 담긴다.
EXEC PROCEDURE_IN_OUT(1010, :VAR_ENAME, :VAR_SAL, :VAR_JOB);
EXEC PROCEDURE_IN_OUT(7788, :VAR_ENAME, :VAR_SAL, :VAR_JOB);
EXEC PROCEDURE_IN_OUT(7839, :VAR_ENAME, :VAR_SAL, :VAR_JOB);

--4. OUT 매개변수 출력
PRINT VAR_ENAME;
PRINT VAR_SAL;
PRINT VAR_JOB;
------------------------함수----------------------------------------------------
-- 사원번호를 입력하면 연봉을 알려주는 함수를 정의 & 실행해보자.
CREATE OR REPLACE FUNCTION EMP_FUNC(PARA_EMPNO EMP.EMPNO%TYPE)
    RETURN NUMBER
IS
    EMP_SAL EMP.SAL%TYPE;
BEGIN
    SELECT SAL INTO EMP_SAL
    FROM EMP
    WHERE EMPNO = PARA_EMPNO;
    
    RETURN (EMP_SAL * 12);
END;
/

VARIABLE VAR_YEARSAL NUMBER;

EXEC :VAR_YEARSAL := EMP_FUNC(7839);

PRINT VAR_YEARSAL;
---------------------------------트리거------------------------------------------
CREATE TABLE EMP_TRIGGER AS (SELECT * FROM EMP);

CREATE OR REPLACE TRIGGER TGR_01
    AFTER INSERT
    ON EMP_TRIGGER
BEGIN
    DBMS_OUTPUT.PUT_LINE('신입사원이 입사했습니다.');
END;
/

INSERT INTO EMP_TRIGGER VALUES (7777, 'SCOTT', 'MANAGER', 7778, '82/01/22', 1400, 100, 10);
INSERT INTO EMP_TRIGGER VALUES (1111, 'KING', 'MANAGER', 7748, '82/01/22', 14000, 200, 30);