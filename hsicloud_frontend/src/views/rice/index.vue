<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="Title">
        <template slot-scope="scope">
          <span>{{ scope.row.filename }}</span>
        </template>
      </el-table-column>

<!-- 光谱 -->
      <el-table-column width="120px" align="center" label="Bands">
        <template slot-scope="scope">
          <span>{{ scope.row.bands }}</span>
        </template>
      </el-table-column>

<!-- 光谱格式 -->
      <el-table-column width="140px" align="center" label="FileType">
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

      <el-table-column width="100px" align="center" label="Image Bin">
        <template slot-scope="scope">
          <span>{{ scope.row.imagebin }}</span>
          <!-- <svg-icon v-for="n in +scope.row.importance" :key="n" icon-class="star" class="meta-item__icon" /> -->
        
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="Interleave">
        <template slot-scope="scope">
          <span>{{ scope.row.interleave }}</span>
          <!-- <svg-icon v-for="n in +scope.row.importance" :key="n" icon-class="star" class="meta-item__icon" /> -->
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Datatype" width="110">
        <template slot-scope="{row}">
          <span>{{ row.datatype }}</span>
          <!-- <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag> -->
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
              Edit
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
      list: [{
        id:1,
        importance:2,
        timestamp:"2019",
        author:"penn",
        status:"success",
        title:"T30-550-10-850-2019-04-30-17-09-06"
      }],
      total: 1,
      listLoading: true,
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
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data
        console.log(response)
        this.total = response.total
        this.listLoading = false
      })
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
