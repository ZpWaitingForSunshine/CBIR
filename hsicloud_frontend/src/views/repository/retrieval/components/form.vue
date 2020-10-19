<template>
  <div class="formBar">
    <!-- {{ formdata.filename }}
    {{ formdata }} -->
    <el-form :model="formdata" :label-position="labelPosition" label-width="180px">
      <el-form-item v-for="(item,i) in form" :key="i" prop="title" :label="item.inputLabel">
        <!-- <label>{{ item.name }}</label> -->
        <el-input
          v-model="formdata[item.name]"
          :type="item.inputType"
          :disabled="item.disabled"
          :placeholder="item.placeholder"
          :required="item.required"
        />
      </el-form-item>
    </el-form>
    <el-form>
      <el-form-item>
        <label>upload image data</label>
        <el-upload
          :v-model="file"
          class="upload-file"
          :action="doUpload"
          :limit="1"
          :before-upload="beforeUploadFile"
          :on-change="fileChange"
          :on-exceed="exceedFile"
          :on-success="handleSuccess"
          :on-error="handleError"
          :data="formdata"
        >
          <el-button size="small" type="primary">Upload</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <br>
        <el-button type="primary" @click="submitForm(formdata)">Confirm</el-button>
        <div slot="tip">confirm the image's information</div>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
import { imgUpdate } from '@/api/image'

export default {
  components: {},
  props: {
    formdata: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      file: null,
      filelist: [],
      formBarForm: {},
      form2: { id: '1' },
      labelPosition: 'left',
      doUpload: 'rest/image/imgupload',
      form: [
        {
          name: 'id', type: 'input', inputType: 'number', disabled: true, inputLabel: 'ID', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'filename', type: 'input', inputType: 'string', disabled: false, inputLabel: 'filename', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'samples', type: 'input', inputType: 'string', disabled: false, inputLabel: 'samples', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'rows', type: 'input', inputType: 'string', disabled: false, inputLabel: 'rows', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'bands', type: 'input', inputType: 'string', disabled: false, inputLabel: 'bands', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'headeroffset', type: 'input', inputType: 'string', disabled: false, inputLabel: 'headeroffset', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'filetype', type: 'input', inputType: 'string', disabled: false, inputLabel: 'filetype', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'datatype', type: 'input', inputType: 'string', disabled: false, inputLabel: 'datatype', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'interleave', type: 'input', inputType: 'string', disabled: false, inputLabel: 'interleave', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'byteorder', type: 'input', inputType: 'string', disabled: false, inputLabel: 'byteorder', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'bandnames', type: 'input', inputType: 'textarea', disabled: false, inputLabel: 'bandnames', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'wavelength', type: 'input', inputType: 'textarea', disabled: false, inputLabel: 'wavelength', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'timeofexposure', type: 'input', inputType: 'string', disabled: false, inputLabel: 'timeofexposure', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'imagebin', type: 'input', inputType: 'string', disabled: false, inputLabel: 'imagebin', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'autoexproix', type: 'input', inputType: 'string', disabled: false, inputLabel: 'autoexproix', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'autoexproiy', type: 'input', inputType: 'string', disabled: false, inputLabel: 'autoexproiy', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'autoexproiwidth', type: 'input', inputType: 'string', disabled: false, inputLabel: 'autoexproiwidth', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'autoexproiheight', type: 'input', inputType: 'string', disabled: false, inputLabel: 'autoexproiheight', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'gain', type: 'input', inputType: 'string', disabled: false, inputLabel: 'gain', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'exposurecoefficient', type: 'input', inputType: 'string', disabled: false, inputLabel: 'exposurecoefficient', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'lctftemperature', type: 'input', inputType: 'string', disabled: false, inputLabel: 'lctftemperature', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'wavebegin', type: 'input', inputType: 'string', disabled: false, inputLabel: 'wavebegin', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'waveend', type: 'input', inputType: 'string', disabled: false, inputLabel: 'waveend', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'thumbnailurl', type: 'input', inputType: 'string', disabled: true, inputLabel: 'thumbnailurl', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'hdfsurl', type: 'input', inputType: 'string', disabled: true, inputLabel: 'hdfsurl', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }, {
          name: 'size', type: 'input', inputType: 'string', disabled: true, inputLabel: 'size', modelName: 'userName', placeholder: '', required: true, defaultValue: null
        }
      ]
    }
  },
  created() {
    console.log('created')
    // console.log(this.formdata)
  },
  mounted() {

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
      // console.log(file)
      this.form.file = file.raw
      // console.log(this.form.file)
      // console.log(fileList)
    },

    beforeUploadFile(file) {
      console.log('before upload')
      // console.log(file)
      const extension = file.name.substring(file.name.lastIndexOf('.') + 1)
      const size = file.size / 1024 / 1024
      // if (extension !== 'img' || ' ') {
      //   this.$notify.warning({
      //     title: 'warning',
      //     message: `hdr only`
      //   })
      // }
      // if (size > 100) {
      //   this.$notify.warning({
      //     title: 'warning',
      //     message: `10M limited`
      //   })
      // }
    },

    // 文件上传成功时的钩子
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: 'success',
        message: `image upload successfully`
      })
      this.formdata.size = file.size / 1024 / 1024
      this.formdata.thumbnailurl = res.data.thumbnailurl
      this.formdata.hdfsurl = res.data.hdfsurl
      this.formdata.hdfsid = res.data.hdfsid
      console.log('success')
    },

    // 文件上传失败时的钩子
    handleError(err, file, fileList) {
      console.log(err)
      this.$notify.error({
        title: 'error',
        message: `fiel upload faild`
      })
    },

    submitForm(formdata) {
      console.log(formdata)
      delete formdata['hdfsurl']
      imgUpdate(formdata).then(response => {
        console.log(response.data)
        this.$emit('showFinalStep', this.formdata)
      })
      this.$store.commit('retrieval/SET_STEP', 2)
    },
    showFinal(payload) {
      console.log('hellod')
      this.orderStatus = 2
      this.final = payload
    }
  }
}
</script>

<style scoped>

</style>

