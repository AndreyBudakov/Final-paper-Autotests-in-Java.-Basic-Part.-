import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationAndRegistrationTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String authorizationAndRegistrationPageUrl = "https://intershop5.skillbox.ru/my-account/";
        driver.navigate().to(authorizationAndRegistrationPageUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private final By buttonRegistration = By.cssSelector(".custom-register-button");
    private final By breadcrumbRegistration = By.cssSelector(".current");
    private final By fieldName = By.cssSelector("#reg_username");
    private final By fieldEmail = By.cssSelector("#reg_email");
    private final By fieldPassword = By.cssSelector("#reg_password");
    private final By buttonRegistrationTwo = By.cssSelector("[name='register']");
    /*Последния кнопка buttonRegistrationTwo 'зарегистрироваться' после заполнения данных*/
    private final By fieldNameOrEmailLogin = By.cssSelector("#username");
    private final By fieldPasswordLogin = By.cssSelector("#password");
    private final By buttonEntrance = By.cssSelector("[name='login']");
    private final By breadcrumbAccount = By.cssSelector(".current");
    private final By buttonExitEntrance = By.cssSelector(".login-woocommerce");
    private final By linkExitMenu = By.cssSelector(".woocommerce-MyAccount-content strong ~*");
    private final String name = "Buda1";
    private final String email = "Buda1@mail.ru";
    private final String password = "B123";

    @Test
    public void openInternetShopSkillbox_UserRegistration_SuccessfulRegistration() { /*Нужно изменять данные при регистрации*/

        var breadcrumbRegistrationText = "Регистрация";
        var finishRegistration = By.cssSelector(".content-page");
        var finishFinishRegistrationText = "Регистрация завершена";

        driver.findElement(buttonRegistration).click();
        Assert.assertEquals("Открыта другая страница",
                breadcrumbRegistrationText, driver.findElement(breadcrumbRegistration).getText());
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(buttonRegistrationTwo).click();
        Assert.assertEquals("Не прошла регистрация или неверное сообщение об ошибке",
                finishFinishRegistrationText, driver.findElement(finishRegistration).getText());
    }
    @Test
    public void openInternetShopSkillbox_LoginToAccount_LoginCompleted() {

        var breadcrumbAccountText = "Мой Аккаунт";

        driver.findElement(fieldNameOrEmailLogin).sendKeys(name); /*Можно Через Email*/
        driver.findElement(fieldPasswordLogin).sendKeys(password);
        driver.findElement(buttonEntrance).click();
        Assert.assertEquals("Открыта другая страница или заголовок ошибочный",
                breadcrumbAccountText, driver.findElement(breadcrumbAccount).getText());
    }
    @Test
    public void openInternetShopSkillbox_LogoutButtonPageFooter_LogoutSignedOutFromAccount() {

        var buttonText = "Войти";

        driver.findElement(fieldNameOrEmailLogin).sendKeys(email);
        driver.findElement(fieldPasswordLogin).sendKeys(password);
        driver.findElement(buttonEntrance).click();
        driver.findElement(buttonEntrance).click();
        driver.findElement(buttonExitEntrance).click();
        Assert.assertEquals("Кнопка 'Выйти' отсутствует или открыта другая страница",
                buttonText, driver.findElement(buttonExitEntrance).getText());
    }
    @Test
    public void openInternetShopSkillbox_LogoutButtonPageMenu_LogoutSignedOutFromAccount() {

        var buttonText = "Войти";

        driver.findElement(fieldNameOrEmailLogin).sendKeys(name);
        driver.findElement(fieldPasswordLogin).sendKeys(password);
        driver.findElement(buttonEntrance).click();
        driver.findElement(linkExitMenu).click();
        Assert.assertEquals("Кнопка(Ссылка) 'Выйти' отсутствует или открыта другая страница",
                buttonText, driver.findElement(buttonExitEntrance).getText());
    }
}
