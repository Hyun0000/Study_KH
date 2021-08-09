-----필수과제-----
--1. EMP테이블에서 COMM 의 값이 NULL이 아닌 정보 조회
select * from emp where comm is not null;

--2. EMP테이블에서 커미션을 받지 못하는 직원 조회
select * from emp where comm is null;

--3. EMP테이블에서 관리자가 없는 직원 정보 조회
select * from emp where mgr is null;

--4. EMP테이블에서 급여를 많이 받는 직원 순으로 조회
select * from emp ORDER BY sal desc;

--5. EMP테이블에서 급여가 같을 경우 커미션을 내림차순 정렬 조회
select * from emp ORDER BY sal desc, comm desc;

--6. EMP테이블에서 사원번호, 사원명, 직급, 입사일 조회 (단, 입사일을 오름차순 정렬 처리)
select deptno 사원번호, ename "사원명", job as 직급, hiredate 입사일 from emp ORDER BY hiredate;

--7. EMP테이블에서 사원번호, 사원명 조회 (사원번호 기준 내림차순 정렬)
select deptno 사원번호, ename "사원명" from emp ORDER by 사원번호 desc;

--8. EMP테이블에서 사번, 입사일, 사원명, 급여 조회
-- (부서번호가 빠른 순으로, 같은 부서번호일 때는 최근 입사일 순으로 처리)
select deptno 사번, hiredate 입사일, ename 사원명, sal 급여 from emp
ORDER by deptno asc, 입사일 desc;