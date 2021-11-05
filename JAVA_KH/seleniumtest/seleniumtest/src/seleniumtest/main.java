package seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class main {

	public static void main(String[] args) {
		System.getProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver wd = new ChromeDriver();
		WebDriverWait wdw = new WebDriverWait(wd, 10);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) wd;
		
		wd.get("https://www.iei.or.kr/main/main.kh?null");
		// 창 크기 최대화
		wd.manage().window().maximize();
		// 브라우저 크기가 작아 버튼 자체가 창에 보이지 않으면 아예 로그인 버튼 클릭 자체가 되지 않는다.
		// 코드에 문제가 있는게 아니라 물리적인 창 크기 자체로 인해 발생하는 문제이다. 
		
		// 팝업창 닫기
		WebElement we1 = wd.findElement(By.id("popup_mj_check"));
		we1.click();
		
		// 로그인 버튼 클릭
		WebElement we2 = wd.findElement(By.cssSelector("div.head_top_right > a:nth-child(1)"));
		we2.click();
		
		// 아이디 & 비밀번호 자동입력
		WebElement we3 = wd.findElement(By.id("id"));
		we3.sendKeys("아이디");
		
		WebElement we4 = wd.findElement(By.id("password"));
		we4.sendKeys("비밀번호");
		
		javascriptExecutor.executeScript("javascript:fnLogin()");
		
		if (ExpectedConditions.alertIsPresent().apply(wd) == null) {
			System.out.println("망할 팝업창 NoNoNoNo등장");
			// 이때도 팝업창이 등장하는듯
			if (ExpectedConditions.alertIsPresent().apply(wd) != null) {
				wd.switchTo().alert().accept();
				javascriptExecutor.executeScript("fnLogin()");
			}
		} else {
			System.out.println("망할 팝업창 등장");
			wd.switchTo().alert().accept();
			System.out.println("망할 팝업창 확인 클릭");
			javascriptExecutor.executeScript("fnLogin()");
		}
		
		wdw.until(ExpectedConditions.titleContains("마이페이지"));
		
		javascriptExecutor.executeScript("location.href='/login/currBoard.kh'");
		
		wdw.until(ExpectedConditions.titleContains("KH"));
		
		javascriptExecutor.executeScript("javascript:fnListChange('2')");
		
		wdw.until(ExpectedConditions.elementToBeClickable(By.className("common_btn2")));
		
		javascriptExecutor.executeScript("javascript:fnWriteForm()");
		
		wdw.until(ExpectedConditions.elementToBeClickable(By.id("title")));
		
		WebElement we5 = wd.findElement(By.id("title"));
		we5.sendKeys("시험 대비 연습 글 제목");
		
		wd.switchTo().frame("tx_canvas_wysiwyg");
		
		WebElement we6 = wd.findElement(By.cssSelector("body > p"));
		we6.sendKeys("시험 대비 연습 글 내용");
		
		wd.switchTo().parentFrame();
		
//		javascriptExecutor.executeScript("javascript:fnRegister()");
//		
//		wd.switchTo().alert().accept();
//		
//		wd.close();
		
	}
}