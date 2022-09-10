package com.Beymen.Pages;

import com.Beymen.Tests.BaseTest;
import com.Beymen.Utilities.DriverManager;
import com.Beymen.Utilities.ElementHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Random;

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

    By searchInput = By.xpath("//input[contains(@class,'default-input o-header__search--input')]");

    By productList = By.xpath("//div[contains(@class, 'o-productList__itemWrapper')]");

    By productDetailDescription = By.xpath("//span[@class='o-productDetail__description']");

    By productDetailColor = By.xpath("//label[@class='m-form__label m-variation__label']");

    By productDetailPrice = By.xpath("//ins[@id='priceNew']");


    public void openPage() {
        DriverManager.initializeDriver();

        elementHelper.checkElementVisible(onetrustAcceptBtn);
        elementHelper.checkElementClickable(onetrustAcceptBtn);
        elementHelper.click(onetrustAcceptBtn);
    }

    public void checkPage() {
        elementHelper.checkElementVisible(headerLogo);
        String actualTitle = elementHelper.getAttribute(headerLogo, "title");
        String expectedTitle = "Beymen";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    public void searchInInput(int column, String expectedText) throws IOException {
        elementHelper.sendKey(searchInput, elementHelper.readFromExcel(column));
        String actualText = elementHelper.getAttribute(searchInput, "value");
        Assert.assertEquals(expectedText, actualText);
    }

    public void clearSearch() {
        elementHelper.sendKey(searchInput, Keys.CONTROL + "A" + Keys.DELETE);
        String actualText = elementHelper.getAttribute(searchInput, "value");
        String expectedText = "";
        Assert.assertEquals(expectedText, actualText);
    }

    public void pressEnter() {
        elementHelper.sendKey(searchInput, "" + Keys.ENTER);
    }

    public void getRandomProduct() {
        Random random = new Random();
        int productSize = elementHelper.findElements(productList).size();
        int count = random.nextInt(productSize);
        WebElement product = elementHelper.findElements(productList).get(count);
        elementHelper.scrollToElement(product);
        product.click();
        elementHelper.checkElementVisible(productDetailDescription);
    }

    public void writeProductInformation(){
        elementHelper.writeToTxt(productDetailDescription, productDetailColor, productDetailPrice);
    }

}