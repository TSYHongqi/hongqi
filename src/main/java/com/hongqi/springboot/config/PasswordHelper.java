package com.hongqi.springboot.config;

import com.hongqi.springboot.model.SyEmp;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;

public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    /**
     * 加密密码
     */
    public SyEmp encryptPassword(SyEmp emp) {
        //设置随机salt
        emp.setSalt(randomNumberGenerator.nextBytes().toHex());
        //密码明文+随机salt=密码密文
        String newPassword = new SimpleHash(
                algorithmName,
                emp.getPwd(),
                //credentialsSalt = username+salt
                ByteSource.Util.bytes(emp.getCredentialsSalt()),
                hashIterations).toHex();
        //设置密码密文
        emp.setPwd(newPassword);

        return emp;
    }

    /**
     * 查台密码(解密)
     */
    public static String getQueryPwd(String pwd){
        final Base64 base64 = new Base64();
        String pass="";
        try {
            pass= new String(base64.decode(pwd), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pass;
    }
    /**
     * 查台密码（加密）
     */
    public static String addQueryPwd(String text){
        final Base64 base64 = new Base64();
        String encodedText="";
        final byte[] textByte;
        try {
            textByte = text.getBytes("UTF-8");
            encodedText= base64.encodeToString(textByte);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedText;
    }

}
