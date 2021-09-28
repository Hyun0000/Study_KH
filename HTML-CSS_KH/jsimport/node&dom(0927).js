window.onload = function () {
    document.getElementById('btnInsert').onclick = print;

    let eleCnt = 0;
    // 전역변수이면서 그 값이 계속 지속되는 변수, 단, refresh(새로고침)되면 다시 재정의된다.
    // classname 증가를 위한 변수 선언
    function print() {
        // 입력 후 버튼을 누를 때마다 노드(태그)를 추가로 생성
        var pEle = document.createElement('p');

        eleCnt++;
        // 여기서 아예 증가시킨후 (++)없이 변수명만 아래에 대입한다.

        var inputEls = document.getElementsByClassName('iv');
        // 변수의 이름이 모두 다르므로 for문을 돌릴 수 없다.
        var name = inputEls[0].value.trim();
        var ingredient = inputEls[1].value.trim();
        var price = inputEls[2].value.trim();
        var amount = inputEls[3].value.trim();
        // trim() --> value에서 띄어쓰기, 불필요한 공백, 사용자의 실수로 인해 발생한 공백 제거

        pEle.innerHTML = ""; // 텍스트로 입력한 태그(<br>)를 인식해야 하기에 innerHTML을 사용
        pEle.innerHTML += "구매자 : " + name + "님<br>";
        pEle.innerHTML += "구매 재료 : " + ingredient + "<br>";
        pEle.innerHTML += "단가 : " + price + "원<br>";
        pEle.innerHTML += "수량 : " + amount + "개<br>";
        pEle.innerHTML += "총가격 : " + (amount*price) + "원<br>";
        // <button>에도 classname을 지정해보자
        // pEle.innerHTML += '<button class="bele ele_'+eleCnt+'">삭제</button>'; --> class로는 event 등록 불가

        // button 생성 방법 1
        // pEle.innerHTML += '<button id="ele_'+eleCnt+'">삭제</button>';
        // class 속성을 id 속성으로 변경
        // 따옴표 주의

        // button 생성 방법 2
        var btnEle = document.createElement("button");
        btnEle.setAttribute("id", "ele_"+eleCnt);
        btnEle.innerText = "삭제";
        pEle.appendChild(btnEle);

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
        // 이를 위해 let으로 '전역변수를 선언'한다.(let --> 재할당은 되지만 재선언은 안 된다.)
        // pEle.setAttribute("class", "ele_"+(++eleCnt)); // (eleCnt++)으로 하면 (ele_0)부터 된다.

        // (this.parentElement)를 사용하면 해당 코드도 필요없다.
        pEle.setAttribute("class", "pele ele_" + eleCnt);
        // 얘는 굳이 바꿀 필요없다. 어차피 클릭하는건 <button>이니까

        // 버튼 클릭시 삭제 이벤트 등록
        // classname이 ("ele_" + eleCnt) 상태일때는 getElementsByClassName으로 이벤트를 등록할 수 없다.
        // why?
        // classname은 중복되는데 JS에서는 여러개의 태그에 한꺼번에 event를 추가할 수 없다.
        // 또한 {classname = ("ele_" + eleCnt)}인 <button>을 눌러서 지우고 싶은데 classname은 중복이 되므로
        // {classname = ("ele_" + eleCnt)}인 <p> 태그를 눌러도 글이 삭제되기 때문이다. 

        // 따라서 각각 다른 classname을 또 추가한다.
        // (<button> --> bele / <p> --> pele) classname을 추가

        // 사실 애초에 getElementsByClassName()은 해당하는 class의 개수에 상관없이 '무조건 배열형태로 반환'하기 때문에
        // 애초에 getElementsByClassName()을 이용해서는 event 자체를 걸수가 없다.
        // (요소가 하나만 들어있는 배열이더라도 배열 자체에는 event를 등록할 수가 없다.)
        // document.getElementsByClassName('ele').onclick = function () {} --> 무슨 수를 쓰더라도 event 등록 불가

        // 따라서 결국 id를 이용해야한다.
        // 기존 <button>, <p>에 있던 class 속성을 id로 바꾼다.
        //  --> 얘는 굳이 바꿀 필요없다. 어차피 클릭하는건 <button>이니까
        document.getElementById("ele_" + eleCnt).onclick = function() {
            // 클릭 이벤트는 반드시 print 함수안에 있어야한다.
            // 그래야 위의 값들을 인식할 수 있다.
            // 밖에 있으면 print 함수의 지역변수들을 읽을 수가 없다.
            
            // document.getElementById("ele_" + eleCnt).remove();
            // 이 방법도 불가능하다.
            // why?
            // ID는 중복이 불가능하다. 따라서 ("pele ele_" + eleCnt)와 같이 띄어쓰기를 해서
            // ID 값을 여러개 지정하는 것이 불가능하다.
            // 만약 그래도 <p> 태그를 지우고 싶어 getElementById('pele')와 같이 설정하면
            // 특정 구역이 아닌 전체 <p> 태그 영역이 지워진다.
            // 그렇다고 해서 getElementById("ele_" + eleCnt)로 지정하면 현재 <button>의 id값이 ("ele_" + eleCnt)이기에
            // ID가 중복되는 현상이 발생한다.

            // 이럴때 필요한 개념이 바로 'this'이다.
            this.parentElement.remove();
            // 여기서 this는 getElementById("ele_" + eleCnt) 즉, <button>을 의미한다.
            // 삭제 버튼을 누르면 나의 부모 <p>를 찾아가서 나의 부모를 지워줘라
            // 이때도 'SEQUENCE처럼 증가하는 ID 이름' 이런 개념은 필요하다.
            // 각각 하나의 element에다가 각각의 event를 걸어야하기 때문이다.
        }

        // 이렇게도 가능
        document.getElementById("ele_" + eleCnt).onclick = function(event) {
            event.target.parentElement.remove();
            // e.target.parentElement.remove(); --> 이렇게 사용하기도 한다.(parameter도 e로 수정)
            // event.target --> 클릭이 이루어진 element
        }
    }
}