<template>
  <div id="app">
    <tableDisplay></tableDisplay>

  </div>
</template>

<script>
import tableDisplay from './components/tableDisplay.vue';

export default {
  name: 'App',
  components: {
     tableDisplay
   
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
