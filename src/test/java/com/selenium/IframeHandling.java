package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import com.microsoft.playwright.options.*;

import java.util.Arrays;
import java.util.List;

public class IframeHandling {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeClass
    public void start() {
        playwright = Playwright.create();
        browserType = playwright.chromium();

        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized"))); // Maximize window

        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null)); // Use full screen size

        page = browserContext.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    @Test
    public void loginFlow() throws InterruptedException {
        // Open URL
        page.navigate("https://testing-and-learning-hub.vercel.app/index.html");
        Thread.sleep(1000);

        // Enter Email
        ElementHandle elementUname = page.querySelector("#loginEmail");
        elementUname.fill("student@ittrainingbd.com");
        Thread.sleep(1000);

        // Enter Password
        ElementHandle elementPass = page.querySelector("#loginPassword");
        elementPass.fill("ittrainingbd");


         //Click Login Button
//            ElementHandle element = page.querySelector("#signInBtn");
//            element.click();
//            Thread.sleep(2000);


        // Click Login Button

//            page.waitForSelector("#signInBtn", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
//            page.click("#signInBtn");


        page.waitForSelector("#signInBtn", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("#signInBtn", new Page.ClickOptions().setForce(true));
        Thread.sleep(2000); // Wait for the next page to load



    }

    @Test(dependsOnMethods = "loginFlow")
    public void clickGetStarted() throws InterruptedException {
        page.click("//a[@class='btn-get-started']");
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = "clickGetStarted")
    public void clickGetStarted2() throws InterruptedException {
        page.click("//body/main[@id='courses']/div[1]/div[2]/div[1]/div[1]/a[1]");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "clickGetStarted2") //scroll to specific location
    public void scrollToSpecificLocation() throws InterruptedException {
        ElementHandle element = page.querySelector("//a[contains(text(),'Go to IFrame')]");
        element.scrollIntoViewIfNeeded();
        System.out.println(element.textContent());
        Thread.sleep(2000);


    }

    @Test(dependsOnMethods = "scrollToSpecificLocation")
    public void clickiFrame() throws InterruptedException {
        page.click("//a[contains(text(),'Go to IFrame')]");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "clickiFrame") //scroll to specific location
    public void scrollToSpecificLocationiFrame() throws InterruptedException {
        ElementHandle element = page.querySelector("//body/div[@id='example2']/h2[1]");
        element.scrollIntoViewIfNeeded();
        System.out.println(element.textContent());
        Thread.sleep(2000);


    }

    @Test(dependsOnMethods = "scrollToSpecificLocationiFrame") //scroll to specific location
    public void hover() throws InterruptedException {
        ElementHandle element = page.querySelector("//a[@title='Programmable Search Engine by Google']/span");
        element.hover();
        Thread.sleep(4000);


    }

    @Test(dependsOnMethods = "hover")  //iframe size //here iframe is a attribute
    public void iFrameTest() throws InterruptedException {
        List<ElementHandle> iframes = page.querySelectorAll("iframe");
        System.out.println("Iframe: "+ iframes.size());

        Frame iframe = page.frame("googleIframe");
    //        ElementHandle element = iframe.querySelector(".header__logo .text");
        ElementHandle element = iframe.querySelector("//a[@title='Programmable Search Engine by Google']/span");
        System.out.println(element.textContent());

        //question: how to scroll in iframe

    }



    @AfterClass
    public void stop() {
//        page.close();
        browser.close();
        playwright.close();
    }
}
//    @Test (priority = 2)
//   public void clickOnLoginButton() throws InterruptedException {
//       ElementHandle element = page.querySelector("#signInBtn");
//        element.click();
//        Thread.sleep(4000);
//    }




//        @Test(priority = 2)
//        public void clickOnLoginButton() {
//            // Make sure page is initialized properly before this
//            page.click("#signInBtn"); // Clicks the button with id 'signInBtn'
//
//            // Wait for the spinner to appear or the next page load, depending on your app behavior
//            page.waitForSelector(".spinner", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
//        }


    //@Test(dependsOnMethods = "openUrl")  //iframe size //here iframe is a attribute
//    public void iFrameTest() throws InterruptedException {
//        List<ElementHandle> iframes = page.querySelectorAll("iframe");
//        System.out.println("Iframe: "+ iframes.size());
//
//        Frame iframe = page.frame("googleIframe");
////        ElementHandle element = iframe.querySelector(".header__logo .text");
//        ElementHandle element = iframe.querySelector("//a[@title='Programmable Search Engine by Google']/span");
//        System.out.println(element.textContent());
//
//        //question: how to scroll in iframe
//
//    }
