package com.digital.security.utils;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

public class Utils {
    private static final char[] PASSWORD = "welcome123".toCharArray();
    private static final String STORE_TYPE = "JKS";
    private static final String SENDER_KEYSTORE = "sender_keystore.jks";
    private static final String SENDER_ALIAS = "senderKeyPair";
    public static final String SIGNING_ALGORITHM = "SHA256withRSA";
    private static final String RECEIVER_KEYSTORE = "receiver_keystore.jks";
    private static final String RECEIVER_ALIAS = "receiverKeyPair";

    public static final String signatureTest = "avjMGTcqnkVZFmhWuDneHhtz2dJIVTOWMHL5ILcVycCn5GevvPMNSe2EodWfUg7Tk5bs" +
            "jUhVLK0zTSu+uQohRyk2BYR9No/lp9qHdaSRM5H6Lu0fUMRuzARsSd77mFU1GMzD5lXOMPV+LIj9TISsfBWdDD8LW4FFBmmWF" +
            "3zhLrb1kUprqUzmfZ1jQrFiCAGkl2EFkDSv4gXBFNv1kOriKrE" +
            "+dGtPxC5wwGlJR8ewzi3nmIW+3/7vD7EpBh5aR6108ABGdLn11l8S/cp1Lu5O4gYsU" +
            "/Rkof+ChPDcKILbnxlxAMNNlaGlwma79xkvMjlCP3+5vzs5di6mHtBJtssnjg==";


    public static PrivateKey loadPrivateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(STORE_TYPE);
        keyStore.load(new FileInputStream(SENDER_KEYSTORE), PASSWORD);
        return (PrivateKey) keyStore.getKey(SENDER_ALIAS, PASSWORD);
    }

    public static PublicKey loadPublicKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(STORE_TYPE);
        keyStore.load(new FileInputStream(RECEIVER_KEYSTORE), PASSWORD);
        Certificate certificate = keyStore.getCertificate(RECEIVER_ALIAS);
        return certificate.getPublicKey();
    }

}

