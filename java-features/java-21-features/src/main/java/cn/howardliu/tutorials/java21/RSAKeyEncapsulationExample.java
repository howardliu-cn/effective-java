package cn.howardliu.tutorials.java21;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Arrays;

import javax.crypto.KEM;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-08-26
 */
public class RSAKeyEncapsulationExample {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // 创建RSA密钥对生成器
        final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
        keyGen.initialize(2048);
        final KeyPair keyPair = keyGen.generateKeyPair();

        final PublicKey aPublic = keyPair.getPublic();
        final PrivateKey aPrivate = keyPair.getPrivate();

        // 创建RSA-KEM密钥封装器
        final KEM kemS = KEM.getInstance("DHKEM");
        final DHParameterSpec parameterSpec = new DHParameterSpec(BigInteger.ONE, BigInteger.ONE);
        final KEM.Encapsulator e = kemS.newEncapsulator(aPublic, parameterSpec, new SecureRandom());
        final KEM.Encapsulated enc = e.encapsulate();
        SecretKey secS = enc.key();
        final byte[] encapsulation = enc.encapsulation();
        final byte[] params = enc.params();

        final KEM kemR = KEM.getInstance("DHKEM");
        final AlgorithmParameters algParams = AlgorithmParameters.getInstance("DHKEM");
        algParams.init(params);
        final RSAKeyGenParameterSpec specR = algParams.getParameterSpec(RSAKeyGenParameterSpec.class);
        final KEM.Decapsulator d = kemR.newDecapsulator(aPrivate, specR);
        SecretKey secR = d.decapsulate(encapsulation);

        System.out.println(Arrays.toString(secS.getEncoded()));
        System.out.println(Arrays.toString(secR.getEncoded()));


        System.out.println("Secret keys match: " + secS.equals(secR));

    }
}
