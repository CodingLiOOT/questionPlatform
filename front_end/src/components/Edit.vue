<template>
  <div>
    <el-row>
      <el-col :span="12">
        <el-form
          ref="form"
          :inline="true"
          label-width="80px"
        >
          <el-row class="el-row">
            <el-input placeholder="评估指标描述"
                      style="width: 350px;margin-right: 10px">
            </el-input>
          </el-row>
        </el-form>

        <el-row class="el-row">
          <el-button @click="addItem">增加</el-button>
        </el-row>



      </el-col>
      <el-col :span="12">
        <div class="tools">
          <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="prePage" class="mr10"> 上一页</el-button>
          <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="nextPage" class="mr10"> 下一页</el-button>
          <div class="page">{{pageNum}}/{{pageTotalNum}} </div>
        </div>
        <pdf ref="pdf"
             :src="url"
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
  name: "ReportDetail",
  data(){
    return{
      id:'',
      url:"http://storage.xuetangx.com/public_assets/xuetangx/PDF/PlayerAPI_v1.0.6.pdf",
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
    }
  },
  components:{
    pdf,
  },
  created: function() {
    this.id = this.$route.params.id;
  },
  methods:{
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
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}
.item {
  margin-left: 1.5rem;
}
.el-row{
  margin-bottom: 1rem;
}
</style>
