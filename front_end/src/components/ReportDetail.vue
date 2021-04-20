<template>
  <div>
    <el-row>
      <el-col :span="12">
        <el-card shadow="hover">
          <el-row>
            <el-col :span="4">
              标签词：
            </el-col>
            <el-col :span="20">
              <el-tag v-for="o in 4" :key="o" class="text item">标签{{o}}</el-tag>
            </el-col>
          </el-row>
          <el-row>
            <el-divider content-position="right"></el-divider>
          </el-row>
          <el-row class="el-row">
            专家打分情况
          </el-row>
        </el-card>
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
