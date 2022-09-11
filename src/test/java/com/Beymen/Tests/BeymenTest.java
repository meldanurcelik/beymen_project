package com.Beymen.Tests;

import com.Beymen.Pages.BasePage;
import org.junit.Test;

import java.io.IOException;

public class BeymenTest extends BaseTest {

    @Test
    public void beymenTest() throws IOException {
        logger = report.createTest("Beymen Selenium Web Automation");

        BasePage basePage = new BasePage();

        logger.info("www.beymen.com sitesi açılır.");
        basePage.openPage();

        logger.info("Ana sayfanın açıldığı kontrol edilir.");
        basePage.checkPage();

        logger.info("Arama kutucuğuna “şort” kelimesi girilir.");
        basePage.searchInInput(0, "şort");

        logger.info("Arama kutucuğuna girilen “şort” kelimesi silinir.");
        basePage.clearSearch();

        logger.info("Arama kutucuğuna “gömlek” kelimesi girilir.");
        basePage.searchInInput(1, "gömlek");

        logger.info("Klavye üzerinden “enter” tuşuna bastırılır.");
        basePage.pressEnter();

        logger.info("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.");
        basePage.getRandomProduct();

        logger.info("Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.");
        basePage.writeProductInformation();

        logger.info("Seçilen ürün sepete eklenir.");
        basePage.addToBasket();

        logger.info("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.");
        basePage.checkPriceInBasket();

        logger.info("Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.");
        basePage.increaseQuantity("2");

        logger.info("Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.");
        basePage.deleteFromBasket();

    }

}