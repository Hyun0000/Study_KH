-- 1�� ----------------------------------------------------------------------
select e.*, d.deptno, dname, loc
from emp e join dept d
on e.deptno = d.deptno
order by d.deptno;
-- 2�� ----------------------------------------------------------------------
select e.*, grade
from emp e join salgrade s
on e.sal BETWEEN s.losal and s.hisal
order by grade desc;
-- 3�� ----------------------------------------------------------------------
select e.*, grade
from emp e join salgrade s
on e.sal BETWEEN s.losal and s.hisal
where grade < 5 and e.deptno > 10
order by grade desc;
-- 4�� ----------------------------------------------------------------------
select deptno, floor(avg((sal*12 + nvl(comm, 0))))||'��' ��տ���
from emp
where deptno in (20,30)
group by deptno
order by ��տ��� desc;
-- 5�� ----------------------------------------------------------------------
select e.*, d.dname, d.loc, s.grade
from emp e join dept d
on e.deptno = d.deptno
join salgrade s
on e.sal between s.losal and s.hisal
order by s.grade;
-- 6�� ----------------------------------------------------------------------
select e.empno, e.ename, e.job, e.mgr, m.ename "MANAGER"
from emp e left outer join emp m
on e.mgr = m.empno;
-- 7�� ----------------------------------------------------------------------
-- �̿�
--select e.ename, m.ename
--from emp e join emp m



-- 8�� ----------------------------------------------------------------------

-- 9�� ----------------------------------------------------------------------

-- 10�� ----------------------------------------------------------------------

-- 11�� ----------------------------------------------------------------------




----------------����----------------
select to_char(sysdate, 'yyyy-mm-dd am hh24:mi:ss')
from dual;

select e.deptno, dname, count(empno) �����
from emp e join dept d
on e.deptno = d.deptno
GROUP BY e.deptno, dname;

select e.deptno, d.dname, count(*) �����
from emp e, dept d
where e.deptno = d.deptno
GROUP BY e.deptno, d.dname
having count(e.empno) > 5;

select e.ename, m.ename as �Ŵ���
from emp e left outer join emp m
on e.mgr = m.empno;