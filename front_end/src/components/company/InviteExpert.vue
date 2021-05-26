<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Company/ExpertsList' }">专家列表</el-breadcrumb-item>
      <el-breadcrumb-item>邀请专家</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>

    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="报告编号：">
        <el-input v-model="form.reportId" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="报告名称：">
        <el-input v-model="form.reportName" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="关键词：">
        <el-input v-model="form.keyWords" placeholder="">
          <el-select v-model="form.keyWords" slot="prepend" placeholder="请选择">
            <el-option label="石油" value="石油"></el-option>
            <el-option label="井" value="井"></el-option>
            <el-option label="国家能源" value="国家能源"></el-option>
            <el-option label="电" value="电"></el-option>
            <el-option label="安全" value="安全"></el-option>
            <el-option label="煤矿" value="煤矿"></el-option>
          </el-select>
        </el-input>

      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="invite">发送邀请码</el-button>
      </el-form-item>
    </el-form>


  </div>

</template>

<script>
export default {
  name: "InviteExpert",
  data(){
    return{
      form:{
        reportId:'',
        reportName:'',
        keyWords:'',
      }

    }
  },
  methods:{
    invite() {
      let expertName = this.UrlSearch();
      this.$API.p_allocateJudgement({
        expertName: expertName,
        reportId: this.form.reportId,
        reportName: this.form.reportName,
        keyWords: this.form.keyWords,
      })
        .then(
        )
        .catch(
          error => {
          }
        )
    },
    UrlSearch() {
      var name = this.$route.query.name;
      return name;
    }
  },
}
</script>

<style scoped>

</style>
