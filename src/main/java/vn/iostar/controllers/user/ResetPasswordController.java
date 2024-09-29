package vn.iostar.controllers.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserServices;
import vn.iostar.services.impl.UserService;


@WebServlet(urlPatterns = {"/reset_password"})
public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//lay toan bo ham trong service
		IUserServices service = new UserService();
		
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    // Lấy thông tin từ request
		    String email = req.getParameter("email");
		    
		    // Kiểm tra xem email có hợp lệ không (có thể thêm logic kiểm tra)
		    if (email == null || email.isEmpty()) {
		        req.setAttribute("alert", "Email không hợp lệ.");
		        req.getRequestDispatcher("/views/reset_password.jsp").forward(req, resp);
		        return;
		    }

		    // Kiểm tra xem người dùng có tồn tại trong hệ thống không
		    UserModel user = service.FindByEmail(email); // Giả sử bạn đã có phương thức findByEmail trong service

		    if (user != null) {
		        // Nếu người dùng tồn tại, chuyển đến trang reset password
		        req.setAttribute("userId", user.getId()); // Lưu ID người dùng để sử dụng trong doPost
		        req.getRequestDispatcher("/views/reset_password.jsp").forward(req, resp);
		    } else {
		        // Nếu không tìm thấy người dùng, hiển thị thông báo lỗi
		        req.setAttribute("alert", "Email không tồn tại trong hệ thống.");
		        req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
		    }
		}					
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userId"));
        String newPassword = req.getParameter("newPassword");
        // Tìm người dùng theo ID
        UserModel user = service.findById(userId);

        if (user != null) {
            // Cập nhật mật khẩu mới
            user.setPassword(newPassword);  // Nên mã hóa mật khẩu trước khi lưu vào DB
            service.update(user);

            // Thông báo thành công và chuyển hướng về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Thông báo lỗi nếu người dùng không tìm thấy
            req.setAttribute("alert", "User not found.");
            req.getRequestDispatcher("/views/user/reset_password.jsp").forward(req, resp);
        }
	}

}
