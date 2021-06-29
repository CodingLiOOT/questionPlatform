<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Company/Evaluate' }">报告列表</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>
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
        width="250">
      </el-table-column>

      <el-table-column
        prop="tag"
        label="标签"
        width="200"
        :filters=this.filters
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
            v-for="item in scope.row.tag"
            :key="scope.row">{{ item }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column
        prop="time"
        label="创建时间"
        width="120">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
      </el-table-column>

      <el-table-column
        prop="jClassName"
        label="指标类">
      </el-table-column>

      <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <el-button @click="handleDetail(scope.row)" type="text" size="small">查看</el-button>
          <el-button @click="handleClick(scope.row)" type="text" size="small">分配指标类</el-button>
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
  name: "Evaluate",
  data() {
    return {
      currentPage: 1,
      filters: [],
      tableData: []
    }
  },
  methods: {
    handleDetail(row) {
      console.log(row);
      this.$router.push({
        path: 'DetailReport',
        query: {
          reportId: row.id,
        }
      });
    },
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
        path: 'Edit',
        query: {
          id: row.id,
        }
      });
    },
    filterTag(value, row) {
      for (let item in row.tag) {
        if (row.tag[item] === value) {
          return true;
        }
      }
      return false;
    },
    getAllReportList() {
      this.$API.p_getAllReportList()
        .then(
          data => {
            for (let i = 0; i < data.reports.length; i++) {
              let temp = {
                id: '',
                name: '',
                time: '',
                tag: [],
                information: '',
                jClassName:'',
              };
              temp.id = data.reports[i].reportId;
              temp.name = data.reports[i].reportName;
              temp.time = data.reports[i].createTime;
              temp.jClassName=data.reports[i].jClassName;
              if (temp.jClassName == null) {
                temp.jClassName = '未分配';
              }
              for (let j = 0; j < data.reports[i].keyWord.length; j++) {
                let k = data.reports[i].keyWord[j].word;
                temp.tag.push(k);
              }
              let reStatus = parseInt(data.reports[i].reportStatus);
              switch (reStatus){
                case 1:
                  temp.status = '待分配指标类';
                  break;
                case 2:
                  temp.status = '待分配专家';
                  break;
                case 3:
                  temp.status = '专家待打分';
                  break;
                case 4:
                  temp.status = '已完成打分';
                  break;
                default:
                  temp.status = '无';
                  break;
              }
              this.tableData.push(temp);
            }
          }
        )
        .catch(
          error => {
          }
        )
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
  },
  mounted() {
    this.getAllReportList();
    this.getFilters();
    this.getList();
  }
}
</script>

<style scoped>
</style>
