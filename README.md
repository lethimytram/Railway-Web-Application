# Railway System Project
## Giới thiệu
Dự án này là một hệ thống quản lý đường sắt được xây dựng bằng Java. Nó bao gồm các trang web để người dùng có thể đăng ký, đăng nhập, đặt vé, xem thông tin vé, và nhiều chức năng khác liên quan đến hệ thống đường sắt.

## Cấu trúc thư mục
Dự án được tổ chức thành hai thư mục chính:

### 1. pageObjects/Railway
Thư mục này chứa các lớp Java đại diện cho các trang web trong hệ thống đường sắt. Các lớp này bao gồm:
- BookTicketPage.java: Trang đặt vé.
- ChangePasswordPage.java: Trang thay đổi mật khẩu.
- ForgotPasswordPage.java: Trang quên mật khẩu.
- GeneralPage.java: Trang chung.
- HomePage.java: Trang chủ.
- LoginPage.java: Trang đăng nhập.
- MyTicketPage.java: Trang vé của tôi.
- RegisterPage.java: Trang đăng ký.
- TicketInfor.java: Trang thông tin vé (có thể là lỗi chính tả của TicketInfo).
- TimetablePage.java: Trang thời gian biểu.
### 2. test/java/Testcases/Railway
Thư mục này chứa các lớp Java cho các trường hợp kiểm thử của hệ thống đường sắt. Các lớp này bao gồm:
- LoginTest.java: Kiểm thử đăng nhập.
- RegisterTest.java: Kiểm thử đăng ký.
## Cài đặt
Để cài đặt và chạy dự án này, bạn cần:
Cài đặt Java Development Kit (JDK).
Cài đặt một IDE như IntelliJ IDEA hoặc Eclipse.
Clone dự án từ repository.
Mở dự án trong IDE và chạy các kiểm thử.
## Sử dụng
Dự án này cung cấp các chức năng sau:
Đăng ký tài khoản mới.
Đăng nhập vào hệ thống.
Đặt vé tàu.
Xem thông tin vé đã đặt.
Thay đổi mật khẩu.
Xem thời gian biểu tàu.
## Ghi chú
- Dự án này sử dụng Selenium WebDriver để tự động hóa các tác vụ trên trình duyệt.
