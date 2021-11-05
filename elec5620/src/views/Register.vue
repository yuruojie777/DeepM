<template>
  <div class="regis">
   Register
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="Email" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>
      <el-form-item label="Veification Code">
        <el-button type="success">send</el-button>
      </el-form-item>
      <el-form-item label="Code" prop="code">
        <el-input v-model="ruleForm.code"></el-input>
      </el-form-item>
      <el-form-item label="Name" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password1">
        <el-input v-model="ruleForm.password1"></el-input>
      </el-form-item>
      <el-form-item label="Confirm password" prop="password2">
        <el-input v-model="ruleForm.password2"></el-input>
      </el-form-item>
      <el-form-item label="Gender" prop="gender">
        <el-radio v-model="ruleForm.gender" label="male">male</el-radio>
        <el-radio v-model="ruleForm.gender" label="female">female</el-radio>
      </el-form-item>
      <el-button style="width:100px" @click="gologin" type="primary">Login</el-button>
      <el-button style="width:100px" @click="submit"  type="primary">Submit</el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      ruleForm: {
        name: '',
        code:'',
        email:'',
        gender:'',
        password1:'',
        password2:'',
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
          {required: true, message: 'please input your email', trigger: 'blur'},
          {
            type: 'email',
            message: 'Please input the right email adress',
            trigger: ['blur', 'change'],
          },
        ],
        password1:[
          {required: true, message: 'please input your email', trigger: 'blur'}
        ],
        password2:[
          {required: true, message: 'please input your email', trigger: 'blur'}
        ],
        gender:[
          {required: true, message: 'please input your email', trigger: 'blur'}
        ],

      }
    }
  },

  methods:{

    gologin(){
      this.$router.push('/login')
    },
    submit(){
      this.$refs.ruleForm.validate((valid)=> {
        if(valid) {
          if(this.ruleForm.password1==this.ruleForm.password2) {
            this.$alert('You have successfully  registered.', 'Welcome', {
              confirmButtonText: 'Confirm',
            });
            this.$router.push('/login')
          }
          else{this.$alert('Two password does not match！')}
        }
      })
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
