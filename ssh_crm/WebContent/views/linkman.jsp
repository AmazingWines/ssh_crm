<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联系人信息管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/themes/icon.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.1.2.6.min.js"></script>
	<script type="text/javascript" src='${pageContext.request.contextPath }/js/index.js'> </script>

<script type="text/javascript">
    $(function() {
    	$("#linkmanid").datagrid({
             url:"${pageContext.request.contextPath }/linkman_linkmanJson.action", //返回json数据的action路径
             columns:[[
       					{field:'lkmName',title:'联系人名称',width:200},
       					{field:'lkmGender',title:'联系人性别',width:150},
       					{field:'lkmPhone',title:'联系人电话',width:100},
       					//{field:'customer.custName',title:'所属客户',width:100} //这种写法数据显示不出来，错误
       					{field:'customer',title:'所属客户',width:100,formatter:function(value,row,index){
	  		    		    if(row.customer){
	  		   				    return row.customer.custName;
	  		    		    }
	  		    	    }}
       				 ]],
       	     pagination:true, //是否显示分页
       	     singleSelect:true
        });
    })
</script>
</head>
<body>
    <table id="linkmanid"></table>
</body>
</html>
