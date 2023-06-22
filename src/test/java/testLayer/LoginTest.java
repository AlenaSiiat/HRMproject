package testLayer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pompackage.PomLogin;
import testdata.ExcelSheet;
import basePackage.BaseHRMClass;
//make this class as a child of BaseHRMClass
public class LoginTest extends BaseHRMClass { 
//now we can use all methods and variables of parent class
	PomLogin Log;//variable is declared as global	
//1.create a constructor of this class	
	public LoginTest() {
		super (); //we've called parent's class constructor (public BaseHRMClass)with the help of this word
	//it will read the properties of config.properties file
		
	}
@BeforeMethod
public void initsetup() {
	initiation();//it's referring to parent's method and it will act according to the code in parent's method-passing values here from config.properties file(url value, browser's value and so on)
	
	 Log=new PomLogin();//initiating Log variable by creating an object of the class, whose methods we want to use
}
@Test (priority=1)
public void Title () {//checking the title of a webpage
	String actual=Log.verify();//we've called the method with reference variable and stored the result in a variable actual(actual result)
	System.out.println(actual);//to display actual result
	Assert.assertEquals(actual,"OrangeHRM");//we can also write a checkpoint here: actual result is coming from "actual"
}
@DataProvider
public Object[][] Details(){
	Object result[][]=ExcelSheet.readdata("Sheet1");
	return result;
	
}
@Test (priority=2,dataProvider="Details")
public void Login(String name,String password ) throws InterruptedException {
	
//	Log.typeusername(prop.getProperty("username"));//we want to read properties of config.properties file, so we use prop.
	Thread.sleep(1000);

	Log.typeusername(name);
//	Log.typepassword(prop.getProperty("password"));
	Log.typepassword(password);
	screenshots("Login4");
	Thread.sleep(500);
    Log.clickbtn();
}
@AfterMethod
public void close() {//close the browser
	driver.close();
	
}
}
