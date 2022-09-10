package com.Beymen.Tests;

import com.Beymen.Pages.BasePage;
import org.junit.Test;

public class BeymenTest extends BaseTest {

    @Test
    public void beymenTest() {
        BasePage basePage = new BasePage();

        //www.beymen.com sitesi açılır.
        basePage.openPage();

        //Ana sayfanın açıldığı kontrol edilir.
        basePage.checkPage();


    }

}