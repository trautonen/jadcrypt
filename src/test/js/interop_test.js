import fs from 'fs';
import { strictEqual } from 'assert';
import { charset } from '../../main/js/presets';
import { decryptRaw, decrypt } from '../../main/js/jadcrypt';

const key = Buffer.from(fs.readFileSync("./src/test/resources/key.txt", { encoding: 'utf8' }), 'hex');
const password = fs.readFileSync("./src/test/resources/password.txt", { encoding: 'utf8' });

describe('javascript jadcrypt', () => {
  it('decrypts raw content to correct message', (done) => {
    const encrypted = fs.readFileSync("./src/test/resources/encrypted-raw.txt", { encoding: 'utf8' });
    const [iv, content] = encrypted.split(':');

    decryptRaw(Buffer.from(content, 'hex'), key, Buffer.from(iv, 'hex'), (err, decrypted) => {
      if (err) {
        throw err;
      } else {
        strictEqual(decrypted.toString(charset), 'Hello world! This is sparta!');
      }
      done();
    });
  });
  
  it('decrypts salted content to correct message', (done) => {
    const encrypted = fs.readFileSync("./src/test/resources/encrypted-salt.txt", { encoding: 'utf8' });
    const [salt, content] = encrypted.split(':');

    decrypt(content, password, salt, (err, decrypted) => {
      if (err) {
        throw err;
      } else {
        strictEqual(decrypted.toString(charset), 'Hello world! This is sparta!');
      }
      done();
    });
  });
});
