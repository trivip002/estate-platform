<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/building"/>
<html>
<head>
    <title>Chỉnh sửa tòa nhà</title>
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
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form:form id="formEdit" commandName="model">

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên sản phẩm</label>
                            <div class="col-sm-9">
                                <form:input path="name" id="name" cssClass="form-control"  />
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Quận</label>
                            <div class="col-sm-9">
                                <form:select path="district" id="district">
                                    <form:option value="" label="--- Quận ---"/>
                                    <form:options items="${model.districts}" />
                                </form:select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Phường</label>
                            <div class="col-sm-9">
                                <form:input path="ward" id="ward" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Đường</label>
                            <div class="col-sm-9">
                                <form:input path="street" id="street" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Kết cấu</label>
                            <div class="col-sm-9">
                                <form:input path="structure" id="structure" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Diện tích sàn</label>
                            <div class="col-sm-9">
                                <form:input path="floorArea" id="floorArea" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Hướng</label>
                            <div class="col-sm-9">
                                <form:input path="direction" id="direction" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Hạng</label>
                            <div class="col-sm-9">
                                <form:input path="type" id="type" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Diện tích thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentArea" id="rentArea" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giá thuê</label>
                            <div class="col-sm-9">
                                <form:input path="price" id="price" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Phí dịch vụ</label>
                            <div class="col-sm-9">
                                <form:input path="serviceFee" id="serviceFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Phí ô tô</label>
                            <div class="col-sm-9">
                                <form:input path="carParkingCharge" id="carParkingCharge" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Phí mô tô</label>
                            <div class="col-sm-9">
                                <form:input path="motorParkingCharge" id="motorParkingCharge" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Phí ngoài giờ</label>
                            <div class="col-sm-9">
                                <form:input path="extraCharge" id="extraCharge" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tiền điện</label>
                            <div class="col-sm-9">
                                <form:input path="electricCharge" id="electricCharge" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Đặt cọc</label>
                            <div class="col-sm-9">
                                <form:input path="deposit" id="deposit" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thanh toán</label>
                            <div class="col-sm-9">
                                <form:input path="payment" id="payment" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thời hạn thuê</label>
                            <div class="col-sm-9">
                                <form:input path="timeForRent" id="timeForRent" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thời gian trang trí</label>
                            <div class="col-sm-9">
                                <form:input path="timeForDecorate" id="timeForDecorate" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên quản lý</label>
                            <div class="col-sm-9">
                                <form:input path="managerName" id="managerName" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Điện thoại quản lý</label>
                            <div class="col-sm-9">
                                <form:input path="managerPhone" id="managerPhone" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Phí môi giới</label>
                            <div class="col-sm-9">
                                <form:input path="agencyCharge"  cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-inline">
                            <label class="col-sm-3 control-label no-padding-right">Lựa chọn</label>
                            <div class="col-sm-9">
                                <%--<form:checkbox path="option" value="0" cssClass="form-control"  /><span class="option">Thuê nguyên căn</span>--%>
                                <%--<form:checkbox path="option"  value="1" cssClass="form-control" /><span class="option">Tầng trệt</span>--%><%--<form:checkbox path="option"  value="2" cssClass="form-control"  /><span class="option">Nội thất</span>--%>
                                    <form:checkboxes items="${mapTypes}"  path="typeArrays"  class="option" id="typeArrays"/>
                            </div>
                            <div id="fieldHidden">
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Chú thích</label>
                            <div class="col-sm-9">
                                <form:input path="description" id="description" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Link sản phẩm</label>
                            <div class="col-sm-9">
                                <form:input path="link" id="link" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Bản đồ</label>
                            <div class="col-sm-9">
                                <form:input path="map" id="map" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Ảnh đại diện</label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadImage" value="${model.avatar}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật tòa nhà" id="btnAddOrUpdateBuilding"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới tòa nhà" id="btnAddOrUpdateBuilding"/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="buildingId"/>
                        <form:hidden path="createdBy" id="createdBy"/>
                        <form:hidden path="createdDate" id="createdDate"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var check_valdidate;
    var typeArrays;
    $(document).ready(function () {
        check_valdidate = false;
        typeArrays = true;
        $("#formEdit").validate({
            rules: {
                "district": { // <-- assign by field name and use quotes
                    required: true,
                },
                "uploadImage": { // <-- assign by field name and use quotes
                    required: true,
                },
                "typeArrays": { // <-- assign by field name and use quotes
                    required: true,
                }

            },
            messages: {
                "district":{
                    required:"Please choose a district"
                },
                "uploadImage":{
                    required:"Please choose a Image"
                },
                "typeArrays":{
                    //required:"Please choose at least a option",
                }

            },
            submitHandler: function(form)
            {
                check_valdidate = true;
            }

        });
        if(!typeArrays){
            var buildingIdHidden = ' <label id="typeArrays-error" class="error" for="typeArrays">Please choose a option</label>';
            $('#fieldHidden').html(buildingIdHidden);
        }

    });

    $('#btnAddOrUpdateBuilding').click(function (event) {
        event.preventDefault();
        var formData = $("#formEdit").serializeArray();
        var dataArray = {};
        var typeArray = [];
        $.each(formData,function (i,v){
            if(v.name == 'typeArrays'){
                typeArray.push(v.value);
            }else if(v.name == 'createdBy' || v.name == 'createdDate'){
                return;
            }
            else{
                dataArray[""+v.name+""] = v.value;
            }
        });
        dataArray["typeArrays"] = typeArray;
        dataArray["district"] = $("#district").val();
        var files = $('#uploadImage')[0].files[0];
        var id = $('#buildingId').val();
        if (files == undefined && id != "") {//update
            dataArray["imageName"] = "";
            updateBuilding(dataArray, id);
        }
        else {
            $('#formEdit').submit();
            if(check_valdidate && files != undefined){
                dataArray["imageName"] = files.name;
                var reader = new FileReader();
                reader.onload = function (e) {
                    dataArray["avatarBase64"] = e.target.result;
                    if (id == "") {
                        addBuilding(dataArray);
                    } else {
                        dataArray["createdBy"] = $("#createdBy").val();
                        dataArray["createdDate"] = $("#createdDate").val();
                        updateBuilding(dataArray, id);
                    }
                }
                reader.readAsDataURL(files);
            }

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
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }

</script>
</body>
</html>
