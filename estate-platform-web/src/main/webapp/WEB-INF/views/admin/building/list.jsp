<%@ page import="com.estate.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tag.jsp"%>
<c:url var="formUrl" value="/admin/building/list"/>
<c:url var="formAPI" value="/api/admin/building"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách tòa nhà</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                        <a href="/admin/home">Trang chủ</a>
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
                                                    <div class="col-sm-6">
                                                        <div class="fg-line">
                                                            <form:input path="name" cssClass="form-control input-sm"
                                                                        placeholder="Tên tòa nhà"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="fg-line">
                                                            <input type="number" name="floorArea"
                                                                   class="form-control input-sm"
                                                                   value="${model.acreageFloor}"
                                                                   placeholder="Diện tích sàn"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:select path="district" id="district">
                                                                <form:option value="" label="--- Quận ---"/>
                                                                <form:options items="${model.districts}" />
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="precint" id="ward"
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
                                                            <form:input path="direction" id="direction"
                                                                        cssClass="form-control input-sm"
                                                                        placeholder="Hướng"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="fg-line">
                                                            <form:input path="ranking" id="type"
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
                                                                   value="${model.priceFrom}"
                                                                   placeholder="Gía thuê từ" id="costFrom"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <div class="fg-line">
                                                            <input type="number" name="costTo"
                                                                   class="form-control input-sm"
                                                                   value="${model.priceTo}"
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
                                                                    <form:options items="${listUsers}"/>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <form:hidden path="searchValue" id="searchValue" />
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <div class="fg-line">
                                                            <form:checkboxes path="typeArray" items="${mapType}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <button id="btnSearch" type="button"
                                                                class="btn btn-sm btn-success">
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110">Tìm kiếm</i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <security:authorize access="hasRole('ADMIN')">
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <div class="dt-buttons btn-overlap btn-group">
                                            <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                               data-toggle="tooltip" title="Thêm tòa nhà" href='<c:url value="/admin/building/edit"/>'>
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
                            </security:authorize>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <display:table name="model.listResult" cellspacing="0" cellpadding="0" requestURI="${formUrl}"
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
                                        <display:column headerClass="text-left" property="name" title="Tên"/>
                                        <display:column headerClass="text-left" property="rentCost" title="Giá thuê"/>
                                        <display:column headerClass="text-left" property="thumnail" title="Hình ảnh"/>
                                        <display:column headerClass="col-actions" title="Ưu tiên">
                                            <c:if test="${tableList.priority == 0}">
                                                <a class="btn btn-sm btn-primary btn-success" data-toggle="tooltip" id = "btnChange_${tableList.id}" onclick="changePriority(${tableList.id})"
                                                   title="Chọn quyền ưu tiên" ><i class="fa fa-refresh bigger-" aria-hidden="true"></i>
                                                </a>
                                            </c:if>
                                            <c:if test="${tableList.priority != 0}">
                                                <a class="btn btn-sm btn-primary btn-danger" data-toggle="tooltip" id = "btnChange_${tableList.id}" onclick="changePriority(${tableList.id})"
                                                   title="Hủy quyền ưu tiên" ><i class="fa fa-refresh bigger-" aria-hidden="true"></i>
                                                </a>
                                            </c:if>
                                        </display:column>
                                        <display:column headerClass="col-actions" title="Thao tác">
                                            <a class="btn btn-sm btn-primary" data-toggle="tooltip"
                                               title="Chi tiết tòa nhà" href='<c:url value="/admin/building/${tableList.id}/detail"/>'><i class="fa fa-search-plus bigger" aria-hidden="true"></i>
                                            </a>
                                            <security:authorize access="hasRole('ADMIN')">
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                   title="Cập nhật bài viết" href='<c:url value="/admin/building/edit?id=${tableList.id}"/>'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                </a>
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="modal" data-target="#myModal"
                                                   title="Giao cho Người dùng quản lý"><i class="fa fa-share bigger" aria-hidden="true"></i>
                                                </a>
                                                <!-- Modal -->
                                                <div id="myModal" class="modal fade" role="dialog">
                                                    <div class="modal-dialog">

                                                        <!-- Modal content-->
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                <h4 class="modal-title">Giao quyền</h4>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal" >Đóng</button>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
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
</div>

<script type="text/javascript">
    $(document).ready(function () {
    });

    $('#btnSearch').click(function () {
        $('#listForm').submit();
    });
    function protocolUser(id) {
        var checkedVals = $('input.modal-body:checkbox:checked').map(function() {
            return this.value;
        }).get();
    }
    function changePriority(id) {
        $.ajax({
            url: '${formAPI}',
            type: 'PUT',
            contentType:'application/json',
            data: JSON.stringify(id),
            success: function(res) {
                console.log(res);
                if ($("#btnChange_" + id).hasClass('btn-success')){
                    $("#btnChange_" + id).attr('class','btn btn-sm btn-primary btn-danger');
                }else{
                    $("#btnChange_" + id).attr('class','btn btn-sm btn-primary btn-success');
                }

            },
            error: function(res) {
                console.log(res);
                alert("Chọn quyền ưu tiên thất bại");
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
    function deleteBuilding(data) {
        $.ajax({
            url: '${formAPI}',
            type: 'DELETE',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                console.log(res);
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
