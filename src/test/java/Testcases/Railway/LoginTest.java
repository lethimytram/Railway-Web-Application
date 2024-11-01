package Testcases.Railway;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Railway.*;
import Common.Constant.Constant;

import Common.common.Utilities;

public class LoginTest {
        @BeforeMethod
        public void beforeMethod() {
            System.out.println("Pre-condition");

//            System.setProperty("webdriver.chrome.driver", javax.swing.text.Utilities.getProjectPath()
//                    + "\\Executables\\chromedriver.exe");
            Constant.WEBDRIVER = new ChromeDriver();
            Constant.WEBDRIVER.manage().window().maximize();

        }

        @AfterMethod
        public void afterMethod() {
            System.out.println("Post-condition");

            Constant.WEBDRIVER.quit();
        }

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid user name and password");
        HomePage homePage = new HomePage();
        homePage.open(); // Mở trang chính
        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập

        // Thực hiện đăng nhập
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        String actualMsg = homePage.getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        // Kiểm tra thông điệp chào mừng
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank \"Username\" textbox");
        HomePage homePage = new HomePage();
        homePage.open(); // Mở trang chính

        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập

        // Thực hiện đăng nhập với username trống
        loginPage.login("", Constant.PASSWORD);

        // Lấy thông báo lỗi
        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        // Kiểm tra thông báo lỗi
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }
    @Test
    public void TC03() {
        System.out.println("TC03 - User can't login with invalid password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, "invalidPassword");

        // Lấy thông báo lỗi
        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        // Kiểm tra thông báo lỗi
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Login page displays when un-logged User clicks on 'Book ticket' tab");

        // Mở trang chính
        HomePage homePage = new HomePage();
        homePage.open();

        // Nhấp vào tab "Book ticket"
        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();

        // Kiểm tra xem có phải là trang đăng nhập hiển thị không
        String actualUrl = homePage.getCurrentUrl(); // Lấy URL hiện tại
        String expectedUrl = "http://railwayb1.somee.com/Account/Login.cshtml?ReturnUrl=/Page/BookTicketPage.cshtml"; // Thay bằng URL thực tế của trang đăng nhập

        // Kiểm tra URL
        Assert.assertEquals(actualUrl, expectedUrl, "User is not redirected to the login page when clicking on 'Book ticket' tab.");
    }

    @Test

    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        // Thực hiện đăng nhập với mật khẩu sai 4 lần
        for (int i = 0; i < 4; i++) {
            loginPage.login(Constant.USERNAME, "invalidPassword");
        }

        // Lấy thông báo lỗi sau khi thử 4 lần
        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        // Kiểm tra thông báo lỗi
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected after multiple failed login attempts.");
    }

    @Test
    public void TC06() {
        System.out.println("TC06 - Additional pages display once user logged in");

        HomePage homePage = new HomePage();
        homePage.open(); // Mở trang chính

        // Bước 2: Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập

        // Bước 3: Login with valid account
        loginPage.login(Constant.USERNAME, Constant.PASSWORD); // Đăng nhập với tài khoản hợp lệ

        // Bước 4: Kiểm tra sự xuất hiện của các tab bổ sung
        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "\"My ticket\" tab is not displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "\"Change password\" tab is not displayed");

        // Bước 5: Click "My ticket" tab và kiểm tra điều hướng
        homePage.gotoMyTicketPage();
        String actualMsg,expectedMsg;
        actualMsg = homePage.getCurrentUrl();
        expectedMsg = "http://railwayb1.somee.com/Page/ManageTicket.cshtml";
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "User is not directed to My ticket page");

        homePage.goBack(); // Quay lại trang chính

        homePage.gotoChangePasswordPage();
        actualMsg = homePage.getCurrentUrl();
        expectedMsg = "http://railwayb1.somee.com/Account/ChangePassword.cshtml";
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "User is not directed to Change password page");
    }


//    @Test
//    public void TC08() {
//        System.out.println("TC08 - User can't login with an account hasn't been activated");
//
//        // Bước 1: Navigate to QA Railway Website
//        HomePage homePage = new HomePage();
//        homePage.open(); // Mở trang chính
//
//        // Bước 2: Click on "Login" tab
//        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập
//        loginPage.login(Constant.USERNAME, Constant.PASSWORD); // Thực hiện đăng nhập
//
//        // Bước 4: Lấy thông báo lỗi
//        String actualMsg = loginPage.getErrorMessage();// Lấy thông báo lỗi
//        String expectedMsg = "Invalid username or password. Please try again."; // Thông báo mong đợi
//
//        // Kiểm tra thông báo lỗi
//        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
//    }

    @Test
    public void TC09() {
        System.out.println("TC09 - User can change password");

        HomePage homePage = new HomePage();
        homePage.open(); // Mở trang chính


        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập
        loginPage.login(Constant.USERNAME, Constant.PASSWORD); // Đăng nhập

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage(); // Nhấp vào tab "Change Password"

        changePasswordPage.changePassword(Constant.PASSWORD,Constant.NEW_PASSWORD,Constant.NEW_PASSWORD);

        // Kiểm tra thông báo thành công
        String actualMsg = changePasswordPage.getSuccessMessage(); // Lấy thông báo thành công
        String expectedMsg = "Your password has been updated!"; // Thông báo mong đợi

        // Kiểm tra thông báo
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Success message is not displayed as expected");
    }
    @Test
    public void TC12() {
        System.out.println("TC12 - Errors display when password reset token is blank");

        // Mở trang đăng nhập
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập
        //loginPage.login(Constant.USERNAME, Constant.PASSWORD); // Đăng nhập

        // Nhấp vào liên kết "Forgot Password"
        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();


        forgotPasswordPage.enterEmailAndSendInstructions("fakemail@gmail.com");


    }
    @Test
    public void TC13() {
        System.out.println("TC12 - Errors display when password reset token is blank");

        // Mở trang đăng nhập
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập
        //loginPage.login(Constant.USERNAME, Constant.PASSWORD); // Đăng nhập

        // Nhấp vào liên kết "Forgot Password"
        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();


        forgotPasswordPage.enterEmailAndSendInstructions("fakemail@gmail.com");


    }

    @Test
    public void TC14() {
            System.out.println("TC14 - User can book 1 ticket at a time");

            // Bước 1: Navigate to QA Railway Website
            HomePage homePage = new HomePage();
            homePage.open(); // Mở trang chính

            // Bước 2: Login with a valid account
            LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập
            loginPage.login(Constant.USERNAME, Constant.PASSWORD); // Đăng nhập bằng tài khoản hợp lệ

            // Bước 3: Click on "Book ticket" tab
            BookTicketPage ticketBookingPage = homePage.gotoBookTicketPage(); // Chuyển đến trang đặt vé

            ticketBookingPage.bookTicket("11/1/2024","Huế","Nha Trang","Soft bed with air conditioner",1);

            // Kiểm tra thông báo thành công
            String actualMsg = ticketBookingPage.getSuccessMessage(); // Lấy thông báo thành công
            String expectedMsg = "Ticket booked successfully!"; // Thông báo mong đợi
            Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Success message is not displayed as expected.");

            // Kiểm tra thông tin vé
            TicketInfor ticketInfor = ticketBookingPage.getTicketInfo(); // Lấy thông tin vé
            Assert.assertEquals(ticketInfor.getDepartDate(), "11/1/2024", "Depart Date is not correct.");
            Assert.assertEquals(ticketInfor.getDepartStation(), "Huế", "Depart Station is not correct.");
            Assert.assertEquals(ticketInfor.getArriveStation(), "Nha Trang", "Arrive Station is not correct.");
            Assert.assertEquals(ticketInfor.getSeatType(), "Soft bed with air conditioner", "Seat Type is not correct.");
            Assert.assertEquals(ticketInfor.getAmount(), 1, "Ticket Amount is not correct.");

    }


    @Test
    public void TC15() {
        System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

        // Bước 1: Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open(); // Mở trang chính

        // Bước 2: Login with a valid account
        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập
        loginPage.login(Constant.USERNAME, Constant.PASSWORD); // Đăng nhập bằng tài khoản hợp lệ

        // Bước 3: Click on "Timetable" tab
        TimetablePage timetablePage = homePage.gotoTimetablePage(); // Chuyển đến trang thời gian biểu

        // Bước 4: Click on "book ticket" link of the route from "Huế" to "Sài Gòn"
        BookTicketPage ticketBookingPage = timetablePage.clickBookTicketLink("Sài Gòn", "Huế"); // Nhấp vào liên kết đặt vé

        // Kiểm tra xem trang "Book ticket" đã được tải đúng
        String actualDepartFrom = ticketBookingPage.getDdlDepartStation().getText(); // Lấy thông tin điểm khởi hành
        String actualArriveAt = ticketBookingPage.getDdlArriveStation().getText(); // Lấy thông tin điểm đến

        Assert.assertEquals(actualDepartFrom, "Sài Gòn", "Depart from value is not correct.");
        Assert.assertEquals(actualArriveAt, "Huế", "Arrive at value is not correct.");
    }

    @Test
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");

        // Bước 1: Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open(); // Mở trang chính

        // Bước 2: Login with a valid account
        LoginPage loginPage = homePage.gotoLoginPage(); // Chuyển đến trang đăng nhập
        loginPage.login(Constant.USERNAME, Constant.PASSWORD); // Đăng nhập bằng tài khoản hợp lệ

        // Bước 3: Book a ticket first to ensure there is a ticket to cancel
        BookTicketPage ticketBookingPage = homePage.gotoBookTicketPage(); // Chuyển đến trang đặt vé
        ticketBookingPage.bookTicket("11/17/2024", "Huế", "Nha Trang", "Soft bed with air conditioner", 1);

        // Bước 4: Click on "My ticket" tab
        MyTicketPage myTicketPage = homePage.gotoMyTicketPage(); // Chuyển đến trang "My ticket"

        // Bước 5: Click on "Cancel" button for the ticket to cancel
        myTicketPage.cancelTicket("11/17/2024", "Huế", "Nha Trang", "Soft bed with air conditioner", 1); // Hủy vé
        Assert.assertFalse(myTicketPage.isTicketVisible("11/17/2024", "Huế", "Nha Trang", "Soft bed with air conditioner", 1), "The canceled ticket is still visible.");

    }
}
