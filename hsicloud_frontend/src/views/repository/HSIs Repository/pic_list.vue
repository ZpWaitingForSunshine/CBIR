<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">

      <el-table-column align="center" label="id" width="50px">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="filename">
        <template slot-scope="scope">
          <span>{{ scope.row.filename }}</span>
        </template>
      </el-table-column>

      <el-table-column width="90px" align="center" label="samples">
        <template slot-scope="scope">
          <span>{{ scope.row.samples }}</span>
        </template>
      </el-table-column>

      <el-table-column width="90px" align="center" label="rows">
        <template slot-scope="scope">
          <span>{{ scope.row.rows }}</span>
        </template>
      </el-table-column>

      <el-table-column width="90px" align="center" label="bands">
        <template slot-scope="scope">
          <span>{{ scope.row.bands }}</span>
        </template>
      </el-table-column>

      <el-table-column width="110px" label="headeroffset">
        <template slot-scope="scope">
          <span>{{ scope.row.headeroffset }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="filetype" width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.filetype }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="30px" label="datatype">
        <template slot-scope="scope">
          <span>{{ scope.row.datatype }}</span>
        </template>
      </el-table-column>

      <el-table-column width="90px" align="center" label="interleave">
        <template slot-scope="scope">
          <span>{{ scope.row.interleave }}</span>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="createTime">
        <template slot-scope="scope">
          <span>{{ scope.row.createtime+ '000'  | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="thumbnailurl">
        <template slot-scope="scope">
          <img v-bind:src=" scope.row.thumbnailurl" class="image"/>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Actions">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="JumpToList(scope.row.id)">
            View
          </el-button>
           <el-button type="danger" size="small">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :limit.sync="listQuery.limit" :total="total" :page.sync="listQuery.page" @pagination="getList" />
  </div>
</template>

<script>
import { fetchList } from '@/api/image'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
const defaultSettings = require('../../../../src/settings.js')
export default {
  name: 'ArticleList',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger',
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        n: 100,
        offset: 0,
        page: Math.floor(this.n / 10),
        limit: 10,
        server: defaultSettings.server
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        console.log(response)
        this.list = response.data.slice((this.listQuery.page - 1) * this.listQuery.limit, (this.listQuery.page - 1) * this.listQuery.limit + this.listQuery.limit)
        this.total = response.data.length
        this.listLoading = false
      })
    },
    JumpToList(id) {
      this.$router.push({ path: '/repository/List Details', query: { userId: id /* 参数等API*/ }})
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
.image{
  height: 80px;
}
</style>
