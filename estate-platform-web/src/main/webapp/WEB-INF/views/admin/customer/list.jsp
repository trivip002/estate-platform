<%@ page import="com.estate.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tag.jsp"%>
<c:url var="formUrl" value="/admin/customer/list"/>
<c:url var="formAPI" value="/api/admin/customer"/>
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
                    <li class="active">Danh sách bài viết</li>
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
                        <security:authorize access="hasRole('ADMIN')">
                            <div class="row">
                                <div class="col-xs-12">s
                                    <div class="table-btn-controls">
                                        <div class="pull-right tableTools-container">
                                            <div class="dt-buttons btn-overlap btn-group">
                                                <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                                   data-toggle="tooltip" title="Thêm bài khách hàng mới" href='<c:url value="/admin/customer/edit"/>'>
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
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </security:authorize>
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
                                        <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                                        <display:column headerClass="col-actions" title="Hợp đồng">
                                            <a class="btn btn-sm btn-primary btn-success" data-toggle="tooltip" id = "btnChange_${tableList.id}" onclick="changeStatus(${tableList.id})"
                                               title="Chốt hợp đồng" ><i class="fa fa-plus center" aria-hidden="true"></i>
                                            </a>
                                        </display:column>
                                        <display:column headerClass="col-actions" title="Thao tác">
                                            <a class="btn btn-sm btn-primary" data-toggle="tooltip"
                                               title="Chi tiết khách hàng " href='<c:url value="/admin/customer/${tableList.id}/detail"/>'><i class="fa fa-search-plus bigger" aria-hidden="true"></i>
                                            </a>
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Cập nhật khách hàng" href='<c:url value="/admin/customer/edit?id=${tableList.id}"/>'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </a>
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
    function changeStatus(id) {
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
                alert("Đăng ký hợp đồng thất bại thất bại");
            }
        });
    }

    $('#btnSearch').click(function () {
        $('#listForm').submit();
    });

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteUser(dataArray);
        });
    }
    function deleteUser(data) {
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
