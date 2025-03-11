package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class Locators {
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

    @Test (priority = 0)
    public void openUrl() throws InterruptedException {
            Thread.sleep(2000);
            page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/registration_form.html");
        Thread.sleep(5000);
    }
    @Test (priority = 1)
    public void locateByID() throws InterruptedException {
        ElementHandle element = page.querySelector("#first-name"); // By ID
        element.fill("Shahriar");
        Thread.sleep(2000);
    }

    @Test (priority = 2)
    public void locateByName() throws InterruptedException {
        ElementHandle element = page.querySelector("[name='last-name']");
        element.fill("Kabbo");
        Thread.sleep(2000);
    }

//    @Test (priority = 3)
    public void locateByLinkText() throws InterruptedException {
        ElementHandle element = page.querySelector("a:has-text(\"Back to Home\")");
        element.click();
        Thread.sleep(2000);
    }

    @Test (priority = 4)  //How many input tags exist
    public void locate() throws InterruptedException {
        List<ElementHandle> element = page.querySelectorAll("input");
        System.out.println("Element size : " + element.size());
        Thread.sleep(2000);

        for (ElementHandle handle : element){
            String id = handle.getAttribute("id");
            System.out.println("ID is: " +id);
        }
    }

//        @Test (priority = 4)  //How many input tags exist
    public void locatenew() throws InterruptedException {



        List<ElementHandle> element = page.querySelectorAll("input[placeholder]");
        System.out.println("Element size : " + element.size());
        Thread.sleep(2000);

        for (ElementHandle handle : element){
            String id = handle.getAttribute("placeholder");
//            System.out.println("ID size : " + element.size());
            System.out.println("ID is: " +id);
        }
    }


    @Test (priority = 5)
    public void locateByXPath() throws InterruptedException {
        ElementHandle element = page.querySelector("//input[@id='qualification']");
        element.fill("SQA");
        Thread.sleep(2000);
    }





    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }

}
