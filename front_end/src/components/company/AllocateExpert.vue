<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Company/ExpertsList' }">专家列表</el-breadcrumb-item>
      <el-breadcrumb-item>新建专家</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>
    <el-form ref="form" :model="form" label-width="100px">


      <el-form-item label="姓名：">
        <el-input v-model="form.expertName" placeholder=""></el-input>
      </el-form-item>

      <el-form-item label="关键词：">
        <el-input v-model="form.keysId" placeholder="">
          <el-select v-model="form.keysId" slot="prepend" placeholder="请选择">
            <el-option
              v-for="item in keys"
              :key="item.keysId"
              :label="item.keysContent"
              :value="item.keysContent">
            </el-option>
        </el-select>
        </el-input>
      </el-form-item>

      <el-form-item label="类型：">
        <el-input v-model="form.expertType" placeholder=""></el-input>
      </el-form-item>

      <el-form-item label="所属单位：">
        <el-input v-model="form.expertUnit" placeholder=""></el-input>
      </el-form-item>

      <el-form-item label="专家简介：">
        <el-input v-model="form.expertInformation" placeholder=""></el-input>
      </el-form-item>

      <el-form-item label="邮箱：">
        <el-input v-model="form.mail" placeholder=""></el-input>
      </el-form-item>

      <el-form-item label="手机号：">
        <el-input v-model="form.phone" placeholder=""></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">新建</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "AllocateExpert",
  data() {
    return {
      form: {
        expertName: '',
        keysId: '',
        expertType: '',
        expertUnit: '',
        expertInformation: '',
        mail: '',
        phone: '',
      },
      keys:[],
    }
  },
  methods:{
    getKeyWords(){
      this.$API.p_getKeyWords({})
        .then(
          data=>{
            for(let i=0;i<data.keys.length;i++){
              let temp={
                keysId:'',
                keysContent:'',
              }
              temp.keysId=data.keys[i].keysId;
              temp.keysContent=data.keys[i].keysContent;
              this.keys.push(temp);
            }
          }
        )
        .catch(
          error => {
            console.log(error);
          }
        )
    },
    onSubmit() {
      this.$API.p_createExpert({
        expertName: this.form.expertName,
        keysId: this.form.keysId,
        expertType: this.form.expertType,
        expertUnit: this.form.expertUnit,
        expertInformation: this.form.expertInformation,
        mail: this.form.mail,
        phone: this.form.phone
      })
        .then(
          data=>{
            // for(let i=0;i<data.experts.length;i++){
            //   let temp={
            //     expertName: '',
            //     keysId: '',
            //     expertType: '',
            //     expertUnit:'',
            //     expertInformation: '',
            //     mail:'',
            //     phone:'',
            //   };
            //   temp.expertName=data.experts[i].expertName;
            //   temp.keysId=data.experts[i].keysId;
            //   temp.expertType=data.experts[i].expertType;
            //   temp.expertUnit=data.experts[i].expertUnit;
            //   temp.expertInformation=data.experts[i].expertInformation;
            //   temp.mail=data.experts[i].mail;
            //   temp.phone=data.experts[i].phone;
            // }
          }
        )
        .catch(
          error => {
            console.log(error);
          }
        )

    }
  },
  mounted() {
    this.getKeyWords()
  }
}
</script>

<style scoped>
</style>
