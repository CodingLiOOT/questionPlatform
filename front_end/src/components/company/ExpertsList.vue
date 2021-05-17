<template>
  <div>
    <el-input
      v-model="input"
      :placeholder="placeholder"
      prefix-icon="el-icon-search"
      style="width: 350px;margin-right: 10px"
      clearable
      @clear="search"
      @keydown.enter.native="search">
    </el-input>
    <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
    <el-button icon="el-icon-plus" @click="invite">邀请专家</el-button>

    <el-card shadow="hover" style="margin-top:40px">
      <el-form ref="form"  label-width="200px">
        <el-row>
          <el-col span="4">
            <div class="left">
              <img src="../../assets/icon.jpg" alt="" style="width:100px; height:100px">
            </div>
          </el-col>
          <el-col span="10">
            <el-form-item label="专家姓名：张三"></el-form-item>
            <el-form-item label="关键词：石油"></el-form-item>
          </el-col>
          <el-col span="10">
            <el-form-item label="所属单位：XX公司"></el-form-item>
            <el-form-item label="专家简介：......"></el-form-item>
          </el-col>
        </el-row>


      </el-form>
    </el-card>
    <el-card shadow="hover">
      <el-form ref="form"  label-width="200px">
        <el-row>
          <el-col span="4">
            <div class="left">
              <img src="../../assets/icon.jpg" alt="" style="width:100px; height:100px">
            </div>
          </el-col>
          <el-col span="10">
            <el-form-item label="专家姓名：李四"></el-form-item>
            <el-form-item label="关键词：教育"></el-form-item>
          </el-col>
          <el-col span="10">
            <el-form-item label="所属单位：XX公司"></el-form-item>
            <el-form-item label="专家简介：......"></el-form-item>
          </el-col>
        </el-row>


      </el-form>
    </el-card>
    <el-card shadow="hover">
      <el-form ref="form"  label-width="200px">
        <el-row>
          <el-col span="4">
            <div class="left">
              <img src="../../assets/icon.jpg" alt="" style="width:100px; height:100px">
            </div>
          </el-col>
          <el-col span="10">
            <el-form-item label="专家姓名：王五"></el-form-item>
            <el-form-item label="关键词：生物"></el-form-item>
          </el-col>
          <el-col span="10">
            <el-form-item label="所属单位：XX公司"></el-form-item>
            <el-form-item label="专家简介：......"></el-form-item>
          </el-col>
        </el-row>


      </el-form>
    </el-card>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-size="10"
      layout="prev, pager, next, jumper"
      :total="10">
    </el-pagination>
  </div>
</template>

<script>
import {unique} from "webpack-merge";
export default {
  name: "List",
  props: {
    disabled: {
      type: Boolean,
      default: true
    },
    placeholder: {
      type: String,
      default: '输入关键词进行搜索'
    },
    data() {
      return {
        currentPage: 1,
        filters: [],
        input: '',
      }
    },
    methods: {
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      // 选中查看某一条
      handleClick(row) {
        console.log(row);
        this.$router.push({
          path: 'ReportDetail',
          query: {
            id: row.id,
          }
        });
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
    },
    mounted() {
      this.getFilters();
    },
    search() {
      this.$emit("search", ['search', this.keyword])
    },
    invite() {
    },
  }
}
</script>

<style scoped>
.left{
  text-align: left
}
.center{
  text-align: center
}
</style>
