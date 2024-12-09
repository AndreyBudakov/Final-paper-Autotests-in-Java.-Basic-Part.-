import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlacingAnOrderTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {

        System.setProperty("webdriver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String placingAnOrderPageUrl = "https://intershop5.skillbox.ru/my-account/";
        driver.get(placingAnOrderPageUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    private final By nameEmail = By.cssSelector("#username");
    private final By password = By.cssSelector("#password");
    private final By buttonEntrance = By.cssSelector("[name='login']");
    private final By catalog = By.cssSelector("#menu-item-46");
    private final By buttonAdd = By.cssSelector(".post-15 .price-cart a");
    private final By buttonDetails = By.cssSelector(".post-15 .price-cart a.added_to_cart");
    private final By buttonDesign = By.cssSelector(".checkout-button");
    private final By name = By.cssSelector("#billing_first_name");
    private final By surname = By.cssSelector("#billing_last_name");
    private final By address = By.cssSelector("#billing_address_1");
    private final By street = By.cssSelector("#billing_city");
    private final By region = By.cssSelector("#billing_state");
    private final By indexMail = By.cssSelector("#billing_postcode");
    private final By phone = By.cssSelector("#billing_phone");
    private final By buttonDesignLast = By.cssSelector("#place_order");
    private final By titleOrderReceived = By.cssSelector(".woocommerce-notice");
    private final By buttonDirectBankTransfer = By.cssSelector("#payment_method_bacs");
    private final By buttonPayOnDelivery = By.cssSelector("#payment_method_cod");
    private final By bu = By.cssSelector("#payment_method_bacs");
    public final String nameEmailText = "Dell8";
    public final String passwordText = "Dell8";
    public final String nameText = "Вася";
    public final String surnameText = "Сайтов";
    public final String addressText = "Тестовая 404 Дом 6";
    public final String streetText = "Екатеринбург";
    public final String regionText = "Свердловская область";
    public final String indexMailText = "666999";
    public final String phoneText = "89996669900";
    public final String titleOrderReceivedText = "Спасибо! Ваш заказ был получен.";
    String[] locators = {"#billing_first_name", "#billing_last_name", "#billing_address_1", "#billing_city", "#billing_state", "#billing_postcode", "#billing_phone"};

    @Test
    public void openInternetShopSkillbox_ProductDesignSpecifyDirectBankTransfer_PageOpenedPlacingAnOrder() {


        driver.findElement(nameEmail).sendKeys(nameEmailText);
        driver.findElement(password).sendKeys(passwordText);
        driver.findElement(buttonEntrance).click();
        driver.findElement(catalog).click();
        driver.findElement(buttonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonDetails));
        driver.findElement(buttonDetails).click();
        driver.findElement(buttonDesign).click();
        for (String locator : locators) {
            List<WebElement> inputFields = driver.findElements(By.cssSelector(locator));
            for (WebElement inputField : inputFields) {
                inputField.clear();
            }
        }
        driver.findElement(name).sendKeys(nameText);
        driver.findElement(surname).sendKeys(surnameText);
        driver.findElement(address).sendKeys(addressText);
        driver.findElement(street).sendKeys(streetText);
        driver.findElement(region).sendKeys(regionText);
        driver.findElement(indexMail).sendKeys(indexMailText);
        driver.findElement(phone).sendKeys(phoneText);
        driver.findElement(buttonDirectBankTransfer).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonDesignLast));
        driver.findElement(buttonDesignLast).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(titleOrderReceived));

        Assert.assertEquals("Ошибка оформления заказа или ошибка в сообщении",
                titleOrderReceivedText, driver.findElement(titleOrderReceived).getText());
    }

    @Test
    public void openInternetShopSkillbox_SelectPaymentOnDelivery_PageOpenedPlacingAnOrder() {

        driver.findElement(buttonEntrance);
        driver.findElement(nameEmail).sendKeys(nameEmailText);
        driver.findElement(password).sendKeys(passwordText);
        driver.findElement(buttonEntrance).click();
        driver.findElement(catalog).click();
        driver.findElement(buttonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonDetails));
        driver.findElement(buttonDetails).click();
        driver.findElement(buttonDesign).click();
        for (String locator : locators) {
            List<WebElement> inputFields = driver.findElements(By.cssSelector(locator));
            for (WebElement inputField : inputFields) {
                inputField.clear();
            }
        }
        driver.findElement(name).sendKeys(nameText);
        driver.findElement(surname).sendKeys(surnameText);
        driver.findElement(address).sendKeys(addressText);
        driver.findElement(street).sendKeys(streetText);
        driver.findElement(region).sendKeys(regionText);
        driver.findElement(indexMail).sendKeys(indexMailText);
        driver.findElement(phone).sendKeys(phoneText);
        driver.findElement(buttonPayOnDelivery).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonDesignLast));
        driver.findElement(buttonDesignLast).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(titleOrderReceived));

        Assert.assertEquals("Ошибка оформления заказа или ошибка в сообщении",
                titleOrderReceivedText, driver.findElement(titleOrderReceived).getText());
    }
}
