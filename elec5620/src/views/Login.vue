<template>
  <div class="trueson">
    <el-container>
      <el-aside width="800px">
    <div class="logo">
<el-image class="login-pic" :src="require('../../src/img/logo.png')"></el-image>
    </div>
      </el-aside>
    <el-main>
  <div class="login-container">

    <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" label-width="100px" class="loginForm"  @keyup.enter.native="login">
  <!--      email-->
        <el-form-item label="Email" prop="email">
          <el-input v-model="loginForm.email"></el-input>
        </el-form-item>
  <!--      password-->
        <el-form-item label="Password" prop="password" >
          <el-input v-model="loginForm.password" type="password" show-password></el-input>
        </el-form-item>
  <!--      button-->
        <el-form-item>
            <el-button type="primary" @click="register">
              Sign in
            </el-button>
            <el-button type="primary" @click="login">
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
  data() {
    return {
      //data object
      loginForm:{
        email:"123@qq.com",
        password:"123456"
      },
      //rules
      loginRules:{
        //username verify
        email:[
          {required:true, message:'Please input your email', trigger:'blur'},
          {min:1, max:20, message:'length should be within 20', trigger:'blur'}
        ],
        //password verify
        password:[
          {required:true, message:'Please input your password', trigger:'blur'},
          {min:6, max:20, message:'length should be between 6 to 20', trigger:'blur'}
        ]
      }
    }
  },
  methods: {
    login(){
      console.log(this.loginForm.email);
      console.log(this.loginForm.password);
      var result;
      this.$refs.loginFormRef.validate(async valid =>{
        if( !valid) return;
        this.$axios.post('/login', {
          email: this.loginForm.email,
          password: this.loginForm.password,
        }).then(res => {
          console.log(res.status)
          if(res.data.status == "success") {
            console.log(res.data.role);
            localStorage.setItem('role', res.data.role);
            localStorage.setItem('ticket', res.data.ticket);
            localStorage.setItem('uid',res.data.id);
            if(res.data.role == 0) this.$router.push({path:'/studentHome'});
            if(res.data.role == 1) this.$router.push({path:'/teacherHome'});
            if(res.data.role == 2) this.$router.push({path:'/admin'});
          }else {
            this.$notify({
              title : 'message',
              message : res.data.status,
              type : 'error'
            });
          }
          }).catch(err => {
            console.log(err)
        })
      })
    },
    register() {
      this.$router.push('/register');
    }
  }
}
</script>

<style scoped>
.trueson {

}
.logo{
  margin-top: 200px;
  margin-left: 200px;
}

.login-container {
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
