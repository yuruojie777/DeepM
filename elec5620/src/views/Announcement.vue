<template>
  <div class="announcement">
    <el-page-header @back="goBack" title="Back"></el-page-header>
    <div class="title mt20">Announcement</div>
    <div class="mt20">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="Announcement" prop="homework"> </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      size: "",
      tableData: [
        {
          homework: "homework 1" + " has been graded. Your grade is " + "23" + ".",
        },
      ],
    };
  },
  methods: {
    goBack() {
      console.log("go back");
      this.$router.go(-1);
    },

  },
};
</script>

<style>
.announcement {
  padding: 20px 20px;
  text-align: left;
  margin: 0 10vw;
  background: #fafafa;
}
</style>
