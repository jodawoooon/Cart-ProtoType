<!-- 상품 목록 Master Component -->
<template>
  <div class="container main_container">
    <h3>상품 목록</h3>
    <div class="full line-bar"></div>
    <div>
      <div>
        <div class="row option-row">
          <div class="col-auto me-auto">
            <el-checkbox-group v-model="filterList">
              <el-checkbox label="10">쓱배송</el-checkbox>
              <el-checkbox label="20">새벽배송</el-checkbox>
              <el-checkbox label="30">택배</el-checkbox>
            </el-checkbox-group>
          </div>
          <div class="col-auto">
            <el-select v-model="sortOption" placeholder="정렬 기준">
              <el-option
                  v-for="item in sortOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </div>
        </div>
        <div>
          <div class="custom-row row justify-content-start" v-if="productItems.length > 0">
            <div class="col-3" v-for="(card, idx) in productItems" :key="idx">
              <ProductCard
                  :card="card"
                  class="product_card"
                  @refresh-page="loadProductItems"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="footer">
        <Pagination
            :list-row-count="this.sizePerPage"
            :page-link-count="this.sizePerPage"
            :current-page-index="this.page"
            :board-list-count="this.totalElement"
            @move-page="movePage"
        />
      </div>
    </div>
  </div>
</template>
<script>
import ProductCard from "@/views/product/ProductCard";
import axios from "axios";
import Pagination from "@/views/product/Pagination";
import Swal from "sweetalert2";

export default {
  name: 'Product',
  components: {ProductCard, Pagination},
  data() {
    return {
      productItems: [],
      page: 1,
      sizePerPage: 8,
      totalElement: 0,
      totalPage: 0,
      sortOption: 'alphabetical',
      filterList: ['10', '20', '30'],
      sortOptions: [
        {
          value: 'alphabetical',
          label: '가나다순',
        },
        {
          value: 'new',
          label: '신상품',
        },
        {
          value: 'lowest',
          label: '가격 (낮은 순)',
        },
        {
          value: 'highest',
          label: '가격 (높은 순)',
        }
      ],
    };
  },
  computed: {
    boardListCount() {
      return this.totalElement;
    },
    currentPageIndex() {
      return this.page;
    },
  },
  methods: {
    loadProductItems() {
      axios.get('/api/product/page', {
        params: {
          page: this.page - 1,
          sizePerPage: this.sizePerPage,
          sortOption: this.sortOption,
          filterList: this.filterList,
        },
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Content-Type': 'application/json; charset = utf-8'
        }
      })
          .then(response => {
            this.productItems = response.data.content;
            this.totalElement = response.data.totalElements;
            this.totalPage = response.data.totalPages;
          })
          .catch(e => {
            Swal.fire({text: '상품 목록 조회에 실패했습니다.', icon: 'error', confirmButtonText: '닫기'});
            console.log('error : ', e)
          })
    },
    movePage(pageIndex) {
      this.page = pageIndex;
      this.loadProductItems();
    }
  },
  watch: {
    sortOption() {
      this.loadProductItems();
    },
    filterList(val) {
      if (val.length === 0) {
        Swal.fire({text: '선택된 상품 그룹이 없습니다. 전체 그룹을 재조회합니다.', icon: 'info', confirmButtonText: '확인'});
        this.filterList = ['10', '20', '30'];
        return;
      }
      this.loadProductItems();
    }
  },
  created() {
    this.loadProductItems();
  },

}
</script>
<style scoped>

:deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
  background-color: #F7D147;
  border-color: #F7D147;
}

:deep(.el-checkbox__input.is-checked+.el-checkbox__label) {
  color: black;
}

:deep(.el-button--info) {
  color: black;
  border-color: #fff;
  background-color: #fff;
}

:deep(.el-button--info:hover) {
  color: black;
  border-color: #fff;
  background-color: #fff;
}

:deep(.el-button--info:focus) {
  color: black;
  border-color: #fff;
  background-color: #fff;
}

.product_card {
  margin: 20px;
}

.custom-row {
  min-width: 1024px;
}

.main-body {
  width: 100%;
  display: block;
}

.option-row {
  margin-bottom: 10px;
  margin-left: 10px;
  margin-right: 10px;
  padding-left: 15px;
  padding-right: 15px;
  padding-top: 20px;
  min-width: 1024px;
}

</style>