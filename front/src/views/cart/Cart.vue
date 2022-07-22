<!-- 장바구니 목록 Master Component -->
<template>
  <div class="container main_container">
    <h3>장바구니</h3>
    <div class="full line-bar"></div>
    <div class="row">
      <div class="col-9">
        <div id="content-cart" class="mt-3">
          <el-row>
            <el-checkbox v-model="allSelectFlag" @click="allSelect">전체 선택</el-checkbox>
            <div class="mx-4">
              <el-button @click="batchDelete" :disabled="noneFlag">선택삭제</el-button>
            </div>
          </el-row>
          <div v-if="noneFlag">
            <el-empty description="장바구니에 담긴 상품이 없습니다">
              <el-button type="primary" @click="goProductList">상품 목록 보기</el-button>
            </el-empty>
          </div>
          <div id="delivery-group-cont" v-if="!noneFlag">
            <div v-if="ssgFlag" class="mb-5 mt-3">
              <el-row>
                <el-checkbox v-model="ssgSelectFlag" @click="ssgAllSelect"/>
                <p class="mx-2"><span class="cart-title">쓱배송</span> 상품 : {{ length(ssgCartItems) }}개</p>
              </el-row>
              <div v-for="(card, idx) in ssgCartItems" :key="idx">
                <CartCard
                    :card="card"
                    @click-check="setCartInfo"
                    @buy-cart-item="loadCartItems"
                    @delete-cart-item="loadCartItems"
                    @change-product-cnt="changeProductCnt"
                />
              </div>
            </div>
            <div v-if="basicFlag" class="mt-5 mb-5">
              <el-row>
                <el-checkbox v-model="basicSelectFlag" @click="basicAllSelect"/>
                <p class="mx-2"><span class="cart-title">택배배송</span> 상품 : {{ length(basicCartItems) }}개</p>
              </el-row>
              <div v-for="(card, idx) in basicCartItems" :key="idx">
                <CartCard
                    :card="card"
                    @click-check="setCartInfo"
                    @delete-cart-item="loadCartItems"
                    @change-product-cnt="changeProductCnt"
                />
              </div>
            </div>

            <div v-if="freshFlag" class="mt-5">
              <el-row>
                <el-checkbox v-model="freshSelectFlag" @click="freshAllSelect"/>
                <p class="mx-2"><span class="cart-title">새벽배송</span> 상품 : {{ length(freshCartItems) }}개</p>
              </el-row>
              <div v-for="(card, idx) in freshCartItems" :key="idx">
                <CartCard
                    :card="card"
                    @click-check="setCartInfo"
                    @delete-cart-item="loadCartItems"
                    @change-product-cnt="changeProductCnt"
                />
              </div>
            </div>
          </div>

        </div>
      </div>

      <div class="col-3 cart-aside">
        <CartTotalAmt
            :ssg-flag="ssgFlag"
            :basic-flag="basicFlag"
            :fresh-flag="freshFlag"
            :basic-total-amt="basicTotalAmt"
            :fresh-total-amt="freshTotalAmt"
            :ssg-total-amt="ssgTotalAmt"
            :total-amt="totalAmt"
            :total-product-cnt="totalProductCnt"
            @click-total-buy-button="clickTotalBuyButton"
        />
      </div>
    </div>
  </div>
</template>

<script>

import CartCard from "@/views/cart/CartCard";
import axios from "axios";
import Swal from "sweetalert2";
import CartTotalAmt from "@/views/cart/CartTotalAmt";
import router from '@/router/router.js';


export default {
  name: 'Cart',
  components: {CartTotalAmt, CartCard},
  data() {
    return {
      noneFlag: false,
      allSelectFlag: true,
      ssgSelectFlag: true,
      freshSelectFlag: true,
      basicSelectFlag: true,
      ssgCartItems: [],
      ssgTotalAmt: 0,
      freshCartItems: [],
      freshTotalAmt: 0,
      basicCartItems: [],
      basicTotalAmt: 0,
      totalAmt: 0,
      totalProductCnt: 0,
    };
  },
  computed: {
    ssgFlag() {
      return this.ssgCartItems != null && this.ssgCartItems.length > 0;
    },
    freshFlag() {
      return this.freshCartItems != null && this.freshCartItems.length > 0;
    },
    basicFlag() {
      return this.basicCartItems != null && this.basicCartItems.length > 0;
    }
  },
  methods: {
    loadCartItems() {
      axios.get('/api/cart/page', {
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Content-Type': 'application/json; charset = utf-8'
        }
      })
          .then(response => {
            this.ssgCartItems = response.data.ssgCartList;
            this.freshCartItems = response.data.freshCartList;
            this.basicCartItems = response.data.basicCartList;

            if (this.length(this.ssgCartItems) + this.length(this.freshCartItems) + this.length(this.basicCartItems) === 0) {
              this.noneFlag = true;
            }
            this.setCartInfo();
          })
          .catch(e => {
            Swal.fire({text: '장바구니 목록 조회에 실패했습니다.', icon: 'error'});
            console.log('error : ', e)
          })
    },
    setCartInfo() {
      let basicTotalAmt = 0;
      let freshTotalAmt = 0;
      let ssgTotalAmt = 0;

      let checkedBasicTotalAmt = 0;
      let checkedFreshTotalAmt = 0;
      let checkedSsgTotalAmt = 0;

      let totalCnt = 0;

      for (const item in this.ssgCartItems) {
        if (this.ssgCartItems[item].checked) {
          totalCnt += 1;
          checkedSsgTotalAmt += (this.ssgCartItems[item].amt * this.ssgCartItems[item].cnt);
        }
        ssgTotalAmt += (this.ssgCartItems[item].amt * this.ssgCartItems[item].cnt);
      }
      for (const item in this.basicCartItems) {
        if (this.basicCartItems[item].checked) {
          totalCnt += 1;
          checkedBasicTotalAmt += (this.basicCartItems[item].amt * this.basicCartItems[item].cnt);
        }
        basicTotalAmt += (this.basicCartItems[item].amt * this.basicCartItems[item].cnt);
      }
      for (const item in this.freshCartItems) {
        if (this.freshCartItems[item].checked) {
          totalCnt += 1;
          checkedFreshTotalAmt += (this.freshCartItems[item].amt * this.freshCartItems[item].cnt);
        }
        freshTotalAmt += (this.freshCartItems[item].amt * this.freshCartItems[item].cnt);
      }
      if(totalCnt === 0){
        //단, 선택(체크)된 상품(들)이 미존재 시 장바구니 내 모든 상품(들)의 합계 금액.
        this.totalProductCnt = this.length(this.freshCartItems) + this.length(this.ssgCartItems) + this.length(this.basicCartItems);
        this.ssgTotalAmt = ssgTotalAmt;
        this.basicTotalAmt = basicTotalAmt;
        this.freshTotalAmt = freshTotalAmt;
      }else{
        // 선택(체크)된 상품(들)이 존재하면 해당 상품들의 합계 금액.
        this.totalProductCnt = totalCnt;
        this.ssgTotalAmt = checkedSsgTotalAmt;
        this.basicTotalAmt = checkedBasicTotalAmt;
        this.freshTotalAmt = checkedFreshTotalAmt;
      }

      this.totalAmt = (ssgTotalAmt + basicTotalAmt + freshTotalAmt);
    },
    changeProductCnt() {
      this.setCartInfo();
    },
    clickTotalBuyButton() {
      let buyDtoList = [];

      if(this.totalProductCnt === 0){
        this.setCartInfo();
        this.allSelect();
      }
      Swal.fire({
        title: this.totalProductCnt + '건의 상품을 모두 구매하시겠습니까?',
        text: '총 금액 : ' + this.totalAmt + '원',
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '닫기'
      })
          .then((result) => {
            if (result.isConfirmed) {
              if(this.totalProductCnt === this.length(this.freshCartItems) + this.length(this.ssgCartItems) + this.length(this.basicCartItems)){
                //전체 상품 선택 시 모두 구매한다.
                if(this.freshCartItems != null) buyDtoList = buyDtoList.concat(this.freshCartItems);
                if(this.ssgCartItems != null) buyDtoList = buyDtoList.concat(this.ssgCartItems);
                if(this.basicCartItems != null) buyDtoList = buyDtoList.concat(this.basicCartItems);
              }else {
                //체크한 항목만 구매한다.
                for (const item in this.ssgCartItems) {
                  if (this.ssgCartItems[item].checked) {
                    buyDtoList.push(this.ssgCartItems[item]);
                  }
                }
                for (const item in this.basicCartItems) {
                  if (this.basicCartItems[item].checked) {
                    buyDtoList.push(this.basicCartItems[item]);
                  }
                }
                for (const item in this.freshCartItems) {
                  if (this.freshCartItems[item].checked) {
                    buyDtoList.push(this.freshCartItems[item]);
                  }
                }
              }

              axios.post('/api/cart/buy/', buyDtoList, {
                headers: {
                  'Access-Control-Allow-Origin': '*',
                  'Content-Type': 'application/json; charset = utf-8'
                }
              })
                  .then(response => {
                    Swal.fire({text: '구매가 성공적으로 완료되었습니다.', icon: 'success', confirmButtonText: '확인'});
                    this.loadCartItems();
                  })
                  .catch(e => {
                    Swal.fire({text: '상품 구매에 실패했습니다.', icon: 'error', confirmButtonText: '닫기'});
                    console.log(e);
                  });
            }
          });
    },
    length(items) {
      if (items == null) return 0;
      return items.length;
    },
    goProductList() {
      router.push('/shop/product');
    },
    allSelect() {
      this.ssgSelectFlag = !this.allSelectFlag;
      for (const item in this.ssgCartItems) {
        this.ssgCartItems[item].checked = !this.allSelectFlag;
      }
      this.basicSelectFlag = !this.allSelectFlag;
      for (const item in this.basicCartItems) {
        this.basicCartItems[item].checked = !this.allSelectFlag;
      }
      this.freshSelectFlag = !this.allSelectFlag;
      for (const item in this.freshCartItems) {
        this.freshCartItems[item].checked = !this.allSelectFlag;
      }
      this.setCartInfo();
    },
    ssgAllSelect() {
      for (const item in this.ssgCartItems) {
        this.ssgCartItems[item].checked = !this.ssgSelectFlag;
      }
      this.setCartInfo();
    },
    basicAllSelect() {
      for (const item in this.basicCartItems) {
        this.basicCartItems[item].checked = !this.basicSelectFlag;
      }
      this.setCartInfo();

    },
    freshAllSelect() {
      for (const item in this.freshCartItems) {
        this.freshCartItems[item].checked = !this.freshSelectFlag;
      }
      this.setCartInfo();
    },
    batchDelete() {
      let deleteList = [];
      for (const item in this.ssgCartItems) {
        if (this.ssgCartItems[item].checked) {
          deleteList.push(this.ssgCartItems[item].cartId);
        }
      }
      for (const item in this.basicCartItems) {
        if (this.basicCartItems[item].checked) {
          deleteList.push(this.basicCartItems[item].cartId);
        }
      }
      for (const item in this.freshCartItems) {
        if (this.freshCartItems[item].checked) {
          deleteList.push(this.freshCartItems[item].cartId);
        }
      }


      Swal.fire({
        text: '선택된 상품들을 장바구니에서 모두 삭제하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '닫기'
      })
          .then((result) => {
            if (result.isConfirmed) {
              axios.delete('/api/cart/carts', {
                params: {
                  deleteIdList : deleteList,
                },
                headers: {
                  'Access-Control-Allow-Origin': '*',
                  'Content-Type': 'application/json; charset = utf-8'
                }
              })
                  .then(response => {
                    Swal.fire({text: '장바구니에서 상품이 삭제되었습니다.', icon: 'success', confirmButtonText: '확인'});
                    this.loadCartItems();
                  })
                  .catch(e => {
                    Swal.fire({text: '장바구니 상품 삭제에 실패했습니다.', icon: 'error', confirmButtonText: '닫기'});
                    console.log(e);
                  });
            }
          });
    },
  },
  created() {
    this.loadCartItems();
  },
}
</script>

<style scoped>

.cart-aside {
  margin-top:50px;
}

.main_body {
  padding-right: 10%;
  padding-left: 10%;
  min-width: 1024px;

}

.cart-title {
  font-weight: 500;
  font-size: 15pt;
}


.delivery-group-cont {
  margin : 5px;
}

:deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
  background-color: #F7D147;
  border-color: #F7D147;
}

:deep(.el-checkbox__input.is-checked+.el-checkbox__label) {
  color: black;
}
</style>
