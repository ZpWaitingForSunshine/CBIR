<template>
  <div class="whole">
    <p class="specialtitle">算法详情</p>
    <p class="titile" display:inline-block>名称：</p>
    <p class="content1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{parametername}}</p>
    <p class="titile">说明:</p>
    <p class="content2">&nbsp;&nbsp;&nbsp;&nbsp;{{message}}</p>
    <p class="titile">运行状况:</p>
    <p class="content2">
      <a href="http://www.w3school.com.cn/index.html">This is a link to xml:{{xmllink}}</a>
    </p>

    <button class="button button1" type="button" @click="goback()">返回</button>
    <button class="button button2" type="button" @click="compute()">运算</button>
  </div>
</template>
<script>
import { fetchOne } from "@/api/components";

export default {
  data() {
    return {
      parametername: "",
      message: "",
      xmllink: "",
      cid: ""
    };
  },
  created() {
    const id = this.$route.params && this.$route.params.cid;
    this.cid = id;
    this.getDetil(id);
  },
  methods: {
    goback() {
      this.$router.push({ path: "/s_algorithm/List" });
    },
    compute() {
      this.$router.push({ path: "/s_algorithm/detail/" + this.cid });
    },
    getDetil(cid) {
      let params = { cid: cid };
      // this.listLoading = true
      fetchOne(params).then(response => {
        this.detail = response.data;
        this.parametername = response.data.name; // name
        this.message = response.data.detail;
        this.xmllink = response.data.xmllink;
      });
    }
  }
};
</script>
<style lang="scss">
.whole {
}
.specialtitle {
  display: inline-block;
  text-decoration: underline;
  font-size: 40px;
  color: #4a9ff9;
  font-style: italic;
  text-align: left;
}
.titile {
  font-size: 30px;
  color: #4a9ff9;
  font-style: italic;
  font-weight: bold;
  border-style: outset;
  border-top-style: none;
  border-width: 15px;
  text-align: left;
  //width:200px;
  // border-style: solid double;
}
.content1 {
  font-size: 20px;
  text-align: left;
}
.content2 {
  font-size: 20px;
  text-align: left;
}
.button {
  background-color: #4caf50; /* Green */
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
}
.button1 {
  background-color: white;
  color: black;
  border: 2px solid #008cba;
  position: absolute;
  left: 1300px;
}

.button1:hover {
  background-color: #008cba;
  color: white;
}

.button2 {
  background-color: white;
  color: black;
  border: 2px solid #555555;
  position: absolute;
  left: 1410px;
}

.button2:hover {
  background-color: #555555;
  color: white;
}
table {
  border-collapse: collapse;
}

table,
td,
th {
  border: 1px solid black;
}
</style>
