import babel from 'rollup-plugin-babel';

export default {
  entry: 'src/main/js/index.js',
  dest: 'dist/jadcrypt.js',
  format: 'cjs',
  plugins: [
    babel({ exclude: 'node_modules/**', presets: [ "es2015-rollup" ] })
  ]
}
