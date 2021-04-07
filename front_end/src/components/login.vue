<template>
  <div class="login-wrapper">
    <div class="login-content">
      <div class="login-main">
        <h2 class="login-main-title">管理员登录</h2>
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="login()" status-icon>
          <el-form-item prop="userName">
            <el-input v-model="dataForm.userName" placeholder="帐号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
          </el-form-item>
          <router-link to="/forget">忘记密码</router-link>
          <router-link to="/register">注册账号</router-link>
          <el-form-item>
            <el-button class="login-btn-submit" type="primary" @click="login()">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  data() {
    return {
      dataForm: {
        userName: '',
        password: ''
      },
      dataRule: {
        userName: [{
          required: true,
          message: '帐号不能为空',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '密码不能为空',
          trigger: 'blur'
        }]
      }
    }
  },
  name: 'login',
  methods: {
    login() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {

          this.$API.p_Login({
            userName: this.dataForm.userName,
            password: this.dataForm.password,
          })
            .then(
              res => {
                this.$store.commit('login', res);
                //this.$router.replace('/index')
                let redirect=decodeURIComponent(this.$route.query.redirect||'/index');
                this.$router.push({path:redirect});
              }
            )
            .catch(err => {

            })
        } else {
          return false
        }
      })
    }
  }
}
</script>
<style>
.login-wrapper {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: hidden;
  background: rgba(38, 50, 56, .6) url(../assets/login_bg.jpg) no-repeat;
  background-size: 100% 100%;
}

.login-content {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  height: 300px;
  width: 400px;
  background-color: #112234;
  opacity: .8;
}

.login-main {
  color: beige;
  padding: 20px 20px 10px 20px;
}
h3 {
  color: #0babeab8;
  font-size: 24px;
}
hr {
  background-color: #444;
  margin: 20px auto;
}
a {
  text-decoration: none;
  color: #aaa;
  font-size: 15px;
}
a:hover {
  color: coral;
}

.login-btn-submit{
  margin-top: 10px;
}

</style>
