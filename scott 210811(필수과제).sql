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























