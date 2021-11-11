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
          v-model="searchessays"
          placeholder="Search the course"
          style="width: 600px">
          <el-button
            slot="append"
            icon="el-icon-search">
          </el-button>
        </el-input>
      </el-header>
      <!--display essays-->
      <el-main></el-main>
    </el-tab-pane>

    <el-tab-pane label="4.View Feedback" name="fourth">
      <div class="feedback">
        <el-row style="margin-top: 30px" class="feedback">
          <el-col
            :span="5"
            v-for="item in feedback"
            :key="item.id"
            :offset="1"
            style="margin-top: 30px"
          >
            <div>
              <el-card :body-style="{ padding: '0px' }">
                <div slot="header" class="clearfix">
                  <span>{{ item.title }}</span>
                </div>
                <el-row>
                  <div style="padding: 14px">
                    <el-row
                    ><span>{{ item.feedback }}</span></el-row
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
          prop="userid"
          label="UserID"
          width="200"
        ></el-table-column>
        <el-table-column
          prop="username"
          label="Username"
          width="400"
        ></el-table-column>
        <el-table-column
          prop="email"
          label="email"
          width="400"
        ></el-table-column>
        <el-table-column
          prop="gender"
          label="Gender"
          width="200"
        ></el-table-column>
        <el-table-column
          prop="usertype"
          label="User Type"
          width="200"
        ></el-table-column>
        <el-table-column
          prop="status"
          label="Status"
          width="200"
        ></el-table-column>
        <el-table-column fixed="right" label="Operation">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="danger"
              @click="deleteUser(scope.$index)"
            >
              Delete
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
      searchessays:'',
      searchUser:'',
      adminname:'JohnWick',
      adminemail:'4399@qq.com',
      admingender:'Male',
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
          status:'normal',
        },
        {
          userid:'2',
          username:'杰尼龟',
          gender:'公',
          email:'1234',
          usertype:'学生',
          status:'forbidden',
        },
        {
          userid:'3',
          username:'妙蛙种子',
          gender:'公',
          email:'12345',
          usertype:'学生',
          status:'normal',
        },
      ],
      feedback:[
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        {
          id:'1',title:'shit',feedback:'As the most common language in the world, English has become a mandatory subject for students in many non-native English speaking countries. And in English teaching, there is a lot of effort given to writing assignments. As a teacher, it takes at least 15 minutes to review an essay carefully, and the task of reviewing a class is too heavy for the teacher. Therefore, considering the time allocated, teachers usually do not assign many essay exercises, and there is a risk that teachers will not be able to point out mistakes in detail or that it will not be possible to make all essay grades as objective and fair as possible. Therefore, in order to allow students to practice their writing skills as much as possible, and reduce the burden on teachers at the same time, we came up with the idea of creating a website that grades essays based on artificial intelligence. The essays uploaded by students can first be automatically graded by artificial intelligence to give teachers and students a reference. In this way, students can have a more intuitive cognition of their writing ability. And at the same time, it can also give teachers some reference opinions on teaching and guidance. Considering that most English learning software or websites in the market are focusing on reading and memorizing words, there are few automatic scoring projects for composition based on artificial intelligence. Teachers urgently need such an intelligent scoring system to improve the quality of teaching. Students also need such a system to improve their writing ability. Therefore, I think our project is feasible  and has broad market prospects.\n',
        },
        ],
    };
  },
  methods: {
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
