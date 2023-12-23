package vn.edu.hcmuaf.fit.controller.User;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.services.KeyService;
import vn.edu.hcmuaf.fit.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@WebServlet(name = "changeEmail", value = "/changeEmail")
@MultipartConfig
public class ChangeEmailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String oldEmail = req.getParameter("oldEmail");
        String newEmail = req.getParameter("newEmail");
        Part filePart = req.getPart("private-key");
        InputStream inputStream = filePart.getInputStream();
        byte[] privateKeyBytes = inputStream.readAllBytes();
        String privateKey = new String(privateKeyBytes, StandardCharsets.UTF_8);
        User user = (User) session.getAttribute("user");


        KeyService keyService = new KeyService();
        UserService userService = new UserService();
        try {
            String currentEmailEncrypt = keyService.encrypt(user.getEmail(), keyService.getKeyByUserId(user.getId()).getPublicKey());
            String currentEmailDecrypt = keyService.decrypt(currentEmailEncrypt, privateKey);
            if(oldEmail.equals(currentEmailDecrypt)) {
                user.setEmail(newEmail);
                userService.update(user);
                resp.getWriter().write("1");
            } else {
                resp.getWriter().write("2");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
