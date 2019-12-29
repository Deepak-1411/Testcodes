package aertrip;

import org.openqa.selenium.By;

/** page factory , used to store locator for the login page of the application using By class
 * 
 * @author Deepak Pal
 */
public class PagefactoryLogin {

	public static By register_signIn= By.xpath("//div[@class='registerSignIn js-register-signin']");
	public static By loginpagetextvalidation= By.xpath("//div[text()='Login with']");
	public static By uname = By.xpath("//input[@id='email']");
	public static By pass= By.xpath("//input[@id='password']");
	public static By loginbutton=By.xpath("//input[@id='login_submit']");
}
