-- 저번주(2021.08.06)에 만들었던 테이블을 지우고 동일한 명으로 새로 만들었다.

-- 계산식도 쓸수 있다.
SELECT ENAME, JOB, 34*5 FROM EMP;

-- 함수도 쓸 수 있다. --> NVL()
SELECT ENAME, JOB, 34*5, NVL(COMM, 0), COMM FROM EMP;
-- NVL(COMM,0) --> COMM 열에서 null value가 있다면 '0'으로 대신 나타내라
--------------------------------------------------------------------------------------------
-- 별칭(별명)을 쓸 수 있다. 설정한 별칭이 컬럼명 대신 들어간다.
-- 별칭은 띄어쓰기로만 구분해도 된다. 작은 따옴표는 필요없다.
-- AS는 붙여도 되고 안 붙여도 된다.

-- 만약(의미없는숫자계산)을 (의미없는 숫자 계산)과 같이 띄어쓰기를 하면 실행에 오류가 난다.
-- why? 별칭을 띄어쓰기로 구분하기 때문에 오류가 나는 것이다.
-- 이럴때는 "의미없는 숫자 계산"과 같이 "큰 따옴표"로 묶는다.(특수문자도 동일)

-- SAL열 값에 '만원'을 붙여서 표시하고 싶다.
-- SAL||'만원' (|| --> 연결자)
-- 별칭은 큰 따옴표 / 그 외에 연결자 등 데이터로 쓰일때는 작은 따옴표를 쓴다.
SELECT ENAME AS 사원명, JOB, 34 * 5 "의미없는 숫자계산", NVL(COMM,0) , COMM, SAL||'만원' 월급
FROM EMP;
--------------------------------------------------------------------------------------------
-- 사원명과 연봉을 표시해주세요 (연봉은 SAL*12 + COMM 만큼 나타내라)
SELECT ENAME 사원명, SAL*12 + NVL(COMM,0)||'만원' 연봉 FROM EMP;
-- NVL(COMM,0)을 쓰는 이유 --> COMM 컬럼에 NULL 값이 있는데
-- NULL 값이 있으면 계산이 안된다.
-- 그래서 COMM 대신에 NVL(COMM,0)을 넣어준다.
--------------------------------------------------------------------------------------------
-- WHERE에는 조건식이 들어간다.
-- 단, 컬럼을 기준으로 비교 연산자와 비교값을 넣어준다.

-- 급여SAL이 2500이상인 사원명과 급여를 나타내라.
-- 이거 문법순서 무조건 지켜야한다.(순서 바뀌는 경우는 절대 없다.)
SELECT ENAME 사원명, SAL FROM EMP 
WHERE SAL >= 2500;

-- 급여SAL이 2500이상인 사원명과 급여를 나타내라.
-- 단, 급여가 높은 순으로 표시해라
-- ORDER BY도 컬럼을 기준으로 비교한다.
SELECT ENAME 사원명, SAL FROM EMP 
WHERE SAL >= 2500 ORDER BY SAL;
-- 아무것도 안 적으면 오름차순이 디폴트 (ASC)
-- ASC 써도 되고 안 써도되고

-- DESC --> 내림차순
SELECT ENAME 사원명, SAL FROM EMP 
WHERE SAL >= 2500 ORDER BY SAL DESC;

-- 급여SAL이 2500 미만인 사원명과 부서번호를 사원명순으로 표시
SELECT ENAME 사원명, DEPTNO 부서번호 FROM EMP
WHERE SAL < 2500 ORDER BY 사원명;
--------------------------------------------------------------------------------------------
-- 키워드, 컬럼, 테이블 등은 대소문자 구분 X
-- 대소문자를 구분 하는 경우는 아래와 같다.
select ename, deptno from emp
where ename = 'SMITH';
-- 이미 들어간 데이터, 고유명사 같은 데이터는 대소문자를 구분한다.
-- 즉, 'Data 값'에 대해서는 대소문자를 구분한다.
-- SMITH는 입력 자체가 대문자로 됐기에 찾을때도 대소문자를 구분해야하는 것이다.
-- 대소문자 구분 --> 도메인, 인스턴스, 레코드 등
--------------------------------------------------------------------------------------------
-- 중복제거
-- 우리 회사의 부서번호를 나타내 주세요
SELECT DISTINCT deptno from emp;

-- 부서번호별로 어떤 직군이 있는지 나타내 주세요
select DISTINCT deptno 부서번호, job from emp
ORDER BY 부서번호;
------------------
select * from emp where empno between 7430 and 7876;
select * from emp where empno not between 7430 and 7876;

select * from emp where deptno in (10, 20);
select * from emp where deptno not in (10, 20);

select * from emp where comm is null;
select * from emp where comm is not null;

select * from emp where ename like 'S%';
-- 이름이 대문자 S로 시작하는 애들 다 뽑아줘
-- % --> 비스무리한 애들로 다 찾아줘
-- S% --> 대문자 S로 '시작'하는 애들을 다 뽑아줘

select * from emp where ename like '%S%';
-- 이름중에 대문자 S가 들어가 있으면 다 찾아줘

select * from emp where ename like '_S%';
-- 이름에서 두번째가 S인 사람을 찾아줘