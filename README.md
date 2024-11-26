# APP QUẢN LÍ THƯ VIỆN  

## Final Project for OOP (team 3HTeam)

## Team Members
- Vũ Minh Hiến - 23020064
- Nguyễn Minh Hải - 23020055
- Nguyễn Như Hiếu - 23020067

# Giới thiệu
- Ứng dụng quản lý thư viện là một giải pháp phần mềm hiện đại, giúp tối ưu hóa việc quản lý và vận hành các hoạt động trong thư viện.

- Được thiết kế với giao diện trực quan, thân thiện và tích hợp các tính năng mạnh mẽ, ứng dụng đáp ứng nhu cầu của cả người quản trị thư viện và người dùng mượn sách.

- Được viết bằng ngôn ngữ Java và hỗ trợ bởi thư viện JavaFX để tạo ra giao diện đồ hoạ thú vị, đẹp mắt.

## Table of contents
- [Chức năng](#Chức-năng)
  - [Đăng nhập và đăng kí tài khoản](#Đăng-nhập-và-đăng-kí-tài-khoản)
    - [Chức năng đăng nhập](#Chức-năng-đăng-nhập)
    - [Chức năng đăng kí](#Chức-năng-đăng-kí)
    - [Chức năng lấy lại tài khoản nếu quên mật khẩu](#Chức-năng-lấy-lại-tài-khoản-nếu-quên-mật-khẩu)

# Chức năng
Ứng dụng được phát triển các tính năng cho cả người quản trị thư viện và người dùng mượn sách.
## Đăng nhập và đăng kí tài khoản
### Chức năng đăng nhập
- Người dùng nhập tên đăng nhâp và mật khẩu để thực hiện đăng nhập vào ứng dụng.

- Nếu tên đăng nhập hoặc mật khẩu không đúng sẽ hiện lên thông bào và yêu cầu người dùng nhập lại.

- Khi người dùng xác thực đăng nhập thành công, tài khoản sẽ được xác thực quyền, nếu là admin thì sẽ được chuyển hướng về giao diện admin, nếu là user sẽ được chuyển hướng về giao diện user.
![alt text](preview/image.png)

### Chức năng đăng kí
- Nếu người dùng chưa có tài khoản, có thể vàn trang đăng kí để đăng kí tài khoản sử dụng ứng dụng.

- Chức năng đăng kí chỉ dành cho người dùng mượn sách, admin không thể đăng kí tài khoản mà sẽ được người phát triển ứng dụng tào tài khoản sẵn cho.

- Người dùng nhập các thông tin để đăng kí tài khoản và sẽ được hiện thông báo nếu id vè username đẽ tồn tại, đã được đăng kí tài khoản rồi.
![alt text](preview/image-1.png)

### Chức năng lấy lại tài khoản nếu quên mật khẩu

- Khi người dùng quên mất khẩu và không thể vào ứng dụng thì có thể chọn chức năng quên mật khẩu ở trang login.

- Người dùng nhập email đăng kí tài khoản và lấy mà OTP xác thực được gửi về email.
![alt text](preview/image-2.png)

- Khi thấy thông báo gửi OTP thành công thì một mã OTP xác thực đã được gửi mề email.
![alt text](preview/image-3.png)

- Thực hiện nhập OTP để nhận được để xác thực, nếu xác thực mã otp thành công sẽ được chuyển hướng sang trang tạo mật khẩu mới.
![alt text](preview/image-4.png)

- Người dùng thực hiện lấy lại mật khẩu và sẽ được chuyển hướng về trang admin để đăng nhập.

## Phía người quản trị thư viện