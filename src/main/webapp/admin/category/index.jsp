<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <title>Phân loại</title>
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
                                    <li class="breadcrumb-item active">Phân loại</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Phân loại</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row mb-2">
                                    <div class="col-sm-4">

                                        <c:choose>
                                            <c:when test="${sessionScope.admin.level ge 1}">
                                                <a href="/admin/category/create" class="btn btn-danger mb-2"><i
                                                        class="mdi mdi-plus-circle mr-2"></i>Thêm loại</a>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="col-sm-8">
                                        <!-- <div class="text-sm-right">
                                            <button type="button" class="btn btn-success mb-2 mr-1"><i
                                                    class="mdi mdi-settings"></i></button>
                                            <button type="button" class="btn btn-light mb-2 mr-1">Nhập file</button>
                                            <button type="button" class="btn btn-light mb-2">Xuất file</button>
                                        </div> -->
                                    </div><!-- end col-->
                                </div>

                                <div class="table-responsive">
                                    <table class="table table-centered w-100 dt-responsive nowrap"
                                           id="products-datatable">
                                        <thead class="thead-light">
                                        <tr>
                                            <th class="all">Tên loại</th>
                                            <th class="text-center">Tình trạng</th>
                                            <c:choose>
                                                <c:when test="${sessionScope.admin.level ge 1}">
                                                    <th class="text-center">Hành động</th>
                                                </c:when>
                                            </c:choose>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${requestScope['categoryList']}"
                                                   varStatus="status">

                                            <tr>
                                                <td>
                                                    <p class="m-0 d-inline-block align-middle font-16">
                                                        <a href="" style="pointer-events: none; cursor: default;"
                                                           class="text-body"><c:out value="${item.name}"/> </a>
                                                        <br/>
                                                    </p>
                                                </td>
                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${item.status eq 0}">
                                                            <span class="badge badge-success">Đang bán</span>
                                                        </c:when>
                                                        <c:when test="${item.status eq 1}">
                                                            <span class="badge badge-warning">Hết nguyên liệu</span>
                                                        </c:when>
                                                        <c:when test="${item.status eq 2}">
                                                            <span class="badge badge-danger">Ngưng bán</span>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <c:choose>
                                                    <c:when test="${sessionScope.admin.level ge 1}">
                                                        <form action="/admin/category/delete" method="post"
                                                              id="delete-form-<c:out value="${item.id}"/>">
                                                            <td class="table-action text-center">
                                                                <a href="/admin/category/update?id=<c:out value="${item.id}"/>"
                                                                   class="action-icon"> <i
                                                                        class="mdi mdi-square-edit-outline"></i></a>
                                                                <input type="text" name="id" id="id"
                                                                       value="<c:out value="${item.id}"/>" class="d-none">
                                                                <button type="submit" class="d-none"></button>
                                                                <a href="javascript:{0}"
                                                                   class="action-icon"> <i class="mdi mdi-delete delete-btn"
                                                                                           data-name="<c:out value="${item.name}"/>"
                                                                                           data-id="<c:out value="${item.id}"/>"></i>
                                                                </a>

                                                            </td>
                                                        </form>
                                                    </c:when>
                                                </c:choose>


                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <c:set var="endPage" value='${requestScope["endPage"]}'/>
                                    <c:choose>
                                        <c:when test="${empty param['page']}">
                                            <c:set var="currentPage" value="1"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="currentPage" value="${param['page']}"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <nav>
                                        <ul class="pagination pagination-rounded mb-0">
                                            <li class="page-item  <c:if test="${currentPage eq 1 }"> d-none </c:if>">
                                                <a class="page-link" href="/admin/category?page=<c:out value='${i-1}'/>"
                                                   aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <c:forEach var="i" begin="1" end="${endPage}">
                                                <li class="page-item  <c:if test="${currentPage eq i }"> active </c:if>">
                                                    <a class="page-link"
                                                       href="/admin/category?page=<c:out value='${i}'/>">
                                                        <c:out value="${i}"/>
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item <c:if test="${currentPage eq endPage }"> d-none </c:if>">
                                                <a class="page-link" href="/admin/category?page=<c:out value='${i+1}'/>"
                                                   aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
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
<jsp:include page="../right-sidebar.jsp"/>
<!-- /Right-bar -->

<!-- bundle -->
<script src="../../assets/js/vendor.min.js"></script>
<script src="../../assets/js/app.min.js"></script>

<!-- third party js -->
<%--<script src="../assets/js/vendor/apexcharts.min.js"></script>--%>
<script src="../../assets/js/vendor/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../../assets/js/vendor/jquery-jvectormap-world-mill-en.js"></script>
<!-- third party js ends -->

<script>
    $(document).ready(function () {
        $(".delete-btn").click(function () {

            if (window.confirm("Bạn muốn xóa " + $(this).attr("data-name") + " ?")) {
                url = "#delete-form-" + $(this).attr("data-id");
                // console.log(url)
                $(url).submit();
                // return false;
            } else {
                return false;
            }
        })
    })
</script>


</body>

</html>