<template>
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      width="200px"
      label="专家个人信息"
      prop="id">
    </el-table-column>
    <el-table-column>
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="专家姓名">
            <span>{{ props.row.name }}</span>
          </el-form-item>
          <el-form-item label="所属单位">
            <span>{{ props.row.unit }}</span>
          </el-form-item>
          <el-form-item label="关键词">
            <span>{{ props.row.key }}</span>
          </el-form-item>
          <el-form-item label="专家类型">
            <span>{{ props.row.type }}</span>
          </el-form-item>
          <el-form-item label="邮箱">
            <span>{{ props.row.email }}</span>
          </el-form-item>
          <el-form-item label="电话">
            <span>{{ props.row.phone }}</span>
          </el-form-item>
          <el-form-item label="专家简介">
            <span>{{ props.row.introduction }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
  </el-table>
</template>

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>

<script>
export default {
  name: 'ExpertHome',
  data() {
    return {
      tableData: [{
        name: '张三',
        unit: '北京交通大学软件学院',
        key: '数据库',
        type: '数据库专家',
        email: '12345678@bjtu.edu.cn',
        phone: '19876543210',
        introduction: '数据库专家'
      }]
    }
  }
}
</script>
