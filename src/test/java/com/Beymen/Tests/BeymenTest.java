package com.Beymen.Tests;

import com.Beymen.Pages.BasePage;
import org.junit.Test;

import java.io.IOException;

public class BeymenTest extends BaseTest {

    @Test
    public void beymenTest() throws IOException {
        BasePage basePage = new BasePage();

        //www.beymen.com sitesi açılır.
        basePage.openPage();

        //Ana sayfanın açıldığı kontrol edilir.
        basePage.checkPage();

        //Arama kutucuğuna “şort” kelimesi girilir.
        basePage.searchInInput(0, "şort");

        //Arama kutucuğuna girilen “şort” kelimesi silinir.
        basePage.clearSearch();

        //Arama kutucuğuna “gömlek” kelimesi girilir.
        basePage.searchInInput(1, "gömlek");

        //Klavye üzerinden “enter” tuşuna bastırılır.
        basePage.pressEnter();

        //Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
        basePage.getRandomProduct();

        //Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.
        basePage.writeProductInformation();

        //Seçilen ürün sepete eklenir.
        basePage.addToBasket();

    }

}