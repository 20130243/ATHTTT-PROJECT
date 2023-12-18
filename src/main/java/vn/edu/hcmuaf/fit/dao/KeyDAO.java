package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.db.JDBIConnector;

import java.util.Map;

public class KeyDAO {
    String tableName = "keys";

    public void create(String key, int userId, int status) {
        JDBIConnector.get().withHandle(h ->
                h.createUpdate("INSERT INTO `" + tableName + "`(public_key,user_id,status) VALUE (:key,:userId,:status) ")
                        .bind("key", key)
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

    public Map<String, Object> getByUserId(int userId) {
        return JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT * from `" + tableName + "` WHERE user_id = :userId AND status = '0'  AND (expired_at is null OR expired_at < NOW())")
                        .bind("userId", userId)
                        .mapToMap()
                        .first());
    }
}
