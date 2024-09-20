package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserServices;
import vn.iostar.services.impl.UserService;


@WebServlet(urlPatterns = {"/forgot_password"})
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//lay toan bo ham trong service
	IUserServices service = new UserService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // Kiểm tra xem người dùng đã đăng nhập chưa bằng session
	    HttpSession session = req.getSession(false);
	    if (session != null && session.getAttribute("username") != null) {
	        // Nếu người dùng đã đăng nhập, chuyển hướng họ về trang quản trị hoặc trang chính của người dùng
	        resp.sendRedirect(req.getContextPath() + "/home"); // hoặc /admin nếu là trang quản trị
	        return;
	    }

	    // Kiểm tra cookie nếu có
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("username")) {
	                // Nếu tìm thấy cookie "username", tạo session và chuyển hướng người dùng
	                session = req.getSession(true);
	                session.setAttribute("username", cookie.getValue());
	                resp.sendRedirect(req.getContextPath() + "/home");
	                return;
	            }
	        }
	    }

	    // Nếu người dùng chưa đăng nhập, hiển thị trang yêu cầu đặt lại mật khẩu
	    req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Email = req.getParameter("email");

        // Tìm người dùng dựa trên email hoặc tên người dùng
        UserModel user = service.FindByEmail(Email);

        if (user != null) {
            // Chuyển hướng tới trang đặt lại mật khẩu
            req.setAttribute("userId", user.getId());
            req.getRequestDispatcher("/reset-password.jsp").forward(req, resp);
        } else {
            // Thông báo không tìm thấy người dùng
            req.setAttribute("alert", "Email or Username not found.");
            req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
        }
	}

}
