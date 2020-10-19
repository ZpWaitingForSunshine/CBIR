<template>
  <div class="components-container">
    <!-- <aside>
      Based on <a class="link-type" href="https://github.com/rowanwins/vue-dropzone"> dropzone </a>.
      {{ $t('components.dropzoneTips') }}
    </aside> -->
    <!-- <div class="editor-container">
      <dropzone id="myVueDropzone" url="http://localhost/apis/rest/image/addImage" @dropzone-removedFile="dropzoneR" @dropzone-success="dropzoneS" />
    </div> -->

    <!-- <el-upload
 class="upload-demo"
 ref="upload"
 action="doUpload"
 :limit="1"
 :before-upload="beforeUpload">
 <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
 <a href="./static/moban.xlsx" rel="external nofollow" download="模板"><el-button size="small" type="success">下载模板</el-button></a>
  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button> -->
 <!-- <div slot="tip" class="el-upload__tip">只能上传excel文件，且不超过5MB</div>
 <div slot="tip" class="el-upload-list__item-name">{{fileName}}</div>
</el-upload> 
<span slot="footer" class="dialog-footer">
 <el-button @click="visible = false">取消</el-button>
 <el-button type="primary" @click="submitUpload()">确定</el-button>
</span> --> 


<template>

  <el-row :gutter="8">
      <h3>图片:T17-09-38</h3>
      <el-col :xs="{span: 24}" :sm="{span: 24}" :md="{span: 24}" :lg="{span: 12}" :xl="{span: 12}" style="padding-right:8px;margin-bottom:30px;">
        <!-- 图片显示 -->
        <img src="@/assets/525.jpg" height="400px"/>
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 24}" :md="{span: 24}" :lg="{span: 12}" :xl="{span: 12}" style="padding-right:8px;margin-bottom:30px;">
        <lineList :chart-data="lineChartData"/>
      </el-col>
      <el-col :xs="{span: 48}" :sm="{span: 48}" :md="{span: 48}" :lg="{span: 24}" :xl="{span: 24}" style="padding-right:8px;margin-bottom:30px;" align="center">
        <el-button type="primary" size="small" icon="el-icon-edit">
              去操作
            </el-button>
      </el-col>
    </el-row>
</template>

    <!-- <split-pane split="vertical" @resize="resize">
      
      <template slot="paneL">
        <split-pane split="horizontal">
          <template slot="paneL">
            <div class="top-container" />
          </template>
          <template slot="paneR">
            <div class="bottom-container" />
          </template>
        </split-pane>
      </template>
      <template slot="paneR">
        <div class="left-container" />
      </template>
    </split-pane> -->

  </div>
</template>

<script>
const lineChartData = {
  newVisitis: {
    expectedData: [4517,4757,4887,5029,5145,5215,5307,5468,5641,5708,5654,5534,5571,5376,5228,5009,4866,4686,4492,4361,4250,4234,4224,4275,4192,4191,4172,4215,4194,4108,4066]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}
import Dropzone from '@/components/Dropzone'
import splitPane from 'vue-splitpane'
import axios from 'axios'
import lineList from './comphonents/lineList'
export default {
  data() {
    return {
      limitNum: 1,
      formLabelWidth: '80px',
      form: {
        file: ''
      },
      form2: {
        file: ''
      },
      fileList: [],
      fileList2: [],
      importHeaders:['multipart/form-data'],
      name: 'DropzoneDemo',
      lineChartData: lineChartData.newVisitis
    }
  },
  created(){
     const id = this.$route.params && this.$route.params.id
     
  },
  components: { Dropzone, splitPane, lineList },
  methods: {
        dropzoneS(file) {
      console.log(file)
      this.$message({ message: 'Upload success', type: 'success' })
    },
    dropzoneR(file) {
      console.log(file)
      this.$message({ message: 'Delete success', type: 'success' })
    },
    resize() {
      console.log('resize')
    },
    beforeUpload(file){
        return true;
    },
    // 文件超出个数限制时的钩子
    exceedFile(files, fileList) {
      this.$notify.warning({
        title: '警告',
        message: `只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length} 个`
      });
    },
    exceedFile2(files, fileList) {
      this.$notify.warning({
        title: '警告',
        message: `只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length} 个`
      });
    },
    // 文件状态改变时的钩子
    fileChange(file, fileList) {
      console.log('change')
      console.log(file)
      this.form.file = file.raw
      console.log(this.form.file)
      console.log(fileList)
    },
     fileChange2(file, fileList) {
      console.log('change')
      console.log(file)
      this.form2.file = file.raw
      console.log(this.form.file)
      console.log(fileList)
    },
    // 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
    beforeUploadFile(file) {
      console.log('before upload')
      console.log(file)
      let extension = file.name.substring(file.name.lastIndexOf('.')+1)
      let size = file.size / 1024 / 1024
      // if(extension !== 'xlsx') {
      //   this.$notify.warning({
      //     title: '警告',
      //     message: `只能上传Excel2017（即后缀是.xlsx）的文件`
      //   });
      // }
      if(size > 100) {
        this.$notify.warning({
          title: '警告',
          message: `文件大小不得超过10M`
        });
      }
    },
    // 文件上传成功时的钩子
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: '成功',
        message: `her文件上传成功`
      });
    },
    handleSuccess2(res, file, fileList) {
      this.$notify.success({
        title: '成功',
        message: `img文件上传成功`
      });
    },
    // 文件上传失败时的钩子
    handleError(err, file, fileList) {
      console.log(err)
      this.$notify.error({
        title: '错误',
        message: `文件上传失败`
      });
    },
    handleError2(err, file, fileList) {
      console.log(err)
      this.$notify.error({
        title: '错误',
        message: `文件上传失败`
      });
    },
    uploadFile() {
      this.$refs.uploadExcel.submit()
    },
    uploadFile2() {
      this.$refs.uploadImg.submit()
    }
  }
}

// export default {
//   data(){return{
//     fileName:"dddd"}
//   },
//   name: 'DropzoneDemo',
//   components: { Dropzone, splitPane },
//   methods: {
//     dropzoneS(file) {
//       console.log(file)
//       this.$message({ message: 'Upload success', type: 'success' })
//     },
//     dropzoneR(file) {
//       console.log(file)
//       this.$message({ message: 'Delete success', type: 'success' })
//     },
//     resize() {
//       console.log('resize')
//     },
//     beforeUpload(file){
//         return true;
//     },
//     submitUpload() {
//       debugger
//       console.log('上传'+this.files.name)
//       if(this.fileName == ""){
//       this.$message.warning('请选择要上传的文件！')
//       return false
//       }
//       let fileFormData = new FormData();
//       fileFormData.append('file', this.files, this.fileName);//filename是键，file是值，就是要传的文件，test.zip是要传的文件名
//       let requestConfig = {
//         headers: {
//         'Content-Type': 'multipart/form-data'
//         },
//       }
//       this.$http.post(`http://10.10.10.96:8080/rest/image/upload1?companyId=`+this.company, fileFormData, requestConfig).then((res) => {
//       debugger
//       if (data && data.code === 0) {
//       this.$message({
//         message: '操作成功',
//         type: 'success',
//         duration: 1500,
//         onClose: () => {
//           this.visible = false
//           this.$emit('refreshDataList')
//         }
//       })
//       } else {
//         this.$message.error(data.msg)
//         }
//       }) 
//     },
//   }
// }
</script>
<style  scoped>
  .components-container {
    position: relative;
    height: 100vh;
  }

  .left-container {
    background-color: #F38181;
    height: 100%;
  }

  .right-container {
    background-color: #FCE38A;
    height: 200px;
  }

  .top-container {
    background-color: #FCE38A;
    width: 100%;
    height: 100%;
  }

  .bottom-container {
    width: 100%;
    background-color: #95E1D3;
    height: 100%;
  }
</style>
