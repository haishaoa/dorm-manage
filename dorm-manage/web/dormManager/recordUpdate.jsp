<%--
  Created by IntelliJ IDEA.
  User: 爱你的嗨少
  Date: 2020/5/23
  Time: 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
    function checkForm(){
        var studentNumber = document.getElementById("studentNumber").value;
        var detail = document.getElementById("detail").value;
        if(studentNumber==""||detail==""){
            document.getElementById("error").innerHTML="信息填写不完整！";
            return false;
        }
        return true;
    }

    $(document).ready(function(){
        $("ul li:eq(2)").addClass("active");
    });
</script>
<div class="data_list">
    <div class="data_list_title">
        <c:choose>
            <c:when test="${student.studentId!=null }">
                修改缺勤记录
            </c:when>
            <c:otherwise>
                添加缺勤记录
            </c:otherwise>
        </c:choose>
    </div>
    <form action="RecordServlet?action=update&recordId=${record.recordId}" method="post" onsubmit="return checkForm()">
        <div class="data_form" >
            <input type="hidden" id="recordId" name="recordId" value="${record.recordId }"/>
            <table align="center">
                <tr>
                    <td><font color="red">*</font>学号：</td>

                    <c:choose>
                        <c:when test="${student.studentId!=null }">
                            <td><input readonly="readonly" type="text" id="studentNumber"  name="studentNumber" value="${student.studentId}"  style="margin-top:5px;height:30px;" ${studentNumber!=null?'readonly':'' }/></td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="text" id="studentNumber1"  name="studentNumber" value="${record.studentNumber==null?studentNumber:record.studentNumber }"  style="margin-top:5px;height:30px;" ${studentNumber!=null?'readonly':'' }/></td>
                        </c:otherwise>
                    </c:choose>

                </tr>
                <tr>
                    <td><font color="red">*</font>日期：</td>
                    <td><input type="text" id="date"  name="date" value="${record.date==null?date:record.date }"  style="margin-top:5px;height:30px;" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>备注：</td>
                    <td><input type="text" id="detail"  name="detail" value="${record.detail }"  style="margin-top:5px;height:30px;" /></td>
                </tr>
            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="保存"/>
                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='RecordServlet?action=list'">返回</button>
            </div>
            <div align="center">
                <font id="error" color="red">${error }</font>
            </div>
        </div>
    </form>
</div>
