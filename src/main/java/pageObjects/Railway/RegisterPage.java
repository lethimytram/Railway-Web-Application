package pageObjects.Railway;
import Common.Constant.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    // Locators
    private final By _txtUsername = By.xpath("//input[@id='email']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _txtconfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _txtPID = By.xpath("//input[@id='pid']"); // Locator cho trường PID
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblConfirmationMsg = By.xpath("//div[@id='content']/p"); // Locator cho thông báo xác nhận
    private final By _lblErrorMsg = By.xpath("//p[@class='message error']");
    private final By _lblPasswordErrorMsg = By.xpath("//label[@for='password' and @class='validation-error']");
    private final By _lblPidErrorMsg = By.xpath("//label[@for='pid' and @class='validation-error']");
    // Elements
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getTxtconfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtconfirmPassword);
    }
    public WebElement getTxtPID() {
        return Constant.WEBDRIVER.findElement(_txtPID);
    }

    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }
    public WebElement getLblConfirmationMessage() {
        return Constant.WEBDRIVER.findElement(_lblConfirmationMsg);
    }
    private WebElement getLblErrorMsg() {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
        try {
            // Chờ cho đến khi thông báo lỗi có thể nhìn thấy
            return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblErrorMsg));
        } catch (TimeoutException e) {
            System.err.println("Thông báo lỗi không tìm thấy: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy thông báo lỗi
        } catch (NoSuchElementException e) {
            System.err.println("Không tìm thấy phần tử thông báo lỗi: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy phần tử
        }
    }
    private WebElement getLblPasswordErrorMsg() {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
        try {
            // Chờ cho đến khi thông báo lỗi có thể nhìn thấy
            return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblPasswordErrorMsg));
        } catch (TimeoutException e) {
            System.err.println("Thông báo lỗi không tìm thấy: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy thông báo lỗi
        } catch (NoSuchElementException e) {
            System.err.println("Không tìm thấy phần tử thông báo lỗi: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy phần tử
        }
    }
    private WebElement getLblPidErrorMsg() {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
        try {
            // Chờ cho đến khi thông báo lỗi có thể nhìn thấy
            return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblPidErrorMsg));
        } catch (TimeoutException e) {
            System.err.println("Thông báo lỗi không tìm thấy: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy thông báo lỗi
        } catch (NoSuchElementException e) {
            System.err.println("Không tìm thấy phần tử thông báo lỗi: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy phần tử
        }
    }
    public String getErrorMsg() {
        return getLblErrorMsg().getText();
    }
    public String getPasswordErrorMsg() {
        return getLblPasswordErrorMsg().getText();
    }
    public String getPidErrorMsg() {
        return getLblPidErrorMsg().getText();
    }
    private WebElement getLblConfirmationMsg() {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
        try {
            // Chờ cho đến khi thông báo xác nhận có thể nhìn thấy
            return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblConfirmationMsg));
        } catch (TimeoutException e) {
            System.err.println("Thông báo xác nhận không tìm thấy: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy thông báo
        } catch (NoSuchElementException e) {
            System.err.println("Không tìm thấy phần tử thông báo xác nhận: " + e.getMessage());
            return null; // Trả về null nếu không tìm thấy phần tử
        }
    }

    public String getConfirmationMessage() {
        return getLblConfirmationMsg().getText();
    }
    // Phương thức để đăng ký
    public void register(String username, String password,String confirmPassword, String pid) {
        getTxtUsername().sendKeys(username);
        getTxtPassword().sendKeys(password);
        getTxtconfirmPassword().sendKeys(confirmPassword);
        getTxtPID().sendKeys(pid);
        WebElement registerButton = getBtnRegister();
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", registerButton);


        registerButton.click();
    }
}