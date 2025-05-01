package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.util.Arrays;

public class AlertHandle {
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



        page.waitForSelector("#signInBtn", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("#signInBtn", new Page.ClickOptions().setForce(true));
        Thread.sleep(2000); // Wait for the next page to load



    }
    @Test(dependsOnMethods = "loginFlow")
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
    public void scrollToSpecificLocationiFrame() throws InterruptedException {
        ElementHandle element = page.querySelector("//a[normalize-space()='Go to JavaScript Alerts']");
        element.scrollIntoViewIfNeeded();
        Thread.sleep(2000);
        System.out.println(element.textContent());
        Thread.sleep(2000);
        element.click();
        Thread.sleep(2000);


    }
//    @Test(dependsOnMethods = "scrollToSpecificLocationiFrame")
//    public void simpleAlert() throws InterruptedException {
//        page.onceDialog(dialog -> {
//            System.out.println(dialog.message());
//            try {
//                Thread.sleep(3000);
//            } catch (
//                    InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            dialog.accept();
//        } );
//
//        page.click("//button[normalize-space()='Show Alert']");
//        Thread.sleep(3000);
//    }
//    @Test(dependsOnMethods = "simpleAlert")
//    public void confirmAlert() throws InterruptedException {
//        page.onceDialog(dialog -> {
//            System.out.println(dialog.type());
//            System.out.println(dialog.message());
//            try {
//                Thread.sleep(3000);
//            } catch (
//                    InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            dialog.dismiss();
//            try {
//                Thread.sleep(2000);
//            } catch (
//                    InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("Cancel successful");
//
//
//        } );
//
//        page.click("//button[normalize-space()='Show Confirm']");
//        Thread.sleep(3000);
//    }
//
//    @Test(dependsOnMethods = "confirmAlert")
//    public void showPrompt() throws InterruptedException {
//        page.onceDialog(dialog -> {
//            try {
//                System.out.println(dialog.type());
//                System.out.println(dialog.message());
//
//                // Wait 5 seconds before accepting the dialog
//                Thread.sleep(5000);
//
//                dialog.accept("Testing");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//            System.out.println("Dialog accepted successfully");
//        });
//
//        page.click("//button[normalize-space()='Show Prompt']");
//        Thread.sleep(3000);
//    }
//
//    @Test(dependsOnMethods = "showPrompt")
//    public void multipleAllert() throws InterruptedException {
//
//        page.onDialog(dialog -> {
//            try {
//                System.out.println(dialog.type());
//                System.out.println(dialog.message());
//
//                // Wait 2 seconds before accepting each alert (to see them)
//                Thread.sleep(2000);
//
//                dialog.accept();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//            System.out.println("Dialog accepted successfully");
//        });
//
//        page.click("//button[normalize-space()='Show Multiple Alerts']");
//
//        // Wait enough time to handle all alerts
//        Thread.sleep(7000);
//    }

//    @Test(dependsOnMethods = "scrollToSpecificLocationiFrame")
//    public void handleAlerts() throws InterruptedException {
//
//        String alertType = "simple"; // Change this value: simple / confirm / prompt / multiple
//
//        if (alertType.equalsIgnoreCase("simple")) {
//            page.onceDialog(dialog -> {
//                System.out.println("Simple Alert: " + dialog.message());
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                dialog.accept();
//            });
//            page.click("//button[normalize-space()='Show Alert']");
//            Thread.sleep(3000);
//
//        } else if (alertType.equalsIgnoreCase("confirm")) {
//            page.onceDialog(dialog -> {
//                System.out.println("Confirm Alert Type: " + dialog.type());
//                System.out.println("Confirm Alert Message: " + dialog.message());
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                dialog.dismiss();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("Confirm alert dismissed successfully");
//            });
//            page.click("//button[normalize-space()='Show Confirm']");
//            Thread.sleep(3000);
//
//        } else if (alertType.equalsIgnoreCase("prompt")) {
//            page.onceDialog(dialog -> {
//                System.out.println("Prompt Alert Type: " + dialog.type());
//                System.out.println("Prompt Alert Message: " + dialog.message());
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                dialog.accept("Testing");
//                System.out.println("Prompt accepted successfully with input");
//            });
//            page.click("//button[normalize-space()='Show Prompt']");
//            Thread.sleep(3000);
//
//        } else if (alertType.equalsIgnoreCase("multiple")) {
//            page.onDialog(dialog -> {
//                System.out.println("Multiple Alert Type: " + dialog.type());
//                System.out.println("Multiple Alert Message: " + dialog.message());
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                dialog.accept();
//                System.out.println("One alert accepted successfully");
//            });
//            page.click("//button[normalize-space()='Show Multiple Alerts']");
//            Thread.sleep(7000);
//
//        } else {
//            System.out.println("Invalid alert type provided.");
//        }
//    }

    @Test(dependsOnMethods = "scrollToSpecificLocationiFrame")
    public void handleAllAlerts() throws InterruptedException {

        // Handle Simple Alert
        page.onceDialog(dialog -> {
            System.out.println("Simple Alert: " + dialog.message());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dialog.accept();
            System.out.println("Simple alert accepted");
        });
        page.click("//button[normalize-space()='Show Alert']");
        Thread.sleep(3000);

        // Handle Confirm Alert
        page.onceDialog(dialog -> {
            System.out.println("Confirm Alert Type: " + dialog.type());
            System.out.println("Confirm Alert Message: " + dialog.message());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dialog.dismiss(); // Cancel the confirm alert
            System.out.println("Confirm alert dismissed");
        });
        page.click("//button[normalize-space()='Show Confirm']");
        Thread.sleep(3000);

        // Handle Prompt Alert
        page.onceDialog(dialog -> {
            System.out.println("Prompt Alert Type: " + dialog.type());
            System.out.println("Prompt Alert Message: " + dialog.message());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dialog.accept("Testing input"); // Enter text into prompt
            System.out.println("Prompt alert accepted with input");
        });
        page.click("//button[normalize-space()='Show Prompt']");
        Thread.sleep(3000);

        // Handle Multiple Alerts
        page.onDialog(dialog -> {
            System.out.println("Multiple Alert Type: " + dialog.type());
            System.out.println("Multiple Alert Message: " + dialog.message());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dialog.accept(); // Accept each alert
            System.out.println("Multiple alert accepted");
        });
        page.click("//button[normalize-space()='Show Multiple Alerts']");
        Thread.sleep(7000); // Enough time to handle all multiple alerts

        System.out.println("All alert types handled successfully!");
    }

    //common method
//    public void alert(String msg) throws InterruptedException {
//        String value = "accept";
//        page.onceDialog(dialog -> {
//            System.out.println(dialog.type());
//            System.out.println(dialog.message());
//            if(dialog.type().equals("alert")) {
//                dialog.accept();
//                System.out.println("you clicked accept button");
//            }else if(dialog.type().equals("confirm")) {
//                if(value.equals("accept")) {
//                    dialog.accept();
//                }else {
//                    dialog.dismiss();
//                }
//
//            }  else if(dialog.type().equals("prompt")) {
//                if(value.equals("accept")) {
//                    dialog.accept(msg);
//                } else {
//                    dialog.dismiss();
//                }
//            }
//        });
//    }

//@Test(dependsOnMethods = "scrollToSpecificLocationiFrame")
//public void simpleAlert() throws InterruptedException {
//    page.onceDialog(dialog -> {
//        System.out.println("Alert message: " + dialog.message());
//        try {
//            Thread.sleep(2000); // Pause to let the alert be visible
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        dialog.accept();
//    });
//
//    page.click("//button[normalize-space()='Show Alert']");
//    Thread.sleep(3000); // Wait to observe post-alert behavior
//}


    @AfterClass
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
