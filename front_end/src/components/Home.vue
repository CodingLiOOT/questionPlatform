<template>
  <el-container>
    <el-header class="header" height="100px">
      <h3>我的网站</h3>
      <p>Vue小模块之用户登录功能</p>
    </el-header>
    <el-main>
      <router-link to="/login">Go to login</router-link>
    </el-main>
    <el-button type="primary" @click="test()">test</el-button>
  </el-container>
</template>

<script>
export default {
  name: 'App',
  methods:{
    test(){
        this.$API.g_getAllUser()
        .then(
          data=>{
            alert(data[0].userName);
          }
        )
      .catch(
        error=>{

        }
      )
    }
  }
}
</script>

<style>
.header{
  background-color: #409EFF;
  color: white;
}
</style>
