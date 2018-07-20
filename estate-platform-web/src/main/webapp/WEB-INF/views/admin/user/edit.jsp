<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tag.jsp"%>
<c:url var="formUrl" value="/api/admin/user"/>
<c:url var="presentForm" value="/admin/user/edit?id=${model.id}"/>
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
                <li class="active">Chỉnh sửa bài viết</li>
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
                    <form:form id="formEdit" commandName="model" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Chọn vai trò</label>
                            <div class="col-sm-9">
                                <form:select path="role" id="role" name = "role">
                                    <form:option value="NONE" label="--- Chọn loại người dùng ---"/>
                                    <form:options items="${model.rolesName}" />
                                </form:select>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Tên đăng nhập </label>
                            <div class="col-sm-9">
                                <form:input path="userName" id = "userName" name = "userName" cssClass="form-control"/>
                                <p id="errorUserName" hidden style="color: red">tên người dùng đã tồn tại!</p>
                            </div>

                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Tên đầy đủ </label>
                            <div class="col-sm-9">
                                <form:input path="fullName" id = "fullName" name = "fullName" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Email </label>
                            <div class="col-sm-9">
                                <form:input path="email" id = "email" name = "email" cssClass="form-control"/>
                                <p id="errorEmail"  hidden style="color: red;"> email đã tồn tại</p>
                            </div>

                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  >Số điện thoại</label>
                            <div class="col-sm-9">
                                <form:input path="phoneNumber" id = "phoneNumber" name = "phoneNumber" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật người dùng" id="btnAddOrUpdateUsers"/>
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thay đổi mật khẩu" id="btnChangePass"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới người dùng" id="btnAddOrUpdateUsers"/>
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thay đổi mật khẩu" id="btnChangePass" disabled/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="userId"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var emailDefalt = $("#email").val();
    var userNameDefault =  $("#userName").val();
    $(document).ready(function () {

        $("#formEdit").validate({
            rules:{
                role: { valueNotEquals: "--- Chọn loại người dùng ---" },
                userName:{
                    required: true,
                    minlength: 4
                },
                fullName: "required",
                email:{
                    required:true,
                    email:true
                },
                phoneNumber: {
                    require: true,
                    minlength: 10
                }
            },
            messages:{
                role: "Please select an item!",
                userName: "vui lòng nhập tên đăng nhập (phải có ít nhất 4 ký tự)",
                fullName: "vui lòng nhập đầy đủ họ tên",
                email: "vui lòng nhập chính xác email",
                phoneNumber: "vui lòng nhập số diện thoại"
            }
        })
    });
    $("#userName").blur(function() {
        var array = {};
        array["email"] = $("#email").val();
        array["userName"] = $("#userName").val();
        if(array["error"] != userNameDefault){
            //checkUserNameOrEmail(array);
        }

    });
    $("#email").blur(function() {
        var array = {};
        array["email"] = $("#email").val();
        array["userName"] = $("#userName").val();
        if(array["email"] != emailDefalt){
            //checkUserNameOrEmail(array);
        }
    });
    function checkUserNameOrEmail(data) {
        $.ajax({
            url: '${formUrl}/usages',
            type: 'GET',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {

            },
            error: function(res) {
                alert(res);

            }
        });
    }
    $('#btnAddOrUpdateUsers').click(function (event) {
        event.preventDefault();
        var checkClick = true;
        var dataArray = {};
        dataArray["role"] = $('#role').val();
        if(dataArray["role"] == "NONE"){
            alert("vui lòng chọn loại người dùng");
            checkClick = false;
        }
        dataArray["userName"] = $('#userName').val();
        if(dataArray["userName"] == null || dataArray["userName"].length <= 3){
            alert("vui lòng nhập lại tên đăng nhập");
            $('#userName').focus();
            checkClick = false;
        }
        dataArray["fullName"] = $('#fullName').val();
        if(dataArray["fullName"] == null || dataArray["fullName"] == ""){
            alert("vui lòng nhập họ và tên");
            $('#fullName').focus();
            checkClick = false;
        }
        dataArray["email"] = $('#email').val();
        if(dataArray["email"] == null || dataArray["email"] == ""){
            alert("vui lòng nhập lại email");
            $('#email').focus();
            checkClick = false;
        }
        dataArray["phoneNumber"] = $('#phoneNumber').val();
        if(dataArray["phoneNumber"] == 0 ||  dataArray["phoneNumber"].length < 10){
            alert("vui lòng nhập lại số điện thoại");
            $('#phoneNumber').focus();
            checkClick = false;
        }
        if(checkClick){
            var id = $('#userId').val();
            if(id == "") {
                addUsers(dataArray);
            }
            else {
                updateUsers(dataArray,id);
            }
        }else {
            alert("Bạn đã nhập sai , xin vui lòng nhập lại!");
        }

    });

    $('#btnChangePass').click(function (event) {
        event.preventDefault();
        var data = $('#userId').val();
        chagePass(data);
        });

    function chagePass(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'PUT',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='?message=update_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='?message=error_system'/>";
            }
        });
    }

    function addUsers(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/user/list?message=insert_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/user/list?message=error_system'/>";
            }
        });
    }
    function updateUsers(data, id) {
        $.ajax({
            url: '${formUrl}/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {

                window.location.href = "<c:url value='/admin/user/list?message=update_success'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/admin/user/list?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
