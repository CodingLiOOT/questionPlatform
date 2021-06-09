<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Company/ExpertsList' }">专家列表</el-breadcrumb-item>
      <el-breadcrumb-item>邀请专家</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>

    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="报告名称：">
        <el-select v-model="form" filterable placeholder="请选择">
          <el-option
            v-for="item in reports"
            :key="item"
            :label="item.reportName"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>

    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="报告编号：">
        <el-input v-model="form.reportId" placeholder="" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="报告名称：">
        <el-input v-model="form.reportName" placeholder="" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="关键词：">
        <el-input v-model="form.keyWords" placeholder="" :disabled="true"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="invite">发送邀请码</el-button>
      </el-form-item>
    </el-form>
</template>

<script>
export default {
  name: "InviteExpert",
  data() {
    return {
      form: {
        reportId: '',
        reportName: '',
        createTime: '',
        keyWords: [],
      },
      reports: [
      //   {
      //   reportId: '1',
      //   reportName: '报告1',
      //   createTime: '2020-6-6',
      //   keyWords: ["石油","中石油"],
      //   jClassName: '未分配',
      // },{
      //   reportId: '2',
      //   reportName: '报告2',
      //   createTime: '2020-6-7',
      //   keyWords: ["中石油","中石化"],
      //   jClassName: '未分配',
      // },{
      //   reportId: '3',
      //   reportName: '报告3',
      //   createTime: '2020-6-9',
      //   keyWords: ["关键词1"],
      //   jClassName: '未分配',
      // },
      ],
    }
  },
  methods: {
    invite() {
      let expertName = this.UrlSearch();
      this.$API.p_inviteExpert({
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
    },
    getAllReportList() {
      this.$API.p_getAllReportList()
        .then(
          data => {
            for (let i = 0; i < data.reports.length; i++) {
              let temp = {
                reportId: '',
                reportName: '',
                createTime: '',
                keyWords: [],
                jClassName: '',
              };
              temp.reportId = data.reports[i].reportId;
              temp.reportName = data.reports[i].reportName;
              temp.createTime = data.reports[i].createTime;
              temp.jClassName = data.reports[i].jClassName;
              if (temp.jClassName == null) {
                temp.jClassName = '未分配';
              }
              for (let j = 0; j < data.reports[i].keyWord.length; j++) {
                let k = data.reports[i].keyWord[j].word;
                temp.keyWords.push(k);
              }
              this.reports.push(temp);
            }
          }
        )
        .catch(
          error => {
          }
        )
    },
  },
  mounted() {
    this.getAllReportList();
  },
}
</script>

<style scoped>
</style>
