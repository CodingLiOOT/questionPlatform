<template>
  <div class="login-wrapper">
    <div class="login-content">
      <div class="login-main">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="账号登录" name="first">
            <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="login()" status-icon>
              <el-form-item prop="userName">
                <el-input v-model="dataForm.userName" placeholder="帐号"></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
              </el-form-item>
              <router-link to="/forget">忘记密码</router-link>
              <router-link to="/register">注册账号</router-link>
              <router-link to="/expertInvitation">专家入口</router-link>
              <el-form-item>
                <el-button class="login-btn-submit" type="primary" @click="login()">登录</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>

          <el-tab-pane label="邮箱登录" name="second">
            <el-form :model="emailDataForm" :rules="dataRule" ref="emailDataForm" @keyup.enter.native="login()" status-icon>

              <el-form-item prop="email">
                <el-input v-model="emailDataForm.email" type="email" placeholder="邮箱"></el-input>
              </el-form-item>

              <el-form-item prop="emailCode" :inline="true" >
                <el-input v-model="emailDataForm.emailCode" placeholder="验证码" style="width:230px"></el-input>
                <el-button :disabled="disabled" @click="sendCode" class="sendcode" style="width:125px">{{btnTxt}}</el-button>
              </el-form-item>

              <el-form-item>
                <el-button class="login-btn-submit" type="primary" @click="login()">登录</el-button>
              </el-form-item>

            </el-form>
          </el-tab-pane>
        </el-tabs>


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
        password: '',
      },
      emailDataForm: {
        email: '',
        emailCode: ''
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
        }],
        email: [{
          required: true,
          message: '邮箱不能为空',
          trigger: 'blur'
        }],
        emailCode: [{
          required: true,
          message: '验证码不能为空',
          trigger: 'blur'
        }],
      },
      activeName: 'first',
      disabled:false,
      time:30,
      btnTxt:"发送验证码",
      type:'0'
    }
  },
  name: 'login',
  methods: {
    login() {
      if(this.type==='0') {
        //console.log(this.dataForm.userName);
        //console.log(this.dataForm.password);
        this.$refs["dataForm"].validate((valid) => {
          if (valid) {
            this.$API.p_Login({
              username: this.dataForm.userName,
              password: this.dataForm.password,
              loginType: 0
            })
              .then(
                data => {
                  this.$store.commit('login', data);
                  //this.$router.replace('/index')
                  let redirect
                  if(data.type==1){
                    redirect = decodeURIComponent(this.$route.query.redirect || '/MainPage');
                  }
                  else if(data.type==2){
                    redirect = decodeURIComponent(this.$route.query.redirect || '/Company');
                  }
                  this.$router.push({path: redirect});
                }
              )
              .catch(err => {
              })
          } else {
            return false
          }
        })
      }
      else
      {
        //console.log(this.dataForm.email);
        //console.log(this.dataForm.emailCode);
        this.$refs["emailDataForm"].validate((valid) => {
          if (valid) {
            this.$API.p_Login({
              mail: this.emailDataForm.email,
              verifyCode: this.emailDataForm.emailCode,
              loginType: 1
            })
              .then(
                data => {
                  this.$store.commit('login', data);
                  //this.$router.replace('/index')
                  let redirect = decodeURIComponent(this.$route.query.redirect || '/index');
                  this.$router.push({path: redirect});
                }
              )
              .catch(err => {
              })
          } else {
            return false
          }
        })
      }
    },
    //切换不同登录方式
    handleClick(tab, event) {
      this.type = tab.index;
      //console.log("now is " + tab.index);
      console.log(tab,event);
    },
    //发送邮箱验证码，30秒后重新发送
    sendCode(){
      this.time=30
      this.timer();
      this.$API.p_SendCode({
        mail: this.emailDataForm.email
      })
        .then(
        )
    },
    //发送手机验证码倒计时
    timer() {
      if (this.time > 0) {
        this.disabled=true;
        this.time--;
        this.btnTxt=this.time+"s后重新发送";
        setTimeout(this.timer, 1000);
      } else{
        this.time=0;
        this.btnTxt="发送验证码";
        this.disabled=false;
      }
    },
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
