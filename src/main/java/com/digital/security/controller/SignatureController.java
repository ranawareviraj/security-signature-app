package com.digital.security.controller;

import com.digital.security.digitalsignature.SignMessage;
import com.digital.security.digitalsignature.VerifySignature;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DigitalSignatureController {

    private final SignMessage signMessage;
    private final VerifySignature verifySignature;

    @Autowired
    public DigitalSignatureController(SignMessage signMessage, VerifySignature verifySignature) {
        this.signMessage = signMessage;
        this.verifySignature = verifySignature;
    }

        @PostMapping("/sign")
        public ResponseEntity<String>digitalSignature(@RequestBody Message message){
            return ResponseEntity.ok(signMessage.digitalSignature(message.getPayload()));
        }

        @PostMapping("/verify")
        public ResponseEntity<Boolean>digitalVerification(@RequestBody Message message){
            return ResponseEntity.ok(verifySignature.digitalVerification(message.getChecksum(), message.getPayload()));
        }
    }

    @Data
    class Message {
        private String payload;
        private String checksum;
    }