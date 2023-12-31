<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <title>Thêm mã giảm giá</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- App favicon -->
    <link rel="shortcut icon" href="../../assets/images/favicon.ico">

    <!-- third party css -->
    <link href="../../assets/css/vendor/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css"/>
    <!-- third party css end -->

    <!-- App css -->
    <link href="../../assets/css/icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../assets/css/app.min.css" rel="stylesheet" type="text/css" id="light-style"/>
    <link href="../../assets/css/app-dark.min.css" rel="stylesheet" type="text/css" id="dark-style"/>

</head>


<body class="loading"
      data-layout-config='{"leftSideBarTheme":"dark","layoutBoxed":false, "leftSidebarCondensed":false, "leftSidebarScrollable":false }'>

<!-- data-layout-config='{"leftSideBarTheme":"dark","layoutBoxed":false, "leftSidebarCondensed":false, "leftSidebarScrollable":false,"darkMode":false, "showRightSidebarOnStart": true}' -->
<!-- Begin page -->
<div class="wrapper">
    <!-- ========== Left Sidebar Start ========== -->
    <%@include file="../left-menu.jsp" %>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">
            <!-- Topbar Start -->
            <jsp:include page="../topbar.jsp"/>
            <!-- end Topbar -->

            <!-- Start Content-->
            <div class="container-fluid">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="../admin/coupon">Giảm giá</a></li>
                                    <li class="breadcrumb-item active"> Thêm mã giảm giá</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Giảm giá</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="header-title">Giảm giá mới</h4>
                                <form action="${pageContext.request.contextPath}/admin/coupon/create" method="post">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group mb-3">
                                                <label for="code">Mã giảm giá</label>
                                                <input type="text" id="code" name="code" class="form-control" required>
                                            </div>

                                            <div class="form-group mb-3">
                                                <label for="quantity">Số lượng mã</label>
                                                <input type="number" id="quantity" name="quantity" class="form-control"
                                                       required>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="percent">% giảm</label>
                                                <input type="number" id="percent" name="percent" class="form-control"
                                                       required>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="max_price_sale">Số tiền giảm tối đa</label>
                                                <input type="number" id="max_price_sale" name="max_price_sale"
                                                       class="form-control" value="0" required>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="min_price_order">Giá trị hóa đơn tối thiểu</label>
                                                <input type="number" id="min_price_order" name="min_price_order"
                                                       class="form-control" value="0" required>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Thời gian có hiệu lực</label>
                                                <input type="text" class="form-control date" id="singledaterange"
                                                       name="date"
                                                       data-toggle="date-picker" data-cancel-class="btn-warning"
                                                       required>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="content">Nội dung</label>
                                                <textarea type="text" id="content" name="content"
                                                          class="form-control" cols="30" rows="5"></textarea>
                                                <h4 class="text-info">Cho tài khoản</h4>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="content">Số đơn hàng đã đặt tối thiểu</label>
                                                <input type="number" id="min_num_contentorder" name="min_num_order"
                                                       class="form-control" value="0" required>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="date_regis_acc">Ngày lập tài khoản tối thiểu</label>
                                                <input type="date" id="date_regis_acc" name="date_regis_acc"
                                                       class="form-control" value="2022-01-01" required>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Thêm mã giảm giá</button>
                                </form>
                            </div> <!-- end card-body-->
                        </div> <!-- end card-->
                    </div> <!-- end col -->
                </div>
                <!-- end row -->

            </div> <!-- container -->

        </div> <!-- content -->


    </div>
    <!-- content -->

    <!-- Footer Start -->
    <%@include file="../footer.jsp" %>
    <!-- end Footer -->

</div>

<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->


</div>
<!-- END wrapper -->

<!-- Right Sidebar -->
<%@include file="../right-sidebar.jsp"%>
<!-- /Right-bar -->

<!-- bundle -->
<script src="../../assets/js/vendor.min.js"></script>
<script src="../../assets/js/app.min.js"></script>

<!-- third party js -->
<script src="../../assets/js/vendor/apexcharts.min.js"></script>
<script src="../../assets/js/vendor/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../../assets/js/vendor/jquery-jvectormap-world-mill-en.js"></script>
<!-- third party js ends -->

<!-- demo app -->
<script src="../../assets/js/pages/revenue.jsp"></script>
<!-- end demo js-->
</body>

</html>