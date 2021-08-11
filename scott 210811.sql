-- 괄호안의 내용을 먼저 해석하고 나머지를 해석해야 한다.
-- 괄호안의 내용 해석 --> KING의 부서번호(deptno)가 나온다.
-- 서브쿼리의 내용만 먼저 실행해서 내가 원하는 결과가 나오는지 확인한다.
-- 그후 그 값만 메인 쿼리랑 또 확인을 헤본다.
select dname from dept
where deptno = 
    (select deptno
    from emp
    where ename = 'KING');
-------------------------------------------------------------
SELECT * FROM emp
WHERE sal > (
    SELECT AVG(sal) 
    FROM emp);
    
SELECT * FROM emp
WHERE sal in (
    SELECT min(sal) 
    FROM emp
    GROUP BY deptno
    );
-- 부서별로 제일 조금 받는 사람
-- in(800, 950, 1300) --> 이런 형태를 띄게 된다.
-------------------------------------------------------------
select d.dname, e.empno, e.ename, e.sal
from emp e join dept d
using (deptno)
where e.sal in (select max(sal) from emp group by deptno);
-- 아래 교재(별칭을 따로 쓰지 않았다.)
-- USING(deptno)을 썼기에 select문에서 deptno를 쓸 수 없다.
SELECT dname, empno, ename, sal FROM emp
JOIN dept USING(deptno)
WHERE sal IN (SELECT MAX(sal) FROM emp GROUP BY deptno);
-------------------------------------------------------------
select empno, ename, sal
from emp
where sal > any(select sal from emp where job = 'SALESMAN');
-- 결과 : 1250보다 높은 애들 찾아줘

select empno, ename, sal
from emp
where sal = any (select sal from emp where job = 'SALESMAN');
-- '=' 은 in을 쓰는것과 똑같은 결과
-------------------------------------------------------------
select empno, ename, sal
from emp
where sal > all(select sal from emp where job = 'MANAGER');
-- 결론 : 2975보다 큰 사람 찾아라
-------------------------------------------------------------
select empno, ename, sal
from emp e
where exists (select m.empno from emp m where e.empno = mgr);
-- where절에 값이 있다면 select절의 컬럼을 보여줘라
-- exists --> true / false를 뱉어낸다.
-- self join 형태를 서브쿼리로 나타낸것
-- 별칭을 서브쿼리에 쓸 수 있는 이유
-- (from > where 절 > 서브쿼리 생성) 순서이니까 가능
-- 중요 : 서브쿼리에서 어떤 table의 무엇을 반환하는지 봐라
-------------------------------------------------------------
select empno, ename, sal, deptno
from emp
where (deptno, sal) in (
    select deptno, sal
    from emp
    where deptno = 30
);
-- (30, sal)이 짝으로 맞아야한다.
-------------------------------------------------------------
select ename, deptno, sal,
    (
    select trunc(avg(sal))
    from emp
    where deptno = e.deptno
    ) as AVGDEPTSAL
from emp e;
-- 서브쿼리 : 각 부서별로 선별한 다음에 각 부서의 평균을 구하는 것이다.
-------------------------------------------------------------
select empno, ename, 
    CASE
    WHEN DEPTNO = (select deptno from dept where loc = 'NEW YORK')-- 서브쿼리의 실행결과 = 10
    THEN '본사'
    ELSE '분점'
end as 소속
from emp
order by 소속 desc;
-- 순서상 별칭 쓸 수 있다.
-------------------------------------------------------------
SELECT ENAME
, JOB
, (SELECT DNAME FROM DEPT WHERE DEPTNO = E.DEPTNO) DNAME
FROM EMP E;
-------------------------------------------------------------
--급여가 부서번호 20인 부서의 평균보다 높고 사원을 관리하는 ‘MANAGER’로써 20부서에 속하지 않는 사원을 조회

--select b.empno, b.ename, b.job, b.sal, b.deptno
--from 
--    (select empno from emp where sal >
--        ( select avg(sal) from emp where deptnpo = 20
--    ) a, emp b
--WHERE a.empno = b.empno
--AND b.mgr is NOT NULL
--AND b.deptno != 20;

SELECT b.empno, b.ename, b.job, b.sal, b.deptno
FROM (
    SELECT empno, deptno FROM emp --20번 부서만 구하는 것은 아니다.
    WHERE sal > (
                   SELECT AVG(sal) FROM emp
                   WHERE deptno = 20
                   )
    ) a, emp b
WHERE a.empno = b.empno
AND b.mgr is NOT NULL
AND b.deptno != 20;
-------------------------------------------------------------
SELECT EMPNO, ENAME, DEPTNO
from emp e order by (
                    select dname -- dname을 가지고 정렬하겠다는 의미
                    from dept
                    where deptno = e.deptno) desc;
-------------------------------------------------------------
select *
from emp e join dept d
on e.deptno = d.deptno;

select e.*, d.*
from emp e join dept d
on e.deptno = d.deptno;

select *
from emp e join dept d
USING(deptno); -- 결과물에 deptno가 사라진다.

-- union 결합
select * from emp where job = 'SALESMAN'
union
select * from emp where job = 'MANAGER';

-- select 뒤에 오는 컬럼은 동일해야한다.
select ename, job from emp where job = 'SALESMAN'
union
select ename, job frㅊom emp where job = 'MANAGER';
-- 동일 --> 데이터 타입잉 동일해야 한다는 것이다.
select ename, sal from emp where job = 'SALESMAN'
union
select ename, deptno from emp where job = 'MANAGER';




















































































































































