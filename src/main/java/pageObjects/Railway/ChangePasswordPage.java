package pageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.*;


import java.time.Duration;
public class ChangePasswordPage {

    // Locators
    private By _txtCurrentPassword = By.xpath("//input[@id='currentPassword']"); // Locator cho mật khẩu hiện tại
    private By _txtNewPassword = By.xpath("//input[@id='newPassword']"); // Locator cho mật khẩu mới
    private By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']"); // Locator cho xác nhận mật khẩu mới
    private By _btnChangePasswordButton = By.xpath("//input[@value='Change Password']"); // Locator cho nút thay đổi mật khẩu
    private By _IbIsuccessMsg = By.xpath("//p[@class='message success']"); // Locator cho thông báo thành công

    public WebElement getTxtCurrentPassword() {
        return Constant.WEBDRIVER.findElement(_txtCurrentPassword);
    }
    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    public WebElement getBtnChangePasswordButton() {
        return Constant.WEBDRIVER.findElement(_btnChangePasswordButton);
    }
    public WebElement getIbIsuccessMsg() {
        return Constant.WEBDRIVER.findElement(_IbIsuccessMsg);
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        getTxtCurrentPassword().sendKeys(currentPassword);
        getTxtNewPassword().sendKeys(newPassword);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getBtnChangePasswordButton().click();
    }

    // Lấy thông báo thành công
    public String getSuccessMessage() {
        return getIbIsuccessMsg().getText();
    }
}