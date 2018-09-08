<%@ page import="com.estate.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tag.jsp"%>
<c:url var="formUrl" value="/admin/customer/list"/>
<c:url var="deleteAPI" value="/api/admin/user"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chi tiết khách hàng</title>
</head>
<body>
<div class="main-content">
    <center>
        <center><h3>CHI TIẾT BÁO GIÁ</h3></center>
        <div class="row">
            <div class="col-xs-11">
                <div class="tabbable">
                    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
                        <li class="active">
                            <a data-toggle="tab" href="#home4">Thông tin gửi mail</a>
                        </li>

                        <li>
                            <a data-toggle="tab" href="#profile4">Quá trình CSKH</a>
                        </li>

                        <li>
                            <a data-toggle="tab" href="#dropdown14">Tình trạng</a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="home4" class="tab-pane in active">
                            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                <tbody>
                                <tr>
                                    <th>Tên khách hàng</th>
                                    <th>${model.name}</th>
                                </tr>
                                <tr>
                                    <th>Di động</th>
                                    <th>${model.phoneNumber}</th>
                                </tr>
                                <tr>
                                    <th>Email</th>
                                    <th>${model.email}</th>
                                </tr>
                                <tr>
                                    <th>Ngày nhập</th>
                                    <th>${model.modifiedDate}</th>
                                </tr>
                                <tr>
                                    <th>Nhu cầu</th>
                                    <th>${model.need}</th>
                                </tr>
                                <tr>
                                    <th>Ngày gửi mail</th>
                                    <th>${model.dateMailing}</th>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <div id="profile4" class="tab-pane">
                            <table id="dynamic2-table" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Thời gian</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>

                        <div id="dropdown14" class="tab-pane">
                            <table id="dynamic3-table" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Trạng thái</th>
                                    <th>Ghi chú</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>
                                            <c:if test="${model.status == 1}">
                                                <input type="checkbox" name="checkList1" value="status" class="check-box-element" checked="checked"/>Đã ký
                                                <input type="checkbox" name="checkList2" value="status" class="check-box-element"/>Chưa ký
                                            </c:if>
                                            <c:if test="${model.status != 1}">
                                                <input type="checkbox" name="checkList1" value="status" class="check-box-element" />Đã ký
                                                <input type="checkbox" name="checkList2" value="status" class="check-box-element" checked="checked"/>Chưa ký
                                            </c:if>
                                        </th>
                                    </tr>

                                    <tr>
                                        <th>
                                            <p>${model.note}</p>
                                        </th>
                                    </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </center>
</div><!-- /.col -->
<script type="text/javascript">
    $(document).ready(function () {
    });
</script>
</body>
</html>
