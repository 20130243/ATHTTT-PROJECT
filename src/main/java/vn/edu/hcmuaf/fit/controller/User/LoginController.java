package vn.edu.hcmuaf.fit.controller.User;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.services.KeyService;
import vn.edu.hcmuaf.fit.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(name = "Login", value = "/login")
public class LoginController extends HttpServlet {

    private int loginAttempts = 0;
    private static final int MAX_LOGIN_ATTEMPTS = 150;
    private static final int LOCK_TINE_IN_MINUTES = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login-register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        UserService userService = new UserService();
        KeyService keyService = new KeyService();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);


        if (!(loginAttempts >= MAX_LOGIN_ATTEMPTS)) {
            // login thanh cong
            if (user != null) {
                userService.logLogin(user.getId(),request.getRemoteAddr(),"LOGIN PAGE");
                userService.updateToken(user);
                String save = request.getParameter("save");
                Cookie cToken = new Cookie("tokenID", user.getToken());
                if (save != null) {
                    cToken.setMaxAge(60 * 60 * 24 * 7); // 7 days
                    response.addCookie(cToken);
                }
                loginAttempts = 0;
                if (user.getLevel() == -1) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("BannedAccount.jsp").forward(request, response);
                } else {
                    String checkPrivateKey = keyService.getPublicKeyByUserId(user.getId());
                    if(checkPrivateKey != null) {
                        // Đăng nhập
                        session.setAttribute("user", user);
                        session.setMaxInactiveInterval(24 * 60 * 60);
                        response.getWriter().write("2");
                    } else {
                        // Báo Key hết hạn
                        session.setAttribute("user", user);
                        session.setMaxInactiveInterval(24 * 60 * 60);
                        response.getWriter().write("3");
                    }

                }
                userService.logLogin(user.getId(),request.getRemoteAddr(), "LOGGIN_WEB");
            }
        }
    }
}
