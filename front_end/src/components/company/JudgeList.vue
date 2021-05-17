<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="指标列表" name="show">
        <el-table
          :data="judgeListData"
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
            width="320">
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
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="10"
            layout="prev, pager, next, jumper"
            :total="10">
          </el-pagination>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="添加指标" name="add">
        <el-form ref="form" :model="form" label-width="100px">
          <el-form-item label="指标名称">
            <el-input v-model="form.name" placeholder="请填写指标名称"></el-input>
          </el-form-item>
          <el-form-item label="指标内容">
            <el-input type="textarea" v-model="form.content" placeholder="请填写指标内容"></el-input>
          </el-form-item>
          <el-form-item label="指标权重">
            <el-input v-model="form.proportion" placeholder="请填写1~5的数字"></el-input>
          </el-form-item>
          <el-form-item label="管理者编号">
            <el-input v-model="form.managerId" placeholder="请填写管理者编号"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">创建指标</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "JudgeList",
  data() {
    return {
      currentPage: 1,
      filters:[],
      judgeListData: [{
        id: 1,
        name: '架构模式',
        content: '评价该项目的架构模式，由差到好划分为1~5分',
        proportion: 3,
        managerId: 9791
      },
        {
          id: 2,
          name: '设计模式',
          content: '评价该项目的架构模式，由差到好划分为1~5分',
          proportion: 2,
          managerId: 2871
        },
        {
          id: 3,
          name: '算法的选择',
          content: '评价该项目的架构模式，由差到好划分为1~5分',
          proportion: 5,
          managerId: 7890
        }],
      form: {
        name: '',
        content: '',
        proportion: '',
        managerId: ''
      },
      activeName: 'show'
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
    mounted(){
      this.getFilters();
    },
    handleClick(tab, event) {
      console.log(tab,event);
    },
    onSubmit() {
      console.log("send judgement");
    }
  }
}
</script>

<style scoped>

</style>
