package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

//read attribute value, location, page title,

public class GetCommands {
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
        page.navigate("https://testing-and-learning-hub.vercel.app/index.html");
        Thread.sleep(5000);
    }

      //@Test(dependsOnMethods = "openUrl")
    public void fetch() throws InterruptedException {
        System.out.println("Title: " + page.title());
        System.out.println("URL: " + page.url());
        System.out.println("Page Source: "+page.content());
    }

    @Test(dependsOnMethods = "openUrl")
    public void getCSSValue() throws InterruptedException {
        ElementHandle element = page.querySelector("//span[normalize-space()='Intermediate']");
        String color = element.evaluate("element => getComputedStyle(element).backgroundColor").toString();
        System.out.println(color);
    }

    @Test (priority = 4)  //How many input tags exist
    public void locate() throws InterruptedException {
        List<ElementHandle> element = page.querySelectorAll("input"); //For Tag
        System.out.println("Element size : " + element.size());
        Thread.sleep(2000);

        for (ElementHandle handle : element){
            String id = handle.getAttribute("id"); //For Attritbute value only
            System.out.println("ID is: " +id);
        }
    }



    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
