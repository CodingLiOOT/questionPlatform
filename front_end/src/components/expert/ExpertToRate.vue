<template>
  <div>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/ExpertMainPage/ExpertRating' }">返回待打分方案</el-breadcrumb-item>
    <el-breadcrumb-item>打分</el-breadcrumb-item>
  </el-breadcrumb>
  <br/>
  <el-container>
    <el-aside width="400px">
      <el-col :span="12">
        <div class="tools">
          <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="prePage" class="mr10"> 上一页</el-button>
          <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="nextPage" class="mr10"> 下一页</el-button>
          <div class="page">{{pageNum}}/{{pageTotalNum}} </div>
        </div>
        <pdf ref="pdf"
             :src="src"
             :page="pageNum"
             :rotate="pageRotate"
             @progress="loadedRatio = $event"
             @page-loaded="pageLoaded($event)"
             @num-pages="pageTotalNum=$event"
             @error="pdfError($event)"
             @link-clicked="page = $event">
        </pdf>
      </el-col>
    </el-aside>
    <el-main>
      <el-form ref="form" :model="RateForm">
        <el-table
          :data="tableData"
          border
          style="width: 100%">
          <el-table-column
            prop="judgeId"
            label="指标编号"
            width="100">
          </el-table-column>
          <el-table-column
            prop="judgeName"
            label="指标名称"
            width="120">
          </el-table-column>
          <el-table-column
            prop="judgeContent"
            label="指标内容"
            width="170">
          </el-table-column>
          <el-table-column
            prop="judgeProportion"
            label="权重"
            width="80">
          </el-table-column>
          <el-table-column
            prop="score"
            label="得分"
            width="110">
            <template slot-scope="scope">
              <el-input v-if="scope.row.judgeId===editId" v-model="scope.row.score"></el-input>
              <span v-else>{{ scope.row.score}}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button type="primary" v-if="scope.row.judgeId!==editId" @click="changeClick(scope.row)" size="small">评分
              </el-button>
              <el-button type="success" v-if="scope.row.judgeId===editId" @click="saveClick(scope.row)" size="small">保存
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-input
          placeholder="请点击下方按钮计算总分"
          v-model="RateForm.totalScore"
          :disabled="true">
        </el-input>
        <el-form-item>
          <el-button type="primary" @click="handleCountClick()">计算总得分</el-button>
        </el-form-item>

        <el-input
          type="textarea"
          :rows="3"
          placeholder="请输入建议"
          v-model="RateForm.suggestArea">
        </el-input>
      </el-form>
      <el-button type="success" round @click="onSubmit()">提交</el-button>

    </el-main>
  </el-container>
  </div>
</template>

<script>
import pdf from "vue-pdf";
let scoreList = new Array(0);
let scoreListSize = 0;

export default {
  name: "ExpertToRate",

  data() {
    return {
      id:'',
      src:pdf.createLoadingTask({
        url: 'http://localhost:8090/static/try.pdf',
        httpHeaders: {
          token:this.$store.state.token
        },
      }),
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,

      tableData: [],
      RateForm: {
        suggestArea: '',
        totalScore: ''
      },

      editData: [],  //编辑行初始数据
      editId: '',  //判断编辑的是哪一行

      scores:'',
    }
  },
  components:{
    pdf,
  },
  created: function() {
    this.id = this.$route.params.id;
  },
  mounted() {
    this.getList();
    this.getFilters();
    this.showPDF();
  },

  methods: {
    showPDF() {
      this.$API.p_getReportDetail({
        reportId:this.$route.query.reportId
      })
      .then(
        res => {
          this.src=pdf.createLoadingTask({
            url: res.file,
            httpHeaders: {
              token:this.$store.state.token
            },
          })
        }
      )
      .catch(
        error => {
          console.log(error);
      })
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
    //从后端获取指标列表放在界面上
    getList() {
      let str = this.UrlSearch();
      this.$API.p_getOneReport({
        reportId: str
      })
        .then(
          data => {

            this.src=pdf.createLoadingTask({
              url: data.reportPdf,
              httpHeaders: {
                token:this.$store.state.token
              },
            })
            //显示该报告相应指标
            let jClassId = data.jClass.jClassId;
            for (let i = 0; i < data.jClass.judgements.length; i++) {
              let temp = {
                judgeId: '',
                judgeName: '',
                judgeContent: '',
                judgeProportion: '',
                score:'',
              };
              temp.judgeId = data.jClass.judgements[i].judgeId;
              temp.judgeName = data.jClass.judgements[i].judgeName;
              temp.judgeContent = data.jClass.judgements[i].judgeContent;
              temp.judgeProportion= data.jClass.judgements[i].judgeProportion;
              temp.score=0;
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
    // 编辑指标接口
    changeClick(row) {
      if (this.tableData.some((item) => {
        return item.judgeId === '';
      })) {
        this.$message({
          message: '请先保存',
          type: 'warning',
        });
        return;
      }
      this.editId = row.judgeId;
    },
    // 保存指标接口
    saveClick(row) {
      let element = {
        judgeId: row.judgeId,
        score: row.score,
        proportion: row.judgeProportion
      };
      scoreListSize = scoreList.push(element);
      this.editId = '';
    },
    onSubmit() {
      let str = this.UrlSearch();
      let judgeWithScore = new Array(0);
      this.scores=judgeWithScore;
      let judgeWithScoreSize = 0;
      for(let i=0;i<scoreListSize;i++){
        let element = {
          judgeId: scoreList[i].judgeId,
          score: parseInt(scoreList[i].score),
        };
        judgeWithScoreSize = judgeWithScore.push(element);
      }
      this.$API.p_sendScores({
        reportId: str,
        expertname: '李一',
        judgeWithScore: JSON.stringify(this.scores),
        totalScore: this.count(),
        suggestion: this.RateForm.suggestArea
      })
      .then(
        data => {
          // 无返回
        }
      )
      .catch(
        error => {
          console.log(error);
        }
      )

      //clean array
      scoreList = [];
      scoreListSize = 0;
    },
    count() {
      let total_score = 0;
      let total_proportion = 0;
      for (let i=0;i<scoreListSize;i++) {
        total_score += scoreList[i].score * scoreList[i].proportion;
        total_proportion += parseInt(scoreList[i].proportion);
      }
      let result = total_score/total_proportion;
      return result;
    },
    handleCountClick() {
      let result = this.count();
      this.RateForm.totalScore = result;
    },
    // 上一页函数，
    prePage() {
      let page = this.pageNum
      page = page > 1 ? page - 1 : this.pageTotalNum
      this.pageNum = page
    },
    // 下一页函数
    nextPage() {
      let page = this.pageNum
      page = page < this.pageTotalNum ? page + 1 : 1
      this.pageNum = page
    },
    // 页面加载回调函数，其中e为当前页数
    pageLoaded(e) {
      this.curPageNum = e
    },
    // 其他的一些回调函数。
    pdfError(error) {
      console.error(error)
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
