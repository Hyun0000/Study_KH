$(function () {
    $('.c').keyup(update);
    // $('.c').blur(update);

    function update() {
        console.log('ㅅㅂ');
        var v1 = $('#c1').val();
        var v2 = $('#c2').val();
        var v3 = $('#c3').val();
        // jQurey에서는 .value 가 아니라 val() 을 사용
        // val() 또한 js의 다른 method와 마찬가지로 읽기 & 쓰기 동시에 가능

        v1 = isNaN(v1) ? 0 : v1*1;
        v2 = isNaN(v2) ? 0 : v2*1;
        v3 = isNaN(v3) ? 0 : v3*1;
        
        var p1 = 2500 * v1;
        var p2 = 3000 * v2;
        var p3 = 4500 * v3;

        p1 = isNaN(p1) ? 0 : parseInt(p1);
        p2 = isNaN(p2) ? 0 : parseInt(p2);
        p3 = isNaN(p3) ? 0 : parseInt(p3);

        $('#c1Price').val(p1);
        $('#c2Price').val(p2);
        $('#c3Price').val(p3);

        var totalCnt = v1 + v2 + v3;
        var totalPrice = p1 + p2 + p3;

        $('#cTotalCnt').val(totalCnt);
        // $('#cTotalCnt').val(v1*1+v2*1+v3*1); --> 뽀록
        $('#cTotalPrice').val(totalPrice);
    }
});