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
		// â ũ�� �ִ�ȭ
		wd.manage().window().maximize();
		// ������ ũ�Ⱑ �۾� ��ư ��ü�� â�� ������ ������ �ƿ� �α��� ��ư Ŭ�� ��ü�� ���� �ʴ´�.
		// �ڵ忡 ������ �ִ°� �ƴ϶� �������� â ũ�� ��ü�� ���� �߻��ϴ� �����̴�. 
		
		// �˾�â �ݱ�
		WebElement we1 = wd.findElement(By.id("popup_mj_check"));
		we1.click();
		
		// �α��� ��ư Ŭ��
		WebElement we2 = wd.findElement(By.cssSelector("div.head_top_right > a:nth-child(1)"));
		we2.click();
		
		// ���̵� & ��й�ȣ �ڵ��Է�
		WebElement we3 = wd.findElement(By.id("id"));
		we3.sendKeys("���̵�");
		
		WebElement we4 = wd.findElement(By.id("password"));
		we4.sendKeys("��й�ȣ");
		
		javascriptExecutor.executeScript("javascript:fnLogin()");
		
		if (ExpectedConditions.alertIsPresent().apply(wd) == null) {
			System.out.println("���� �˾�â NoNoNoNo����");
			// �̶��� �˾�â�� �����ϴµ�
			if (ExpectedConditions.alertIsPresent().apply(wd) != null) {
				wd.switchTo().alert().accept();
				javascriptExecutor.executeScript("fnLogin()");
			}
		} else {
			System.out.println("���� �˾�â ����");
			wd.switchTo().alert().accept();
			System.out.println("���� �˾�â Ȯ�� Ŭ��");
			javascriptExecutor.executeScript("fnLogin()");
		}
		
		wdw.until(ExpectedConditions.titleContains("����������"));
		
		javascriptExecutor.executeScript("location.href='/login/currBoard.kh'");
		
		wdw.until(ExpectedConditions.titleContains("KH"));
		
		javascriptExecutor.executeScript("javascript:fnListChange('2')");
		
		wdw.until(ExpectedConditions.elementToBeClickable(By.className("common_btn2")));
		
		javascriptExecutor.executeScript("javascript:fnWriteForm()");
		
		wdw.until(ExpectedConditions.elementToBeClickable(By.id("title")));
		
		WebElement we5 = wd.findElement(By.id("title"));
		we5.sendKeys("���� ��� ���� �� ����");
		
		wd.switchTo().frame("tx_canvas_wysiwyg");
		
		WebElement we6 = wd.findElement(By.cssSelector("body > p"));
		we6.sendKeys("���� ��� ���� �� ����");
		
		wd.switchTo().parentFrame();
		
//		javascriptExecutor.executeScript("javascript:fnRegister()");
//		
//		wd.switchTo().alert().accept();
//		
//		wd.close();
		
	}
}