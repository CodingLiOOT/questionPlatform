<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleChangeTab">
      <el-tab-pane label="指标列表" name="show">
        <el-table
          :data="judgeListData"
          border
          style="width: 100%;height: 100%">
          <el-table-column
            prop="id"
            label="指标类编号"
            width="100">
          </el-table-column>
          <el-table-column
            prop="name"
            label="指标类名称"
            width="300">
          </el-table-column>
          <el-table-column
            prop="time"
            label="创建时间"
            width="300">
          </el-table-column>
          <el-table-column
            prop="managerId"
            label="管理者编号"
            width="200">
          </el-table-column>
        </el-table>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">查看指标</el-button>
            </template>
          </el-table-column>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-size="10"
          layout="prev, pager, next, jumper"
          :total="10">
        </el-pagination>
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
        time: '2021.05.04',
        managerId: 9791
      },
        {
          id: 2,
          name: '设计模式',
          time: '2021.05.04',
          managerId: 2871
        },
        {
          id: 3,
          name: '算法的选择',
          time: '2021.05.04',
          managerId: 7890
        }],
      form: {
        name: '',
        content: '',
        proportion: '',
        managerId: ''
      },
      dataRules: {
        name: [{
          required: true,
          message: '指标名称不能为空',
          trigger: 'blur'
        }],
        content: [{
          required: true,
          message: '指标内容不能为空',
          trigger: 'blur'
        }],
        proportion: [{
          required: true,
          message: '权重不能为空',
          trigger: 'blur'
        }],
        managerId: [{
          required: true,
          message: '管理者编号不能为空',
          trigger: 'blur'
        }]
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
    //从后端获取指标列表放在界面上
    getList() {
      this.$API.g_getJClassList({})
      .then(
        data => {
          for(let i=0;i<data.JClass.length();i++){
            let temp={
              id: '',
              name: '',
              time: '',
              managerId: ''
            };
            temp.id = data.JClass[i].JClassId;
            temp.name = data.JClass[i].JClassName;
            temp.time = data.JClass[i].JClassTime;
            temp.managerId = data.JClass[i].managerId;

            this.judgeListData.push(temp);
          }
        }
      )
      .catch(
        error => {
          console.log(error);
        }
      )
    },
    // 选中查看某一条
    handleClick(row) {
      console.log(row);
      this.$router.push({
        path: 'ShowJudgement',
        query: {
          id: row.id,
        }
      });
    },
    mounted(){
      this.getFilters();
      this.getList();
    },
    handleChangeTab(tab, event) {
      console.log(tab,event);
    },
    //点击“创建”按钮将指标数据发送给后端
    onSubmit() {
      //console.log(this.form.name);
      this.$refs.form.validate((valid) => {
        if(valid){
          this.$API.p_newJudgement({
            judgementName: this.form.name,
            judgementContent: this.form.content,
            judgementProportion: this.form.proportion,
            managerId: this.form.managerId
          })
          .then(
            data => {
              let status = data.status;
              if(status==='1'){
                alert("成功创建指标");
              }
              else {
                alert("创建指标失败，请重试");
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

      this.form.name = '';
      this.form.content = '';
      this.form.proportion = '';
      this.form.managerId = '';
    }
  }
}
</script>

<style scoped>

</style>
