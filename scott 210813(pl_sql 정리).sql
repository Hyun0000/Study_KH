----------------------변수 선언 & 대입 & 출력----------------------
declare
    -- 변수 3개 생성
    -- 변수 '선언'은 반드시 이 위치에서 해야한다.
    emp_id number;
    emp_name varchar2(30);
    emp_empno number := 25;  -- 이 변수는 일부러 초기화까지 이 부분에서 해봤다.
begin
    -- 변수에 값 대입(:=) 
    emp_id := 888;
    emp_name := '배장남';
    
    -- 변수 출력  /  || --> 연결자
    dbms_output.put_line('emp_id : ' || emp_id);
    dbms_output.put_line('emp_name : ' || emp_name);
    dbms_output.put_line('emp_empno : ' || emp_empno); -- 출력이 정상적으로 됐다.
end;
/
----------------------참조(레퍼런스)변수의 선언과 초기화, 변수 값 출력----------------
----------------------사원번호를 입력하면 사원 정보를 출력----------------------
declare
    emp_id emp.empno%TYPE;
    -- emp.empno%TYPE --> emp 테이블의 empno 컬럼의 데이터 타입을
    -- 변수 EMP_ID에게 적용하겠다는 의미 by %TYPE
    emp_name emp.ename%TYPE;
    -- emp.ename%TYPE --> emp 테이블의 ename 컬럼의 데이터 타입을
    -- 변수 emp_name에게 적용하겠다는 의미 by %TYPE
BEGIN
    select empno, ename
    into emp_id, emp_name
    -- into의 의미
    -- empno의 값을 변수 emp_id에, ename의 값을 변수 emp_name에 대입
    -- (select문의 결과물을 대입하겠다는 의미)
    from emp
    where empno = '&emp_id'; -- 팝업창
    -- 꼭 empno 만이 아니라 다양한 컬럼을 where절의 조건으로 넣을 수 있다.
    
    DBMS_OUTPUT.PUT_LINE('emp_id : ' || emp_id);
    DBMS_OUTPUT.PUT_LINE('emp_name : ' || emp_name);
end;
/
------------------?한 행에 대한 ROWTYPE 변수의 선언과 초기화, 값 출력-----------------
declare
    e emp%rowtype;
    -- emp 테이블의 한 행에 있는 모든 데이터 타입들을 하나로 퉁쳐 하나의 자료형으로 쓰겠다는 의미
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
    
    if(bonus = 0) -- if문에 '=' 은 한 개를 쓴다.
        then DBMS_OUTPUT.PUT_LINE('보너스를 지급받지 않는 사원입니다.');
    end if;  
    
    DBMS_OUTPUT.PUT_LINE('bonusrate : ' || bonus*100 || '%');
 end;
/
--------------------------IF ~ THEN ~ ELSE ~ END IF------------------?------------------?
---------------------------else if 느낌-----------------------------------------
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
        then DBMS_OUTPUT.PUT_LINE('보너스를 지급받지 않는 사원입니다.');
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
    
        dbms_output.put_line('emp_deptno : ' || emp_deptno);
end;
/
--------------------------반복문------------------?----------------------?---------
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