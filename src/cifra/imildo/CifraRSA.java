package cifra.imildo;

import java.math.BigInteger;
import java.security.SecureRandom;

public class CifraRSA {

    private static void cipher() {
        String msg = "Get this piece of code, bro!";
        String msgCifrada = null;
        String msgDecifrada = null;
        BigInteger n, d, e;
        int bitlen = 2048;
        //Random choose of two prime numbers p e q
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        //Compute n = p * q
        n = p.multiply(q);
        //Compute the totiente function phi(n) = (p-1)(q-1)
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        //Escolher one whole number "e" : 1 < e <phi(n) : "e" e phi(n) beign primes to each other
        e = new BigInteger("3");

        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        // d beign o multiplicative inverse of "e"
        d = e.modInverse(m);
        System.out.println("p : " + p);
        System.out.println("q : " + q);
        System.out.println("n : " + n);
        System.out.println("e : " + e);
        System.out.println("d : " + d);

        //Encrypted message - RSA_encrypt
        msgCifrada = new BigInteger(msg.getBytes()).modPow(e, n).toString();
        System.out.println("Encrypted messag : " + msgCifrada);

        //Decrypted message - RSA_encrypt
        msgDecifrada = new String(new BigInteger(msgCifrada).modPow(d, n).toByteArray());
        System.out.println("Decrypted message : " + msgDecifrada);
    }

    public static void main(String[] args) {
        cipher();
    }
}
