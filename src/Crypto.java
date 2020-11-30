import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Crypto {
    private static Signature sig;

    static {
        try {
            sig = Signature.getInstance("SHA256WithRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String generateSignature(String string_to_sign) throws Exception {
        byte[] data = string_to_sign.getBytes(StandardCharsets.UTF_8);

        sig.initSign(getPrivate("src/resources/private_key.der"));
        sig.update(data);

        byte[] signatureBytes = sig.sign();
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    public static Boolean validateSignature(String signature, String string_to_validate) throws Exception {
        byte[] string_to_validate_bytes = string_to_validate.getBytes(StandardCharsets.UTF_8);
        byte[] signature64 = Base64.getDecoder().decode(signature);

        sig.initVerify(getPublic("src/resources/public_key.der"));
        sig.update(string_to_validate_bytes);

        return sig.verify(signature64);
    }

    private static PrivateKey getPrivate(String filename)
            throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private static PublicKey getPublic(String filename)
            throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

}
