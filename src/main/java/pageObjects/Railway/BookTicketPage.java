package pageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class BookTicketPage {
    // Locators
    private final By _ddlDepartDate = By.xpath("//select[@name='Date']");
    private final By _ddlDepartStation = By.xpath("//select[@name='DepartStation']");
    private final By _ddlArriveStation = By.xpath("//select[@name='ArriveStation']");
    private final By _ddlSeatType = By.xpath("//select[@name='SeatType']");
    private final By _ddlTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By _btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
    private final By _lblSuccessMsg = By.xpath("//div[@id='content']/h1"); // Thay đổi ID nếu cần

    public WebElement getDdlDepartDate() {
        return Constant.WEBDRIVER.findElement(_ddlDepartDate);
    }

    public WebElement getDdlDepartStation() {
        return Constant.WEBDRIVER.findElement(_ddlDepartStation);
    }

    public WebElement getDdlArriveStation() {
        return Constant.WEBDRIVER.findElement(_ddlArriveStation);
    }

    public WebElement getDdlSeatType() {
        return Constant.WEBDRIVER.findElement(_ddlSeatType);
    }

    public WebElement getDdlTicketAmount() {
        return Constant.WEBDRIVER.findElement(_ddlTicketAmount);
    }

    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(_btnBookTicket);
    }

    public WebElement getLblSuccessMsg() {
        return Constant.WEBDRIVER.findElement(_lblSuccessMsg);
    }

    // Phương thức chọn ngày khởi hành
    public void selectDepartDate(String departDate) {
        WebElement dateDropdown = getDdlDepartDate();

        // Chờ cho dropdown có thể nhấp
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dateDropdown));

        // Cuộn đến dropdown
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", dateDropdown);

        dateDropdown.click(); // Mở dropdown

        // Chờ cho các tùy chọn có thể nhấp
        wait.until(ExpectedConditions.visibilityOfAllElements(dateDropdown.findElements(By.tagName("option"))));

        for (WebElement option : dateDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(departDate)) {
                // Chờ cho tùy chọn có thể nhấp
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click(); // Chọn ngày
                break;
            }
        }
    }

    // Phương thức chọn điểm khởi hành
    public void selectDepartFrom(String departFrom) {
        WebElement departDropdown = getDdlDepartStation();
        departDropdown.click(); // Mở dropdown
        for (WebElement option : departDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(departFrom)) {
                option.click(); // Chọn điểm khởi hành
                break;
            }
        }
    }

    // Phương thức chọn điểm đến
    public void selectArriveAt(String arriveAt) {
        WebElement arriveDropdown = getDdlArriveStation();
        arriveDropdown.click(); // Mở dropdown
        for (WebElement option : arriveDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(arriveAt)) {
                option.click(); // Chọn điểm đến
                break;
            }
        }
    }

    // Phương thức chọn loại ghế
    public void selectSeatType(String seatType) {
        WebElement seatDropdown = getDdlSeatType();
        seatDropdown.click(); // Mở dropdown
        for (WebElement option : seatDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(seatType)) {
                option.click(); // Chọn loại ghế
                break;
            }
        }
    }

    // Phương thức chọn số lượng vé
    public void selectTicketAmount(int ticketAmount) {
        WebElement amountDropdown = getDdlTicketAmount();
        amountDropdown.click(); // Mở dropdown
        for (WebElement option : amountDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(String.valueOf(ticketAmount))) {
                option.click(); // Chọn số lượng vé
                break;
            }
        }
    }

    // Phương thức đặt vé tổng hợp
    public void bookTicket(String departDate, String departFrom, String arriveAt, String seatType, int ticketAmount) {
        selectDepartDate(departDate);
        selectDepartFrom(departFrom);
        selectArriveAt(arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(ticketAmount);

        getBtnBookTicket().click();
    }

    public String getSuccessMessage() {
        return getLblSuccessMsg().getText();
    }


    public TicketInfor getTicketInfo() {
        WebElement tableRow = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='DivTable']//tr[@class='OddRow']"));

        String departStation = tableRow.findElement(By.xpath("./td[1]")).getText();
        String arriveStation = tableRow.findElement(By.xpath("./td[2]")).getText();
        String seatType = tableRow.findElement(By.xpath("./td[3]")).getText();
        String departDate = tableRow.findElement(By.xpath("./td[4]")).getText();
        int amount = Integer.parseInt(tableRow.findElement(By.xpath("./td[7]")).getText());

        return new TicketInfor(departStation, arriveStation, seatType, departDate, amount);
    }


}
