package com.Beymen.Tests;

import com.Beymen.Utilities.ConfigurationReader;
import com.Beymen.Utilities.DriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    Properties properties;

    @Before
    public void before() {
        properties = ConfigurationReader.initializeProperties();
        driver = DriverManager.initializeDriver();
    }

    @After
    public void after() {
        DriverManager.terminateDriver();
    }

}