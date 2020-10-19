<template>
  <div class="mixin-components-container" v-if="detail" v-loading="listLoading">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>组件算法-{{detail.name}}</span>
        </div>
        <div style="margin-bottom:5px;">{{detail.summary}}</div>
      </el-card>
    </el-row>

    <el-row :gutter="20" style="margin-top:25px;">
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>小组件参数输入</span>
          </div>
          <div class="component-item" style="min-height:340px;" v-for="(item) in input">
            <h3>{{item.name}}</h3>
            <el-form :model="input[item.index]">
              <el-form-item prop="title">
                <md-input
                  v-model="input[item.index].input[index].value"
                  name="title"
                  :placeholder="item1.name"
                  v-for="(item1,index) in item.input"
                >{{item1.name}}</md-input>
              </el-form-item>
            </el-form>
          </div>

          <el-button type="primary" size="small" icon="el-icon-edit" @click="executor()">EXECUTE</el-button>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>结果展示</span>
          </div>

          <div v-if="name === '大米检测'" class="component-item" style="min-height:340px;">
            <el-col :span="12" class="text-center">
              <h4>原图</h4>
              <img v-if="output" :src="output.original" width="400" />
            </el-col>
            <el-col :span="12" class="text-center" width="100">
              <h4>结果图</h4>
              <img v-if="output" :src="output.result" width="400" />
            </el-col>
            <!-- <dropdown-menu :items="articleList" style="margin:0 auto;" title="系列文章" /> -->
          </div>

          <div v-if="name === '纯净像元指数算法'" class="component-item" style="min-height:340px;">
            <el-col :span="12" class="text-center" width="100">
              <h4>结果图</h4>
              <img v-if="output" :src="output.result" width="400" />
            </el-col>
            <el-col :span="12" class="text-center">
              <h4>端元位置</h4>
              <img v-if="output" :src="output.original" width="400" />
            </el-col>
          </div>
          <div v-if="name === '主成分分析法'" class="component-item" style="min-height:340px;">
            <el-col :span="12" class="text-center">
              <img v-if="output" :src="output.original" width="400" />
            </el-col>
            <el-col :span="12" class="text-center" width="100">
              <h4>降维后文件</h4>
              <img v-if="output" :src="output.result" width="400" />
            </el-col>
          </div>
          <div v-if="name === '光谱特征匹配算法'" class="component-item" style="min-height:340px;">
            <el-col :span="12" class="text-center" width="100">
              <h4>匹配结果</h4>
              <img v-if="output" :src="output.result" width="400" />
            </el-col>
          </div>
          <div v-if="name === 'K-Means聚类算法'" class="component-item" style="min-height:340px;">
            <el-col :span="12" class="text-center" width="100">
              <h4>聚类结果图</h4>
              <img v-if="output" :src="output.result" width="400" />
            </el-col>
          </div>
          <div v-if="name === 'N-Finder算法'" class="component-item" style="min-height:340px;">
            <el-col :span="12" class="text-center" width="100">
              <h4>端元位置</h4>
              <img v-if="output" :src="output.result" width="400" />
            </el-col>
          </div>
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
export default {
  name: "ComponentMixinDemo",
  components: {
    PanThumb,
    MdInput,
    Mallki,
    DropdownMenu
  },
  created() {
    const id = this.$route.params && this.$route.params.cid;
    this.getList(id);
  },
  methods: {
    getList(cid) {
      let params = { cid: cid };
      // this.listLoading = true
      fetchOne(params).then(response => {
        this.detail = response.data;
        this.name = response.data.name; // name
        var components = JSON.parse(response.data.input);
        var input = components.components;
        for (var i = 0; i < input.length; i++) {
          input[i].index = i;
        }
        this.input = input;
      });
    },
    // execute the rice detection
    executor() {
      let params = {
        params: JSON.stringify({
          name: this.name,
          components: this.input
        })
      };
      exec(params).then(response => {
        this.listLoading = true;
        this.result = response.data;
        var spark = JSON.parse(response.data.spark);
        this.result.spark = spark;
        if (response.data.spark.success === true) {
          var submissionId = spark.submissionId;
          this.timer = setInterval(() => {
            this.check(submissionId);
          }, 2000);
        } else {
          this.listLoading = false;
        }
      });
    },
    // check the spark cluster state
    check(submissionId) {
      let params = {
        params: submissionId
      };
      var this_ = this;
      fetchState(params).then(response => {
        var data = response.data;
        var driverState = data.driverState;
        if (driverState === "FINISHED") {
          console.log("finished");
          clearInterval(this.timer);
          this.listLoading = false;
          if (this.name === "大米检测") {
            console.log("dd");
            console.log(this.result);
            this.output.original = this.result.result;
            this.output.result = this.result.original;
            console.log(this.output);
          } else if (this.name === "纯净像元指数算法") {
            console.log(this.result);
            this.output.original = this.result.filename;
            this.output.result = this.result.result;
          } else if (this.name === "主成分分析法") {
            console.log(this.result);
            this.output.original = this.result.filename;
            this.output.result = this.result.result;
          } else if (this.name === "光谱特征匹配算法") {
            console.log(this.result);
            this.output.original = this.result.filename;
            this.output.result = this.result.result;
          } else if (this.name === "K-Means聚类算法") {
            console.log(this.result);
            this.output.original = this.result.filename;
            this.output.result = this.result.result;
          } else if (this.name === "N-Finder算法") {
            console.log(this.result);
            this.output.original = this.result.filename;
            this.output.result = this.result.result;
          }
        } else if (driverState === "FAILED") {
          clearInterval(this.timer);
          this.listLoading = false;
          console.log("failed");
        } else {
          console.log("running");
        }
      });
    }
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
