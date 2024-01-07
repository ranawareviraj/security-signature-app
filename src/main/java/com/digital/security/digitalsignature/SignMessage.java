package com.digital.security.digitalsignature;

import com.digital.security.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Arrays;
import java.util.Base64;

@Component
public class SignMessage {

    Logger logger = LoggerFactory.getLogger(SignMessage.class);

    public String digitalSignature(String message){
        byte[] digitalSign = {};
        try{

            PrivateKey privateKey = Utils.loadPrivateKey();
            Signature signature = Signature.getInstance(Utils.SIGNING_ALGORITHM);
            signature.initSign(privateKey);

            signature.update(message.getBytes());
            digitalSign = signature.sign();
            logger.info(Arrays.toString(Base64.getEncoder().encode(digitalSign)));
        }
        catch (Exception exception){
            logger.error("Error Occurred",exception);

        }
        return new String(Base64.getEncoder().encode(digitalSign));
    }


}
