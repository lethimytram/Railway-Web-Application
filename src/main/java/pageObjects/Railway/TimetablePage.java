package pageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TimetablePage {
    public BookTicketPage clickBookTicketLink(String departFrom, String arriveAt) {
        // Tìm tất cả các hàng trong bảng
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='MyTable WideTable']")));
        List<WebElement> rows = Constant.WEBDRIVER.findElements(By.xpath("//table[@class='MyTable WideTable']/tbody/tr"));
        for (WebElement row : rows) {
            // Lấy thông tin từ từng hàng
            String actualDepartFrom = row.findElement(By.xpath(".//td[2]")).getText();
            String actualArriveAt = row.findElement(By.xpath(".//td[3]")).getText();
            // Kiểm tra xem hàng có phải là tuyến cần tìm không
            if (actualDepartFrom.equals(departFrom) && actualArriveAt.equals(arriveAt)) {
                // Nhấp vào liên kết "book ticket"
                WebElement bookTicketLink = row.findElement(By.xpath(".//td/a[contains(text(), 'book ticket')]"));
                ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", bookTicketLink);
                bookTicketLink.click();
                return new BookTicketPage(); // Trả về trang đặt vé
            }
        }

        throw new NoSuchElementException("No booking link found for the route from " + departFrom + " to " + arriveAt);
    }
}
