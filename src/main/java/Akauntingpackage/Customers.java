package Akauntingpackage;

import Util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import javax.xml.xpath.XPath;
import java.io.FileNotFoundException;

public class Customers {


    WebDriver driver;

    @FindBy(xpath = "//h2[@class='d-inline-flex mb-0 long-texts']")
    WebElement verfiyCustomerPage;

    @FindBy(xpath = "//h2[contains(text(),'New Customer')]")
    WebElement newCustomerForm;

    @FindBy(linkText ="Add New")
    WebElement addnewcustomer;

    @FindBy(id = "name")
    WebElement namefield;


    // constructor
    public Customers(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyCustomerPageDisplays()
    {
        Assert.assertTrue(verfiyCustomerPage.isDisplayed());
    }

    public void checkCustomerNewFormDisplayed(){
        Assert.assertTrue(newCustomerForm.isDisplayed());
    }

    public void clickAddNewCustomer()
    {
        addnewcustomer.click();
    }

    public void addNewCustomer(String Name)
    {
        namefield.clear();
        namefield.sendKeys(Name);

        driver.findElement(By.xpath("//*[contains(text(),'Save')]")).click();

    }

    public void createAddNewCustomerForm(String Name)
    {
        namefield.clear();
        namefield.sendKeys(Name);

        driver.findElement(By.xpath("//*[contains(text(),'Save')]")).click();

    }

    public void verifycustomervcreated(String newdform2) {
         WebElement isCustomer = driver.findElement(By.xpath("//*[contains(text(),'"+newdform2+"')]"));
        System.out.println(isCustomer.getText());

         Assert.assertTrue(isCustomer.isDisplayed());
    }


}
