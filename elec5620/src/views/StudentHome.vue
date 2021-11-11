<template>
  <div class="student-home">
    <el-page-header @back="goBack" title="Back"></el-page-header>
    <div class="title mt20">Student Home</div>
    <div class="body">
      <el-row>
        <el-col :span="8">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>Student Info</span>
            </div>

            <div class="text item">
              <span>Name: </span>
              <span>{{ name }}</span>
            </div>
            <div class="text item">
              <span>Email: </span>
              <span>{{ email }}</span>
            </div>
            <el-button type="primary" @click="gofeedback">feedback</el-button>
          </el-card>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="8" v-for="(item, i) of btns" :key="i">
          <el-card
            class="box-card"
            :class="[i == 0 ? 'bg-ecf5fc' : i == 1 ? 'bg-dfedfa' : 'bg-d3e2f7']"
          >
            <div slot="header" class="clearfix">
              <span>{{ item.title }}</span>
              <el-button
                style="float: right; padding: 3px 0"
                type="text"
                @click="$router.push({ path: item.path })"
                >More</el-button
              >
            </div>
            <div class="text item">{{ item.desc }}</div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "xx",
      email: "xx@163.com",
      btns: [
        {
          path: "upcomingEssay",
          title: "Upcoming Assignments",
          desc: "",
        },
        {
          path: "submissionList",
          title: "Past Submission List",
          desc: "",
        },
        {
          path: "announcement",
          title: "Announcement",
          desc: "",
        },
      ],
      uid: localStorage.getItem("uid"),
    };
  },
  methods: {
    goBack() {
      console.log("go back");
      this.$router.go(-1);
    },
    gofeedback() {
      this.$router.push("/Feedback");
    },
    getInfo() {
      this.$axios
        .get(`/user/?id=${this.uid}`)
        .then((res) => {
          console.log(res);
          this.name = res.data.data.name;
          this.email = res.data.data.email;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
  created() {
    this.getInfo();
  },
};
</script>

<style>
.student-home {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}

.body {
  padding-left: 30px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 400px;
}

.el-row + .el-row {
  margin-top: 30px;
}
</style>
