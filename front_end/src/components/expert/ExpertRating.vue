<template>
  <div>
    <el-table
      :data="tableData"
      border
      style="width: 100%;height: 100%">
      <el-table-column
        prop="id"
        label="报告编号"
        width="80">
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
            :key="scope.row">{{ item }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="创建时间">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
      </el-table-column>
      <el-table-column
        label="详细信息">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" style="color: red">打分</el-button>
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
  name: "ExpertRating",
  data() {
    return {
      currentPage: 1,
      filters: [],
      tableData: [{
        id: 1,
        name: '中石油探井信息资源共享',
        time: '2016-05-03',
        status: '未分配指标',
        tag: ['石油', '井'],
      },
        {
          id: 2,
          name: '中石化可研报告',
          time: '2016-05-03',
          status: '已分配指标',
          tag: ['石油', '国家能源'],
        },
        {
          id: 3,
          name: '电力信息资源共享',
          time: '2016-05-03',
          status: '已分配指标',
          tag: ['电', '安全'],
        },
        {
          id: 4,
          name: '煤矿信息资源共享',
          time: '2016-05-03',
          status: '未评分',
          tag: ['煤矿'],
        },]
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
    //获取待打分列表
    /**
    getReportList() {
      this.$API.g_getReportList({
        expertName:'sd'
      })
        .then(
          data => {
            console.log(data);
            for (let i = 0; i < data.reports.length; i++) {
              let temp = {
                id: '',
                name: '',
                time: '',
                tag: [],
              };
              temp.id = data.reports[i].reportId;
              temp.name = data.reports[i].reportName;
              temp.time = data.reports[i].createTime;
              for (let j = 0; j < data.reports[i].keyWord.length; j++) {
                let k = data.reports[i].keyWord[j].word;
                temp.tag.push(k);
              }
              alert(temp)
              this.tableData.push(temp);

            }

          }
        )
        .catch(
          error => {
            console.log(error);
          }
        )
    },
     */
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
    //获取已打分报告列表
    getList() {
      this.$API.p_getRatedReportList()
        .then(
          data => {
            for (let i = 0; i < data.reports.length; i++) {
              let temp = {
                id: '',
                name: '',
                tag: [],
                time: '',
                status: ''
              };
              temp.id = data.reports[i].reportId;
              temp.name = data.reports[i].reportName;
              for (let j = 0; j < data.reports[i].reportkeyWord.length; j++) {
                let k = data.reports[i].reportkeyWord[j].word;
                temp.tag.push(k);
              }
              temp.time = data.reports[i].time;

              let reStatus = data.reports[i].reportStatus;
              switch (reStatus){
                case 1:
                  temp.status = '待分配指标类';
                  break;
                case 2:
                  temp.status = '待分配指标类';
                  break;
                case 3:
                  temp.status = '待分配指标类';
                  break;
                case 4:
                  temp.status = '待分配指标类';
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
            console.log(error)
          }
        )
    }
  },

  mounted() {
    //this.getReportList();
    this.getList();
    this.getFilters();
  }
}
</script>

<style scoped>

</style>
