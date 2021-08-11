package Akauntingpackage;
import Util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class LoginPage
{
    ConfigReader objCofigReader = new ConfigReader();
    WebDriver driver;

    @FindBy(xpath="//*[@id='email']")
    WebElement EnterEmail;


    @FindBy(xpath="//*[@id='password']")
    WebElement EnterPassword;

    @FindBy(xpath="//*[@id='login-form-submit']")
    WebElement clickLogin;

    @FindBy(name ="remember_me")
    WebElement ChkBoxField;

    @FindBy(xpath ="//div[@id='rc-anchor-container']")
    WebElement captaField;


    // constructor
    public LoginPage(WebDriver driver) throws FileNotFoundException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void LoginForm(String email, String password) throws InterruptedException {
        EnterEmail.clear();
        EnterEmail.sendKeys(email);

        EnterPassword.clear();
        EnterPassword.sendKeys(password);

        Thread.sleep(50000);
        clickLogin.click();
    }

    public boolean LoginPage_contentsEnabled()
    {
        Boolean flag = false;

       // Assert.assertTrue(captaField);

        if( EnterEmail.isDisplayed() && EnterPassword.isDisplayed() &&   ChkBoxField.isDisplayed())
        {
            flag = true;
        }
        return flag;
    }



}
