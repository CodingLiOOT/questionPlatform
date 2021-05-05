<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Company/Evaluate' }">报告列表</el-breadcrumb-item>
      <el-breadcrumb-item>分配指标类</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>
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
        width="400">
      </el-table-column>

      <el-table-column
        prop="time"
        label="创建时间"
        width="180">
      </el-table-column>

      <el-table-column
        prop="managerId"
        label="管理者编号"
        width="180">
      </el-table-column>

      <el-table-column
        prop="action"
        label="操作"
        width="160"
        fixed="right">
        <template slot-scope="scope">
          <el-button @click="allocate(scope.row)" type="primary">分配</el-button>
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
export default {
  name: "Edit",
  data() {
    return {
      currentPage: 1,
      filters: [],
      judgeListData: [{
        id: 1,
        name: '架构模式',
        time: '2021-05-05',
        managerId: 9791
      },
        {
          id: 2,
          name: '设计模式',
          time: '2021-05-05',
          managerId: 2871
        },
        {
          id: 3,
          name: '算法的选择',
          time: '2021-05-05',
          managerId: 7890
        }],
      form: {
        name: '',
        time: '',
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
      for (let item in row.tag) {
        if (row.tag[item] === value) {
          return true;
        }
      }
      return false;
    },
    getFilters() {
      for (let item in this.tableData) {
        for (let tag in this.tableData[item].tag) {
          let temp = {text: '', value: '',}
          temp.text = this.tableData[item].tag[tag];
          temp.value = this.tableData[item].tag[tag];
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
    mounted() {
      this.getFilters();
    },
    onSubmit() {
      console.log("send judgement");
    },
    allocate(row) {
      let reportId = this.UrlSearch();
      this.$API.p_allocateJudgement({
        reportId: reportId,
        JClassId: row.id,
      })
        .then(

        )
        .catch(
          error => {
          }
        )
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
