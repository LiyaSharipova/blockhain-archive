package com.github.liyasharipova.blockchain.archive.blochain_realisation.util;

import com.github.liyasharipova.blockchain.archive.blochain_realisation.transaction.TransactionDto;
import com.google.gson.GsonBuilder;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Утилита для хэширования
 */
public class StringUtil {

    /**
     * ГОСТ Р 34.11-2012
     */
    private static final String STRIBOG_512_NAME = "Stribog256";

    /**
     * Применяет функцию хэширования ГОСТ Р 34.11-2012 к строке
     * @param input
     * @return
     */
    public static String applyStribog(String input) {

        try {
            MessageDigest digest = MessageDigest.getInstance(STRIBOG_512_NAME);

            //Применяем функцию хэширования к строке
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            //hexString содержит шестнадцетеричный хэш
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Applies ECDSA Signature and returns the result ( as bytes ).
    public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
        Signature dsa;
        byte[] output = new byte[0];
        try {
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            output = realSig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    //Verifies a String signature
    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Short hand helper to turn Object into a json string
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static String getMerkleRoot(ArrayList<TransactionDto> transactions) {
        int count = transactions.size();

//      собираем все хеши транзакций в список previousTreeLayer
        ArrayList<String> previousTreeLayer = new ArrayList<String>();
        for (TransactionDto transaction : transactions) {
            previousTreeLayer.add(transaction.getHash());
        }
        ArrayList<String> treeLayer = previousTreeLayer;

        while (count > 1) {
            treeLayer = new ArrayList<String>();
            for (int i = 1; i < previousTreeLayer.size(); i++) {
                treeLayer.add(applyStribog(previousTreeLayer.get(i - 1) + previousTreeLayer.get(i)));
            }
            count = treeLayer.size();
            previousTreeLayer = treeLayer;
        }
//      если размер списка хешей транзакций  = 1 то возвращаем хеш транзакции
        String merkleRoot = (treeLayer.size() == 1) ? treeLayer.get(0) : "";
        return merkleRoot;
    }
}
