<template>
  <div
    class="page-content"
    @mousedown="startNodesBus($event)"
    @mousemove="moveNodesBus($event)"
    @mouseup="endNodesBus($event)"
  >
    <div class="tapBar"></div>
    <div class="mainContent">
      <div class="nav">
        <div class="nodes_bus">
          <p>可选算法列表:</p>
          <el-select
            v-model="arithmeticID"
            style="width:220px;margin:auto 6px auto 5px;"
            placeholder="算法库"
          >
            <el-option
              v-for="item in arithmeticID_list"
              :key="item"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          <span v-if="arithmeticID==1" @mousedown="dragIt('PCA')">PCA</span>
          <span v-if="arithmeticID==3" @mousedown="dragIt('SAD')">SAD</span>
          <span v-if="arithmeticID==2" @mousedown="dragIt('PPI')">PPI</span>
          <span v-if="arithmeticID==4" @mousedown="dragIt('KMeans')">KMeans</span>
          <span v-if="arithmeticID==2" @mousedown="dragIt('NFindr')">NFindr</span>
          <p>已有组件列表:</p>
          <el-select
            v-model="selected"
            @change="getValue"
            style="width:220px;margin:auto 6px auto 5px;"
            placeholder="组件库"
          >
            <el-option v-for="item in list" :key="item" :label="item.value" :value="item"></el-option>
          </el-select>

          <button class="button" type="button" @click="dagnamepara()">保存</button>
          <button class="button" type="button" @click="clear()">清空</button>
          <button class="button" type="button" @click="next()">下一步</button>

          <div class="title" v-if="this.dagname === true">
            <p style="display:inline-block;font-size:20px">
              输入名称:
              <el-input
                placeholder="请输入名称"
                v-model="namepara"
                style="width:220px; :auto 6px auto 5px;"
                clearable
              ></el-input>
            </p>
            <button class="button" @click="save()">确定</button>
            <button class="button" @click="cancle()">取消</button>
          </div>
        </div>
      </div>
      <div class="DAG-content">
        <dagContent />
      </div>
    </div>
    <nodes-bus
      v-if="dragBus"
      :value="busValue.value"
      :pos_x="busValue.pos_x"
      :pos_y="busValue.pos_y"
    />

    <div id="app">
      <!-- <select v-model="selected" @change='getValue'>
<option v-for="(item) in list" v-bind:key="item.value">{{item}}</option>
      </select>-->
    </div>
  </div>
</template>

<script>
import dagContent from "@/components/STEP/dagContent";
import NodesBus from "./nodesBus";
import { mapActions } from "vuex";

import dagStore from "@/store/modules/dagStore.js";
import {
  fetchdag,
  fetchList,
  getdaginfo,
  savedaginfo,
  getConponentName
} from "@/api/components";

////////////////////////////////////////////
//import lastdag from "./lastdagCon.vue"
//import dialogBar from './dialog.vue'

export default {
  created() {
    this.getInfo();
    if (this.$route.query.eid != null) {
      this.excuteid = this.$route.query.eid;
    }
  },
  data() {
    return {
      excuteid: "",
      ob: "[object Object]",
      arithmeticID: "",
      arithmeticID_list: [
        {
          value: "1",
          label: "降维算法"
        },
        {
          value: "2",
          label: "端元提取"
        },
        {
          value: "3",
          label: "光谱匹配"
        },
        {
          value: "4",
          label: "分类算法"
        }
      ],
      dagname: false,
      namepara: "",
      n: 0,
      num: 0,
      arr: [],
      list: [], //get list
      list2: [],
      nodes1: [], //get nodes1
      nodes2: [],
      edges1: [], //get edges1
      edges2: [],
      dag_json: [],
      dag_res: [],
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
  methods: {
    ...mapActions(["addNode"]),
    dragIt(val) {
      sessionStorage["dragDes"] = JSON.stringify({
        drag: true,
        name: val
      });
    },
    nodeege() {
      for (var i = 0; i < this.nodes1.length; i++) {
        if (this.nodes1[i].listname == this.selected) {
          this.nodes2 = this.nodes2.concat(this.nodes1[i]).slice(0);
        }
      }
      for (var i = 0; i < this.edges1.length; i++) {
        if (this.edges1[i].listname == this.selected) {
          this.edges2 = this.edges2.concat(this.edges1[i]).slice(0);
        }
      }

      dagStore.state.DataAll.nodes = this.nodes2.slice(0);
      dagStore.state.DataAll.edges = this.edges2.slice(0);
      this.nodes1 = [];
      this.edges1 = [];
      this.nodes2 = [];
      this.edges2 = [];
    },

    getInfo() {
      getConponentName()
        .then(response => {
          if (response.code === 200) {
            var componentsList = response.data;
            for (var i = 0; i < componentsList.length; i++) {
              this.list = this.list.concat(componentsList[i].name);
              //this.list[i] = componentsList[i].name
              console.log(componentsList[i].name);
            }
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    startNodesBus(e) {
      /**
       *  别的组件调用时, 先放入缓存
       * dragDes: {
       *    drag: true,
       *    name: 组件名称
       *    type: 组件类型
       *    model_id: 跟后台交互使用
       * }
       **/
      let dragDes = null;
      if (sessionStorage["dragDes"]) {
        dragDes = JSON.parse(sessionStorage["dragDes"]);
      }
      if (dragDes && dragDes.drag) {
        const x = e.pageX;
        const y = e.pageY;
        this.busValue = Object.assign({}, this.busValue, {
          pos_x: x,
          pos_y: y,
          value: dragDes.name
        });
        this.dragBus = true;
      }
    },
    moveNodesBus(e) {
      if (this.dragBus) {
        const x = e.pageX;
        const y = e.pageY;
        this.busValue = Object.assign({}, this.busValue, {
          pos_x: x,
          pos_y: y
        });
      }
    },
    endNodesBus(e) {
      let dragDes = null;
      if (sessionStorage["dragDes"]) {
        dragDes = JSON.parse(sessionStorage["dragDes"]);
      }
      if (dragDes && dragDes.drag && e.toElement.id === "svgContent") {
        const { model_id, type } = dragDes;
        const pos_x =
          (e.offsetX - 90 - (sessionStorage["svg_left"] || 0)) /
          (sessionStorage["svgScale"] || 1); // 参数修正
        const pos_y =
          (e.offsetY - 15 - (sessionStorage["svg_top"] || 0)) /
          (sessionStorage["svgScale"] || 1); // 参数修正
        const params = {
          model_id: sessionStorage["newGraph"],
          desp: {
            type,
            pos_x,
            pos_y,
            name: this.busValue.value
          }
        };
        this.addNode(params);
      }
      window.sessionStorage["dragDes"] = null;
      this.dragBus = false;
    },
    next() {
      //   console.log(dagStore.state.DataAll);
      //   console.log("--------------------");
      //   console.log(this.nodes1);

      //  console.log("--------------------");
      //  console.log(this.nodes1);
      //         console.log("--------------------");
      //  console.log(this.list);

      //this.$router.push({path:'/aprogress/progress',query:{method:dagStore.state.DataAll}})
      let params = {
        list: JSON.stringify(dagStore.state.DataAll)
      };

      fetchdag(params)
        .then(response => {
          if (response.code == 200) {
            this.excuteid = response.data;

            this.$router.push({
              path: "/algorithms/DAGResult",
              query: { eid: this.excuteid }
            });
          }
        })
        .catch(error => {});
    },
    save() {
      //
      if (this.namepara == "") {
        alert("不能为空！");
      } else {
        for (var i = 0; i < dagStore.state.DataAll.nodes.length; i++) {
          dagStore.state.DataAll.nodes[i].listname = this.namepara;
        }
        for (var i = 0; i < dagStore.state.DataAll.edges.length; i++) {
          dagStore.state.DataAll.edges[i].listname = this.namepara;
        }
        this.nodes1 = this.nodes1.concat(dagStore.state.DataAll.nodes);
        this.edges1 = this.edges1.concat(dagStore.state.DataAll.edges); //post this.edges1
        this.list = this.list.concat(this.namepara);
        this.n = this.n + 1;

        alert("success");
        dagStore.state.DataAll.nodes = [];
        dagStore.state.DataAll.edges = [];
        //去除重复nodes元素
        var judegement = 0;
        for (var i = 0; i < this.nodes1.length; i++) {
          for (var j = 0; j < this.arr.length; j++) {
            if (this.arr[j] == this.nodes1[i]) judegement = 1;
            if (judegement == 1) break;
          }
          if (judegement == 0) {
            this.arr = this.arr.concat(this.nodes1[i]);
          } else judegement = 0;
        }
        this.nodes1 = this.arr.slice(0); //post this.nodes1
        this.arr = [];

        //去除list中被覆盖的内容
        judegement = 0;
        for (var i = 0; i < this.list.length; i++) {
          if (judegement == 0) {
            this.list2 = [];
            judegement = 1;
            i = i - 1;
          } else {
            for (var j = 0; j < this.nodes1.length; j++) {
              if (this.list[i] == this.nodes1[j].listname) {
                this.list2 = this.list2.concat(this.list[i]);
                break;
              }
            }
          }
        }
        this.list = [];
        this.list = this.list.concat(this.list2.slice(0)); //post this.list
        this.dagname = false;
        this.dag_json = this.dag_json.concat(this.nodes1);
        this.dag_json = this.dag_json.concat(this.edges1);
        this.dag_json = this.dag_json.concat(this.namepara);
        this.namepara = "";
        let params = {
          info: JSON.stringify(this.dag_json)
        };
        savedaginfo(params)
          .then(response => {})
          .catch(error => {
            console.log(error);
          });
      }
    },

    getValue() {
      //
      var that = this;
      this.$confirm("是否选用此组件", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let params = {
            cname: JSON.stringify(this.selected)
          };
          getdaginfo(params)
            .then(response => {
              // console.log(response.data[0].dag_json);
              // var b = response.data[0].dag_json.substring(1);
              // var a = JSON.parse(b);
              this.dag_res = response.data[0].dag_json.split("}");
              for (var i = 0; i < this.dag_res.length; i++) {
                var tmp = this.dag_res[i];

                if (tmp[2] == "p") {
                  tmp = tmp + "}";
                  //tmp = tmp.substring(1);

                  var a = JSON.parse(tmp);
                  this.nodes1 = this.nodes1.concat(a);
                } //node
                else if (tmp[2] == "s") {
                  //edge

                  tmp = tmp + "}";
                  var b = JSON.parse(tmp);

                  this.edges1 = this.edges1.concat(b);
                }
              }
              // console.log(a);
            })
            .catch(error => {
              console.log(error);
            });

          ///////////////////////////////
          var t;
          var that = this;
          clearTimeout(t);
          t = setTimeout(function() {
            that.nodeege();
          }, 1000);

          this.$message({
            type: "success",
            message: "成功"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消"
          });
        });
    },

    //////////////////////////////////////
    dagnamepara() {
      this.dagname = true;
      console.log("nodes1中存储的内容为：" + this.nodes1);
      console.log("nodes1中存储的内容为：" + this.edges1);
    },
    cancle() {
      this.dagname = false;
    },
    clear() {
      dagStore.state.DataAll.nodes = [];
      dagStore.state.DataAll.edges = [];
    }
    ////////////////////////////////////////////////
  },
  mounted() {},
  components: {
    dagContent,
    NodesBus
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.nodes_bus .el-select {
  background-color: #212528 !important;
}
.page-content {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  overflow: hidden;
}
.tapBar {
  width: 100%;
  height: 60px;
  background-color: #212528;
  display: flex;
  padding-top: 10px;
  padding-left: 300px;
}

.tapBar .title {
  color: white;
  font-weight: bold;
}

.tapEach {
  height: 50px;
  line-height: 50px;
  text-align: center;
  width: 100px;
  color: #ffffff;
  cursor: pointer;
}
.tapEachSel {
  height: 50px;
  line-height: 50px;
  text-align: center;
  width: 100px;
  color: #212528;
  background: #cccccc;
  cursor: pointer;
  box-sizing: border-box;
}
.mainContent {
  width: 100%;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  text-align: left;
}
.mainContent #helpContent {
  margin-top: 100px;
  text-align: left;
  text-indent: 20px;
  line-height: 20px;
}
.mainContent .nav {
  width: 300px;
  background: #212528;
  position: absolute;
  left: 0;
  bottom: 0;
  top: 0;
}
.mainContent .nav div {
  color: #ffffff;
  height: 50px;
  line-height: 50px;
  width: 100%;
  text-align: center;
}
.DAG-content {
  position: absolute;
  left: 300px;
  top: 0;
  bottom: 0;
  right: 0;
}
.nodes_bus {
  user-select: none;
  text-align: center;
}
.nodes_bus span {
  display: block;
  border: 1px white solid;
  height: 50px;
  width: 200px;
  margin-left: 50%;
  transform: translateX(-50%);
  margin-bottom: 30px;
  cursor: move;
  border-radius: 50px;
}
.dname {
  position: absolute;
  left: 300px;
  top: 0px;
}
.button {
  background-color: grey; /* Green */
  border: none;
  color: white;
  padding: 10px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  margin: 10px 5px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
  /* position: relative;left:1300px;top:500px */
}
</style>
