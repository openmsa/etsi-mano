openssl cms -sign -signer userkey.crt -inkey ~/ovi.pem -nodetach -md sha256 -in *.csar -outform der -out sample.cms-der -noattr
