package Akauntingpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    WebDriver driver;

    @FindBy(id = "top-account")
    WebElement profileIcon;

    @FindBy(xpath = "//div[@class='dropdown open']")
    WebElement profileOpen;

    @FindBy(xpath = "//*[text() = 'Register']")
    WebElement Register;

    @FindBy(xpath = "//a[contains(text(), 'Login')]")
    WebElement clicklogin;


    // constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProfileIcon() {
        profileIcon.click();
    }

    public void clickRegister() {
        Register.click();
    }

    public void clickLogin() {
        clicklogin.click();
    }

    public void verifyProfileDropdownOpens() {
        boolean flag = false;
        if (profileOpen.getAttribute("class").contains("dropdown open")) {
            flag = true;
        }

        Assert.assertTrue(flag);
    }

    public void verifyTheDisplayOfProfileDropdown() {
        Boolean flag = false;

        List<String> expectedMenuItems = new ArrayList<String>();
        expectedMenuItems.add("Login");
        expectedMenuItems.add("Register");


        List<String> actualMenuItems = new ArrayList<String>();

        // finding main menu

        //finding the list of sub menus of the main menu
        List<WebElement> subMenu = driver.findElements(By.xpath("//div[@id='top-account']/ul[@class='dropdown-menu dropdown-menu-right']/li/a"));
        if (subMenu.size() > 0) {
            for (WebElement submenuitem : subMenu) {
                actualMenuItems.add(submenuitem.getText());
            }


        } else {

            System.out.println("Sub menu is empty");
        }


        flag = actualMenuItems.equals(expectedMenuItems);
        Assert.assertTrue(flag);
    }


}
