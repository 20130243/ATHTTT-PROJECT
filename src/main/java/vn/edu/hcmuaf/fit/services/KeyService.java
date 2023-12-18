package vn.edu.hcmuaf.fit.services;

import vn.edu.hcmuaf.fit.dao.KeyDAO;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.SQLException;
import java.util.Base64;

public class KeyService {

    private KeyPair keyPair;
    KeyDAO keyDAO = new KeyDAO();

    public static void main(String[] args) throws Exception {
        KeyService keyService = new KeyService();
        String pbk = keyService.getPublicKeyByUserId(12);
//        System.out.println(keyService.encrypt("Trái đất đau đớn",pbk));

        String pvk ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCigL59WtHIhzTXTxb74LAEch0DHfjDACttHhYjQ9R+G3bqFKlKdgnFyfXcR/vgKbwYOky7BiuN4CsI0OGV8D69nYzAU7VJ5qb381k/NyG8Ddn04IlnacAGcL543dDkFWdOP0CHGwO7OEWfWIp14fPj1z27Qx+XIZzP1GjJGsu+Mn4QsRCMcdXflCpPp/8Rcr2La5ABzacGQ0dlcBw8dwBlS1Wz8JrShL6kJhq4ra5N+Dk9judgJet6KnOgmQM/OpBTMC82n539lOt6vhs08n3AMrtoPHQBX8yLf5Hf9y1wUoCjMhl/QTxkcJYRbNlSuxkClaTdS7mOQtLtZIKtkoLpAgMBAAECggEAClKx5y40CfBW4b+fNuh+3gVuxOIsSZzy5UdmQh6+urUdcIDYAUgu1j7+bJwxCrwz+/kGI1JgxW5CZZi5X+hHYA3IYXZf2544qKMvnfWvYa4ilK0H5YchAPe8DdsPeFHIwn5/p6WGbugD+juqbhMoxmZHwGPi95uVxdkZP/9BBqe+Et0lcnR3PgR6GOur/rxOLKYea4+CVFW242Pet1Pkys1Di1L9DuiinMdnCuZcPs/iRnSUO66yitGl6kcZxbPMSco0kQxXcHgUJhAL71hR3HFrMShMKA4sxxX3Dt0REjj9pmXK50u17ppuILqTQQbVVvV7F7VoQM6h2kk7xcp3wQKBgQDNB37nBJgrmyyx1UY98EskIoxMeZcODk89WHdpp+zMO5rvNgsLqBd74LwZnq7I4llSk5/3a33aE2i1Y9JxDxOfeLs01C2GdQGwFfnPKJ98T3ND7kLaTdK4raV8s4b8aikawFfbbqOWVHeRsIqn49tQd5FDEQwETim0Mxs3VP/FYQKBgQDK5sdNsjj2j/5A4bMwM8h+lld1PG5AzQe/pA2NOl9uB1fdFgWiO+yfHKLBbMxrQlJqvV+PkZmG7IhrbgNd20Kgojlu9G35C7+bAKX7gJoy6J8lKrbo/Kh/SirvC3DjJN9TQuthN3STDtQIIozwDBGs+jPtI4zF8n0ntdacb1siiQKBgQCgUhIrNu5+epJSZEDOwg0frlwimJlDhnDzuS920LZbZOhb0NiGPuROG4c6SVjHACd0aLcXU1hPpAmmfO3bNjzT4CYSwy7uCqRNOoHiOROssruhDLf92AjEHBRhFEijK6gEoQ6SKUuFi3sL0w45BfVCUJyn4raQz0XRAH1xoOd3QQKBgET8FcMa1zctWdqhYhT/Zilz4CRh+XJaw0mq81iTAiMcqAjpFd1E1nx6dfOhlBbbo73ayQS9Eh/Hh9JOwyJEC/EldrV8sW89SVXf8I8D+GlaodE3Jp0sWfV9N/+yOh1gSfW7COAyxJXDMt/ZL2XmFJRlNQZpUdFzUo4PyvnA+pFRAoGBAJLMCYHjItsAJTSwTyOTNTc8l2W1m5PUtVYmK0Gd+FBCW79w7iMufwJzQ4tYyagnQOHc+N1QTvsjBvuy32po7Gh6ILyNK2YCqZnzm+viOdZBeo3VRu1vVKy1eBCUuF3ypEVUDUppg64O0ybKRqJwNyww/ySR5Z7kRYUUwyjnZ6/j";
String cipher = "LzI52mtmdG33MDke4xgfVvuo8JbtekjoHcKeciMIQ9AaRe0g1mKseMoXhZHpvhx18t7/G3BXouiXme/NskJkfASo57uflHuZFmSVsfrYSzaCqbtCxlavquOfFi9ZL3wq8sW3v6/0DhOVq8AUHBnyQ1VZ2nQDz6VPRPL0/4se1er+6AuxXIxuQo5/AMAqd+T1Up9iiQXEahPI/Jg3umMQGk5oyruoVr16mKizkvFSbl/Gx8mW5T66HzCYj9ebAzd9TklHkpjfqzRPnej8dDcgoXDokXci33ef1ak9Tp9BZwaOc8eotV7JnLkLYIaJuFya4V0C7zZWM7mHqNr3GY7LAw==";
        System.out.println(keyService.decrypt(cipher, pvk));

    }

    public String createKey(int userId) throws NoSuchAlgorithmException, SQLException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);
        keyPair = keyGenerator.generateKeyPair();
        // expired old key
        deleteOldKey( userId);
        // store public key
        keyDAO.create(publicKeyToString(keyPair), userId, 0);
        // return private key
        return privateKeyToString(keyPair);
    }
public void deleteOldKey(int userId){
        int id = (int) keyDAO.getByUserId(userId).get("id");
        keyDAO.deleteById(id);
}
    public String getPublicKeyByUserId(int userId) {
        return (String) keyDAO.getByUserId(userId).get("public_key");
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

        byte[] decryptedBytes = outputStream.toByteArray();

        return new String(decryptedBytes, StandardCharsets.UTF_8);
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
}
