<template>
  <router-view />
</template>

<script>

export default {
  name: 'App',
  components: {
  },
  mounted() {
     this.$store.dispatch("placeToken");
  }
}
</script>

<style>
#app {
}
</style>
