<template>
  <div class="register-wrapper">
    <div class="register-content">
      <div class="register-main">
        <router-link to="/login" style="float: right">返回登录</router-link>
        <h2 class="register-main-title" align="left">注册</h2>
        <el-form :model="RegisterForm" :rules="RegisterRule" ref="RegisterForm" @keyup.enter.native="login()"
                 status-icon>
          <el-form-item prop="userName">
            <el-input v-model="RegisterForm.userName" placeholder="帐号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="RegisterForm.password" type="password" placeholder="密码"></el-input>
          </el-form-item>
          <el-form-item prop="confirmPassword">
            <el-input v-model="RegisterForm.confirmPassword" type="password" placeholder="确认密码"></el-input>
          </el-form-item>
          <el-form-item prop="email">
            <el-input v-model="RegisterForm.email" placeholder="邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="emailCode" :inline="true" >
            <el-input v-model="RegisterForm.emailCode" placeholder="验证码" style="width:230px"></el-input>
            <el-button :disabled="disabled" @click="sendCode" class="sendcode" style="width:125px">{{btnTxt}}</el-button>
          </el-form-item>
          <el-form-item>
            <el-button class="register-btn-submit" type="primary" @click="register()">注册</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {

    /**
     * 验证用户名
     * @param rule [规则名称]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validateUsername=(rule,value,callback)=>{
      if (value === "") {
        callback(new Error("请输入账号"));
      } else {
        const regPass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if (!regPass.test(value)) {
          callback(new Error("至少八位字符，包含大小写字母和数字，不含特殊字符"));
        }
        callback();
      }
    };

    /**
     * 验证密码是否为空
     * @param rule [规则名字]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        const regPass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if (this.$refs.RegisterForm.confirmPassword !== "") {
          this.$refs.RegisterForm.validateField("confirmPass");
        }
        if (!regPass.test(value)) {
          callback(new Error("至少八位字符，包含大小写字母和数字，不含特殊字符"));
        }
        callback();
      }
    };

    /**
     * 二次验证密码
     * @param rule [规则名称]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validConfirmPass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.RegisterForm.password) {
        callback(new Error("两次输入密码不一致！"));
      } else {
        callback();
      }
    };

    /**
     * 验证邮箱
     * @param rule [规则名称]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validEmail = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入邮箱"));
      } else {
        const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
        if (regEmail.test(value)) {
          callback();
        }
        callback(new Error("请输入合法邮箱"))
      }
    };

    return {
      RegisterForm: {
        userName: '',
        password: '',
        confirmPassword: '',
        email: '',
        emailCode: ''
      },
      RegisterRule: {
        userName: [{
          validator: validateUsername,
          trigger: 'change'
        },
          {
            required: true,
            message: '请输入账号',
            trigger: 'blur'
          }],
        password: [{
          validator: validatePass,
          trigger: 'change'
        },
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }],
        confirmPassword: [{
          validator: validConfirmPass,
          trigger: 'change'
        },
          {
            required: true,
            message: '请再次输入密码',
            trigger: 'blur'
          }],
        email: [{
          validator: validEmail,
          trigger: 'change'
        },
          {
            required: true,
            message: '请输入邮箱',
            trigger: 'blur'
          }],
        emailCode: [{
<<<<<<<<< Temporary merge branch 1
            required: true,
            message: '请输入验证码',
            trigger: 'blur'
          }]
=========
          required: true,
          message: '请输入验证码',
          trigger: 'blur'
        }]
>>>>>>>>> Temporary merge branch 2
      },
      disabled:false,
      time:30,
      btnTxt:"发送验证码",
    }
  },
  name: 'Register',

  methods: {
    register() {
      this.$refs.RegisterForm.validate((valid) => {
        if (valid) {
          this.$API.p_Register({
            username:this.RegisterForm.userName,
            password:this.RegisterForm.password,
            mail:this.RegisterForm.email,
            verifyCode:this.RegisterForm.emailCode
          })
          .then(
            res=>{
              this.$router.replace('/login');
            }
          )
          .catch({

          })
        } else {
          return false
        }
      })
    },

    //发送邮箱验证码，30秒后重新发送
    sendCode(){
      this.time=30
      this.timer();
      this.$API.p_SendCode({
        mail: this.RegisterForm.email
      })
        .then(

        )
    },
    //发送邮箱验证码倒计时
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

<style scoped>
.register-wrapper {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: hidden;
  background: rgba(38, 50, 56, .6) url(../assets/login_bg.jpg) no-repeat;
  background-size: 100% 100%;
}

.register-content {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  height: 460px;
  width: 400px;
  background-color: #112234;
  opacity: .8;
}

.register-main {
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

.register-btn-submit {
  margin-top: 0;
}

</style>
