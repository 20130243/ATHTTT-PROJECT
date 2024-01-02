package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.bean.Key;
import vn.edu.hcmuaf.fit.db.JDBIConnector;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class KeyDAO {
    String tableName = "keys";

//    public void create(String key, int userId, int status) {
//        JDBIConnector.get().withHandle(h ->
//                h.createUpdate("INSERT INTO `" + tableName + "`(public_key,user_id,status) VALUE (:key,:userId,:status) ")
//                        .bind("key", key)
//                        .bind("userId", userId)
//                        .bind("status", status)
//                        .execute()
//        );
//    }

    public void create(String key, int userId, int status) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime expiredAt = createdAt.plusMonths(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        JDBIConnector.get().withHandle(h ->
                h.createUpdate("INSERT INTO `" + tableName + "`(public_key, create_at, expired_at, user_id, status) VALUES (:key, :createdAt, :expiredAt, :userId, :status)")
                        .bind("key", key)
                        .bind("createdAt", Timestamp.valueOf(createdAt.format(formatter)))
                        .bind("expiredAt", Timestamp.valueOf(expiredAt.format(formatter)))
                        .bind("userId", userId)
                        .bind("status", status)
                        .execute()
        );
    }
    public void deleteById(int id) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("UPDATE `" + tableName + "` SET status = '-1',expired_at =NOW() WHERE id= :id")
                        .bind("id", id).execute()
        );
    }

    public void deleteByUserId(int userId) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("UPDATE `" + tableName + "` SET status = '-1',expired_at =NOW() WHERE user_id= :userId AND status = '0'")
                        .bind("userId", userId).execute()
        );
    }

    public Map<String, Object> getByUserId(int userId) {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT * from `" + tableName + "` WHERE user_id = :userId AND status = '0'  AND (expired_at is null OR expired_at > NOW())")
                        .bind("userId", userId)
                        .mapToMap()
                        .findOne()
                        .orElse(null));
    }



    public Map<String,Object> getByOrderId(int orderId) {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT k.* FROM `" + tableName + "` k JOIN orders o ON k.id = o.key_id WHERE o.id = :orderId")
                        .bind("orderId", orderId)
                        .mapToMap()
                        .findOne()
                        .orElse(null));
    }

    public static void main(String[] args) {
        KeyDAO k = new KeyDAO();
        System.out.println(k.checkExist("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg6Jv5e6iyahLYxDJKWzfChk6cXjkTFih3Vzl9RvyzYpeIF3htZ+vmiBUJ8RVwNA+2EdxFJ4pe1/RIURI+JGKELud3RB2Lr96TbHU0AV2IMmTtO73nqQz6iaZVi4h71txiy4vR1OJA1Pyy0MWI9jMTMYvxIkzNMBxaspXSox5n41JgQDOu0QsQzt8IKzRCANTrp7jGVLF1F1RYL6furx0UDsexU3j4ZY8XW6UDQOFv4NWw8E4mNGpwWSz3vBsF2ISVckeOMy5/YiCoshR4lMepeUHqSPCA9o8GuwKa4EfCjN5dAfDrGHmuljEF826u9v8zA0xl0JuBjtyk7TaSiv8XQIDAQAB"));
    }

    public boolean checkExist(String publicKey) {
        return JDBIConnector.get().withHandle(h -> {
            Integer count = h.createQuery("SELECT count(*) from `" + tableName + "` WHERE public_key = :publicKey")
                    .bind("publicKey", publicKey)
                    .mapTo(Integer.class)
                    .findOne()
                    .orElse(0);
            return count > 0;
        });
    }
}
