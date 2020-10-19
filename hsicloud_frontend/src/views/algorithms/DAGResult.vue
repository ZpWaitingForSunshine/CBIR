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
      <el-menu-item index="1" @click="selects(1)">调度简介</el-menu-item>
      <!-- <el-submenu index="2">
    <template slot="title" @click="selects(2)">算法简介</template>
    <el-menu-item index="2-1" @click="selects(2)">主成分分析(PCA)</el-menu-item>
    <el-menu-item index="2-2" @click="selects(3)">纯净像元指数(PPI)</el-menu-item>
    <el-menu-item index="2-3" @click="selects(4)">光谱特征匹配(SAD)</el-menu-item>
      </el-submenu>-->
    </el-menu>

    <div v-if="selected==1">
      <h3 class="title">调度简介</h3>
      <div class="sectile">
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item name="1">
            <template slot="title">
              <el-button type="info" size="medium" class="label">
                免疫调度算法
                <i class="el-icon-caret-bottom"></i>
              </el-button>
            </template>
            <div
              class="thirtilte"
              style="text-indent:34px"
            >免疫调度算法是指基于免疫学原理和免疫系统机制设计的调度问题解决方法的总称。 免疫机理多种多样, 免疫调度算法并没有统一的框架。但目前绝大多数</div>
            <div
              class="thirtilte"
            >免疫调度算法都是基于免疫优化机理, 免疫算法是利用生命科学中的免疫原理而设计的优化算法，它利用抗体多样性的概念保证解的全局性，通过自我调节机制</div>
            <div class="thirtilte">保证解的全局性，通过自我调节机制和免疫记忆的形式进行最优解的搜索。特别适合一些具有不确定性、多目标、动态特征的调度优化问题.</div>
            <div class="thirtilte"></div>
          </el-collapse-item>
          <el-collapse-item name="2">
            <template slot="title">
              <el-button type="info" size="medium" class="label">
                粒子群调度算法
                <i class="el-icon-caret-bottom"></i>
              </el-button>
            </template>
            <div
              class="thirtilte"
              style="text-indent:34px"
            >粒子群调度算法模拟了生物界鸟群的飞行觅食行为，各个粒子代表鸟群中的个体，粒子间通过共享个体极值和全局极值进行信息交互寻找最优解。 在任务调度</div>
            <div
              class="thirtilte"
            >中，为各个算法寻找最优运行策略的过程即为粒子移动寻找最优解的过程。它是一种智能种群的随机优化算法，能够有效的搜索集群中各节点的运行状态获得全局</div>
            <div class="thirtilte">最优解。</div>
          </el-collapse-item>
          <el-collapse-item name="3">
            <template slot="title">
              <el-button type="info" size="medium" class="label">
                任务调度算法
                <i class="el-icon-caret-bottom"></i>
              </el-button>
            </template>
            <div
              class="thirtilte"
              style="text-indent:34px"
            >蚁群算法是模仿真实的蚁群行为而提出的一种模拟进化算法。蚂蚁个体之间是通过一种称之为信息素的物质进行信息传递，从而能相互协作，完成复杂的任务。</div>
            <div
              class="thirtilte"
            >蚂蚁在运动过程中，能够在它所经过的路径上留下信息素，而且蚂蚁在运动过程中能够感知这种物质的存在及其强度，并以此指导自己的运动方向，蚂蚁倾向于朝着</div>
            <div
              class="thirtilte"
            >该物质强度高的方向移动。因此，由大量蚂蚁组成的蚁群的集体行为便表现出一种信息正反馈现象：某一路径上走过的蚂蚁越多，则后来者选择该路径的概率就越大。</div>

            <div class="thirtilte">蚂蚁个体之间就是通过这种信息素的交流，搜索到一条从蚁巢到食物源的可能的较短路径。</div>
          </el-collapse-item>
          <el-collapse-item name="4">
            <template slot="title">
              <!-- <el-button type="primary">选择调度算法<i class="el-icon-caret-bottom">"></i></el-button> -->
              <el-button type="info" size="medium" class="label">选择调度算法:</el-button>
            </template>
            <el-select v-model="value" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>

    <el-button class="button" type="primary" @click="goback()">返回</el-button>
    <el-button class="button" type="primary" @click="compute()">确定</el-button>
  </div>
</template>
<script>
import { dodispatch } from "@/api/components";

export default {
  data() {
    return {
      dispatch: "",
      excuteid: "",
      options: [
        {
          value: "选项1",
          label: "免疫调度算法"
        },
        {
          value: "选项2",
          label: "粒子群调度算法"
        },
        {
          value: "选项3",
          label: "布谷鸟调度算法"
        }
      ],
      value: "",
      selected: 1,
      activeNames: ["1", "2", "3", "4"],
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
          example: "示例3"
        },
        {
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
  created() {
    this.excuteid = this.$route.query.eid;
    console.log("&&" + this.excuteid);
  },
  methods: {
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
        path: "/algorithms/HomePage",
        query: { eid: this.excuteid }
      });
    },
    compute() {
      if (this.value == "") {
        alert("请选择调度算法！");
      } else {
        let params = {
          pid: this.value,
          eid: this.excuteid
        };
        dodispatch(params)
          .then(response => {})
          .catch(error => {});
        this.$router.push({
          path: "/algorithms/ganttChart",
          query: { eid: this.excuteid }
        });
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
  text-align: left;
  padding-left: 20%;
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
.label {
  margin-left: 10px;
}
</style>