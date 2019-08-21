<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>工厂管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">编辑工厂</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="/system/factory/edit.do" method="post">
                            <input type="hidden" name="id" value="${factory.id}">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">厂家类型</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="厂家类型" name="ctype" value="${factory.ctype}">
                                    </div>
                                    <div class="col-md-2 title">厂家全称</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="厂家全称" name="fullName" value="${factory.fullName}">
                                    </div>
                                    <div class="col-md-2 title">厂家简称</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="厂家简称" name="factoryName" value="${factory.factoryName}">
                                    </div>
                                    <div class="col-md-2 title">联系人</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="联系人" name="contacts" value="${factory.contacts}">
                                    </div>
                                    <div class="col-md-2 title">电话</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="电话" name="phone" value="${factory.phone}">
                                    </div>
                                    <div class="col-md-2 title">手机</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="手机" name="mobile" value="${factory.mobile}">
                                    </div>
                                    <div class="col-md-2 title">传真</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="传真" name="fax" value="${factory.fax}">
                                    </div>
                                    <div class="col-md-2 title">地址</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="地址" name="address" value="${factory.address}">
                                    </div>
                                    <div class="col-md-2 title">验货员</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="验货员" name="inspector" value="${factory.inspector}">
                                    </div>

                                    <div class="col-md-2 title">说明</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="说明" name="remark" value="${factory.remark}">
                                    </div>
                                    <div class="col-md-2 title">排序号</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="排序号" name="orderNo" value="${factory.orderNo}">
                                    </div>
                                    <%--<div class="col-md-2 title">上级工厂</div>--%>
                                   <%-- <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="parent.id">
                                            <option value="">请选择</option>
                                            <c:forEach items="${factory}" var="item">
                                                <option ${factory.parent.id == item.id ?'selected':''} value="${item.id}">${item.factoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>--%>
                                    <div class="col-md-2 title">状态</div>
                                    <div class="col-md-10 data">
                                        <div class="form-group form-inline">
                                            <div class="radio"><label><input type="radio" ${factory.state==0?'checked':''} name="state" value="0">停用</label></div>
                                            <div class="radio"><label><input type="radio" ${factory.state==1?'checked':''} name="state" value="1">启用</label></div>
                                        </div>
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>

</html>