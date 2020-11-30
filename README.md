# Required dependings:

`<orderEntry type="library" name="com.googlecode.json-simple:json-simple:1.1" level="project" />`

# Usefull OpenSSL comands:

Generate a 1024-bit RSA private key

`$ openssl genrsa -out private_key.pem 1024`

Generate a public key

`$ openssl rsa -in private_key.pem -outform PEM -pubout -out public_key.pem`

Convert private Key to PKCS#8 format (so Java can read it)

`$ openssl pkcs8 -topk8 -inform PEM -outform DER -in private_key.pem -out private_key.der -nocrypt`

Output public key portion in DER format (so Java can read it)

`$ openssl rsa -in private_key.pem -pubout -outform DER -out public_key.der`
