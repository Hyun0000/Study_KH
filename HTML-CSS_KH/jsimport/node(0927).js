window.onload = function () {
    document.getElementById('btnInsert').onclick = print;

    let eleCnt = 0;
    // 전역변수이면서 그 값이 계속 지속되는 변수, 단, refresh(새로고침)되면 다시 재정의된다.
    // classname 증가를 위한 변수 선언
    function print() {
        var inputEls = document.getElementsByClassName('iv');
        // 변수의 이름이 모두 다르므로 for문을 돌릴 수 없다.
        var name = inputEls[0].value;
        var ingredient = inputEls[1].value;
        var price = inputEls[2].value;
        var amount = inputEls[3].value;

        // 입력 후 버튼을 누를 때마다 노드(태그)를 추가로 생성
        var pEle = document.createElement('p');
        pEle.innerHTML = "";
        pEle.innerHTML += "구매자 : " + name + "님<br>";
        pEle.innerHTML += "구매 재료 : " + ingredient + "<br>";
        pEle.innerHTML += "단가 : " + price + "원<br>";
        pEle.innerHTML += "수량 : " + amount + "개<br>";
        pEle.innerHTML += "총가격 : " + (amount*price) + "원<br>";
        pEle.innerHTML += '<button>삭제</button>';
        // 텍스트로 입력한 태그(<br>)를 인식해야 하기에 innerHTML을 사용

        // (#btnInsert) 버튼을 누를 때마다 입력한 내용이 화면에 출력된다.
        document.body.appendChild(pEle);

        // ==================지금부터 사용하는 방식은 this 개념을 몰랐을 때 하는 방법이다.==================
        // <button>삭제</button>가 눌러지면 해당 구역의 내용이 사라지게 해보자
        // 이를 위해 <p>와 <button> 각각에 id 혹은 classname을 준다.
        // ex) button(동일 classname).click(함수 : <p>(동일 classname).remoce();)
        // 간단히 하기위해 중복이 되는 classname을 이용하자.
        // 이때 중요한건 화면에 새로운 내용이 출력될 때마다 SEQUENCE처럼 classname이 하나씩 증가하는 규칙을 갖고 있어야한다는 것이다.

        // dom을 이용해 생성한 <p> 태그에 class 속성을 추가해보자.
        // 이때 classname이 ele-1 / ele-2 / ele-3 / ele-4...처럼 계속 변화해야한다.
        // 이를 위해 let으로 변수를 선언한다.(let --> 재할당은 되지만 재선언은 안 된다.)
        pEle.setAttribute("class", "ele_"+(++eleCnt)); // (eleCnt++)으로 하면 (ele_0)부터 된다.
    }
}