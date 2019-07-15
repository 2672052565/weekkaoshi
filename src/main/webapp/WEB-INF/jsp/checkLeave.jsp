<%--
  Created by IntelliJ IDEA.
  User: join
  Date: 2019/7/13
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>编号</td>
        <td>开始时间</td>
        <td>结束时间</td>
        <td>原因</td>
        <td>审批</td>
    </tr>
    <c:forEach items="${list }" var="leave" varStatus="i">
        <tr>
            <td>${i.count}</td>
            <td>${leave.startDate}</td>
            <td>${leave.endDate}</td>
            <td>${leave.reason}</td>
            <td>
                <c:if test="${leave.state == 0}">
                    未通过
                </c:if>
                <c:if test="${leave.state == 1}">
                    已通过
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
