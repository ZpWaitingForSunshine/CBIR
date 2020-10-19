<template>
<div>

  <el-menu
  :default-active="activeIndex2"
  class="el-menu-demo"
  mode="horizontal"
  @select="handleSelect"
  background-color="#545c64"
  text-color="#545c64"
  active-text-color="#545c64">
  <el-menu-item index="1" @click="selects(1)">查找资源</el-menu-item>
  <!-- <el-submenu index="2">
    <template slot="title" @click="selects(2)">算法简介</template>
    <el-menu-item index="2-1" @click="selects(2)">主成分分析(PCA)</el-menu-item>
    <el-menu-item index="2-2" @click="selects(3)">纯净像元指数(PPI)</el-menu-item>
    <el-menu-item index="2-3" @click="selects(4)">光谱特征匹配(SAD)</el-menu-item>
  </el-submenu> -->
</el-menu>

<div v-if="selected==1">
<div class="sectile">

<el-collapse v-model="activeNames"  @change="handleChange" >
  <el-collapse-item  >
    <template slot="title" >
        <el-button type="text"  >关键字查找 : </el-button>
      <el-input  placeholder="请输入内容"></el-input>
    </template>
    
  </el-collapse-item>
    <el-collapse-item  >
    <template slot="title" >
        <el-button type="text"  >按时间查找 : </el-button>
       <div class="block">
    <el-date-picker
      v-model="value1"
      type="date"
      placeholder="选择日期">
    </el-date-picker>
  </div>
    </template>
    
  </el-collapse-item>
  <el-collapse-item  >
    <template slot="title" >
        <el-button type="text"  >按大小查找 :  </el-button>
      <el-select v-model="value" placeholder="请选择">
    <el-option
      v-for="item in options2"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
    </template>
    
  </el-collapse-item> 
   <el-collapse-item  >
    <template slot="title" >
        <el-button type="text" >按类型查找 : </el-button>
      <el-select v-model="value" placeholder="请选择">
    <el-option
      v-for="item in options1"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
    </template>
    
  </el-collapse-item>
</el-collapse>
</div>
</div>


  
    <el-button class="button" type="primary" @click="compute()">检索</el-button>
  </div>
</template>

<script>
import errGif from '@/assets/401_images/401.gif'
  export default {
    data() {
      return {
        options1: [{
          value: '选项1',
          label: '岩矿'
        }, {
          value: '选项2',
          label: '农作物'
        }, {
          value: '选项3',
          label: '其他'
        }],
         options2: [{
          value: '选项1',
          label: '大于1TB '
        }, {
          value: '选项2',
          label: '1TB~1GB'
        }, {
          value: '选项3',
          label: '小于1GB'
        }],
        selected:1,
        activeNames: [],
        activeIndex: '1',
        activeIndex2: '1',
        pcaData: [{
          name: '参数1',
          describe: '描述1',
          example: '示例1'
        }, {
          name: '参数2',
          describe: '描述2',
          example: '示例2'
        }, {
          name: '参数3',
          describe: '描述3',
          example: '示例3'
        }, {
          name: '参数4',
          describe: '描述4',
          example: '示例4'
        }],
        pcaRes: [{
          name: '输出结果',
          expalin: '输出结果解释'
        }],        
        ppiData: [{
          name: '参数1',
          describe: '描述1',
          example: '示例1'
        }, {
          name: '参数2',
          describe: '描述2',
          example: '示例2'
        }, {
          name: '参数3',
          describe: '描述3',
          example: '示例3'
        }, {
          name: '参数4',
          describe: '描述4',
          example: '示例4'
        }],
        ppiRes: [{
          name: '输出结果',
          expalin: '输出结果解释'
        }]
      };
    },
    methods: {
      handleChange(val) {
        console.log(val);
      },
     handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      selects(a){
              this.selected = a;
              //console.log("data:",this.data);
     },
    
     compute(){
         this.$router.push({path:'/images/detail'})
     },
    }
  }
</script>
<style scoped>
.title{
  font-size: 35px;
  color: rgb(37, 36, 39)
}
.sectile{
  font-size:20px;
  /* background-color: black; */
  color: black;
  font-family: "微软雅黑";
  font-style: normal;
  margin-top:30px;
  margin-left:10px;
}
.thirtilte{

  font-size:17px;
}
.button {
    float: right ;
    background-color: #008CBA; /* Green */
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
     margin-top:30px;
     margin-right:30px;
}

</style>