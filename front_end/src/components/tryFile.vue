<!--<template>-->
<!--  <el-upload-->
<!--    :before-upload="beforeAlbumUpload"-->
<!--    :on-success="handleAlbumSuccess"-->
<!--    :show-file-list="false"-->
<!--    accept="image/*"-->
<!--    action="http://127.0.0.1:8090/api/file/upload"-->
<!--    class="avatar-uploader"-->
<!--    name="file">-->
<!--&lt;!&ndash;    <img :src="imageUrl" alt="专辑图片" class="avatar" v-if="imageUrl">&ndash;&gt;-->
<!--    <i class="el-icon-plus avatar-uploader-icon" v-else></i>-->
<!--  </el-upload>-->

<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "tryFile",-->
<!--  methods:{-->
<!--    beforeAlbumUpload(file any) {-->
<!--      const isJPG = file.type === 'image/jpeg';-->
<!--      const isLt2M = file.size / 1024 / 1024 < 2;-->

<!--      if (!isJPG) {-->
<!--        this.$message.error('上传图片只能是 JPG 格式!');-->
<!--      }-->
<!--      if (!isLt2M) {-->
<!--        this.$message.error('上传图片大小不能超过 2MB!');-->
<!--      }-->
<!--      return isJPG && isLt2M;-->
<!--    },-->
<!--    handleAlbumSuccess(res: any, file: any) {-->
<!--      // res表示后端的返回值，其中应包含文件上传后的url路径-->
<!--      console.log(res);-->
<!--      // file表示这个上传文件本身-->
<!--      console.log(file);-->
<!--      // 不是很清楚这个URL创建的方法，应该是文件展示的一种方式-->
<!--      // this.imageUrl = URL.createObjectURL(file.raw);-->
<!--      // 此处可以将上传成功的文件url赋值给image的src属性，一般为相对路径，需要拼接为完整的url-->
<!--      this.imageUrl = 'http://www.another.ren/audios/' + res;-->
<!--    }-->


<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
