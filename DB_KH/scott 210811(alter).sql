-- �÷� �߰�
alter table test02
	add (t4 number);
    
alter table dept01
    add (t4 number);
    
-- ���� �÷��� ����    
alter table dept01
    rename column t4 to t1;
-- (rename column) --> rename�� �ٲٰ� ���� ���� �������� ������� �Ѵ�.
-- �������� �� �پ��Ѱ� ���� �� �����ϱ�

-- ����(modify)
alter table dept01
    modify (t1 varchar2(30));
-- not null�� ���� �߰� �� �Ѵ�.
-- �̹� null�� �⺻������ ���� ����

alter table dept01
    modify (deptno varchar2(30));
    -- �� �ȴ�. �̹� �ڷᰡ �ִ� ��쿡�� ���� �Ұ�

alter table dept01
    modify (loc varchar2(6));
    --ũ�Ⱑ �۾� ����Ұ�

alter table dept01
    modify (loc varchar2(120) DEFAULT '�����Դϴ�.');
--    DEFAULT --> �ƹ��͵� �Է����� �ʾ����� �̰��� ������ �־��ְڴ�.
-- �̰� ���������� �ƴϴ�.

-- drop
-- �÷� ����
alter table dept01
    DROP COLUMN loc;-- �� ������ ��� ������� (�÷��� ������ ���������� ������)
    
    select * from dept01;
    
    -- �������� ����
alter table dept01
    drop CONSTRAINT SYS_C007022;
    
drop table dept01;
-- dept01�� �ִ� PK�� �ܷ�Ű�� ���� �����ǰ� �ִ� ���¶� �� �����.
    
drop table dept01 CONSTRAINT CASECADE;
-- �׷��� �� �𸣰ڰ� ���� �����ְ� ���� �����ϰ� CONSTRAINT���� �� �� ������

    
    
    

    



