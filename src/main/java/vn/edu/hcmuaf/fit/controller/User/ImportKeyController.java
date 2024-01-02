package vn.edu.hcmuaf.fit.controller.User;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.services.KeyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ImportKey", value = "/importkey")
@MultipartConfig
public class ImportKeyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String typeImport = req.getParameter("import-type");
        User user = (User) session.getAttribute("user");
        KeyService keyService = new KeyService();
        if (typeImport.equals("text")) {
            String publicKeyInput = req.getParameter("public-key-text");
            if (keyService.checkExist(publicKeyInput)) {
                resp.getWriter().write("-1");
            }else{
                Part pvk = req.getPart("private-key-old");

                try (InputStream inputStream = pvk.getInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                    StringBuilder privateKeyBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        privateKeyBuilder.append(line);
                    }
                    String privateKey = privateKeyBuilder.toString().trim()
                            .replace("-----BEGIN PRIVATE KEY-----", "")
                            .replace("-----END PRIVATE KEY-----", "");
                    String message = user.toString();
                    String messageEncrypt = keyService.encrypt(message, keyService.getKeyByUserId(user.getId()).getPublicKey());
                    String sign = keyService.sign(messageEncrypt, KeyService.stringToPrivateKey(privateKey));
                    boolean verify = keyService.verify(messageEncrypt, sign, KeyService.stringToPublicKey(keyService.getKeyByUserId(user.getId()).getPublicKey()));

                    if (verify) {
                        boolean importKey = keyService.checkRSAPublicKeyByText(user.getId(), publicKeyInput);
                        if (importKey == true) {
                            resp.getWriter().write("1");
                        } else {
                            resp.getWriter().write("-1");
                        }
                    } else {
                        resp.getWriter().write("2");
                    }
                } catch (Exception e) {
                    resp.getWriter().write("3");
                }
            }



        }
        if (typeImport.equals("file")) {
            Part filePart = req.getPart("public-key-file");
            Part pvk = req.getPart("private-key-old");

            try (InputStream puk = filePart.getInputStream();
                 BufferedReader rd = new BufferedReader(new InputStreamReader(puk, StandardCharsets.UTF_8))) {
                StringBuilder publicBuider = new StringBuilder();
                String pbk;
                while ((pbk = rd.readLine()) != null) {
                    publicBuider.append(pbk);
                }
                if (!keyService.checkExist(publicBuider.toString().trim())) {
                    resp.getWriter().write("-1");
                } else {
                    try (InputStream inputStream = pvk.getInputStream();
                         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                        StringBuilder privateKeyBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            privateKeyBuilder.append(line);
                        }
                        String privateKey = privateKeyBuilder.toString().trim()
                                .replace("-----BEGIN PRIVATE KEY-----", "")
                                .replace("-----END PRIVATE KEY-----", "");
                        String message = user.toString();
                        String messageEncrypt = keyService.encrypt(message, keyService.getKeyByUserId(user.getId()).getPublicKey());
                        String sign = keyService.sign(messageEncrypt, KeyService.stringToPrivateKey(privateKey));
                        boolean verify = keyService.verify(messageEncrypt, sign, KeyService.stringToPublicKey(keyService.getKeyByUserId(user.getId()).getPublicKey()));

                        if (verify) {
                            boolean importKey = keyService.checkRSAPublicKeyByFile(user.getId(), filePart);
                            if (importKey == true) {
                                resp.getWriter().write("1");
                            } else {
                                resp.getWriter().write("-1");
                            }
                        } else {
                            resp.getWriter().write("2");
                        }
                    } catch (Exception e) {
                        resp.getWriter().write("3");
                    }
                }
            }


        }
    }
}
