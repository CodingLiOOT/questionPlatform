<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/ExpertMainPage/ExpertRated' }">已打分报告列表</el-breadcrumb-item>
      <el-breadcrumb-item>查看打分详情</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>
    <el-row>
      <el-steps :active="active" finish-status="success">
        <el-step title="报告已上传"></el-step>
        <el-step title="已分配指标类"></el-step>
        <el-step title="已分配专家"></el-step>
        <el-step title="已完成打分"></el-step>
      </el-steps>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-card shadow="hover">
          <el-row>
            <el-col :span="4">
              关键词：
            </el-col>
            <el-col :span="20">
              <el-tag v-for="item in keyWord" :key="item" class="text item">{{ item }}</el-tag>
            </el-col>
          </el-row>
          <el-row>
            <el-divider content-position="right"></el-divider>
          </el-row>
          <el-row class="el-row">
            <el-card shadow="hover">
              <el-row class="expertDetails">
                <el-col>
                  专家打分情况
                </el-col>
              </el-row>
              <el-row class="expertDetails">
                <el-col>
                  总体打分：{{ this.totalScore }}
                </el-col>
              </el-row>
              <el-row class="expertDetails">
                <el-col>
                  指标打分：
                </el-col>
              </el-row>
              <el-row class="expertDetails">
                <el-col>
                  指标类名称：{{ this.jClass.jClassName }}
                </el-col>
              </el-row>
              <el-row v-for="j in  this.jClass.judgement" :key="j" class="expertDetails">
                <el-col>
                  指标名称：{{ j.judgementName }}<br/>
                  指标内容：{{ j.judgeContent }}<br/>
                  指标权重：{{ j.judgeProportion }}<br/>
                  分数：{{ j.score }}<br/>
                </el-col>
              </el-row>
              <el-row class="expertDetails">
                <el-col>
                  专家建议：{{ this.suggestion }}
                </el-col>
              </el-row>
            </el-card>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="12">
        <div class="tools">
          <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="prePage" class="mr10"> 上一页
          </el-button>
          <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="nextPage" class="mr10"> 下一页
          </el-button>
          <div class="page">{{ pageNum }}/{{ pageTotalNum }}</div>
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
    </el-row>
  </div>

</template>

<script>
import pdf from "vue-pdf";

export default {
  name: "ShowScoreDetails",
  data() {
    return {
      active:'',
      id: '',
      src: pdf.createLoadingTask({
        url: 'http://localhost:8090/static/try.pdf',
        httpHeaders: {
          token:this.$store.state.expert.token
        },
      }),
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
      // 关键词
      keyWord: [],
      // 指标类
      jClass: '',
      // 总分
      totalScore: '',
      // 建议
      suggestion: '',
    }
  },
  mounted() {
    this.showDetail();
  },
  methods: {
    showDetail() {
      let reportId = this.UrlSearch();
      this.$API.p_getScoreDetails({
        reportId: reportId,
        expertname: this.$store.state.expert.expertName
      })
        .then(
          res => {
            this.active=res.reportStatus;
            this.src = pdf.createLoadingTask({
              url: res.reportPdf,
              httpHeaders: {
                token:this.$store.state.expert.token
              },
            })
            for (let i = 0; i < res.keyWords.length; i++) {
              this.keyWord.push(res.keyWords[i].word);
            }
            this.totalScore = res.totalScore;
            this.suggestion = res.suggestion;
            let newjClass = {
              jClassId: '',
              jClassName: '',
              managerId: '',
              judgement: [],
            };
            newjClass.jClassId = res.jClass.jClassId;
            newjClass.jClassName = res.jClass.jClassName;
            newjClass.managerId = res.jClass.managerId;

            for (let i = 0; i < res.jClass.judgement.length; i++) {
              let j = {
                judgeId: '',
                judgementName: '',
                judgeContent: '',
                judgeProportion: '',
                score: '',
              };
              j.judgeId = res.jClass.judgement[i].judgeId;
              j.judgementName = res.jClass.judgement[i].judgeName;
              j.judgeContent = res.jClass.judgement[i].judgeContent;
              j.judgeProportion = res.jClass.judgement[i].judgeProportion;
              j.score = res.jClass.judgement[i].score;
              newjClass.judgement.push(j);
            }

            this.jClass = newjClass;

          }
        )
        .catch({})
    },
    UrlSearch() {
      let str = location.href; //获取到整个地址
      let num = str.indexOf("?")
      str = str.substr(num + 1); //取得num+1后所有参数，这里的num+1是下标 str.substr(start [, length ]
      str = str.substring(3, str.length);
      return str;
    },
    // 上一页函数，
    prePage() {
      let page = this.pageNum;
      page = page > 1 ? page - 1 : this.pageTotalNum;
      this.pageNum = page;
    },
    // 下一页函数
    nextPage() {
      let page = this.pageNum;
      page = page < this.pageTotalNum ? page + 1 : 1;
      this.pageNum = page;
    },
    // 页面加载回调函数，其中e为当前页数
    pageLoaded(e) {
      this.curPageNum = e;
    },
    // 其他的一些回调函数。
    pdfError(error) {
      console.error(error);
    },
  },
  components: {
    pdf,
  },
  created: function () {
    this.id = this.$route.params.id;
  },
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-left: 1.5rem;
  margin-top: 1rem;
}

.el-row {
  margin-bottom: 1rem;
}

.expertDetails {
  margin-left: 0;
}
</style>
