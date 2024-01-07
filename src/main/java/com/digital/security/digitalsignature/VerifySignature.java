package com.digital.security.digitalsignature;

import com.digital.security.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
@Component
public class VerifySignature {
    Logger logger = LoggerFactory.getLogger(VerifySignature.class);

    public boolean digitalVerification(String signedMessage, String message) {
        boolean isMessgaeValid = false;
        try {
            PublicKey publicKey = Utils.loadPublicKey();
            Signature signature = Signature.getInstance(Utils.SIGNING_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(message.getBytes());

            isMessgaeValid = signature.verify(Base64.getDecoder().decode(signedMessage));
            logger.info("Signature" + (isMessgaeValid ? "correct" : "incorrect"));
        } catch (Exception exception) {
            logger.error("Error Occurred", exception);
        }
        return isMessgaeValid;
    }
}


