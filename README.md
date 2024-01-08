# Security Signature Verification Application
**Author: Viraj Ranaware**

## Overview:

The application employes the security signature verification for verifying the origin and integrity of a message. It can be used for variety of applications.

**Data Integrity:**
- When transmitting sensitive information over a network, ensuring that the data has not been tampered with during transit is crucial.
- By digitally signing a message, the sender creates a unique checksum (signature) based on the message content. The recipient can then verify this signature to confirm the integrity of the received data.

**Message Authentication:**
- The digital signature provides a means of verifying that the message was indeed sent by the expected sender.
- If the signature is valid, the recipient can trust the origin of the message.

**Secure Communication:**
- Digital signatures help in ensuring that the data exchanged between the parties is both secure and authentic.
- Verifying the signature ensures that the data has not been modified and that it originated from the expected sender.


## Application Workflow
**Certificate Generation:**
- The sender generates a key pair and creates a self-signed certificate.
- The sender shares the public key via the certificate with the receiver.

**Signing a Message:**

- The sender sends a POST request to http://127.0.0.1:8080/sign with a payload.
- The application signs the payload and returns the digitally signed message (checksum).

**Verifying a Message:**
- The receiver sends a POST request to http://127.0.0.1:8080/verify with a payload and the received checksum.
- The application verifies the payload against the provided checksum.

## Installation Steps:

- Create JKS certificate using below command (Use keytool which is a tool which comes with JDK installation):

```
keytool -genkeypair -alias senderKeyPair -keyalg RSA -keysize 2048 -dname "CN=VIRAJ" -validity 3650 -keystore sender_keystore.jks -storepass welcome123
```

This will create JKS java specific self signed certificate.


- Now you need to export our public key from pair of public-private keys to self signed certificate using below command:

```
keytool -exportcert -alias senderKeyPair -storetype -keystore sender_keystore.jks -file sender_certificate_jks.cer -rfc -storepass welcome123
```

This sender_certificate_jks.cer certificate can be published to clients.


- Load the public key into Keystore using the importcert command as below:
```
keytool -importcert -alias receiverKeyPair -storetype -keystore receiver_keystore.jks -file sender_certificate_jks.cer -rfc -storepass welcome123
```

-------
- Start the application using below command:

```
mvn spring-boot:run
```

-------

## URL to access API endpoints:

**1. Signing a message:**

```
POST http://127.0.0.1:8080/sign 
{
  "payload": "content is private"
}
```

**2. Verifying at receiver end:**
```
POST http://127.0.0.1:8080/verify
{
  "payload": "content is private",
  "checksum": "QfEBLwzhg9ROvdy0koL2KsId2+nueQmKuxE4jDWJ/DPLhPIPlZqBWiYOvpRxwJEmmCmHbrRT+Hs+KZ04YLVVcbL0qUXyvXhh5DaAiMoYYtWbrqSXh12p222a01bCy2iLS5AaA6ngJRUo/Vo0+UUrJP58hr+sRGaqDa7ZFVwKhDNXFP1yPzIa4Hbs3lANkuTUTRhbKoh6Cv0exkqjrxjZz1JDAmq7caoqhcb6bbFHd9/lB7NAEd8TPEAz9G/imItWwxdwNOHlzjG3LTVFY+zDw/Su/lsKKSTTqij3ir4Vwmz4WdBdFGsA5BP6jZpLjTFSOJUFRKvUxJApqXi8p/6koQ=="
}
```

Checksum is the digitally signed message received as the response of signing API.

