package net.tislib.encryptionservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@RestController
@RequestMapping("/api/1.0/encryption")
public class EncryptionController {

    public static final int AES_KEY_SIZE = 256;
    public static final int GCM_IV_LENGTH = 12;
    public static final int GCM_TAG_LENGTH = 16;

    private SecretKey key;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(AES_KEY_SIZE);

        // Generate Key
        this.key = keyGenerator.generateKey();
    }

    @GetMapping("encrypt")
    public String encrypt(@RequestParam String data) throws Exception {
        byte[] IV = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        byte[] cipherText = AES_GCM_Example.encrypt(data.getBytes(), key, IV);

        return Base64.getEncoder().encodeToString(cipherText);
    }

}
