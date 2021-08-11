package Util;

import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {

    Properties objProjecties;

    public ConfigReader() throws FileNotFoundException {

        try {
            File src = new File("/Users/satishtamilselvan/Downloads/akaunting/config/UAT.properties");
            FileInputStream fis = new FileInputStream(src);
            objProjecties = new Properties();
            objProjecties.load(fis);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

    public String GetChromeDriverPath() {
        String chromeDriverPath = objProjecties.getProperty("chromeDriver_path");
        return chromeDriverPath;
    }

    public String GetUrl() {
        String url = objProjecties.getProperty("url");
        return url;
    }

    public String GetMailId() {
        return objProjecties.getProperty("mailid");

    }


    public String GetPassword() {
        return objProjecties.getProperty("password");

    }

    public String GetVAlidationMessageForName() {
        return objProjecties.getProperty("validationMesssge_name_field");
    }

    public String GetName_numeric() {
        return objProjecties.getProperty("name_field_numeric");
    }

    public String GetEmailvalid() {
        return objProjecties.getProperty("email_field");
    }

    public String GetPassword_valid() {
        return objProjecties.getProperty("password_field");
    }

    public String Get_validName() {
        return objProjecties.getProperty("nameField_valid");
    }

    public String GetInvalidEmail() {
        return objProjecties.getProperty("invalidEmailField");
    }

    public String GetPassword_lessthanfivecharacters()

    {
        return objProjecties.getProperty("password_lessthanfive_charachters");
    }

    public String GetPassword_onlyNumeric()

    {
        return objProjecties.getProperty("password_onlyNumeric");
    }

    public String GetPassowrd_onlySpecialCharacters(){

        return objProjecties.getProperty("password_onlySpeialcharater");
    }
}
