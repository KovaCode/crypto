import java.nio.charset.Charset;

final class CryptoConstants {

    static final String secretKey = System.getenv("KEY");
    static final String charSet = Charset.forName("UTF-8").displayName();
    static final String keyfactoryInstance = "PBEWithMD5AndDES";

    // 8-byte Salt
    static final byte[] salt = {
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    // Iteration count
    static final int iterationCount = 20;

    private CryptoConstants() {
        //this prevents even the native class from calling this actor as well :
        throw new AssertionError();
    }
}
