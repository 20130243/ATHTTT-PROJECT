package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.db.JDBIConnector;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderDAO extends RD {
    private static final String tableName = "orders";

    @Override
    public List<Map<String, Object>> getAll() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT * FROM " + tableName + " ORDER BY id DESC ")
                        .mapToMap()
                        .list());
    }

    @Override
    public Map<String, Object> getById(int id) {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT * FROM " + tableName + " WHERE id = ? ")
                        .bind(0, id)
                        .mapToMap()
                        .first());
    }

    @Override
    public void delete(int id) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("DELETE FROM " + tableName + " WHERE id=:id")
                        .bind("id", id)
                        .execute()
        );
    }

    public static void insert(int user_id, String name, String phone, String address, String note, int coupon_id, float total, String hash_message,int key_id) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("INSERT INTO " + tableName + "(user_id,name,phone,address,note,coupon_id,total,hash_message,key_id) VALUES(:user_id,:name,:phone,:address,:note,:coupon_id,:total,:hash_message,:key_id)")
                        .bind("user_id", user_id)
                        .bind("name", name)
                        .bind("phone", phone)
                        .bind("address", address)
                        .bind("note", note)
                        .bind("coupon_id", coupon_id == 0 ? null : coupon_id)
                        .bind("total", total)
                        .bind("hash_message", hash_message)
                        .bind("key_id", key_id)
                        .execute()
        );
    }
    // update logistic query
    public void insertOrderLogistic(int orderId, String logisticId) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("INSERT INTO `order_logistic`"  + "(order_id,logistic_id) VALUES(:order_id,:logistic_id)")
                        .bind("order_id", orderId)
                        .bind("logistic_id", logisticId)

                        .execute()
        );
    }
    public String getOrderLogisticId(int orderId) {
        String rs = "";
        int a = JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT COUNT(*) FROM `order_logistic`" + " WHERE order_id= ?")
                        .bind(0, orderId)
                        .mapTo(Integer.class).first());
        if (a>0){
            Map<String, Object> result= JDBIConnector.get().withHandle(h ->
                    h.createQuery("SELECT * FROM `order_logistic`"+ " WHERE order_id = ? ")
                            .bind(0, orderId)
                            .mapToMap().first()
            );
            rs = result.get("logistic_id").toString();
        }

        return rs;
    }
    // end update logistic

    public void update(int id, String name, String phone, String address, String note, int coupon_id, float total) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("UPDATE " + tableName + " SET id = :id, name = :name,phone=:phone,address=:address,note=:note,coupon_id=: coupon_id,total=:total WHERE id =:id")
                        .bind("id", id)
                        .bind("name", name)
                        .bind("phone", phone)
                        .bind("address", address)
                        .bind("note", note)
                        .bind("coupon_id", coupon_id)
                        .bind("total", total)
                        .execute()

        );
    }

    public int getTotal() {
        int count = JDBIConnector.get().withHandle(h ->
                h.createQuery("select count(*) from " + tableName + "").mapTo(Integer.class).first()
        );
        return count;
    }


    public List<Map<String, Object>> paging(int index) throws SQLException {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("select * from " + tableName + " order by id DESC LIMIT ? , 10;")
                        .bind(0, (index - 1) * 10)
                        .mapToMap()
                        .list()
        );
    }


    public void updateStatus(int id, int status) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("UPDATE " + tableName + " SET status = :status WHERE id = :id")
                        .bind("status", status)
                        .bind("id", id)
                        .execute());
    }

    public void updateCoupon(int id, int couponID) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("UPDATE " + tableName + " SET coupon_id = :coupon_id WHERE id = :id")
                        .bind("coupon_id", couponID)
                        .bind("id", id)
                        .execute());
    }

    public Map<String, Object> findFirst() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT * FROM " + tableName + " ORDER BY id DESC LIMIT 1")
                        .mapToMap().first());
    }

    public List<Map<String, Object>> getOrderByUser(int userID) {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT * from " + tableName + " \n" +
                                "WHERE user_id = :user_id ORDER BY id DESC")
                        .bind("user_id", userID)
                        .mapToMap()
                        .list());
    }

    public Map<String, Object> getOrderByUserAndOrder(int userID, int orderID) {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT * from " + tableName + " \n" +
                                "WHERE user_id = :user_id AND id=:order_id")
                        .bind("user_id", userID)
                        .bind("order_id", orderID)
                        .mapToMap()
                        .first());
    }

    public Map<String, Object> getCouponIdByOrder(int orderId) {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT coupon_id FROM " + tableName + " WHERE id = :id")
                        .bind("id", orderId)
                        .mapToMap().first());
    }

    public int countCustomer() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  COUNT(DISTINCT user_id) from " + tableName + "WHERE   status = 3")
                        .mapTo(Integer.class).first());
    }

    public int countCustomerThisMonth() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  COUNT(DISTINCT user_id) from  " + tableName + " WHERE " +
                                "YEAR(time) = YEAR(CURRENT_DATE)\n" +
                                "AND MONTH(time) = MONTH(CURRENT_DATE) AND status = 3")
                        .mapTo(Integer.class).first());
    }

    public int countCustomerPreMonth() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  COUNT(DISTINCT user_id) from " + tableName + " WHERE " +
                                "YEAR(time) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH)\n" +
                                "AND MONTH(time) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) AND status = 3")
                        .mapTo(Integer.class).first());
    }

    public int countOrder() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  COUNT(DISTINCT id) from " + tableName + " WHERE  status = 3")
                        .mapTo(Integer.class).first());
    }

    public int countOrderThisMonth() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  COUNT(DISTINCT id) from " + tableName + " WHERE " +
                                "YEAR(time) = YEAR(CURRENT_DATE)\n" +
                                "AND MONTH(time) = MONTH(CURRENT_DATE)  AND status = 3")
                        .mapTo(Integer.class).first());
    }

    public int countOrderPreMonth() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  COUNT(DISTINCT id) from " + tableName + " WHERE " +
                                "YEAR(time) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH)\n" +
                                "AND MONTH(time) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH)  AND status = 3")
                        .mapTo(Integer.class).first());
    }

    public float sumTotalThisMonth() {
        String result = JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  SUM(total) from " + tableName + " WHERE " +
                                "YEAR(time) = YEAR(CURRENT_DATE)\n" +
                                "AND MONTH(time) = MONTH(CURRENT_DATE)  AND status = 3")
                        .mapTo(String.class).first());

        return result != null ? Float.parseFloat(result) : 0;
    }

    public float sumTotalPreMonth() {
        String result = JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  SUM(total) from " + tableName + " WHERE " +
                                "YEAR(time) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH)\n" +
                                "AND MONTH(time) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH)  AND status = 3")
                        .mapTo(String.class).first());

        return result != null ? Float.parseFloat(result) : 0;
    }

    public float sumTotalThisWeek() {
        float result = 0;
        for (float f : perDayThisWeek()) {
            result += f;
        }
        return result;
    }


    public float sumTotalPreWeek() {
        float result = 0;
        for (float f : perDayPreWeek()) {
            result += f;
        }
        return result;
    }

    public float sumTotalToday() {
        String result = JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT  SUM(total) from " + tableName + " WHERE DATE(time) = CURDATE()  AND status = 3")
                        .mapTo(String.class).first()); 
        return result != null ? Float.parseFloat(result) : 0;
    }

    public List<Map<String, Object>> top5Product() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT products.id,products.name,SUM(order_detail.quantity) as quantity \n" +
                                "FROM order_detail JOIN product_size on product_size.id= order_detail.product_size_id  \n" +
                                "JOIN  " + tableName + " on  " + tableName + ".id= order_detail.order_id \n" +
                                "JOIN products on products.id=product_size.product_id \n" +
                                "WHERE  " + tableName + ".time BETWEEN NOW() - INTERVAL 30 DAY AND NOW()  AND " + tableName + ".status = 3 \n" +
                                "GROUP BY products.id,products.name\n" +
                                "ORDER BY SUM(order_detail.quantity) DESC\n" +
                                "LIMIT 5")
                        .mapToMap()
                        .list());
    }

    public List<Map<String, Object>> totalCategory() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT categories.id,categories.name,SUM(" + tableName + ".total)as total\n" +
                                "FROM order_detail JOIN product_size on product_size.id= order_detail.product_size_id  \n" +
                                "JOIN " + tableName + " on " + tableName + ".id= order_detail.order_id \n" +
                                "JOIN products on products.id=product_size.product_id \n" +
                                "JOIN categories ON categories.id= products.category_id\n" +
                                "WHERE " + tableName + ".time BETWEEN NOW() - INTERVAL 30 DAY AND NOW()  AND " + tableName + ".status = 3 \n" +
                                "GROUP BY categories.id,categories.name")
                        .mapToMap()
                        .list());
    }

    public List<Map<String, Object>> listNewOrder() {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT * FROM " + tableName + " ORDER BY time DESC LIMIT 5")
                        .mapToMap()
                        .list());
    }

    public List<Float> perDayThisWeek() {
        List<Float> result = new ArrayList<Float>();
        String str = new SimpleDateFormat("u").format(new Date());
        if (str.equals("7")) {
            String[] dayName = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            for (String day : dayName) {
                String number = JDBIConnector.get().withHandle(h ->
                        h.createQuery("SELECT SUM(total) FROM " + tableName + " WHERE  YEARWEEK(time) = YEARWEEK(NOW()- INTERVAL 1 WEEK) AND DAYNAME(time) like '" + day + "' AND status = 3")
                                .mapTo(String.class).first());
                result.add(number != null ? Float.parseFloat(number) : 0);
            }
            String number = JDBIConnector.get().withHandle(h ->
                    h.createQuery("SELECT SUM(total) FROM " + tableName + " WHERE  YEARWEEK(time) = YEARWEEK(NOW()) AND DAYNAME(time) like '" + "Sunday" + "' AND status = 3")
                            .mapTo(String.class).first());
            result.add(number != null ? Float.parseFloat(number) : 0);
        } else {
            String[] dayName = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            for (String day : dayName) {
                String number = JDBIConnector.get().withHandle(h ->
                        h.createQuery("SELECT SUM(total) FROM " + tableName + " WHERE  YEARWEEK(time) = YEARWEEK(NOW()) AND DAYNAME(time) like '" + day + "' AND status = 3")
                                .mapTo(String.class).first());
                result.add(number != null ? Float.parseFloat(number) : 0);
            }
            result.add((float) 0);
        }
        return result;
    }

    public List<Float> perDayPreWeek() {
        List<Float> result = new ArrayList<Float>();

        String str = new SimpleDateFormat("u").format(new Date());
        if (str.equals("7")) {
            String[] dayName = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            for (String day : dayName) {
                String number = JDBIConnector.get().withHandle(h ->
                        h.createQuery("SELECT SUM(total) FROM " + tableName + " WHERE  YEARWEEK(time) = YEARWEEK(NOW()- INTERVAL 2 WEEK) AND DAYNAME(time) like '" + day + "' AND status = 3")
                                .mapTo(String.class).first());
                result.add(number != null ? Float.parseFloat(number) : 0);
            }
            String number = JDBIConnector.get().withHandle(h ->
                    h.createQuery("SELECT SUM(total) FROM " + tableName + " WHERE  YEARWEEK(time) = YEARWEEK(NOW()- INTERVAL 1 WEEK) AND DAYNAME(time) like '" + "Sunday" + "' AND status = 3")
                            .mapTo(String.class).first());
            result.add(number != null ? Float.parseFloat(number) : 0);
        } else {
            String[] dayName = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            for (String day : dayName) {
                String number = JDBIConnector.get().withHandle(h ->
                        h.createQuery("SELECT SUM(total) FROM " + tableName + " WHERE  YEARWEEK(time) = YEARWEEK(NOW() - INTERVAL 1 WEEK) AND DAYNAME(time) like '" + day + "' AND status = 3")
                                .mapTo(String.class).first());
                result.add(number != null ? Float.parseFloat(number) : 0);
            }
            String number = JDBIConnector.get().withHandle(h ->
                    h.createQuery("SELECT SUM(total) FROM " + tableName + " WHERE  YEARWEEK(time) = YEARWEEK(NOW()) AND DAYNAME(time) like '" + "Sunday" + "' AND status = 3")
                            .mapTo(String.class).first());
            result.add(number != null ? Float.parseFloat(number) : 0);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new OrderDAO().getOrderLogisticId(12));
    }
}
