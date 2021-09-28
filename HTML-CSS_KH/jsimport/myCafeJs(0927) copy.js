window.onload = function () {
    // 이벤트 등록
    document.getElementById('c1').onblur = update;
    document.getElementById('c2').onblur = update;
    document.getElementById('c3').onblur = update;
    // onkeyup --> 키보드를 떼는 순간
    // onfocus --> 포커스 in 되면
    // onblur --> 포커스 out 되면

    function update() {
        // 입력 수량
        var count1 = document.getElementById('c1').value;
        var count2 = document.getElementById('c2').value;
        var count3 = document.getElementById('c3').value;
        // 현재 count1,2,3은 숫자 형태가 아닌 '문자 형태'이다. by <input> 태그
        // 따라서 이것을 숫자로 형변환해야한다.

        // 형변환 1(정석) --> (+) 연산자 중 문자가 하나라도 있으면 전체가 문자 처리되므로 parseInt()를 사용
        // count1 = isNaN(count1) ? 0 : parseInt(count1);
        // count2 = isNaN(count2) ? 0 : parseInt(count2);
        // count3 = isNaN(count3) ? 0 : parseInt(count3);
        // 이게 귀찮으면 뽀록을 이용한다.

        // 형변환 2(뽀록) --> (*1)을 이용 : (문자열 * 숫자 = 숫자) 처리된다.
        // console.log(typeof "");은 String이다.
        count1 = isNaN(count1) ? 0 : count1 * 1;
        count2 = isNaN(count2) ? 0 : count2 * 1;
        count3 = isNaN(count3) ? 0 : count3 * 1;

        // 실패한 삼항연산자
        // count1 = typeof(count1)!=Number ? 0 : v1;
        // count2 = typeof(count2)!=Number ? 0 : v2;
        // count3 = typeof(count3)!=Number ? 0 : v3;
        // typeof 로는 원하는 결과를 도출할 수가 없다.
        // why? '<input>에서 꺼낸 value는 무조건 String 모양'을 갖추고 있기 때문이다.

        // 이렇게하면 원하는 결과를 얻을 수 있긴하지만 합계 중간중간에 NaN이 뜨는 문제가 있다.
        // console.log(typeof "");은 String이다. --> 이것이 NaN이 뜨는 이유의 핵심이다
        // count1 = typeof(count1)==Number ? 0 : parseInt(count1);
        // count2 = typeof(count2)==Number ? 0 : parseInt(count2);
        // count3 = typeof(count3)==Number ? 0 : parseInt(count3);

        // 각 메뉴 합계 가격 --> * 연산자로 인해 (문자열 * 숫자 = 숫자) 처리
        var t1 = count1 * 2500;
        var t2 = count2 * 3000;
        var t3 = count3 * 4500;

        // t1,2,3가 NaN일때 해결
        t1 = isNaN(t1) ? 0 : t1 * 1;
        t2 = isNaN(t2) ? 0 : t2 * 1;
        t3 = isNaN(t3) ? 0 : t3 * 1;

        // 수량 총 합계
        var totalCnt = count1 + count2 + count3;

        // 총 가격
        var totalPrice = t1 + t2 + t3;

        document.getElementById('c1Price').value = t1;
        document.getElementById('c2Price').value = t2;
        document.getElementById('c3Price').value = t3;

        document.getElementById('cTotalCnt').value = totalCnt;
        document.getElementById('cTotalPrice').value = totalPrice;
    }
}