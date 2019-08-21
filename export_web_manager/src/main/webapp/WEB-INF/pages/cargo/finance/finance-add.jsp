<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<script>
    function getPackingListId(value) {
        $("#invoiceId").val(value);
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            财务管理
            <small>生成财务报运单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">财务管理</a></li>
            <li class="active">生成财务报运单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--委托单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">新增财务报运单</div>
            <form id="editForm" action="${ctx}/cargo/finance/edit.do" method="post" enctype="multipart/form-data">
                <input type="hidden" name="shippingOrderId" value="${finance.financeId}">
                <input type="hidden" name="invoiceId" value="" id="invoiceId">
                <%--<input type="hidden" name="factoryName" id="factoryName" value="${shipping.factoryName}">--%>
                <div class="row data-type" style="margin: 0px">

                    <div class="col-md-2 title">制单日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="制单日期"  name="inputDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${finance.inputDate}" pattern="yyyy-MM-dd"/>" id="inputDate">
                        </div>
                    </div>

                    <div class="col-md-2 title">制单人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="制单人" name="inputBy" value="${finance.inputBy}">
                    </div>

                    <%--<div class="col-md-2 title">报运单状态</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="报运单状态" name="state" value="${finance.state}">
                    </div>--%>

                    <div class="col-md-2 title">创建人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建人" name="createBy" value="${finance.createBy}">
                    </div>

                    <div class="col-md-2 title">创建部门</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建部门" name="createDept" value="${finance.createDept}">
                    </div>

                    <div class="col-md-2 title">创建日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="创建日期"  name="createTime" class="form-control pull-right"
                                   value="<fmt:formatDate value="${finance.createTime}" pattern="yyyy-MM-dd"/>" id="createTime">
                        </div>
                    </div>

                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">发票列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                            <th class="sorting">报运合同</th>
                            <th class="sorting">提单号</th>
                            <th class="sorting">贸易条款</th>
                            <th class="sorting">创建人</th>
                            <th class="sorting">创建部门</th>
                            <th class="sorting">创建日期</th>
                            <th class="sorting">状态</th>
                            <%--<th class="text-center">操作</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page}" var="invoice" varStatus="status">
<%--                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >--%>
                                <td><input type="radio" name="id" value="${invoice.invoiceId}" onclick="getInvoiceId(this.value)"/></td>
                                <td>${invoice.scNo}</td>
                                <td>${invoice.blNo}</td>
                                <td>${invoice.tradeTerms}</td>
                                <td>${invoice.createBy}</td>
                                <td>${invoice.createDept}</td>
                                <td><fmt:formatDate value="${invoice.createTime}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <c:if test="${invoice.state==0}">草稿</c:if>
                                    <c:if test="${invoice.state==1}"><font color="green">已装箱</font></c:if>

                                </td>
                               <%-- <td>
                                    <a href="${ctx }/cargo/export/toView.do?id=${invoice.packingListId}">[查看]</a>
                                    <a href="${ctx }/cargo/packing/toUpdate.do?id=${invoice.packingListId}">[修改]</a>&ndash;%&gt;
                                        &lt;%&ndash;<c:if test="${invoice.state==1}">&ndash;%&gt;
                                        </c:if>&ndash;%&gt;&ndash;%&gt;
                               </td>&ndash;%&gt;&ndash;%&gt;--%>
                            </tr>--%>
                        </c:forEach>--%>
                        </tbody>
                    </table>
                    <!--数据列表/-->
                    <!--工具栏/-->
                </div>--%>
                <!-- 数据表格 /-->
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="/cargo/finance/list.do?contractId=${contractId}" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->
        </div>

    </section>

</div>
<!-- 内容区域 /-->
</body>
<script>
    $('#loadingDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#limitDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>