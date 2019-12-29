package aertrip;

import org.openqa.selenium.By;
/**
 * Page factory used to store locator of the search hotel and choose hotel page
 * @author Deepak Pal
 *
 */
public class PagefactorySearchHotel {
	
public static By hoteltablink=By.xpath("//li[@id='hotels-module']");
public static By citybox= By.xpath("//input[@placeholder='City, Area or Hotels']");
public static By checkindate= By.xpath("//input[@class=\"datepicker\" and @placeholder=\"Check-in\"]");
public static By checkoutdate=By.xpath("//input[@placeholder='Check-out']");

public static By sundaydate= By.xpath("//*[@id='datepicker_116']/div[9]/table/tbody/tr/td[3]/table/tbody[2]/tr[4]/td[7]");
public static By threedayafterSunday = By.xpath("//*[@id='datepicker_116']/div[9]/table/tbody/tr/td[3]/table/tbody[2]/tr[5]/td[4]");
public static By chooseroombutton= By.xpath("//input[@placeholder='1 Adult']");

public static By adultselectionlocator = By.xpath("//*[@id='guests']/div[2]/div[2]/div[2]/i[2]");
public static By addroomsbutton = By.xpath("//div[text()='+Add rooms ']");
public static By searchhotelbutton=By.xpath("//button[@id='searchHotelButton']");

public static By nearbytab=By.xpath("//span[@class='nearby-tab-js']");

public static By nearesthotelLink =By.xpath("//*[@id='nearby-hotels-js']/div[1]/div/figcaption");

public static By firsthotel=By.xpath("//figcaption[@iscurrent='1']");
public static By firsthotelBookbutton= By.xpath("//button[text()='Book']");

}
