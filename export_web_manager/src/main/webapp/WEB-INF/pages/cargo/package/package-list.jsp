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
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/cargo/packing/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function submit() {
        var id = getCheckId()
        if(id) {
            $.post("/cargo/packing/pdSubmit.do","id="+id,function (d) {
                if (d==1){
                    location.href="${ctx}/cargo/packing/submit.do?id="+id;
                } else {
                    alert("状态为已装箱,不可以再提交")
                }
            })

        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function cancel() {
        var id = getCheckId()
        if(id) {
            $.post("/cargo/packing/pdCancel.do","id="+id,function (d) {
                if (d==1){
                    location.href="${ctx}/cargo/packing/cancel.do?id="+id;
                } else {
                    alert("只能取消:::状态为已装箱的，其他不行!")
                }
            })

        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function exportE() {
        var id = getCheckId()
        if(id) {
            location.href="${cx}/cargo/export/exportE.do?id="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        装箱单管理
        <small>装箱完喽</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">装箱单列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="提交" onclick='submit()'><i class="fa fa-file-o"></i> 提交</button>
                            <button type="button" class="btn btn-default" title="取消" onclick='cancel()'><i class="fa fa-file-o"></i> 取消</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                        <th class="sorting">装箱单号</th>
                        <th class="sorting">卖方</th>
                        <th class="sorting">买方</th>
                        <th class="sorting">发票号</th>
                        <th class="sorting">发票日期</th>
                        <th class="sorting">唛头</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="pack" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                            <td><input type="checkbox" name="id" value="${pack.packingListId}"/></td>
                            <td>${pack.packingListId}</td>
                            <td>${pack.seller}</td>
                            <td>${pack.buyer}</td>
                            <td>${pack.invoiceNo}</td>
                            <td><fmt:formatDate value="${pack.invoiceDate}" pattern="yyyy-MM-dd"/></td>
                            <td>${pack.marks}</td>
                            <%--<td>${pack.state}</td>--%>
                            <td>
                                <c:if test="${pack.state==0}">草稿</c:if>
                                <c:if test="${pack.state==1}"><font color="green">已装箱</font></c:if>
                                <c:if test="${pack.state==2}"><font color="green">已委托</font></c:if>

                            </td>
                            <td>
                                <a href="${ctx }/cargo/packing/toView.do?id=${pack.packingListId}">[查看]</a>
                                <a href="${ctx }/cargo/packing/toUpdate.do?id=${pack.packingListId}">[修改]</a>
                                <%--<c:if test="${pack.state==1}">
                                    <a href="/cargo/export/exportPdf.do?id=${o.id}">[下载]</a>
                                </c:if>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- /.box-body -->

        <!-- .box-footer-->
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="cargo/contract/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
        <!-- /.box-footer-->


    </div>

</section>
</div>
</body>

</html>