package Akauntingpackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import Util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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

    @Test(groups={"regression"},priority=-50)
        public void addEmployee(){
        System.out.println("Employee details are added");
    }

    @Test(dependsOnMethods = "addEmployee", priority=-100,groups={"regression"})
    public void updateEmployee(){
        System.out.println("Emoloyee details are updated");
    }

    @Test(dependsOnGroups = "regression", priority = -200,groups={"smoke"})
    public void removeEmployee(){
        System.out.println("Removed Employee details");
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


        @Test(priority = 13, enabled = false)
        public void verifyprofileIsCreated_success() throws InterruptedException, FileNotFoundException {
            HomePage objHomePge = new HomePage(driver);
            Register objRegister = new Register(driver);

            objHomePge.clickProfileIcon();
            objHomePge.clickRegister();

            objRegister.Register_Form("John Root", "mactest136@gmail.com", "Test@1234");

            Thread.sleep(50000);

            objRegister.registerformsubmit();

        }

        @Test(priority = 14, enabled = false)
        public void verfiyLoginPageOpens() throws FileNotFoundException {
            HomePage objHomePage = new HomePage(driver);
            Register objRegister = new Register(driver);
            objHomePage.clickProfileIcon();
            objHomePage.clickLogin();
            objRegister.verifyUserFormPageOpens("Login - Akaunting");
        }

    @Test(priority = 15, enabled = false)
    public void verfiyLoginPageContents() throws FileNotFoundException {
        HomePage objHomePage = new HomePage(driver);
        Register objRegister = new Register(driver);
        LoginPage objLoginPage= new LoginPage(driver);

        objHomePage.clickProfileIcon();
        objHomePage.clickLogin();
        objRegister.verifyUserFormPageOpens("Login - Akaunting");
        if (objLoginPage.LoginPage_contentsEnabled()){
            System.out.println("method login page contents passwewd");
        }
    }



    @Test(priority = 16, enabled = false)
    public void  Verify_dashaBoardPageOpens() throws FileNotFoundException {
        HomePage objHomePage = new HomePage(driver);
        Register objRegister = new Register(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        Dashboard objDashboard = new Dashboard(driver);

       objHomePage.clickProfileIcon();
        objHomePage.clickLogin();
        objRegister.verifyUserFormPageOpens("Login - Akaunting");
        if (objLoginPage.LoginPage_contentsEnabled()){
            System.out.println("method login page contents passwewd");
        }
        objDashboard.dashaBoardPageOpens("Dashboard - Akaunting");

    }

    @Test(priority = 17, enabled = false)
    public void  Verify_getStartedFormOpen() throws FileNotFoundException, InterruptedException {
        HomePage objHomePage = new HomePage(driver);
        Register objRegister = new Register(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        Dashboard objDashboard = new Dashboard(driver);
        GetStarted objGetStarted = new GetStarted(driver);

        objHomePage.clickProfileIcon();
        objHomePage.clickLogin();
        objRegister.verifyUserFormPageOpens("Login - Akaunting");
        objLoginPage.LoginForm("mactest136@gmail.com", "Test@1234");
        objDashboard.dashaBoardPageOpens("Dashboard - Akaunting");
        objDashboard.clickGetStarted.click();
        objGetStarted.GetStarted_PageOpensForm("Get Started for FREE - Akaunting");

    }


    @Test(priority = 18, enabled = false,groups = {"regression","smoke","unittesting","systemtest"})
    public void  Verify_displayOfGetStartedContains() throws FileNotFoundException, InterruptedException {
        HomePage objHomePage = new HomePage(driver);
        Register objRegister = new Register(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        Dashboard objDashboard = new Dashboard(driver);
        GetStarted objGetStarted = new GetStarted(driver);

        objHomePage.clickProfileIcon();
        objHomePage.clickLogin();
        objRegister.verifyUserFormPageOpens("Login - Akaunting");
        objLoginPage.LoginForm("mactest136@gmail.com", "Test@1234");
        objDashboard.dashaBoardPageOpens("Dashboard - Akaunting");
        objDashboard.clickGetStarted.click();
        objGetStarted.GetStarted_PageOpensForm("Get Started for FREE - Akaunting");
        objGetStarted.verifyGetStarted();
    }


    @Test(description= "Verify the sales menu" , timeOut = 500000, groups = {"regression","smoke","unittesting","systemtest"})
    public void Verify_ProfileDropdownOpen() throws InterruptedException, FileNotFoundException {
        HomePage objHomePage = new HomePage(driver);
        Register objRegister = new Register(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        Dashboard objDashboard = new Dashboard(driver);
        GetStarted objGetStarted = new GetStarted(driver);
        Cloud objCloud = new Cloud(driver);


        objHomePage.clickProfileIcon();
        objHomePage.clickLogin();

        objLoginPage.LoginForm("mactest136@gmail.com", "Test@1234");
        objDashboard.dashaBoardPageOpens("Dashboard - Akaunting");

        objDashboard.clickGetStarted.click();

        //click Startaed
        driver.findElement(By.xpath("//a[@class='button button-rounded button-reveal button-large button-green']")).click();

        //click create company
        objCloud.EnterCompanyName("Max iT 2");

        driver.findElement(By.xpath("//div[@class='input-group input-group-merge has-label']/input[@type='text' and @readonly='readonly']")).click();

       WebElement cal_year =  driver.findElement(By.xpath("//input[@class='numInput cur-year']"));
       cal_year.click();
       cal_year.sendKeys("2021");

        System.out.println("Year is selected");
        WebElement months = driver.findElement(By.xpath("//select[@class='flatpickr-monthDropdown-months']"));

        Select monthDropDown = new Select(months);
        monthDropDown.selectByVisibleText("February");
        System.out.println("Month is selected");
        List<WebElement> allDates = driver.findElements(By.xpath("//div[@class='dayContainer']/span[@class='flatpickr-day '] "));

        for (WebElement date : allDates) {
            String date_expected = date.getText();
            System.out.println("expected Date" + date_expected);
            if (date_expected.equalsIgnoreCase("15")) {
                date.click();
                System.out.println("Date is clicked");
                break;
            }

        }

        //enter teh caleneodr date time from date piker
       // WebElement calendr_date =  driver.findElement(By.xpath("//input[@class='form-control datepicker input']"));
       // calendr_date.click();
       // calendr_date.sendKeys("21 November");

        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();

        driver.findElement(By.xpath("(//*[contains(text(),'Next')])[1]")).click();
        driver.findElement(By.xpath("(//*[contains(text(),'Next')])[1]")).click();

        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();

        objDashboard.Verify_userMenu();
        //click sales menu
        driver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();

        objDashboard.Verify_SalesMenu();

    }
}


