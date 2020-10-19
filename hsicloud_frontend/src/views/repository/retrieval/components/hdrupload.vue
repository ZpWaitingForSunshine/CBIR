<template>
  <div class="dashboard-editor-container">
    <div class="tabs">
      <transition key="hdr" name="el-fade-in-linear">
        <el-form :model="form">
          <el-form-item>
            <el-upload
              class="upload-file"
              drag
              :action="doUpload"
              :limit="1"
              :before-upload="beforeUploadFile"
              :on-change="fileChange"
              :on-exceed="exceedFile"
              :on-success="handleSuccess"
              :on-error="handleError"
            >
              <i class="el-icon-upload" />
              <div class="el-upload__text">Drag HDR file here, or <em>Click for uploading</em></div>
            </el-upload>
          </el-form-item>
        </el-form>
      </transition>
    </div>
  </div>
</template>
<script>

export default {
  name: 'Hdrupload',
  components: { },
  data() {
    return {
      orderStatus: 0,
      doUpload: 'rest/image/hdrupload',
      fileList: [],
      importHeaders: ['multipart/form-data'],
      form: {
        file: ''
      },
      formdata: {},
      final: {}
    }
  },
  computed: {
    // ...mapGetters([
    //   'name',
    //   'avatar',
    //   'roles'
    // ])
  },
  methods: {
    beforeUpload(file) {
      return true
    },

    // 文件超出个数限制时的钩子
    exceedFile(files, fileList) {
      this.$notify.warning({
        title: 'warning',
        message: `只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length} 个`
      })
    },

    fileChange(file, fileList) {
      console.log('change')
      this.form.file = file.raw
    },

    beforeUploadFile(file) {
      console.log('before upload')
      // console.log(file)
      const extension = file.name.substring(file.name.lastIndexOf('.') + 1)
      const size = file.size / 1024 / 1024
      if (extension !== 'hdr') {
        this.$notify.warning({
          title: 'warning',
          message: `hdr only`
        })
      }
      if (size > 100) {
        this.$notify.warning({
          title: 'warning',
          message: `10M limited`
        })
      }
    },

    // 文件上传成功时的钩子 success
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: 'success',
        message: `hdr upload successfully`
      })
      console.log('success')
      console.log('step' + this.$store.state.retrieval.step)
      console.log(res.data)
      this.$store.commit('retrieval/SET_STEP', 1)
      // console.log(res)
      // console.log(JSON.stringify(res))
      this.formdata = res.data
      this.$emit('showFinalStep', this.formdata)

      this.formdata['hdfsurl'] = null
    },

    // 文件上传失败时的钩子 failed
    handleError(err, file, fileList) {
      console.log(err)
      this.$notify.error({
        title: 'error',
        message: `fiel upload faild`
      })
    },

    showFinal(payload) {
      console.log('hellod')
      this.orderStatus = 2
      this.final = payload
    }

    // uploadFile() {
    //   // this.$refs.uploadExcel.submit()
    // }
  }
}
</script>

<style lang="scss" scoped>
.tabs{
  margin-top: 20px;
}
  .emptyGif {
    display: block;
    width: 45%;
    margin: 0 auto;
  }

  .dashboard-editor-container {
    background-color: #fff;
    min-height: 100vh;
    padding: 50px 60px 0px;
    .pan-info-roles {
      font-size: 12px;
      font-weight: 700;
      color: #333;
      display: block;
    }
    .info-container {
      position: relative;
      margin-left: 190px;
      height: 150px;
      line-height: 200px;
      .display_name {
        font-size: 48px;
        line-height: 48px;
        color: #212121;
        position: absolute;
        top: 25px;
      }
    }
  }
.property-title{
  display:flex;
  justify-content:flex-end;
  padding-right:20px;
}
</style>
