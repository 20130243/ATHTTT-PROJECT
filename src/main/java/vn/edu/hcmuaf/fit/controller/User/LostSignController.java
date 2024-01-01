package vn.edu.hcmuaf.fit.controller.User;
import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.services.KeyService;
import vn.edu.hcmuaf.fit.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "LostSign", value = "/lostSign")

public class LostSignController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        KeyService keyService = new KeyService();
        String email = request.getParameter("emailCheck");
        User user = (User) session.getAttribute("user");

        if(email.equals(user.getEmail())) {
            // Tạo Key
            String privateKey = null;
            try {
                privateKey = keyService.createKey(user.getId());
                privateKey = keyService.formatPrivateKey(privateKey);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            File tempFile = File.createTempFile(user.getUsername() + "_private_key", ".key");
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(privateKey);
            } catch (IOException e) {
                throw new RuntimeException("Error writing private key to file", e);
            }
            String emailSubject = "Cấp Lại Private Key cho Tài Khoản Hahati Shop của Bạn";
            String emailBody = "Xin chào " + user.getName() + ",\n\n"
                    + "Chúng tôi nhận được yêu cầu của bạn về việc cấp lại private key cho tài khoản Hahati Shop.\n\n"
                    + "Dưới đây là private key mới cho tài khoản của bạn:\n\n"
                    + "Xin vui lòng giữ an toàn thông tin này và không chia sẻ với người khác.\n\n"
                    + "Nếu có bất kỳ vấn đề hoặc câu hỏi nào, hãy liên hệ với đội hỗ trợ của chúng tôi.\n\n"
                    + "Trân trọng,\n"
                    + "Đội ngũ Hahati Shop";

            UserService.sendMailFile(user.getEmail(), emailSubject, emailBody, tempFile.getAbsolutePath());

            response.getWriter().write("2");
        } else {
            response.getWriter().write("1");
        }
    }
}
