<template>
  <el-row>
    <el-col :span="24" class="left">
        <h3>注册组件</h3>
        <el-form>
            <el-form-item>
                <label>请输入待上传Jar包</label>
                <el-upload
                    class="upload-demo"
                    ref="jar"
                    :action="upload1"
                    :multiple="false"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :file-list="fileList"
                    :data="formdata"
                    :on-success="handlerSuccess"
                    :auto-upload="false">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                </el-upload>
            </el-form-item>
            <el-form-item>
                <label>请输入待上传XML文件</label>
                <el-upload
                    class="upload-demo"
                    ref="xml"
                    :action="doUpload2"
                    :multiple="false"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :file-list="fileList"
                    :data="formdata"
                    :on-success="handlerSuccess"
                    :auto-upload="false">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                </el-upload>
            </el-form-item>
            <el-form-item>
                <br>
                <el-button type="primary" @click="submitform">上传</el-button>
                <div slot="tip"></div>
            </el-form-item>
        </el-form>
    </el-col>
  </el-row>
</template>

<script>

export default {
  props: {
  },
  data() {
    return {
      upload1:"rest/xxx",
      upload2:"rest/xxx"
    }
  },
  created() {
    this.getLib()
  },
  methods: {
    submitform(){
        this.$refs.jar.submit();
        this.$refs.xml.submit();
    }
  }
}
</script>

<style scoped>
.col {
  color: black;
  font-weight: bold;
 }
 .left{
     padding: 20px;
 }
</style>
