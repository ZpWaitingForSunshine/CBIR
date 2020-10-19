<template>
  <svg
     id="svgContent"
     :style="{cursor: this.currentEvent === 'move_graph' ? 'grabbing' : 'grab'}"
     xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" width="1260" height="1029" data-spm-anchor-id="TODO.11007039.0.i6.12b64a9bcbXQmm"
  >
  <g :transform="` translate(${svg_left}, ${svg_top}) scale(${svgScale})`" >
    <g
      v-for="(item, i) in DataAll.nodes"
        :key="'_' + i" class="svgEach"
        :transform="`translate(${item.pos_x}, ${item.pos_y})`"
    >
              <foreignObject width="180" height="30" >
              <body xmlns="http://www.w3.org/1999/xhtml" style="margin: 0" >
              <div>
                <div :class="choice.paneNode.indexOf(item.id) !== -1 ? 'pane-node-content selected' : 'pane-node-content'">
        <!-- <span class="icon icon-data"></span>  -->
      <circle-progress 
        v-if="choice.paneNode.indexOf(item.id) == -1"
        ref="$circle"
        class="icon icon-data"
        key="duration-model"
        :isAnimation="false"
        :isRound="true"
        :width="width"
        :radius="radius"
        :progress="currentTime / durationTime * 100"
        :barColor="barColor"
        :duration="duration"
        :delay="delay"
        :timeFunction="timeFunction"
        :backgroundColor="backgroundColor">
      </circle-progress>
      <span v-else class="bcon bcon-data"></span>
              <input type="text" class="name"  v-model="item.name">
                </div>
                <div :class="currentEvent === 'dragLink' ? 'pane-node-parent-hl' : 'pane-node-parent' ">
                  <div v-for="(poi, nth) in item.in_ports" :key="'__' + nth" :style="{width: `${ 100 / (item.in_ports.length + 1)}%`}">
                    <span class="space"></span>
                  </div>
                </div>
                <div class="pane-node-children">
                  <div v-for="(poi, nth) in item.out_ports" :key="'___' + nth" :style="{width: `${ 100 / (item.out_ports.length + 1)}%`}">
                    <span class="space" ></span>
                  </div>
                </div>
              </div>
              </body>
            </foreignObject>
          </g>
          <Arrow v-for="(each, n) in DataAll.edges" :key="'____' + n" :DataAll="DataAll" :each="each" :index="n" />
    </g>
      <Control  :currentEvent="currentEvent" />
    </svg>
</template>
<script>
import Arrow from "./arrow.vue";
import { mapState, mapActions } from "vuex";
import Control from "./control.vue";
import { setTimeout } from 'timers';
import { networkInterfaces } from 'os';
import {dagcheck} from '@/api/components'
import CircleProgress from './circle-progress';



export default {
 
  props: {
        
    model_id: {
      // 入参model_id 如果此入参存在,则认为是打开已保存的模型
      type: String,
      default: null
    },
    template: {
      // 入参template 如果model_id不存在, 则会新建一个此类型的模型.
      type: String,
      default: "default"
    }
  },
  computed: mapState({
    DataAll: state => state.dagStore.DataAll,
    svgScale: state => state.dagStore.svgSize
  }),
  created() {
  },
  mounted() {
    sessionStorage["svg_left"] = 0;
    sessionStorage["svg_top"] = 0;
   // this.changebackground();
    this.startTime();
   
    //this.start();
  },
  methods: {
   
    ...mapActions([
      "openGraph",
      "newGraph",
      "addEdge",
      "delEdge",
      "addNode",
      "delNode",
      "showGraph",
      "saveGraph",
      "moveNode",
      "changeSize"
    ]),
    ////////////////////////////////////////////
    changebackground(e){

        var that = this;
        //var n = 0;
         this.timer = setInterval(function(){
          
         that.fn(e);//每隔3调用函数fn
         that.startTime();
        // n++;
       },3000);
    },
    rmTimer(){
      console.log("rm sucess")
      clearInterval(this.timer)
    },
    fn(e){  
        let params={
        "eid":e
      }
      
        dagcheck(params).then(response => {
          console.log(response.data)
        this.list = response.data
        		 for(var i = 0;i < this.list.length ; i++){//遍历运行中的算法数组
			 if(this.list[i].value==1){//判断是否运行结束
			     //当运行结束后
				 for(var j = 0;j < this.DataAll.nodes.length ; j++)//遍历算法节点数组
				     if(this.DataAll.nodes[j].id == this.list[i].cid){//更改运行完成的算法的背景颜色
					    this.choice.paneNode.push(this.DataAll.nodes[j].id)					 
					 }				 
       }
     }
      })
	/*
          if(n < this.DataAll.nodes.length){
          this.choice.paneNode.push(this.DataAll.nodes[n].id)
          }
	*/
	  //  var running_id =  ？  //向后台发送请求 获取正在运行的算法的id
		//  var running_state = ？//向后台发送请求 获取正在运行的算法的状态 id与状态相对应

    //  var count=0;
    //  for(var i = 0;i < this.list.length ; i++){//遍历运行中的算法数组
		// 	 if(this.list[i].value==1){//判断是否运行结束
		// 	   count=count+1;
    //    }
    //  }
     
    //  if(count==this.this.list.length ){
    //      this.$router.push({path:'../../algorithms/ShowResult/'})
    //  } 
        },
    ////////////////////////////////////////////
  // 开始计时
    // start(){
    //     var that = this;
    //     var timer = setInterval(function(){
    //      that.startTime();//
    //    },10000);
    // },
    startTime() {
      let spaceTime = 10;
      clearInterval(this.intervalTimer)
      this.currentTime = 0
      this.intervalTimer = setInterval(() => {
        this.currentTime += spaceTime / 1000
        if (this.currentTime >= this.durationTime) {
          clearInterval(this.intervalTimer)
        }
      }, spaceTime)
    },
    ////////////////////////////////////////////
    /**
     * 工具类
     */
    setInitRect() {
      // 矫正svg组件坐标
      let { left, top } = document
        .getElementById("svgContent")
        .getBoundingClientRect();
      this.initPos = { left, top };
    }
  },
  data() {
    return {
   timer:null,
      //动画
      isDurationModel: true, // 是否打开持续缓动模式
      isShow: true,
      width: 25,
      radius: 5,
      progress: 70,
      duration: 1000,
      delay: 20,
      barColor: '#289de9',
      backgroundColor: '#87CEFA',
      timeFunction: 'cubic-bezier(0.99, 0.01, 0.22, 0.94)',
      intervalTimer: null, // 持续模式，循环计时器
      currentTime: 0, // 当前时间
      durationTime: 3, // 持续模式总时长
       //动画
      choice: {
        paneNode: [], // 选取的节点下标组
        index: -1,
        point: -1 // 选取的点数的下标
      },
      dragFrame: {
        // 节点模态
        posX: 9999,
        posY: 9999
      },
      dragLink: {
        // 连线模态
        fromX: 0,
        fromY: 0,
        toX: 0,
        toY: 0
      },
      currentEvent: null, // 当前执行的方法
      initPos: {
        // 初始化svg dom位置
        left: -1,
        top: -1
      },
      timeStamp: "",
      is_edit_area: {
        value: false,
        x: -9999,
        y: -9999
      }, // 是否在编辑节点
      simulate_sel_area: {
        // 框选节点
        left: 10,
        top: 50,
        width: 0,
        height: 0
      },
      svg_left: 0,
      svg_top: 0,
      svg_trans_init: {
        x: 0,
        y: 0
      },
      canMouseWheelUse: true
    };
  },
    //动画
  watch: {
    isDurationModel() {
      clearInterval(this.intervalTimer)
      this.currentTime = 0
    }
  },
  components: {
    Arrow,
    Control,
   CircleProgress,
  }
};
</script>

<style lang="scss" scoped>
.svgEach {
  position: relative;
}
.pane-node-content {
  box-sizing: border-box;
  width: 180px;
  height: 30px;
  background-color: white;//按钮背景颜色
  border: 1px solid #289de9;
  border-radius: 15px;
  font-size: 12px;
  -webkit-transition: background-color 0.2s;
  transition: background-color 0.2s;
  .bcon{
    width: 26px;
    height: 26px;
    margin: 1px;
    border-radius: 100%;
    float: left;
    color: #fff;
    font-size: 16px;
    background-color:#289de9;
    cursor: pointer;
  }
  .icon {
    width: 26px;
    height: 26px;
    margin: 1px;
    border-radius: 100%;
    float: left;
    color: #fff;
    font-size: 16px;
    cursor: pointer;
  }
  .name {
    float: left;
    margin-left: 2px;
    width: 135px;
    // height: 28px;
    line-height: 28px;
    font-size: 14px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: pointer;
    user-select: none;
    height: 26px;
    background: transparent;
    border: none;
  }
  .parentLink {
    font-size: 0;
    height: 12px;
    width: 12px;
    position: absolute;
    top: 0;
    left: 90px;
    transform: translateX(-50%);
    border-top: 6px solid black;
    border-left: 6px solid transparent;
    border-right: 6px solid transparent;
  }
  .childLink {
    height: 10px;
    width: 10px;
    position: absolute;
    bottom: 0;
    left: 90px;
    transform: translate(-50%, 50%);
    border-radius: 50%;
    background: #ffffff;
    cursor: crosshair;
  }
}
.pane-node-parent-hl {
  position: fixed;
  top: -5px;
  height: 10px;
  width: 100%;
  display: flex;
  transform: translateX(6px);
  .space {
    width: 12px;
    height: 12px;
    border-radius: 50%;
    border: 1px solid gray;
    background: #ffffff;
    position: absolute;
    right: 0;
    top: 0;
    cursor: crosshair;
  }
  .space:hover {
    box-shadow: 0 0 0 6px #3ddd73;
  }
}
.pane-node-parent-hl > div {
  position: relative;
  display: inline-block;
}

.pane-node-parent {
  position: fixed;
  top: -5px;
  height: 10px;
  width: 100%;
  display: flex;
  opacity: 0;
  transform: translateX(6px);
  .space {
    width: 12px;
    height: 12px;
    border-radius: 50%;
    border: 1px solid gray;
    background: #ffffff;
    position: absolute;
    right: 0;
    top: 0;
  }
}

.pane-node-parent > div {
  position: relative;
  display: inline-block;
}

.pane-node-children {
  position: fixed;
  bottom: 0;
  width: 100%;
  display: flex;
  opacity: 0;
  transform: translateX(6px);
  .space {
    width: 12px;
    height: 12px;
    border-radius: 50%;
    border: 1px solid gray;
    background: #ffffff;
    position: absolute;
    right: 0px;
    bottom: -6px;
    cursor: crosshair;
  }
  .space:hover {
    background: #cccccc;
  }
}
.pane-node-children:hover {
  opacity: 1;
}

.pane-node-children > div {
  position: relative;
  display: inline-block;
}

.selected {
  background: #f26006 !important;
}
.connector {
  stroke: hsla(0, 0%, 50%, 0.6);
  stroke-width: 2px;
  fill: none;
}
.simulate_sel_area {
  border: 3px dashed blue;
  position: absolute;
}
</style>
