package com.Beymen.Tests;

import com.Beymen.Pages.BasePage;
import org.junit.Test;

import java.io.IOException;

public class BeymenTest extends BaseTest {

    @Test
    public void beymenTest() throws IOException {
        logger = report.createTest("Beymen Selenium Web Automation");

        BasePage basePage = new BasePage();

        logInfo("www.beymen.com sitesi açılır.");
        basePage.openPage();

        logInfo("Ana sayfanın açıldığı kontrol edilir.");
        basePage.checkPage();

        logInfo("Arama kutucuğuna “şort” kelimesi girilir.");
        basePage.searchInInput(0, "şort");

        logInfo("Arama kutucuğuna girilen “şort” kelimesi silinir.");
        basePage.clearSearch();

        logInfo("Arama kutucuğuna “gömlek” kelimesi girilir.");
        basePage.searchInInput(1, "gömlek");

        logInfo("Klavye üzerinden “enter” tuşuna bastırılır.");
        basePage.pressEnter();

        logInfo("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.");
        basePage.getRandomProduct();

        logInfo("Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.");
        basePage.writeProductInformation();

        logInfo("Seçilen ürün sepete eklenir.");
        basePage.addToBasket();

        logInfo("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.");
        basePage.checkPriceInBasket();

        logInfo("Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.");
        basePage.increaseQuantity("2");

        logInfo("Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.");
        basePage.deleteFromBasket();

    }

    public void logInfo(String text) {
        logger.info(text);
        log4j.info(text);
    }

}