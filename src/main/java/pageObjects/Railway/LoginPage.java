package pageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage
{

    //Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _IbILoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _tabForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");

    //Elements
    public WebElement getTxtUsername()
    {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }
    public WebElement getTxtPassword()
    {

        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getBtnLogin()
    {

        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    public WebElement getTabForgotPassword() {

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));

        try {
            // Chờ cho đến khi phần tử "Register" có mặt và có thể nhấp được
            return wait.until(ExpectedConditions.elementToBeClickable(_tabForgotPassword)); // Thay đổi XPath nếu cần
        } catch (TimeoutException e) {
            System.out.println("Đã hết thời gian chờ khi tìm tab 'ForgotPassword': " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy tab 'ForgotPassword': " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Một lỗi khác đã xảy ra: " + e.getMessage());
        }
        return null; // Trả về null nếu không tìm thấy phần tử hoặc có lỗi
    }
    private WebElement getLbLoginErrorMsg() {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
        try {
            // Chờ cho đến khi thông báo lỗi có thể nhìn thấy
            return wait.until(ExpectedConditions.visibilityOfElementLocated(_IbILoginErrorMsg));
        } catch (TimeoutException e) {
            System.err.println("Thông báo lỗi không tìm thấy: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy thông báo lỗi
        } catch (NoSuchElementException e) {
            System.err.println("Không tìm thấy phần tử thông báo lỗi: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy phần tử
        }
    }

    public String getErrorMessage()
    {
        return this.getLbLoginErrorMsg().getText();
    }


    public void login(String username, String password)
    {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();

    }

    public ForgotPasswordPage gotoForgotPasswordPage() {
        this.getTabForgotPassword().click();
        return new ForgotPasswordPage();
    }


}