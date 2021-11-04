<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}


.title {
  position: relative;
  font-size: 24px;
  font-weight: bold;
  color: #333;
  padding-left: 10px;
  /* display: inline-block; */
  margin-bottom: 20px;
}

.title::before {
  content: "";
  position: absolute;
  left: 0;
  width: 3px;
  height: 100%;
  background: #3295fb;
}

.mt20{
  margin-top: 20px;
}
</style>

<!--*<img src="./assets/logo.png"-->
