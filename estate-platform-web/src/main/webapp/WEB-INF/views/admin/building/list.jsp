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
                                    <%--<div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <div class="fg-line">
                                                            <form:input path="name" cssClass="form-control input-sm"
                                                                        placeholder="Tên tòa nhà"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="fg-line">
                                                            <input type="number" name="buildingArea"
                                                                   class="form-control input-sm"
                                                                   value="${model.floorArea}"
                                                                   placeholder="Diện tích sàn"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="district" id="district"
                                                                        cssClass="form-control input-sm"
                                                                        placeholder="Quận hiện có"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="ward" id="ward"
                                                                        cssClass="form-control input-sm"
                                                                        placeholder="Phường"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="street" id="street"
                                                                        cssClass="form-control input-sm"
                                                                        placeholder="Đường"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <input type="number" name="numberOfBasement"
                                                                   class="form-control input-sm"
                                                                   value="${model.numberOfBasement}"
                                                                   placeholder="Số tầng hầm" id="numberOfBasement"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="direction" id="direction"
                                                                        cssClass="form-control input-sm"
                                                                        placeholder="Hướng"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="level" id="level"
                                                                        cssClass="form-control input-sm"
                                                                        placeholder="Hạng"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-3">
                                                        <div class="fg-line">
                                                            <input type="number" name="areaFrom"
                                                                   class="form-control input-sm"
                                                                   value="${model.areaFrom}"
                                                                   placeholder="Diện tích từ" id="areaFrom"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <div class="fg-line">
                                                            <input type="number" name="areaTo"
                                                                   class="form-control input-sm"
                                                                   value="${model.areaTo}"
                                                                   placeholder="Diện tích đến" id="areaTo"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <div class="fg-line">
                                                            <input type="number" name="costFrom"
                                                                   class="form-control input-sm"
                                                                   value="${model.costFrom}"
                                                                   placeholder="Gía thuê từ" id="costFrom"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <div class="fg-line">
                                                            <input type="number" name="costTo"
                                                                   class="form-control input-sm"
                                                                   value="${model.costTo}"
                                                                   placeholder="Gía thuê đến" id="costTo"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="managerName" id="managerName"
                                                                        cssClass="form-control input-sm"
                                                                        placeholder="Tên quản lý"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="managerPhone" id="managerPhone"
                                                                        cssClass="form-control input-sm"
                                                                        placeholder="Điện thoại quản lý"/>
                                                        </div>
                                                    </div>
                                                    <c:if test="${urlMapping != '/admin/building/assignment'}">
                                                        <div class="col-sm-4">
                                                            <div class="fg-line">
                                                                <form:select path="staffName" id="staffName">
                                                                    <form:option value=""
                                                                                 label="--- Chọn nhân viên phụ trách ---"/>
                                                                    <form:options items="${staffMaps}"/>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <div class="fg-line">
                                                            <form:checkboxes path="typeArrays" items="${mapTypes}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <button id="btnSearch" type="button"
                                                                class="btn btn-sm btn-success">
                                                            <spring:message code="label.search"/>
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>--%>
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
                                        <c:if test="${prioritize != 1}">
                                            <display:column headerClass="" title="HOT">
                                                <c:choose>
                                                    <c:when test="${tableList.prioritize == 1}">
                                                        <a href="#" buildingId="${tableList.id}" class="btnPriority" action="remove">
                                                            <span class="glyphicon glyphicon-ok"></span>
                                                        </a>
                                                    </c:when>
                                                    <c:when test="${tableList.prioritize == 0}">
                                                        <a href="#" buildingId="${tableList.id}" class="btnPriority" action="add">
                                                            <span class="glyphicon glyphicon-plus"></span>
                                                        </a>
                                                    </c:when>
                                                </c:choose>
                                            </display:column>
                                        </c:if>
                                        <display:column headerClass="col-actions" title="Thao tác">
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Cập nhật tòa nhà" href='<c:url value="/admin/building/edit?id=${tableList.id}"/>'><i class="fa fa-edit" aria-hidden="true"></i></a>
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Chi tiết tòa nhà" href='<c:url value="/admin/building/detail/${tableList.id}"/>'><i class="fa fa-external-link" aria-hidden="true"></i></a>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="modal" data-target ="#exampleModal" data ="${tableList.id}" id="${tableList.id}"
                                                   title="Giao cho user quản lý" href='<c:url value="#"/>'><i class="fa fa-users" aria-hidden="true"></i></a>
                                            </security:authorize>
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
    <input type="hidden" value="<%=SecurityUtils.getPrincipal().getId()%>" id="userId">
    <security:authorize access="hasRole('MANAGER')">
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
                                    <label class="col-sm-3 control-label no-padding-right">Danh sách user đã được giao</label>
                                    <div class="col-sm-9">
                                        <ul style="list-style-type:square">
                                            <%--<c:forEach var = "i" items="${model.listResult.get(0).userAssignment}">--%>
                                                <%--<li>${i.userName}</li>--%>
                                            <%--</c:forEach>--%>

                                        </ul>
                                    </div>
                                </div>
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
    </security:authorize>
</div>
<script type="text/javascript">
    var users = "";
    $(document).ready(function () {

    });

    $('#btnSearch').click(function () {
        $('#listForm').submit();
    });


    $('.btnPriority').click(function (event) {
        event.preventDefault();
        buildingId = $(this).attr("buildingId");
        action = $(this).attr("action");
        updatePrioritize(action,buildingId);
    });

    $('#btnAdd').click(function (event) {
        event.preventDefault();
        users += ($('#userName').val()+",");
    });

    $('#btnSave').click(function (event) {
        event.preventDefault();
        //var id = $('#tableList.id')
        updateBuilding(users, 3);
    });

    function updateBuilding(data, id) {
        $.ajax({
            url: '${API}/'+id+'/users',
            type: 'POST',
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

    function updatePrioritize(action,id) {
        $.ajax({
            url: '${API}/'+id+'/priority?action='+action,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(),
            success: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=update_success'/>";
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

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteBuilding(dataArray);
        });
    }
</script>
</body>
</html>
