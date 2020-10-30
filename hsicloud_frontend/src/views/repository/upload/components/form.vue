<template>
  <div class="formBar">
    <!-- {{ formdata.filename }}
    {{ formdata }} -->
    <el-form
      :model="formdata"
      :label-position="labelPosition"
      label-width="180px"
    >
      <el-form-item
        v-for="(item, i) in form"
        :key="i"
        prop="title"
        :label="item.inputLabel"
      >
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
          class="upload-demo"
          ref="upload"
          :action="doUpload"
          :multiple="false"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :file-list="fileList"
          :data="formdata"
          :on-success="handlerSuccess"
          :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary"
            >Choose HSI data</el-button
          >
          <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
        </el-upload>
      </el-form-item>
      <el-form-item>
        <br />
        <el-button type="primary" @click="submitForm">Confirm</el-button>
        <div slot="tip">confirm the image's information</div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { imgUpload } from "@/api/image";

export default {
  components: {},
  props: {
    formdata: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      file: null,
      fileList: [],
      labelPosition: "left",
      doUpload: "rest/image/imgupload",
      form: [
        {
          name: "id",
          type: "input",
          inputType: "number",
          disabled: true,
          inputLabel: "ID",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "filename",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "filename",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "samples",
          type: "input",
          inputType: "number",
          disabled: false,
          inputLabel: "samples",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "rows",
          type: "input",
          inputType: "number",
          disabled: false,
          inputLabel: "rows",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "bands",
          type: "input",
          inputType: "number",
          disabled: false,
          inputLabel: "bands",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "headeroffset",
          type: "input",
          inputType: "number",
          disabled: false,
          inputLabel: "headeroffset",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "filetype",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "filetype",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "datatype",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "datatype",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "interleave",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "interleave",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "byteorder",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "byteorder",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "bandnames",
          type: "input",
          inputType: "textarea",
          disabled: false,
          inputLabel: "bandnames",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "wavelength",
          type: "input",
          inputType: "textarea",
          disabled: false,
          inputLabel: "wavelength",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "timeofexposure",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "timeofexposure",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "imagebin",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "imagebin",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "autoexproix",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "autoexproix",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "autoexproiy",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "autoexproiy",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "autoexproiwidth",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "autoexproiwidth",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "autoexproiheight",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "autoexproiheight",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "gain",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "gain",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "exposurecoefficient",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "exposurecoefficient",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "lctftemperature",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "lctftemperature",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "wavebegin",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "wavebegin",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "waveend",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "waveend",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "thumbnailurl",
          type: "input",
          inputType: "string",
          disabled: true,
          inputLabel: "thumbnailurl",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "hdfsurl",
          type: "input",
          inputType: "string",
          disabled: true,
          inputLabel: "hdfsurl",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "size",
          type: "input",
          inputType: "string",
          disabled: true,
          inputLabel: "size",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
        {
          name: "blocknumber",
          type: "input",
          inputType: "string",
          disabled: false,
          inputLabel: "blocknumber",
          modelName: "userName",
          placeholder: "",
          required: true,
          defaultValue: null,
        },
      ],
    };
  },
  watch: {},
  created() {
    console.log("created");
    // console.log(this.formdata)
  },
  mounted() {},
  methods: {
    submitForm(formdata) {
      // imgUpload(formdata).then(response => {
      //   console.log(response.data)
      //   // this.$emit('showFinalStep', this.formdata)
      // })
      this.$refs.upload.submit();
    },

    handleRemove(file, fileList) {
      console.log(file, fileList);
    },

    handlePreview(file) {
      console.log(file);
    },

    handleCchange(file, fileList) {
      // formdata.append("file", file)
    },
    handlerSuccess(res, file, fileList) {
      if (res.message == "200") {
        this.$emit("showFinalStep", res.data);
      }
    },
  },
};
</script>

<style scoped>
</style>

