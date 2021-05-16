<template>
  <div>
    <el-header class="header">
      <p style="float: left;color: #ffffff">国家能源集团信息化项目信息资源共享评估平台</p>
      <div class="header-main">
        <div><router-link to="/ExpertInvitation" style="float: right">退出</router-link></div>
        <div style="float: right;padding-right: 20px;">专家xxx</div>
      </div>
    </el-header>
    <el-container class="el-container">
      <aside width="200px">
        <el-menu
          default-active="2"
          class="el-menu-vertical"
          router="true"
          style="width:200px">
          <el-menu-item index="/ExpertMainPage/ExpertRating">
            <span slot="title">待打分方案</span>
          </el-menu-item>
          <el-menu-item index="/ExpertMainPage/ExpertRated">
            <template slot="title">已打分方案</template>
          </el-menu-item>
          <el-menu-item index="/ExpertMainPage/ExpertHome">
            <template slot="title">个人中心</template>
          </el-menu-item>
        </el-menu>
      </aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "ExpertMainPage",
}
</script>

<style scoped>
.el-container {
  height: 100%;
  width: 100%;
  top: 0;
  bottom: 0;
}

.header {
  background-color: #6ba2db;
}

.header-main {
  color: beige;
  padding: 20px 20px 10px 20px;
}

.el-menu-vertical {
  background-color: #7798c6;
}

</style>
