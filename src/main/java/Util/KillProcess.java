package Util;

import java.io.IOException;

public class KillProcess {

    public void killChromeExecProcess() throws IOException {

        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
        System.out.println("Chrome driver exe process is killed");
    }

    public void killEdgeBrowserExecProcess() throws IOException {
        Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
        System.out.println("Edge browser exe process is killed");
    }
}