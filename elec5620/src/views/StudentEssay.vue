<template>
  <div class="student-essay">
    <el-page-header
      @back="goBack"
      title="Back"
      content="Student Essay"
    ></el-page-header>
    <div class="title mt20">Student Essay</div>
    <div class="student-essay-list mt20">
      <el-row>
        <el-col :span="12" :offset="0">
          <el-input
            v-model="search"
            placeholder="Place Enter Score or Date"
            size="normal"
            clearable
          >
            <template slot="prepend">Search</template>
          </el-input>
        </el-col>
      </el-row>
      <el-row class="mt20">
        <el-col :span="24">
          <el-table :data="tableData" style="width: 100%">
            <el-table-column label="Student name">
              <template slot-scope="scope">
                <el-tag size="medium">{{ scope.row.sid }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="Date" prop="submitTime"></el-table-column>
            <el-table-column label="Score">
              <template slot-scope="scope">
                {{scope.row.grade == -1 ? "not graded" : scope.row.grade }}
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
      search: "",
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          score: 50,
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
          score: 60,
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
          score: 70,
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
          score: 80,
        },
      ],
      tid: +localStorage.getItem("uid")
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
    initTableData() {
      this.$axios
        .get("/essay/teacher/?tid=" + this.tid)
        .then((res) => {
          this.tableData = res.data.data;
          console.log(res.data.data);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
  created(){
    this.initTableData();
  }
};
</script>

<style>
.student-essay {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}

.student-essay .student-essay-list {
  padding: 10px;
  border: 1px solid #eee;
  border-radius: 4px;
}
</style>
