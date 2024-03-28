<template>
  <div class="pagination">
  <el-pagination background class="page" layout="prev, pager, next"
                 @current-change="handleCurrentChange"
                 :current-page="currentPage"
                 :total="tableLength" />
  </div>
  <el-button type='default' style="width: 100%" @click="openAddPopup()" :icon="Plus">添加</el-button>

</template>

<script setup>
import {Plus} from "@element-plus/icons-vue";


defineProps({
  currentPage:Number,
  tableLength:Number
})

const emits = defineEmits(['openAddPopup','handleCurrentChange']);
const openAddPopup = () => {
  emits('openAddPopup');
}

const handleCurrentChange = (val) => {
  emits('handleCurrentChange',val);
}
</script>

<style scoped>
.pagination{
  margin-bottom: 10px;
}
</style>