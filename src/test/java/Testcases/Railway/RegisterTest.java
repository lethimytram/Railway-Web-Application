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

public class RegisterTest {
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
    public void TC07() {
        System.out.println("TC07 - User can create new account");

        // Mở trang chính
        HomePage homePage = new HomePage();
        homePage.open();

        // Chuyển đến trang đăng ký
        RegisterPage registerPage = homePage.gotoRegisterPage();


        registerPage.register(Constant.USERNAME_REGISTER,Constant.PASSWORD_REGISTER,Constant.PASSWORD_REGISTER  ,Constant.PID);
        // Lấy thông báo xác nhận
        String actualMsg = registerPage.getConfirmationMessage();
        String expectedMsg = "Thank you for registering your account.";

        // Kiểm tra thông báo xác nhận
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Confirmation message is not displayed as expected.");
    }
    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with 'Confirm password' is not the same with 'Password'");

        // Mở trang chính
        HomePage homePage = new HomePage(); // Thêm driver nếu cần
        homePage.open(); // Mở trang chính

        // Chuyển đến trang đăng ký
        RegisterPage registerPage = homePage.gotoRegisterPage(); // Chuyển đến trang đăng ký


        registerPage.register(Constant.USERNAME_REGISTER, Constant.PASSWORD_REGISTER,Constant.INVALIDPASSWORD_REGISTER ,Constant.INVALIDPASSWORD_REGISTER);

        // Lấy thông báo lỗi
        String actualMsg = registerPage.getErrorMsg(); // Lấy thông báo lỗi
        String expectedMsg = "There're errors in the form. Please correct the errors and try again."; // Thông báo mong đợi

        // Kiểm tra thông báo lỗi
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected.");
    }

    @Test
    public void TC11() {
        System.out.println("TC11 - User can't create account while password and PID fields are empty");

        // Bước 1: Navigate to QA Railway Website
        HomePage homePage = new HomePage(); // Thêm driver nếu cần
        homePage.open(); // Mở trang chính

        // Bước 2: Click on "Register" tab
        RegisterPage registerPage = homePage.gotoRegisterPage(); // Chuyển đến trang đăng ký

        registerPage.register(Constant.USERNAME_REGISTER,"","","");
        // Lấy thông báo lỗi
        String actualFormErrorMsg = registerPage.getErrorMsg(); // Thông báo lỗi chung trên form
        String expectedFormErrorMsg = "There're errors in the form. Please correct the errors and try again."; // Thông báo mong đợi

        // Kiểm tra thông báo lỗi trên form
        Assert.assertEquals(actualFormErrorMsg.trim(), expectedFormErrorMsg.trim(), "Form error message is not displayed as expected.");

        // Lấy thông báo lỗi cho mật khẩu
        String actualPasswordErrorMsg = registerPage.getPasswordErrorMsg();// Thông báo lỗi cho mật khẩu
        String expectedPasswordErrorMsg = "Invalid password length"; // Thông báo mong đợi cho mật khẩu

        // Kiểm tra thông báo lỗi mật khẩu
        Assert.assertEquals(actualPasswordErrorMsg.trim(), expectedPasswordErrorMsg.trim(), "Password error message is not displayed as expected.");

        // Lấy thông báo lỗi cho PID
        String actualPidErrorMsg = registerPage.getPidErrorMsg(); // Thông báo lỗi cho PID
        String expectedPidErrorMsg = "Invalid ID length"; // Thông báo mong đợi cho PID

        // Kiểm tra thông báo lỗi PID
        Assert.assertEquals(actualPidErrorMsg.trim(), expectedPidErrorMsg.trim(), "PID error message is not displayed as expected.");
    }
}
