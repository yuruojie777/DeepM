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
        <el-autocomplete
          v-model="state"
          :fetch-suggestions="querySearchAsync"
          placeholder="请输入内容"
          @select="handleSelect"
        ></el-autocomplete>
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
      refire:[
        { "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
        { "value": "Hot honey 首尔炸鸡（仙霞路）", "address": "上海市长宁区淞虹路661号" },
        { "value": "新旺角茶餐厅", "address": "上海市普陀区真北路988号创邑金沙谷6号楼113" },
        { "value": "泷千家(天山西路店)", "address": "天山西路438号" },
        { "value": "胖仙女纸杯蛋糕（上海凌空店）", "address": "上海市长宁区金钟路968号1幢18号楼一层商铺18-101" },
        { "value": "贡茶", "address": "上海市长宁区金钟路633号" },
        { "value": "豪大大香鸡排超级奶爸", "address": "上海市嘉定区曹安公路曹安路1685号" },
        { "value": "茶芝兰（奶茶，手抓饼）", "address": "上海市普陀区同普路1435号" },
        { "value": "十二泷町", "address": "上海市北翟路1444弄81号B幢-107" },
        { "value": "星移浓缩咖啡", "address": "上海市嘉定区新郁路817号" },
        { "value": "阿姨奶茶/豪大大", "address": "嘉定区曹安路1611号" },
        { "value": "新麦甜四季甜品炸鸡", "address": "嘉定区曹安公路2383弄55号" },
        { "value": "Monica摩托主题咖啡店", "address": "嘉定区江桥镇曹安公路2409号1F，2383弄62号1F" },
        { "value": "浮生若茶（凌空soho店）", "address": "上海长宁区金钟路968号9号楼地下一层" },
        { "value": "NONO JUICE  鲜榨果汁", "address": "上海市长宁区天山西路119号" },
        { "value": "CoCo都可(北新泾店）", "address": "上海市长宁区仙霞西路" },
        { "value": "快乐柠檬（神州智慧店）", "address": "上海市长宁区天山西路567号1层R117号店铺" },
        { "value": "Merci Paul cafe", "address": "上海市普陀区光复西路丹巴路28弄6号楼819" },
        { "value": "猫山王（西郊百联店）", "address": "上海市长宁区仙霞西路88号第一层G05-F01-1-306" },
        { "value": "枪会山", "address": "上海市普陀区棕榈路" },
        { "value": "纵食", "address": "元丰天山花园(东门) 双流路267号" },
        { "value": "钱记", "address": "上海市长宁区天山西路" },
        { "value": "壹杯加", "address": "上海市长宁区通协路" },
        { "value": "唦哇嘀咖", "address": "上海市长宁区新泾镇金钟路999号2幢（B幢）第01层第1-02A单元" },
        { "value": "爱茜茜里(西郊百联)", "address": "长宁区仙霞西路88号1305室" },
        { "value": "爱茜茜里(近铁广场)", "address": "上海市普陀区真北路818号近铁城市广场北区地下二楼N-B2-O2-C商铺" },
        { "value": "鲜果榨汁（金沙江路和美广店）", "address": "普陀区金沙江路2239号金沙和美广场B1-10-6" },
        { "value": "开心丽果（缤谷店）", "address": "上海市长宁区威宁路天山路341号" },
        { "value": "超级鸡车（丰庄路店）", "address": "上海市嘉定区丰庄路240号" },
        { "value": "妙生活果园（北新泾店）", "address": "长宁区新渔路144号" },
        { "value": "香宜度麻辣香锅", "address": "长宁区淞虹路148号" },
        { "value": "凡仔汉堡（老真北路店）", "address": "上海市普陀区老真北路160号" },
        { "value": "港式小铺", "address": "上海市长宁区金钟路968号15楼15-105室" },
        { "value": "蜀香源麻辣香锅（剑河路店）", "address": "剑河路443-1" },
        { "value": "北京饺子馆", "address": "长宁区北新泾街道天山西路490-1号" },
        { "value": "饭典*新简餐（凌空SOHO店）", "address": "上海市长宁区金钟路968号9号楼地下一层9-83室" },
        { "value": "焦耳·川式快餐（金钟路店）", "address": "上海市金钟路633号地下一层甲部" },
        { "value": "动力鸡车", "address": "长宁区仙霞西路299弄3号101B" },
        { "value": "浏阳蒸菜", "address": "天山西路430号" },
        { "value": "四海游龙（天山西路店）", "address": "上海市长宁区天山西路" },
        { "value": "樱花食堂（凌空店）", "address": "上海市长宁区金钟路968号15楼15-105室" },
        { "value": "壹分米客家传统调制米粉(天山店)", "address": "天山西路428号" },
        { "value": "福荣祥烧腊（平溪路店）", "address": "上海市长宁区协和路福泉路255弄57-73号" },
        { "value": "速记黄焖鸡米饭", "address": "上海市长宁区北新泾街道金钟路180号1层01号摊位" },
        { "value": "红辣椒麻辣烫", "address": "上海市长宁区天山西路492号" },
        { "value": "(小杨生煎)西郊百联餐厅", "address": "长宁区仙霞西路88号百联2楼" },
        { "value": "阳阳麻辣烫", "address": "天山西路389号" },
        { "value": "南拳妈妈龙虾盖浇饭", "address": "普陀区金沙江路1699号鑫乐惠美食广场A13" }],
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
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
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
