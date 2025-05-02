package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class CrossBrowser {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;


    public void start(String browserName, String headless) {
        playwright = Playwright.create();

        if(browserName.equalsIgnoreCase("chrome")){
            browserType = playwright.chromium();
        }else if(browserName.equalsIgnoreCase("firefox")){
            browserType = playwright.firefox();
        }else if(browserName.equalsIgnoreCase("ie")){
                browserType = playwright.webkit();
        }else if(browserName.equalsIgnoreCase("opera")){
            browserType = playwright.webkit();
        }else{
            System.out.println("Invalid browser name");
        }

        if(headless.equalsIgnoreCase("true")){
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));
        }else{
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        }





        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(); // new season create
        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }


    public void launchApplication(String url) throws InterruptedException {
        page.navigate(url);
    }
    @Test
    public void home(@Optional("https://testing-and-learning-hub.vercel.app/index.html") String url, @Optional("chrome") String browserName, @Optional("false") String headless) throws InterruptedException {
        start(browserName, headless);
        launchApplication(url);
        Thread.sleep(2000);
        }




    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
