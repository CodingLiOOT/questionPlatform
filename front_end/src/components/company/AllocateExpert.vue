<template>
  <el-form ref="form" :model="form" label-width="100px">

    <el-form-item label="专家列表>新建专家" label-width="150px"></el-form-item>

    <el-form-item label="姓名：">
      <el-input v-model="form.expertName" placeholder=""></el-input>
    </el-form-item>

    <el-form-item label="关键词：">
      <el-input v-model="form.keysId" placeholder=""></el-input>
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
      <el-input v-model="form.expertEmail" placeholder=""></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">新建</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
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
        expertInformation:'',
        expertEmail:'',
      },

      //点击“新建”按钮发送专家信息
      onSubmit() {
        //console.log(this.form.name);
        this.$refs.form.validate((valid) => {
          if(valid){
            this.$API.p_allocateExpert({
              expertName: this.form.expertName,
              keysId: this.form.keysId,
              expertType: this.form.expertType,
              expertUnit: this.form.expertUnit,
              expertInformation: this.form.expertInformation,
            })
              .then(
                data=>{
                  for(let i=0;i<data.report.length;i++){
                    let temp={
                      expertName: '',
                      keysId: '',
                      expertType: '',
                      expertUnit:'',
                      expertInformation: '',
                      expertEmail:'',
                    };
                    temp.expertName=data.experts[i].expertName;
                    temp.keysId=data.experts[i].keysId;
                    temp.expertType=data.experts[i].expertType;
                    temp.expertUnit=data.experts[i].expertUnit;
                    temp.expertInformation=data.experts[i].expertInformation;
                    temp.expertEmail=data.experts[i].expertEmail;
                  }
                }
              )
              .catch(
                error => {
                  console.log(error);
                }
              )
          }
        })

      }
    }
  }
}
</script>

<style scoped>
</style>
