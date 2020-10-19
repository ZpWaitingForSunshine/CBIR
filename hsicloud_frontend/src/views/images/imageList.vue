<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="50px">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="Title">
        <template slot-scope="scope">
          <span>{{ scope.row.filename }}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column width="100px" align="center" label="Thumbnail" height="50px;">
        <template slot-scope="scope">
          <img :src="scope.row.thumbnailurl" height="50px;"/>
        </template>
      </el-table-column> -->

<!-- 光谱 -->
      <el-table-column width="80px" align="center" label="Bands">
        <template slot-scope="scope">
          <span>{{ scope.row.bands }}</span>
        </template>
      </el-table-column>

<!-- 光谱格式 -->
      <el-table-column width="140px" align="center" label="File Type">
        <template slot-scope="scope">
          <span>{{ scope.row.filetype }}</span>
          <!-- <svg-icon v-for="n in +scope.row.importance" :key="n" icon-class="star" class="meta-item__icon" /> -->
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="Samples">
        <template slot-scope="scope">
          <span>{{ scope.row.samples }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="Lines">
        <template slot-scope="scope">
          <span>{{ scope.row.rows }}</span>
          <!-- <svg-icon v-for="n in +scope.row.importance" :key="n" icon-class="star" class="meta-item__icon" /> -->
        
        </template>
      </el-table-column>
      <el-table-column width="100px" align="center" label="Interleave">
        <template slot-scope="scope">
          <span>{{ scope.row.interleave }}</span>
          <!-- <svg-icon v-for="n in +scope.row.importance" :key="n" icon-class="star" class="meta-item__icon" /> -->
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Data Type" width="110">
        <template slot-scope="{row}">
          <span>{{ row.datatype }}</span>
      
        </template>
      </el-table-column>

      <el-table-column min-width="300px" label="HDFS URL">
        <template slot-scope="{row}">
          <span>{{ row.hdfs }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Actions" width="120">
        <template slot-scope="">
          <router-link :to="'/rice/detail'">
         
             <el-button type="primary" size="small" icon="el-icon-edit">
              分享
            </el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>

<script>
import { fetchList } from '@/api/article'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'ArticleList',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      // list: [{
      //   id:1,
      //   filename:2,
      //   bands:31,
      //   filetype:"ENVI Standard",
      //   samples:1220,
      //   lines:1024,
      //   datatype:12,
      //   thumail:"https://dfdf.jpg",
      //   interleave:"bsq",
      //   hdfs:"hdfs://10.10.10.96/rice/images/"
      // },{
      //   id:1,
      //   filename:2,
      //   bands:31,
      //   filetype:"ENVI Standard",
      //   samples:1220,
      //   lines:1024,
      //   datatype:12,
      //   interleave:"bsq",
      //   hdfs:"hdfs://10.10.10.96/rice/images/"
      // }],
      list: [{
        id:1,
        filename:"T17-09-38",
        bands:31,
        filetype:"ENVI Standard",
        samples:1220,
        lines:1024,
        datatype:12,
        thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/E6CC20972E4A44ECAD524C2229591C48/525",
        interleave:"bsq",
        hdfs:"hdfs://10.10.10.96/rice/images/T17-09-38"
      },{
        id:2,
        filename:"T17-16-04",
        bands:31,
        filetype:"ENVI Standard",
        samples:1220,
        lines:1024,
        datatype:12,
        thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/7E1830FBB35B468DB9AD211690A8E4E2/527",
        interleave:"bsq",
        hdfs:"hdfs://10.10.10.96/rice/images/T17-16-04"
      },{
        id:3,
        filename:"T17-16-18",
        bands:31,
        filetype:"ENVI Standard",
        samples:1220,
        lines:1024,
        datatype:12,
        thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/F0237C66AE52424993DC79FE2FA7BED6/519",
        interleave:"bsq",
        hdfs:"hdfs://10.10.10.96/rice/images/T17-16-18"
},{
        id:4,
        filename:"W17-09-06",
        bands:31,
        filetype:"ENVI Standard",
        samples:1220,
        lines:1024,
        datatype:12,
        thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/8507488E8D444317BD9E8FF1F3992443/529",
        interleave:"bsq",
        hdfs:"hdfs://10.10.10.96/rice/images/W17-09-06"
      },{
        id:5,
        filename:"c350",
        bands:192,
        filetype:"ENVI Standard",
        samples:350,
        lines:350,
        datatype:2,
        thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/B8C064A034BB4AC59B9733A7EFEA0004/518",
        interleave:"bil",
        hdfs:"hdfs://10.10.10.96/rice/images/c350"
      },{
        id:6,
        filename:"c20",
        bands:192,
        filetype:"ENVI Standard",
        samples:350,
        lines:350,
        datatype:2,
        thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/6FC0431A589046599D351F96E47ACA5B/520",
        interleave:"bip",
        hdfs:"hdfs://10.10.10.96/rice/images/c20"
        }
      ],

      total: 6,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 20
      }, 
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      fetchList().then(response => {
        this.list = response.data
        console.log(response)
        this.total = response.total
        // this.listLoading = false
      })
      // this.listLoading = true
      // fetchList(this.listQuery).then(response => {
      //   this.list = response.data
      //   console.log(response)
      //   this.total = response.total
      //   this.listLoading = false
      // })
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
