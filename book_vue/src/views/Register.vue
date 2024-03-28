<template>

  <el-form class="register-form" :model="formData" :rules="formRules" ref="registerForm"
           status-icon>
    <h2>新用户注册</h2>
    <el-form-item label="用户名" prop="username"  label-width="100px">
      <el-input v-model="formData.username" style="width: 85%" placeholder="用户名由1-15位长的字符组成" maxlength="15"
                clearable></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password"  label-width="100px">
      <el-input type="password" v-model="formData.password" style="width: 85%"
                placeholder="密码由8-20位长的字符组成" minlength="8"
                maxlength="20" clearable show-password></el-input>
    </el-form-item>
    <el-form-item class="outside_buttons">
      <el-button class="buttons" type="primary" @click="register($refs.registerForm)">注册</el-button>
    </el-form-item>
    <el-form-item class="outside_links">
      <el-link class="links" type="primary" @click="backToLogin">返回登录</el-link>
    </el-form-item>
  </el-form>
</template>

<script src="@/js/register.js">

</script>


<style scoped>
h2 {
  margin-top: 0;
  margin-bottom: 7%;
  text-align: center;
  color: #333; /* 设置字体颜色 */
}
.register-form {
  max-width: 400px;
  margin: 13% auto;
  padding: 40px 5px 30px 5px;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.buttons{
  margin: 10px auto 0 auto;
  width:77%
}
.outside_buttons{
  margin-bottom: 15px;
}
.links {
  margin: 0 auto ;
}

.outside_links{
  margin-bottom: 0;
}
</style>