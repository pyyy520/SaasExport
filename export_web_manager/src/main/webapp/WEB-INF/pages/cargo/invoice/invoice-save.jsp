<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            新增发票
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">新增发票</a></li>
            <li class="active">发票表单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">新增发票</div>
            <form id="editForm" action="${ctx}/cargo/invoice/edit.do" method="post">
                <input type="hidden" name="invoiceId" value="${invoice.invoiceId}">

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">报运合同</div>
                    <div class="col-md-4 data">
                        <input type="text"  name="scNo" disabled=disabled class="form-control" value="${invoice.scNo}">
                    </div>
                    <div class="col-md-2 title">提单号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="提单号" name="blNo" value="${invoice.blNo}">
                    </div>

                    <div class="col-md-2 title">贸易条款</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="贸易条款" name="tradeTerms" value="${invoice.tradeTerms}">
                    </div>

                    <div class="col-md-2 title">创建人</div>
                    <div class="col-md-4 data">

                            <input type="text" placeholder="创建人"  name="createBy" class="form-control pull-right"
                              value="${invoice.createBy}"/>

                    </div>
                    <div class="col-md-2 title">创建部门</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建部门" name="createDept" value="${invoice.createDept}">
                    </div>
                    <div class="col-md-2 title">创建日期</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建日期" name="createTime" value="<fmt:formatDate value="${invoice.createTime}" pattern="yyyy-MM-dd"/>">
                    </div>
                    <div class="box-tools text-center">
                        <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                    </div>

                    <div class="table-box">
                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right:0px;"></th>
                                <td class="sorting">运输</td>
                                <td class="sorting">货主</td>
                                <td class="sorting">提单</td>
                                <td class="sorting">通知人</td>
                                <td class="sorting">信用证</td>
                                <td class="sorting">装运港</td>
                                <td class="sorting">转船港</td>
                                <td class="sorting">卸货港</td>
                                <td class="sorting">装期</td>
                                <td class="sorting">效期</td>
                                <td class="sorting">分批</td>
                                <td class="sorting">转船</td>
                                <td class="sorting">份数</td>
                                <td class="sorting">说明</td>
                                <td class="sorting">要求</td>
                                <td class="sorting">运费</td>
                                <td class="sorting">复核人</td>
                                <td class="sorting">状态</td>
                                <td class="sorting">创建人</td>
                                <td class="sorting">部门</td>
                                <%--                            <td class="sorting">日期</td>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${shippingOrder}" var="ship" varStatus="status">
                                <tr>
                                    <td><input type="radio" name="id" onclick="checkAll(this.value)" value="${ship.shippingOrderId}"/></td>
                                    <td>${ship.orderType}</td>
                                    <td>${ship.shipper}</td>
                                    <td>${ship.consignee}</td>
                                    <td>${ship.notifyParty}</td>
                                    <td>${ship.lcNo}</td>
                                    <td>${ship.portOfLoading}</td>
                                    <td>${ship.portOfTrans}</td>
                                    <td>${ship.portOfDischarge}</td>
                                    <td><fmt:formatDate value="${ship.loadingDate}" pattern="yyyy-MM-dd"/></td>
                                    <td><fmt:formatDate value="${ship.limitDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${ship.isBatch}</td>
                                    <td>${ship.isTrans}</td>
                                    <td>${ship.copyNum}</td>
                                    <td>${ship.remark}</td>
                                    <td>${ship.specialCondition}</td>
                                    <td>${ship.freight}</td>
                                    <td>${ship.checkBy}</td>
                                    <td>
                                        <c:if test="${ship.state==0}">草稿</c:if>
                                        <c:if test="${ship.state==1}"><font color="green">已委托</font></c:if>
                                        <c:if test="${ship.state==2}"><font color="green">已生成发票</font></c:if>
                                    </td>
                                    <td>${ship.createBy}</td>
                                    <td>${ship.createDept}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!--数据列表/-->
                        <!--工具栏/-->
                    </div>
                </div>
          </form>
        </div>
        <!--订单信息/-->


        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#signingDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#deliveryPeriod').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#shipTime').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>