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
        Signature sig = Signature.getInstance("MD5withRSA");
        sig.initVerify(publicKey);
        sig.update(messagehash.getBytes());
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
public boolean checkExist(String publicKey){
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

}
