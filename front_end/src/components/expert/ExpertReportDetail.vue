<template>
  <div>
    <el-row>
      <el-steps :active="active" finish-status="success">
        <el-step title="报告已上传"></el-step>
        <el-step title="已分配指标类"></el-step>
        <el-step title="已分配专家"></el-step>
        <el-step title="已完成打分"></el-step>
      </el-steps>
    </el-row>
    <el-row>
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
    </el-row>
  </div>
</template>


<script>
import pdf from "vue-pdf";
export default {
  name: "ReportDetail",
// })
  data(){
    return{
      active:'',
      id:'',
      src:pdf.createLoadingTask({
        url: 'http://localhost:8090/static/try.pdf',
        httpHeaders: {
          token:this.$store.state.token
          // token:"eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MjMyNTAyODMsInN1YiI6Ik5hbmN5MTIzIiwiY3JlYXRlZCI6MTYyMzI0Nzg4MzcwNn0.qlIGPbE_yqIWNP6-_OjOPK36AxSTCzrL0cMatml7ocmoeOpByFPXrfV-4cAO2LvA9i4pVLj6jaaTIJRnXr-aQw"
        },
      }),
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
      // 标签词
      keyWord:[],
      // 专家
      grades:[
      ],
      judgement:[
      ],
    }
  },
  methods:{
    showDetail(){
      this.$API.p_getReportDetail({
        reportId:this.$route.query.reportId
      })
        .then(
          res=>{
            alert("res")
            alert(res.file)
            this.src=pdf.createLoadingTask({
              url: res.file,
              httpHeaders: {
                token:this.$store.state.token
              },
            })
            this.active=res.reportStatus;
            for(let i=0;i<res.keyWord.length;i++){
              this.keyWord.push(res.keyWord[i].word);
            }
            for(let i=0;i<res.grades.length;i++){
              let grade={
                expertName:'',
                totalScore:'',
                suggestion:'',
                judgement: [],
              };
              grade.expertName=res.grades[i].expertName;
              grade.totalScore=res.grades[i].totalScore;
              grade.suggestion=res.grades[i].suggestion;
              this.grades.push(grade);
            }
            for(let i=0;i<res.judgement.length;i++){
              let j={
                expertName:'',
                judgementName:'',
                judgementContent:'',
                score:'',
              };
              j.expertName=res.judgement[i].expertName;
              j.judgementName=res.judgement[i].judgementName;
              j.judgementContent=res.judgement[i].judgementContent;
              j.score=res.judgement[i].score;
              this.judgement.push(j);
            }
            for(let i=0;i<this.grades.length;i++){
              for(let j=0;j<this.judgement.length;j++){
                if(this.grades[i].expertName===this.judgement[j].expertName){
                  this.grades[i].judgement.push(this.judgement[j])
                }
              }
            }
          }
        )
        .catch({
        })
    },
    // show pdf
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
  },
  components:{
    pdf,
  },
  created: function() {
    this.id = this.$route.params.id;
  },
  mounted() {
    console.log(this.$store.state.token)
    this.showDetail();
  }
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
.el-row{
  margin-bottom: 1rem;
}
.expertName{
  margin-left:0
}
</style>
