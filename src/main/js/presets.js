export const charset = 'utf8';

export const presets = {
  defaults: {
    keyBits: 256,
    iterations: 65536,
    pbkdf2Digest: 'sha256'
  },
  mobile: {
    keyBits: 128,
    iterations: 4096,
    pbkdf2Digest: 'sha1'
  }
};
