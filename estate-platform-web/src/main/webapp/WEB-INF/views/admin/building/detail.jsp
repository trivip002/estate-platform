<%@include file="/common/tag.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết</title>
</head>
<body>
<div class="main-content">
    <div class="row">
        <div class="col-xs-7">
            <div class="clearfix">
                <div class="pull-right tableTools-container"></div>
            </div>
            <div class="table-header">
                Bảng chi tiết tòa nhà"
            </div>
            <div>
                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                    <tbody>
                    <tr>
                        <th>Tên tòa nhà</th>
                        <th>${model.name}</th>
                    </tr>
                    <tr>
                        <th>Quận</th>
                        <th>${model.district}</th>
                    </tr>
                    <tr>
                        <th>Phường</th>
                        <th>${model.precint}</th>
                    </tr>
                    <tr>
                        <th>Đường</th>
                        <th>${model.street}</th>
                    </tr>
                    <tr>
                        <th>Kết cấu</th>
                        <th>${model.structure}</th>
                    </tr>
                    <tr>
                        <th>Số tầng hầm</th>
                        <th>${model.basementNumber}</th>
                    </tr>
                    <tr>
                        <th>Diện tích sàn</th>
                        <th>${model.acreageFloor}</th>
                    </tr>
                    <tr>
                        <th>Hướng</th>
                        <th>${model.direction}</th>
                    </tr>
                    <tr>
                        <th>Hạng</th>
                        <th>${model.ranking}</th>
                    </tr>
                    <tr>
                        <th>Diện tích thuê</th>
                        <th>${model.acreageRent}</th>
                    </tr>
                    <tr>
                        <th>Mô tả diện tích</th>
                        <th>${model.acreageDescription}</th>
                    </tr>
                    <tr>
                        <th>Giá thuê</th>
                        <th>${model.rentCost}</th>
                    </tr>
                    <tr>
                        <th>Mô tả giá thuê</th>
                        <th>${model.priceDescription}</th>
                    </tr>
                    <tr>
                        <th>Phí dịch vụ</th>
                        <th>${model.serviceCharge}</th>
                    </tr>
                    <tr>
                        <th>Phí ô tô</th>
                        <th>${model.carCharge}</th>
                    </tr>
                    <tr>
                        <th>Phí ngoài giờ</th>
                        <th>${model.overtimeCharge}</th>
                    </tr>
                    <tr>
                        <th>Tiền điện</th>
                        <th>${model.electricBill}</th>
                    </tr>
                    <tr>
                        <th>Tiền đặt cọc</th>
                        <th>${model.deposit}</th>
                    </tr>
                    <tr>
                        <th>Thanh toán</th>
                        <th>${model.pay}</th>
                    </tr>
                    <tr>
                        <th>Thời hạn thuê</th>
                        <th>${model.rentDuration}</th>
                    </tr>
                    <tr>
                        <th>Thời gian trang trí</th>
                        <th>${model.decorateTime}</th>
                    </tr>
                    <tr>
                        <th>Phí mô giới</th>
                        <th>${model.agencyCharge}</th>
                    </tr>
                    <tr>
                        <th>Chú thích</th>
                        <th>${model.note}</th>
                    </tr>
                    <tr>
                        <th>Đường dẫn</th>
                        <th>${model.link}</th>
                    </tr>
                    <tr>
                        <th>Vị trí</th>
                        <th>${model.map}</th>
                    </tr>
                    <tr>
                        <th>Loại nhà</th>
                        <th>${model.types}</th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <c:if test="${not empty model.thumnail}">
                    <c:set var="image" value="/repository/${model.thumnail}"/>
                    <img src="${image}" id="viewImage" width="600px" height="950px">
                </c:if>
                <c:if test="${empty model.thumnail}">
                    <img src="<c:url value='/image/no-image.png'/>" id="viewImage" width="600px" height="950px">
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
