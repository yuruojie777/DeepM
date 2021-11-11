<template>
  <div class="teacher-home">
    <el-page-header
      @back="goBack"
      title="Back"
      content="Teacher Home"
    ></el-page-header>
    <div class="title mt20">Teacher Home</div>
    <div class="teacher-home-body">
      <el-row>
        <el-col :span="8">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>UserInfo</span>
            </div>
            <div class="text item">
              <span>Name: </span>
              <span>{{teachername}} </span>
            </div>
            <div class="text item">
              <span>Email: </span>
              <span>{{teacheremail}} </span>
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
              >To learn more</el-button
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
      teachername:'',
      teacheremail:'',
      currentDate: new Date(),
      btns: [
        {
          path: "essayDetail",
          title: "Historical release",
          desc: "Check out the composition assigned earlier",
        },
        {
          path: "setEssay",
          title: "Release the topic",
          desc: "Release new composition topics",
        },
        {
          path: "feedback",
          title: "Suggestions and Feedback",
          desc: "Submit suggestions and feedback",
        },
      ],
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
          this.teachername=res.data.user.name
          this.teacheremail=res.data.user.email
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
.teacher-home {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}

.teacher-home-body {
  padding-left: 30px;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.info-item {
  padding: 14px;
}

.info-item:nth-child(odd) {
  padding: 0 14px;
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
  width: 480px;
}

.bg-ecf5fc {
  color: #333333;
  background: #ecf5fc;
}
.bg-dfedfa {
  color: #333333;
  background: #dfedfa;
}
.bg-d3e2f7 {
  color: #333333;
  background: #d3e2f7;
}

.el-row + .el-row {
  margin-top: 30px;
}
</style>
