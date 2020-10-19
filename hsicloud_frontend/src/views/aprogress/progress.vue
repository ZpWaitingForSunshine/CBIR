<template>
  <div class="DAG-content">
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#545c64"
      active-text-color="#ffd04b"
    >
      <el-menu-item index="1" @click="selects(1)">运行进度</el-menu-item>
      <!-- <el-submenu index="2">
    <template slot="title" @click="selects(2)">算法简介</template>
    <el-menu-item index="2-1" @click="selects(2)">主成分分析(PCA)</el-menu-item>
    <el-menu-item index="2-2" @click="selects(3)">纯净像元指数(PPI)</el-menu-item>
    <el-menu-item index="2-3" @click="selects(4)">光谱特征匹配(SAD)</el-menu-item>
      </el-submenu>-->
    </el-menu>

    <h3 class="title">运行进度</h3>

    <el-button type="primary" class="butcontent" @click="result()">确定</el-button>
    <users ref="mychild"></users>
    <dagContent />
    <nodes-bus
      v-if="dragBus"
      :value="busValue.value"
      :pos_x="busValue.pos_x"
      :pos_y="busValue.pos_y"
    />
  </div>
</template>

<script>
import dagContent from "./dagContent";
import NodesBus from "./nodesBus";
import { mapActions } from "vuex";
import dagStore from "@/store/modules/dagStore.js";

import users from "./dagContent/index";

export default {
  data() {
    return {
      name: "123456798",
      excuteid: "",
      dagname: false,
      dragBus: false,
      //nodes : lastdag.ldag,
      selected: "",
      busValue: {
        value: "name",
        pos_x: 100,
        pos_y: 100
      }
    };
  },
  created() {
    this.excuteid = this.$route.query.eid;
    // console.log("&&"+this.excuteid)
  },
  methods: {
    ...mapActions(["addNode"]),
    dragIt(val) {
      sessionStorage["dragDes"] = JSON.stringify({
        drag: true,
        name: val
      });
      //  console.log(dagStore.state.DataAll);
    },
    test() {
      var t;
      var that = this;
      clearTimeout(t);
      t = setTimeout(function() {
        that.$refs.mychild.changebackground(that.excuteid);
      }, 1000);
    },
    result() {
      var that = this;
      that.$refs.mychild.rmTimer();
      //   console.log(dagStore.state.DataAll);
      console.log(this.excuteid);
      this.$router.push({
        path: "../../algorithms/ShowResult/",
        query: { eid: this.excuteid }
      });
    }
    ////////////////////////////////////////////////
  },
  mounted() {
    this.test();
  },
  //动画
  components: {
    dagContent,
    NodesBus,
    users
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style  scoped>
.DAG-content {
  position: absolute;
  left: 0px;
  top: 0;
  bottom: 0;
  right: 0;
}
.butcontent {
  position: absolute;
  left: 1200px;
  top: 50px;
}
.title {
  font-size: 35px;
  color: #008cba;
}
</style>
