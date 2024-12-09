import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPageTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String catalogPageUrl = "https://intershop5.skillbox.ru/product-category/catalog/";
        String basketPageUrl = "https://intershop5.skillbox.ru/cart/";
        driver.navigate().to(catalogPageUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    private final By buttonBasketMenu = By.cssSelector(".menu-item-29");
    private final By breadcrumbBasket = By.cssSelector(".current");
    private final By productButtonAdd = By.cssSelector(".products li:nth-child(4) .price-cart a");
    private final By productButtonDetails = By.cssSelector(".products li:nth-child(4) .price-cart a.added_to_cart");
    private final By productButtonDelete = By.cssSelector(".product-remove a");
    private final By messageBasket = By.cssSelector(".cart-empty");
    private final By buttonReturn = By.cssSelector(".restore-item");
    private final By buttonCheckout = By.cssSelector(".wc-proceed-to-checkout");
    private final By breadcrumbCheckout = By.cssSelector(".post-title");
    private final By nameProduct = By.cssSelector(".products li:nth-child(4) h3");
    private final By nameProductBasket = By.cssSelector("tbody .product-name");
    private final By fieldCoupon = By.cssSelector("#coupon_code");
    private final By buttonApplyCoupon = By.cssSelector("[name='apply_coupon']");
    private final By discountCoupon = By.xpath("//*[@class='cart-discount coupon-sert500']");
    private final By messageCoupon = By.cssSelector(".woocommerce-message");
    private final By messageCouponError = By.cssSelector(".woocommerce-error li");

    @Test
    public void openInternetShopSkillbox_NavigationButtonBasket_TransitionBasket() {
        var breadcrumbBasketText = "Корзина";
        driver.findElement(buttonBasketMenu).click();
        Assert.assertEquals("Открыта другая страница",
                breadcrumbBasketText, driver.findElement(breadcrumbBasket).getText());
    }
    @Test
    public void openInternetShopSkillbox_RemoveProductCart_CartEmpty() {

        var messageBasketText = "Корзина пуста.";
        driver.findElement(productButtonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(productButtonDetails));
        driver.findElement(productButtonDetails).click();
        driver.findElement(productButtonDelete).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(messageBasket));
        Assert.assertEquals("Нет сообщение о пустой корзине или товар не удалён",
                messageBasketText, driver.findElement(messageBasket).getText());
    }
    @Test
    public void openInternetShopSkillbox_ProductDesign_PageOpenedPlacingAnOrder() {

        var breadcrumbCheckoutText = "Оформление заказа";
        driver.findElement(productButtonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(productButtonDetails));
        driver.findElement(productButtonDetails).click();
        driver.findElement(buttonCheckout).click();
        Assert.assertEquals("Открыта не та страница. Нужна страница 'Оформление заказа'",
                breadcrumbCheckoutText, driver.findElement(breadcrumbCheckout).getText());
    }
    @Test
    public void openInternetShopSkillbox_CheckPoductName_TheNameMatches() {

        var nameProductCatalog = driver.findElement(nameProduct).getText();
        driver.findElement(productButtonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(productButtonDetails));
        driver.findElement(productButtonDetails).click();
        Assert.assertEquals("Разные названия товара", nameProductCatalog, driver.findElement(nameProductBasket).getText());
    }
    @Test
    public void openInternetShopSkillbox_ApplyCoupon_DiscountOk() {

        var couponText = "sert500";
        var couponMessageText = "Купон успешно добавлен.";
        driver.findElement(productButtonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(productButtonDetails));
        driver.findElement(productButtonDetails).click();
        driver.findElement(fieldCoupon).sendKeys(couponText);
        driver.findElement(buttonApplyCoupon).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(messageCoupon));
        Assert.assertEquals("Текст сообщения ошибочный", couponMessageText, driver.findElement(messageCoupon).getText());
        wait.until(ExpectedConditions.presenceOfElementLocated(discountCoupon));
        Assert.assertTrue("Купон не применился", driver.findElement(discountCoupon).isDisplayed());
    }
    @Test
    public void openInternetShopSkillbox_InvalidCouponInput_ErrorCoupon() {

        var couponText = "go500";
        var errorText = "Неверный купон.";
        driver.findElement(productButtonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(productButtonDetails));
        driver.findElement(productButtonDetails).click();
        driver.findElement(fieldCoupon).sendKeys(couponText);
        driver.findElement(buttonApplyCoupon).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(messageCouponError));
        Assert.assertEquals("Неверный купон или сообщение ошибочное",
                errorText, driver.findElement(messageCouponError).getText());
    }
    private final By byText = By.cssSelector(".woocommerce-info");
    @Test
    public void openInternetShopSkillbox_ReturnProductCart_ItemInCart() {

        var text = "Корзина пуста.";
        driver.findElement(productButtonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(productButtonDetails));
        driver.findElement(productButtonDetails).click();
        driver.findElement(productButtonDelete).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(byText));
        Assert.assertEquals("Товар не удалён", text, driver.findElement(byText).getText());
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonReturn));
        driver.findElement(buttonReturn).click();

    }
}

