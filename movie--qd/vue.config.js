const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

})

module.exports = {
  devServer: {
    port: 8088,
    proxy: {
      "/": {
        target: 'http://localhost:40015',
        ws: false,
        origin: true,
        pathRewrite: {
          "^/": "/"
        }
      }
    }
  },
  pages: {
    index: {
      entry: 'src/main.js',
      filename: 'index.html',
      template: 'public/index.html',
      title: 'Movie System',
      chunks: ['chunk-vendors', 'chunk-common', 'index']
    }
  }

}