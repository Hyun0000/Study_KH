---------------실습---------------
-- 33p
select ename from emp;
select empno, ename from emp;

-- 34p
select * from emp;

-- 35p
select ename "이름", sal 봉급 from emp;
select ename as 이름, sal as 봉급 from emp;

-- 36p
select ename "성   명", sal "$_봉급" from emp;
show user;

-- 37p
select * from tab;

-- 38p
desc emp;
select DISTINCT deptno from emp;
 
-- 39p
select DISTINCT deptno, job from emp;

-- 40p
select ename, sal, sal*12 연봉 from emp;

-- 41p
select * from emp where deptno = 10;

-- 42p
select * from emp WHERE comm > 500;
select * from emp WHERE hiredate < '81/01/01';

-- 43p
select * from emp WHERE ename = 'WARD';
select * from emp WHERE ename = 'ward';

-- 44p
SELECT * FROM emp where deptno <> 30;
SELECT * FROM emp where deptno != 30;
SELECT * FROM emp where deptno ^= 30;
SELECT * FROM emp where not deptno = 30;

SELECT * FROM emp where sal >= 3000 and deptno = 20;

-- 45p
SELECT * FROM emp where sal >= 3000 or deptno = 20;
SELECT * FROM emp where sal >= 2000 and deptno = 20 and hiredate < '82/01/01';

-- 46p
SELECT * FROM emp
WHERE deptno = 20 AND (sal >= 2000 OR hiredate < '82/01/01');

-- 47p
select * from emp where deptno in(20,30);

-- 48p
select * from emp where deptno not in(20, 30);
select * from emp where empno BETWEEN 7698 and 7902;

-- 49p
select empno 사번, ename from emp where ename like '%K%';
-- 이름중에 K가 들어가는 사람을 찾아줘

-- 50p
select empno 사번, ename from emp where ename like 'K%';
-- 이름이 K로 시작하는 사람을 찾아줘

select empno 사번, ename from emp where ename like '%K';
-- 이름이 K로 끝나는 사람을 찾아줘

select empno 사번, ename from emp where ename like '_A%';
-- 이름의 두 번째가 A인 사람을 찾아줘

-- 51p
SELECT * FROM emp where comm is null;

SELECT * FROM emp where comm is not null;

-- 52p
select * from emp ORDER by sal;

-- 53p
select * from emp ORDER by sal, ename;

-- 54p
select * from emp ORDER by sal desc, ename asc;
-- ORDER by에 컬럼 순서를 이용
select * from emp ORDER by 6 desc, 2 asc;

-- 55p
select empno 사원번호, ename 사원명, sal 월급, hiredate 입사일 from emp ORDER BY 월급 desc, 사원명 asc;

-- 56p
--연결 연산자 ||
select ename||'''s JOB is '||job as EMPLOYEE from emp;
-- 가장 바깥의 ' '는 표현하고 싶은 문자를 감싸는 것이고
-- 안쪽의 ''는 이것이 문자를 감싸는 ' '가 아니라는 것을 나타내기위해
-- 두 개를 사용한 것 같다.