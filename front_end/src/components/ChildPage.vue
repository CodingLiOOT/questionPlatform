<template>
  <div class="hello">
    <el-row>
      <el-col :span="6">
        <p>标签词：{{index+1}}</p>
      </el-col>
      <el-col :span="14">
        <el-input v-model="tags.word" placeholder="请输入标签词"></el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="danger" icon="el-icon-delete" circle @click="deleteTag"></el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  props: {
    index: {
      type: Number,
      required: true
    },
    items: {
      type: Array,
      default: Array
    }
  },
  data () {
    return {
      tags:{
        word:'',
      }
    }
  },
  watch: {
    tags: {
      handler (newV, oldV) {
        if (newV.word.length === 0) {
          return false
        }
        this.$emit('uploadData', {index: this.index, data: newV})
      },
      deep: true
    },
    items: {
      handler (newV, oldV) {
        if (newV.length !== 0) {
          this.tags = {...newV[this.index]}
        }
      },
      deep: true
    }
  },
  methods: {
    deleteTag: function () {
      this.$emit('deleteIndex', this.index)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
