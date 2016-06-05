import babel from 'rollup-plugin-babel';
import multiEntry from 'rollup-plugin-multi-entry';

export default {
  entry: 'src/test/js/**/*_test.js',
  dest: 'target/test_bundle.js',
  format: 'cjs',
  plugins: [
    babel({ exclude: 'node_modules/**', presets: [ "es2015-rollup" ] }),
    multiEntry()
  ]
}
