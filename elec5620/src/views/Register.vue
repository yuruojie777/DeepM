<template>
  <div class="regis">
    注册
    <div>
      <el-input placeholder="Account" label="Account" style="width:300px"></el-input>
    </div>
    <div>
      <el-input  label="Password" style="width:300px"></el-input>
    </div>
    <div>
      <el-input label="Password" style="width:300px"></el-input>
    </div>
    <div>
      <el-button style="width:100px" @click="gologin" type="primary">Login</el-button>
      <el-button style="width:100px" type="primary" @click="goregister">Submit</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  methods:{
    gologin(){
      this.$router.push('/login')
    },
  }
}
</script>

<style scoped>
.regis{
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
