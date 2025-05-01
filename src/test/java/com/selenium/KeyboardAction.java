package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class KeyboardAction {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeClass
    public void start(){
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

    @org.testng.annotations.Test
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



        page.waitForSelector("#signInBtn", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("#signInBtn", new Page.ClickOptions().setForce(true));
        Thread.sleep(2000); // Wait for the next page to load



    }
    @org.testng.annotations.Test(dependsOnMethods = "loginFlow")
    public void clickGetStarted() throws InterruptedException {
        page.click("//a[@class='btn-get-started']");
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = "loginFlow")
    public void clickGetStartedBasic() throws InterruptedException {
        page.click("a[href='/WebAutomation/contents/basic.html']");
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = "clickGetStartedBasic") //scroll to specific location
    public void scrollToSpecificLocationElements() throws InterruptedException {
        ElementHandle element = page.querySelector("//a[normalize-space()='Go to Elements']");
        element.scrollIntoViewIfNeeded();
        Thread.sleep(2000);
        System.out.println(element.textContent());
        Thread.sleep(2000);
        element.click();
        Thread.sleep(2000);


    }
    @Test(dependsOnMethods = "scrollToSpecificLocationElements")
    public void keyboardAction() throws InterruptedException {
        page.click("#first-name");
        page.keyboard().type("Kabbo");
        Thread.sleep(2000);

        //select
        page.keyboard().down("Control");
        page.keyboard().press("KeyA");
        page.keyboard().up("Control");
        Thread.sleep(2000);

        //copy
        page.keyboard().down("Control");
        page.keyboard().press("KeyC");
        page.keyboard().up("Control");
        page.keyboard().press("Tab");

        page.keyboard().down("Control");
        page.keyboard().press("KeyV");
        Thread.sleep(2000);




    }

}
