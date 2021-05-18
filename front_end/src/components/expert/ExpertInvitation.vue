<template>
  <div class="invitation-wrapper">
    <div class="invitation-content">
      <div class="invitation-main">
        <router-link to="/home" style="float: right">返回</router-link>
        <h2 class="invitation-main-title" align="left">专家打分</h2>
        <el-form :model="InvitationForm" :rules="InvitationRule" ref="InvitationForm" @keyup.enter.native="login()"
                 status-icon>
          <el-form-item prop="expertName">
            <el-input v-model="InvitationForm.expertName" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item prop="email">
            <el-input v-model="InvitationForm.email" type="email" placeholder="邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="code" :inline="true" >
            <el-input v-model="InvitationForm.code" placeholder="邀请码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="invitation-btn-submit" type="primary" @click="submit()">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ExpertInvitation",
  data() {
    /**
     * 验证专家姓名
     * @param rule [规则名称]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validateExpertName=(rule,value,callback)=>{
      if (value === "") {
        callback(new Error("请输入姓名"));
      } else {
        const regPass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if (!regPass.test(value)) {
          callback(new Error("至少八位字符，包含大小写字母和数字，不含特殊字符"));
        }
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

    /**
     * 验证邀请码
     * @param rule [规则名称]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validateCode = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入邀请码"));
      } else {
        const regPass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if (!regPass.test(value)) {
          callback(new Error("至少八位字符，包含大小写字母和数字，不含特殊字符"));
        }
        callback();
      }
    };

    return {
      InvitationForm: {
        expertName:'',
        email:'',
        code: ''
      },
      InvitationRule: {
        expertName: [{
          validator: validateExpertName,
          trigger: 'change'
        },
          {
            required: true,
            message: '请输入姓名',
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
        code: [{
          validator: validateCode,
          trigger: 'change'
        },
          {
            required: true,
            message: '请输入邀请码',
            trigger: 'blur'
          }],
      },
      activeName: 'first',
      disabled:false,
      time:30,
      btnTxt:"发送邀请码",
      type:'0'
    }
  },
  methods: {
    submit() {
      this.$router.replace('/ExpertMainPage');
      // this.$refs.InvitationForm.validate((valid) => {
      //   if (valid) {
      //     this.$API.p_Invitation({
      //       code:this.InvitationForm.code
      //     })
      //       .then(
      //         res=>{
      //           this.$router.replace('/expertRating');
      //         }
      //       )
      //   } else {
      //     return false;
      //   }
      // })
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
    }
  }
}
</script>

<style scoped>
.invitation-wrapper {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: hidden;
  background: rgba(38, 50, 56, .6) url(../../assets/login_bg.jpg) no-repeat;
  background-size: 100% 100%;
}

.invitation-content {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  height: 350px;
  width: 400px;
  background-color: #112234;
  opacity: .8;
}

.invitation-main {
  color: beige;
  padding: 20px 20px 10px 20px;
}

.invitation-btn-submit {
  margin-top: 10px;
}

a {
  text-decoration: none;
  color: #aaa;
  font-size: 15px;
}

a:hover {
  color: coral;
}

</style>
