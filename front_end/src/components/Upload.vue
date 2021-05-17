<template>
  <div>
    <el-card shadow="hover" :mode="uploadForm">
      <el-form ref="form"  label-width="100px">
        <el-form-item label="上传报告">
          <el-upload
            class="upload-demo"
            action=""
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            :before-remove="beforeRemove"
            multiple
            :limit="3"
            :on-exceed="handleExceed"
            :headers="myHeaders"
            :file-list="fileList"
            :http-request="uploadOk">
            <el-button size="small" type="primary">选择文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item>
        </el-form-item>
        <el-form-item label="填写关键词">
          <div class="inputTag">
            <child-page v-for="(item,index) in items"
                        :key="index"
                        :index="index"
                        :items="items"
                        @deleteIndex="del"
                        @uploadData="getData">
            </child-page>
            <el-button type="primary" icon="el-icon-circle-plus" @click="add" round>添加</el-button>
            <el-button type="success" icon="el-icon-success" round @click="send">提交</el-button>
          </div>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import ChildPage from "./ChildPage";
import pdf from 'vue-pdf';
import axios from "axios";
export default {
  data () {
    return {
      file:'',
      filename:'',
      fileList: [],
      myHeaders:{
        'token':this.$store.state.token
      },

      items: [{}],
      dataRec: [],
    }
  },
  components: {
    ChildPage,
    pdf,
  },
  methods:{
    //  add tag
    add: function () {
      this.items.push({word: ''})
    },
    // delete tag
    del: function (index) {
      //  至少有一个标签词
      if (this.items.length !== 1) {
        this.items.splice(index, 1)
        console.log('deleted:', JSON.stringify(this.items))
      }
    },
    //  get the data from child
    getData: function (val) {
      let index = val.index
      this.items[index] = val.data
      console.log('I got the data:', JSON.stringify(this.items))
    },
    send(){
      let fd = new FormData()
      fd.append("username",this.$store.state.user.username)
      fd.append('file', this.file)
      fd.append('fileName', this.file.name)
      fd.append("keyWord",JSON.stringify(this.items))
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data',
        }
      }
      axios.post('/file/upload', fd, config).then(data => {
        if (data.code === 200) {
          this.$message.info('成功上传')
        }
      })

    },
    // upload pdf
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: '成功',
        message: `文件上传成功`
      });
    },
    uploadOk(val){
      this.file=val.file;
      this.filename=val.file.name;
    }
  },
}
</script>

<style scoped>
.inputTag{
  margin-top: 2rem;
}
.el-icon-circle-plus{
  color: #6ba2db;
  size: A3;
}
</style>
