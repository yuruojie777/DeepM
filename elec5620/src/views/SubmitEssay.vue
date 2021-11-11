<template>
  <div class="essay-detail">
    <el-page-header
      @back="goBack"
      title="Back"
    ></el-page-header>
    <div class="title mt20">Past Submission List</div>
    <div class="mt20">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="Homework" prop="homework"> </el-table-column>
        <el-table-column label="Submit time" prop="submitTime"> </el-table-column>
        <el-table-column label="Grade" prop="grade"> </el-table-column>
        <el-table-column>
          <template slot-scope="scope">
            <el-button type="text" @click="toDetail(scope.$index, scope.row)">
              view
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      size: "",
      tableData: [
        {
          submitTime: "2016-05-02",
          grade: "22",
          homework: "homework 1",
        },
        {
          submitTime: "2016-05-02",
          grade: "22",
          homework: "homework 2",
        },
      ],
    };
  },
  methods: {
    goBack() {
      console.log("go back");
      this.$router.go(-1);
    },
    toDetail() {
      this.$router.push({
        path: "studentDetail",
      });
    },
  },
};
</script>

<style>
.essay-detail {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}
</style>
