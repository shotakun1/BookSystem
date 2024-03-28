<template>
  <el-table class="table" stripe :data="books.slice((currentTablePage - 1) * 10, currentTablePage * 10)" height="529.83px"
            style="width: 100%"

  >
    <el-table-column fixed type="index" width="50"/>
    <el-table-column label="编号" prop="bookId" width="100px" sortable></el-table-column>
    <el-table-column label="书名" prop="title" sortable></el-table-column>
    <el-table-column label="作者" prop="author" sortable></el-table-column>
    <el-table-column label="出版社" prop="publisher" sortable></el-table-column>
    <el-table-column label="价格" prop="price" sortable></el-table-column>
    <el-table-column fixed="right" width="350px">
      <template #header>
        <el-input size="small" v-model="search" style="max-width: 600px" placeholder="下拉框或输入框为空时查询全部"
                  :prefix-icon="Search">
          <template #prepend>
            <el-select size="small" v-model="select" placeholder="选择" style="width: 70px">
              <el-option label="书名" value="1"/>
              <el-option label="作者" value="2"/>
              <el-option label="出版社" value="3"/>
            </el-select>
          </template>
          <template #append>
            <el-button size="small" :icon="Search" @click="searchByOption(search,select)"/>
          </template>
        </el-input>
      </template>
      <template #default="{ row }">
        <el-button-group>
          <el-popconfirm title="是否删除" @confirm="deleteBook(row)">
            <template #reference>
              <el-button type="danger" :icon="Delete">删除</el-button>
            </template>
          </el-popconfirm>
          <el-button @click="openUpdatePopup(row)" :icon="Edit">更改</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import {Delete, Edit, Search} from '@element-plus/icons-vue'
import {ref} from "vue";

defineProps({
  books: {
    type: Array,
    required: true,
  },
  currentTablePage:Number
});

const emits = defineEmits(['deleteBook', 'openUpdatePopup', 'searchByOption']);

const deleteBook = (row) => {
  emits('deleteBook', row);
};

const openUpdatePopup = (row) => {
  emits('openUpdatePopup', row);
};

const searchByOption = (searchText, selectAttr) => {
  emits('searchByOption', searchText, selectAttr)
}

const search = ref('');
const select = ref('');

</script>

<style scoped>
.table {
  border-radius: 4px;
  background-color: rgba(255, 255, 255, 0.6);
}
</style>