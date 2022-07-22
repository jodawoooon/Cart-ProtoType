module.exports = {
  transpileDependencies: true,
  // npm run serve 개발 진행시에 포트가 다르기때문에 프록시 설정
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080/',
        changeOrigin: true,
      }
    }
  },
  lintOnSave: false,
  outputDir: "../server/src/main/resources/static",  // 빌드 타겟 디렉토리
}
