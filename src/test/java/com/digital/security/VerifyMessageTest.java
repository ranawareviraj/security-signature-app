package com.digital.security;

import com.digital.security.digitalsignature.VerifySignature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.digital.security.utils.Utils.signatureTest;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class VerifyMessageTest {
    @Autowired
    private VerifySignature verifySignature;

 @Test
    public void testVerifyMessage(){
        boolean verificationStatus = verifySignature.digitalVerification(signatureTest ,"this is a private message");
        assertTrue(" Doesn't Match",verificationStatus);
    }

    @Test
    public void testVerifyMessage_False(){
        boolean verificationStatus = verifySignature.digitalVerification("asdasd","this is a private message");
        assertFalse("Doesn't match",verificationStatus);
    }
}


