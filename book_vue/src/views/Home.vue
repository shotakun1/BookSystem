<template>
  <el-container v-loading="loading">
    <el-header>
      <HomeHeader
          :username="username"
          @logout="logout"
      ></HomeHeader>
    </el-header>
    <el-container class="main">

      <el-main>
        <HomeTable
            :books="books"
            :currentTablePage="currentTablePage"
            @deleteBook="deleteBook"
            @openUpdatePopup="openUpdatePopup"
            @searchByOption="searchByOption"
        ></HomeTable>
      </el-main>
      <el-footer>
        <HomeFooter
            :currentPage="currentPage"
            :tableLength="tableLength"
            @openAddPopup="openAddPopup"
            @handleCurrentChange="handleCurrentChange"
        ></HomeFooter>
      </el-footer>

    </el-container>
  </el-container>

  <home-dialog
      :popup="popup"
      :editedBook="editedBook"
      :formRules="formRules"
      :addOption="addOption"
      :updateOption="updateOption"
      :title="title"
      @close-popup="closePopup"
      @add-book="addBook"
      @update-book="updateBook"
  ></home-dialog>
</template>

<script src="@/js/home.js"></script>


<style>

.el-header {
  background: linear-gradient(135deg, #56ab2f, #a8e063);
}


</style>