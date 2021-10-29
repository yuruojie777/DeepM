<template>
  <div class="logincon">
    登录
    <div>
      <el-input placeholder="Account" label="Account" style="width:300px"></el-input>
    </div>
    <div>
      <el-input placeholder="Password" label="Password" style="width:300px"></el-input>
    </div>
    <div>


          <el-button style="width:100px" type="primary">Login</el-button>

          <el-button type="primary" @click="goregister">Register</el-button>


    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  methods:{
    goregister(){this.$router.push('/register')}
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
