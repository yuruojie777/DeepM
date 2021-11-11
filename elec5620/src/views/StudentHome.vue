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
              <span> {{studentname}} </span>
            </div>
            <div class="text item">
              <span>Email: </span>
              <span>{{studentemail}}</span>
            </div>
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
      studentname:'',
      studentemail:'',

      btns: [
        {
          path: "upcomingEssay",
          title: "Upcoming Assignments",
          desc: "Check your upcoming assignment",
        },
        {
          path: "submissionList",
          title: "Past Submission List",
          desc: "Check your submission list",
        },
        {
          path: "feedback",
          title: "Feedback",
          desc: "give your feedback",
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
    getinfo(){
      this.ticket = localStorage.getItem('ticket')
      console.log(this.ticket)
      this.$axios.get('/user/id/?ticket='+ this.ticket)
        .then(res=>{
          console.log(res);
          this.studentname=res.data.user.name
          this.studentemail=res.data.user.email
        })
        .catch(function (error) {
          console.log(error);
        })
    },

  },
  mounted(){
    this.getinfo()
  }
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
