package vn.edu.hcmuaf.fit.bean;

import java.time.LocalDateTime;

public class Key {
    int id;
    String publicKey;
    LocalDateTime createAt;
    LocalDateTime expiredAt;
    int user_id;
    int status;

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }



    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }





    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Key() {
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", publicKey='" + publicKey + '\'' +
                ", createAt=" + createAt +
                ", expiredAt=" + expiredAt +
                ", user_id=" + user_id +
                ", status=" + status +
                '}';
    }
}
