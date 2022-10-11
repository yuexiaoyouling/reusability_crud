const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  /* webpack-dev-server 相关配置 */
  devServer: {
    /* 自动打开浏览器 */
    open: true,
    /* 设置为0.0.0.0则所有的地址均能访问 */
    host: '0.0.0.0',
    port: 8080,
    https: false,
    /* 使用代理 */
    proxy: { // string | Object
        '/api': {
        /* 目标代理服务器地址 */
        target: 'http://localhost:9080',
        /* 允许跨域 */
        changeOrigin: true,
        pathRewrite: {
          '^/api': '' //规定请求地址以什么作为开头
        }
      
      },
    },
  },

})
