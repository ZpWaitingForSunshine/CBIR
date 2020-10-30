<template></template>

<script>
export default {
  props: {},
  data() {
    return {
      upload1: "rest/xxx",
      upload2: "rest/xxx",
    };
  },
  created() {
    this.getLib();
  },
  methods: {
    submitform() {
      this.$refs.jar.submit();
      this.$refs.xml.submit();
    },
  },
};
</script>

<style scoped>
.col {
  color: black;
  font-weight: bold;
}
.left {
  padding: 20px;
}
</style>
