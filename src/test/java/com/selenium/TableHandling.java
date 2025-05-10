package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TableHandling {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
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

    @Test(priority = 1)
    public void loginFlow() throws InterruptedException {
        LoginHelper loginHelper = new LoginHelper(page);
        loginHelper.performLogin();
        Thread.sleep(2000);
    }
    @Test(priority = 2)
    public void clickAutomation() throws InterruptedException {
        page.querySelector("//a[@href='../WebAutomation/selenium.html']").click();
        Thread.sleep(2000);
    }
    @Test(priority = 3)
    public void clickAutomation2() throws InterruptedException {
        page.querySelector("//a[@href='/WebAutomation/contents/intermediate.html']").click();
        Thread.sleep(2000);
    }
    @Test(priority = 4)
    public void scrollToSpecificLocation() throws InterruptedException {
        // Use Locator API instead of ElementHandle (safer and easier)
        Locator element = page.locator("//a[normalize-space()='Go to Table']");

        // Wait until the element is visible
        element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        // Scroll into view
        element.scrollIntoViewIfNeeded();
        Thread.sleep(1500);

        // Click and wait for navigation
        page.waitForNavigation(() -> {
            element.click();
        });

        // Optional: print URL or confirmation
        System.out.println("Clicked 'Go to Table' and navigated to: " + page.url());
        Thread.sleep(3000);
    }

//    public void fetchTableHeading() throws InterruptedException {
//        List<ElementHandle> headers = page.querySelectorAll("//table[@id='table1']//thead//tr//th");
//        System.out.println("Number of headers: " + headers.size());
//
//        for(ElementHandle header : headers){
//            String value = header.textContent();
//            System.out.println("Header value : " + value);
//        }
//    }
    @Test(priority = 5)
    public void fetchSpecificvalue() throws InterruptedException {
        List<ElementHandle> table_rows = page.querySelectorAll("//table[@id='table1']//tbody//tr");

        for(int i = 1; i<=table_rows.size(); i++){
            ElementHandle table_data = page.querySelector("//table[@id='table1']//tr["+i+"]//td[5]");
            String value = table_data.textContent();
            System.out.println("Email : " + value);
        }
    }

    @Test(priority = 5)
    public void fetchColumn() throws InterruptedException {
        List<ElementHandle> table_rows = page.querySelectorAll("//table[@id='table1']//tbody//tr");

        for(int i = 1; i<=table_rows.size(); i++){
            List<ElementHandle> table_col = page.querySelectorAll("//table[@id='table1']//tbody//tr[1]//td");
            for(int j = 1; j<=table_col.size(); j++){
                ElementHandle table_data = page.querySelector("//table[@id='table1']//tr["+i+"]//td["+j+"]");
                String value = table_data.textContent();
                System.out.println(value);
            }

                System.out.println("==============================");

        }
    }


    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
