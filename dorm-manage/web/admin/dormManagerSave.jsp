<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var dormManagerId=document.getElementById("dormManagerId").value;
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		var rPassword=document.getElementById("rPassword").value;
		var dormBuildId=document.getElementById("dormBuildId").value;
		var name=document.getElementById("name").value;
		var sex=document.getElementById("sex").value;
		var tel=document.getElementById("tel").value;
		if(dormBuildId==""||dormManagerId==""||userName==""||password==""||rPassword==""||name==""||sex==""||tel==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		} else if(password!=rPassword){
			document.getElementById("error").innerHTML="密码填写不一致！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${dormManager.dormManagerId!=null }">
				修改管理员
			</c:when>
			<c:otherwise>
				添加管理员
			</c:otherwise>
		</c:choose>
		</div>
		<form action="DormManagerServlet?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<%--<input type="hidden" id="dormManagerId" name="dormManagerId" value="${dormManager.dormManagerId }"/>--%>
					<table align="center">
						<tr>
							<td><font color="red">*</font>编号：</td>
							<td><input type="text" id="dormManagerId"  name="dormManagerId" value="${dormManager.userName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>登录名：</td>
							<td><input type="text" id="userName"  name="userName" value="${dormManager.userName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>密码：</td>
							<td><input type="password" id="password"  name="password" value="${dormManager.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>重复密码：</td>
							<td><input type="password" id="rPassword"  name="rPassword" value="${dormManager.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>

						<tr>
							<td><font color="red">*</font>宿舍楼编号：</td>
							<td>
								<select id="dormBuildId" name="dormBuildId" style="width: 90px;">
									<c:forEach var="dormBuild" items="${dormBuildList}">
										<option id="dormBuildId1" value="${dormBuild.dormBuildId}" ${student.dormBuildId==dormBuild.dormBuildId?'selected':''}>${dormBuild.dormBuildId }</option>
									</c:forEach>
								</select>
							</td>
						</tr>

						<tr>
							<td><font color="red">*</font>真实姓名：</td>
							<td><input type="text" id="name"  name="name" value="${dormManager.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>性别：</td>
							<td>
								<select id="sex" name="sex" style="width: 90px;">
									<option value="男" ${dormManager.sex eq "男"?'selected':'' }>男</option>
									<option value="女" ${dormManager.sex eq "女"?'selected':'' }>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>联系电话：</td>
							<td><input type="text" id="tel"  name="tel" value="${dormManager.tel }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>