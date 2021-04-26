<template>
  <div>
    <el-card shadow="hover">
      <el-form ref="form"  label-width="100px">
        <el-form-item label="上传报告">
          <el-upload
            class="upload-demo"
            action="http://127.0.0.1:8090/api/file/upload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            :on-change="changeFile"
            :before-remove="beforeRemove"
            multiple
            :limit="3"
            :on-exceed="handleExceed"
            :headers="myHeaders"
            :file-list="fileList">
            <el-button size="small" type="primary">选择文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button @click="uploadFile">上传文件</el-button>
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
      this.items.push({text: ''})
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
      // this.items[0].text是第一个关键词
      alert(JSON.stringify(this.items));
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
    uploadFile(){
      alert(this.fileList)
      let formData=new FormData(this.fileList[0])
      formData.append('file', this.fileList[0])
      alert(formData);
      this.$API.p_testUpload({
        formData: formData
      })
      .then(
        data => {
          alert(data)
        }
      )
        .catch(err => {

        })
    },
    // changeFile(file){
    //   let fd = new FormData()
    //   fd.append('file', file)
    //   fd.append('fileName', file.name)
    //   let config = {
    //     headers: {
    //       'Content-Type': 'multipart/form-data'
    //     }
    //   }
    //   axios.post('/file/upload', fd, config).then(data => {
    //     if (data.code === 200) {
    //       this.$message.info('成功上传')
    //     }
    //   })
    // }
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
