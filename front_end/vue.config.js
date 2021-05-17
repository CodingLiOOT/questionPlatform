module.exports = {
  chainWebpack: config => {
    const fileRule = config.module.rule('file')
    fileRule.uses.clear()
    fileRule
      .test(/\.pdf|ico$/)
      .use('file-loader')
      .loader('file-loader')
      .options({
        limit: 10000,
      })
  },
  publicPath: './'
}
