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

    String detailPrice;

    public BasePage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
    }

    By onetrustAcceptBtn = By.xpath("//*[@id='onetrust-accept-btn-handler']");

    By headerLogo = By.xpath("//*[@class='o-header__logo']");

    By searchInput = By.xpath("//*[contains(@class,'default-input o-header__search--input')]");

    By productList = By.xpath("//*[contains(@class, 'o-productList__itemWrapper')]");

    By productDescription = By.xpath("//*[@class='o-productDetail__description']");

    By productColor = By.xpath("//*[@class='m-colorsSlider__top']//label");

    By productPrice = By.xpath("//*[@id='priceNew']");

    By productSizes = By.xpath("//*[contains(@class, 'm-variation__item')]");

    By addBasketBtn = By.xpath("//*[@id='addBasket']");

    By notificationTitle = By.xpath("//*[@class='m-notification__title']");

    By notificationBtn = By.xpath("//*[@class='m-notification__button btn']");

    By orderSummaryTitle = By.xpath("//*[@class='m-orderSummary__title']");

    By productSalePrice = By.xpath("//*[@class='m-productPrice__salePrice']");


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
        elementHelper.checkElementVisible(productDescription);
    }

    public void writeProductInformation() {
        elementHelper.writeToTxt(productDescription, productColor, productPrice);
    }

    public void addToBasket() {
        detailPrice = elementHelper.getText(productPrice);
        int bodySizeCount = elementHelper.findElements(productSizes).size();
        while (true){
            Random random = new Random();
            int count = random.nextInt(bodySizeCount);
            WebElement selectedBody = elementHelper.findElements(productSizes).get(count);
            if (!selectedBody.getAttribute("class").contains("-disabled")) {
                selectedBody.click();
                break;
            }
        }
        elementHelper.click(addBasketBtn);
        elementHelper.checkElementVisible(notificationTitle);
        String actualText = elementHelper.getText(notificationTitle);
        String expectedText = "Sepete Eklendi";
        Assert.assertEquals(expectedText, actualText);
    }

    public void checkPriceInBasket() {
        elementHelper.click(notificationBtn);
        elementHelper.checkElementVisible(orderSummaryTitle);
        String salePrice = elementHelper.getText(productSalePrice);
        Assert.assertEquals(detailPrice, salePrice);
    }

}