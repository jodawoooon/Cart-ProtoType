<!-- 장바구니 구매 금액 카드 Component-->
<template>
  <div class="card custom-card">
    <div class="card-body">
      <div class="card-title">
        <el-icon>
          <Goods/>
        </el-icon>
        전체상품 : {{ totalProductCnt }}개
      </div>
      <div class="amt-desc">
        <div v-if="ssgFlag" class="row">
          <div class="col-4">쓱배송</div>
          <div class="col-8"><span class="amt">{{ ssgTotalAmt }}</span>원</div>
        </div>
        <div v-if="basicFlag" class="row">
          <div class="col-4">택배배송</div>
          <div class="col-8"><span class="amt">{{ basicTotalAmt }}</span>원</div>
        </div>
        <div v-if="freshFlag" class="row">
          <div class="col-4">새벽배송</div>
          <div class="col-8"><span class="amt">{{ freshTotalAmt }}</span>원</div>
        </div>
        <el-divider/>
        <div class="total-amt">최종결제금액 : <span class="amt">{{ totalAmt }}</span>원</div>
      </div>
      <el-button class="full" type="danger" @click="clickTotalBuyButton">주문하기</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CartTotalAmt',
  props: ["totalProductCnt", "ssgFlag", "basicFlag", "freshFlag", "totalAmt", "ssgTotalAmt", "basicTotalAmt", "freshTotalAmt"],
  methods: {
    clickTotalBuyButton() {
      this.$emit('click-total-buy-button');
    }
  }
}
</script>

<style scoped>
.custom-card {
  padding: 20px 10px;
}

.amt-desc {
  padding: 50px 0px 20px 0px;
  font-size: 10pt;
  /* width: 100% */

}

.total-amt {
  color: #DE4646;
}
</style>