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
        basePage.searchInInput();
    }

}