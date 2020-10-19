<template>
  <el-row>
    <el-col :span="6" class="left">
      <h3>HSI information</h3>
      <el-table
        :data="tableData"
        style="width: 100%"
        :row-class-name="tableRowClassName"
      >

        <el-table-column
          prop="name"
          label="property"
          width="180"
        />
        <el-table-column
          prop="data"
          label="value"
          width="180"
        />
      </el-table>
    </el-col>

    <el-col :span="10">
      
     
      <div class="chart-wrapper">
          <h3>Thumbnail</h3>
          <div class="image center" width="100%">
              <img v-bind:src="thumbnailurl" />  <br>
          </div>
        
          <h3>Abundance Information</h3>
          <pie-chart :chartsdata = 'specdata'/>
           
        </div>


    </el-col>
     <el-col :span="8">
      <div class="chart-wrapper">
          
           <h3>Spectral Information</h3>
          <chart height="400px" width="100%" :swss="allImgDate" />
           
        </div>


    </el-col>
  </el-row>
</template>


<script>

import { fetchsignal, getSpecdataByImageID } from '@/api/image'
import PieChart from './components/PieChart'
import LineChart from './components/LineChart'
import chart from '@/components/common/specImg'
export default {
  components:{
    PieChart,
    LineChart,
    chart
  },
  data() {
    return {
      tableData: [],
      items: [],
      thumbnailurl: "",
      specdata:[],
      allImgDate:[]
    }
  },
  created() {
    this.getDetails()
    this.getSpecdataByImageID()
  },
  methods: {
    getDetails() {
      this.items = this.$route.query
      fetchsignal(this.items.userId).then(response => {
        console.log(response)
        var data = response.data
        this.thumbnailurl = data.thumbnailurl
        console.log(data)
        for (const key in data) {
          if (data.hasOwnProperty(key)) {
            if (data[key] != null) {
              this.tableData.push(
                { 'name': key, 'data': data[key] }
              )
            }
          }
        }
      })
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row'
      } else if (rowIndex === 3) {
        return 'success-row'
      }
      return ''
    },
    // 获取图片的光谱信息光谱信息
    getSpecdataByImageID(){
      this.items = this.$route.query
      getSpecdataByImageID(this.items.userId).then(
        res => {
          this.specdata = res.data
          res.data.map(x => {
              this.allImgDate.push({
                // id: x.id,
                reflectStr: x.relectivity.split(','),
                waveLenStr: x.wavelength.split(',')
              })
          })

           
        }
      )
    }
  }
}
</script>

<style>
  .el-table .warning-row {
    background: oldlace;
  }
  .center{
    text-align:center;
  }
  .left{
    padding-left: 20px;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
  .chart{
  position: relative;
  width: 300px;
  height: 300px;
  background:url(http://img3.imgtn.bdimg.com/it/u=2927739851,1695391316&fm=11&gp=0.jpg);
  }
  .content {
  position: relative;
  width: 300px;
  height: 300px;
  border-radius: 4px;
    background-color: red;
  }
</style>