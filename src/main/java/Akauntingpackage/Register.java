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

public class Register {
     ConfigReader objCofigReader = new ConfigReader();
    WebDriver driver;

    @FindBy(xpath="//input[@id='register-form-first-name']")
    WebElement nameField;

    @FindBy(id="register-form-email")
    WebElement EmailField;

    @FindBy(id="register-form-password")
    WebElement passwordField;

    @FindBy(name="agreement")
    WebElement checkboxforTerms;

    @FindBy(xpath="//div[@class='rc-anchor rc-anchor-normal rc-anchor-light']")
    WebElement captaField;

    @FindBy(id="register-form-submit")
    WebElement RegisterFormButton;

    @FindBy(xpath="//div[@class='alert alert-danger fade in alert-dismissable']")
    WebElement ValidationErrorMessage;



    // constructor
    public Register(WebDriver driver) throws FileNotFoundException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void registerformsubmit()
    {
        RegisterFormButton.click();
    }

    public void verifyUserFormPageOpens(String expectedTitle)
    {
        try{
            Assert.assertEquals(expectedTitle, driver.getTitle());
            System.out.println("page navigated to register form ");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }

    }

    public void IsNameFieldEnabled()
    {
            Assert.assertEquals(true, nameField.isEnabled() && nameField.isDisplayed());
        }

    public void IsEmailFieldEnabled()
    {
        Assert.assertEquals(true, EmailField.isEnabled() && EmailField.isDisplayed());
    }
    public void IsPasswordFieldEnabled()
    {
        Assert.assertEquals(true, passwordField.isEnabled() && passwordField.isDisplayed());
    }

    public void IsTermsFieldEnabled()
    {
        Assert.assertEquals(true, checkboxforTerms.isEnabled() && checkboxforTerms.isDisplayed());
    }
    public void IscaptaFieldEnabled()
    {
        Assert.assertEquals(true, captaField.isEnabled() && captaField.isDisplayed());
    }

    public void EnterNameField(String name)
    {
        nameField.clear();
        nameField.sendKeys(name);
    }
    public void Register_Form(String name, String Email, String password) {
        EnterNameField(name);

        EmailField.clear();
        EmailField.sendKeys(Email);

        passwordField.clear();
        passwordField.sendKeys(password);

        if(!checkboxforTerms.isDisplayed() && checkboxforTerms.isSelected())
        {
            checkboxforTerms.click();
            System.out.println("checkbox of terms ansd condition  has been not selected and clicked");
        }
        else {
            System.out.println("check box is  dispalyed and selected ");
        }

    }

    public void uncheck()
    {

      if(checkboxforTerms.isSelected())
      {
          checkboxforTerms.click();
      }

    }





   /* public void Register_Form(String name, String Email, String password)
    {

        Assert.assertEquals(isNamefieldValid(name),false);
        EnterNameField(name);

        EmailField.clear();
        EmailField.sendKeys(Email);

        //verify the password is matching the requirements
        Assert.assertTrue(!isValid(password,err));
        passwordField.clear();
        passwordField.sendKeys(password);

        if(!checkboxforTerms.isDisplayed() && checkboxforTerms.isSelected())
        {
            checkboxforTerms.click();
            System.out.println("checkbox of terms ansd condition  has been not selected and clicked");
        }
        else {
            System.out.println("check box is  dispalyed and selected ");
        }



    }
*/
    public static boolean isNamefieldValid(String name)
    {
        boolean flag = false;
       if(!name.isBlank() && name.isEmpty() ) {
           flag = true;
       }
       else
       {
           flag= false;
       }
        return flag;
    }

    public static boolean isValid(String passwordhere, List<String> errorList) {

        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        errorList.clear();

        boolean flag=true;


        if (passwordhere.length() < 8) {
            errorList.add("Password lenght must have alleast 8 character !!");
            flag=false;
        }
        if (!specialCharPatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one specail character !!");
            flag=false;
        }
        if (!UpperCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one uppercase character !!");
            flag=false;
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one lowercase character !!");
            flag=false;
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one digit character !!");
            flag=false;
        }

        return flag;

    }

    public String getErrorMessage()
    {
        return  ValidationErrorMessage.getText();
    }

    public void verifyValidationErrorMessage_emptyEmailfield()
    {
    String expectedMessage = "Oh snap! The email field is required.";

    String actualMessage = getErrorMessage();
    String[] parts = actualMessage.split("\n");
    actualMessage = parts[1];
        System.out.println(actualMessage);
        Assert.assertEquals(expectedMessage,actualMessage);
    }

    public void verifyValidationErrorMessage_InvalidEmailfield()
    {
        WebElement email_username = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='register-form-email']")));
       // System.out.println(email_username.getAttribute("validationMessage"));
        String expected = "'.' is used at a wrong position in '.com'.";
        String actual = email_username.getAttribute("validationMessage");

        Assert.assertEquals(actual, expected);
    }

    public void verifyValidationErrorMessage_LessThanFiveCharaters()
    {
        String expected = "Oh snap! The password must be at least 5 characters." ;
        String actualMessage = getErrorMessage();
        String[] parts = actualMessage.split("\n");
        actualMessage = parts[1];
        System.out.println(actualMessage);
        Assert.assertEquals(expected,actualMessage);

    }

    public void verifyValidationErrorMessage_onlyNumeric()
    {
        String expected = "Password should be combination of alphabate , numeric qnd special character." ;
        String actualMessage="";
       try{
           actualMessage = getErrorMessage();
       }
       catch (NoSuchElementException e)
       {
           throw new RuntimeException( "No Validation error messagae");
       }

        String[] parts = actualMessage.split("\n");
        actualMessage = parts[1];
        System.out.println(actualMessage);
        Assert.assertEquals(expected,actualMessage);
    }

   public void verifyValidationErrorMessage_onlySpecilCharacters()
   {
       String expected = "Password should be combination of alphabate , numeric qnd special character." ;
       String actualMessage="";
       try{
           actualMessage = getErrorMessage();
       }
       catch (NoSuchElementException e)
       {
           throw new RuntimeException( "No Validation error messagae");
       }

       String[] parts = actualMessage.split("\n");
       actualMessage = parts[1];
       System.out.println(actualMessage);
       Assert.assertEquals(expected,actualMessage);
   }

   public void verifyValidationErrorMessage_blankTerms()
   {
       String expected = "Oh snap! The agreement field is required." ;
       String actualMessage=null;
       try{
           actualMessage = getErrorMessage();
       }
       catch (NoSuchElementException e)
       {
           throw new RuntimeException( "No Validation error messagae");
       }

       String[] parts = actualMessage.split("\n");
       actualMessage = parts[1];
       System.out.println(actualMessage);
       Assert.assertEquals(expected,actualMessage);
   }



}

