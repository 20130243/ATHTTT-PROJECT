package vn.edu.hcmuaf.fit.services;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import vn.edu.hcmuaf.fit.bean.Key;
import vn.edu.hcmuaf.fit.dao.KeyDAO;

import javax.crypto.Cipher;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

public class KeyService {

    private KeyPair keyPair;
    KeyDAO keyDAO = new KeyDAO();


    public String createKey(int userId) throws NoSuchAlgorithmException, SQLException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);
        keyPair = keyGenerator.generateKeyPair();
        // expired old key
        deleteOldKey(userId);
        // store public key
        keyDAO.create(publicKeyToString(keyPair), userId, 0);
        // return private key
        return privateKeyToString(keyPair);
    }

    public void deleteOldKey(int userId) {
        int id = (int) (keyDAO.getByUserId(userId) != null ? keyDAO.getByUserId(userId).get("id") : 0);
        if (id != 0) {
            keyDAO.deleteById(id);
        } else {
            try {
                keyDAO.deleteByUserId(userId);
            } catch (Exception e) {
                System.out.println("lỗi");
            }
        }
    }

    public String getPublicKeyByUserId(int userId) {
        return keyDAO.getByUserId(userId) != null ? (String) keyDAO.getByUserId(userId).get("public_key") : null;
    }

    public String getPublicKeyByOrder(int orderId) {
        return keyDAO.getByOrderId(orderId) != null ? (String) keyDAO.getByOrderId(orderId).get("public_key") : null;
    }

    public String encrypt(String text, String public_key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(cipher.ENCRYPT_MODE, stringToPublicKey(public_key));
        byte[] inputBytes = text.getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int i = 0; i < inputBytes.length; i += 100) {
            int length = Math.min(100, inputBytes.length - i);
            byte[] block = cipher.doFinal(inputBytes, i, length);
            outputStream.write(block, 0, block.length);
        }

        byte[] encryptedBytes = outputStream.toByteArray();


        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String text, String privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(cipher.DECRYPT_MODE, stringToPrivateKey(privateKey));

        byte[] encryptedBytes = Base64.getDecoder().decode(text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int i = 0; i < encryptedBytes.length; i += 256) {
            int length = Math.min(256, encryptedBytes.length - i);
            byte[] block = cipher.doFinal(encryptedBytes, i, length);
            outputStream.write(block, 0, block.length);
        }

        return outputStream.toString(StandardCharsets.UTF_8);
    }


    public static String publicKeyToString(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    public static String publicKeyToString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public static String privateKeyToString(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }

    public static String privateKeyToString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public static PublicKey stringToPublicKey(String publicKeyString) throws Exception {
        byte[] publicBytes = Base64.getDecoder().decode(transformKeyValue(publicKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));
    }

    public static PrivateKey stringToPrivateKey(String privateKeyString) throws Exception {
        byte[] privateBytes = Base64.getDecoder().decode(transformKeyValue(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));

    }

    public String formatPublicKey(String publicKey) {
        return "----BEGIN PUBLIC KEY-----" + "\n"
                + publicKey + "\n"
                + "-----END PUBLIC KEY-----";
    }

    public String formatPrivateKey(String privateKey) {
        return "-----BEGIN PRIVATE KEY-----" + "\n"
                + privateKey + "\n"
                + "-----END PRIVATE KEY-----";
    }

    public Key getKeyByUserId(int id) {
        Map<String, Object> keyDao = new KeyDAO().getByUserId(id);
        Key key = null;
        if (keyDao != null) {
            key = new Key();
            key.setId((int) keyDao.get("id"));
            key.setPublicKey(keyDao.get("public_key").toString());
            key.setCreateAt((LocalDateTime) keyDao.get("create_at"));
            key.setExpiredAt((LocalDateTime) keyDao.get("expired_at"));
            key.setUser_id((int) keyDao.get("user_id"));
            key.setStatus((int) keyDao.get("status"));
        }
        return key;
    }

    public Key getKeyByOrderId(int id) {
        Map<String, Object> keyDao = new KeyDAO().getByOrderId(id);
        Key key = null;
        if (keyDao != null) {
            key = new Key();
            key.setId((int) keyDao.get("id"));
            key.setPublicKey(keyDao.get("public_key").toString());
            key.setCreateAt((LocalDateTime) keyDao.get("create_at"));
            key.setExpiredAt((LocalDateTime) keyDao.get("expired_at"));
            key.setUser_id((int) keyDao.get("user_id"));
            key.setStatus((int) keyDao.get("status"));
        }
        return key;
    }

    public String sign(String message, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());

        byte[] signatureBytes = signature.sign();

        return Base64.getEncoder().encodeToString(signatureBytes);
    }

//    public PrivateKey convertPrivateKey(String keyString, String algorithm) throws Exception {
//
//        byte[] keyBytes = Base64.getDecoder().decode(transformKeyValue(keyString));
//
//        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
//
//        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
//    }

    public PublicKey convertPublicKey(String keyString, String algorithm) throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(keyString);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        return keyFactory.generatePublic(keySpec);
    }

    public boolean verify(String messagehash, String signature, PublicKey publicKey) throws Exception {
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        System.out.println(1);
        Signature sig = Signature.getInstance("MD5withRSA");
        sig.initVerify(publicKey);
        System.out.println(2);
        sig.update(messagehash.getBytes());
        System.out.println(3);
        return sig.verify(signatureBytes);
    }


    public boolean checkRSAPublicKeyByText(int userId, String publicKeyString) {
        try {
            String keyString = Base64.getEncoder().encodeToString(stringToPublicKey(transformKeyValue(publicKeyString)).getEncoded());
            // expired old key
            deleteOldKey(userId);
            // store public key
            keyDAO.create(keyString, userId, 0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean checkRSAPublicKeyByFile(int userId, Part filePart) {
        try (InputStream fileInputStream = filePart.getInputStream();
             PemReader pemReader = new PemReader(new InputStreamReader(fileInputStream))) {

            PemObject pemObject = pemReader.readPemObject();
            if (pemObject == null) {
                System.out.println("Error: No PEM object found in the file.");
                return false;
            }

            byte[] content = pemObject.getContent();

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(content);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            String keyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            System.out.println("Public key: " + publicKey.toString());

            // expired old key
            deleteOldKey(userId);

            // store public key
            keyDAO.create(keyString, userId, 0);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String hashString(String dataInput) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] output = md.digest(dataInput.getBytes());
            BigInteger num = new BigInteger(1, output);
            return num.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public String readFile(Part filePart) {
        StringBuilder fileContent = new StringBuilder();
        String res = "";
        try {
            InputStream fileContentStream = filePart.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileContentStream));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            res = fileContent.toString().trim()
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean checkExist(String publicKey) {
        return keyDAO.checkExist(transformKeyValue(publicKey));
    }

    public static String transformKeyValue(String key) {
        key = key.replace("-----BEGIN RSA PRIVATE KEY-----", "");
        key = key.replace("-----END RSA PRIVATE KEY-----", "");
        key = key.replace("-----BEGIN PUBLIC KEY-----", "");
        key = key.replace("-----END PUBLIC KEY-----", "");
        key = key.replace("-----END PRIVATE KEY-----", "");
        key = key.replace("-----END PRIVATE KEY-----", "");
        key = key.replace("\n", "");
        return key;
    }

    public static void main(String[] args) throws Exception {


        KeyService ks = new KeyService();
        String pu = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnq6QTTe/+neIzrIeLgEZW0KjdYl4pC0v4oO1WAm4yY5nXXSOxab2YcdsODhMc8xeD3UfnmZWUu3hILs4NE1sh/QNJUr3oO02skuk7dVPvr0cyrv81O9OaptZOOMoZV/67IiC9NZqPIdmeRgYBH6TPUyFvrxqwwQ2P9GmXkz2Sd+WTpZm0Tsw3XzT/p3dgFvTAQbtSI5fZfsYSLYkWqRbIBcZDVcIBWdAMY3TjiBtOKppg8Bvr/j8jHfK8cSGpQn9JzGoO+4jr+IppZwMczhc7Kk1oDVWQxlVB6XNByHPh8KjaiYqa5JdAMSLeM+CTj0vDzon44g7yYfl5Hsj8f8VbQIDAQAB";
        String pv = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCerpBNN7/6d4jOsh4uARlbQqN1iXikLS/ig7VYCbjJjmdddI7FpvZhx2w4OExzzF4PdR+eZlZS7eEguzg0TWyH9A0lSveg7TayS6Tt1U++vRzKu/zU705qm1k44yhlX/rsiIL01mo8h2Z5GBgEfpM9TIW+vGrDBDY/0aZeTPZJ35ZOlmbROzDdfNP+nd2AW9MBBu1Ijl9l+xhItiRapFsgFxkNVwgFZ0AxjdOOIG04qmmDwG+v+PyMd8rxxIalCf0nMag77iOv4imlnAxzOFzsqTWgNVZDGVUHpc0HIc+HwqNqJiprkl0AxIt4z4JOPS8POifjiDvJh+XkeyPx/xVtAgMBAAECggEAA3VxnsVC0X5OhJbcUotilpsmvfafoxB7FThiuQcJOJd6iE50mDlR8IKsyLLtc662NCcM5+J6KjeWKyT5jiZL4dC/gpsWN7ND+HHup+J46QLtQDwFwExxDKDTOoq1BDSp3wMAu756tVQMN0J/r4VkxzbGSYiI2TRbzFtRAYgy7BxDPH67Z2zFvHBFb9Pomp0JhZrFCkudHEgZd1RHetEZBsXjYg82thkEBOy+iZr42nNRfRPoMfbQzUTMGNWecd8V1MVFSp76FmAVs7X3kNAilpeqgKCpewohiOJmznF38AHlfl4nTuM9kFefpxixOcibH4TE2lyaesJLLoc03kY+0QKBgQDLwTBQ4wnC+gAyzrEjqw0xelcDBTIHG3GQuYXfnZF0uziGO9RUdro0mwggGiK9jGfR71rdmNI4xoXKmCZaT+wTnarZlbeJqN0b0qSHSWjeBs0HxYRmE6JzgQbdLEd88irwSR8yOJHK6BWwXN1zd6Wu2Z6cc2EqQ5E7KAB54YaxaQKBgQDHXrhzRSrb+Yc5rSE/EjaAy4pvOXoFVg7+7N7xHtGETN7nahbywyS8Y2aGf6NIX3A2Mr8gUNrSdAdw3qROS1g1XlGpH9kbWmkg21wBwMISnWoBiXrjKBjIbxD4IFYtZx4bM7pqcAOgPTVDdXz6DehuLTRA9A7nWCBu6ZH65Bh/ZQKBgDFq5qQHnLt8VQX9mXr0JpwEdwwVe3+7yTx3sp98oOW17synwCTIMvtFSqA2loxwfFGvgtNSGFKK27I6Wrr2vWQ4jlJ9fGlX2mDAIwVo6ZVIAvxKjIURaAoplheYgLIuLfnfUkCk9b9HsNL4gwFZWIbi4xRA66ULhDLbzdnBuCBZAoGACCiuL7ly6ZGqoQeexq/+HwM1ZRvbDIDbGD+lWATE2j8zGBFVYxXtWPBQb8ylZ/7P3sfw3mewrNAmxc22t6WJtJVpjhxJjD8E8l1HxKKYzp42il2P89/eM8Wn0h+wpycDKmXRiS2QWGxo05vpQ2pySp/69XWHbpxCp4dTfAr9nakCgYAQ2uBXdbeO/6mL26o89uORN4n3dD/lRNCPS0XJ35D5s3myKrRZJ+4IQYnbQWP7QelIXx4JdSVXiMZwxyk7vQSuz7xVjRx1c/6T9SSiB445eCkdwIO1FncBj/yF21kPAwljetSrW+HUEpqvRRJFgkCPdHczzgSpnfld1qI0WtUgtw==";
        PublicKey publicKey = KeyService.stringToPublicKey(pu);
        PrivateKey privateKey = KeyService.stringToPrivateKey(pv);


        String bill = "NGUYEN HUY HOANG0981003797?à-79-761-26767[Item{, product={id=126, name='Trà Xanh Gong Cha', idCategory=1, priceSize=[PriceSize{id=156, product_id=156, size='M', price=4800.0, originalPrice=38000.0}], image=[Image{id=115, name='null', url='/img/product/products/TraXanhGongCha.png', product_id=126, status=1}, Image{id=396, name='null', url='/img/product/products/phuc-long-1.jpg', product_id=126, status=1}], status=1, topping=[]}, quantity=1, price=4800.0, note=''}]4800.0";
        String data = "MRz4hsv6D2pvhqogcOsbhUy4kaGvTWbDcZgfoav50TAFYfANgW9r1/KEko8QDmVXkjmZP3DYv+SvjICX2mDMvMNogCYT6rvKdltoMacBQAu6jXATJrhyDGTreehYivHPnHqVwQFE3My19uxBUf1Aa1Mmy3gqYatge1PDqHP1zdA+EoOZUIFjZ8VcgXOyVd2ono7FFP2CoUwFhe4mPSxl6VCh9N7nWPBKM0LlQgJVK3/3tddQ/4tZtaV0p+H40bGjAbozka6P8bH8+drznOrYPVA+M38r+4gJsBXXNtERsv4jWEX7bqbsufMaxNtIpV3M4CF1DoLpYwqRrtJ0VJo9WQ==";
        String hash = ks.hashString(bill);
        System.out.println(hash);
        String signature = ks.sign(hash, privateKey);
        System.out.println(signature);
        boolean verify = ks.verify(hash, data, publicKey);
        System.out.println(verify);

        String a = "NGUYEN HUY HOANG0981003797as f-79-776-27421[Item{product={ name='Trà Xoài B??i H?ng', idCategory=1, priceSize=[PriceSize{id=110, product_id=110, size='M', price=5250.0, originalPrice=30000.0}], image=[Image{id=118, name='null', url='/img/product/products/TraXoaiBuoiHong.png', product_id=129, status=1}, Image{id=399, name='null', url='/img/product/products/phuc-long-1.jpg', product_id=129, status=1}], status=1, topping=[]}, quantity=1, price=5250.0, note=''}]5250.0\n";
        String b = "NGUYEN HUY HOANG0981003797as f-79-776-27421[Item{product={ name='Trà Xoài B??i H?ng', idCategory=1, priceSize=[PriceSize{id=110, product_id=129, size='M', price=5250.0, originalPrice=30000.0}], image=[Image{id=118, name='null', url='/img/product/products/TraXoaiBuoiHong.png', product_id=129, status=1}, Image{id=399, name='null', url='/img/product/products/phuc-long-1.jpg', product_id=129, status=1}], status=1, topping=[]}, quantity=1, price=5250.0, note=''}]5250.0\n";
    }

}
