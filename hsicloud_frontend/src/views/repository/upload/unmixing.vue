<template>
  <div class="dashboard-editor-container">
    <el-row>
      <el-form ref="form" :model="form" label-width="200px">
        <el-col :span="12" class="left">
          <h3>Image ID</h3>
          <h4>{{ form.imageid }}</h4>

          <h3>Algorithm Selection</h3>
          <el-form-item label="Master" hidden>
            <el-input v-model="form.imageid"></el-input>
          </el-form-item>
          <el-form-item label="Endmember Extraction">
            <el-select v-model="form.extraction" placeholder="">
              <el-option label="PPI" value="ppi"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Signature Comparison">
            <el-select v-model="form.compare" placeholder="">
              <el-option label="SAD" value="sad"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Abundance Fractions">
            <el-select v-model="form.abundance" placeholder="">
              <el-option label="SCLS" value="scls"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12" class="left">
          <h3>Resource Allocation</h3>
          <el-form-item label="Master">
            <el-input v-model="form.master"></el-input>
          </el-form-item>
          <el-form-item label="Diver Memory(G)">
            <el-input v-model="form.drivermemory"></el-input>
          </el-form-item>
          <el-form-item label="Executor Memory(G)">
            <el-input v-model="form.executormemory"></el-input>
          </el-form-item>
          <el-form-item label="Executor-Cores">
            <el-input v-model="form.executorcores" type="number"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">Submit</el-button>
          </el-form-item>
          <el-form-item>
            <p v-show="submissionid != null">
              You can see the job(submissionid)<b>{{ submissionid }}</b>
              running state in
              <a href="https://10.10.10.110:8080"> Spark UI</a>.
            </p>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>

<script>
import { unmixing } from "@/api/unmixing";

export default {
  name: "Unmixing",
  data() {
    return {
      form: {
        master: "spark://10.10.10.110",
        drivermemory: 4,
        executormemory: 4,
        executorcores: 2,
        imageid: 0,
      },
      submissionid: null,
    };
  },
  watch: {},
  mounted() {
    this.form.imageid = this.$route.query.id;
  },
  computed: {},
  methods: {
    submitForm() {
      console.log(this.form);
      unmixing(this.form).then((res) => {
        console.log(res);
        this.submissionid = res.data;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  background-color: #fff;
  min-height: 100vh;
  padding: 20px 60px 0px;
}
</style>
