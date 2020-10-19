<template>
  <div class="mixin-components-container">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span style="font-size:30px;font-weight:bold;">运行结果</span>
        </div>
      </el-card>
    </el-row>

    <el-row :gutter="20" style="margin-top:25px;">
      <el-col>
        <el-card class="box-card">
          <!-- <div slot="header" class="clearfix">
            <span>结果展示</span>
          </div>-->
          <div v-for="(item,index) in this.results" :key="index" style="margin-top:20px">
            <h3>{{item.component}}</h3>
            <div v-if="item.component === 'PPI'" class="component-item" style="min-height:500px;">
              <el-col :span="12" class="text-center" width="100">
                <h4>端元位置图</h4>
                <img v-if="item" :src="item.pic" width="400" height="350" />
              </el-col>
              <el-col :span="12" class="text-center">
                <h4>其他结果</h4>
                <br />
                <span style="font-size:15px">ppi算法得到了光谱的纯净像元分布图和纯净像元的位置信息。</span>
                <br />
                <div style="heigth:30px;color:#00CCFF;">
                  <br />
                  <a
                    :href="item2"
                    target="_blank"
                    class="component"
                    v-for="(item2,index2) in item.results"
                    type="success"
                    :key="index2"
                  >
                    {{item2}}
                    <br />
                    <br />
                  </a>
                </div>
              </el-col>
            </div>
            <div v-if="item.component === 'PCA'" class="component-item" style="min-height:500px;">
              <el-col :span="12" class="text-center">
                <h4>特征值分布</h4>
                <img v-if="item" :src="item.pic" width="400" height="350" />
              </el-col>
              <el-col :span="12" class="text-center" width="100">
                <h4>其他结果</h4>
                <br />
                <span style="font-size:15px">pca算法结果包含了特征值的分布图，降维后的字节流文件以及对应的头文件。</span>
                <br />
                <div style="heigth:30px;color:#00CCFF;">
                  <br />

                  <a
                    :href="item2"
                    target="_blank"
                    class="component"
                    v-for="(item2,index2) in item.results"
                    type="success"
                    :key="index2"
                  >
                    {{item2}}
                    <br />
                    <br />
                  </a>
                </div>
              </el-col>
            </div>
            <div v-if="item.component === 'SAD'" class="component-item" style="min-height:500px;">
              <!-- <el-col :span="12" class="text-center" width="100">
                <h4>匹配结果</h4>
                <img v-if="item" :src="item.pic" width="400" />
              </el-col>-->
              <el-col :span="12" class="text-center" width="100">
                <h4>光谱匹配结果</h4>
                <br />
                <span style="font-size:15px">sad算法得到了光谱匹配的结果，将对应的光谱名称及光谱输出</span>
                <br />
                <div style="heigth:30px;color:#00CCFF;">
                  <br />
                  <a
                    :href="item2"
                    target="_blank"
                    class="component"
                    v-for="(item2,index2) in item.results"
                    type="success"
                    :key="index2"
                  >
                    {{item2}}
                    <br />
                    <br />
                  </a>
                </div>
              </el-col>
            </div>
            <div
              v-if="item.component === 'KMeans'"
              class="component-item"
              style="min-height:500px;"
            >
              <el-col :span="12" class="text-center" width="100">
                <h4>聚类结果图</h4>
                <img v-if="item" :src="item.pic" width="400" />
              </el-col>
              <el-col :span="12" class="text-center" width="100">
                <h4>其他结果</h4>
                <br />
                <span style="font-size:15px">pca算法结果包含了特征值的分布图，降维后的字节流文件以及对应的头文件。</span>
                <br />
                <div style="heigth:30px;color:#00CCFF;">
                  <br />
                  <a
                    :href="item2"
                    target="_blank"
                    class="component"
                    v-for="(item2,index2) in item.results"
                    type="success"
                    :key="index2"
                  >
                    {{item2}}
                    <br />
                    <br />
                  </a>
                </div>
              </el-col>
            </div>
          </div>
          <!-- <div v-if="name === '大米检测'" class="component-item" style="min-height:340px;">
            <el-col :span="12" class="text-center">
              <h4>原图</h4>
              <img v-if="output" :src="output.original" width="400" />
            </el-col>
            <el-col :span="12" class="text-center" width="100">
              <h4>结果图</h4>
              <img v-if="output" :src="output.result" width="400" />
            </el-col>
            <dropdown-menu :items="articleList" style="margin:0 auto;" title="系列文章" />
          </div>-->
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PanThumb from "@/components/PanThumb";
import MdInput from "@/components/MDinput";
import Mallki from "@/components/TextHoverEffect/Mallki";
import DropdownMenu from "@/components/Share/DropdownMenu";
import waves from "@/directive/waves/index.js"; // 水波纹指令
import { fetchOne, exec, fetchState } from "@/api/components";
import { fetchSparkState } from "@/api/spark";
import axios from "axios";
import { setInterval, clearInterval } from "timers";
import { dagcheck, fetchresulturl } from "@/api/components";
import dagStore from "@/store/modules/dagStore.js";
export default {
  name: "ComponentMixinDemo",
  components: {
    PanThumb,
    MdInput,
    Mallki,
    DropdownMenu
  },
  mounted() {
    let params = {
      eid: this.excuteid
    };
    fetchresulturl(params)
      .then(response => {
        if (response.code == 200) {
          this.url = response.data;
          var array = this.url.split(",");
          for (var i = 1; i < array.length - 1; i++) {
            var b = array[i].substring(4, array[i].length);

            console.log("bbbbb" + b);
            var a = array[i].substring(0, 3);
            console.log("aaaaa" + a);
            for (var j = 0; j < this.nodes.length; j++) {
              console.log("jjjjjj" + this.nodes[j].id);
              if (a == this.nodes[j].id) {
                console.log("jjjjjj" + this.nodes[j].name);
                if (this.nodes[j].name == "PCA") {
                  this.PCA[0].results.push(b);
                  console.log("this.PCA[0].result:" + b);
                  if (
                    b.indexOf("jpg") != -1 ||
                    b.indexOf("png") != -1 ||
                    b.indexOf("bmp") != -1
                  ) {
                    this.PCA[0].pic = b;
                    console.log("png+++++===++sssss");
                    console.log("png+++++===++sssss" + this.PCA[0]);
                  }
                }
                if (this.nodes[j].name == "PPI") {
                  this.PPI[0].results.push(b);
                  if (
                    b.indexOf("jpg") != -1 ||
                    b.indexOf("png") != -1 ||
                    b.indexOf("bmp") != -1
                  ) {
                    this.PPI[0].pic = b;
                  }
                }
                if (this.nodes[j].name == "SAD") {
                  this.SAD[0].results.push(b);
                  if (
                    b.indexOf("jpg") != -1 ||
                    b.indexOf("png") != -1 ||
                    b.indexOf("bmp") != -1
                  ) {
                    this.SAD[0].pic = b;
                  }
                }
                if (this.nodes[j].name == "KMeans") {
                  this.KMeans[0].results.push(b);
                  if (
                    b.indexOf("jpg") != -1 ||
                    b.indexOf("png") != -1 ||
                    b.indexOf("bmp") != -1
                  ) {
                    this.KMeans[0].pic = b;
                  }
                }
                if (this.nodes[j].name == "NFindr") {
                  this.NFindr[0].results.push(b);
                  if (
                    b.indexOf("jpg") != -1 ||
                    b.indexOf("png") != -1 ||
                    b.indexOf("bmp") != -1
                  ) {
                    this.NFindr[0].pic = b;
                  }
                }
              }
            }
          }
          console.log("I AM RESULT:" + this.PCA);
          console.log("I AM RESULT:" + this.PCA[0].results.length);
          if (this.PCA[0].results.length != 0) {
            this.results = this.results.concat(this.PCA);
            console.log("result add pca");
          }
          if (this.PPI[0].results.length != 0) {
            this.results = this.results.concat(this.PPI);
          }
          if (this.SAD[0].results.length != 0) {
            this.results = this.results.concat(this.SAD);
          }
          if (this.KMeans[0].results.length != 0) {
            this.results = this.results.concat(this.KMeans);
          }
          if (this.NFindr[0].results.length != 0) {
            this.results = this.results.concat(this.NFindr);
          }
        }
        console.log("I AM RESULT:" + this.results);
      })
      .catch(error => {});
  },
  created() {
    this.excuteid = this.$route.query.eid;
  },
  methods: {
    checkName() {
      console.log(this.nodes);
      for (var i = 0; i < this.nodes.length; i++) {
        if (this.nodes[i].name == "PCA") {
          this.results = this.results.concat(this.PCA);
        }
        if (this.nodes[i].name == "PPI") {
          this.results = this.results.concat(this.PPI);
        }
        if (this.nodes[i].name == "SAD") {
          this.results = this.results.concat(this.SAD);
        }
        if (this.nodes[i].name == "KMeans") {
          this.results = this.results.concat(this.KMeans);
        }
        if (this.nodes[i].name == "NFindr") {
          this.results = this.results.concat(this.NFindr);
        }
      }
    }

    // execute the rice detection
    // check the spark cluster state
  },
  directives: {
    waves
  },

  data() {
    const validate = (rule, value, callback) => {
      if (value.length !== 6) {
        callback(new Error("请输入六个字符"));
      } else {
        callback();
      }
    };

    return {
      url: "",
      excuteid: "",
      nodes: dagStore.state.DataAll.nodes,
      results: [],
      PCA: [
        {
          component: "PCA",
          results: [],
          pic: ""
        }
      ],
      PPI: [
        {
          component: "PPI",
          results: [],
          pic: ""
        }
      ],
      SAD: [
        {
          component: "SAD",
          results: [],
          pic: ""
        }
      ],
      KMeans: [
        {
          component: "KMeans",
          results: [],
          pic: ""
        }
      ],
      NFindr: [
        {
          component: "NFindr",
          results: [],
          pic: ""
        }
      ],

      demo: {
        title: ""
      },
      demoRules: {
        title: [{ required: true, trigger: "change", validator: validate }]
      },
      detail: null,
      input: null,
      name: null,
      result: null,
      listLoading: false,
      timer: null,
      output: {}
    };
  }
};
</script>

<style scoped>
.mixin-components-container {
  background-color: #f0f2f5;
  padding: 30px;
  min-height: calc(100vh - 84px);
}
.component-item {
  min-height: 100px;
}
</style>
