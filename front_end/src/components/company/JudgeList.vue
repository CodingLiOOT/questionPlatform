<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleChangeTab">
      <el-tab-pane label="指标类列表" name="show">
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
            width="150">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="primary" size="small">查看指标类</el-button>
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
      </el-tab-pane>

      <el-tab-pane label="添加指标类" name="add">
        <el-form ref="form" :model="JClassForm" label-width="100px">
          <el-form-item label="指标类名称">
            <el-input v-model="JClassForm.JClassName" placeholder="请填写指标类名称"></el-input>
          </el-form-item>
          <el-form-item label="管理者编号">
            <el-input v-model="JClassForm.managerId" placeholder="请填写管理者编号"></el-input>
          </el-form-item>
          <el-table
            :data="judgementData"
            border
            style="width: 100%">
            <el-table-column
              prop="id"
              label="指标编号"
              width="120">
            </el-table-column>
            <el-table-column
              label="指标名称"
              width="200">
              <template slot-scope="scope">
                <el-input v-if="scope.row.id===editId" v-model="scope.row.name"></el-input>
                <span v-else>{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="指标内容"
              prop="content"
              width="350">
              <template slot-scope="scope">
                <el-input v-if="scope.row.id===editId" v-model="scope.row.content"></el-input>
                <span v-else>{{ scope.row.content }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="指标权重"
              prop="proportion">
              <template slot-scope="scope">
                <el-input v-if="scope.row.id===editId" v-model="scope.row.proportion"></el-input>
                <span v-else>{{ scope.row.proportion }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              width="200">
              <template slot-scope="scope">
                <el-button type="primary" v-if="scope.row.id!==editId" @click="changeClick(scope.row)" size="small">编辑
                </el-button>
                <el-button type="danger" v-if="scope.row.id!==editId" @click="delClick(scope.$index, scope.row)" size="small">删除
                </el-button>
                <el-button type="success" v-if="scope.row.id===editId" @click="saveClick(scope.row)" size="small">保存
                </el-button>
                <el-button type="info" v-if="scope.row.id===editId" @click="cancelClick(scope.row)" size="small">取消
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-button type="success" @click="addClick" style="float:left;">新增</el-button>
          <br><br><br><br>
          <el-form-item>
            <el-button type="primary" @click="onSubmit" style="float:right;">创建指标类</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
let maxID = 0;
let judgeList = new Array(0);
let listSize = 0;
export default {
  name: "JudgeList",
  maxID,
  judgeList,
  listSize,
  data() {
    return {
      currentPage: 1,
      filters: [],
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
      // 指标类格式
      JClassForm: {
        JClassId: '',
        JClassName: '',
        // 指标格式
        judgement: [{
          name: '',
          content: '',
          proportion: '',
          managerId: '',
        }],
        managerId: '',
      },
      activeName: 'show',
      editData: [],  //编辑行初始数据
      editId: '',  //判断编辑的是哪一行
      judgementData: []
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
      for (let item in row.tag) {
        if (row.tag[item] === value) {
          return true;
        }
      }
      return false;
    },
    getFilters() {
      for (let item in this.judgementData) {
        for (let tag in this.judgementData[item].tag) {
          let temp = {text: '', value: '',}
          temp.text = this.judgementData[item].tag[tag];
          temp.value = this.judgementData[item].tag[tag];
          this.filters.push(temp);
        }
      }
      let array = [];
      for (let i = 0; i < this.filters.length; i++) {
        let f = true;
        for (let j = 0; j < array.length; j++) {
          if (this.filters[i].value === array[j].value) {
            f = false;
          }
        }
        if (f) {
          array.push(this.filters[i]);
        }
      }
      this.filters = array;
    },
    //从后端获取指标类列表放在界面上
    getList() {
      this.$API.g_getJClassList({})
        .then(
          data => {
            for (let i = 0; i < data.JClass.length; i++) {
              let temp = {
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
    // 选中查看某一指标类
    handleClick(row) {
      console.log(row);
      this.$router.push({
        path: 'ShowJudgement',
        query: {
          id: row.id,
        }
      });
    },
    mounted() {
      this.getFilters();
      this.getList();
    },
    handleChangeTab(tab, event) {
      console.log(tab, event);
    },
     checkMaxId(id) {
       if(id > maxID) {
         maxID = id;
       }
     },
    // 编辑指标接口
    changeClick(row) {
      if (this.judgementData.some((item) => {
        return item.id === '';
      })) {
        this.$message({
          message: '请先保存',
          type: 'warning',
        });
        return;
      }
      this.editData = JSON.parse(JSON.stringify(row));    //把当前行数据存一份，取消的时候行数据还原
      this.editId = row.id;
      this.checkMaxId(parseInt(this.editId));
      //alert(this.editId);
    },
    // 删除指标接口，成功以后 this.editId = ''
    delClick(index,row) {
      --maxID;
      this.judgementData.splice(index,1);
      this.editId = '';
    },
    // 取消操作接口
    cancelClick(row) {
      if (row.id) {
        for (let i in row) {
          row[i] = this.editData[i];
        }
        this.editId = '';
      } else {
        this.judgementData.forEach((item, index) => {
          if (item.id === '') {
            this.judgementData.splice(index, 1);
          }
        })
      }
    },
    // 保存指标接口，成功以后 this.editId = ''
    saveClick(row) {
      let element = {
        id: row.id,
        judgementname: row.name,
        judgementcontent: row.content,
        judgementproportion: row.proportion
      };
      listSize = judgeList.push(element);
      this.editId = '';
    },
    // 添加指标接口
    addClick() {
      if (this.editId !== '') {
        this.$message({
          message: '请先保存',
          type: 'warning',
        });
        return;
      }
      //获取当前指标编号
      maxID++;
      let currentId = maxID;
      if (this.judgementData.some((item) => {
        return item.id === '';
      })) {
        this.$message({
          message: '请先保存',
          type: 'warning',
        });
        return;
      }
      this.judgementData.push(
        {
          id: currentId,
          name: '',
          content: '',
          proportion: '',
        }
      )
    },
    //点击“创建指标类”按钮将指标类数据发送给后端
    onSubmit() {
      /*
      console.log(this.JClassForm.JClassName);
      console.log(this.JClassForm.managerId);
      console.log(listSize);
      for(let i=0; i<listSize; i++){
        console.log(judgeList[i].name);
      }*/

      this.$refs.form.validate((valid) => {
        if (valid) {
          this.$API.p_newJudgement({
            jClassName: this.JClassForm.JClassName,
            managerId: this.JClassForm.managerId,
            judgement: JSON.stringify(judgeList)
          })
            .then(
              data => {
                // let status = data.status;
                // if (status === '1') {
                //   alert("成功创建指标");
                // } else {
                //   alert("创建指标失败，请重试");
                // }
              }
            )
            .catch(
              error => {
                console.log(error);
              }
            )
        }
      })
      this.JClassForm.JClassId = '';
      this.JClassForm.JClassName = '';
      this.JClassForm.judgement.name = '';
      this.JClassForm.judgement.content = '';
      this.JClassForm.judgement.proportion = '';
      this.JClassForm.judgement.managerId = '';
      judgeList = [];
      listSize = 0;
    },
  }
}
</script>

<style scoped>

</style>
