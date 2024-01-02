package vn.edu.hcmuaf.fit.controller.User;

import vn.edu.hcmuaf.fit.bean.*;
import vn.edu.hcmuaf.fit.services.CartOrderService;
import vn.edu.hcmuaf.fit.services.KeyService;
import vn.edu.hcmuaf.fit.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderController", value = "/order")
@MultipartConfig
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("checkout").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        CartOrderService cartOrderService = new CartOrderService();

        KeyService keyService = new KeyService();


        if (cart != null && user != null) {
            String nameUser = request.getParameter("nameUser");
            String phoneUser = request.getParameter("phoneUser");
            String addressUser = request.getParameter("addressUser");
            String addressCity = request.getParameter("addressCity");
            String addressDistrict = request.getParameter("addressDistrict");
            String addressWard = request.getParameter("addressWard");
            String noteUser = request.getParameter("noteUser");
            int priceLogistic = Integer.parseInt(request.getParameter("priceLogistic"));
            int total = (int) (cart.getTotalMoney() + priceLogistic);
            Key key = keyService.getKeyByUserId(user.getId());
            int key_id = key.getId();
            String address = addressUser + "-" + addressCity + "-" + addressDistrict + "-" + addressWard;

            String message = (nameUser.trim() + phoneUser.trim() + address.trim() + noteUser.trim() + total);
            String content_file;
            String key_text = request.getParameter("privatekey_text");
            if (key_text.isEmpty() || key_text.isBlank()) {
                Part filePart = request.getPart("fileInput");
                  content_file = keyService.readFile(filePart).trim();
            }else{
                content_file = key_text.trim()  ;
            }

            boolean verify = false;
            String sign = "";
            try {
                String hash_message = keyService.hashString(message);
                sign = keyService.sign(hash_message, KeyService.stringToPrivateKey(content_file));
                verify = keyService.verify(hash_message, sign, KeyService.stringToPublicKey(key.getPublicKey()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error occurred during sign or verify process: " + e.getMessage());
            }


            if (nameUser.equals("") || phoneUser.equals("") || addressUser.equals("")) {
//                System.out.println("sign: " + sign);
//                System.out.println("checkverify :" + verify);
                response.getWriter().write("1");
            } else {
                if (!verify) {
                    response.getWriter().write("3");
                } else {
                    System.out.println("sign1" + sign);
                    System.out.println("checkverify2 :" + verify);
                    Order order = new Order();
                    order.setUser_id(user.getId());
                    order.setName(nameUser);
                    order.setPhone(phoneUser);
                    order.setAddress(address);
                    order.setNote(noteUser);
                    List<Item> listItems = cart.getItems();
                    order.setListItems(listItems);
                    order.setCoupon(cart.getCoupon());
                    order.setTotal(cart.getTotalMoney() + priceLogistic);
                    order.setHash_message(sign);
                    order.setKey_id(key_id);
                    try {
                        cartOrderService.addOrder(order);
                        new OrderService().logOrder(cartOrderService.getOrderFirst().getId(), "user", user.getId(), 0);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    session.removeAttribute("cart");
                    response.getWriter().write("0");
                }
            }
        } else if (user == null) {
            response.getWriter().write("2");
        }
    }
}
