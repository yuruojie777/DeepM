<template>
  <div class="teacher-home">
    <el-page-header
      @back="goBack"
      title="Back"
      content="Teacher Home"
    ></el-page-header>
    <div class="title mt20">Teacher Home</div>
    <el-row>
      <!-- <el-col :span="8">
        <el-descriptions title="UserInfo">
          <el-descriptions-item label="Name">好吃的汉堡</el-descriptions-item>
          <el-descriptions-item label="Email"
            >xxx@163.com</el-descriptions-item
          >
          <el-descriptions-item label="Remark">
            <el-tag size="small">Teacher</el-tag>
          </el-descriptions-item>
        </el-descriptions>
        <el-descriptions direction="vertical" :colon="false">
          <el-descriptions-item
            ><el-button
              type="primary"
              plain
              @click="$router.push({ path: 'essayDetail' })"
              >历史发布</el-button
            ></el-descriptions-item
          >
          <el-descriptions-item
            ><el-button
              type="info"
              plain
              @click="$router.push({ path: 'setEssay' })"
              >发布题目</el-button
            ></el-descriptions-item
          >
          <el-descriptions-item>
            <el-button
              type="warning"
              plain
              @click="$router.push({ path: 'feedback' })"
              >反馈</el-button
            >
          </el-descriptions-item>
        </el-descriptions>
      </el-col> -->
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>UserInfo</span>
          </div>
          <div class="text item">
            <span>Name: </span>
            <span>好吃的汉堡</span>
          </div>
          <div class="text item">
            <span>Email: </span>
            <span>xxx@163.com</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8" :offset="1">
        <el-card class="box-card" style="text-align: center">
          <el-button
            type="primary"
            plain
            @click="$router.push({ path: 'essayDetail' })"
            >Historical release</el-button
          >
          <el-button
            type="info"
            plain
            @click="$router.push({ path: 'setEssay' })"
            >Release the topic</el-button
          >
          <el-button
            type="warning"
            plain
            @click="$router.push({ path: 'feedback' })"
            >feedback</el-button
          >
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentDate: new Date(),
    };
  },
  methods: {
    goBack() {
      console.log("go back");
      this.$router.go(-1);
    },
  },
};
</script>

<style>
.teacher-home {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
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
</style>