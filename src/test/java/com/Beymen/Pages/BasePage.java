package com.Beymen.Pages;

import com.Beymen.Tests.BaseTest;
import com.Beymen.Utilities.DriverManager;
import com.Beymen.Utilities.ElementHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

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

    By headerLogo = By.xpath("//a[@class='o-header__logo']");

    By searchInput = By.xpath("//input[@class='default-input o-header__search--input']");

    public void openPage() {
        DriverManager.initializeDriver();

        elementHelper.checkElementClickable(onetrustAcceptBtn);
        elementHelper.click(onetrustAcceptBtn);
    }

    public void checkPage() {
        elementHelper.checkElementVisible(headerLogo);
        String actualTitle = elementHelper.getAttribute(headerLogo, "title");
        String expectedTitle = "Beymen";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    public void searchInInput(int column, String text) throws IOException {
        elementHelper.sendKey(searchInput, elementHelper.readFromExcel(column));
        String actualText = elementHelper.getAttribute(searchInput, "value");
        String expectedText = text;
        Assert.assertEquals(expectedText, actualText);
    }

    public void clearSearch() {
        elementHelper.sendKey(searchInput, Keys.CONTROL + "A" + Keys.DELETE);
        String actualText = elementHelper.getAttribute(searchInput, "value");
        String expectedText = "";
        Assert.assertEquals(expectedText, actualText);
    }

}