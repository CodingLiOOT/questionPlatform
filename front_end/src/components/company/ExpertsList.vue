<template>
  <div>
    <el-input
      v-model="input"
      placeholder="输入关键词进行搜索"
      prefix-icon="el-icon-search"
      style="width: 350px;margin-right: 10px"
      clearable
      @clear="search"
      @keydown.enter.native="search">
    </el-input>
    <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
    <router-link to="/Company/AllocateExpert">
      <el-button icon="el-icon-plus" @click="invite">邀请专家</el-button>
    </router-link>

    <el-table
      :data="expertsListData"
      border
      style="width: 100%;height: 100%">
      <el-table-column
        prop="name"
        label="专家姓名"
        width="100">
      </el-table-column>
      <el-table-column
        prop="keysId"
        label="专家关键词"
        width="300">
      </el-table-column>
      <el-table-column
        prop="type"
        label="专家类型"
        width="320">
      </el-table-column>
      <el-table-column
        prop="unit"
        label="所属单位"
        width="100">
      </el-table-column>
      <el-table-column
        prop="information"
        label="专家简介"
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
  name: "JudgeList",
  data() {
    return {
      currentPage: 1,
      filters:[],
      input: '',
      expertsListData: [{
        name: '张三',
        keysId: '石油',
        type: '......',
        unit: 'xxx公司',
        information: '......'
      },
        {
          name: '李四',
          keysId: '教育',
          type: '......',
          unit: 'xxx公司',
          information: '......'
        },
        {
          name: '王五',
          keysId: '生物',
          type: '......',
          unit: 'xxx公司',
          information: '......'
        }],
      form: {
        name: '',
        keysId: '',
        type: '',
        unit: '',
        information: ''
      },
      dataRules: {
        name: [{
          required: true,
          message: '专家姓名不能为空',
          trigger: 'blur'
        }],
        keysId: [{
          required: true,
          message: '专家关键词不能为空',
          trigger: 'blur'
        }],
        type: [{
          required: true,
          message: '专家类型不能为空',
          trigger: 'blur'
        }],
        unit: [{
          required: true,
          message: '所属单位不能为空',
          trigger: 'blur'
        }]
        ,
        information: [{
          required: true,
          message: '专家简介不能为空',
          trigger: 'blur'
        }]
      }
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
    //从后端获取专家列表放在界面上
    getList() {
      this.$API.g_getExpertList({})
        .then(
          data => {
            for(let i=0;i<data.experts.length();i++){
              let temp={
                name: '',
                keysId: '',
                type: '',
                unit: '',
                information: ''
              };
              temp.name = data.expert[i].expertName;
              temp.keysId = data.expert[i].keysId;
              temp.type = data.expert[i].expertType;
              temp.unit = data.expert[i].expertUnit;
              temp.information = data.expert[i].expertInformation;

              this.expertsListData.push(temp);
            }
          }
        )
        .catch(
          error => {
            console.log(error);
          }
        )
    },
    mounted(){
      this.getFilters();
      this.getList();
    },
  }
}
</script>

<style scoped>

</style>
