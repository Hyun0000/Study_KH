-- 101p
select empno, ename, sal
from emp e
where EXISTS(select empno from emp where e.empno = mgr);
-- 102p
select empno, ename, sal, deptno
from emp
where (sal, deptno) in (select sal, deptno from emp where deptno = 30);
-- 스칼라 서브쿼리
select ename, deptno, sal, (
                            select trunc(avg(sal))
                            from emp
                            where deptno = e.deptno
                            )
from emp e;
-- 103p
select empno, ename, 
                    CASE
                    when deptno = (
                                    select deptno from dept where loc = 'NEW YORK'
                                    )
                    then 'A'
                    else 'B'
                    end as 지점
from emp
order by 지점
;

select ename, job, (
                    select dname
                    from dept
                    where e.deptno = deptno) 부서
from emp e;
-- 103p
select ename, job
from emp e
where deptno = (
                select deptno
                from dept
                where deptno=e.deptno and dname = 'SALES')
;

















































