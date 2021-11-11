<template>
  <div class="feedback">
    <el-page-header
      @back="goBack"
      title="Back"
      content="Feedback"
    ></el-page-header>
    <div class="feedback-top">
      <div class="title">Feedback</div>
      <div class="form-wrap">
        <el-input
          type="textarea"
          placeholder="Place Enter"
          v-model="feedback"
          style="margin-top: 20px"
        >
        </el-input>
        <el-row :gutter="20" style="margin-top: 20px">
          <el-col :span="2" :push="21">
            <el-button type="primary" size="default" @click="submitFn"
              >submit</el-button
            >
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
      feedback:'',
    };
  },
  methods: {
    goBack() {
      console.log("go back");
      this.$router.go(-1);
    },
    submitFn() {
      this.id = localStorage.getItem('uid');
      console.log(this.id);
      this.$axios.post('/feedback',
        {
          content: this.feedback,
          fromid: this.id,

        })
        .then(function (response){
          console.log(response);
          window.alert("submit success!")
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.feedback {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}

.feedback .feedback-top {
  border: 1px solid #eee;
  margin-top: 20px;
  min-height: 300px;
  padding: 10px;
}

.form-wrap {
  margin-top: 20px;
}
</style>
