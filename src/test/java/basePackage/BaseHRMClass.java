package basePackage;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

import utility.TimeUtils;

public class BaseHRMClass {
public static Properties prop=new Properties();//created an object of properties class to be able to access "config.properties"
public static WebDriver driver;//reference variable for WebDriver so we can use his methods

//Step 1:create a constructor of this class

public BaseHRMClass() {
//we'll write the code to read properties of config.properties file here	
try {
	FileInputStream	file=new FileInputStream("C:\\Users\\asiia\\eclipse-workspace\\HRmanagement\\src\\test\\java\\environmentvariables\\Config.properties");
	prop.load(file);//to read properties of file we use this "load" method and in () "file" automatically will come
//file is a reference variable of the class "FileInputStream", which has methods to read data from any file
//in () we specify the path of the file, which properties we want to read
//the location of Config.properties can be found in properties of this file(right click>properties)
//to handle an exception in case the file is not found, we use "try/catch" block instead of "throws" declaration
}
catch (FileNotFoundException e) { //file "config.properties" is not found exception
	e.printStackTrace();
}
catch (IOException a) {
	a.printStackTrace(); //because we use "prop.load(file)" to read the properties, if I'm not able to read data or make some input into this file, we use this exception
	}}
//Step 2: create a method where we will keep all common commands, which we'll be using for child classes
public static void initiation() //static because we want to use it in other classes also
{ //driver's path webdriver.chrome or gecko (firefox)
  //maximize pageload,implicit,getting url
 
  //else if (browser=firefox) then webdriver.gecko.driver
String browsername=prop.getProperty("browser"); //get property of browser from config.properties and store it in a variable "browsername"; ""browser" in same case as it is written in Config.properties
if (browsername.equals("Chrome")) { //if browser=chrome then webdriver.chrome.driver
	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	driver=new ChromeDriver();}
	else if (browsername.equals("Firefox")) {
	System.setProperty("webdriver.gecko.driver","geckodriver.exe");	
	driver=new FirefoxDriver();
	}
driver.manage().window().maximize();
//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeUtils.timepage));
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeUtils.timepage));//the value is specified in TimeUtils class. so we can change time here
driver.get(prop.getProperty("url"));//we want to read properties of config.properties file, so we use prop.
}

public static void screenshots(String Filename)  {
	
	File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
	FileUtils.copyFile(file,new File("C:\\Users\\asiia\\eclipse-workspace\\HRmanagement\\src\\test\\java\\screenshots\\Screenshots"+Filename+".jpg"));
	}
	catch(IOException e) {e.printStackTrace();}
}

}
	

