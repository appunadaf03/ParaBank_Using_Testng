package parabank;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class parabank {
	
	
static	WebDriver driver;

@BeforeTest
public void System_Setup(){
	
	driver= new ChromeDriver();
	driver.get("https://parabank.parasoft.com/");
	driver.manage().window().maximize();
	//assertEquals(driver.getCurrentUrl(),"https://parabank.parasoft.com/parabank/index.htm;jsessionid=E5A7CF4AE09FA7B6AEED9666438F9A0D");
	screenshot("MainPage");
}


@Test(priority=0)
public void register() {
	driver.findElement(By.xpath("//a[text()='Register']")).click();
	
	
}

                     //Registering As New Account
@Test(priority=1)
public static void sign_up_form() throws InterruptedException {
	driver.findElement(By.id(sign_up("FirstName"))).sendKeys(sign_up_data("FirstName"));
	screenshot("FirstName");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("LastName"))).sendKeys(sign_up_data("LastName"));
	screenshot("LastName");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("Address"))).sendKeys(sign_up_data("Address"));
	screenshot("Address");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("City"))).sendKeys(sign_up_data("City"));
	screenshot("City");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("State"))).sendKeys(sign_up_data("State"));
	screenshot("State");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("Zip code"))).sendKeys(sign_up_data("Zip code"));
	screenshot("ZipCode");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("Phone"))).sendKeys(sign_up_data("Phone"));
	screenshot("Phone");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("SSN"))).sendKeys(sign_up_data("SSN"));
	screenshot("SSN");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("Username"))).sendKeys(sign_up_data("Username"));
	screenshot("Username");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("Password"))).sendKeys(sign_up_data("Password"));
	screenshot("Password");
	Thread.sleep(1000);
	driver.findElement(By.id(sign_up("Confirm pwd"))).sendKeys(sign_up_data("Confirm pwd"));
	screenshot("ConfirmPwd");
	Thread.sleep(1000);
	driver.findElement(By.xpath(sign_up("RegisterClick"))).click();
	Thread.sleep(1000);
	//driver.switchTo().window("https://parabank.parasoft.com/");
}

@Test(priority=2)
public void account() {
	driver.findElement(By.xpath("//a[@href='openaccount.htm']")).click();
	screenshot("accountOpen");
	Select svg = new Select(driver.findElement(By.id("type")));
	svg.selectByIndex(1);
	screenshot("accountType");
	Select amt = new Select(driver.findElement(By.id("fromAccountId")));
	amt.selectByIndex(0);
	screenshot("accountNo");
	driver.findElement(By.xpath("//input[@class='button']")).click();
}




@Test
public static String sign_up(String fieldname) {
	
	try {
		
		File src = new File("Para_Bank.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb =  new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("pararegister");
		DataFormatter format = new DataFormatter();
		
		for(int row=1; row<=sh.getLastRowNum(); row++) {
			if(format.formatCellValue(sh.getRow(row).getCell(0)).equals(fieldname)) {
				return format.formatCellValue(sh.getRow(row).getCell(1));
			}
		}
		
		
	}catch(Exception e) {
		e.getStackTrace();
	}
	return null;
	
}

@Test
public static String sign_up_data(String fieldname) {
	try {
	File src = new File("./Para_Bank.xlsx/");
	FileInputStream fis = new FileInputStream(src);
	XSSFWorkbook wb= new XSSFWorkbook(fis);
	XSSFSheet sh = wb.getSheet("pararegister");
	DataFormatter format = new DataFormatter();
	
	for(int row=1; row<=sh.getLastRowNum(); row++) {
		if(format.formatCellValue(sh.getRow(row).getCell(0)).equals(fieldname)) {
			return format.formatCellValue(sh.getRow(row).getCell(2));
		}
	}
	
	
	
	}
	catch(Exception e) {
		e.getStackTrace();
	}
	return null;
}

@Test(priority=4)
public static void log_in_form() throws Exception {

	driver.findElement(By.xpath(log_in("Username"))).sendKeys(sign_up_data("Username"));
	screenshot("loginUserId");
	Thread.sleep(2000);
	driver.findElement(By.xpath(log_in("Password"))).sendKeys(sign_up("Password"));
	screenshot("loginUserPWD");
	Thread.sleep(2000);
	driver.findElement(By.xpath(log_in("button"))).click();
}



@Test 

public static String log_in(String fieldname) {
	try {
	File src = new File("./Para_Bank.xlsx/");
	FileInputStream fis = new FileInputStream(src);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sh =wb.getSheet("loginregister");
	DataFormatter format = new DataFormatter();
	
	for(int row=0; row<=sh.getLastRowNum(); row++) {
		if(format.formatCellValue(sh.getRow(row).getCell(0)).equals(fieldname));
	return format.formatCellValue(sh.getRow(row).getCell(1));
	}
}
	catch(Exception e) {
	e.getStackTrace();
}
return null;	
}


@Test
public static String log_in_data(String fieldname) {
	try
	{
	File src = new File("./Para_Bank.xlsx/");
	FileInputStream fis = new FileInputStream(src);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sh = wb.getSheet("loginregister");
	DataFormatter format = new DataFormatter();
	
	for(int row=1; row<=sh.getLastRowNum(); row++) {
		if(format.formatCellValue(sh.getRow(row).getCell(0)).equals(fieldname))
		return format.formatCellValue(sh.getRow(row).getCell(2));
	}
}
	catch(Exception e) {
		e.getStackTrace();
	}
	return null;
}





public static void screenshot(String filename) {
try {
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File("./Snaps/"+filename+".png"));
	
}catch(Exception e) {
	e.getStackTrace();
}
}

@Test(priority=3)
public void logout() {
	driver.findElement(By.xpath("//a[text()='Log Out']")).click();
}





@AfterTest
public void log_out() {
	driver.findElement(By.xpath("//a[text()='Log Out']")).click();
}



}
