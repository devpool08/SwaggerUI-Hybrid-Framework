package org.swagger.base;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.swagger.utils.SingletonWebDriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Log4j2
@SuppressWarnings({"unused","ResultOfMethodCallIgnored"})
public class BaseTest {
    protected WebDriver driver;
    protected static SingletonWebDriverFactory factory;
    public Properties properties;
    public FileReader reader;
    public String browserName;

    @SneakyThrows
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        factory = SingletonWebDriverFactory.getInstance(browser);
        driver = factory.getDriver();
        properties = new Properties();
        reader = new FileReader("./src/test/resources/config.properties");
        properties.load(reader);
        browserName = browser;
        driver.manage().window().maximize();
        log.info("setUp completed for {}", this.getClass().getName());
    }
    public void openPage(String URL) {
        driver.get(URL);
        log.info("{} url is opened", URL);
    }
    public String captureScreenshot(String name) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + name + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

    @AfterClass
    public void tearDown() {
        log.info("{} test Completed Successfully", this.getClass().getName());
    }

}
