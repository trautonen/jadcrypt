Jadcrypt
========

**Jadcrypt** is _simple Java and Node.js encryption library_ which provides consistent and simple
programming interface for message encryption in both environments without compromising security.
The library provides zero configuration interface where you can encrypt messages in one environment
and decrypt in other without any hassle.

The library uses **aes-256-cbc** (or **aes-128-cbc** in mobile mode) algorithm to encrypt the
messages. The algorithm requires 256 or 126 bit secret key (depending on preset mode) and 128 bit
random initialization vector for each encrypted message. But for simplicity also arbitrary length
string password and salt can be used.

The programming interfaces for Java and Node are similar as possible. Java version is synchronous
and Node version uses callbacks in asynchronous manner.


### Java interface:

_byte[] encryptRaw(byte[] plain, byte[] key, byte[] iv)_  
_String encrypt(String plain, String password, String salt, Presets presets)_  
_String encrypt(String plain, String password, String salt)_

_byte[] decryptRaw(byte[] encrypted, byte[] key, byte[] iv)_  
_String decrypt(String encrypted, String password, String salt, Presets presets)_  
_String decrypt(String encrypted, String password, String salt)_


### Node interface:

_encryptRaw(plain, key, iv, presets, callback)_  
_encrypt(plain, password, salt, presets, callback)_

_decryptRaw(encrypted, key, iv, presets, callback)_  
_decrypt(encrypted, password, salt, presets, callback)_
