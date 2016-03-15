package com.dnd.diarynoteday.activity;


import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 的加密与解密
 * Created by liuhongwu on 16/3/15.
 */
public  class AESCodeKeyUtils {
    //set random 16 number
    private static  final String IV="qws871bz73msl9x8";
    private  static final String ALGORITHM="AES/CBC/PKCS5Padding";

    private static SecretKeySpec getKey(String strKey){
      byte[] arrTemp=strKey.getBytes();
      byte[] arrNum=new byte[16];  //create the null 16 bit array default 0
        for (int i=0;i<arrTemp.length && i<arrNum.length;i++){
            arrNum[i]=arrTemp[i];
        }
        SecretKeySpec skeySpec=new SecretKeySpec(arrNum,"AES");

     return skeySpec;
    }

    /**
     * 获取指定长度的随机数
     * @param length
     * @return
     */
    public  static  String getRandomString(int length){
        StringBuffer buffer=new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb=new StringBuffer();
        Random random=new Random();
        int range=buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }
    /**
     * 加密
     * @param data
     * @param KEY
     * @return
     */
    public  static  String encrypt(String data,String KEY){

        try {

            SecretKeySpec skeySpec=getKey(KEY);
            Cipher cipher=Cipher.getInstance(ALGORITHM);
            IvParameterSpec iv=new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE,skeySpec,iv);
            byte[] encrypted=cipher.doFinal(data.getBytes());

            return  parseByte2HexStr(encrypted);
        } catch (Exception e) {
            return  "";
        }

    }

    /**
     * 解密
     * @param data
     * @param KEY
     * @return
     */
     public  static  String decrypt(String data,String KEY){

         try {
             SecretKeySpec keySpec=getKey(KEY);
             Cipher cipher=Cipher.getInstance(ALGORITHM);
             IvParameterSpec iv=new IvParameterSpec(IV.getBytes());
             cipher.init(Cipher.DECRYPT_MODE,keySpec,iv);
             byte[]  decrypted=parseHexStr2Byte(data);
             byte[] original=cipher.doFinal(decrypted);

             return new String(original);
         } catch (Exception e) {
             return  "";
         }


     }
    /**
     * 将16进制转换为二进制
      * @param hexStr
     * @return
     */
    public  static  byte[] parseHexStr2Byte(String hexStr){

        if (hexStr.length()<1)
            return  null;
        byte[] result=new byte[hexStr.length()/2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high=Integer.parseInt(hexStr.substring(i*2,i*2+1),16);
            int low=Integer.parseInt(hexStr.substring(i*2+1,i*2+2),16);
            result[i]= (byte) (high*16+low);
        }
        return result;
    }
    /**
     * 将二进制转换成16进制
      * @param buf
     * @return
     */
    public  static  String parseByte2HexStr(byte buf[]){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex=Integer.toHexString(buf[i]&0xFF);
            if (hex.length()==1){
                hex='0'+hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
