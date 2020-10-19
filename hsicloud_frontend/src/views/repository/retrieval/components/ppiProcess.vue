<template>
  <el-form>
    <el-form-item>
      <label>id</label>
      <el-input v-model="id" :disabled="true" />
    </el-form-item>
    <el-form-item>
      <label>randomEndmemberNum</label>
      <el-input v-model="formdata.randomVecNum" />
    </el-form-item>
    <el-form-item>
      <label>hdfsPath</label>
      <el-input v-model="formdata.baseUrl" />

    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="execPPI(formdata)">exec</el-button>

    </el-form-item>
  </el-form>
</template>
<script>
import { exec } from '@/api/image'
export default {
  props: {
  // id:" "
  },
  data() {
    return {
      formdata: {
        id: '',
        baseUrl: '',
        randomVecNum: '',
        componentName: ''
      }

    }
  },
  methods: {
    execPPI(formdata) {
      console.log('ppi' + formdata)
      this.formdata.componentName = '光谱信息提取'
      exec(formdata).then(response => {
        console.log(response.data)
        // this.$emit('showFinalStep', this.formdata)
      })
    }
  }
}
</script>
