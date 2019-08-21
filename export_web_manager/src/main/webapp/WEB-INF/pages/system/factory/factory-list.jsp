<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %><!--因为要判断用户是否有配置的权限，所以引入这个标签-->
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
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="/system/factory/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        工厂信息
        <small>工厂管理</small>
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
            <h3 class="box-title">工厂列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="/system/factory/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>

                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>

                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
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
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">编号</th>
                        <th class="sorting">工厂公司名称</th>
                        <th class="sorting">工厂名称简写</th>
                        <th class="sorting">联系人</th>
                        <th class="sorting">座机</th>
                        <th class="sorting">手机号</th>
                        <th class="sorting">注册地址</th>
                        <th class="sorting">验货员</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="factory"  varStatus="st">
                        <tr>
                            <td><input type="checkbox" name="id" value="${factory.id }"/></td>
                            <td>${st.count }</td>
                            <td>${factory.fullName }</td>
                            <td>${factory.factoryName }</td>
                            <td>${factory.contacts }</td>
                            <td>${factory.phone }</td>
                            <td>${factory.mobile }</td>
                            <td>${factory.address }</td>
                            <td>${factory.inspector }</td>
                            <%--<td><a href="/system/factory/toUpdate.do?id=${factory.id }">${factory.factoryName }</a></td>--%>
                            <th class="text-center"><button type="button" class="btn bg-olive btn-xs" onclick='location.href="/system/factory/show.do?id=${factory.id}"'>查看</button>
                           <button type="button" class="btn bg-olive btn-xs" onclick='location.href="/system/factory/toUpdate.do?id=${factory.id}"'>编辑</button></th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/factory/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>