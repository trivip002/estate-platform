<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tag.jsp"%>
<c:url var="formUrl" value="/api/admin/building"/>
<c:url var="presentForm" value="/admin/building/edit?id=${model.id}"/>
<html>
<head>
    <title>Chỉnh sửa người dùng</title>

</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa tòa nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form:form id="formEdit" commandName="model" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Quận </label>
                            <div class="col-sm-9">
                                <form:input path="district" id = "district" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Tên </label>
                            <div class="col-sm-9">
                                <form:input path="name" id = "name" name = "name" cssClass="form-control"/>
                            </div>

                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Phường</label>
                            <div class="col-sm-9">
                                <form:input path="precint" id = "precint" name = "precint" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Đường </label>
                            <div class="col-sm-9">
                                <form:input path="street" id = "street" name = "street" cssClass="form-control"/>
                            </div>

                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Kết cấu</label>
                            <div class="col-sm-9">
                                <form:input path="structure" id = "structure" name = "structure" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Hướng</label>
                            <div class="col-sm-9">
                                <form:input path="direction" id = "direction" name = "direction" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Hạng</label>
                            <div class="col-sm-9">
                                <form:input path="ranking" id = "ranking" name = "ranking" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Mô tả diện tích</label>
                            <div class="col-sm-9">
                                <form:input path="acreageDescription" id = "acreageDescription" name = "acreageDescription" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Mô tả giá</label>
                            <div class="col-sm-9">
                                <form:input path="priceDescription" id = "priceDescription" name = "priceDescription" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Thời hạn thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentDuration" id = "rentDuration" name = "rentDuration" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Thời gian trang trí</label>
                            <div class="col-sm-9">
                                <form:input path="decorateTime" id = "decorateTime" name = "decorateTime" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Số tầng hầm</label>
                            <div class="col-sm-9">
                                <form:input path="basementNumber" id = "basementNumber" name = "basementNumber" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Diện tích sàn</label>
                            <div class="col-sm-9">
                                <form:input path="acreageFloor" id = "acreageFloor" name = "acreageFloor" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Diện tích thuê</label>
                            <div class="col-sm-9">
                                <form:input path="acreageRent" id = "acreageRent" name = "acreageRent" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Giá thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentCost" id = "rentCost" name = "rentCost" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Phí phục vụ</label>
                            <div class="col-sm-9">
                                <form:input path="serviceCharge" id = "serviceCharge" name = "serviceCharge" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Phí ô tô</label>
                            <div class="col-sm-9">
                                <form:input path="carCharge" id = "carCharge" name = "carCharge" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Phí ngoài giờ</label>
                            <div class="col-sm-9">
                                <form:input path="overtimeCharge" id = "overtimeCharge" name = "overtimeCharge" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Tiền điện</label>
                            <div class="col-sm-9">
                                <form:input path="electricBill" id = "electricBill" name = "electricBill" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Tiền đặt cọc</label>
                            <div class="col-sm-9">
                                <form:input path="deposit" id = "deposit" name = "deposit" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Thanh toán</label>
                            <div class="col-sm-9">
                                <form:input path="pay" id = "pay" name = "pay" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Phí mô giới</label>
                            <div class="col-sm-9">
                                <form:input path="agencyCharge" id = "agencyCharge" name = "agencyCharge" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Lựa chọn</label>
                            <div class="col-sm-9">
                                <form:checkboxes items="${mapType}"  path="typeArray"  cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Ghi chú</label>
                            <div class="col-sm-9">
                                <form:textarea  path="note" id="note" name="note" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Link</label>
                            <div class="col-sm-9">
                                <form:input path="link" id = "link" name = "link" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Bản đồ</label>
                            <div class="col-sm-9">
                                <form:input path="map" id = "map" name = "map" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thumbnail bài viết:</label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadImage"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Xem trước</label>
                            <div class="col-sm-9">
                                <c:if test="${not empty model.thumnail}">
                                    <c:set var="image" value="/repository/${model.thumnail}"/>
                                    <img src="${image}" id="viewImage" width="150px" height="150px">
                                </c:if>
                                <c:if test="${empty model.thumnail}">
                                    <img src="<c:url value='/image/no-image.png'/>" id="viewImage" width="150px" height="150px">
                                </c:if>
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật tòa nhà" id="btnAddOrUpdateBuildings"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới tòa nhà" id="btnAddOrUpdateBuildings"/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="buildingId"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var districts = ["Quận 1", "Quận 2","Quận 3","Quận 4","Quận 5","Quận 6","Quận 7","Quận 8","Quận 9", "Quận 10","Gò vấp","Tân bình"];
    $(document).ready(function () {
        $('#uploadImage').change(function () {
            openImage(this, "viewImage");
        });
    });
    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $('#btnAddOrUpdateBuildings').click(function (event){
        event.preventDefault();
        var formData = $("#formEdit").serializeArray();
        var files = $('#uploadImage')[0].files[0];
        var typeArray = [];
        var dataArray = {};
        $.each(formData,function (i,v) {
            if(v.name == 'typeArray'){
                typeArray.push(v.value);
            }else{
                dataArray[""+v.name+""] = v.value;
            }
        });
        dataArray["typeArray"] = typeArray;
        dataArray["priority"] = 0;
        if(files == undefined){
            dataArray["imageName"] = "";
            updateBuilding(dataArray, $('#buildingId').val());
        }else{
            dataArray["imageName"] = files.name;
            var reader = new FileReader();
            reader.onload = function (e){
                var id = $('#buildingId').val();
                dataArray["thumbnailBase64"] = e.target.result;
                if(id == ""){
                    addBuilding(dataArray);
                }else{
                    updateBuilding(dataArray,id);
                }
            }
            reader.readAsDataURL(files);
        }
    });
    function addBuilding(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=insert_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }
    function updateBuilding(data, id) {
        $.ajax({
            url: '${formUrl}/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=update_success'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }

</script>
</body>
</html>
