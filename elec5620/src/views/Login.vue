<template>

   <div>
     <el-container>
<el-main>
  <div class="logincon">
    Login
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="Email" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input v-model="ruleForm.password" show-password></el-input>
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
</el-main>
     </el-container>
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
      this.$refs.ruleForm.validate((valid)=>{
      if(valid){

      if(this.ruleForm.email === '4399@qq.com'&& this.ruleForm.password=== '4399')
        {
          this.$alert('You have successfully logged in.', 'Welcome', {
            confirmButtonText: 'Confirm',
          });
          // callback: action => {
          //        this.$message({
          //          type: 'info',
          //          message: `action: ${ action }`
          //        });
          //      }
          this.$router.push('/admin')
        }
      else {
       this.$alert('wrong password or email!')
      }
      }
      }
      )
      }
  },
  data(){
    return{
      abc:'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      ruleForm: {
        email: '',
        password:'',
      },
      rules: {
       email: [
          {required: true, message: 'Please input your name', trigger: 'blur'},
         {
           type: 'email',
           message: 'Please input the right email adress',
           trigger: ['blur', 'change'],
         },
        ],
        password:[
          {required: true, message: 'Please input your password', trigger: 'blur'}
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
  left:400px;
}
</style>
