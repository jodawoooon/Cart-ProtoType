<!-- NotFound Component -->
<template>
  <div>
    <div class="container main_container">
      <div class="not-found-text">
        <p>페이지를 찾을 수 없습니다</p>
      </div>
      <div class="mb-5 pb-5">
        <el-button type="danger" @click="goProductList">상품 목록 보기</el-button>
      </div>

    </div>
  </div>
</template>

<script>
import router from "@/router/router";

export default {
  name: 'NotFoundVue',
  methods: {
    goProductList() {
      router.push('/shop/product');
    },
  }
}
</script>

<style scoped>
.not-found-text {
  height: 200px;
  display: flex;
  align-items: end;
  font-size: 20pt;
  font-weight: 700;
}
</style>