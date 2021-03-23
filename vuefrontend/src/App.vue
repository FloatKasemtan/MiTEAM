<template>
  <div id="app">
    <vue-extend-layouts />
  </div>
</template>

<script>
import VueExtendLayouts from 'vue-extend-layout'
export default {
  name: 'App',
  components: { VueExtendLayouts }
}
</script>
<style>
.header {
  font-size: 60px;
  color: #31517d;
}
</style>