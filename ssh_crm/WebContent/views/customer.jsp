<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/themes/icon.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.1.2.6.min.js"></script>
	<script type="text/javascript" src='${pageContext.request.contextPath }/js/index.js'> </script>

<script type="text/javascript">
    $(function() {
    	$("#customerid").datagrid({
             url:"${pageContext.request.contextPath }/customer_customerPageJson.action", //返回json数据的action路径
             columns:[[
       					{field:'custName',title:'客户名称',width:200},
       					{field:'custSource',title:'客户来源',width:150},
       					{field:'custMobile',title:'客户电话',width:100}
       				 ]],
       	     pagination:true, //是否显示分页
       	     singleSelect:true
        });
    })
</script>
</head>
<body>
    <table id="customerid"></table>
</body>
</html>
