<%--
  Created by IntelliJ IDEA.
  User: Jaime
  Date: 01/04/2015
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../layout/top.jsp" />
<jsp:include page="../layout/menu.jsp" />


<c:forEach items="${clientList}" var="item">

</c:forEach>

<jsp:include page="../layout/footer.jsp" />