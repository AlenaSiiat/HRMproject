package pompackage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import basePackage.BaseHRMClass;

public class PomLogin extends BaseHRMClass { //created inheritance in order to use "driver" (reference variable of WebDriver)
//1.create object repository for particular page (login in our example)
@FindBy(name="username") WebElement Username; //driver.findElement(By.=@FindBy
@FindBy(name="password") WebElement Password;	
@FindBy(css=".oxd-button oxd-button--medium oxd-button--main orangehrm-login-button") WebElement Loginbtn;	

//2.initiate page elements
//create constructor of PomLogin class
public PomLogin() {
PageFactory.initElements(driver, this);/*init.Elemens-static method which used to initialize all WebElmements
 located at @FindBy, otherwise you can't access them */
//driver is coming from our parent-base class	
//"this" keyword converts local variables to global:values will be passed to the driver and it will interact with WebElements
}
//3.create methods: all actions(methods) which we perform on the element, also created and stored here in POM
public void typeusername(String name) {//String name-parameter of the particular method, which is passed to "Sendkeys()"
	Username.sendKeys(name);//method to type username
}
public void typepassword(String pass) {//method to type password
	Password.sendKeys(pass);
}
public void clickbtn() {//we're not passing any value to this action,because it's click,so no parameter for this
	Loginbtn.click();
	
}
public String verify() {//to verify title of the page
	return driver.getTitle();
	
}
}
