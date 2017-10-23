import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 *
 * Created by ivankovacic on 12/07/17.
 * Basic encryption/decryption module 'Crypto'
 */
class Crypto {

    private Cipher cipher;
    private SecretKey secretKey;
    private PBEParameterSpec pbeParameterSpec;

    Crypto() {
        secretKey = prepareSecretKey(CryptoConstants.secretKey);
        pbeParameterSpec = preapreParamSpec();
    }

    private SecretKey prepareSecretKey(String secretKey) {
        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), CryptoConstants.salt, CryptoConstants.iterationCount);
        try {
            return SecretKeyFactory.getInstance(CryptoConstants.keyfactoryInstance).generateSecret(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PBEParameterSpec preapreParamSpec() {
        return new PBEParameterSpec(CryptoConstants.salt, CryptoConstants.iterationCount);
    }

    String encrypt(String plainText) {
        try {
            secretKey = prepareSecretKey(CryptoConstants.secretKey);
            pbeParameterSpec = preapreParamSpec();

            //Enc process
            cipher = Cipher.getInstance(secretKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
            byte[] in = plainText.getBytes(CryptoConstants.charSet);
            byte[] out = cipher.doFinal(in);
            return new String(Base64.getEncoder().encode(out));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    String decrypt(String encryptedText) {

        try {
            secretKey = prepareSecretKey(CryptoConstants.secretKey);
            pbeParameterSpec = preapreParamSpec();
            cipher = Cipher.getInstance(secretKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
            byte[] enc = Base64.getDecoder().decode(encryptedText);
            byte[] utf8 = cipher.doFinal(enc);
            return new String(utf8, CryptoConstants.charSet);

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;

    }

}
