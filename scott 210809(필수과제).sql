-----�ʼ�����-----
--1. EMP���̺��� COMM �� ���� NULL�� �ƴ� ���� ��ȸ
select * from emp where comm is not null;

--2. EMP���̺��� Ŀ�̼��� ���� ���ϴ� ���� ��ȸ
select * from emp where comm is null;

--3. EMP���̺��� �����ڰ� ���� ���� ���� ��ȸ
select * from emp where mgr is null;

--4. EMP���̺��� �޿��� ���� �޴� ���� ������ ��ȸ
select * from emp ORDER BY sal desc;

--5. EMP���̺��� �޿��� ���� ��� Ŀ�̼��� �������� ���� ��ȸ
select * from emp ORDER BY sal desc, comm desc;

--6. EMP���̺��� �����ȣ, �����, ����, �Ի��� ��ȸ (��, �Ի����� �������� ���� ó��)
select deptno �����ȣ, ename "�����", job as ����, hiredate �Ի��� from emp ORDER BY hiredate;

--7. EMP���̺��� �����ȣ, ����� ��ȸ (�����ȣ ���� �������� ����)
select deptno �����ȣ, ename "�����" from emp ORDER by �����ȣ desc;

--8. EMP���̺��� ���, �Ի���, �����, �޿� ��ȸ
-- (�μ���ȣ�� ���� ������, ���� �μ���ȣ�� ���� �ֱ� �Ի��� ������ ó��)
select deptno ���, hiredate �Ի���, ename �����, sal �޿� from emp
ORDER by deptno asc, �Ի��� desc;