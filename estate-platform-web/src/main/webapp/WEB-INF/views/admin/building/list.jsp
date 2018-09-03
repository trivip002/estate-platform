<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.estate.utils.SecurityUtils" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin/building/list"/>
<c:url var="API" value="/api/admin/building"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content">
    <form:form commandName="model" action="${formUrl}" id="listForm" method="GET">
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
                    <li class="active">Danh sách tòa nhà</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${messageResponse!=null}">
                            <div class="alert alert-block alert-${alert}">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="widget-header">
                                        <h4 class="widget-title">Tìm kiếm</h4>
                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Gía trị cần tìm:</label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-8">
                                                        <button type="button" class="btn btn-sm btn-success" id="btnSearch">
                                                            Tìm kiếm
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <div class="dt-buttons btn-overlap btn-group">
                                            <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                               data-toggle="tooltip" title="Thêm tòa nhà mới" href='<c:url value="/admin/building/edit"/>'>
                                                    <span>
                                                    <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                </span>
                                            </a>
                                            <button id="btnDelete" type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" disabled
                                                    data-toggle="tooltip" title="Xóa tòa nhà" onclick="warningBeforeDelete()">
                                                    <span>
                                                    <i class="fa fa-trash-o bigger-110 pink"></i>
                                                	</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <display:table  name="model.listResult" cellspacing="0" cellpadding="0" requestURI="${formUrl}" uid="row"
                                                   partialList="true" size="${model.totalItems}" id="tableList" pagesize="${model.maxPageItems}" export="false"
                                                    class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                   style="margin: 3em 0 1.5em;">
                                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                        headerClass="center select-cell">
                                            <fieldset>
                                                <input type="checkbox" name="checkList" value="${tableList.id}" id="checkbox_${tableList.id}" class="check-box-element"/>
                                            </fieldset>
                                        </display:column>
                                        <display:column headerClass="text-left" property="name" title="Tên sản phẩm"/>
                                        <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                                        <display:column headerClass="text-left" property="managerName" title="Tên Q.Lý"/>
                                        <display:column headerClass="text-left" property="managerPhone" title="Phone"/>
                                        <display:column headerClass="text-left" property="rentArea" title="Diện tích sàn"/>
                                        <display:column headerClass="text-left" property="name" title="Diện tích trống"/>
                                        <display:column headerClass="text-left" property="price" title="Giá thuê"/>
                                        <display:column headerClass="text-left" property="agencyCharge" title="Phí môi giới"/>
                                        <c:if test="${model.prioritize != 1}">
                                            <display:column headerClass="" title="HOT">
                                                <a href="#" id="${tableList.id}" class="btnPlus">
                                                    <c:set var = "index" scope = "session" value = "${tableList_rowNum-1}"/>
                                                    <c:if test="${model.listResult.get(index).prioritize == 1}">
                                                        <span id="btn_${tableList.id}" class="glyphicon glyphicon-ok"></span>
                                                    </c:if>
                                                    <c:if test="${model.listResult.get(index).prioritize == 0}">
                                                        <span id="btn_${tableList.id}" class="glyphicon glyphicon-plus"></span>
                                                    </c:if>
                                                </a>
                                            </display:column>
                                        </c:if>
                                        <display:column headerClass="col-actions" title="Thao tác">
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Cập nhật tòa nhà" href='<c:url value="/admin/building/edit?id=${tableList.id}"/>'><i class="fa fa-edit" aria-hidden="true"></i></a>
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Chi tiết tòa nhà" href='<c:url value="/admin/building/detail/${tableList.id}"/>'><i class="fa fa-external-link" aria-hidden="true"></i></a>
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="modal" data-target ="#exampleModal" data ="${tableList.id}" id="${tableList.id}"
                                               title="Giao cho user quản lý" href='<c:url value="#"/>'><i class="fa fa-users" aria-hidden="true"></i></a>
                                        </display:column>
                                    </display:table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form:form>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Giao cho user</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <span>Mã tòa nhà: ${tableList.id}</span>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nhập username</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="userName">
                                </div>
                            </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="btnAdd">Add</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id ="btnSave">Lưu</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var userArray = "";
    $(document).ready(function () {

    });

    $('#btnSearch').click(function () {
        $('#listForm').submit();
    });


    $('.btnPlus').click(function (event) {
        event.preventDefault();
        var dataArray = {};
        id = $(this).attr("id");
        userId = 2;
        var prioritize;
        if($('#btn_'+id).hasClass("glyphicon glyphicon-plus")){
            prioritize = 1;
            $('#btn_'+id).removeClass("glyphicon glyphicon-plus");
            $('#btn_'+id).addClass("glyphicon glyphicon-ok");
        }else{
            prioritize = 0;
            $('#btn_'+id).removeClass("glyphicon glyphicon-ok");
            $('#btn_'+id).addClass("glyphicon glyphicon-plus");
        }
        dataArray["user_id"] = userId
        dataArray["buiding_id"] = id;

        updatePrioritize(dataArray,prioritize);

    });

    $('#btnAdd').click(function (event) {
        event.preventDefault();
        userArray += ($('#userName').val()+",");

    });

    $('#btnSave').click(function (event) {
        event.preventDefault();
        //var id = $('#tableList.id')
        updateBuilding(userArray, 3);
    });

    function updateBuilding(data, id) {
        $.ajax({
            url: '${API}/user/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data,id),
            success: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=update_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }


    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteBuilding(dataArray);
        });
    }

    function updatePrioritize(data,proritize) {
        $.ajax({
            url: '${API}/prioritize/'+proritize,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                <%--window.location.href = "<c:url value='/admin/building/list'/>";--%>
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }

    function deleteBuilding(data) {
        $.ajax({
            url: '${API}',
            type: 'DELETE',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=delete_success'/>";
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
