<template>
  <div class="essay-detail">
    <el-page-header
      @back="goBack"
      title="Back"
      content="Essay Detail"
    ></el-page-header>
    <div class="title mt20">Essay Detail</div>
    <div class="mt20">
      <el-row>
        <el-col :span="24">
          <el-table :data="tableData" style="width: 100%">
            <el-table-column label="Homework" prop="homework"> </el-table-column>
            <el-table-column label="Release Date" prop="releaseDate"> </el-table-column>
            <el-table-column label="End Date" prop="endDate"> </el-table-column>
            <el-table-column label="Quantity submitted" prop="quantity">
              <template slot-scope="scope">
                <el-tag size="medium">{{ scope.row.quantity }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                <el-button
                  type="text"
                  @click="toDetail(scope.$index, scope.row)"
                >view</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-col>
        <el-col class="mt20" style="text-align: center">
          <el-pagination small layout="prev, pager, next" :total="50">
          </el-pagination>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center"> </el-row>
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
          releaseDate: "2016-05-02",
          endDate: "2016-05-02",
          homework: "homework 1",
          address: "上海市普陀区金沙江路 1518 弄",
          quantity: 50,
        },
        {
          releaseDate: "2016-05-04",
          endDate: "2016-05-04",
          homework: "homework 2",
          address: "上海市普陀区金沙江路 1517 弄",
          quantity: 60,
        },
        {
          releaseDate: "2016-05-01",
          endDate: "2016-05-01",
          homework: "homework 3",
          address: "上海市普陀区金沙江路 1519 弄",
          quantity: 70,
        },
        {
          releaseDate: "2016-05-03",
          endDate: "2016-05-03",
          homework: "homework 4",
          address: "上海市普陀区金沙江路 1516 弄",
          quantity: 80,
        },
        {
          releaseDate: "2016-05-03",
          endDate: "2016-05-03",
          homework: "homework 4",
          address: "上海市普陀区金沙江路 1516 弄",
          quantity: 80,
        },
        {
          releaseDate: "2016-05-03",
          endDate: "2016-05-03",
          homework: "homework 4",
          address: "上海市普陀区金沙江路 1516 弄",
          quantity: 80,
        },
        {
          releaseDate: "2016-05-03",
          endDate: "2016-05-03",
          homework: "homework 4",
          address: "上海市普陀区金沙江路 1516 弄",
          quantity: 80,
        },
        {
          releaseDate: "2016-05-03",
          endDate: "2016-05-03",
          homework: "homework 4",
          address: "上海市普陀区金沙江路 1516 弄",
          quantity: 80,
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
        path: "studentEssay",
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
