package Util;

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
            objProjecties=new Properties();
            objProjecties.load(fis);

        }catch(Exception e) {
             System.out.println(e.getStackTrace());
        }

    }

    public String GetChromeDriverPath(){
       String chromeDriverPath= objProjecties.getProperty("chromeDriver_path");
       return chromeDriverPath;
    }

    public String GetUrl()
    {
       String url= objProjecties.getProperty("url") ;
        return url;
    }

    public String GetMailId()
    {
        return objProjecties.getProperty("mailid");

    }


    public String GetPassword()
    {
        return objProjecties.getProperty("password");

    }

}
