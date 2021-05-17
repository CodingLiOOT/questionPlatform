<template>
  <el-form ref="form" :model="form" label-width="100px">

    <el-form-item label="专家列表>分配专家" label-width="150px"></el-form-item>

    <el-form-item label="报告编号：">
      <el-input v-model="form.reportId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="关键词：">
      <el-input v-model="form.keywords" placeholder=""></el-input>
    </el-form-item>

    <el-form-item label="报告名称：">
      <el-input v-model="form.reportName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="创建日期：">
      <el-input v-model="form.createTime" placeholder="xxxx-xx-xx">
        <i slot="suffix" class="el-input__icon el-icon-date"></i>
      </el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">分配专家</el-button>
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
        reportId: '',
        keywords: '',
        reportName: '',
        createTime: ''
      },

      //点击“创建”按钮将报告数据发送给后端
      onSubmit() {
        //console.log(this.form.name);
        this.$refs.form.validate((valid) => {
          if(valid){
            this.$API.p_allocateExpert({
              reportId: this.form.reportId,
              keyWords: this.form.keywords,
              reportName: this.form.reportName,
              createTime: this.form.createTime
            })
              .then(
                data=>{
                  for(let i=0;i<data.experts.length;i++){
                    let temp={
                      expertName: '',
                      keysId: '',
                      expertType: '',
                      expertUnit: '',
                      expertInformation: '',
                    };
                    temp.expertName=data.experts[i].expertName;
                    temp.keysId=data.experts[i].reportName;
                    temp.expertType=data.experts[i].expertType;
                    temp.expertUnit=data.experts[i].expertUnit;
                    temp.expertInformation=data.experts[i].expertInformation;

                    this.tableData.push(temp);
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
