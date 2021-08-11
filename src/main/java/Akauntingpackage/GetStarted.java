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




public class GetStarted {

    ConfigReader objCofigReader = new ConfigReader();
    WebDriver driver;

    @FindBy(xpath="//span[text() = 'Start']")
    WebElement start;

    @FindBy(id="company_name")
    WebElement compName;

    @FindBy(xpath = "//span[text() = 'Create Company']")
    WebElement craeteCompany;

    // constructor
    public GetStarted(WebDriver driver) throws FileNotFoundException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void GetStarted_PageOpensForm(String expectedTitle)
    {
        try{
            Assert.assertEquals(expectedTitle, driver.getTitle());
            System.out.println("page navigated to register form ");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }

    }

    public void   verifyGetStarted() {

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

    public void createCompany(String CompanyName)
    {

        start.click();
        compName.clear();
        compName.sendKeys(CompanyName);
        craeteCompany.click();


    }

}
