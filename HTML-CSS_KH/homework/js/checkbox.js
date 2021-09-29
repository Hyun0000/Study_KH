window.onload = function () {

    document.getElementById('all').onclick = allCheck;

    function allCheck() {
        console.log("진입");
        // var inputEle = document.getElementById('A');
        // console.log(inputEle);




        var inputEle = document.getElementsByClassName('.category');
        console.log("진입2");

        for (const key in inputEle) {
            for (let i = 0; i < inputEle.length; i++) {
                console.log(inputEle[i]);
                
            }
        }

        // for (let i = 0; i < inputEle.length; i++) {
        //     console.log("진입3");
        //     console.log(inputEle[i].value);
        //     console.log("진입4");
        // }
    }

}



