---------------�ǽ�---------------
-- 33p
select ename from emp;
select empno, ename from emp;

-- 34p
select * from emp;

-- 35p
select ename "�̸�", sal ���� from emp;
select ename as �̸�, sal as ���� from emp;

-- 36p
select ename "��   ��", sal "$_����" from emp;
show user;

-- 37p
select * from tab;

-- 38p
desc emp;
select DISTINCT deptno from emp;
 
-- 39p
select DISTINCT deptno, job from emp;

-- 40p
select ename, sal, sal*12 ���� from emp;

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
select empno ���, ename from emp where ename like '%K%';
-- �̸��߿� K�� ���� ����� ã����

-- 50p
select empno ���, ename from emp where ename like 'K%';
-- �̸��� K�� �����ϴ� ����� ã����

select empno ���, ename from emp where ename like '%K';
-- �̸��� K�� ������ ����� ã����

select empno ���, ename from emp where ename like '_A%';
-- �̸��� �� ��°�� A�� ����� ã����

-- 51p
SELECT * FROM emp where comm is null;

SELECT * FROM emp where comm is not null;

-- 52p
select * from emp ORDER by sal;

-- 53p
select * from emp ORDER by sal, ename;

-- 54p
select * from emp ORDER by sal desc, ename asc;
-- ORDER by�� �÷� ������ �̿�
select * from emp ORDER by 6 desc, 2 asc;

-- 55p
select empno �����ȣ, ename �����, sal ����, hiredate �Ի��� from emp ORDER BY ���� desc, ����� asc;

-- 56p
--���� ������ ||
select ename||'''s JOB is '||job as EMPLOYEE from emp;
-- ���� �ٱ��� ' '�� ǥ���ϰ� ���� ���ڸ� ���δ� ���̰�
-- ������ ''�� �̰��� ���ڸ� ���δ� ' '�� �ƴ϶�� ���� ��Ÿ��������
-- �� ���� ����� �� ����.