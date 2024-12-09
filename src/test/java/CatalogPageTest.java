import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPageTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String catalogPageUrl = "https://intershop5.skillbox.ru/product-category/catalog/";
        driver.navigate().to(catalogPageUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private final By catalogTitle = By.xpath("//*[@class='entry-title ak-container']");
    private final By rightNavigationButton = By.cssSelector(".woocommerce-pagination li:last-child");
    private final By theeNavigationButton = By.cssSelector("li:nth-child(3) span[aria-current='page']");
    private final By cardProduct = By.cssSelector(".products li:nth-child(4)");
    private final By catalogProductTitle = By.cssSelector(".products li:nth-child(4) h3");
    private final By cardProductTitle = By.cssSelector(".product_title");
    private final By categoryPhone = By.cssSelector(".product-categories li:nth-child(9) a");
    private final By categoryPhoneTitle = By.xpath("//*[@class='entry-title ak-container']");
    private final By productButtonAdd = By.cssSelector(".products li:nth-child(4) .price-cart a");
    private final By productButtonDetails = By.cssSelector(".products li:nth-child(4) .price-cart a.added_to_cart");
    private final By breadcrumbBasket = By.cssSelector(".current");
    private final By backNavigationButton = By.cssSelector(".woocommerce-pagination li:first-child");
    private final By forwardNavigationButton = By.cssSelector(".woocommerce-pagination li:nth-child(1)");
    private final By pageNumber = By.cssSelector(".woocommerce-result-count");

    @Test
    public void openInternetShopSkillbox_CheckingCatalogPageTitle_TitleMatches() {
        var catalogTitleText = "КАТАЛОГ";
        Assert.assertEquals("Открыта другая страница или заголовок не совпадает", catalogTitleText, driver.findElement(catalogTitle).getText());
    }

    @Test
    public void openInternetShopSkillbox_ClickTheForwardButtonBlockAllProducts_ProductsHaveChanged() {
        driver.findElement(rightNavigationButton).click();
        Assert.assertTrue("Переход на другую страницу каталога товаров", driver.findElement(theeNavigationButton).isDisplayed());
    }

    @Test
    public void openInternetShopSkillbox_PressForwardBackwardButtons_TransitionCompleted() {
        var pageNumberText = driver.findElement(pageNumber).getText();
        driver.findElement(backNavigationButton).click();
        driver.findElement(forwardNavigationButton).click();
        Assert.assertEquals("Открыта другая страница каталога", pageNumberText, driver.findElement(pageNumber).getText());
    }

    @Test
    public void openInternetShopSkillbox_OpenProductCard_CardOpen() {
        var catalogProductTitleText = driver.findElement(catalogProductTitle).getText();
        driver.findElement(cardProduct).click();
        Assert.assertEquals("Заголовок не совпадает",
                catalogProductTitleText, driver.findElement(cardProductTitle).getText());

    }

    @Test
    public void openInternetShopSkillbox_SelectingProduct_CategoryPhoneOpen() {
        var catalogProductTitleText = "ТЕЛЕФОНЫ";
        driver.findElement(categoryPhone).click();
        Assert.assertEquals("Заголовок категирии товара не совпадает с открытой страницей",
                catalogProductTitleText, driver.findElement(categoryPhoneTitle).getText());
    }

    @Test
    public void openInternetShopSkillbox_OpenCatalogThroughProductCategory_CatalogOpen() {
        var categoryCloth = By.cssSelector(".product-categories li:nth-child(5) a");
        var catalog = By.cssSelector(".woocommerce-breadcrumb a:nth-child(2)");
        var title = By.xpath("//*[@class='entry-title ak-container']");
        var titleText = "КАТАЛОГ";
        driver.findElement(categoryCloth).click();
        driver.findElement(catalog).click();
        Assert.assertEquals("Заголовок категирии товара не совпадает с открытой страницей",
                titleText, driver.findElement(title).getText());
    }

    @Test
    public void openInternetShopSkillbox_AddProductToCartFrom_ProductAdded() {
        var basketText = "Корзина";
        driver.findElement(productButtonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(productButtonDetails));
        driver.findElement(productButtonDetails).click();
        Assert.assertEquals("Переход в 'Корзину' не выполнен",
                basketText, driver.findElement(breadcrumbBasket).getText());
    }
}
