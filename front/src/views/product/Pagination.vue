<!-- 상품 목록 페이지바 Component -->
<template>
  <nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
      <li v-if="prev" class="page-item">
        <a class="page-link" href="#" aria-label="Previous" @click="paginationChanged(startPageIndex - 1)">
          <span aria-hidden="true">«</span>
        </a>
      </li>
      <li v-for="index in endPageIndex - startPageIndex + 1" :key="index"
          v-bind:class="{ active: startPageIndex + index - 1 === currentPageIndex }" class="page-item">
        <a @click="paginationChanged(startPageIndex + index - 1)" class="page-link"
           href="#">{{ startPageIndex + index - 1 }}</a>
        <!-- href 는 그대로, 커서 모양 유지-->
      </li>
      <li v-if="next" class="page-item">
        <a class="page-link" href="#" aria-label="Next" @click="paginationChanged(endPageIndex + 1)">
          <span aria-hidden="true">»</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "Pagination",
  props: {
    listRowCount: Number,
    pageLinkCount: Number,
    currentPageIndex: Number,
    boardListCount: Number,
  },
  computed: {
    pageCount() {
      return Math.ceil(this.boardListCount / this.listRowCount);
    },
    startPageIndex() {
      if (this.currentPageIndex % this.pageLinkCount === 0) {
        return (this.currentPageIndex / this.pageLinkCount - 1) * this.pageLinkCount + 1;
      } else {
        return Math.floor(this.currentPageIndex / this.pageLinkCount) * this.pageLinkCount + 1;
      }
    },
    endPageIndex() {
      let ret = 0;
      if (this.currentPageIndex % this.pageLinkCount === 0) {
        ret = (this.currentPageIndex / this.pageLinkCount - 1) * this.pageLinkCount + this.pageLinkCount;
      } else {
        ret = Math.floor(this.currentPageIndex / this.pageLinkCount) * this.pageLinkCount + this.pageLinkCount;
      }
      return ret > this.pageCount ? this.pageCount : ret;
    },
    prev() {
      return this.currentPageIndex > this.pageLinkCount;
    },
    next() {
      return this.endPageIndex < this.pageCount;
    },
  },
  methods: {
    // 부모에게 event 전달
    paginationChanged(pageIndex) {
      this.$emit('move-page', pageIndex);
    },
  },
};
</script>

<style scoped>
:deep(.page-item.active .page-link) {
  color: black;
  background-color: #F7D147;
  border-color: #F7D147;
  font-weight: 700;
}

:deep(.page-link) {
  color: black;
}
</style>
