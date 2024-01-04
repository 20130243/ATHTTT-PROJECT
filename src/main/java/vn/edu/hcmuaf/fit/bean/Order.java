package vn.edu.hcmuaf.fit.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Order implements Serializable {
    int id;
    int user_id;
    String name;
    String phone;
    String address;
    Timestamp time;
    String note;
    private Coupon coupon;
    List<Item> listItems;
    float total;
    int status;
    String hash_message;

    int key_id;

    public Order(int id, int user_id, String name, String phone, String address, Timestamp time, String note, Coupon coupon, List<Item> listItems, float total, int status, String hash_message, int key_id) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.time = time;
        this.note = note;
        this.coupon = coupon;
        this.listItems = listItems;
        this.total = total;
        this.status = status;
        this.hash_message = hash_message;
        this.key_id = key_id;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHash_message() {
        return hash_message;
    }

    public void setHash_message(String hash_message) {
        this.hash_message = hash_message;
    }

    public int getKey_id() {
        return key_id;
    }

    public void setKey_id(int key_id) {
        this.key_id = key_id;
    }

    //    public void setTotal() {
//        if(this.cart != null) {
//            this.total = this.cart.getTotalMoney();
//        }
//        this.total = 0;
//
//    }

    public void setTotal() {
        if (this.listItems.size() > 0) {
            for (Item item : this.listItems) {
                this.total += item.getPrice();
            }
        } else {
            this.total = 0;
        }
    }

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    public List<Item> getListItems() {
        return listItems;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "Order{" +
//                "id=" + id +
//                ", user_id=" + user_id +
                ", name='" + name.toString() + '\'' +
                ", phone='" + phone.toString() + '\'' +
                ", address='" + address.toString() + '\'' +
//                ", time=" + time +
                ", note='" + note.toString() + '\'' +
//                ", coupon=" + coupon +
//                ", listItems=" + listItems +
                ", total=" + total +
//                ", status=" + status +
//                ", hash_message='" + hash_message + '\'' +
//                ", key_id=" + key_id +
                '}';
    }

    public String bill() {
        StringBuilder itemBill = new StringBuilder();
        for (Item item : listItems) {
            itemBill.append(item.bill()).append(";");
        }
        return name.trim() + phone.trim() + address.trim() + note.trim() + itemBill.toString() + total;
    }
}
//ô mai0344558307s-Thành phố Hồ Chí Minh-Quận 8-Phường 15 Trà Xoài Bưởi Hồng Kem Phô Mai,40000.0,1,56000.0;ha   ,32000.0,1,32000.0;88000.0
//ô mai0344558307s-Thành phố Hồ Chí Minh-Quận 8-Phường 15ha   ,32000.0,1,32000.0; Trà Xoài Bưởi Hồng Kem Phô Mai,40000.0,1,56000.0;88000.0