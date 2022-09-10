package com.Beymen.Pages;

import com.Beymen.Tests.BaseTest;
import com.Beymen.Utilities.DriverManager;
import com.Beymen.Utilities.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;

    public BasePage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
    }

    By onetrustAcceptBtn = By.xpath("//button[@id='onetrust-accept-btn-handler']");

    public void openPage() {
        DriverManager.initializeDriver();

        elementHelper.checkElementClickable(onetrustAcceptBtn);
        elementHelper.click(onetrustAcceptBtn);
    }

}