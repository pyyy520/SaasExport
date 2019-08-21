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
        $("#packingListId").val(value);
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>生成委托单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">生成委托单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--委托单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">新增委托单</div>
            <form id="editForm" action="${ctx}/cargo/shipping/edit.do" method="post" enctype="multipart/form-data">
                <input type="hidden" name="shippingOrderId" value="${shipping.shippingOrderId}">
                <input type="hidden" name="packingListId" value="" id="packingListId">
                <%--<input type="hidden" name="factoryName" id="factoryName" value="${shipping.factoryName}">--%>
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">份数</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="份数" name="copyNum" value="${shipping.copyNum}">
                    </div>

                    <div class="col-md-2 title">货主</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="货主" name="shipper" value="${shipping.shipper}">
                    </div>

                    <div class="col-md-2 title">提单抬头</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="提单抬头" name="consignee" value="${shipping.consignee}">
                    </div>

                    <div class="col-md-2 title">信用证</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="信用证" name="lcNo" value="${shipping.lcNo}">
                    </div>

                    <div class="col-md-2 title">运送方式</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${shipping.orderType=="海运"?'checked':''} name="orderType" value="海运">海运</label></div>
                            <div class="radio"><label><input type="radio" ${shipping.orderType=="空运"?'checked':''} name="orderType" value="空运">空运</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">装运港</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="装运港" name="portOfLoading" value="${shipping.portOfLoading}">
                    </div>

                    <div class="col-md-2 title">转船港</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="转船港" name="portOfTrans" value="${shipping.portOfTrans}">
                    </div>

                    <div class="col-md-2 title">卸货港</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="卸货港" name="portOfDischarge" value="${shipping.portOfDischarge}">
                    </div>
                    <div class="col-md-2 title">装货日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="装货日期"  name="loadingDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${shipping.loadingDate}" pattern="yyyy-MM-dd"/>" id="loadingDate">
                        </div>
                    </div>

                    <div class="col-md-2 title">有效日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="有效日期"  name="limitDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${shipping.limitDate}" pattern="yyyy-MM-dd"/>" id="limitDate">
                        </div>
                    </div>
                    <div class="col-md-2 title rowHeight2x">运输要求</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="运输要求" name="specialCondition">${shipping.specialCondition}</textarea>
                    </div>

                    <div class="col-md-2 title rowHeight2x">运费说明</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="运费说明" name="freight">${shipping.freight}</textarea>
                    </div><div class="col-md-2 title">是否分批</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="是否分批" name="isBatch" value="${shipping.isBatch}">
                    </div>

                    <div class="col-md-2 title">是否转船</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="是否转船" name="isTrans" value="${shipping.isTrans}">
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
        <%--<div class="box box-primary">--%>
            <%--<div class="box-header with-border">--%>
                <%--<h3 class="box-title">货物列表</h3>--%>
            <%--</div>--%>

            <%--<div class="box-body">--%>

                <%--<!-- 数据表格 -->--%>
                <%--<div class="table-box">--%>
                    <%--<!--数据列表-->--%>
                    <%--<table id="dataList" class="table table-bordered table-striped table-hover dataTable">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>--%>
                            <%--<th class="sorting">装箱单号</th>--%>
                            <%--<th class="sorting">卖方</th>--%>
                            <%--<th class="sorting">买方</th>--%>
                            <%--<th class="sorting">发票号</th>--%>
                            <%--<th class="sorting">发票日期</th>--%>
                            <%--<th class="sorting">唛头</th>--%>
                            <%--<th class="sorting">状态</th>--%>
                            <%--&lt;%&ndash;<th class="text-center">操作</th>&ndash;%&gt;--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<c:forEach items="${page.list}" var="pack" varStatus="status">--%>
                            <%--<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >--%>
                                <%--<td><input type="radio" name="id" value="${pack.packingListId}" onclick="getPackingListId(this.value)"/></td>--%>
                                <%--<td>${pack.packingListId}</td>--%>
                                <%--<td>${pack.seller}</td>--%>
                                <%--<td>${pack.buyer}</td>--%>
                                <%--<td>${pack.invoiceNo}</td>--%>
                                <%--<td><fmt:formatDate value="${pack.invoiceDate}" pattern="yyyy-MM-dd"/></td>--%>
                                <%--<td>${pack.marks}</td>--%>
                                    <%--&lt;%&ndash;<td>${pack.state}</td>&ndash;%&gt;--%>
                                <%--<td>--%>
                                    <%--<c:if test="${pack.state==0}">草稿</c:if>--%>
                                    <%--<c:if test="${pack.state==1}"><font color="green">已装箱</font></c:if>--%>

                                <%--</td>--%>
                               <%--&lt;%&ndash; <td>--%>
                                    <%--<a href="${ctx }/cargo/export/toView.do?id=${pack.packingListId}">[查看]</a>--%>
                                    <%--<a href="${ctx }/cargo/packing/toUpdate.do?id=${pack.packingListId}">[修改]</a>--%>
                                        <%--&lt;%&ndash;<c:if test="${pack.state==1}">--%>
                                            <%--<a href="/cargo/export/exportPdf.do?id=${o.id}">[下载]</a>--%>
                                        <%--</c:if>&ndash;%&gt;--%>
                                <%--</td>&ndash;%&gt;--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                    <%--<!--数据列表/-->--%>
                    <%--<!--工具栏/-->--%>
                <%--</div>--%>
                <%--<!-- 数据表格 /-->--%>
            <%--</div>--%>
            <%--<!-- /.box-body -->--%>

            <%--<!-- .box-footer-->--%>
            <%--<div class="box-footer">--%>
                <%--<jsp:include page="../../common/page.jsp">--%>
                    <%--<jsp:param value="/cargo/shipping/list.do?contractId=${contractId}" name="pageUrl"/>--%>
                <%--</jsp:include>--%>
            <%--</div>--%>
            <%--<!-- /.box-footer-->--%>
        <%--</div>--%>

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