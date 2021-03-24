<template>
  <h1 class="header">{{ pageName }}</h1>
</template>

<script>
export default {
  props: {
    pageName: String,
  },
};
</script>

<style scoped>
.header {
  font-size: 60px;
  color: #31517d;
}
</style>