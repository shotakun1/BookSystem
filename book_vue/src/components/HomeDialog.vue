<template>
  <div class="dialog">
    <el-dialog width="400px" :model-value="popup" :title="title" @close="closePopup($refs.submitForm)" destroy-on-close>

      <el-form :model="editedBook" :rules="formRules" ref="submitForm" label-width="100px">
        <el-form-item label="书名" prop="title" required>
          <el-input class="input" v-model="editedBook.title"></el-input>
        </el-form-item>
        <el-form-item label="作者" prop="author" required>
          <el-input class="input"  v-model="editedBook.author"></el-input>
        </el-form-item>
        <el-form-item label="出版社" prop="publisher" required>
          <el-input class="input"  v-model="editedBook.publisher"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price" required>
          <el-input class="input"  v-model.number="editedBook.price" type="number"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button v-if="addOption" type="primary" @click="addBook($refs.submitForm)">确认</el-button>
        <el-button v-else-if="updateOption" type="primary" @click="updateBook($refs.submitForm)">确认</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>

defineProps({
  popup: Boolean,
  editedBook: {},
  formRules: Object,
  addOption: Boolean,
  updateOption: Boolean,
  title: String
});

const emits = defineEmits(['close-popup', 'add-book', 'update-book']);

const closePopup = (ref) => {
  emits('close-popup', ref);
};

const addBook = (ref) => {
  emits('add-book', ref);
};

const updateBook = (ref) => {
  emits('update-book', ref);
};
</script>

<style scoped>

</style>
