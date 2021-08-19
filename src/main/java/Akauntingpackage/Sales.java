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


public class Sales {

    ConfigReader objCofigReader = new ConfigReader();
    WebDriver driver;

    @FindBy(xpath = "//span[text() = 'Invoices']")
    WebElement InvoicesLocator;

    @FindBy(xpath = "//span[text() =//a[text() = 'Add New']]")
    WebElement addNewInvoiceLocator;





    // constructor
    public Sales(WebDriver driver) throws FileNotFoundException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}



