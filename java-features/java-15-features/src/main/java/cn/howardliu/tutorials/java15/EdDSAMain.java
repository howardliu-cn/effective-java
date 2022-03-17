package cn.howardliu.tutorials.java15;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.EdECPoint;
import java.security.spec.EdECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.NamedParameterSpec;

import org.apache.commons.codec.binary.Hex;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/3/1 08:32
 */
public class EdDSAMain {
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, SignatureException {
        byte[] msg = "Hello, World!".getBytes(StandardCharsets.UTF_8);

        // example: generate a key pair and sign
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519");
        KeyPair kp = kpg.generateKeyPair();

        // algorithm is pure Ed25519
        Signature sig = Signature.getInstance("Ed25519");
        sig.initSign(kp.getPrivate());
        sig.update(msg);
        System.out.println(Hex.encodeHexString(sig.sign()));

        // example: use KeyFactory to contruct a public key
        KeyFactory kf = KeyFactory.getInstance("EdDSA");
        NamedParameterSpec paramSpec = new NamedParameterSpec("Ed25519");
        EdECPublicKeySpec pubSpec = new EdECPublicKeySpec(paramSpec, new EdECPoint(true, new BigInteger("1")));
        PublicKey pubKey = kf.generatePublic(pubSpec);
        System.out.println(pubKey.getAlgorithm());
        System.out.println(Hex.encodeHexString(pubKey.getEncoded()));
        System.out.println(pubKey.getFormat());
    }
}
