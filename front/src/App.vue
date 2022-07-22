<template>
  <div id="app">
    <div class="container">
      <div class="header">
        <Menu/>
      </div>
      <div class="main app-main">
        <div id="content">
          <router-view></router-view>
        </div>
      </div>
      <div class="footer">
        <p>SSG TECH LOOKIE</p>
        <p>DAUN JO (jodawoooooon@naver.com)</p>
      </div>
    </div>
  </div>
</template>

<script>
import Menu from '@/components/Menu';

export default {
  name: 'App',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    Menu,
  }
}
</script>

<style>
@import '@/components/main.css';


</style>
