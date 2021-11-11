<template>
  <div class="essay-detail">
    <el-page-header
      @back="goBack"
      title="Back"
    ></el-page-header>
    <div class="title mt20">Submit Essay</div>
    <div class="form-wrap">
      <el-row :gutter="20">
        <el-col :span="12" :offset="0">
          <el-input
            v-model="title"
            placeholder="Place Enter Title"
            size="normal"
            clearable
          >
            <template slot="prepend">Title</template>
          </el-input>
        </el-col>
      </el-row>
      <el-input
        type="textarea"
        placeholder="Place Enter"
        v-model="content"
        style="margin-top: 20px"
      >
      </el-input>
      <el-row :gutter="20" style="margin-top: 20px">
        <el-col :span="2" :push="21">
          <el-button type="primary" size="default" @click="submitFn">submit</el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title:"",
      content:"",

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
      this.$axios.post('/essay',
      {
        title: this.title,
        content: this.content,
        sid: this.id,

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
.essay-detail {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}
</style>
