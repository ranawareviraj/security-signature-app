Installations**

Create JKS certificate using below command (Use keytool which is a tool which comes with JDK installation):

`keytool -genkeypair -alias senderKeyPair -keyalg RSA -keysize 2048 -dname "CN=Nikita" -validity 3650 -keystore sender_keystore.jks -storepass welcome123`

this will create JKS java specific self signed certificate.

Now you need to export our public key from pair of public-private keys to self signed certificate using below command:

`keytool -exportcert -alias senderKeyPair -storetype -keystore sender_keystore.jks -file sender_certificate_jks.cer -rfc -storepass welcome123`

this sender_certificate_jks.cer certificate can be published to clients.

Load the public key into Keystore using the importcert command as below:

`keytool -importcert -alias receiverKeyPair -storetype -keystore receiver_keystore.jks -file sender_certificate_jks.cer -rfc -storepass welcome123`

-------
Start the application using below command:
`mvn spring-boot:run`

**URL to access API end points**:

Signing a message:
`POST http://127.0.0.1:8080/sign 
{
  "payload": "content is private"
}`

Verifying at receiver end:
`POST http://127.0.0.1:8080/verify
{
  "payload": "content is private",
  "checksum": "QfEBLwzhg9ROvdy0koL2KsId2+nueQmKuxE4jDWJ/DPLhPIPlZqBWiYOvpRxwJEmmCmHbrRT+Hs+KZ04YLVVcbL0qUXyvXhh5DaAiMoYYtWbrqSXh12p222a01bCy2iLS5AaA6ngJRUo/Vo0+UUrJP58hr+sRGaqDa7ZFVwKhDNXFP1yPzIa4Hbs3lANkuTUTRhbKoh6Cv0exkqjrxjZz1JDAmq7caoqhcb6bbFHd9/lB7NAEd8TPEAz9G/imItWwxdwNOHlzjG3LTVFY+zDw/Su/lsKKSTTqij3ir4Vwmz4WdBdFGsA5BP6jZpLjTFSOJUFRKvUxJApqXi8p/6koQ=="
}`

checksum is the digitally signed message received as the response of signing API.

