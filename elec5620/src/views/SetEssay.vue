<template>
  <div class="setEssay">
    <el-page-header
      @back="goBack"
      title="Back"
      content="SetEssay"
    ></el-page-header>
    <div class="setEssay-top">
      <div class="title">SetEssay</div>
      <div class="form-wrap">
        <el-row :gutter="20">
          <el-col :span="12" :offset="0">
            <el-input
              v-model="score"
              placeholder="Place Enter Title"
              size="normal"
              clearable
            >
              <template slot="prepend">Title</template>
            </el-input>
          </el-col>
        </el-row>
        <el-row style="margin-top:20px">
          <el-col :span="12" :offset="0">
            <el-date-picker
              v-model="date"
              type="daterange"
              range-separator="to"
              start-placeholder="Start date"
              end-placeholder="End date"
              value-format="yyyy-MM-dd"
            >
            </el-date-picker>
          </el-col>
        </el-row>
        <el-input
          type="textarea"
          :rows="9"
          placeholder="Place Enter"
          v-model="textarea"
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
      date: ""
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
.setEssay {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}

.setEssay .setEssay-top {
  border: 1px solid #eee;
  margin-top: 20px;
  min-height: 300px;
  padding: 10px;
}

.form-wrap {
  margin-top: 20px;
}

.form-wrap .el-input-group__prepend {
  background: #3295fb;
  color: #eee;
}
</style>
