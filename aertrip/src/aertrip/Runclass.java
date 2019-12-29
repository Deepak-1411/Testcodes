package aertrip;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * testNG framework used to run the cases on priority basic
 * 
 * @author Deepak Pal
 *
 */

public class Runclass {
	ExtentTest test;
	ExtentReports report;
	ChromeOptions options;
	WebDriver driver;

	// code to login in to the appplication
	@Test(priority = 1)
	public void login() {
		driver.get("https://aertrip.com/");
		test.log(LogStatus.PASS, "navigated to the url");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='registerSignIn js-register-signin']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
			driver.findElement(PagefactoryLogin.uname).sendKeys("deepakpal1411@gmail.com");
			driver.findElement(PagefactoryLogin.pass).sendKeys("password");
			driver.findElement(PagefactoryLogin.loginbutton).click();
			test.log(LogStatus.PASS, "logged in to website");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Looged into the application");
			e.printStackTrace();
		}
	}

	// function to type the location and choose the check in date and checkout date
	@Test(priority = 2)
	public void searchhotel() {

		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			waitfor(10);
			WebElement ele = driver.findElement(PagefactorySearchHotel.hoteltablink);
			System.out.println(ele.isEnabled());
			driver.findElement(PagefactorySearchHotel.hoteltablink).click();
			waitfor(5);
			driver.findElement(PagefactorySearchHotel.citybox).clear();
			driver.findElement(PagefactorySearchHotel.citybox).sendKeys("Mumbai, Maharashtra, India");
			waitfor(4);

			test.log(LogStatus.PASS, "entered the location details");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "unable to enter the location details");
			e.printStackTrace();
		}
		try {
			// datepicker(PagefactorySearchHotel.checkindate, "31", "4");
			driver.findElement(PagefactorySearchHotel.checkindate).clear();
			driver.findElement(PagefactorySearchHotel.checkoutdate).clear();
			driver.findElement(PagefactorySearchHotel.checkindate).click();
		//	datepicker1();
			driver.findElement(By.xpath("//td[@class='datepickerSunday']//span[contains(text(),'5')]")).click();
			driver.findElement(By.xpath("//td[5]//table[1]//tbody[2]//tr[2]//td[4]//a[1]//span[text()='9']")).click();

			test.log(LogStatus.PASS, "selected the check in and checkout date");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to enter the date");
			e.printStackTrace();
		}

		/*
		 * driver.findElement(PagefactorySearchHotel.sundaydate).click();
		 * driver.findElement(PagefactorySearchHotel.threedayafterSunday).click();
		 */

		driver.findElement(PagefactorySearchHotel.chooseroombutton).click();
		waitfor(4);

	}
	// function to select the total room and number of the guest and enter there age
	// and this method using the customized function

	@Test(priority = 3)
	public void roomselection() {
		try {
			adultselectionroom1(2);
			childselectionroom1(1, "10", "0", "0");
			waitfor(4);
			test.log(LogStatus.PASS, "selected the people for room");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select the people for room1");
			e.printStackTrace();
		}

		driver.findElement(PagefactorySearchHotel.addroomsbutton).click();
		try {
			adultselectionroom2(3);
			childselectionroom2(2, "10", "12", "0");
			test.log(LogStatus.PASS, "selected people for room2");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "unable to select people for room2");
			e.printStackTrace();
		}
		driver.findElement(PagefactorySearchHotel.searchhotelbutton).click();

	}

	@Test(priority = 4)
	public void selectSecoundlowestRoom() {
		waitfor(10);
		driver.findElement(PagefactoryHoteldetails.sortby).click();
		driver.findElement(PagefactoryHoteldetails.distancenearfirst).click();
		waitfor(5);
		driver.findElement(PagefactoryHoteldetails.rangetab).click();
		waitfor(7);

	}
	
	//function to find and book the second lowest rate room from hotel
    @Test(priority=5)
	public void booksecoundlowesthotel() {

		List<WebElement> listofhotels = driver.findElements(By.xpath("//figcaption"));

		for (int i = 1; i < listofhotels.size(); i++) {
			driver.findElement(By.xpath("//*[@id=\"withinRange-hotels-js\"]/div[" + i + "]/div/figcaption")).click();
			waitfor(6);
			driver.findElement(PagefactoryHoteldetails.roomtypebox).click();

			boolean rooms= false;
			try {
				rooms = driver.findElement(PagefactoryHoteldetails.secoundlowestroom).isDisplayed();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (rooms) {
                driver.findElement(PagefactoryHoteldetails.secoundlowestroom).click();   
                driver.findElement(PagefactoryHoteldetails.bookbutton).click();
			}
		}
		
	}

	// this method is to take the screenshot for all the test
	// this will run before test
	@BeforeMethod
	public void beforeMethod() {
		takepicture();
	}

	// this method will run after each test and take the screen shot \
	@AfterMethod
	public void afterMethod() {
		takepicture();
	}

	// this will run before methode once
	// this will initiate the extent report objects
	@BeforeClass
	public void beforeClass() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		test = report.startTest("aertest");

	}

//tear down of the application it will run at the end
//contain code to close the browser and create the summary report for the testcase
	@AfterClass
	public void afterClass() {

		driver.quit();
		report.endTest(test);

		report.flush();

	}

//function to setup and initiate the chrome browser
	// this will run once
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//resource//driver//chromedriver.exe");
		options = new ChromeOptions();
		options.addArguments("--disable-notification");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	// below are the function for reuse or customise the flow
	public void waitfor(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//customized funtion to do dynemic selection of the room and guest
	public void adultselectionroom1(int adultcount) {
		if (adultcount == 1) {
			System.out.println("one adult is already selected");

		}
		if (adultcount == 2) {
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[2]/i[2]")).click();
		}
		if (adultcount == 3) {
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[2]/i[3]")).click();
		}
	}

	public void adultselectionroom2(int adultcount) {
		if (adultcount == 1) {
			System.out.println("one adult is already selected");

		}
		if (adultcount == 2) {
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[2]/i[2]")).click();
		}
		if (adultcount == 3) {
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[2]/i[3]")).click();
		}
	}

	public void childselectionroom1(int childcount, String age1, String age2, String age3) {
		if (childcount == 1) {
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[3]/div[1]/i")).click();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[3]/div[2]/span/input")).clear();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[3]/div[2]/span/input")).sendKeys(age1);

		}
		if (childcount == 2) {
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[3]/div[1]/i")).click();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[3]/div[2]/span/input")).clear();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[3]/div[2]/span/input")).sendKeys(age1);

			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[3]/div[3]/i")).click();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[2]/div[4]/div[2]/span/input")).sendKeys(age2);
		}
	}

	public void childselectionroom2(int childcount, String age1, String age2, String age3) {
		if (childcount == 1) {
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[1]/i")).click();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[2]/span/input")).clear();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[2]/span/input")).sendKeys(age1);

		}
		if (childcount == 2) {
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[1]/i")).click();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[2]/span/input")).clear();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[2]/span/input")).sendKeys(age1);

			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[3]/i")).click();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[4]/span/input")).clear();
			driver.findElement(By.xpath("//*[@id='guests']/div[2]/div[3]/div[3]/div[4]/span/input")).sendKeys(age2);
		}
	}

//function to take the screen shot and save it in same dir inside the pic folder and
//using date stamp as name
	public void takepicture() {
		String pattern = "dd_mm_yyyy_hh_mm_ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String Date = dateFormat.format(new Date());
		System.out.println(Date);
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(System.getProperty("user.dir") + "\\pics\\" + Date + ".png");
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	 String monthandyear = "January, 2020";
	 String checkindate ="5";
	 String checkoutdate="9";
		public  void datepicker1() throws InterruptedException {
			driver.findElement(PagefactorySearchHotel.checkindate).clear();
			driver.findElement(PagefactorySearchHotel.checkoutdate).clear();
			
			driver.findElement(PagefactorySearchHotel.checkindate).click();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			  while(true) {
				  Thread.sleep(3000);
			  String month=driver.findElement(By.xpath("(//div[9]/table/tbody/tr/td[3]/table/thead/tr[1]/th[2]/a/span)[2]")).getText(); 
			  System.out.println(month);
			  if(month.equalsIgnoreCase(monthandyear)) {
				  break;
				  }else {
			  driver.findElement(By.xpath("//td[5]//table[1]//thead[1]//tr[1]//th[3]")).
			  click(); } 
			  }
			 
			//table/tbody/tr/td[3]/table/tbody[2]/tr//td//a/span
		List<WebElement> alldates =driver.findElements(By.xpath("//div[9]/table/tbody/tr/td[3]/table/tbody[2]/tr/td/a/span"));
		
		for(WebElement ele:alldates) {
			String date1=ele.getText();
			if(date1.equals(checkindate)) {
				ele.click();
			}
		}
		
		List<WebElement> alldates2= driver.findElements(By.xpath("//div[9]/table/tbody/tr/td[3]/table/tbody[2]/tr/td/a/span"));
		for(WebElement ele:alldates) {
			String date2 = ele.getText();
			if(date2.equals(checkoutdate)) {
				ele.click();
			}
		}
		}
	
	
	
	
//customized date picker select the date by passing the check in and checkput date and locator of the date picker //limited to this application only
	public void datepicker(By loc, String checkindate, String checkoutdate) {
		WebElement element = driver.findElement(loc);
		element.click();
//code to take dynemic date by passing through the parameter, for check in date
		try {
			String row6 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[3]/table/tbody[2][@class='datepickerDays']/tr[6]/td/a/span[text()="
					+ checkindate + "]";
			By indate = By.xpath(row6);
			WebElement element1 = driver.findElement(indate);
			element1.click();
		} catch (Exception p) {
			try {
				String row5 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[3]/table/tbody[2][@class='datepickerDays']/tr[5]/td/a/span[text()="
						+ checkindate + "]";
				By indate = By.xpath(row5);
				WebElement element1 = driver.findElement(indate);
				element1.click();
			} catch (Exception r) {
				try {
					String row4 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[3]/table/tbody[2][@class='datepickerDays']/tr[4]/td/a/span[text()="
							+ checkindate + "]";
					By indate = By.xpath(row4);
					WebElement element1 = driver.findElement(indate);
					element1.click();
				} catch (Exception s) {
					try {
						String row3 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[3]/table/tbody[2][@class='datepickerDays']/tr[3]/td/a/span[text()="
								+ checkindate + "]";
						By indate = By.xpath(row3);
						WebElement element1 = driver.findElement(indate);
						element1.click();
					} catch (Exception e1) {
						try {
							String row2 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[3]/table/tbody[2][@class='datepickerDays']/tr[2]/td/a/span[text()="
									+ checkindate + "]";
							By indate = By.xpath(row2);
							WebElement element1 = driver.findElement(indate);
							element1.click();
						} catch (Exception t) {
							String row1 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[3]/table/tbody[2][@class='datepickerDays']/tr[1]/td/a/span[text()="
									+ checkindate + "]";
							By indate = By.xpath(row1);
							WebElement element1 = driver.findElement(indate);
							element1.click();

						}
					}
				}
			}
		}

		// code to take the check out date dynemic by passing through the parameter
		// div[@class="datepickerContainer"])[5]/table/tbody/tr/td[5]/table/tbody[2][@class='datepickerDays']/tr[1]/td/a/span[text()='4']
		try {
			String row6 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[5]/table/tbody[2][@class='datepickerDays']/tr[6]/td/a/span[text()="
					+ checkindate + "]";
			By indate = By.xpath(row6);
			WebElement element1 = driver.findElement(indate);
			element1.click();
		} catch (Exception p) {
			try {
				String row5 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[5]/table/tbody[2][@class='datepickerDays']/tr[5]/td/a/span[text()="
						+ checkoutdate + "]";
				By indate = By.xpath(row5);
				WebElement element1 = driver.findElement(indate);
				element1.click();
			} catch (Exception r) {
				try {
					String row4 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[5]/table/tbody[2][@class='datepickerDays']/tr[4]/td/a/span[text()="
							+ checkoutdate + "]";
					By indate = By.xpath(row4);
					WebElement element1 = driver.findElement(indate);
					element1.click();
				} catch (Exception s) {
					try {
						String row3 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[5]/table/tbody[2][@class='datepickerDays']/tr[3]/td/a/span[text()="
								+ checkoutdate + "]";
						By indate = By.xpath(row3);
						WebElement element1 = driver.findElement(indate);
						element1.click();
					} catch (Exception e1) {
						try {
							String row2 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[5]/table/tbody[2][@class='datepickerDays']/tr[2]/td/a/span[text()="
									+ checkoutdate + "]";
							By indate = By.xpath(row2);
							WebElement element1 = driver.findElement(indate);
							element1.click();
						} catch (Exception t) {
							String row1 = "(//div[@class='datepickerContainer'])[5]/table/tbody/tr[1]/td[5]/table/tbody[2][@class='datepickerDays']/tr[1]/td/a/span[text()="
									+ checkoutdate + "]";
							By indate = By.xpath(row1);
							WebElement element1 = driver.findElement(indate);
							element1.click();

						}
					}
				}
			}
		}
	}

}
