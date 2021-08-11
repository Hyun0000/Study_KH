-- 컬럼 추가
alter table test02
	add (t4 number);
    
alter table dept01
    add (t4 number);
    
-- 기존 컬럼명 변경    
alter table dept01
    rename column t4 to t1;
-- (rename column) --> rename은 바꾸고 싶은 것이 무엇인지 적어줘야 한다.
-- 제약조건 등 다양한게 들어올 수 있으니까

-- 수정(modify)
alter table dept01
    modify (t1 varchar2(30));
-- not null은 따로 추가 못 한다.
-- 이미 null이 기본값으로 들어가기 때문

alter table dept01
    modify (deptno varchar2(30));
    -- 안 된다. 이미 자료가 있는 경우에는 변경 불가

alter table dept01
    modify (loc varchar2(6));
    --크기가 작아 변경불가

alter table dept01
    modify (loc varchar2(120) DEFAULT '서울입니다.');
--    DEFAULT --> 아무것도 입력하지 않았을때 이것을 값으로 넣어주겠다.
-- 이건 제약조건이 아니다.

-- drop
-- 컬럼 삭제
alter table dept01
    DROP COLUMN loc;-- 뭘 지울지 명시 해줘야함 (컬럼을 지울지 제약조건을 지울지)
    
    select * from dept01;
    
    -- 제약조건 삭제
alter table dept01
    drop CONSTRAINT SYS_C007022;
    
drop table dept01;
-- dept01에 있는 PK가 외래키에 의해 참조되고 있는 형태라 못 지운다.
    
drop table dept01 CONSTRAINT CASECADE;
-- 그런건 난 모르겠고 나도 지워주고 나를 참조하고 CONSTRAINT까지 싹 다 지워줘

    
    
    

    



