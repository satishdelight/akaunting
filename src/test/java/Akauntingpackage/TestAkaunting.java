package Akauntingpackage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
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

import static java.lang.Thread.*;

public class TestAkaunting<alloptions> {
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
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    }

    @AfterClass
    public static void tearDown() throws IOException {
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


    @Test(groups = {"regression"}, priority = -50)
    public void addEmployee() {
        System.out.println("Employee details are added");
    }

    @Test(dependsOnMethods = "addEmployee", priority = -100, groups = {"regression"})
    public void updateEmployee() {
        System.out.println("Emoloyee details are updated");
    }

    @Test(dependsOnGroups = "regression", priority = -200, groups = {"smoke"})
    public void removeEmployee() {
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

        sleep(50000);

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

        sleep(50000);

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

        sleep(50000);

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

        sleep(50000);

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

        sleep(50000);

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

        sleep(50000);

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

        sleep(50000);

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

        sleep(50000);

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
        LoginPage objLoginPage = new LoginPage(driver);

        objHomePage.clickProfileIcon();
        objHomePage.clickLogin();
        objRegister.verifyUserFormPageOpens("Login - Akaunting");
        if (objLoginPage.LoginPage_contentsEnabled()) {
            System.out.println("method login page contents passwewd");
        }
    }


    @Test(priority = 16, enabled = false)
    public void Verify_dashaBoardPageOpens() throws FileNotFoundException {
        HomePage objHomePage = new HomePage(driver);
        Register objRegister = new Register(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        Dashboard objDashboard = new Dashboard(driver);

        objHomePage.clickProfileIcon();
        objHomePage.clickLogin();
        objRegister.verifyUserFormPageOpens("Login - Akaunting");
        if (objLoginPage.LoginPage_contentsEnabled()) {
            System.out.println("method login page contents passwewd");
        }
        objDashboard.dashaBoardPageOpens("Dashboard - Akaunting");

    }

    @Test(priority = 17, enabled = false)
    public void Verify_getStartedFormOpen() throws FileNotFoundException, InterruptedException {
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


    @Test(priority = 18, enabled = false, groups = {"regression", "smoke", "unittesting", "systemtest"})
    public void Verify_displayOfGetStartedContains() throws FileNotFoundException, InterruptedException {
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


    @Test(priority = 19, enabled = false, description = "Verify the sales menu", timeOut = 500000, groups = {"regression", "smoke", "unittesting", "systemtest"})
    public void Verify_ProfileDropdownOpen() throws InterruptedException, FileNotFoundException, AWTException {
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

        WebElement cal_year = driver.findElement(By.xpath("//input[@class='numInput cur-year']"));
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

        //upload the image fiel
        // WebElement uploadElement = driver.findElement(By.xpath("//button[@class='dz-button']"));
        //uploadElement.click();

        // uploadElement.sendKeys("/Users/satishtamilselvan/Downloads/Driving Test/RoadSignsZip1/300.gif");

        //upload file via robot class

        //Click on the Import Button

        WebElement uploadElement = driver.findElement(By.xpath("//button[@class='dz-button']"));
        uploadElement.click();
        sleep(2000);

//File Need to be imported

        File file = new File("/Users/satishtamilselvan/Downloads/Driving Test/RoadSignsZip1/300.gif");

        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());

//Copy to clipboard Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        Robot robot = new Robot();

// Cmd + Tab is needed since it launches a Java app and the browser looses focus

        robot.keyPress(KeyEvent.VK_META);

        robot.keyPress(KeyEvent.VK_TAB);

        robot.keyRelease(KeyEvent.VK_META);

        robot.keyRelease(KeyEvent.VK_TAB);

        robot.delay(500);

//Open Goto window

        robot.keyPress(KeyEvent.VK_META);

        robot.keyPress(KeyEvent.VK_SHIFT);

        robot.keyPress(KeyEvent.VK_G);

        robot.keyRelease(KeyEvent.VK_META);

        robot.keyRelease(KeyEvent.VK_SHIFT);

        robot.keyRelease(KeyEvent.VK_G);

//Paste the clipboard value

        robot.keyPress(KeyEvent.VK_META);

        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_META);

        robot.keyRelease(KeyEvent.VK_V);

//Press Enter key to close the Goto window and Upload window

        robot.keyPress(KeyEvent.VK_ENTER);

        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(500);


        robot.keyPress(KeyEvent.VK_ENTER);

        robot.keyRelease(KeyEvent.VK_ENTER);


        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();

        driver.findElement(By.xpath("(//*[contains(text(),'Next')])[1]")).click();
        driver.findElement(By.xpath("//button[contains(text(), 'Next')]")).click();

        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();

        objDashboard.Verify_userMenu();

        //click sales menu
        driver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();

        objDashboard.Verify_SalesMenu();
    }


    @Test(priority = 20, enabled = false, description = "verify the customer lust page opens", groups = {"regression"})
    public void ChkTheCustomerPageOpens() {


        driver.navigate().to("https://app.akaunting.com/auth/login");

        appLogin objapplogin = new appLogin(driver);
        Customers objCustomers = new Customers(driver);

        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click sales
        driver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();

        //click customer link
        driver.findElement(By.xpath("//span[contains(text(),'Customers')]")).click();

        objCustomers.verifyCustomerPageDisplays();

    }

    @Test(priority = 21, enabled = false, description = "verify the customer form is displayed")
    public void customerformdisplayed() throws InterruptedException {

        driver.navigate().to("https://app.akaunting.com/auth/login");

        appLogin objapplogin = new appLogin(driver);
        Customers objCustomers = new Customers(driver);

        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click sales
        driver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();

        //click customer link
        sleep(5000);
        driver.findElement(By.xpath("//span[contains(text(),'Customers')]")).click();

        //click add new customer link

        objCustomers.clickAddNewCustomer();
        objCustomers.checkCustomerNewFormDisplayed();

    }
/*
    @Test(description = "app login ")
    public void LoginInApp()
    {
        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");
    }*/

    @Test(priority = 22, enabled = false, groups = {"regression"})
    public void createNewCustomer() throws InterruptedException {

        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");


        Customers objCustomers = new Customers(driver);

        //click sales
        driver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();

        //c lick customer link
        sleep(5000);
        driver.findElement(By.xpath("//span[contains(text(),'Customers')]")).click();

        //click add new customer link
        objCustomers.clickAddNewCustomer();

        objCustomers.checkCustomerNewFormDisplayed();
        //enter name field to craeta customer
        objCustomers.createAddNewCustomerForm("newdform2");

        objCustomers.verifycustomervcreated("newdform2");
    }

    @Test(priority = 23, enabled = false, description = "profile dropdown should be open")
    public void profile_dropdown() {
        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click profile
        driver.findElement(By.xpath("//div[@class=\"media align-items-center\"]")).click();

        boolean flag = driver.findElement(By.xpath("//div[@class=\"dropdown-menu dropdown-menu-right show\"]")).isDisplayed();

        Assert.assertTrue(flag);

    }


    @Test(priority = 24, enabled = false, description = "verify roles ")
    public void verify_roles() {
        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click profile
        driver.findElement(By.xpath("//div[@class=\"media align-items-center\"]")).click();

        //click roles
        driver.findElement(By.xpath("//span[text() = 'Roles']")).click();

        List<WebElement> actualRoles = driver.findElements(By.xpath("//table[@class='table table-flush table-hover']/tbody/tr/td[2]/a"));

        List<String> expectedRoles = new ArrayList<String>();
        expectedRoles.add("Customer");
        expectedRoles.add("Manager");
        expectedRoles.add("Client");

        List<String> actual = new ArrayList<String>();

        for (WebElement role : actualRoles) {
            actual.add(role.getText());
            System.out.println(role.getText());
        }

        Assert.assertEquals(expectedRoles, actual);
    }

    /*

    1. Open login URL: https://app.akaunting.com/auth/login
2. Enter valid email and password.(mactest136@gmail.com & Test@1234).
3. Click on Login button.
4. Click on Profile icon.
5. Click on Roles link.
6. Click on Customer link.
7. Click on Create button.

     */
    @Test(enabled = false, description = "Verify when user clicks on Read button under Permissions section at Edit Role page. ")
    public void verify_check() {

        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click profile
        driver.findElement(By.xpath("//div[@class=\"media align-items-center\"]")).click();

        //click roles
        driver.findElement(By.xpath("//span[text() = 'Roles']")).click();

        //click customer
        driver.findElement(By.xpath("//a[contains(text(),'Customer')]")).click();

        //click create button
        driver.findElement(By.xpath("//a[contains(text(),'Read')]")).click();

        //list of optiosn
        List<WebElement> allOptions = driver.findElements(By.xpath("//div[@id='tab-read']/div/div/div/div/label"));

        for (WebElement option : allOptions) {

            if (option.isSelected() == false) {
                if (option.isDisplayed() & option.isEnabled()) {
                    option.click();
                }
            }
        }

    }

    /*\
    Test case Nos: ID_074
   1. Open login URL: https://app.akaunting.com/auth/login
2. Enter valid email and password.(mactest136@gmail.com & Test@1234).
3. Click on Login button.
4. Click on Profile icon.
5. Click on Roles link.
6. Click on Customer link.
7. Click on Create button.
    expected Result:
     */


    @Test(enabled = false, priority = 74, description = "Verify when user clicks on Read button under Permissions section at Edit Role page. ")
    public void verify_create() {

        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click profile
        driver.findElement(By.xpath("//div[@class=\"media align-items-center\"]")).click();

        //click roles
        driver.findElement(By.xpath("//span[text() = 'Roles']")).click();

        //click customer
        driver.findElement(By.xpath("//a[contains(text(),'Customer')]")).click();

        //click create button
        driver.findElement(By.xpath("//a[contains(text(),'Create')]")).click();

        //list of optiosn
        List<WebElement> allOptions = driver.findElements(By.xpath("//div[@id='tab-create']/div/div/div/div/label"));

        for (WebElement option : allOptions) {

            if (option.isSelected() == false) {
                if (option.isDisplayed() & option.isEnabled()) {
                    option.click();
                }
            }
        }

    }


    /*
    Test case Nos: ID_074
    1.
    2.
    3.
    4.
    expected Result:
     */
    @Test(enabled = true, priority = 75, description = "Verify when user clicks on Read button under Permissions section at Edit Role page. ")
    public void verify_update() {

        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click profile
        driver.findElement(By.xpath("//div[@class=\"media align-items-center\"]")).click();

        //click roles
        driver.findElement(By.xpath("//span[text() = 'Roles']")).click();

        //click customer
        driver.findElement(By.xpath("//a[contains(text(),'Customer')]")).click();

        //click create button
        driver.findElement(By.xpath("//a[contains(text(),'Update')]")).click();

        //list of optiosn
        List<WebElement> allOptions = driver.findElements(By.xpath("//div[@id='tab-update']/div/div/div/div/label"));

        for (WebElement option : allOptions) {

            if (!option.isSelected()) {
                if (option.isDisplayed() & option.isEnabled()) {
                    option.click();
                }
            }
        }

    }

/*
    1. Open login URL: https://app.akaunting.com/auth/login
            2. Enter valid email and password.(mactest136@gmail.com & Test@1234).
            3. Click on Login button.
            4. Click on Profile icon.
            5. Click on Roles link.
            6. Click on Manager link.
            7. Click on any of Read/Create/Update/Delete button.
            8. Click on Select All button.
    */


    @Test(description = "test case title", enabled = false, priority = 74, groups = {"regression"})
    public void verify_selectAll() {
        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click profile
        driver.findElement(By.xpath("//div[@class=\"media align-items-center\"]")).click();

        //click roles
        driver.findElement(By.xpath("//span[text() = 'Roles']")).click();

        //click manager
        driver.findElement(By.xpath("//a[contains(text(),'Manager')]")).click();

        //CLICK SELECTALL UNDER CREATE BUTTON
        driver.findElement(By.xpath("//a[contains(text(),'Create')]")).click();

        //lick select alll
        driver.findElement(By.xpath("//div[@id='role-permissions']/span[contains(text(),'Select All')]")).click();

        List<WebElement> alloptions = driver.findElements(By.xpath("//div[@id='tab-create']/div/div/div/div/input"));
        boolean flag = true;
        for (WebElement option : alloptions) {
            if (!option.isSelected()) {
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

/*
    1. Open login URL: https://app.akaunting.com/auth/login
            2. Enter valid email and password.(mactest136@gmail.com & Test@1234).
            3. Click on Login button.
            4. Click on Profile icon.
            5. Click on Roles link.
            6. Click on Manager link.
            7. Click on any of Read/Create/Update/Delete button.
            8. Click on Unselect All button.*/


    @Test(priority = 89, enabled = false, description = "Verify when user clicks on Unselect All button.")
    public void verify_deselct() {
        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("mactest136@gmail.com", "Test@1234");

        //click profile
        driver.findElement(By.xpath("//div[@class=\"media align-items-center\"]")).click();

        //click roles
        driver.findElement(By.xpath("//span[text() = 'Roles']")).click();

        //click manager
        driver.findElement(By.xpath("//a[contains(text(),'Manager')]")).click();

        //CLICK SELECTALL UNDER CREATE BUTTON
        driver.findElement(By.xpath("//a[contains(text(),'Create')]")).click();

        //lick select alll
        driver.findElement(By.xpath("//div[@id='role-permissions']/span[contains(text(),'Unselect All')]")).click();

        List<WebElement> alloptions = driver.findElements(By.xpath("//div[@id='tab-create']/div/div/div/div/input"));
        boolean flag = true;
        for (WebElement option : alloptions) {
            if (option.isSelected()) {
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);

    }

   /* 1. Open login URL: https://app.akaunting.com/auth/login
            2. Enter valid email and password.(employe987@gmail.com & Test@1234).
            3. Click on Login button.
    */


    @Test(priority = 91, enabled = false, description = "Verify when Manager login with valid login credentials.")
    public void verfiy_managerlogin() throws InterruptedException {
        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("employe987@gmail.com", "Test@1234");
        Thread.sleep(40000);
    }


   /*   1. Open login URL: https://app.akaunting.com/auth/login
        2. Enter valid email and password.(employe987@gmail.com & Test@1234).
        3. Click on Login button.

        Manager dashboard (left side menu) should be contain following-
        1. Items
        2. Sales
        3. Purchase
        4. Banking
        5. Reports
        6. Setting
*/


    @Test(priority = 96, enabled = false, description = "Verify when Manager login with valid login credentials.")
    public void verfiy_managerdashbord() throws InterruptedException {
        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("employe987@gmail.com", "Test@1234");

        List<String> expectedMenu = new ArrayList<String>();
        expectedMenu.add("Items");
        expectedMenu.add("Sales");
        expectedMenu.add("Purchases");
        expectedMenu.add("Banking");
        expectedMenu.add("Reports");
        expectedMenu.add("Settings");

        List<String> ActualMenu = new ArrayList<String>();

        List<WebElement> menus = driver.findElements(By.xpath("//div[@id='sidenav-collapse-main']/ul/li/a/span"));

        for (WebElement menu : menus) {
            if (menu.getText().equalsIgnoreCase("Dashboard") || menu.getText().equalsIgnoreCase("Apps") || menu.getText().equalsIgnoreCase("New Apps")) {
                continue;
            }

            System.out.println(menu.getText());

            ActualMenu.add(menu.getText());
        }

        Assert.assertEquals(expectedMenu, ActualMenu);
    }

/*
            1. Open login URL: https://app.akaunting.com/auth/login
            2. Enter valid email and password.(employe987@gmail.com & Test@1234).
            3. Click on Login button.
            4. Click on items link.
            5. Click on Add new button.
            6. Fill all the required details.
            7. Click on save button.*/

    @Test(priority = 96, enabled = false, description = "Verify the manager should have permission to create items.")
    public void verfiy_managerCanCreateItem() throws InterruptedException {
        driver.navigate().to("https://app.akaunting.com/auth/login");
        appLogin objapplogin = new appLogin(driver);
        objapplogin.applicationLogin("employe987@gmail.com", "Test@1234");

        //click on items link
        driver.findElement(By.xpath("//span[contains(text(),'Items')]")).click();

        //click on Add new button
        driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();

        //click th edropdown Tax
        driver.findElement(By.xpath("//input[@class='el-select__input']")).click();

        //take drpdown in list
        List<WebElement> actaulItems = driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper is-multiple']/div/div/ul/li/span"));

        boolean flag = false;
        for (WebElement actual : actaulItems) {
            System.out.println(actual.getText());
            actual.getText().equalsIgnoreCase("Test (7%)");
            actual.click();
            flag = true;
            break;

        }

        //select the dropdown category
            driver.findElement(By.xpath("//div[@id='form-select-category_id']/div/div/div/input")).click();

        //get the items and loop
        List<WebElement> actualItems = driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper']/div/div/ul/li/span"));

        boolean flag_here = false;
        for (WebElement actual : actaulItems) {
            System.out.println(actual.getText());
            actual.getText().equalsIgnoreCase("General");
            actual.click();
            flag_here = true;
            break;

        }

        WebElement enterSalePrice = driver.findElement(By.id("sale_price"));
        enterSalePrice.clear();
        enterSalePrice.sendKeys("60");

        WebElement enterPurchasePrice = driver.findElement(By.id("purchase_price"));
        enterPurchasePrice.clear();
        enterPurchasePrice.sendKeys("40");


        driver.findElement(By.xpath("//button[@type='submit']")).click();



    }


/*
    1. Open login URL: https://app.akaunting.com/auth/login
            2. Enter valid email and password.(employe987@gmail.com & Test@1234).
            3. Click on Login button.
            4. Click on sales dropdown.
            5. Click on invoices link.
            6. Click on Add new button.
            7. Fill all the required details.
            8. click on save button.

*/

        @Test(priority = 97, enabled = true, description = "Verify the manager should have permission to create items.")
        public void verfiy_managerCanCreateInvoice() throws InterruptedException {

            driver.navigate().to("https://app.akaunting.com/auth/login");
            appLogin objapplogin = new appLogin(driver);
            objapplogin.applicationLogin("employe987@gmail.com", "Test@1234");

            //cl;ick salessn    //*[text() = 'Sales']
            driver.findElement(By.xpath("//*[text() = 'Sales']")).click();

            //click invoices

            driver.findElement(By.xpath("//a[@href='https://app.akaunting.com/138388/sales/invoices']/span")).click();
//add new shoulf be clicked
            driver.findElement(By.xpath("//a[@class='btn btn-success btn-sm']")).click();


            //clicking ont he edit business link
            driver.findElement(By.xpath("//button[text() = 'Edit your business address']")).click();

            int windowsSize=driver.getWindowHandles().size();

            System.out.println("Windows size" + windowsSize);
            List<String> windowHandles = (List<String>) driver.getWindowHandles();


            for (String windowHandle : windowHandles ) {
                driver.switchTo().window(windowHandle);
                System.out.println("swiched to window");
                if(driver.findElement(By.xpath("//h4[contains(text(),' Edit your business address ')])")).getText().contains("Edit your business address ")) {
                    System.out.println("focus is inside the window of Edit business address");
                    break;
                }
            }

    }
}

