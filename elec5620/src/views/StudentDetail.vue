<template>
  <div class="studen-detail">
    <el-page-header
      @back="goBack"
      title="Back"
      content="Student Detail"
    ></el-page-header>
    <div class="studen-detail-top">
      <div class="article-inner"></div>
      <div class="result">
        <div class="title">Result</div>
        <div class="result-inner">
          <div class="circle">75分</div>
          <div class="items">
            <table>
              <tr>
                <td>Vocabulary scores</td>
                <td><span class="num">78.7</span>分</td>
              </tr>
              <tr>
                <td>Lexical score</td>
                <td><span class="num">72.7</span>分</td>
              </tr>
              <tr>
                <td>Logic score</td>
                <td><span class="num">82.5</span>分</td>
              </tr>
              <tr>
                <td>Content score</td>
                <td><span class="num">67.2</span>分</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div class="studen-detail-bottom">
      <div class="title">score</div>
      <div class="form-wrap">
        <el-row :gutter="20">
          <el-col :span="12" :offset="0">
            <el-input
              v-model="score"
              placeholder="Place Enter Score"
              size="normal"
              clearable
            ></el-input>
          </el-col>
        </el-row>
        <el-input
          type="textarea"
          :rows="6"
          placeholder="Place Enter"
          v-model="textarea"
          style="margin-top: 20px"
        >
        </el-input>
        <el-row style="margin-top: 20px">
          <el-col :span="2" :push="21">
            <el-button type="primary" size="default" @click="submitFn">submit</el-button>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      score: "",
      textarea: "",
    };
  },
  methods: {
    goBack() {
      console.log("go back");
      this.$router.go(-1);
    },
    submitFn() {
      this.$notify({
        title: "success",
        message: "submit successfully",
        type: "success",
      });
    },
  },
};
</script>

<style>
.studen-detail {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}

.studen-detail .studen-detail-top {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.studen-detail .studen-detail-top .article-inner,
.studen-detail .studen-detail-top .result {
  width: 48%;
  border-radius: 4px;
  min-height: 400px;
  background: #eee;
}

.studen-detail .studen-detail-top .result {
  padding: 10px;
}

.studen-detail .studen-detail-top .result .result-inner {
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 50px;
  box-sizing: border-box;
  padding: 20px;
}

.studen-detail .studen-detail-top .result .result-inner .circle {
  width: 150px;
  height: 150px;
  line-height: 150px;
  border-radius: 50%;
  background: #f6f8fc;
  text-align: center;
  font-size: 26px;
  color: #295ee9;
  font-weight: bold;
}
.studen-detail .studen-detail-top .result .result-inner .items {
  font-size: 17px;
  color: #333;
}

.studen-detail .studen-detail-top .result .result-inner .items p {
  margin: 0;
}

.studen-detail .studen-detail-top .result .result-inner .items .num {
  font-size: 24px;
  font-weight: bold;
  margin: 0 4px;
}

.studen-detail .studen-detail-bottom {
  border: 1px solid #eee;
  margin-top: 20px;
  min-height: 300px;
  padding: 10px;
}

.form-wrap {
  margin-top: 20px;
}
</style>
