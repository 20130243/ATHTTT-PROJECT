package vn.edu.hcmuaf.fit.controller.admin.order;

import vn.edu.hcmuaf.fit.bean.Key;
import vn.edu.hcmuaf.fit.bean.Order;
import vn.edu.hcmuaf.fit.services.KeyService;
import vn.edu.hcmuaf.fit.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Order", value = "/admin/order")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String page = request.getParameter("page");
        int index = page == null ? 1 : Integer.parseInt(page);
        OrderService orderService = new OrderService();
        KeyService keyService = new KeyService();
        int count = orderService.getTotal();
        int endPage = count/10;
        if(count % 10 != 0) {
            endPage++;
        }

        List<Order> orderList = null;
        Map<Integer, Integer> verifyMap = new HashMap<>();
        try {
            orderList = orderService.getPaging(index);
            for (Order order: orderList ) {
                int id = order.getId();
                int total = (int) order.getTotal();
                int status = order.getStatus();
                String bill = order.getName().trim() + order.getPhone().trim() + order.getAddress().trim() + order.getNote().trim() + total;
                String hash_message = order.getHash_message() != null ? order.getHash_message().trim() : "" ;
                Key key = keyService.getKeyByUserId(order.getUser_id());
                String hash_bill = keyService.hashString(bill);
                boolean verify = keyService.verify(hash_bill,hash_message,keyService.convertPublicKey(key.getPublicKey(),"RSA"));
                if (status < 4 && !verify) {
                    orderService.updateStatus(order, 5);
                }
                verifyMap.put(id, (verify) ? 1 : 0);
            }
            orderList = orderService.getPaging(index);
            request.setAttribute("verifyMap",verifyMap);
            request.setAttribute("orderList", orderList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("endPage", endPage);
        if (!response.isCommitted()) request.getRequestDispatcher("order/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
