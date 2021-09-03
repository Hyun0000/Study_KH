------------------------------����ó��-------------------------------------------
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
        then dbms_output.put_line('���ʽ��� ���޹��� �ʴ� ����Դϴ�.');
    elsif (emp_comm = 100)
        then dbms_output.put_line('���ʽ��� 100 �޴� ����Դϴ�.');   
    elsif (emp_comm = 200)
        then dbms_output.put_line('���ʽ��� 200 �޴� ����Դϴ�.');
    elsif (emp_comm = 300)
        then dbms_output.put_line('���ʽ��� 300 �޴� ����Դϴ�.');
    else
        dbms_output.put_line('���ʽ��� 300 �̻� �޴� ����Դϴ�.');
    end if; 
EXCEPTION    
    when no_data_found
        then dbms_output.put_line('���� ��� �Դϴ�.');
end;
/
------------------------------���ν��� ����---------------------------------------
create or replace procedure emp_procedure
is --declare ��� is �� ����Ѵ�.
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
        then dbms_output.put_line('���ʽ��� ���޹��� �ʴ� ����Դϴ�.');
    elsif (emp_comm = 100)
        then dbms_output.put_line('���ʽ��� 100 �޴� ����Դϴ�.');   
    elsif (emp_comm = 200)
        then dbms_output.put_line('���ʽ��� 200 �޴� ����Դϴ�.');
    elsif (emp_comm = 300)
        then dbms_output.put_line('���ʽ��� 300 �޴� ����Դϴ�.');
    else
        dbms_output.put_line('���ʽ��� 300 �̻� �޴� ����Դϴ�.');
    end if; 
EXCEPTION    
    when no_data_found
        then dbms_output.put_line('���� ��� �Դϴ�.');
end;
/
------------���ν��� ����
exec emp_procedure;
----------------------�Ű������� �ִ� ���ν��� ����---------------------------------
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
        THEN DBMS_OUTPUT.PUT_LINE('�ش��ϴ� ����� �����ϴ�.');  
END;
/

EXEC PROCEDURE_PARAMETER('SCOTT');
EXEC PROCEDURE_PARAMETER('KING');
EXEC PROCEDURE_PARAMETER('BLAKE');
EXEC PROCEDURE_PARAMETER('ford');
------------------------���ν��� �Ű����� IN/OUT/INOUT----------------------------
-- �����ȣ�� �����ؼ� ����̸��� ����, ������ �����ϴ� ���ν����� ��������
--1. ���ν��� ����
CREATE OR REPLACE PROCEDURE PROCEDURE_IN_OUT (  -- �� �κп��� ������ �����Ѵ�.
                                                PARA_EMPNO IN EMP.EMPNO%TYPE,
                                                PARA_ENAME OUT EMP.ENAME%TYPE,
                                                PARA_SAL OUT EMP.SAL%TYPE,
                                                PARA_JOB OUT EMP.JOB%TYPE
                                                )
IS
BEGIN
    -- �����ȣ�� ��ȸ�ؼ� �� �Ű������� �� ����
    SELECT ENAME, SAL, JOB
    INTO PARA_ENAME, PARA_SAL, PARA_JOB
    FROM EMP
    WHERE EMPNO = PARA_EMPNO;
EXCEPTION
    WHEN NO_DATA_FOUND
        THEN DBMS_OUTPUT.PUT_LINE('�ش��ϴ� ����� �����ϴ�.');
END;
/
--2. ���ε� ���� ����
-- OUT �Ű������� ��ƿ������� '����'�κп� �����ϴ� ������ ���ε� ������� �Ѵ�.
VARIABLE VAR_ENAME VARCHAR2(15);
VARIABLE VAR_SAL NUMBER;
VARIABLE VAR_JOB VARCHAR2(15);

--3. ����
-- :VAR_ENAME, :VAR_SAL, :VAR_JOB --> ���ν��� ���� �� �� �����鿡�� ��������� ����.
EXEC PROCEDURE_IN_OUT(1010, :VAR_ENAME, :VAR_SAL, :VAR_JOB);
EXEC PROCEDURE_IN_OUT(7788, :VAR_ENAME, :VAR_SAL, :VAR_JOB);
EXEC PROCEDURE_IN_OUT(7839, :VAR_ENAME, :VAR_SAL, :VAR_JOB);

--4. OUT �Ű����� ���
PRINT VAR_ENAME;
PRINT VAR_SAL;
PRINT VAR_JOB;
------------------------�Լ�----------------------------------------------------
-- �����ȣ�� �Է��ϸ� ������ �˷��ִ� �Լ��� ���� & �����غ���.
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
---------------------------------Ʈ����------------------------------------------
CREATE TABLE EMP_TRIGGER AS (SELECT * FROM EMP);

CREATE OR REPLACE TRIGGER TGR_01
    AFTER INSERT
    ON EMP_TRIGGER
BEGIN
    DBMS_OUTPUT.PUT_LINE('���Ի���� �Ի��߽��ϴ�.');
END;
/

INSERT INTO EMP_TRIGGER VALUES (7777, 'SCOTT', 'MANAGER', 7778, '82/01/22', 1400, 100, 10);
INSERT INTO EMP_TRIGGER VALUES (1111, 'KING', 'MANAGER', 7748, '82/01/22', 14000, 200, 30);