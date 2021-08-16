----------------------���� ���� & ���� & ���----------------------
declare
    -- ���� 3�� ����
    -- ���� '����'�� �ݵ�� �� ��ġ���� �ؾ��Ѵ�.
    emp_id number;
    emp_name varchar2(30);
    emp_empno number := 25;  -- �� ������ �Ϻη� �ʱ�ȭ���� �� �κп��� �غô�.
begin
    -- ������ �� ����(:=) 
    emp_id := 888;
    emp_name := '���峲';
    
    -- ���� ���  /  || --> ������
    dbms_output.put_line('emp_id : ' || emp_id);
    dbms_output.put_line('emp_name : ' || emp_name);
    dbms_output.put_line('emp_empno : ' || emp_empno); -- ����� ���������� �ƴ�.
end;
/
----------------------����(���۷���)������ ����� �ʱ�ȭ, ���� �� ���----------------
----------------------�����ȣ�� �Է��ϸ� ��� ������ ���----------------------
declare
    emp_id emp.empno%TYPE;
    -- emp.empno%TYPE --> emp ���̺��� empno �÷��� ������ Ÿ����
    -- ���� EMP_ID���� �����ϰڴٴ� �ǹ� by %TYPE
    emp_name emp.ename%TYPE;
    -- emp.ename%TYPE --> emp ���̺��� ename �÷��� ������ Ÿ����
    -- ���� emp_name���� �����ϰڴٴ� �ǹ� by %TYPE
BEGIN
    select empno, ename
    into emp_id, emp_name
    -- into�� �ǹ�
    -- empno�� ���� ���� emp_id��, ename�� ���� ���� emp_name�� ����
    -- (select���� ������� �����ϰڴٴ� �ǹ�)
    from emp
    where empno = '&emp_id'; -- �˾�â
    -- �� empno ���� �ƴ϶� �پ��� �÷��� where���� �������� ���� �� �ִ�.
    
    DBMS_OUTPUT.PUT_LINE('emp_id : ' || emp_id);
    DBMS_OUTPUT.PUT_LINE('emp_name : ' || emp_name);
end;
/
------------------?�� �࿡ ���� ROWTYPE ������ ����� �ʱ�ȭ, �� ���-----------------
declare
    e emp%rowtype;
    -- emp ���̺��� �� �࿡ �ִ� ��� ������ Ÿ�Ե��� �ϳ��� ���� �ϳ��� �ڷ������� ���ڴٴ� �ǹ�
begin
    select * 
    into e
    from emp
    where empno = '&emp_id';
    DBMS_OUTPUT.PUT_LINE('emp_id : ' || e.empno);
    DBMS_OUTPUT.PUT_LINE('emp_name : ' || e.ename);
    DBMS_OUTPUT.PUT_LINE('emp_sal : ' || e.sal);
    DBMS_OUTPUT.PUT_LINE('emp_deptno : ' || e.deptno);
end;
/
--------------------------?IF ~ THEN ~ END IF------------------?------------------?
declare
    emp_id emp.empno%type;
    emp_name emp.ename%type;
    emp_sal emp.sal%type;
    bonus emp.comm%type;
begin
    select empno, ename, sal, nvl(comm,0) comm
    into emp_id, emp_name, emp_sal, bonus
    from emp
    where empno = '&empno';
    
    DBMS_OUTPUT.PUT_LINE('emp_id : ' || emp_id);
    DBMS_OUTPUT.PUT_LINE('emp_name : ' || emp_name);
    DBMS_OUTPUT.PUT_LINE('emp_sal : ' || emp_sal);
    
    if(bonus = 0) -- if���� '=' �� �� ���� ����.
        then DBMS_OUTPUT.PUT_LINE('���ʽ��� ���޹��� �ʴ� ����Դϴ�.');
    end if;  
    
    DBMS_OUTPUT.PUT_LINE('bonusrate : ' || bonus*100 || '%');
 end;
/
--------------------------IF ~ THEN ~ ELSE ~ END IF------------------?------------------?
---------------------------else if ����-----------------------------------------
declare
    emp_id emp.empno%type;
    emp_name emp.ename%type;
    emp_sal emp.sal%type;
    emp_comm emp.comm%type;    
    emp_deptno emp.deptno%type;
begin
    select empno, ename, sal, nvl(comm, 0), deptno
    into emp_id, emp_name, emp_sal, emp_comm, emp_deptno
    from emp
    where empno = '&empno';
    
    dbms_output.put_line('emp_id : ' || emp_id);
    dbms_output.put_line('emp_name : ' || emp_name);
    dbms_output.put_line('emp_sal : ' || emp_sal);
    
    if (emp_comm = 0)
        then DBMS_OUTPUT.PUT_LINE('���ʽ��� ���޹��� �ʴ� ����Դϴ�.');
    else 
        DBMS_OUTPUT.PUT_LINE('bonusrate : ' || emp_comm*100 || '%');
    end if;
    
    dbms_output.put_line('emp_deptno : ' || emp_deptno);
end;
/
--------------------------IF ~ THEN ~ ELSIF~ ELSE ~ END IF------------------?------------------?
declare
    emp_id emp.empno%type;
    emp_name emp.ename%type;
    emp_sal emp.sal%type;
    emp_comm emp.comm%type;    
    emp_deptno emp.deptno%type;
begin
    select empno, ename, sal, nvl(comm, 0), deptno
    into emp_id, emp_name, emp_sal, emp_comm, emp_deptno
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
    
        dbms_output.put_line('emp_deptno : ' || emp_deptno);
end;
/
--------------------------�ݺ���------------------?----------------------?---------
--------------------------BASIC LOOP------------------?----------------------?----
declare
    n number := 1;
begin
    loop
        dbms_output.put_line(n);
        N := N+1;
        
        if (N > 5) then exit;
        end if;
    end loop;
end;
/
--------------------------FOR LOOP1------------------?----------------------?-----
begin
    for n in 1..5 loop
        dbms_output.put_line(n);
    end loop;
end;
/
--------------------------FOR LOOP2------------------?----------------------?-----
begin
    for n in reverse 1..5 loop
        dbms_output.put_line(n);
    end loop;    
end;
/
--------------------------while LOOP------------------?----------------------?----
declare
    n number := 1;
begin
    while (n <= 5)
        loop
            dbms_output.put_line(n);
            n := n+1;
        end loop;
end;
/