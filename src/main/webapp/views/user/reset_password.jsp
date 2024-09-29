<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset password</title>
</head>
<body>
<h2>Reset Password</h2>
    <form action="reset-password" method="post">
        <input type="hidden" name="userId" value="${userId}">
        <label for="newPassword">Enter New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required>
        <input type="submit" value="Reset Password">
    </form>
</body>
</html>