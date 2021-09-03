-- ��ȣ���� ������ ���� �ؼ��ϰ� �������� �ؼ��ؾ� �Ѵ�.
-- ��ȣ���� ���� �ؼ� --> KING�� �μ���ȣ(deptno)�� ���´�.
-- ���������� ���븸 ���� �����ؼ� ���� ���ϴ� ����� �������� Ȯ���Ѵ�.
-- ���� �� ���� ���� ������ �� Ȯ���� �캻��.
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
-- �μ����� ���� ���� �޴� ���
-- in(800, 950, 1300) --> �̷� ���¸� ��� �ȴ�.
-------------------------------------------------------------
select d.dname, e.empno, e.ename, e.sal
from emp e join dept d
using (deptno)
where e.sal in (select max(sal) from emp group by deptno);
-- �Ʒ� ����(��Ī�� ���� ���� �ʾҴ�.)
-- USING(deptno)�� ��⿡ select������ deptno�� �� �� ����.
SELECT dname, empno, ename, sal FROM emp
JOIN dept USING(deptno)
WHERE sal IN (SELECT MAX(sal) FROM emp GROUP BY deptno);
-------------------------------------------------------------
select empno, ename, sal
from emp
where sal > any(select sal from emp where job = 'SALESMAN');
-- ��� : 1250���� ���� �ֵ� ã����

select empno, ename, sal
from emp
where sal = any (select sal from emp where job = 'SALESMAN');
-- '=' �� in�� ���°Ͱ� �Ȱ��� ���
-------------------------------------------------------------
select empno, ename, sal
from emp
where sal > all(select sal from emp where job = 'MANAGER');
-- ��� : 2975���� ū ��� ã�ƶ�
-------------------------------------------------------------
select empno, ename, sal
from emp e
where exists (select m.empno from emp m where e.empno = mgr);
-- where���� ���� �ִٸ� select���� �÷��� �������
-- exists --> true / false�� ����.
-- self join ���¸� ���������� ��Ÿ����
-- ��Ī�� ���������� �� �� �ִ� ����
-- (from > where �� > �������� ����) �����̴ϱ� ����
-- �߿� : ������������ � table�� ������ ��ȯ�ϴ��� ����
-------------------------------------------------------------
select empno, ename, sal, deptno
from emp
where (deptno, sal) in (
    select deptno, sal
    from emp
    where deptno = 30
);
-- (30, sal)�� ¦���� �¾ƾ��Ѵ�.
-------------------------------------------------------------
select ename, deptno, sal,
    (
    select trunc(avg(sal))
    from emp
    where deptno = e.deptno
    ) as AVGDEPTSAL
from emp e;
-- �������� : �� �μ����� ������ ������ �� �μ��� ����� ���ϴ� ���̴�.
-------------------------------------------------------------
select empno, ename, 
    CASE
    WHEN DEPTNO = (select deptno from dept where loc = 'NEW YORK')-- ���������� ������ = 10
    THEN '����'
    ELSE '����'
end as �Ҽ�
from emp
order by �Ҽ� desc;
-- ������ ��Ī �� �� �ִ�.
-------------------------------------------------------------
SELECT ENAME
, JOB
, (SELECT DNAME FROM DEPT WHERE DEPTNO = E.DEPTNO) DNAME
FROM EMP E;
-------------------------------------------------------------
--�޿��� �μ���ȣ 20�� �μ��� ��պ��� ���� ����� �����ϴ� ��MANAGER���ν� 20�μ��� ������ �ʴ� ����� ��ȸ

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
    SELECT empno, deptno FROM emp --20�� �μ��� ���ϴ� ���� �ƴϴ�.
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
                    select dname -- dname�� ������ �����ϰڴٴ� �ǹ�
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
USING(deptno); -- ������� deptno�� �������.

-- union ����
select * from emp where job = 'SALESMAN'
union
select * from emp where job = 'MANAGER';

-- select �ڿ� ���� �÷��� �����ؾ��Ѵ�.
select ename, job from emp where job = 'SALESMAN'
union
select ename, job fr��om emp where job = 'MANAGER';
-- ���� --> ������ Ÿ���� �����ؾ� �Ѵٴ� ���̴�.
select ename, sal from emp where job = 'SALESMAN'
union
select ename, deptno from emp where job = 'MANAGER';




















































































































































