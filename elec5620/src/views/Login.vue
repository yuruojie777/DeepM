<template>
  <div class="logincon">
    Login
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="Email" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input v-model="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="goregister">
          Sign in
        </el-button>
        <el-button type="primary" @click="gologin">
          Log in
        </el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  methods:{
    goregister(){

      this.$router.push('/register')
      },
      gologin(){
      this.$alert('You have successfully logged in.', 'Welcome', {
        confirmButtonText: 'Confirm', });
  // callback: action => {
  //        this.$message({
  //          type: 'info',
  //          message: `action: ${ action }`
  //        });
 //      }

        this.$router.push('/admin')

      }
  },
  data(){
    return{
      ruleForm: {
        email: '',
        password:'',
      },
      rules: {
       email: [
          {required: true, message: 'Please input your name', trigger: 'blur'}
        ],
        password:[
          {required: true, message: 'Please input your name', trigger: 'blur'}
        ],
      }
    }
  }

}
</script>

<style scoped>
.logincon {
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
