import crypto from 'crypto';
import { charset, presets as options } from './presets';
import { pbkdf2, md5 } from './cryptutils';

export function encryptRaw(plain, key, iv, presets, callback) {
  if (typeof presets === 'function') {
    callback = presets;
    presets = options.defaults;
  }
  if (typeof callback !== 'function') {
    throw new Error('No callback provided to encryptRaw');
  }

  let encrypted = undefined;
  try {
    const cipher = crypto.createCipheriv(`aes-${presets.keyBits}-cbc`, key, iv);
    const data = cipher.update(plain);
    const remaining = cipher.final();
    encrypted = Buffer.concat([data, remaining], data.length + remaining.length);
  } catch (err) {
    callback(err);
  }
  if (encrypted !== undefined) {
    callback(null, encrypted);
  }
}

export function encrypt(plain, password, salt, presets, callback) {
  if (typeof presets === 'function') {
    callback = presets;
    presets = options.defaults;
  }
  if (typeof callback !== 'function') {
    throw new Error('No callback provided to encrypt');
  }
  
  pbkdf2(password, salt, presets, (err, key) => {
    if (err) {
      callback(err);
    } else {
      md5(salt, (err, iv) => {
        if (err) {
          callback(err);
        } else {
          encryptRaw(Buffer.from(plain, charset), key, iv, presets, (err, encrypted) => {
            if (err) {
              callback(err);
            } else {
              callback(null, encrypted.toString('hex'));
            }
          });
        }
      });
    }
  });
}

export function decryptRaw(encrypted, key, iv, presets, callback) {
  if (typeof presets === 'function') {
    callback = presets;
    presets = options.defaults;
  }
  if (typeof callback !== 'function') {
    throw new Error('No callback provided to decryptRaw');
  }
  
  let decrypted = undefined;
  try {
    const cipher = crypto.createDecipheriv(`aes-${presets.keyBits}-cbc`, key, iv);
    const data = cipher.update(encrypted);
    const remaining = cipher.final();
    decrypted = Buffer.concat([data, remaining], data.length + remaining.length);
  } catch (err) {
    callback(err);
  }
  if (decrypted !== undefined) {
    callback(null, decrypted);
  }
}

export function decrypt(encrypted, password, salt, presets, callback) {
  if (typeof presets === 'function') {
    callback = presets;
    presets = options.defaults;
  }
  if (typeof callback !== 'function') {
    throw new Error('No callback provided to decrypt');
  }
  
  pbkdf2(password, salt, presets, (err, key) => {
    if (err) {
      callback(err);
    } else {
      md5(salt, (err, iv) => {
        if (err) {
          callback(err);
        } else {
          decryptRaw(Buffer.from(encrypted, 'hex'), key, iv, presets, (err, decrypted) => {
            if (err) {
              callback(err);
            } else {
              callback(null, decrypted.toString(charset));
            }
          });
        }
      });
    }
  });
}
