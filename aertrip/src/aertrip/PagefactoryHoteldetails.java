package aertrip;

import org.openqa.selenium.By;

public class PagefactoryHoteldetails {
public static By sortby= By.xpath("//span[contains(text(),'Best Sellers')]");
public static By distancenearfirst= By.xpath("//li[contains(text(),'Distance - Nearest First')]");
public static By firsthotel=By.xpath("//*[@id=\"withinRange-hotels-js\"]/div[1]/div/figcaption");
public static By secoundoption=By.xpath("//*[@id=\"withinRange-hotels-js\"]/div[2]/div/figcaption");

public static By roomtypebox=By.xpath("//div[@class='css-room-type-box']");

//*[@id="withinRange-hotels-js"]/div[1]/div/figcaption  

public static By rangetab= By.xpath("//span[@class='withinRange-tab-js']");

public static By secoundlowestroom =By.xpath("(//span[text()='Only'])[2]");

public static By bookbutton = By.xpath("//button[@class='button line bookingConfirmation-js']");
	
}
