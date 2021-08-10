package Akauntingpackage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import Util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import Util.WebdriverUtil;
import org.testng.annotations.Test;

public class TestAkaunting {
    static WebDriver driver = null;

    //static String baseURL = "https://akaunting.com/";
    static String baseURL;
    static ConfigReader objCofigReader;
    @BeforeMethod
    public static void setup() throws FileNotFoundException {
        objCofigReader = new ConfigReader();
        baseURL=objCofigReader.GetUrl();
        driver = WebdriverUtil.getWebDriver("CHROME");

        // maximizing the browser window
        driver.manage().window().maximize();

        // launching the website
        driver.get(baseURL);

        // implicit wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    @Test( priority = 0, enabled = false)
    public void HomePageShouldOpen () {
        System.out.println("url has opened");

    }
    @Test(priority = 1, enabled = false)
    public void verifyProfileIconOpens()
    {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickProfileIcon();
        objHomePage.verifyProfileDropdownOpens();
    }




    @Test(priority = 2, enabled = false)
    public void profileDropdownCOntainsMenu()
    {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickProfileIcon();
        objHomePage.verifyTheDisplayOfProfileDropdown();
    }


    @Test(priority = 3, enabled = false)
    public void registerFormOpens()
    {
        HomePage objHomePage = new HomePage(driver);
        Register objRegister = new Register(driver);
        objHomePage.clickProfileIcon();
        objHomePage.clickRegister();
        objRegister.verifyUserFormPageOpens("Register - Akaunting");
    }


    @Test(priority = 4, enabled = false)
    public void registerForm_containsField() {
        Register objRegister = new Register(driver);
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickProfileIcon();
        objHomePage.clickRegister();
        objRegister.IsNameFieldEnabled();
        objRegister.IsEmailFieldEnabled();
        objRegister.IsPasswordFieldEnabled();
        objRegister.IsTermsFieldEnabled();
        objRegister.IscaptaFieldEnabled();
    }

    @Test(priority = 5 , enabled = false)
    public void validateErrMessage()
    {

        Register objRegister = new Register(driver);
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickProfileIcon();
        objHomePage.clickRegister();



        WebElement name =  driver.findElement(By.id("register-form-email"));
        name.clear();
        name.sendKeys("qwe@gmail.com");
        //name.sendKeys(objCofigReader.GetPassword());

        driver.findElement(By.id("register-form-submit")).click();

       List<WebElement>  we = driver.findElements(By.xpath("//div[@class='alert alert-danger fade in alert-dismissable']"));

       for(WebElement act : we){
          System.out.println(act.getText());
}

    }

    @Test(priority = 6, enabled = true)
    public void verifyNameField() {

        HomePage objHomePge = new HomePage(driver);
        Register objRegister = new Register(driver);

        objHomePge.clickProfileIcon();
        objHomePge.clickRegister();

        objRegister.Register_Form(" ", "qwed243@gmail.com", "QWE123");

        driver.findElement(By.id("register-form-submit")).click();

        List<String> expectedValidationMessages = new ArrayList<String>();
        List<String> actualValidationMessages = new ArrayList<String>();

        expectedValidationMessages.add("Oh snap! The name field is required.");
        expectedValidationMessages.add("Oh snap! The email field is required.");

        List<WebElement> we = driver.findElements(By.xpath("//div[@class='alert alert-danger fade in alert-dismissable']"));

        for (WebElement act : we){
            actualValidationMessages.add(act.getText());
        }

      if(  expectedValidationMessages.equals(actualValidationMessages))
      {
          System.out.println(expectedValidationMessages.equals(actualValidationMessages));
      }
      else
      {
          System.out.println("does nt contain validation");
      }
    }



    @AfterClass
    public static void tearDown() throws IOException {
        driver.quit();

        /*
         * KillProcess objKillProcess = new KillProcess();
         * objKillProcess.killChromeExecProcess();
         */
    }
}


