package Akauntingpackage;

import Util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

public class appLogin {

    WebDriver driver;

    @FindBy(name = "email")
    WebElement emailfield;

    @FindBy(name = "password")
    WebElement passowordField;

    @FindBy(xpath = "//span[contains(text(),'Login')]")
    WebElement clickLogin;

    // constructor
    public appLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void applicationLogin(String username, String password)
    {
        emailfield.clear();
        emailfield.sendKeys(username);

        passowordField.clear();
        passowordField.sendKeys(password);

        clickLogin.click();
    }


}
