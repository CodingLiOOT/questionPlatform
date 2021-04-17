<template>
  <el-row>
    <el-col :span="12">
      <el-upload
        class="upload-demo"
        ref="upload"
        :action="action"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-upload="beforeUpload"
        multiple
        :limit="1"
        :on-change="fileChange"
        :on-success="handleSuccess"
        :on-exceed="handleExceed"
        :file-list="fileList"
        :headers="gisqToken">
        <el-button size="small" type="primary">上传文件</el-button>
        <span slot="tip" class="text">请上传.pdf文件</span>
      </el-upload>

    </el-col>
    <el-col :span="12">
      <iframe :src="pathUrl" width="100%" height="100%"></iframe>
    </el-col>
  </el-row>

</template>

<script>
export default {
  data () {
    return {
      pathUrl:''
    }
  },
  methods:{
    // 关于上传pdf部分 start
    handleSuccess (res, file) {  // 上传成功的钩子
      console.log(file,'成功文件')
      // console.log(res, 'res');
      // // this.$refs.upload.clearFiles();
      // this.fileList = [];
      // if (res.status == 200) {
      //   if(res.msg){
      //     this.$message.success(res.msg);
      //   }else{
      //     this.$message.success("文件导入成功");
      //   }
      //   // this.dialogVisible = false;
      // } else {
      //   if(res.msg){
      //     this.$message.error(res.msg);
      //   }else{
      //     this.$message.error("文件导入失败");
      //   }
      // }
    },
    fileChange (file, fileList) {  //文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
      console.log(file, fileList)

      //解决无法判断el-upload是否上传过文件问题
      this.fileList = fileList
    },
    handleRemove(file, fileList) {//文件列表移除文件时的钩子
      console.log(file, fileList);
    },
    handlePreview(file) {//点击文件列表中已上传的文件时的钩子
      console.log(file,'考核标准名称');
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeUpload(file){//文件上传之前的钩子函数
      console.log("文件", file);
      this.file = file;
      console.log(this.file,'sadasdas')
      this.fileName = file.name;
      // this.fileSize = file.size;
      const extension = file.name.split('.').slice(-1) == 'pdf'
      if (!extension) {
        this.$message.warning('上传模板只能是pdf格式!')
        return false
      }
      // let reader = new FileReader();
      // reader.readAsDataURL(file);
      // console.log(new FileReader().readAsDataURL(file),'reader.readAsDataURL(file)')
      // console.log(reader.result,'reader.result')

      // let that = this;
      // reader.onload = function() {
      //   that.fileData = reader.result;
      // };
      // console.log(that.fileData,'that.fileData')
      // return false; // 返回false不会自动上传
    },
    // 删除文件之前的钩子
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    // 关于上传pdf部分 end


  },
  mounted () {
    this.pathUrl = '../../../../../static/pdf/web/viewer.html?file=' + encodeURIComponent('http://192.168.1.110:5000/assets/doc/xx调度方案7.pdf')   // 找到之前引进来pdf文件中的的viewe.html并+pdf地址
  },
}
</script>
