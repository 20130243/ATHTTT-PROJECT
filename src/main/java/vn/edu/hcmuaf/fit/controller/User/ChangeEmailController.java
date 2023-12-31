package vn.edu.hcmuaf.fit.controller.User;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.services.KeyService;
import vn.edu.hcmuaf.fit.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        String message = oldEmail + newEmail;

        Part filePart = req.getPart("private-key");

        try (InputStream inputStream = filePart.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            StringBuilder privateKeyBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                privateKeyBuilder.append(line);
            }
            String privateKey = privateKeyBuilder.toString().trim()
                    .replace("-----BEGIN PRIVATE KEY-----","")
                    .replace("-----END PRIVATE KEY-----","");
            User user = (User) session.getAttribute("user");
            if(user.getEmail().equals(oldEmail)) {

                KeyService keyService = new KeyService();
                UserService userService = new UserService();

                String messageEncrypt = keyService.encrypt(message, keyService.getKeyByUserId(user.getId()).getPublicKey());
                String sign = keyService.sign(messageEncrypt, KeyService.stringToPrivateKey(privateKey));
                boolean verify = keyService.verify(messageEncrypt, sign, KeyService.stringToPublicKey(keyService.getKeyByUserId(user.getId()).getPublicKey()));

                if (verify) {
                    user.setEmail(newEmail);
                    userService.update(user);
                    resp.getWriter().write("1");
                } else {
                    resp.getWriter().write("3");
                }
            } else {
                resp.getWriter().write("2");
            }

        }
        catch (Exception e) {
            System.out.println("Vao day 2");
            e.printStackTrace();
            throw new RuntimeException("Error occurred during sign or verify process: " + e.getMessage());
        }
    }

}
