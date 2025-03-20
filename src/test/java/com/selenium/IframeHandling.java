package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class IframeHandling {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
    public void start(){
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(); // new season create
        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    @Test
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/iframe.html");
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "openUrl")  //iframe size //here iframe is a attribute
    public void iFrameTest() throws InterruptedException {
        List<ElementHandle> iframes = page.querySelectorAll("iframe");
        System.out.println("Iframe: "+ iframes.size());

        Frame iframe = page.frame("googleIframe");
//        ElementHandle element = iframe.querySelector(".header__logo .text");
        ElementHandle element = iframe.querySelector("//a[@title='Programmable Search Engine by Google']/span");
        System.out.println(element.textContent());

        //question: how to scroll in iframe

    }


    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
