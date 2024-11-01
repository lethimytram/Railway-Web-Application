package pageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MyTicketPage {
    public void cancelTicket(String departDate, String departFrom, String arriveAt, String seatType, int ticketAmount) {
            // Tìm tất cả các hàng trong bảng vé
            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='MyTable']/tbody/tr")));
            List<WebElement> rows = Constant.WEBDRIVER.findElements(By.xpath("//table[@class='MyTable']/tbody/tr"));


            for (WebElement row : rows) {

                List<WebElement> cells = row.findElements(By.xpath(".//td"));

                if (cells.isEmpty()) {
                    System.out.println("No cells found in this row.");
                    continue; // Bỏ qua hàng này
                }
                String actualDepartFrom = cells.get(1).getText(); // Depart From
                String actualArriveAt = cells.get(2).getText(); // Arrive At
                String actualSeatType = cells.get(3).getText(); // Seat Type
                String actualDepartDate = cells.get(4).getText(); // Depart Date
                String actualAmount = cells.get(8).getText(); // Ticket Amount
                // Kiểm tra nếu thông tin vé khớp với yêu cầu
                if (actualDepartDate.equals(departDate) &&
                        actualDepartFrom.equals(departFrom) &&
                        actualArriveAt.equals(arriveAt) &&
                        actualSeatType.equals(seatType) &&
                        Integer.parseInt(actualAmount) == ticketAmount) {

                    // Nhấp vào nút "Cancel" trong hàng này
                    WebElement cancelButton = row.findElement(By.xpath(".//input[@value='Cancel']"));
                    ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", cancelButton);
                    cancelButton.click();

                    // Xử lý cảnh báo
                    wait.until(ExpectedConditions.alertIsPresent()); // Đợi cảnh báo xuất hiện
                    Alert alert = Constant.WEBDRIVER.switchTo().alert(); // Chuyển sang cảnh báo
                    alert.accept(); // Chấp nhận cảnh báo (hoặc alert.dismiss() để từ chối)
                    break; // Thoát khỏi vòng lặp sau khi hủy vé
                }
            }
    }

    public boolean isTicketVisible(String departDate, String departFrom, String arriveAt, String seatType, int ticketAmount) {
        // Tìm tất cả các hàng trong bảng vé
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='MyTable']/tbody/tr")));
        List<WebElement> rows = Constant.WEBDRIVER.findElements(By.xpath("//table[@class='MyTable']/tbody/tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));
            // Lấy thông tin từ các ô trong hàng
            if (cells.isEmpty()) {
                System.out.println("No cells found in this row.");
                continue; // Bỏ qua hàng này
            }
            String actualDepartFrom = cells.get(1).getText(); // Depart From
            String actualArriveAt = cells.get(2).getText(); // Arrive At
            String actualSeatType = cells.get(3).getText(); // Seat Type
            String actualDepartDate = cells.get(4).getText(); // Depart Date
            String actualAmount = cells.get(8).getText(); // Ticket Amount

            // Kiểm tra thông tin vé có khớp hay không
            if (actualDepartDate.equals(departDate) &&
                    actualDepartFrom.equals(departFrom) &&
                    actualArriveAt.equals(arriveAt) &&
                    actualSeatType.equals(seatType) &&
                    Integer.parseInt(actualAmount) == ticketAmount) {
                return true; // Vé còn hiển thị
            }
        }
        return false; // Vé không còn hiển thị
    }

}
