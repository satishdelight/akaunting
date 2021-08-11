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
        baseURL = objCofigReader.GetUrl();
        driver = WebdriverUtil.getWebDriver("CHROME");

        // maximizing the browser window
        driver.manage().window().maximize();

        // launching the website
        driver.get(baseURL);

        // implicit wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

        @AfterClass
        public static void tearDown () throws IOException {
            driver.quit();

            /*
             * KillProcess objKillProcess = new KillProcess();
             * objKillProcess.killChromeExecProcess();
             */
        }

    @Test(priority = 0, enabled = false)
    public void HomePageShouldOpen() {
        System.out.println("url has opened");

    }

    @Test(priority = 1, enabled = false)
    public void verifyProfileIconOpens() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickProfileIcon();
        objHomePage.verifyProfileDropdownOpens();
    }

    @Test(priority = 2, enabled = false)
    public void profileDropdownCOntainsMenu() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickProfileIcon();
        objHomePage.verifyTheDisplayOfProfileDropdown();
    }

    @Test(priority = 3, enabled = false)
    public void registerFormOpens() throws FileNotFoundException {
        HomePage objHomePage = new HomePage(driver);
        Register objRegister = new Register(driver);
        objHomePage.clickProfileIcon();
        objHomePage.clickRegister();
        objRegister.verifyUserFormPageOpens("Register - Akaunting");
    }

    @Test(priority = 4, enabled = false)
    public void registerForm_containsField() throws FileNotFoundException {
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

    @Test(priority = 5, enabled = false)
    public void validateErrMessage() throws FileNotFoundException {

        Register objRegister = new Register(driver);
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickProfileIcon();
        objHomePage.clickRegister();


        WebElement name = driver.findElement(By.id("register-form-email"));
        name.clear();
        name.sendKeys("qwe@gmail.com");
        //name.sendKeys(objCofigReader.GetPassword());

        driver.findElement(By.id("register-form-submit")).click();

        List<WebElement> we = driver.findElements(By.xpath("//div[@class='alert alert-danger fade in alert-dismissable']"));

        for (WebElement act : we) {
            System.out.println(act.getText());
        }

    }

    @Test(priority = 6, enabled = false)
    public void verifyNameFieldwithBlankSpace() throws FileNotFoundException {

        HomePage objHomePge = new HomePage(driver);
        Register objRegister = new Register(driver);

        objHomePge.clickProfileIcon();
        objHomePge.clickRegister();

        objRegister.Register_Form(" ", "qwed243@gmail.com", "QWE123");

        driver.findElement(By.id("register-form-submit")).click();

        System.out.println(driver.getPageSource().contains("The name field is required."));
        Assert.assertTrue(driver.getPageSource().contains("The name field is required."));


    }

    @Test(priority = 7, enabled = false)
    public void verifyNameFieldwithNumeric() throws FileNotFoundException, InterruptedException {

        objCofigReader = new ConfigReader();

        HomePage objHomePge = new HomePage(driver);
        Register objRegister = new Register(driver);

        objHomePge.clickProfileIcon();
        objHomePge.clickRegister();

        objRegister.Register_Form(objCofigReader.GetName_numeric(), objCofigReader.GetEmailvalid(), objCofigReader.GetPassword_valid());

        Thread.sleep(50000);

        objRegister.registerformsubmit();

        String expectedMessage = "Oh snap! Name must not be numeric.";

        String actualMessage = objRegister.getErrorMessage();
        String[] parts = actualMessage.split("\n");
        actualMessage = parts[1];
        System.out.println(actualMessage);
        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Test(priority = 8, enabled = false)
    public void verifyEmailFieldAsBlank() throws FileNotFoundException, InterruptedException {
        objCofigReader = new ConfigReader();

        HomePage objHomePge = new HomePage(driver);
        Register objRegister = new Register(driver);

        objHomePge.clickProfileIcon();
        objHomePge.clickRegister();

        objRegister.Register_Form(objCofigReader.Get_validName(), "", objCofigReader.GetPassword_valid());

        Thread.sleep(50000);

        objRegister.registerformsubmit();

        objRegister.verifyValidationErrorMessage_emptyEmailfield();

    }

    @Test(priority = 9, enabled = false)
    public void verifyEmailFIeldInvalid() throws FileNotFoundException, InterruptedException {
        objCofigReader = new ConfigReader();

        HomePage objHomePge = new HomePage(driver);
        Register objRegister = new Register(driver);

        objHomePge.clickProfileIcon();
        objHomePge.clickRegister();

        objRegister.Register_Form(objCofigReader.Get_validName(), objCofigReader.GetInvalidEmail(), objCofigReader.GetPassword_valid());

        Thread.sleep(50000);

        objRegister.registerformsubmit();
        objRegister.verifyValidationErrorMessage_InvalidEmailfield();

    }

    @Test(priority = 10, enabled = false)
    public void verifyValidationErrorMessage_passwordLessThanfivecharacters() throws FileNotFoundException, InterruptedException {
        objCofigReader = new ConfigReader();

        HomePage objHomePge = new HomePage(driver);
        Register objRegister = new Register(driver);

        objHomePge.clickProfileIcon();
        objHomePge.clickRegister();

        objRegister.Register_Form(objCofigReader.Get_validName(), objCofigReader.GetEmailvalid(), objCofigReader.GetPassword_lessthanfivecharacters());

        Thread.sleep(50000);

        objRegister.registerformsubmit();

        objRegister.verifyValidationErrorMessage_LessThanFiveCharaters();

    }

    @Test(priority = 11, enabled = false)
    public void verifyValidationErrorMessage_passowrdOnlyNumeric() throws FileNotFoundException, InterruptedException {
        HomePage objHomePge = new HomePage(driver);
        Register objRegister = new Register(driver);

        objHomePge.clickProfileIcon();
        objHomePge.clickRegister();

        objRegister.Register_Form(objCofigReader.Get_validName(), objCofigReader.GetEmailvalid(), objCofigReader.GetPassword_onlyNumeric());

        Thread.sleep(50000);

        objRegister.registerformsubmit();
        objRegister.verifyValidationErrorMessage_onlyNumeric();
    }

    @Test(priority = 12, enabled = false)
    public void verifyValidationErrorMessage_passowrdOnlySpecialCharacters() throws FileNotFoundException, InterruptedException {
        HomePage objHomePge = new HomePage(driver);
        Register objRegister = new Register(driver);

        objHomePge.clickProfileIcon();
        objHomePge.clickRegister();

        objRegister.Register_Form(objCofigReader.Get_validName(), objCofigReader.GetEmailvalid(), objCofigReader.GetPassowrd_onlySpecialCharacters());

        Thread.sleep(50000);

        objRegister.registerformsubmit();
        objRegister.verifyValidationErrorMessage_onlySpecilCharacters();
    }

    @Test(priority = 12, enabled = false)
    public void verifyValidationErrorMessage_TermsBlank() throws FileNotFoundException, InterruptedException {

            HomePage objHomePge = new HomePage(driver);
            Register objRegister = new Register(driver);

            objHomePge.clickProfileIcon();
            objHomePge.clickRegister();

            objRegister.Register_Form(objCofigReader.Get_validName(), objCofigReader.GetEmailvalid(), objCofigReader.GetPassword_valid());
            objRegister.uncheck();

            Thread.sleep(50000);

            objRegister.registerformsubmit();
            objRegister.verifyValidationErrorMessage_blankTerms();
        }


        @Test(priority = 13, enabled = true)
        public void verifyprofileIsCreated_success() throws InterruptedException, FileNotFoundException {
            HomePage objHomePge = new HomePage(driver);
            Register objRegister = new Register(driver);

            objHomePge.clickProfileIcon();
            objHomePge.clickRegister();

            objRegister.Register_Form("John Root", "mactest136@gmail.com", "Test@1234");

            Thread.sleep(50000);

            objRegister.registerformsubmit();

        }
}


