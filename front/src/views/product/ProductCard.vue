<!-- 상품 Card Component -->
<template>
  <el-card id="product-card" shadow="none">
    <el-row class="tag-row">
      <el-tag
          :class="`mb-2 tag ${card.groupNm}`"
          effect="dark"
      >{{ card.groupNm }}
      </el-tag
      >
    </el-row>
    <el-row
        class="ptb-5"
    >
      <img src="http://placeimg.com/250/250/tech" class="image fit-image"/>
    </el-row>
    <div class="title">
      <div>{{ card.productNm }}</div>
    </div>
    <div class="desc">
      <div><span class="amt">{{ card.amt }}</span>원</div>
      <div :class="`stock ${card.stockCnt >= 3 ? 'stock_color' : 'stock_color_danger'}`"><span class="sold-out"
                                                                                               v-if="card.stockCnt < 3"> {{
          card.stockCnt === 0 ? '품절' : '품절임박'
        }} </span>
        남은 수량 : {{ card.stockCnt }}개
      </div>

    </div>
    <div class="button">
      <el-button :disabled="card.stockCnt==0" @click="addCart(card)">장바구니 담기</el-button>
    </div>
  </el-card>
</template>

<script>
import axios from "axios";
import Swal from 'sweetalert2';
import router from '@/router/router.js';

export default {
  name: "ProductCard",
  props: {
    card: Object
  },
  methods: {
    addCart(data) {
      if (data.stockCnt === 0) {
        Swal.fire({text: data.productNm + ' 상품의 재고가 부족합니다.', icon: 'warning', confirmButtonText: '닫기'});
        return;
      }
      axios.post('/api/cart/carts', {
        productId: data.productId,
        cnt: 1,
        amt: data.amt,
      }, {
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Content-Type': 'application/json; charset = utf-8'
        }
      })
          .then(response => {
            Swal.fire({
              icon: 'success',
              title: data.productNm + '상품을 장바구니에 담았습니다.',
              text: response.data.cnt > 1 ? '이미 담으신 상품이 있습니다. 장바구니로 이동하시겠습니까?' : '',
              showCancelButton: true,
              confirmButtonText: '장바구니 이동',
              confirmButtonColor: '#F7D147',
              cancelButtonText: `쇼핑 계속 하기`,
            }).then((result) => {
              if (result.isConfirmed) {
                router.push('/shop/cart');
              }
            })

          })
          .catch(e => {
            if (e.response.status === 400) {
              Swal.fire({
                text: '상품의 재고보다 구매 희망 수량이 더 많습니다.', icon: 'error', showCancelButton: true,
                confirmButtonText: '장바구니 이동',
                cancelButtonText: `닫기`,
                confirmButtonColor: '#F7D147',
              }).then((result) => {
                if (result.isConfirmed) {
                  router.push('/shop/cart');
                }
              });
            } else {
              Swal.fire({text: '장바구니 저장에 실패했습니다', icon: 'error', confirmButtonText: '닫기'});
            }
            console.log(e);
          })
    }
  }
};
</script>

<style scoped>
.image {
  width: 100%;
  height: 230px;
  display: block;
}

.desc {
  height: 45px;
}

.title {
  height: 25px;
}


.button {
  display: flex;
  float: right;
  height: 30px;
}

:deep(.el-card__body) {
  padding: 0px;
}


.sold-out {
  font-weight: 600;
  font-size: 10pt;
}

.쓱배송 {
  background-color: #F7D147;
}

.새벽배송 {
  background-color: #A7B5CB;
}

.택배 {
  background-color: #B1C84F;
}

.tag {
  height: 20px;
  border: none;
  color: #000;
  font-weight: 700;
}

.fit-image {
  object-fit: cover;
}

.stock {
  font-size: 9pt;
}

.stock_color {
  color: #A1A1A1;
}

.stock_color_danger {
  color: #DE4646;
}

.ptb-5 {
  padding-top: 5px;
  padding-bottom: 5px;
}

</style>