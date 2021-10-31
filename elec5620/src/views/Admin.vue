<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="1.Personal Information" name="first">个人信息
      <div class="information">
        <el-form :model="Form" class="demo-information">
          <el-form-item>

          </el-form-item>
        </el-form>
      </div>
    </el-tab-pane>

    <el-tab-pane label="2.Generate new teacher account" name="second">Register for a teacher
      <div class="teacher">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="Email" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Name" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password1">
          <el-input v-model="ruleForm.password1"></el-input>
        </el-form-item>
        <el-form-item label="Gender" prop="gender">
          <el-radio v-model="ruleForm.gender" label="male">male</el-radio>
          <el-radio v-model="ruleForm.gender" label="female">female</el-radio>
        </el-form-item>
        <el-button style="width:100px"type="primary">Submit</el-button>
      </el-form></div>
    </el-tab-pane>

    <el-tab-pane label="3.Manage Essays" name="third">搜索作文</el-tab-pane>

    <el-tab-pane label="4.View Feedback" name="fourth">查看用户反馈</el-tab-pane>

    <el-tab-pane label="5.Manage User account" name="fifth">查看用户信息</el-tab-pane>
  </el-tabs>
</template>

<script>
export default {
  name: "Admin",
  data() {
    return {
      activeName: 'first',
      ruleForm: {
        name: '',
        code:'',
        email:'',
        gender:'',
        password1:'',
      },
      rules: {
        name: [
          {required: true, message: 'Please input your name', trigger: 'blur'},
          {min: 1, max: 10, message: 'Between 1 and 10 characters in length.', trigger: 'blur'}
        ],
        code:[
          {required: true, message: 'Please input your name', trigger: 'blur'},
          {min: 1, max: 10, message: 'Between 1 and 10 characters in length.', trigger: 'blur'}
        ],
        email: [
          {required: true, message: 'please input your email', trigger: 'blur'}
        ],
        password1:[
          {required: true, message: 'please input your email', trigger: 'blur'}
        ],
        gender:[
          {required: true, message: 'please input your email', trigger: 'blur'}
        ],

      },
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
}
</script>

<style scoped>
.teacher{
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
</style>
