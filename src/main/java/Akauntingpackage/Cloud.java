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

public class Cloud {

    ConfigReader objCofigReader = new ConfigReader();
    WebDriver driver;

    @FindBy(id = "company_name")
    WebElement companyName;

    @FindBy(id = "create_company")
    WebElement createcompany;

    // constructor
    public Cloud(WebDriver driver) throws FileNotFoundException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void EnterCompanyName(String companyNamehere) {
        companyName.clear();
        companyName.sendKeys(companyNamehere);
        createcompany.click();
    }
}
