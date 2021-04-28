<template>
  <div>
    <el-table
      :data="tableData"
      border
      style="width: 100%;height: 100%">
      <el-table-column
        prop="id"
        label="报告编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="name"
        label="报告名称"
        width="300">
      </el-table-column>
      <el-table-column
        prop="tag"
        label="标签"
        width="300"
        :filters=this.filters
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
            v-for="item in scope.row.tag"
            :key="scope.row">{{item}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="创建时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small" style="color: red">编辑</el-button>
        </template>
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
import {unique} from "webpack-merge";
export default {
  name: "List",
  data() {
    return {
      currentPage: 1,
      filters:[],
      tableData: []
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    // 选中查看某一条
    handleClick(row) {
      console.log(row);
      this.$router.push({
        path: 'ReportDetail',
        query: {
          reportId: row.id,
        }
      });
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
    getList(){
      this.$API.p_getList({
        username: this.$store.state.user.username
      })
        .then(
          data=>{
            for(let i=0;i<data.reports.length;i++){
              let temp={
                id: '',
                name: '',
                time: '',
                tag:[],
              };
              temp.id=data.reports[i].reportId;
              temp.name=data.reports[i].reportName;
              temp.time=data.reports[i].createTime;
              for(let j=0;j<data.reports[i].keyWord.length;j++){
                let k=data.reports[i].keyWord[j].word;
                temp.tag.push(k);
              }
              // if(temp.id===data.reports[i].keyWords.reportId)
              //   temp.tag=data.reports[i].keyWords.word;
              this.tableData.push(temp);
            }
          }
        )
        .catch(
          error=>{
          }
        )
    }
  },
  mounted() {
    this.getFilters();
    this.getList();
  }
}
</script>

<style scoped>
</style>
