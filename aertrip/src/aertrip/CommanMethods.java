package aertrip;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CommanMethods {

	public WebDriver driver= null;
	public void setUp(String browser){
		
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//resource//driver//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notification");

			
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		else{
			System.out.println("choose chrome browse only");
			// for other browser add code here
		}
	
	}
	public void launchurl(String urlpath){
		driver.get(urlpath);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}
	
	public void type(By xpath,String data){
		WebElement ele= driver.findElement(xpath);
	  //  wait.until(ExpectedConditions.visibilityOf(ele));
	    ele.clear();
	    ele.sendKeys(data);
		
	}
	public void click(By xpath){
		WebElement element= driver.findElement(xpath);
		element.click();
	}
   public void wait(int sec){
	   System.out.println("waited for "+ sec );
	   try {
		Thread.sleep(sec*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   
   public String getText(By xpath){
	   String ele= driver.findElement(xpath).getText();
	   return ele;
   }
	public void pageload(){
		System.out.println("waiting for page to load.........");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
	}
	public void datepicker(By datepickerloc ,String fromdate,String todate){
		WebElement ele = driver.findElement(datepickerloc);
		
		WebElement fromdateloc= driver.findElement(By.xpath("//*[@id='datepicker_519']/div[9]/table/tbody/tr/td[3]/table/tbody[2]/tr[6]/td[2]/a/span[text()="+fromdate+"]"));
		fromdateloc.click();
		System.out.println("chhose the date "+fromdate+" "+" from the date picker");
		WebElement todatelocator= driver.findElement(By.xpath("//*[@id='datepicker_519']/div[9]/table/tbody/tr/td[3]/table/tbody[2]/tr[6]/td[2]/a/span[text()="+todate+"]"));
		System.out.println("chhose the date "+fromdate+" "+" from the date picker");
		
		
	}
	//*[@id="datepicker_519"]/div[9]/table/tbody/tr/td[3]/table/tbody[2]/tr[6]/td[2]/a/span[text()='30']
	//*[@id="datepicker_519"]/div[9]/table/tbody/tr/td[3]/table/tbody[2]/tr[6]/td[1]/a/span[text()='30']
}


