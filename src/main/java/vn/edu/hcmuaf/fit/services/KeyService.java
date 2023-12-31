package vn.edu.hcmuaf.fit.services;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import vn.edu.hcmuaf.fit.bean.Key;
import vn.edu.hcmuaf.fit.dao.KeyDAO;

import javax.crypto.Cipher;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

//    public static void main(String[] args) throws Exception {
//        KeyService keyService = new KeyService();
//        String pbk = keyService.getPublicKeyByUserId(12);
////        System.out.println(keyService.encrypt("Trái đất đau đớn",pbk));
//
//        String pvk ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCigL59WtHIhzTXTxb74LAEch0DHfjDACttHhYjQ9R+G3bqFKlKdgnFyfXcR/vgKbwYOky7BiuN4CsI0OGV8D69nYzAU7VJ5qb381k/NyG8Ddn04IlnacAGcL543dDkFWdOP0CHGwO7OEWfWIp14fPj1z27Qx+XIZzP1GjJGsu+Mn4QsRCMcdXflCpPp/8Rcr2La5ABzacGQ0dlcBw8dwBlS1Wz8JrShL6kJhq4ra5N+Dk9judgJet6KnOgmQM/OpBTMC82n539lOt6vhs08n3AMrtoPHQBX8yLf5Hf9y1wUoCjMhl/QTxkcJYRbNlSuxkClaTdS7mOQtLtZIKtkoLpAgMBAAECggEAClKx5y40CfBW4b+fNuh+3gVuxOIsSZzy5UdmQh6+urUdcIDYAUgu1j7+bJwxCrwz+/kGI1JgxW5CZZi5X+hHYA3IYXZf2544qKMvnfWvYa4ilK0H5YchAPe8DdsPeFHIwn5/p6WGbugD+juqbhMoxmZHwGPi95uVxdkZP/9BBqe+Et0lcnR3PgR6GOur/rxOLKYea4+CVFW242Pet1Pkys1Di1L9DuiinMdnCuZcPs/iRnSUO66yitGl6kcZxbPMSco0kQxXcHgUJhAL71hR3HFrMShMKA4sxxX3Dt0REjj9pmXK50u17ppuILqTQQbVVvV7F7VoQM6h2kk7xcp3wQKBgQDNB37nBJgrmyyx1UY98EskIoxMeZcODk89WHdpp+zMO5rvNgsLqBd74LwZnq7I4llSk5/3a33aE2i1Y9JxDxOfeLs01C2GdQGwFfnPKJ98T3ND7kLaTdK4raV8s4b8aikawFfbbqOWVHeRsIqn49tQd5FDEQwETim0Mxs3VP/FYQKBgQDK5sdNsjj2j/5A4bMwM8h+lld1PG5AzQe/pA2NOl9uB1fdFgWiO+yfHKLBbMxrQlJqvV+PkZmG7IhrbgNd20Kgojlu9G35C7+bAKX7gJoy6J8lKrbo/Kh/SirvC3DjJN9TQuthN3STDtQIIozwDBGs+jPtI4zF8n0ntdacb1siiQKBgQCgUhIrNu5+epJSZEDOwg0frlwimJlDhnDzuS920LZbZOhb0NiGPuROG4c6SVjHACd0aLcXU1hPpAmmfO3bNjzT4CYSwy7uCqRNOoHiOROssruhDLf92AjEHBRhFEijK6gEoQ6SKUuFi3sL0w45BfVCUJyn4raQz0XRAH1xoOd3QQKBgET8FcMa1zctWdqhYhT/Zilz4CRh+XJaw0mq81iTAiMcqAjpFd1E1nx6dfOhlBbbo73ayQS9Eh/Hh9JOwyJEC/EldrV8sW89SVXf8I8D+GlaodE3Jp0sWfV9N/+yOh1gSfW7COAyxJXDMt/ZL2XmFJRlNQZpUdFzUo4PyvnA+pFRAoGBAJLMCYHjItsAJTSwTyOTNTc8l2W1m5PUtVYmK0Gd+FBCW79w7iMufwJzQ4tYyagnQOHc+N1QTvsjBvuy32po7Gh6ILyNK2YCqZnzm+viOdZBeo3VRu1vVKy1eBCUuF3ypEVUDUppg64O0ybKRqJwNyww/ySR5Z7kRYUUwyjnZ6/j";
//String cipher = "LzI52mtmdG33MDke4xgfVvuo8JbtekjoHcKeciMIQ9AaRe0g1mKseMoXhZHpvhx18t7/G3BXouiXme/NskJkfASo57uflHuZFmSVsfrYSzaCqbtCxlavquOfFi9ZL3wq8sW3v6/0DhOVq8AUHBnyQ1VZ2nQDz6VPRPL0/4se1er+6AuxXIxuQo5/AMAqd+T1Up9iiQXEahPI/Jg3umMQGk5oyruoVr16mKizkvFSbl/Gx8mW5T66HzCYj9ebAzd9TklHkpjfqzRPnej8dDcgoXDokXci33ef1ak9Tp9BZwaOc8eotV7JnLkLYIaJuFya4V0C7zZWM7mHqNr3GY7LAw==";
//        System.out.println(keyService.decrypt(cipher, pvk));
//
//    }

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

    public static String privateKeyToString(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }

    public static PublicKey stringToPublicKey(String publicKeyString) throws Exception {
        byte[] publicBytes = Base64.getDecoder().decode(publicKeyString);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));
    }

    public static PrivateKey stringToPrivateKey(String privateKeyString) throws Exception {
        byte[] privateBytes = Base64.getDecoder().decode(privateKeyString);
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

    public String sign(String message, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());

        byte[] signatureBytes = signature.sign();

        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    public PrivateKey convertPrivateKey(String keyString, String algorithm) throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(keyString);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        return keyFactory.generatePrivate(keySpec);
    }

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
            String keyString = Base64.getEncoder().encodeToString(stringToPublicKey(publicKeyString).getEncoded());
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
        String res="";
        try {
            InputStream fileContentStream = filePart.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileContentStream));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            res = fileContent.toString().trim()
                    .replace("-----BEGIN PRIVATE KEY-----","")
                    .replace("-----END PRIVATE KEY-----","");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static void main(String[] args) throws Exception {
//            System.out.println(new KeyService().decrypt("XQZYykbheF9PsCn1xZS+iBXSKEFAox39huFa8mbQGpUe+2lIDwT78UROnCRXwqqfoHxtrF5zSUSzlP4+hnKJqP6SMvCF3FaW1bessVeXQ40MkTbwidS7DFkJSJhuN8srfFIbrfc1jRicl7ZJIq4BNtjFmWfQstHXGxZ9xK+hj3mhJ05htYUNn4zVaQ875gzTVgv7xxw7v2CvP9yzEahIDbLogfXdfLOUH+0c/MTmIcQUipTGdcpQtQWL4WPJsgtUsruIOV9XYTSwBqkszKuL25T3ChfMb8OrRCiLe+6PdUSkOzafRtxt5Z4+Vyz1F0WhRqOW7XaHw5/tRL8kZuGNtA==", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9003PfrPMQ3LcbWJb+UAaBTmBx+7Zazd7RPCu4BxeqF8llm1x3Gd+nT1nUMXelumS7exoBF/UV7+XQbIacuoChYzwh5rwPuZRfxkREtd+3lrkyKae7r3W8HzVA+WL0ru9ihjPJfYs0CjonTCzXjqZITyc2PJUiAzAM/AcfgC2ALdB8WpD7hR8pniJDqauBNmgMkvqE0vhyJ/wLyaiiKQJxPpg/pbhDvz/42hzeQnGVpFFF24F26sk2DDesv6sJtLfFG97CiWZQaL/w1ICtINnMZSSWSd9T7ps2ZayBAsOuOSPd1x7Qkg4LDosU6WWbTEkD4Dmy9nm8/IoN/4TF3y7AgMBAAECggEAHS6NyLdGtsEruD1MoK0JsLZa59uvmVcFOXsYsRmc4uRpdCLTAm0KsAlGNkrRkG2MzNysujTp8n/m4T7lPXGrDeYwC44dQI+64o5ycRB/dm6CdKdwDgDPyGpCFpE5yawE1peTRs0kMjFyCIiZwwlaYs4cPlSPtHUR2L5jTE4Gl/PyRS25vZNWYAYnz/1dEDMBiVyPm//Y7obfLVVIMOJ/QM6tS7wvNKpAU1vO+Oei5+YY3ABh6d8Mq7D1K/hqPdVDggaH6iEdmiSQx0EGzi4AKhhmzQQ6CeRGkVNhKt1ekBaYOUgBhWdrr/1wUgoy6bWWG0Ff8jGnAzsqrmStswcl+QKBgQDNVZJbccAJWSWaUaVNgaRwp87j/TGrF9IkZp5/HfRQTNqDe6860UCM8W1KTyYwVP+zdSngKp2dezZqWiob7ov4TVDolEColiXnAGNXFtjMFJdhRhxyW1VQizsg0ScW/eQsrwNkQ7tOlkIT3PQRNkOBhKSfW4uhtWRUHxGYY0DRdwKBgQDsqhQGoavYyx2sfIvNWzrWEasPsIW5Uy3Bbvmh//M6Uh8vtqVlvx6uUcsAkpXIWp+LJyCAj5L1Oa/7mY8OCDGzbngWf7NDC/JRqecOICfihClpGralYDkpyJEOojcYXTurWmMTzRawQ9iB8x+6FedfQbiAcKhKkUyW6CjPCrTf3QKBgQCOG7QgP5iFn8ILjIgYHSpeoXTpizlNzT2t3avCFEwbKyVsLDS5Q0smgIyACklG1/zkCamCsFvHOxgNAPv1uuH6ZiAh50DcrJXsyvL1uiUvEO038FsNtjJUfRfd/YLNQcgiOLnjMZE3sXxn3Nq58tFDmTaJ58S6lRyrr5jw23hnvwKBgHoHOEwzEGi4UpSdo3g/khbPBWURn+HvAai7j/v3/XIU5f+0LZRI94jqo8Gn05N7JsiZZCjl3uCS3irdAuY5U2cxjroLHmNzxX5WHM0rx2UEwFVxcLvU4aSpxiHFgqMNb7bq5CtRlGFOmlRnB/TrVmHHgVq4vA223cbx7hjTbHABAoGBAJnVjslIH1n4JEkzJPr9mAyBgmixcSLOL2O+YCSi4M9aRD9EB82tUYMJ1lwM+1G8gCdx2ObAdeHzZzCGbMPxNG6yC0ij/dILqp67K6z5SfHiQHKOynS7W/smHKJU7eFtiMviT90QCG45vhFHVpaS+xSJiHL1Wvf+bqDNY4CKxF2Y"));
//        System.out.println(new KeyService().checkRSAPublicKeyByFile(17,"C:\\Users\\tinh\\Desktop\\publickey.key"));
//        System.out.println(new KeyService().checkRSAPublicKeyByText(17,"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnFEvplKFOsJrcGlGhoqrK490FeCGb+39UAJospGHvrwUlFh/gI9NMr8CzVvUuOt8ynlUhlH0iaqhQnFzhx9qIIeevgQm0yj6/RDrf854sHcLsXe/scFRJlLMb3JTEi2f4TcDaJYpG9FTjiiJpdnjEN7XbDDAbVlnr5YgHpCBskrwQTZUWyYb2hV9cMm09w3EVhbDxh+ngJy4FaymyG8i06d0REyem4ryjYcHiIIu73H3MI4FpXDQTnlEF5ao3yA6LyGGlEopbXImgacQosK0wlnHRLidXAMKUFHRK75z3USUSMiJtHMtAVZWWK+yHRXx0N9VjTI28ntXRQEsS1J87wIDAQAB"));
    }
}
