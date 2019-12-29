package aertrip;


public class Testcases extends CommanMethods {
	public void loginAertrip(){
		setUp("chrome");
		launchurl("https://aertrip.com/");
		wait(4);
		click(PagefactoryLogin.register_signIn);
		wait(3);
		type(PagefactoryLogin.uname, "deepakpal1411@gmail.com");
		wait(3);
		type(PagefactoryLogin.pass,"password");
		wait(3);
		click(PagefactoryLogin.loginbutton);
		pageload();
	}
	
	public void searchHotel(){
		
		click(PagefactorySearchHotel.hoteltablink);
		//pageload();
		wait(5);
		type(PagefactorySearchHotel.citybox, "Andheri East, Mumbai, Maharashtra, India");
		wait(2);
		click(PagefactorySearchHotel.checkindate);
		wait(2);
		click(PagefactorySearchHotel.checkoutdate);
		wait(2);
		
	}
	
}
