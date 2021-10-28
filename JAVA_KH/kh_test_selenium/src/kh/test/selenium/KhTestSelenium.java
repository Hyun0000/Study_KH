package kh.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KhTestSelenium {
	public static void main(String[] args) {
		// 1.
		// main() method가 실행되면 ("chromedriver.exe")를 default로 시작할 것이다.
		// ("chromedriver.exe")에 대한 key는 ("webdriver.chrome.driver")이다.
		System.getProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		// 2.
		WebDriver dr1 = new ChromeDriver();
		// Chrome browser를 열기 위한 준비 끝 --> 이클립스와 Chrome browser 사이의 '연결통로가 생긴 것'이다.
		// selenium 라이브러리에서 WebDriver interface를 지원해준다. (import org.openqa.selenium.WebDriver;)
		// WebDriver는 interface 이기에 이것으로 instance를 만들수는 없다.
		// 따라서 WebDriver interface를 implements한 class로 WebDriver의 instance를 만든다.(다형성 적용)
		
		// 상세설명
		// (1). WebDriver : selenium이 web에 접속하기 위해 사용하는 interface
		// (2). ChromeDriver : 무슨 web에 접속할 것인지를 정하는 것
		// cf) 접속할 web이 Safari면 SafariDriver();로 생성
		
		// 3. "니가 설정한 10초 이상은 기다릴 수 없어(계속 대기 탈 수는 없다.)"
		WebDriverWait w1 = new WebDriverWait(dr1, 10); // 위에서 설정한 크롬창이 완전히 열릴때까지 10초 정도 기다려준다.
		// browser를 실행하면 그것이 바로 열리는 것이 아니기에 browser가 완전히 열릴때까지의 허용 대기 시간을 설정한다.
		// 기능테스트 중 허용가능 delay 시간도 여기서 설정된다.
		// 설정한 시간이 지나면 TimeoutException이 발생한다.
		// cf) 정확히 말하면 응답 대기시간을 설정한 것이다. 너무 짧으면 시간을 더 늘리면 된다.
		
		// 4. 
		dr1.get("https://iei.or.kr/login/login.kh");
		// 맨 처음 selenium이 실행됐을때 열릴 페이지를 설정한다. (URL값을 넣어주면 된다.)
		
		
		// 자동로그인을 실행해보자
		// 5.
		WebElement e1 = dr1.findElement(By.id("id"));
		// findElement(By.id("id")) : HTML의 id 속성값을 이용해 해당 element를 찾아서 반환해준다. 
		// 이때 아래의 element 형태로 뱉어낸다.
		// <input type="text" id="id" name="id" onkeypress="f_login();" placeholder="아이디" autofocus="">
		// (id="id") --> 이 값을 이용한 것이다.
		
		WebElement e2 = dr1.findElement(By.id("password"));
		// 반환 : <input type="password" id="password" name="password" onkeypress="f_login();" placeholder="비밀번호" style="margin-top:8px;">
		
		// 6.
		e1.sendKeys("wnfmawhddl");
		e2.sendKeys("wendy94@!");
		// e1.sendKeys("본인아이디"); e2.sendKeys("본인패스워드");
		// 위에서 findElement() method를 통해 반환받은 element에 자동으로 값이 넣어지도록 한다.
		// sendKeys() method의 (괄호)안에 넣은 값이 selenium 실행시 자동으로 입력된다.
		// 반환된 element에 key를 던진다고(보낸다고) 생각하면 된다.
		
		// 7.
		// ID와 PW를 입력했으니 로그인 버튼을 클랙해보자. 현재 로그인 버튼의 element 구성은 아래와 같다.
		// <a href="javascript:fnLogin();">로그인</a>
		// 즉, 지금 <a> 태그의 href 속성에 JS 함수가 걸려있는 것이다. 따라서 selenium에서 js 함수를 호출하는 방법을 알아보자
		JavascriptExecutor jsexe = (JavascriptExecutor) dr1;
		// JavascriptExecutor는 interface이다. ("나 js 실행하는 executor야")
		// JavascriptExecutor의 instance는(이건 엄밀히 맞는 표현이 아니다.) new로 생성하지 않는다.
		// 대신 위에서 만든 dr1을 이용하자
		// 현재 dr1은 그 자체가 Chrome browser인 것이다. 즉, dr1 안에는 js를 실행할 수 있는 부분이 들어있다는 것이다.
		// 따라서 dr1에서 해당 부분만 꺼내서 JavascriptExecutor의 instance를 만든다. (다운캐스팅??? 적용)
		
		// executeScript() : 인자로 넣은 JS function을 실행해라
		// jsexe.executeScript(arg0, arg1)에는 실행시킬 JS function를 넣으면 된다.
		// Object org.openqa.selenium.JavascriptExecutor.executeScript(String arg0, Object... arg1)
		// argument는 1개 이상만 넣으면 된다.
		jsexe.executeScript("fnLogin()"); // 로그인 성공시 완료 페이지로 이동한다.
		
		// 지금까지의 과정을 정리
		// 1. main() method 실행
		// 2. ("https://iei.or.kr/login/login.kh") page open
		// 3. e1.sendKeys("wnfmawhddl"); & e2.sendKeys("wendy94@!"); --> ID & PW '자동'입력
		// 4. 입력 후 로그인 버튼 '자동' 클릭
		// 5. 로그인 완료시 완료 페이지로 이동
		// 이런식으로 test가 이루어지는 것이다.
// ====================================================================================================
		// 팝업창 떴을때 확인 버튼 누르는 code
		// KH 사이트의 허접함으로 인해 아래의 코드를 추가해야한다.
		// 문제는 추가해도 팝업창 문제가 해결이 안 된다.
		if (ExpectedConditions.alertIsPresent().apply(dr1) == null) {
			System.out.println("망할 팝업창 NoNoNoNo등장");
			// 이때도 팝업창이 등장하는듯
			if (ExpectedConditions.alertIsPresent().apply(dr1) != null) {
				dr1.switchTo().alert().accept();
				jsexe.executeScript("fnLogin()");
			}
		} else {
			System.out.println("망할 팝업창 등장");
			dr1.switchTo().alert().accept();
			System.out.println("망할 팝업창 확인 클릭");
			jsexe.executeScript("fnLogin()");
		}
// ====================================================================================================
		// 8.
		// 우리반 게시판에 게시글을 써보자
		// 바로 jsexe.executeScript("location.href='/login/currBoard.kh'");을 이어가면 안 된다.
		// why?
		// js에서 onload event를 설정했듯이 selenium에서도 이러한 설정이 필요하다.
		// 즉, page가 완전히 load 되지 않은 상태에서는 ("location.href='/login/currBoard.kh'")을 찾을 수 없다는 것이다.
		// (페이지가 모두 load될때까지 '기다려야' 다음 js가 정상 실행된다.)
		
		// 이때 title이 나타나는 시점을 이용한다.
		// title은 page가 완전히 load돼야 나타난다.
		w1.until(ExpectedConditions.titleContains("KH정보교육원 - 마이페이지"));
		// ExpectedConditions : 특정 컨디션(조건)이 될때까지 기다릴 것이다.
		// titleContains("KH정보교육원 - 마이페이지") --> <title>의 내용이 ("KH정보교육원 - 마이페이지")이 될때까지 기다릴 것이다.
		
		// 그 후 (우리반 게시판) 링크를 클릭한다.
		jsexe.executeScript("location.href='/login/currBoard.kh'");
		
		w1.until(ExpectedConditions.titleContains("KH정보교육원 - 우리반 게시판"));
		// 이런걸 페이지 이동이 있을때마다 매번 넣어야한다.
		
		jsexe.executeScript("fnListChange('2')");
		
		// ("우리반 게시판") --> 이렇게 작성해도 실행에는 문제없다.
		// ("우리반") --> 이렇게만 해도 된다 ㅋㅋ
		w1.until(ExpectedConditions.titleContains("우리반"));
		// w1.until(ExpectedConditions.titleContains("KH정보교육원 - 우리반 게시판"));
		
		// 글쓰기 버튼 클릭
		jsexe.executeScript("fnWriteForm()");
		
		// ExpectedConditions.titleContains() 말고 다른 방식도 이용해보자
		// 동일하게 page가 완전히 load될때까지 기다려야하는데 이번에는 제목 입력칸을 클릭할 수 있을때까지 기다리는 것으로 설정해보자
		
		// 페이지 로딩될때까지 기다려야하는데 이번에는 title을 클릭할 수 있을때까지 기다리는것으로 설정
		// title element를 클릭할 수 있는지 확인 될때까지 기다린다.
		w1.until(ExpectedConditions.elementToBeClickable(By.id("title")));
		// w1.until(ExpectedConditions.elementToBeClickable(locator))
		// w1.until(ExpectedConditions.titleContains("KH정보교육원 - 우리반 게시판"));
		
		// WebElement e1 --> 여기에 재할당해도 된다.
		WebElement e3 = dr1.findElement(By.id("title"));
		e3.sendKeys("안녕하세요");
		
//		// WebElement e3 = dr1.findElement(By.id("title"));와 w1.until(ExpectedConditions.elementToBeClickable(By.id("title")));
//		// 의 순서를 바꾸면 안 된다. --> 요소가 없는데 찾는 꼴이다.
//		
//		// body안에 body가 있는게 문제이다.
//		// 프레임 이동
//		// wysiwyg 프레임으로 이동
//		dr1.switchTo().frame("tx_canvas_wysiwyg");
//		
//		// 내용 입력창
//		WebElement e4 = dr1.findElement(By.className("tx-content-container"));
//		
//		// 내용 입력
//		e4.sendKeys("frame 처음만난 사람 모두 안녕하세요 오 오 frame");
//		
//		// 다시 빠져 나온다.
//		// 부모 프레임(원본프레임)으로 이동
//		dr1.switchTo().parentFrame();
//		
//		// 등록 버튼 누르기
//		jsexe.executeScript("javascript:fnRegister();");
//		// javascript: --> 있어도 상관없다.
//		
//		
//		// 팝업 창으로 이동해 확인
//		// 입력 버튼을 누르면 등록 확인 팝업창이 뜬다.
//		// 이것은 프레임 이동은 아니지만 alert 창으로 이동은 해야한다.
//		dr1.switchTo().alert().accept();
			
		// 크롬창 닫고 resource 닫기
		// dr1.close();
		// 다 끝나면 닫아야한다. 꼭, 리소스(자원) 관리를 위해
		// 근데 이걸 활성화 시켜놓고 실행하면 창이 켜짐과 동시에 바로 닫혀버린다.
		
		// selector 복사
//		(1등) #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(1) > div.song_area > div.song > a
//		(2등) #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(2) > div.song_area > div.song > a
//		(3등) #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(3) > div.song_area > div.song > a
//		(4등) #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(4) > div.song_area > div.song > a
//		(5등) #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(5) > div.song_area > div.song > a
		// li:nth-child(1) > ul > li:nth-child(5) --> 이 부분만 바뀐다.
		
//		(6등) #content > div:nth-child(2) > div > div > div > ul > li:nth-child(2) > ul > li:nth-child(1) > div.song_area > div.song > a
		// li:nth-child(1) > ul > li:nth-child(5) --> 이 부분만 바뀐다.
		// for문을 이용해서 이것들을 뽑으면 된다.
		//======================================================================================
//		for (int i = 1; i <= 2; i++) {
//			for (int j = 1; j <= 5; j++) {
//				WebElement e = dr1.findElement(By.cssSelector("#content > div:nth-child(2) > div > div > div > ul > li:nth-child("+i+") > ul > li:nth-child("+j+") > div.song_area > div.song > a"));
//				// data 꺼내기
//				String innerText = e.getText();
//				// 안에 들어있는 text가 꺼내진다. cf) innerHTML 같은거 없다.
//			}
//		}
		//======================================================================================
		// 처음 이런 구조를 짤려면 시간이 좀 걸린다.
		
		// selector 복사
//		#content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(1) > div.song_area > div.artist > span > span:nth-child(1) > span > a
//		#content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(2) > div.song_area > div.artist > span > span:nth-child(1) > span > a
//		#content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(3) > div.song_area > div.artist > span > span:nth-child(1) > span:nth-child(1) > a
//		#content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(4) > div.song_area > div.artist > span > span:nth-child(1) > span > a
//		#content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(5) > div.song_area > div.artist > span > span:nth-child(1) > span:nth-child(1) > a
		
		// 샐렉터 너무 길어 Xpath 사용
		// (1등) //*[@id="content"]/div[1]/div/div/div/ul/li[1]/ul/li[1]/div[3]/div[1]/a
		// (2등) //*[@id="content"]/div[1]/div/div/div/ul/li[1]/ul/li[2]/div[3]/div[1]/a
		
		// (6등) //*[@id="content"]/div[1]/div/div/div/ul/li[2]/ul/li[1]/div[3]/div[1]/a
		//======================================================================================
//		for (int i = 1; i <= 2; i++) {
//			for (int j = 1; j <= 5; j++) {
//				WebElement e = dr1.findElement(By.cssSelector("#content > div:nth-child(2) > div > div > div > ul > li:nth-child("+i+") > ul > li:nth-child("+j+") > div.song_area > div.song > a"));
//				WebElement e10 = dr1.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div/div/ul/li[1]/ul/li[1]/div[3]/div[1]/a"));
//				// cf) xpath는 앞에 '//'가 있다.
//				// data 꺼내기
//				String innerText = e.getText();
//				// 안에 들어있는 text가 꺼내진다. cf) innerHTML 같은거 없다.
//				e.click(); // 아이유 상세 페이지 이동
//				// image, 데뷔년도, 장르등 내려받아 저장
//				
//				// 뒤로가기
//				jsexe.executeScript("history.back();");
//			}
//		}
		//======================================================================================
		// 여기서 뽑은 data를 VO 같은데 채운다음에 DB에 저장하러 가면 된다.
		// FK PK 구조상 데이터를 넣는 순서가 중요하다. --> 가수명을 먼저 넣어준 다음에 그에 해당하는 노래명을 넣어야한다.
		// 더 많은 정보를 얻으려면 해당 가수의 상세페이지로  또 이동
	}
}