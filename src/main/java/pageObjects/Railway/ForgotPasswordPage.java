package pageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
    private final By _emailFiend = By.id("email");
    private final By _btnSendInstructions = By.xpath("//input[@value='Send Instructions']");

    public WebElement getEmail() {
        return Constant.WEBDRIVER.findElement(_emailFiend);
    }
    public WebElement getBtnSendInstructions() {
        return Constant.WEBDRIVER.findElement(_btnSendInstructions);
    }

    public void enterEmailAndSendInstructions(String email) {
        getEmail().sendKeys(email);
        WebElement sendInstructionsButton = getBtnSendInstructions();
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", sendInstructionsButton);
        sendInstructionsButton.click();

    }
}
