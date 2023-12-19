<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8"/>
    <meta name="description" content="Male_Fashion Template"/>
    <meta name="keywords" content="Male_Fashion, unica, creative, html"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Đăng nhập - Đăng kí</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet"/>

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
          integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css"/>
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css"/>
    <link rel="stylesheet" href="css/nice-select.css" type="text/css"/>
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <link rel="stylesheet" href="css/account.css" type="text/css"/>
    <link rel="stylesheet" href="css/header-footer.css" type="text/css"/>
    <link rel="stylesheet" href="css/lostSign.css">
    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <div id="fb-root"></div>
    <script async defer crossorigin="anonymous"
            src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v16.0&appId=1165112207482270&autoLogAppEvents=1"
            nonce="kCjYwcHi"></script>

</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__option">
        <div class="offcanvas__links">
            <a href="login-register.jsp">Đăng nhập</a>
        </div>
    </div>
    <div class="offcanvas__nav__option">
        <a href="#" class="search-switch"><img src="img/icon/search.png" alt=""/></a>
        <a href="#"><img src="img/icon/heart.png" alt=""/></a>
        <a href="#"><img src="img/icon/cart.png" alt=""/> <span>0</span></a>
        <div class="price">0 vnđ</div>
    </div>
    <div id="mobile-menu-wrap"></div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<%@include file="header.jsp" %>
<!-- Header Section End -->

<!-- Shopping Cart Section Begin -->
<section class="login-register">
    <div class="main-content-wrap section-ptb lagin-and-register-page">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 col-md-12 m-auto">
                    <div id="myModal" class="modal-login">
                        <div class="modal-content">
<%--                            <span class="close" id="closeModal">&times;</span>--%>
                            <form action="${pageContext.request.contextPath}/lostSign" method="post"
                                  id="lost-sign">
                                <span class="text-danger" id="lostsign-email-error" style=" display: flex;"></span>
                            <label for="emailCheck">Email:</label>
                            <input type="email" id="emailCheck" name="emailCheck" style="width: 100%;" required>
                                <p class="text-success" style=" display: flex;">Private Key sẽ được gửi vào email này!</p>
                            <button id="confirmBtn" class="submit-button-lost-sign">Xác nhận</button>
                            </form>
                        </div>
                    </div>
                    <div class="login-register-wrapper">
                        <!-- login-register-tab-list start -->
                        <div class="login-register-tab-list nav">
                            <a class="active" data-bs-toggle="tab" href="#lg1">
                                <h4>Đăng nhập</h4>
                            </a>
                            <a data-bs-toggle="tab" href="#lg2">
                                <h4>Đăng kí</h4>
                            </a>
                        </div>
                        <!-- login-register-tab-list end -->
                        <div class="tab-content">
                            <div id="lg1" class="tab-pane active">
                                <div class="login-form-container">
                                    <div class="login-register-form">
                                        <form action="${pageContext.request.contextPath}/login" method="post"
                                              id="login">
                                            <div class="login-input-box">
                                                <span class="text-danger"
                                                      id="login-username-error"></span>
                                                <input type="text" id="login_username" name="username"
                                                       placeholder="Tài khoản"
                                                       value="" required/>
                                                <span class="text-danger" id="login-password-error"></span>
                                                <input type="password" id="login_password" name="password"
                                                       placeholder="Mật khẩu"
                                                       value="" required/>
                                            </div>
                                            <div class="button-box">
                                                <div class="login-toggle-btn">
                                                    <input id="checkSave" type="checkbox" name="save"
                                                           value="checked"/>
                                                    <label for="checkSave">Lưu thông tin</label>
                                                    <a href="./forgotPass">Quên mật khẩu?</a>
                                                </div>

                                                <div class="button-box"
                                                     style=" display: flex; justify-content: center;">
                                                    <button class="login-btn btn" type="submit"
                                                    >
                                                        <span>Đăng nhập</span>
                                                    </button>
                                                </div>
                                            </div>
                                            <hr class="mb-4 mt-1">
                                            <div class="d-flex justify-content-center text-center pt-1">

                                                <div class="col">
                                                    <div id="ButtonGoogleLogin"></div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div id="lg2" class="tab-pane">
                                <div class="login-form-container">
                                    <div class="login-register-form">
                                        <form action="${pageContext.request.contextPath}/register" method="post" id="register">
                                            <div class="login-input-box">
                                                <span class="text-danger" id="register-name-error"></span>
                                                <small id="nameHelpInline" class="text-muted mb-3">
                                                    Tên người dùng. Vd: Nguyễn Văn A.
                                                </small>
                                                <input type="text" id="name" name="name" placeholder="Tên"
                                                       aria-describedby="nameHelpInline" required/>
                                                <span class="text-danger" id="register-username-error"></span>
                                                <small id="usernameHelpInline" class="text-muted mb-3">
                                                    Tên đang nhập. Vd: adeptrai.
                                                </small>
                                                <input type="text" id="username" name="username"
                                                       placeholder="Tên đăng nhập" aria-describedby="usernameHelpInline"
                                                       required/>

                                                <span class="text-danger"
                                                      id="register-email-error"></span>
                                                <small id="emailHelpInline" class="text-muted mb-3">
                                                    Email. Vd: nguyenvana@gmail.com.
                                                </small>

                                                <input name="email" id="email" placeholder="Email" type="email"
                                                       aria-describedby="emailHelpInline" required/>
                                                <span class="text-danger"
                                                      id="register-phone-error"></span>
                                                <small id="phoneHelpInline" class="text-muted mb-3">
                                                    Số điện thoại. Vd: 0987654321.
                                                </small>
                                                <input name="phone" id="phone" placeholder="Số điện thoại" type="tel"
                                                       pattern="[0]{1}[0-9]{9}" aria-describedby="phoneHelpInline"
                                                       required/>
                                                <span class="text-danger"
                                                      id="register-password-error"></span>
                                                <small id="passwordHelpInline" class="text-muted mb-3">
                                                    Mật khẩu ít nhất 8 ký tự.
                                                </small>
                                                <input type="password" id="password" name="password"
                                                       placeholder="Mật nhẩu" aria-describedby="passwordHelpInline"
                                                       minlength="8"
                                                       required/>
                                                <span class="text-danger"
                                                      id="register-repassword-error"></span>
                                                <small id="passcomfirmHelpInline" class="text-muted mb-3">
                                                    Mật khẩu phải trùng với mật khẩu ở trên
                                                </small>
                                                <input type="password" id="repassword" placeholder="Nhập lại mật nhẩu "
                                                       minlength="8" aria-describedby="passcomfirmHelpInline" required/>
                                            </div>
                                            <div class="button-box">
                                                <div class="login-toggle-btn">
                                                    <input type="checkbox" id="agreeTerms" name="agreeTerms" required
                                                           value="checked"
                                                           onchange="changeTerm()"
                                                    />
                                                    <label for="agreeTerms">
                                                        Tôi đồng ý với <a style="color: #00adef; margin-left: 5px" href="#"> Điều khoản sử dụng</a>
                                                    </label>
                                                </div>
                                                <button class="register-btn btn" type="submit" disabled
                                                        onclick="return check_register()">
                                                    <span>Đăng kí</span>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Footer Section Begin -->
<%@include file="footer.jsp" %>
<!-- Footer Section End -->

<!-- Js Plugins -->
<%--<script async defer crossorigin="anonymous"--%>
<%--        src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v15.0&appId=2143582525828112&autoLogAppEvents=1"--%>
<%--        nonce="jhCmVurm"></script>--%>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery.nicescroll.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.countdown.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>
<script src="js/account/bootstrap.min.js"></script>
<script src="https://unpkg.com/jwt-decode/build/jwt-decode.js"></script>
<script>
    function handleCredentialResponse(response) {
        const resp = jwt_decode(response.credential);

        window.location.href = 'Login?action=Google&name=' + resp.name + '&email=' + resp.email + '&id=' + resp.sub;
    }

    window.onload = function () {
        google.accounts.id.initialize({
            client_id: "862913517251-8g2qrfue12q6gojci1tulp9qtfi4hmqv.apps.googleusercontent.com",
            callback: handleCredentialResponse
        });
        google.accounts.id.renderButton(
            document.getElementById("ButtonGoogleLogin"),
            {theme: "outline", size: "large"}  // customization attributes
        );
        google.accounts.id.prompt(); // also display the One Tap dialog
    }
</script>

<script src="js/validate.js"></script>
<script type="text/javascript">

    $('#closeModal').click(function() {
        $('#myModal').hide();
    });

    $("#login").submit(function (e) {
        e.preventDefault();
        $.ajax({
            type: $(this).attr('method'),
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function (data) {
                if (1 == data) {
                    $("#login-username-error").text("Tài khoản hoặc mật khẩu không đúng");
                } else if (2 == data) {
                    window.location.href = "./";

                } else if (3 == data) {
                    $('#myModal').show();
                    console.log(" hay vào đây")


                }
            },
            error: function (data) {
                console.log('An error occurred.');
                console.log(data);
            },
        });
    });
    $("#register").submit(function (e) {
        e.preventDefault();
        $.ajax({
            type: $(this).attr('method'),
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function (data) {
                var responseData = data.split(';');
                if (1 == data) {
                    $("#register-username-error").text("Tên đăng nhập đã được sử dụng");
                } else if (2 == responseData[0]) {
                    var userName = responseData[1];
                    var privateKey = responseData[2];
                    if(privateKey) {
                        var blob = new Blob([privateKey], {type: 'text/plan'})
                        var blobUrl = URL.createObjectURL(blob)

                        var link = document.createElement('a')
                        link.href = blobUrl
                        link.download = userName + '_private_key.key'
                        document.body.appendChild(link)
                        link.click();

                        URL.revokeObjectURL(blobUrl)
                        document.body.removeChild(link)
                        window.location.href = "./";
                    } else {
                        console.error("Private key not found in session.");
                    }
                }

            },
            error: function (data) {
                console.log('An error occurred.');
                console.log(data);
            },
        });
    });

    $("#lost-sign").submit(function (e) {
        e.preventDefault();
        console.log($(this).serialize());
        $.ajax({
            type: $(this).attr('method'),
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function (data) {
                if(data == 1) {
                $('#lostsign-email-error').text("Địa chỉ email không đúng!");
                }
                else if (2 == data) {
                        window.location.href = "./";
                }
            },
            error: function (data) {
                console.log('An error occurred.');
                console.log(data);
            },
        });
    });


    const countdown =(time) => {
        setTimeout(() => {
            $("#login-username-error").text("");
            $(".login-btn").attr("disabled", false);

        }, time * 60 * 1000)
        return 0;
    }

    const changeTerm = () => {
        var checkTerm = document.getElementById("agreeTerms");
        var buttonRegister = document.getElementsByClassName("register-btn")[0];

        if(checkTerm.checked) {
            buttonRegister.disabled = false;
        } else {
            buttonRegister.disabled = true;
        }
    }





</script>

</body>
</html>