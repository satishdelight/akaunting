package Akauntingpackage;

import Util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.ls.LSOutput;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Dashboard {

    ConfigReader objCofigReader = new ConfigReader();
    WebDriver driver;

    @FindBy(xpath = "//*[@id='top-download']")
    WebElement clickGetStarted;

    @FindBy(xpath = "//div[@class='fbox-icon']")
    WebElement verifyCloudAndDownload;

    @FindBy(xpath = "//div[@class='media align-items-center']")
    WebElement click_user;

    @FindBy(xpath = "//h2[text() = 'Dashboard']")
    WebElement dashboard_page;


    // constructor
    public Dashboard(WebDriver driver) throws FileNotFoundException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void GetStarted() {
        clickGetStarted.click();
        List<WebElement> lst = driver.findElements(By.xpath("//div[@class='feature-box fbox-center fbox-bg fbox-light fbox-effect']/h3"));
        boolean flag = false;


        for (WebElement flst : lst) {
            flst.isDisplayed();
            if (flst.getText().contains("CLOUD") && flst.getText().contains("START")) {
                flag = true;
            }
            if (flst.getText().contains("DOWNLOAD") && flst.getText().contains("GET")) {
                flag = true;
            }
        }
    }

    public void dashaBoardPageOpens(String expectedTitle)
    {
        try{
            Assert.assertEquals(expectedTitle, driver.getTitle());
            System.out.println("page navigated to register form ");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }

    }

    public void Verify_userMenu() {
        click_user.click();
        List<String> expectedMenu = new ArrayList<String>();
        expectedMenu.add("Profile");
        expectedMenu.add("Users");
        expectedMenu.add("Roles");
        expectedMenu.add("Logout");

        List<String> actualMenu = new ArrayList<String>();
        List<WebElement> sub_menu = driver.findElements(By.xpath("//div[@class='dropdown-menu dropdown-menu-right show']/a"));

        for (WebElement we : sub_menu) {
            actualMenu.add(we.getText());
        }
        Assert.assertEquals(expectedMenu, actualMenu);

    }

    public void Verify_SalesMenu(){
        List<String> expectedMenu = new ArrayList<String>();
        expectedMenu.add("Invoices");
        expectedMenu.add("Revenues");
        expectedMenu.add("Customers");

        List<String> actualMenu = new ArrayList<String>();
        List<WebElement> sub_menu = driver.findElements(By.xpath("//div[@id='navbar-sales']/ul/li"));

        for (WebElement we : sub_menu) {
            actualMenu.add(we.getText());
        }
        Assert.assertEquals(expectedMenu, actualMenu);
    }



    public void IsDashboardPage()
    {

        dashboard_page.isDisplayed();
    }

    }


