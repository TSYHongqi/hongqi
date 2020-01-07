package com.hongqi.springboot;

import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.service.LoginService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class HongqiApplicationTests {

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
                //credentialsSalt=username+salt
                ByteSource.Util.bytes(emp.getCredentialsSalt()),
                hashIterations).toHex();
        //设置密码密文
        emp.setPwd(newPassword);

        return emp;
    }
    @Test
    void contextLoads() {
        SyEmp emp = new SyEmp();
        emp.setEmpNo("10001");
        emp.setPwd("123456");
        SyEmp emp1 = encryptPassword(emp);
        System.out.println("=========================================="+emp1);
    }



}
