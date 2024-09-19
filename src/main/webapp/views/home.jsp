<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>trang chu cua user</title>
</head>
<body>
	<h2>Trang chủ của user</h2>
	
	<c:choose>
		<c:when test="${sessionScope.account != null}">
            <!-- Hiển thị nút đăng xuất khi người dùng đã đăng nhập -->
            <p>Xin chào, <c:out value="${sessionScope.account.fullname}"/></p>
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" value="Đăng Xuất">
            </form>
        </c:when>
	</c:choose>
</body>
</html>