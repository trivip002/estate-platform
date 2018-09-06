<%@ page import="com.estate.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin/customer/list"/>
<c:url var="API" value="/api/admin/customer"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách khách hàng</title>
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
                    <li class="active">Danh sách khách hàng</li>
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
                                    <div class="table-btn-controls">
                                        <div class="pull-right tableTools-container">
                                            <div class="dt-buttons btn-overlap btn-group">
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                                       data-toggle="tooltip" title="Thêm khách hàng mới" href='<c:url value="/admin/customer/edit"/>'>
                                                            <span>
                                                            <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                        </span>
                                                    </a>

                                                    <button id="btnDelete" type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" disabled
                                                            data-toggle="tooltip" title="Xóa khách hàng" onclick="warningBeforeDelete()">
                                                            <span>
                                                            <i class="fa fa-trash-o bigger-110 pink"></i>
                                                            </span>
                                                    </button>
                                                </security:authorize>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
                                        <display:column headerClass="text-left" property="name" title="Họ tên"/>
                                        <display:column headerClass="text-left" property="phoneNumber" title="Di động"/>
                                        <display:column headerClass="text-left" property="email" title="email"/>
                                        <display:column headerClass="text-left" property="need" title="Nhu cầu"/>
                                        <display:column headerClass="textarea" property="process" title="Quá trình CSKH"/>
                                        <display:column headerClass="text-left" property="modifiedBy" title="Người nhập"/>
                                        <display:column headerClass="text-left" property="createdDate" title="Ngày nhập"/>
                                        <display:column headerClass="col-actions" title="Thao tác">
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                   title="Cập nhật khách hàng" href='<c:url value="/admin/customer/edit?id=${tableList.id}"/>'><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="modal" data-target ="#exampleModal" data ="${tableList.id}" id="${tableList.id}"
                                                       title="Giao cho user quản lý" href='<c:url value="#"/>'><i class="fa fa-users" aria-hidden="true"></i></a>
                                                </security:authorize>
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                title="Chi tiết khách hàng" href='<c:url value="/admin/customer/detail/${tableList.id}"/>'><i class="fa fa-external-link" aria-hidden="true"></i></a>
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
                        <span>Mã khách hàng: ${tableList.id}</span>
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

    $('#btnAdd').click(function (event) {
        event.preventDefault();
        users += ($('#userName').val()+",");

    });

    $('#btnSave').click(function (event) {
        event.preventDefault();
        //var id = $('#tableList.id')
        updateCustomer(users, 3);
    });

    function updateCustomer(data, id) {
        $.ajax({
            url: '${API}/user/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/customer/list?message=update_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer/list?message=error_system'/>";
            }
        });
    }

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteCustomer(dataArray);
        });
    }
    function deleteCustomer(data) {
        $.ajax({
            url: '${deleteAPI}',
            type: 'DELETE',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/user/list?message=delete_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/user/list?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>