<template>

  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    
    <el-table-column label="ID" width="40">
      <template slot-scope="scope">
        {{ scope.row.id}}
      </template>
    </el-table-column>
    <el-table-column label="名称" width="100" align="center">
      <template slot-scope="scope">
        {{ scope.row.filename }}
      </template>
    </el-table-column>
    <!-- <el-table-column label="缩略图" width="100" align="center">
      
    </el-table-column> -->
    <el-table-column width="100" align="center" label="缩略图" height="50px;">
        <template slot-scope="scope">
          <!-- <h6>{{scope.row.thumbnailurl}}</h6> -->
          <img :src="scope.row.thumbnailurl" height="50px;"/>
        </template>
      </el-table-column>

    <el-table-column label="图片大小" width="80">
      <template slot-scope="scope">
        {{ scope.row.size}}
      </template>
    </el-table-column>
    <el-table-column label="HDFS路径" min-width="100" align="center">
      <template slot-scope="{row}">
          {{ row.hdfs }}
      </template>
    </el-table-column>
    <el-table-column label="查看" width="90" align="center">
      <!-- <template slot-scope="{row}">
        <el-tag :type="row.status | statusFilter">
          {{ row.status }}
        </el-tag>
      </template> -->
      <button type="button" class="el-button el-button--primary el-button--medium"><span>查看</span></button>
    </el-table-column>
  </el-table>
</template>

<script>
import { transactionList } from '@/api/remote-search'
import { fetchList} from '@/api/images'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    },
    orderNoFilter(str) {
      return str.substring(0, 30)
    }
  },
  data() {
    return {
      list:null
      //  list: [{
      //   id:1,
      //   filename:"T17-09-38",
      //   bands:31,
      //   filetype:"ENVI Standard",
      //   samples:1220,
      //   lines:1024,
      //   datatype:12,
      //   thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/E6CC20972E4A44ECAD524C2229591C48/525",
      //   interleave:"bsq",
      //   hdfs:"hdfs://10.10.10.96/rice/images/T17-09-38",
      //   size:"77MB"
      // },{
      //   id:2,
      //   filename:"T17-16-04",
      //   bands:31,
      //   filetype:"ENVI Standard",
      //   samples:1220,
      //   lines:1024,
      //   datatype:12,
      //   thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/7E1830FBB35B468DB9AD211690A8E4E2/527",
      //   interleave:"bsq",
      //   hdfs:"hdfs://10.10.10.96/rice/images/T17-16-04",
      //   size:"77MB"
      // },{
      //   id:3,
      //   filename:"T17-16-18",
      //   bands:31,
      //   filetype:"ENVI Standard",
      //   samples:1220,
      //   lines:1024,
      //   datatype:12,
      //   thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/F0237C66AE52424993DC79FE2FA7BED6/519",
      //   interleave:"bsq",
      //   hdfs:"hdfs://10.10.10.96/rice/images/T17-16-18",
      //   size:"77MB"
      // },{
      //   id:4,
      //   filename:"W17-09-06",
      //   bands:31,
      //   filetype:"ENVI Standard",
      //   samples:1220,
      //   lines:1024,
      //   datatype:12,
      //   thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/8507488E8D444317BD9E8FF1F3992443/529",
      //   interleave:"bsq",
      //   hdfs:"hdfs://10.10.10.96/rice/images/W17-09-06",
      //   size:"77MB"
      // },{
      //   id:5,
      //   filename:"c350",
      //   bands:192,
      //   filetype:"ENVI Standard",
      //   samples:350,
      //   lines:350,
      //   datatype:2,
      //   thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/B8C064A034BB4AC59B9733A7EFEA0004/518",
      //   interleave:"bil",
      //   hdfs:"hdfs://10.10.10.96/rice/images/c350",
      //   size:"44MB"
      // },{
      //   id:6,
      //   filename:"c20",
      //   bands:192,
      //   filetype:"ENVI Standard",
      //   samples:350,
      //   lines:350,
      //   datatype:2,
      //   thumail:"https://note.youdao.com/yws/public/resource/7f772b50210fef5266d3f7ce864db99b/xmlnote/6FC0431A589046599D351F96E47ACA5B/520",
      //   interleave:"bip",
      //   hdfs:"hdfs://10.10.10.96/rice/images/c20",
      //   size:"44MB"
      //   }
      // ],
    }
  },
  created() {
    fetchList().then(response => {
        this.list = response.data
        console.log(response)
        this.total = response.total
        // this.listLoading = false
      })
  },
  methods: {
    fetchData() {
      // transactionList().then(response => {
      //   this.list = response.data.items.slice(0, 18)
      // })
    }
  }
}
</script>
