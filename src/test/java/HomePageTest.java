import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePageTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String homePageUrl = "https://intershop5.skillbox.ru/";
        driver.navigate().to(homePageUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private final By divideBooks = By.cssSelector("#accesspress_storemo-2 > div.promo-widget-wrap");
    private final By titlePagesBooks = By.xpath("//*[@class='entry-title ak-container']");
    private final By blockOnSale = By.cssSelector("div.promo-widget-wrap-full");
    private final By cardProduct = By.cssSelector("#primary");
    private final By logoSkillbox = By.cssSelector("#site-branding a");
    private final By promoProductFirstSection = By.cssSelector("#promo-section1");
    private final By badgeDiscount = By.cssSelector("#accesspress_store_product-2 li:nth-child(5) .onsale");
    private final By badgeNew = By.cssSelector("#accesspress_store_product-3 li:nth-child(5) .label-new");
    private final By searchString = By.cssSelector("input[type='text']:nth-child(1)");
    private final By searchButton = By.cssSelector("button[class='searchsubmit']");
    private final By headingOne = By.xpath("//*[@class='entry-title ak-container']");
    private final By divideCamera = By.cssSelector("#accesspress_storemo-4 > div.promo-widget-wrap");
    private final By cardProductOne = By.cssSelector("div.wc-products li:first-child .collection_combine");
    private final By chapterViewedProducts = By.cssSelector("#woocommerce_recently_viewed_products-2");

    @Test
    public void openInternetShopSkillbox_MainBlockTransitionCheck_BookCatalogOpen() {
        var titleText = "КНИГИ";
        driver.findElement(divideBooks).click();
        Assert.assertEquals("Заголовок страницы не соответствует товару на карточке",
                titleText, driver.findElement(titlePagesBooks).getText());
    }

    @Test
    public void openInternetShopSkillbox_ClickBlocForSale_ProductOnTheCardCatalogOpen() {
        driver.findElement(blockOnSale).click();
        Assert.assertTrue("Товар карточки 'Уже в продаже!' не открылся",
                driver.findElement(cardProduct).isDisplayed());
    }

    @Test
    public void openInternetShopSkillbox_ClickLogoSkillbox_MoveToHomePage() {
        driver.findElement(blockOnSale).click();
        driver.findElement(logoSkillbox).click();
        Assert.assertTrue("Переход на главную страницу не сработал",
                driver.findElement(promoProductFirstSection).isDisplayed());
    }

    @Test
    public void openInternetShopSkillbox_CheckingProductCardDiscounts_PresentDiscountText() {
        var textDiscountTest = "Скидка!";
        Assert.assertEquals("Текст 'Скидка!' отсутствует на карточке товара в разделе 'Распродажа'",
                textDiscountTest, driver.findElement(badgeDiscount).getText());
    }

    @Test
    public void openInternetShopSkillbox_CheckingProductCardNew_PresentNewText() {
        var textNewTest = "Новый!";
        Assert.assertEquals("Текст 'Новый!' отсутствует на карточке товара в разделе 'Новые поступления'",
                textNewTest, driver.findElement(badgeNew).getText());
    }

    @Test
    public void openInternetShopSkillbox_ScrollPageClickUp_PageScrolledUp() {
        driver.manage().window().maximize();
        driver.navigate().to("https://intershop5.skillbox.ru/");
        var buttonUp65 = By.xpath("//*[@style='right: -65px;']");
        var buttonUp20 = By.xpath("//*[@style='right: 20px;']");
        Assert.assertTrue("Есть элемент Up с 'style='right: 65px'", driver.findElement(buttonUp65).isDisplayed());
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonUp20));
        driver.findElement(buttonUp20).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonUp65));
        Assert.assertTrue("Есть элемент Up с 'style='right: 65px;'", driver.findElement(buttonUp65).isDisplayed());
    }

    @Test
    public void openInternetShopSkillbox_SearchBySearchBar_ProductPageOpened() {
        var searchText = "Холодильник";
        var titleText = "РЕЗУЛЬТАТЫ ПОИСКА: “ХОЛОДИЛЬНИК”";
        driver.findElement(searchString).sendKeys(searchText);
        driver.findElement(searchButton).click();
        Assert.assertEquals("Заголовок страницы не соответствует поиску товара",
                titleText, driver.findElement(headingOne).getText());
    }

    @Test
    public void openInternetShopSkillbox_CheckingTheViewedProductsBlock_TheBlockAppeared() {
        driver.findElement(divideCamera).click();
        driver.findElement(cardProductOne).click();
        driver.findElement(logoSkillbox).click();
        Assert.assertTrue("Раздел 'Просмотренные товары' отсутсвует",
                driver.findElement(chapterViewedProducts).isDisplayed());
    }
}


