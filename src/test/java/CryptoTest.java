import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by ivankovacic on 12/07/17.
 * Test class for testing base encryption/decryption functionalities form Crypto module
 * Basic encrypt/decrypt functionality based on secretKey gathered form environment variable
 */
public class CryptoTest {

    private Crypto crypto;
    private final String text = "This is an very important message!";

    @BeforeClass
    public void setup() {
        crypto = new Crypto();
    }

    @Test
    public void testEncrypt() {
        String encryptedText = crypto.encrypt(text);
        Assert.assertNotEquals(encryptedText, text, "Text should be equal or readable!");
    }

    @Test
    public void testDecrypt() {
        // encryption //
        String encryptedText = crypto.encrypt(text);
        Assert.assertNotEquals(encryptedText, text, "Text should be equal or readable!");

        // decryption //
        String decryptedText = crypto.decrypt(encryptedText);
        Assert.assertEquals(decryptedText, text, "Text should be equal!");
    }
}
