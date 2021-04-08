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
.dash-topic {
  font-size: 1.5em;
  font-weight: bold;
}
</style>