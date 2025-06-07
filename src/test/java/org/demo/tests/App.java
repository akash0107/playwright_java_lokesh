package org.demo.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class App {
    @Test
    public void browserLaunch() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            System.out.println(page.title());
        }
    }

    @Test
    public void multipleContext() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://playwright.dev");
            System.out.println(page.title());

            BrowserContext context1 = browser.newContext();
            Page page1 = context1.newPage();
            page1.navigate("https://google.com");
            System.out.println(page1.title());

            page.close();
            page1.close();
        }
    }

    @Test
    public void mouseHover() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://demo.automationtesting.in/Register.html");

        Locator switchTo = page.locator("text=SwitchTo");
        switchTo.hover();

        //Print text contents
        Locator skillOptions = page.locator("#Skills option");
        for (Locator skillOption : skillOptions.all()) {
            String text = skillOption.textContent();
            if (text.contains("Select Skills")) {
                continue;
            }
            System.out.println(text);
        }

    }

    @Test
    public void radioButton() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.navigate("https://demo.automationtesting.in/Register.html");

            page.locator("label:has-text('FeMale')").click();

        }
    }

    @Test
    public void nestedFrames() throws InterruptedException {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://ui.vision/demo/webtest/frames/");
            FrameLocator nestedFrame = page.frameLocator("[src='frame_3.html']").frameLocator("xpath=//iframe[contains(@src,'docs.google.com')]");
            nestedFrame.locator("input[aria-label='Other response']").fill("Other value");
            nestedFrame.locator("text=Form Autofilling").click();
            Thread.sleep(3000);
        }
    }

    @Test
    public void shadowDOM()  {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://selectorshub.com/shadow-dom-in-iframe/");
            FrameLocator frameLocator =  page.frameLocator("[id='pact']");
            frameLocator.locator("input#tea").fill("Tea");
        }
    }

}
