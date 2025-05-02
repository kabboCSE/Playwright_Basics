// LoginHelper.java
package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import com.microsoft.playwright.*;

public class LoginHelper {
    private final Page page;

    public LoginHelper(Page page) {
        this.page = page;
    }

    public void performLogin() throws InterruptedException {
        // Open URL
        page.navigate("https://testing-and-learning-hub.vercel.app/index.html");
        Thread.sleep(1000);

        // Enter Email
        page.querySelector("#loginEmail").fill("student@ittrainingbd.com");
        Thread.sleep(1000);

        // Enter Password
        page.querySelector("#loginPassword").fill("ittrainingbd");

        // Click Sign In
        page.waitForSelector("#signInBtn", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("#signInBtn", new Page.ClickOptions().setForce(true));
        Thread.sleep(2000); // Wait for the next page to load
    }
}
