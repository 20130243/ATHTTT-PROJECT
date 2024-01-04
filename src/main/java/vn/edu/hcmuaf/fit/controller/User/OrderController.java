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

            List<Item> listItems = cart.getItems();
//            String message = (nameUser.trim() + phoneUser.trim() + address.trim() + noteUser.trim() + listItems + total);

            String content_file;
            String key_text = request.getParameter("privatekey_text");
            if (key_text.isEmpty() || key_text.isBlank()) {
                Part filePart = request.getPart("fileInput");
                content_file = keyService.readFile(filePart).trim();
            } else {
                content_file = key_text.trim();
            }


            if (nameUser.equals("") || phoneUser.equals("") || addressUser.equals("")) {
//                System.out.println("sign: " + sign);
//                System.out.println("checkverify :" + verify);
                response.getWriter().write("1");
            } else
            {
                Order order = new Order();
                order.setUser_id(user.getId());
                order.setName(nameUser);
                order.setPhone(phoneUser);
                order.setAddress(address);
                order.setNote(noteUser);
//                    List<Item> listItems = cart.getItems();
                order.setListItems(listItems);
                order.setCoupon(cart.getCoupon());
                order.setTotal(cart.getTotalMoney() + priceLogistic);

                boolean verify = false;
                String sign = "";
                try {
                    String message = order.bill();
                    System.out.println(message);
                    String hash_message = keyService.hashString(message);
                    sign = keyService.sign(hash_message, KeyService.stringToPrivateKey(content_file));
                    verify = keyService.verify(hash_message, sign, KeyService.stringToPublicKey(key.getPublicKey()));
                    order.setHash_message(sign);
                    order.setKey_id(key_id);

                    if (!verify) {
                        response.getWriter().write("3");
                    } else {
                        System.out.println("sign1 " + sign);
                        System.out.println("checkverify2 :" + verify);

                        try {
                            cartOrderService.addOrder(order);
                            new OrderService().logOrder(cartOrderService.getOrderFirst().getId(), "user", user.getId(), 0);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        //NGUYEN HUY HOANG0981003797hu-79-777-27436[Item{id=0, product={id=129, name='Trà Xoài B??i H?ng', idCategory=1, priceSize=[PriceSize{id=110, product_id=110, size='M', price=5250.0, originalPrice=30000.0}], image=[Image{id=118, name='null', url='/img/product/products/TraXoaiBuoiHong.png', product_id=129, status=1}, Image{id=399, name='null', url='/img/product/products/phuc-long-1.jpg', product_id=129, status=1}], status=1, topping=[Topping{id=16, name='Espresso Shot', price=24000.0, status=0, category_id=1}]}, quantity=1, price=29250.0, note=''}]29250.0
                        //NGUYEN HUY HOANG0981003797hu-79-777-27436[Item{id=129, product={id=129, name='Trà Xoài B??i H?ng', idCategory=1, priceSize=[PriceSize{id=110, product_id=129, size='M', price=5250.0, originalPrice=30000.0}], image=[Image{id=118, name='null', url='/img/product/products/TraXoaiBuoiHong.png', product_id=129, status=1}, Image{id=399, name='null', url='/img/product/products/phuc-long-1.jpg', product_id=129, status=1}], status=1, topping=[Topping{id=16, name='Espresso Shot', price=24000.0, status=0, category_id=1}]}, quantity=1, price=29250.0, note=''}]29250.0
                        //NGUYEN HUY HOANG0981003797q?eq?e q?e-79-767-27019[Item{, product={id=126, name='Trà Xanh Gong Cha', idCategory=1, priceSize=[PriceSize{id=156, product_id=126, size='M', price=4800.0, originalPrice=38000.0}], image=[Image{id=115, name='null', url='/img/product/products/TraXanhGongCha.png', product_id=126, status=1}, Image{id=396, name='null', url='/img/product/products/phuc-long-1.jpg', product_id=126, status=1}], status=1, topping=[]}, quantity=1, price=4800.0, note=''}]4800.0
                        //NGUYEN HUY HOANG0981003797q?eq?e q?e-79-767-27019[Item{, product={id=126, name='Trà Xanh Gong Cha', idCategory=1, priceSize=[PriceSize{id=156, product_id=156, size='M', price=4800.0, originalPrice=38000.0}], image=[Image{id=115, name='null', url='/img/product/products/TraXanhGongCha.png', product_id=126, status=1}, Image{id=396, name='null', url='/img/product/products/phuc-long-1.jpg', product_id=126, status=1}], status=1, topping=[]}, quantity=1, price=4800.0, note=''}]4800.0
                        session.removeAttribute("cart");
                        response.getWriter().write("0");
                    }
                } catch (Exception e) {
                    response.getWriter().write("5");
                    e.printStackTrace();
                }



            }
        } else if (user == null) {
            response.getWriter().write("2");
        }
    }
}
