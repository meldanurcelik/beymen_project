package com.Beymen.Utilities;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ElementHelper {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.action = new Actions(driver);
    }

    public WebElement findElement(By key) {
        WebElement element = presenceElement(key);
        scrollToElement(element);
        return element;
    }

    public List<WebElement> findElements(By key) {
        List<WebElement> elements = presenceElements(key);
        //scrollToElement(elements.get(0));
        return elements;
    }

    public void click(By key) {
        findElement(key).click();
    }

    public void sendKey(By key, String text) {
        findElement(key).sendKeys(text);
    }

    public void clear(By key) {
        findElement(key).clear();
    }

    public String getText(By key) {
        return findElement(key).getText();
    }

    public boolean checkElementText(By key, String text) {
        return wait.until(ExpectedConditions.textToBe(key, text));
    }

    public void checkElementVisible(By key) {
        wait.until(ExpectedConditions.visibilityOf(findElement(key)));
    }

    public void checkElementClickable(By key) {
        wait.until(ExpectedConditions.elementToBeClickable(findElement(key)));
    }

    public boolean checkTitle(String text) {
        return wait.until(ExpectedConditions.titleIs(text));
    }

    public String getAttribute(By key, String attr) {
        return findElement(key).getAttribute(attr);
    }

    public WebElement presenceElement(By key) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(key));
    }

    public List<WebElement> presenceElements(By key) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(key));
    }

    public void scrollToElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    public String readFromExcel(int column) throws IOException {
        String fileName = "beymen";
        File filePath = new File("./src/test/resources/" + fileName + ".xlsx");
        FileInputStream fs = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(column);
        return cell.toString();
    }

    public void writeToTxt(By descriptionKey, By colorKey, By priceKey)  {
        try {
            FileWriter fileWriter = new FileWriter("./Reports/productInfos.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Ürün Açıklaması : " + getText(descriptionKey));
            printWriter.println("Ürün Rengi : " + getText(colorKey));
            printWriter.println("Ürün Fiyatı : " + getText(priceKey));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getScreenShot(WebDriver driver) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshots/testResult-" + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

}