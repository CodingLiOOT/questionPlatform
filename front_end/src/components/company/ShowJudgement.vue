<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Company/JudgeList' }">指标类列表</el-breadcrumb-item>
      <el-breadcrumb-item>查看指标类</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>
    <el-table
      :data="judgementData"
      border
      style="width: 100%;height: 100%">

      <el-table-column
        prop="id"
        label="指标编号"
        width="100">
      </el-table-column>

      <el-table-column
        prop="name"
        label="指标名称"
        width="300">
      </el-table-column>

      <el-table-column
        prop="content"
        label="指标内容"
        width="300">
      </el-table-column>

      <el-table-column
        prop="proportion"
        label="指标权重"
        width="100">
      </el-table-column>

      <el-table-column
        prop="managerId"
        label="管理者编号"
        width="200">
      </el-table-column>

    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-size="10"
      layout="prev, pager, next, jumper"
      :total="10">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "ShowJudgement",
  data() {
    return {
      currentPage: 1,
      filters:[],
      judgementData: [{
        id: 1,
        name: '架构模式A',
        content: '评价该项目的架构模式，由差到好划分为1~5分',
        proportion: 3,
        managerId: 9791
      },
        {
          id: 2,
          name: '架构模式B',
          content: '评价该项目的架构模式，由差到好划分为1~5分',
          proportion: 2,
          managerId: 9791
        },
        {
          id: 3,
          name: '架构模式C',
          content: '评价该项目的架构模式，由差到好划分为1~5分',
          proportion: 5,
          managerId: 9791
        }]
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    filterTag(value, row) {
      for(let item in row.tag){
        if(row.tag[item]===value){
          return true;
        }
      }
      return false;
    },
    getFilters(){
      for(let item in this.tableData){
        for(let tag in this.tableData[item].tag){
          let temp={text:'',value:'',}
          temp.text=this.tableData[item].tag[tag];
          temp.value=this.tableData[item].tag[tag];
          this.filters.push(temp);
        }
      }
      let array =[];
      for(let i = 0; i < this.filters.length; i++) {
        let f=true;
        for(let j=0;j<array.length;j++){
          if(this.filters[i].value===array[j].value){
            f=false;
          }
        }
        if(f){
          array.push(this.filters[i]);
        }
      }
      this.filters=array;
    },
    //从后端获取某项指标类放在界面上
    getOneJudgement(){
      let str = this.UrlSearch();
      this.$API.p_getOneJudgement({
        JClassId: str
      })
        .then(
          data => {
            for (let i=0; i<data.judgement.length(); i++){
              let temp={
                id: '',
                name: '',
                content: '',
                proportion: '',
                managerId: ''
              };
              temp.id = data.judgement[i].judgementId;
              temp.name = data.judgement[i].judgementName;
              temp.content = data.judgement[i].judgementContent;
              temp.proportion = data.judgement[i].judgementProportion;
              temp.managerId = data.judgement[i].managerId;

              this.judgementData.push(temp);
            }
          })
        .catch(
          error => {
            console.log(error);
        })
    },
    mounted() {
      this.getFilters();
    },
    UrlSearch() {
      let str = location.href; //获取到整个地址
      let num = str.indexOf("?")
      str = str.substr(num + 1); //取得num+1后所有参数，这里的num+1是下标 str.substr(start [, length ]
      str = str.substring(3,str.length); //获取id的值
      return str;
    }
  }
}
</script>

<style scoped>

</style>
