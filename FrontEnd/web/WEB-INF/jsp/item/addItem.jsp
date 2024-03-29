<%--
  Created by IntelliJ IDEA.
  User: Jaime
  Date: 31/03/2015
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../layout/top.jsp" />
<jsp:include page="../layout/menu.jsp" />
<h2 style="margin-top: 40px;">New Item</h2>
<form action="item?add" method="post" class="form" role="form">
  <table style="width:25%; margin-top: 40px;">
    <tr>
      <td><input type="radio" name="type" value="trees" checked>Tree</td>
      <td><input type="radio" name="type" value="seeds">Seeds</td>
      <td><input type="radio" name="type" value="shrubs">Shrubs</td>
    </tr>
  </table>
  <table style="width:30%; margin-top: 20px;">
    <tr>
      <td>Code:</td>
      <td><input type="text" class="form-control" name="code" style="width: 170px"></td>
    </tr>
    <tr>
      <td>Description:</td>
      <td><input type="text" class="form-control" name="desc" style="width: 170px"></td>
    </tr>
    <tr>
      <td>Price:</td>
      <td>
        <input type="number" class="form-control" step="0.01" name="price" min="0" style="width: 170px">
      </td>
    </tr>
    <tr>
      <td>Quantity:</td>
      <td><input type="number" class="form-control" name="quantity" min="0" style="width: 170px"></td>
    </tr>
  </table>
  <button type="submit" class="btn btn-lg btn-primary btn-block" name="save" style="width:100px; margin-top:30px;">Save</button>
</form>
<!--<a href="listItems.html" class="btn btn-lg btn-primary btn-block" style="width:150px; float:right; margin-top:20px;">Cancel</a>-->
<jsp:include page="../layout/footer.jsp" />