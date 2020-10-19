<template>
  <div ref="gantt"></div>
</template>

<script>
import gantt from "dhtmlx-gantt"; // 引入模块
import "dhtmlx-gantt/codebase/dhtmlxgantt.css";
export default {
  name: "gantt",
  props: {
    tasks: {
      type: Object,
      default() {
        return { data: [], links: [] };
      }
    }
  },

  mounted: function() {
    gantt.init(this.$refs.gantt);
    gantt.parse(this.$props.tasks);
  }
};
</script>
