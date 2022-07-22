<!-- 상단 Nav 메뉴바 Component -->
<template>
  <div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
      <span class="navbar-brand nav-logo"><router-link to="/shop/product">SSG.COM</router-link></span>
      <span aria-current="page"> <router-link to="/shop/product"><el-icon><Goods/></el-icon> 상품 목록</router-link></span>
      <span> <router-link to="/shop/cart"><el-icon><ShoppingCart/></el-icon> 장바구니</router-link></span>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'Menu'

}
</script>

<style scoped>
a {
  color: #000000;
  text-decoration: none;
  margin-right: 30px;
}

.nav-logo {
  font-weight: bolder;
  font-size: 20pt;
}
</style>