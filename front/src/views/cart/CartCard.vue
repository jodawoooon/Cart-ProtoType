<!-- 장바구니 상품 카드 Component -->
<template>
  <el-card id="cart-card" shadow="never">

    <div class="row cart-content">
      <div class="col-1">
        <el-checkbox v-model="cardData.checked" @change="clickCheck"/>
      </div>
      <div class="col-2">
        <div>
          <img src="http://placeimg.com/100/100/tech"/>
        </div>
      </div>
      <div class="col-6">
        <div>
          <h5>{{ cardData.productNm }}</h5>
          <p><span class="amt">{{ cardData.amt }}</span>원</p>
          <el-input-number v-model="cardData.cnt" size="small" :min="1" :max="100" @change="changeCnt"/>
        </div>
      </div>

      <div class="col-3">
        <div class="mb-1">
          <el-button type="danger" class="full" @click="clickBuy">바로 구매하기</el-button>
        </div>
        <div>
          <el-button type="info" class="full" @click="clickDelete">삭제하기</el-button>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";
import router from "@/router/router";

export default {
  name: "CartCard",
  props: {
    card: Object,
  },
  data() {
    return {
      cardData: this.card,
    };
  },
  watch: {
    card() {
      this.cardData = this.card;
    }
  },
  methods: {
    changeCnt() {
      axios.put('/api/cart/carts', this.cardData)
          .then(response => {
            console.log(response);
            this.$emit('change-product-cnt');
          })
          .catch(e => {
            if (e.response.status === 400) {
              Swal.fire({text: e.response.data.message, icon: 'error', confirmButtonText: '닫기'});
              this.cardData.cnt -= 1;
            } else {
              Swal.fire({text: '장바구니 상품 갯수 변경에 실패했습니다', icon: 'error', confirmButtonText: '닫기'});
            }
            console.log(e);
          });
    },
    clickCheck() {
      this.$emit('click-check');
    },
    clickBuy() {
      let buyDtoList = [];

      Swal.fire({
        title: this.cardData.productNm + ' 상품 ' + this.cardData.cnt + '건을 구매하시겠습니까?',
        text: '총 금액 : ' + (this.cardData.amt * this.cardData.cnt) + '원',
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '닫기'
      })
          .then((result) => {
            if (result.isConfirmed) {
              buyDtoList.push(this.cardData);
              axios.post('/api/cart/buy/', buyDtoList, {
                headers: {
                  'Access-Control-Allow-Origin': '*',
                  'Content-Type': 'application/json; charset = utf-8'
                }
              })
                  .then(response => {
                    Swal.fire({text: '구매가 성공적으로 완료되었습니다.', icon: 'success', confirmButtonText: '확인'});
                    this.$emit('buy-cart-item');
                  })
                  .catch(e => {
                    Swal.fire({text: '상품 구매에 실패했습니다.', icon: 'error', confirmButtonText: '닫기'});
                    console.log(e);
                  });
            }
          });
    },
    clickDelete() {
      Swal.fire({text: this.cardData.productNm + ' 상품을 장바구니에서 삭제하시겠습니까?', icon: 'warning', showCancelButton: true,})
          .then((result) => {
            if (result.isConfirmed) {
              axios.delete('/api/cart/' + this.cardData.cartId)
                  .then(response => {
                    Swal.fire({text: '장바구니에서 상품이 삭제되었습니다.', icon: 'success'});
                    this.$emit('delete-cart-item');
                  })
                  .catch(e => {
                    Swal.fire({text: '장바구니 상품 삭제에 실패했습니다.', icon: 'error'});
                    console.log(e);
                  });
            }
          });
    }
  }
};
</script>

<style scoped>


.cart-content {
  display: flex;
  align-items: center;
}
</style>