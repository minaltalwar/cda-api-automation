package stepDefination;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;

public class Hooks {
	public static String cookieValue;
	
	@Before
	public void beforeScenario() throws Throwable
	{
		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://10.64.114.191/login.html");
		Thread.sleep(3000);
		driver.findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("etlwmnl");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Ericsson@123");
		driver.findElement(By.cssSelector("#sendLogin")).click();
		Thread.sleep(3000);
		
		Set<Cookie> cookies = driver.manage().getCookies();
		
		
        Iterator<Cookie> itr = cookies.iterator();
        while (itr.hasNext()) {
            Cookie cookie = itr.next();
            cookieValue = "SESSION="+cookie.getValue();
                    }
        System.out.println(cookieValue);
		
        driver.close();
	}

}
