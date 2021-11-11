<template>
  <div class="upcoming-essay">
    <el-page-header @back="goBack" title="Back"></el-page-header>
    <div class="title mt20">Essay Detail</div>
    <div class="mt20">
      <el-table :data="essayData" style="width: 100%">
        <el-table-column width="120px" label="Homework"><template slot-scope="scope"><span>{{scope.row.title}}</span></template></el-table-column>
        <el-table-column width="400px" label="Content"><template slot-scope="scope"><span>{{scope.row.content}}</span></template></el-table-column>
        <el-table-column label="Release Date" prop="releaseDate"><template slot-scope="scope"><span>{{scope.row.releasetime}}</span></template></el-table-column>
        <el-table-column  label="End Date" prop="endDate" ><template slot-scope="scope"><span>{{scope.row.endtime}}</span></template></el-table-column>
        <el-table-column>
          <template slot-scope="scope">
            <el-button type="text" @click="toSubmit(scope.$index, scope.row)">submit</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id:'',
      ticket:'',
      essayData: [
        {
          releaseDate: "2016-05-03",
          endDate: "2016-05-03",
          homework: "homework 4",
          content: "please write a 500-word essay",
        },
      ],
    };
  },
  methods: {
    goBack() {
      console.log("go back");
      this.$router.go(-1);
    },
    toSubmit() {
      this.$router.push({
        path: "submitEssay",
      });
    },
    getessay(){
    this.id = localStorage.getItem('uid');
      console.log(this.id);
      this.$axios.get('/task/student/?sid='+this.id+'&&status=0')
        .then(res=>{
          this.essayData = res.data.data;
        })
        .catch(function (error) {
          console.log(error);
        })
    },
  },

  mounted(){

    this.getessay()
  }
}
</script>

<style>
.upcoming-essay {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}
</style>
