<template>
  <el-text size="large" tag="b"><h2>图书管理系统</h2></el-text>
  <el-text><h3>欢迎用户:{{ username }}</h3></el-text>
  <el-button id="exit" type="success" :icon="SwitchButton"
             @click="logout" circle/>
</template>
<script setup>
import {SwitchButton} from "@element-plus/icons-vue";

defineProps({
  username: String
});

const emits = defineEmits(['logout']);
const logout = () => {
  emits('logout');
}
</script>

<style scoped>
h2 {
  color: antiquewhite;
  width: 80%;
  display: inline-block;
  margin-top: 12px;
}

h3 {
  color: #5b9a11;
  width: 15%;
  display: inline-block;
  margin-top: 20px;
}

#exit {
  margin-top: -5px;
}
</style>