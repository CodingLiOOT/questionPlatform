<template>
  <div>
    <el-header class="header">
      <p style="float: left;color: #ffffff">国家能源集团信息化项目信息资源共享评估平台</p>
      <div class="header-main">
        <div><router-link to="/home" style="float: right">退出</router-link></div>
        <div style="float: right;padding-right: 20px;">可研报告编制单位</div>
      </div>
    </el-header>
    <el-container class="el-container">
      <aside width="200px">
        <el-menu
          default-active="2"
          class="el-menu-vertical"
          router="true"
          style="width:200px">
          <el-menu-item index="/MainPage/Upload">
            <span slot="title">上传报告</span>
          </el-menu-item>
          <el-menu-item index="/MainPage/List">
            <template slot="title">报告列表</template>
          </el-menu-item>
        </el-menu>
      </aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
<!--          <el-footer>-->
<!--            <p>Copyright @ 2021 team7.com Inc. All Rights Reserved.</p>-->
<!--            <p>联系邮箱：18301078@bjtu.edu.cn</p>-->
<!--          </el-footer>-->

  </div>
</template>

<script>
export default {
name: "MainPage",
}
</script>

<style scoped>
.el-container{
  height:100%;
  width:100%;
  top:0;
  bottom:0;
}
.header{
  background-color: #6ba2db;
}
.header-main {
  color: beige;
  padding: 20px 20px 10px 20px;
}
.el-menu-vertical{
  background-color: #7798c6;
}
</style>
