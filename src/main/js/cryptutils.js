import crypto from 'crypto';
import { charset, presets as options } from './presets';

export function random(bits, callback) {
  if (typeof callback !== 'function') {
    throw new Error('No callback provided to random');
  }
  crypto.randomBytes(bits / 8, callback);
}

export function pbkdf2(password, salt, presets, callback) {
  if (typeof presets === 'function') {
    callback = presets;
    presets = options.defaults;
  }
  if (typeof callback !== 'function') {
    throw new Error('No callback provided to pbkdf2');
  }
  crypto.pbkdf2(new Buffer(password, charset), new Buffer(salt, charset),
                presets.iterations, presets.keyBits / 8, presets.pbkdf2Digest, callback);
}

export function md5(value, callback) {
  if (typeof callback !== 'function') {
    throw new Error('No callback provided to md5');
  }
  let hash = undefined;
  try {
    hash = crypto.createHash('md5').update(value).digest();
  } catch (err) {
    callback(err);
  }
  if (hash !== undefined) {
    callback(null, hash);
  }
}
