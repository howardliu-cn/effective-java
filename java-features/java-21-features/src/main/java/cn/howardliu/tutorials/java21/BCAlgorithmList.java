package cn.howardliu.tutorials.java21;

import java.security.Provider;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-26
 */
public class BCAlgorithmList {
    public static void main(String[] args) {
        // 添加 Bouncy Castle Provider 到 Java 安全架构
        Security.addProvider(new BouncyCastleProvider());

        // 获取 Bouncy Castle Provider
        Provider bcProvider = Security.getProvider("BC");

        // 输出支持的算法列表
        System.out.println("Supported Algorithms:");
        for (String algorithm : bcProvider.stringPropertyNames()) {
            if (algorithm.startsWith("Alg.Alias.")) continue; // 跳过别名
            System.out.println(algorithm);
        }
    }
}
