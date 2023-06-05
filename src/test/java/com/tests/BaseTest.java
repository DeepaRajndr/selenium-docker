package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        // BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname

        String host = "localhost";
        //DesiredCapabilities dc;
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();


        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String testName = ctx.getCurrentXmlTest().getName();

        String completeUrl = "http://" + host + ":4444/wd/hub";

        if(System.getProperty("BROWSER") != null &&
            System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            firefoxOptions.setCapability("name", testName);
            driver = new RemoteWebDriver(new URL(completeUrl),firefoxOptions);

        }else{
            chromeOptions.setCapability("name",testName);
            driver = new RemoteWebDriver(new URL(completeUrl),chromeOptions);
        }
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }



}
