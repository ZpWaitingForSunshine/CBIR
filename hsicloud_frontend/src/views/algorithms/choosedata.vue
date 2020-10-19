<template>
  <div class="container">
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#545c64"
      active-text-color="#ffd04b"
    >
      <el-menu-item index="1" @click="selects(1)">运行参数</el-menu-item>
      <!-- <el-submenu index="2">
    <template slot="title" @click="selects(2)">算法简介</template>
    <el-menu-item index="2-1" @click="selects(2)">主成分分析(PCA)</el-menu-item>
    <el-menu-item index="2-2" @click="selects(3)">纯净像元指数(PPI)</el-menu-item>
    <el-menu-item index="2-3" @click="selects(4)">光谱特征匹配(SAD)</el-menu-item>
      </el-submenu>-->
    </el-menu>
    <h3 class="title">运行参数</h3>
    <el-form
      v-for="(item) in nodes"
      :key="item.name"
      label-position="labelPosition"
      label-width="120px"
    >
      <!-- <button  type="button" style="color: white;" class="method" >step:{{item.step}} {{item.name}}</button> -->
      <el-row>
        <el-button type="primary">step:{{item.step}} {{item.name}}</el-button>
      </el-row>
      <div v-if="item.name==='PPI'" class="component">
        <el-row>
          <el-col :span="8">
            <el-form-item label="输入文件名称:">
              <el-input v-model="ppipara[0].inFileName" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输入文件路径:">
              <el-input v-model="ppipara[0].inFilePath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="随机向量数目:">
              <el-input v-model="ppipara[0].randomNum" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输出端元路径:">
              <el-input v-model="ppipara[0].outPiexlPath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="端元展示图:">
              <el-input v-model="ppipara[0].picPiexl" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div v-if="item.name==='PCA'" class="component">
        <el-row>
          <el-col :span="8">
            <el-form-item label="输入文件路径:">
              <el-input v-model="pcapara[0].inFilePath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输入文件名称:">
              <el-input v-model="pcapara[0].inFileName" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="降 维 数 目:">
              <el-input v-model="pcapara[0].demensionNum" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输出结果路径:">
              <el-input v-model="pcapara[0].outResultPath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div v-if="item.name==='SAD'" class="component">
        <el-row>
          <el-col :span="8">
            <el-form-item label="输入文件名称:">
              <el-input v-model="sadpara[0].inFileName" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输入文件路径:">
              <el-input v-model="sadpara[0].inFilePath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输入光谱库名称:">
              <el-input v-model="sadpara[0].inLibraryName" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输入光谱库路径:">
              <el-input v-model="sadpara[0].inLibraryPath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输入端元位置信息:">
              <el-input v-model="sadpara[0].inResultPath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输出结果路径:">
              <el-input v-model="sadpara[0].outPath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div v-if="item.name==='KMeans'" class="component">
        <el-row>
          <el-col :span="8">
            <el-form-item label="输入文件名称:">
              <el-input v-model="kmeanspara[0].inFileName" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输入文件路径:">
              <el-input v-model="kmeanspara[0].inFilePath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="聚类数目:">
              <el-input v-model="kmeanspara[0].classNum" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输出中心点位置:">
              <el-input v-model="kmeanspara[0].classCenter" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="聚类结果图:">
              <el-input v-model="kmeanspara[0].classResultPic" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div v-if="item.name==='NFinder'" class="component">
        <el-row>
          <el-col :span="8">
            <el-form-item label="输入文件名称:">
              <el-input v-model="nfinderpara[0].inFileName" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输入文件路径:">
              <el-input v-model="nfinderpara[0].inFilePath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="端元数目:">
              <el-input v-model="nfinderpara[0].pixNum" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="输出端元路径:">
              <el-input v-model="nfinderpara[0].outPiexlPath" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
    </el-form>
    <div style="margin-top:20px">
      <el-button type="primary" @click="startvm">准备</el-button>
      <el-button type="primary" @click="runprocedure">运行</el-button>
    </div>
  </div>
</template>
  

<script>
import Kanban from "@/components/Kanban";
import axios from "axios";
import { fetchvm, fetchdriverparams1, fetchxml } from "@/api/components";
import dagStore from "@/store/modules/dagStore.js";
// import {fetchdriverparams} from '@/api/components'
export default {
  name: "DragKanbanDemo",
  components: {
    Kanban
  },
  created() {
    this.excuteid = this.$route.query.eid;
    console.log("&&" + this.excuteid);
  },
  data() {
    return {
      fullscreenLoading: false,
      excuteid: "",
      labelPosition: "right",
      nodes: dagStore.state.DataAll.nodes,

      pcapara: [
        {
          name: "pca",
          inFilePath: "hdfs://10.10.10.64:9000/",
          inFileName: "c350bip_2",
          demensionNum: "15",
          outResultPath: "hdfs://10.10.10.64:9000/pca1113"
        }
      ],

      sadpara: [
        {
          name: "sad",
          inFileName: "c350bip_2",
          inFilePath: "hdfs://10.10.10.64:9000/",
          inLibraryName: "usgs_min",
          inLibraryPath: "hdfs://10.10.10.64:9000/",
          inResultPath: "hdfs://10.10.10.64:9000/data/ppitxt.txt",
          outPath: "hdfs://10.10.10.64:9000/sadres.txt"
        }
      ],
      ppipara: [
        {
          name: "ppi",
          inFileName: "c350bip_2",
          inFilePath: "hdfs://10.10.10.64:9000/",
          randomNum: "13586",
          outPiexlPath: "hdfs://10.10.10.64:9000/em.txt",
          picPiexl: "hdfs://10.10.10.64:9000/ppires.jpg"
        }
      ],
      kmeanspara: [
        {
          name: "kmeans",
          inFileName: "c350bip_2",
          inFilePath: "hdfs://10.10.10.64:9000/",
          classNum: "5",
          classCenter: "hdfs://10.10.10.64:9000/kmtxt.txt",
          classResultPic: "hdfs://10.10.10.64:9000/kmjpg.jpg"
        }
      ],
      nfinderpara: [
        {
          name: "nfinder"
        }
      ],
      rdpara: [
        {
          name: "ricedetection"
        }
      ],
      // target:this.$route.query.methods,
      list: [],
      listsum: [],
      formLabelWidth: 80
    };
  },
  //  created() {
  //   const pp  =this.$route.params && this.$route.params.dparams
  //   this.getList(id)
  // },
  methods: {
    dropzoneS(file) {
      this.$message({ message: "Upload success", type: "success" });
    },
    dropzoneR(file) {
      this.$message({ message: "Delete success", type: "success" });
    },
    resize() {
      console.log("resize");
      // console.log(da)
    },
    beforeUpload(file) {
      return true;
    },
    // 文件超出个数限制时的钩子
    exceedFile(files, fileList) {
      this.$notify.warning({
        title: "警告",
        message: `只能选择 ${
          this.limitNum
        } 个文件，当前共选择了 ${files.length + fileList.length} 个`
      });
    },
    exceedFile2(files, fileList) {
      this.$notify.warning({
        title: "警告",
        message: `只能选择 ${
          this.limitNum
        } 个文件，当前共选择了 ${files.length + fileList.length} 个`
      });
    },
    // 文件状态改变时的钩子
    fileChange(file, fileList) {
      console.log("change");
      console.log(file);
      this.form.file = file.raw;
      console.log(this.form.file);
      console.log(fileList);
    },
    fileChange2(file, fileList) {
      console.log("change");
      console.log(file);
      this.form2.file = file.raw;
      console.log(this.form.file);
      console.log(fileList);
    },
    // 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
    beforeUploadFile(file) {
      console.log("before upload");
      console.log(file);
      let extension = file.name.substring(file.name.lastIndexOf(".") + 1);
      let size = file.size / 1024 / 1024;
      // if(extension !== 'xlsx') {
      //   this.$notify.warning({
      //     title: '警告',
      //     message: `只能上传Excel2017（即后缀是.xlsx）的文件`
      //   });
      // }
      if (size > 100) {
        this.$notify.warning({
          title: "警告",
          message: `文件大小不得超过10M`
        });
      }
    },
    // 文件上传成功时的钩子
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: "成功",
        message: `her文件上传成功`
      });
    },
    handleSuccess2(res, file, fileList) {
      this.$notify.success({
        title: "成功",
        message: `img文件上传成功`
      });
    },
    // 文件上传失败时的钩子
    handleError(err, file, fileList) {
      console.log(err);
      this.$notify.error({
        title: "错误",
        message: `文件上传失败`
      });
    },
    handleError2(err, file, fileList) {
      console.log(err);
      this.$notify.error({
        title: "错误",
        message: `文件上传失败`
      });
    },
    uploadFile() {
      this.$refs.uploadExcel.submit();
    },
    uploadFile2() {
      this.$refs.uploadImg.submit();
    },
    ////////////////
    /*
     setValue() {
      //axios.post('/', {})
      axios.post('/api/setValue', {
        id: 777, name: this.inpContent
      }).then( (res) => {
        console.log('res', res);
      })
      alert("success");
    }
    */
    setValue() {
      axios
        .post("/api/setValue", {
          pca: this.pcapara,
          nmf: this.nmfpara,
          svd: this.svdpara
        })
        .then(res => {
          console.log("res", res);
        });
      alert("success");
    },
    startvm() {
      //this.fullscreenLoading = true;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      });

      let params = {
        eid: this.excuteid
      };

      fetchvm(params).then(response => {
        // this.fullscreenLoading = false;
        if (response.code == 200) {
          loading.close();
          alert("启动成功");
        } else {
          loading.close();
          alert("启动失败");
        }
      });
    },

    runprocedure() {
      this.list = this.kmeanspara
        .concat(this.pcapara)
        .concat(this.ppipara)
        .concat(this.sadpara)
        .concat(this.nfinderpara);

      // sum=this.listsum.concat(this.list[i])

      this.$router.push({
        path: "../../aprogress/progress/",
        query: { eid: this.excuteid }
      });
      let params = {
        list: JSON.stringify(this.list),
        eid: this.excuteid
      };

      fetchdriverparams1(params)
        .then(response => {
          if (response.code == 200) {
            console.log(response.data);
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>
<style lang="scss">
.container {
  padding: 10px;
  align-content: "left";
}
.component {
  margin: 20px;
}
.title {
  font-size: 35px;
  color: #008cba;
}
</style>

