<template>
  <div>
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#545c64"
      active-text-color="#ffd04b"
    >
      <el-menu-item index="1" @click="selects(1)">调度结果</el-menu-item>
      <!-- <el-submenu index="2">
    <template slot="title" @click="selects(2)">算法简介</template>
    <el-menu-item index="2-1" @click="selects(2)">主成分分析(PCA)</el-menu-item>
    <el-menu-item index="2-2" @click="selects(3)">纯净像元指数(PPI)</el-menu-item>
    <el-menu-item index="2-3" @click="selects(4)">光谱特征匹配(SAD)</el-menu-item>
      </el-submenu>-->
    </el-menu>

    <div v-if="selected==1">
      <h3 class="title">调度结果</h3>
      <div class="sectile">
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item name="1">
            <template slot="title">
              <el-button type="info" size="medium">
                调度结果
                <i class="el-icon-caret-bottom"></i>
              </el-button>
            </template>
            <el-progress type="circle" :percentage="percent"></el-progress>
            <div class="thirtilte">
              <a :href="url" target="_blank">调度结果(点击查看)</a>
            </div>
          </el-collapse-item>
          <el-collapse-item name="2">
            <template slot="title">
              <el-button type="info" size="medium">
                甘特图
                <i class="el-icon-picture"></i>
                <i class="el-icon-caret-bottom"></i>
              </el-button>
            </template>
            <div class="container">
              <gantt class="left-container" :tasks="tasks"></gantt>
            </div>
            <!-- <div class="thirtilte">
              <img v-if="ganturl" :src="ganturl" width="50%" height="600px" />
            </div>-->
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>

    <el-button class="button" type="primary" @click="gant()">查看甘特图</el-button>
    <el-button class="button" type="primary" @click="goback()">返回</el-button>
    <el-button class="button" type="primary" @click="compute()">确定</el-button>
  </div>
</template>

<script>
import { fetchDispatchurl, fetchgantt } from "@/api/components";
import errGif from "@/assets/401_images/401.gif";
import Gantt from "./Gantt.vue";
export default {
  name: "app",
  components: { Gantt },
  created() {
    this.excuteid = this.$route.query.eid;
    let params = {
      eid: this.excuteid
    };
    var that = this;
    //var n = 0;
    this.timer = setInterval(function() {
      if (that.percent < 100) {
        that.percent = that.percent + 1;
      } else if (that.percent == 100) {
        that.percent == 100;

        fetchDispatchurl(params)
          .then(response => {
            if (response.code == 200) {
              that.url = response.data;
            }
            clearInterval(that.timer); // 清除定时器
            that.timer = null;
          })
          .catch(error => {});
      }
    }, 200);
  },
  data() {
    return {
      tasks: {
        data: [
          {
            text: "Test001-001", //任务名
            start_date: "0", //开始时间
            id: 1, //任务id
            duration: 40, //任务时长，从start_date开始计算
            progress: 0.6, //任务完成情况，进度
            parent: 2, //父任务ID
            user: "李四", //成员
            planned_end: "0", //计划开始时间
            planned_start: "50", //计划结束时间
            show: false,
            open: true, //默认是否打开
            type: "project" // gantt.config.types.milestone为里程碑类型
            // project为项目任务类型，也就是摘要任务，
            // task为普通任务类型
          }
        ],

        links: []
      },

      timer: null,
      url: "",
      ganturl: "",
      excuteid: "",
      percent: 0,
      selected: 1,
      activeNames: ["1", "2"],
      activeIndex: "1",
      activeIndex2: "1",
      pcaData: [
        {
          name: "参数1",
          describe: "描述1",
          example: "示例1"
        },
        {
          name: "参数2",
          describe: "描述2",
          example: "示例2"
        },
        {
          name: "参数3",
          describe: "描述3",
          name: "参数4",
          describe: "描述4",
          example: "示例4"
        }
      ],
      pcaRes: [
        {
          name: "输出结果",
          expalin: "输出结果解释"
        }
      ],
      ppiData: [
        {
          name: "参数1",
          describe: "描述1",
          example: "示例1"
        },
        {
          name: "参数2",
          describe: "描述2",
          example: "示例2"
        },
        {
          name: "参数3",
          describe: "描述3",
          example: "示例3"
        },
        {
          name: "参数4",
          describe: "描述4",
          example: "示例4"
        }
      ],
      ppiRes: [
        {
          name: "输出结果",
          expalin: "输出结果解释"
        }
      ]
    };
  },

  methods: {
    gant() {
      let params = {
        eid: this.excuteid
      };
      this.tasks.data = [];
      //  console.log("eid" + this.ganturl);
      if (this.percent < 100) {
        alert("请等待调度完成");
      } else {
        fetchgantt(params)
          .then(response => {
            if (response.code == 200) {
              for (var i = 0; i < response.data.length; i++) {
                var box = {
                  text: "",
                  start_date: "", //开始时间
                  duration: 0
                };
                box.text = response.data[i].name;
                box.start_date = response.data[i].startTime;
                box.duration = parseInt(response.data[i].parallel);
                this.tasks.data.push(box);
                console.log("task" + this.tasks.data[i].start_date);
              }
            }
            gantt.parse(this.tasks);
          })
          .catch(error => {});
      }
    },

    handleChange(val) {
      console.log(val);
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    selects(a) {
      this.selected = a;
      //console.log("data:",this.data);
    },
    goback() {
      this.$router.push({
        path: "/algorithms/DAGResult",
        query: { eid: this.excuteid }
      });
    },
    compute() {
      if (this.percent == 100) {
        this.$router.push({
          path: "/algorithms/choosedata",
          query: { eid: this.excuteid }
        });
      } else if (this.percent <= 99) {
        alert("请等待调度完成");
      }
    }
  }
};
</script>
<style scoped>
.title {
  font-size: 35px;
  color: #008cba;
}
.sectile {
  font-size: 20px;
  /* background-color: black; */
  color: black;
  font-family: "微软雅黑";
  font-style: normal;
}
.thirtilte {
  font-size: 17px;
}
.button {
  background-color: #008cba; /* Green */
  border: none;
  color: white;
  padding: 10px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  margin: 4px 2px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
}
.left-container {
  height: 400px;
  margin-top: -25px;
}
</style>