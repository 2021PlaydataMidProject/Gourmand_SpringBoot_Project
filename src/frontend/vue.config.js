const webpack = require('webpack');

module.exports = {
  devServer: {
    overlay: false,
  },
  configureWebpack: {
    // Set up all the aliases we use in our app.
    plugins: [
      new webpack.optimize.LimitChunkCountPlugin({
        maxChunks: 6
      })
    ]
  },
  pwa: {
    name: '구르망',
    themeColor: '#172b4d',
    msTileColor: '#172b4d',
    appleMobileWebAppCapable: 'yes',
    appleMobileWebAppStatusBarStyle: '#172b4d'
  },
  css: {
    // Enable CSS source maps.
    sourceMap: process.env.NODE_ENV !== 'production'
  },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:80',
        ws: true,
        changeOrigin: true,
      },
      '/res/': {
        target: 'http://localhost:80',
        ws: true,
        changeOrigin: true,
      },
      '/auth': {
        target: 'http://localhost:80',
        ws: true,
        changeOrigin: true,
      },
      '/#/auth': {
        target: 'http://localhost:80',
        ws: true,
        changeOrigin: true,
      },
      '/user/': {
        target: 'http://localhost:80',
        ws: true,
        changeOrigin: true,
      },
      '/rev/': {
        target: 'http://localhost:80',
        ws: true,
        changeOrigin: true,
      }

    }
  }
};
