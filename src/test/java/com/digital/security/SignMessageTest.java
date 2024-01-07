package com.digital.security;

import com.digital.security.digitalsignature.SignMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.digital.security.utils.Utils.signatureTest;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

@SpringBootTest
public class SignMessageTest {

    @Autowired
    private SignMessage signMessage;

    @Test
    public void  testVerifyMessage() throws Exception{

        String signature = signMessage.digitalSignature("this is a private message");
        System.out.println(signature);
        assertEquals("Messages are correct", signatureTest , signature);
    }

    @Test
    public void testVerifyMessageNotEqual(){
        String signatureTest ="avjMGTcqnkVZFmhWuDneHhtz2dJIVTOWMHL5ILcVycCn5GevvPMNSe2EodWfUg7Tk5bs" +
                "/Rkof+ChPDcKILbnxlxAMNNlaGlwma79xkvMjlCP3+5vzs5di6mHtBJtssnjg==";
        String signature = signMessage.digitalSignature("this is a private message");
        System.out.println(signature);
        assertNotEquals("Messages are not correct", signatureTest , signature);


    }
}