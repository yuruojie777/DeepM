<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">


    <el-tab-pane label="1.Personal Information" name="first">
      <div class="information">
        <el-form :model="Form" class="demo-information">
          <el-form-item>
            Welcome administrator!
          </el-form-item>
          <el-form-item label="Name:" style="background: aquamarine">
            {{adminname}}
          </el-form-item>
          <el-form-item label="Email:" style="background: aquamarine">
            {{adminemail}}
          </el-form-item>
          <el-form-item label="Gender:" style="background: aquamarine">
            {{admingender}}
          </el-form-item>
        </el-form>
      </div>
    </el-tab-pane>

    <el-tab-pane label="2.Generate new teacher account" name="second">
      <div class="teacher">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item>
          Register for a teacher
        </el-form-item>
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
        <el-button @click="generate" style="width:100px"type="primary">Submit</el-button>
      </el-form></div>
    </el-tab-pane>

    <el-tab-pane label="3.Manage Essays" name="third">
      <!-- search essays-->
      <el-header style="margin: 0 auto">
        <el-input
          v-model="state"
          placeholder="Search the essay title"
          @keyup.enter.native="askcourse"
          style="width: 800px"
        ></el-input>
        <el-button icon="el-icon-search" type="primary" size="small" @click="askcourse"></el-button>
      </el-header>
      <!--display essays-->
      <el-main>
<div class="course result">

</div>
        <el-row style="margin-top: 30px" class="course result">
          <el-col
            :span="5"
            v-for="item in refire"
            :key="item.fid"
            :offset="1"
            style="margin-top: 30px"
          >
            <div>
              <el-card :body-style="{ padding: '0px' }">
                <div slot="header" class="clearfix2">
                  <el-button type="text" @click="goessay(item.essayId)">View details</el-button>
                </div>
                <el-row>
                  <span>{{item.essayId}}</span>
                  <div style="padding: 14px">
                    <el-row
                    ><span>{{item.title}}</span></el-row
                    >
                  </div>
                </el-row>

              </el-card>
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-tab-pane>

    <el-tab-pane label="4.View Feedback" name="fourth">
      <div class="feedback">
        <el-row style="margin-top: 30px" class="feedback">
          <el-col
            :span="5"
            v-for="item in feedback"
            :key="item.fid"
            :offset="1"
            style="margin-top: 30px"
          >
            <div>
              <el-card :body-style="{ padding: '0px' }">
                <div slot="header" class="clearfix">
                  <span>{{item.fid}}</span>
                </div>
                <el-row>
                  <div style="padding: 14px">
                    <el-row
                    ><span>{{item.content}}</span></el-row
                    >
                  </div>
                </el-row>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-tab-pane>

    <el-tab-pane label="5.Manage User account" name="fifth">
      <div style="display: flex">
        <el-input
          v-model="searchUser"
          placeholder="Username"
          style="width: 500px"
        >
        </el-input>
        <el-button
          class="el-icon-search"
          size="small"
          @click="getAllUser(1)"
        ></el-button>
      </div>

      <el-table
        :data="userData"
        style="width: 100%; margin-bottom: 20px"
        border
      >
        <el-table-column
          label="UserID"
          width="200"
        >
          <template slot-scope="scope"><span>{{scope.row.uid}}</span></template>
        </el-table-column>
        <el-table-column
          label="Username"
          width="400"
        >
          <template slot-scope="scope"><span>{{scope.row.name}}</span></template>
        </el-table-column>
        <el-table-column
          label="email"
          width="400"
        >
          <template slot-scope="scope"><span>{{scope.row.email}}</span></template>
        </el-table-column>
        <el-table-column

          label="Gender"
          width="200"
        >
          <template slot-scope="scope"><span>{{scope.row.gender}}</span></template>
        </el-table-column>
        <el-table-column

          label="User Type"
          width="200"
        >
          <template slot-scope="scope"><span>{{scope.row.role}}</span></template>
        </el-table-column>
        <el-table-column

          label="Status(0 means disabled)"
          width="200"
        >
          <template slot-scope="scope"><span>{{scope.row.status==1?'available':'banned'}}</span></template>
        </el-table-column>
        <el-table-column fixed="right" label="Operation">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="danger"
              @click="ban(scope.$index,scope.row)"
              style="width: 80px;"
            >
              Ban
            </el-button>
            <el-button
              size="small"
              type="success"
              @click="activate(scope.$index,scope.row)"
            >
              Activate
            </el-button>
          </template>
        </el-table-column>
      </el-table>


    </el-tab-pane>


  </el-tabs>
</template>

<script>
export default {
  name: "Admin",
  data() {
    return {
      restaurants: [],
      refire:[],
      state: '',
      timeout:  null,
      searchessays:'',
      searchUser:'',
      ticket:'',
      adminname:'',
      adminemail:'',
      admingender:'',
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
      userData:[
        {
        userid:'1',
        username:'小火龙',
        gender:'公',
        email:'123',
        usertype:'学生',
          status:'1',
        },
        {
          userid:'2',
          username:'杰尼龟',
          gender:'公',
          email:'1234',
          usertype:'学生',
          status:'1',
        },
        {
          userid:'3',
          username:'妙蛙种子',
          gender:'公',
          email:'12345',
          usertype:'学生',
          status:'1',
        },
      ],
      feedback:[

      ],
    };
  },
  methods: {
    goessay(id){
      console.log(id),
      this.$router.push({
        path: '/studentDetail',
        name: '/StudentDetail',
        params: {
          titleid:id,
        }
      })
    },
    askcourse(){
      this.$axios.get('/essay/search/?search='+ this.state)
        .then(res=>{
          this.refire=res.data.data
          console.log(this.refire)
        })
        .catch(function (error) {
          console.log(error);
        })
    },
    loadAll() {

    return[
    ]=this.refire
    },

    querySearchAsync(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 3000 * Math.random());
    },

    createStateFilter(queryString) {
      return (state) => {
        return (state.title.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },

    handleSelect(item) {
      console.log(item);
    },

    getinfo(){
     this.ticket = localStorage.getItem('ticket')
      console.log(this.ticket)
      this.$axios.get('/user/id/?ticket='+ this.ticket)
      .then(res=>{
        console.log(res);
        this.adminname=res.data.user.name
        this.adminemail=res.data.user.email
          if(res.data.user.gender==1){ this.admingender='male'}
          else{ this.admingender='female'}
      })
        .catch(function (error) {
          console.log(error);
        })
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    generate(){
      this.$refs.ruleForm.validate(async valid =>{
        if( !valid) return;
        this.$axios.post('/register', {
          email: this.ruleForm.email,
          password: this.ruleForm.password1,
          name: this.ruleForm.name,
          gender: this.ruleForm.gender,
          role: 1
        })
          .then(function (response) {
            console.log(response);
            window.alert("submit success!")
          })
          .catch(function (error) {
            console.log(error);
          });
      })
    },
    getfeedback(){
      this.$axios.get('/feedback')
      .then(res=>{
        this.feedback = res.data.data;


        console.log(this.feedback)


      })
        .catch(function (error) {
          console.log(error);
        })
    },
    getalluser(){
      this.$axios.get('/user/all')
      .then(res=>{
        this.userData=res.data.data
      })
        .catch(function (error) {
          console.log(error);
        })
    },
    activate(index,row){
      console.log(row.uid)
      this.$axios.put('/user/status/?id='+row.uid+'&&status=1')
        .then(res=>{
          this.getalluser()
        })
        .catch(function (error) {
          console.log(error);
        })
    },
    ban(index,row){
      console.log(row.uid)
     this.$axios.put('/user/status/?id='+row.uid+'&&status=0')
        .then(res=>{
          this.getalluser()
        })
        .catch(function (error) {
          console.log(error);
        })
    },
  },


  mounted(){
   this.getinfo()
    this.getalluser()
    this.getfeedback()
  this.restaurants = this.loadAll();
  }
}
</script>

<style scoped>
.information{
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
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
.feedback{
right:50px
}
</style>
