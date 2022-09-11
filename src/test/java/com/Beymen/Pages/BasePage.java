package com.Beymen.Pages;

import com.Beymen.Tests.BaseTest;
import com.Beymen.Utilities.DriverManager;
import com.Beymen.Utilities.ElementHelper;
import com.Beymen.Utilities.ReadExcelFile;
import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;
    ExtentTest logger;

    private String detailPrice;

    public BasePage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
        this.logger = BaseTest.logger;
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

    By addedToCartNotifTitle = By.xpath("//*[@class='m-notification__title']");

    By addedToCartNotifBtn = By.xpath("//*[@class='m-notification__button btn']");

    By orderSummaryTitle = By.xpath("//*[@class='m-orderSummary__title']");

    By productSalePrice = By.xpath("//*[@class='m-productPrice__salePrice']");

    By quantityDropdown = By.xpath("//*[@id='quantitySelect0-key-0']");

    By removeCartItemBtn = By.xpath("//*[@id='removeCartItemBtn0-key-0']");

    By removeCartNotifTitle = By.xpath("//*[@id='notifyTitle']");

    By emptyMessageBtn = By.xpath("//*[@class='m-empty__messageBtn']");


    public void openPage() throws IOException {
        try {
            DriverManager.initializeDriver();
            elementHelper.checkElementVisible(onetrustAcceptBtn);
            elementHelper.checkElementClickable(onetrustAcceptBtn);
            elementHelper.click(onetrustAcceptBtn);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void checkPage() throws IOException {
        try {
            elementHelper.checkElementVisible(headerLogo);
            String actualTitle = elementHelper.getAttribute(headerLogo, "title");
            String expectedTitle = "Beymen";
            if (expectedTitle.equals(actualTitle)) {
                Assert.assertEquals(expectedTitle, actualTitle);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void searchInInput(int column, String expectedText) throws IOException {
        try {
            elementHelper.sendKey(searchInput, ReadExcelFile.readFromExcel(column));
            String actualText = elementHelper.getAttribute(searchInput, "value");
            if (expectedText.equals(actualText)) {
                Assert.assertEquals(expectedText, actualText);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void clearSearch() throws IOException {
        try {
            elementHelper.sendKey(searchInput, Keys.CONTROL + "A" + Keys.DELETE);
            String actualText = elementHelper.getAttribute(searchInput, "value");
            String expectedText = "";
            if (expectedText.equals(actualText)) {
                Assert.assertEquals(expectedText, actualText);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void pressEnter() throws IOException {
        try {
            elementHelper.sendKey(searchInput, "" + Keys.ENTER);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void getRandomProduct() throws IOException {
        try {
            Random random = new Random();
            int productSize = elementHelper.findElements(productList).size();
            int count = random.nextInt(productSize);
            WebElement product = elementHelper.findElements(productList).get(count);
            elementHelper.scrollToElement(product);
            product.click();
            elementHelper.checkElementVisible(productDescription);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }

    }

    public void writeProductInformation() throws IOException {
        try {
            elementHelper.writeToTxt(productDescription, productColor, productPrice);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void addToBasket() throws IOException {
        try {
            detailPrice = elementHelper.getText(productPrice);
            int bodySizeCount = elementHelper.findElements(productSizes).size();
            while (true) {
                Random random = new Random();
                int count = random.nextInt(bodySizeCount);
                WebElement selectedBody = elementHelper.findElements(productSizes).get(count);
                if (!selectedBody.getAttribute("class").contains("-disabled")) {
                    selectedBody.click();
                    break;
                }
            }
            elementHelper.click(addBasketBtn);
            elementHelper.checkElementVisible(addedToCartNotifTitle);
            String actualText = elementHelper.getText(addedToCartNotifTitle);
            String expectedText = "Sepete Eklendi";
            if (expectedText.equals(actualText)) {
                Assert.assertEquals(expectedText, actualText);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void checkPriceInBasket() throws IOException {
        try {
            elementHelper.click(addedToCartNotifBtn);
            elementHelper.checkElementVisible(orderSummaryTitle);
            String salePrice = elementHelper.getText(productSalePrice);
            if (detailPrice.equals(salePrice)) {
                Assert.assertEquals(detailPrice, salePrice);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void increaseQuantity(String quantity) throws IOException {
        try {
            Select select = new Select(elementHelper.findElement(quantityDropdown));
            List<WebElement> options = select.getOptions();
            int optionSize = options.size();
            for (int i = 0; i < optionSize; i++) {
                if (options.get(i).getText().contains(quantity)) {
                    select.selectByValue(quantity);
                    break;
                }
            }
            String actualQuantity = select.getFirstSelectedOption().getText();
            if ((quantity + " adet").equals(actualQuantity)) {
                Assert.assertEquals(quantity + " adet", actualQuantity);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void deleteFromBasket() throws IOException {
        try {
            elementHelper.click(removeCartItemBtn);
            elementHelper.checkElementVisible(removeCartNotifTitle);
            elementHelper.checkElementVisible(emptyMessageBtn);
            String actualTitle = elementHelper.getAttribute(emptyMessageBtn, "title");
            String expectedTitle = "Alışverişe Devam Et";
            if (expectedTitle.equals(actualTitle)) {
                Assert.assertEquals(expectedTitle, actualTitle);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

}