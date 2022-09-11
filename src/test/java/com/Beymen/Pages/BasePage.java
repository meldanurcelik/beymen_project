package com.Beymen.Pages;

import com.Beymen.Tests.BaseTest;
import com.Beymen.Utilities.DriverManager;
import com.Beymen.Utilities.ElementHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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

    ExtentReports report;
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

    By removeCartNotifTitlle = By.xpath("//*[@id='notifyTitle']");

    By emptyMessageBtn = By.xpath("//*[@class='m-empty__messageBtn']");


    public void openPage() throws IOException {
        try {
            DriverManager.initializeDriver();
            elementHelper.checkElementVisible(onetrustAcceptBtn);
            elementHelper.checkElementClickable(onetrustAcceptBtn);
            elementHelper.click(onetrustAcceptBtn);
            logger.log(Status.PASS, "Test Case PASSED");
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
            throw new RuntimeException(e);
        }
    }

    public void checkPage() throws IOException {
        try {
            elementHelper.checkElementVisible(headerLogo);
            String actualTitle = elementHelper.getAttribute(headerLogo, "title");
            String expectedTitle = "Beymen";
            logger.log(Status.PASS, "Test Case PASSED");
            Assert.assertEquals(expectedTitle, actualTitle);
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
            throw new RuntimeException(e);
        }
    }

    public void searchInInput(int column, String expectedText) throws IOException {
        try {
            elementHelper.sendKey(searchInput, elementHelper.readFromExcel(column));
            String actualText = elementHelper.getAttribute(searchInput, "value");
            logger.log(Status.PASS, "Test Case PASSED");
            Assert.assertEquals(expectedText, actualText);
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
            throw new RuntimeException(e);
        }
    }

    public void clearSearch() throws IOException {
        try {
            elementHelper.sendKey(searchInput, Keys.CONTROL + "A" + Keys.DELETE);
            String actualText = elementHelper.getAttribute(searchInput, "value");
            String expectedText = "";
            logger.log(Status.PASS, "Test Case PASSED");
            Assert.assertEquals(expectedText, actualText);
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
            throw new RuntimeException(e);
        }
    }

    public void pressEnter() throws IOException {
        try {
            elementHelper.sendKey(searchInput, "" + Keys.ENTER);
            logger.log(Status.PASS, "Test Case PASSED");
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
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
            logger.log(Status.PASS, "Test Case PASSED");
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
            throw new RuntimeException(e);
        }

    }

    public void writeProductInformation() throws IOException {
        try {
            elementHelper.writeToTxt(productDescription, productColor, productPrice);
            logger.log(Status.PASS, "Test Case PASSED");
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
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
            logger.log(Status.PASS, "Test Case PASSED");
            Assert.assertEquals(expectedText, actualText);
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
            throw new RuntimeException(e);
        }
    }

    public void checkPriceInBasket() throws IOException {
        try {
            elementHelper.click(addedToCartNotifBtn);
            elementHelper.checkElementVisible(orderSummaryTitle);
            String salePrice = elementHelper.getText(productSalePrice);
            logger.log(Status.PASS, "Test Case PASSED");
            Assert.assertEquals(detailPrice, salePrice);
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
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
            logger.log(Status.PASS, "Test Case PASSED");
            Assert.assertEquals(quantity + " adet", actualQuantity);
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
            throw new RuntimeException(e);
        }
    }

    public void deleteFromBasket() throws IOException {
        try {
            elementHelper.click(removeCartItemBtn);
            elementHelper.checkElementVisible(removeCartNotifTitlle);
            elementHelper.checkElementVisible(emptyMessageBtn);
            String actualTitle = elementHelper.getAttribute(emptyMessageBtn, "title");
            String expectedTitle = "Alışverişe Devam Et";
            logger.log(Status.PASS, "Test Case PASSED");
            Assert.assertEquals(expectedTitle, actualTitle);
        } catch (Exception e) {
            String screenshotPath = elementHelper.getScreenShot(driver);
            logger.log(Status.FAIL, "Test Case FAILED");
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
            throw new RuntimeException(e);
        }
    }

}