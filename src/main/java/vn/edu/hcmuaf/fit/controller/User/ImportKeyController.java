package vn.edu.hcmuaf.fit.controller.User;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.services.KeyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ImportKey", value = "/importkey")
@MultipartConfig
public class ImportKeyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String typeImport = req.getParameter("import-type");
        User user = (User)  session.getAttribute("user");
        KeyService keyService = new KeyService();
        if(typeImport.equals("text")) {
            String publicKeyInput = req.getParameter("public-key-text");
            try {
                boolean importKey = keyService.checkRSAPublicKeyByText(user.getId(), publicKeyInput);
                if (importKey == true) {
                    resp.getWriter().write("1");
                } else {
                    resp.getWriter().write("-1");
                }
            } catch (Exception e) {
                resp.getWriter().write("3");
            }


        } if(typeImport.equals("file")) {
            Part filePart = req.getPart("public-key-file");
            try {
                boolean importKey = keyService.checkRSAPublicKeyByFile(user.getId(), filePart);
                if (importKey == true) {
                    resp.getWriter().write("1");
                } else {
                    resp.getWriter().write("-1");
                }
            } catch (Exception e) {
                resp.getWriter().write("3");
            }
        }
    }
}
