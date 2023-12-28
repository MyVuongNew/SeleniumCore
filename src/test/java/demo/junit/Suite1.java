package demo.junit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Suite1 {
    private static WebDriver driver;
    @BeforeAll
    public static void beforeAll() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions handlingSSL = new ChromeOptions();

        //Using the accept insecure cert method with true as parameter to accept the untrusted certificate
        handlingSSL.setAcceptInsecureCerts(true);

        //Creating instance of Chrome driver by passing reference of ChromeOptions object
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before
    public void before() {

    }
    @Test
    public void LoginSuccess() {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[class$='orangehrm-login-button']")).click();
//        driver.quit();
    }

    @Test
    public void testCanvas() throws InterruptedException {
        this.LoginSuccess();
        Thread.sleep(5000);
        List<WebElement> els_canvas =  driver.findElements(By.tagName("canvas"));
        System.out.println("Size of list "+ els_canvas.size());
        if(els_canvas.size() > 0) {
            Dimension dimension_canvas = els_canvas.get(1).getSize();
            int xOffset = els_canvas.get(1).getSize().getWidth()/2;
            int yOffset = els_canvas.get(1).getSize().getHeight()/2;
            Actions action = new Actions(driver);
            action.moveToElement(els_canvas.get(1), xOffset, yOffset).click().perform();
        }
    }
    @Test
    public void checkSSLOfHM() {

        //Launching the URL
        driver.navigate().to("https://dev.hm-ctt.com/");
        driver.findElement(By.name("username")).sendKeys("JE016415");
        driver.findElement(By.name("password")).sendKeys("Copper@1");
        driver.findElement(By.id("submitButton")).click();
        System.out.println("The page title is : " +driver.getTitle());
        driver.quit();
    }

}
