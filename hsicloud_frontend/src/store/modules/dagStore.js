
const dagStore = {
  state: {
    DataAll: { 
      
"edges": [],

"nodes": [] 
},
    svgSize: 1
}, 
  actions: {
    openGraph: ({ commit }, model_id) => { // 打开图
    },
    showGraph: ({ commit }, model_id) => { // 打开暂存的图
    },
    newGraph: ({ commit }, params) => { // 新增图
    },
    addEdge: ({ commit }, { desp }) => { // 增加边
      console.log(desp)
      commit('ADD_EDGE_DATA', desp)
    },
    delEdge: ({ commit }, { id }) => { // 删除边
      commit('DEL_EDGE_DATA', id)
    },
    moveNode: ({ commit }, params) => { // 移动点的位置
      commit('MOVE_NODE_DATA', params)
    },
    addNode: ({ commit }, params) => { // 增加节点
      commit('ADD_NODE_DATA', params)
    },
    delNode: ({ commit }, { id }) => { // 删除节点
      commit('DEL_NODE_DATA', id)
    },
    saveGraph: () => {
    },
    changeSize: ({ commit }, action) => { // 改变size
      commit('CHANGE_SIZE', action)
    }
  },
  mutations: {
    CHANGE_SIZE: (state, action) => {
      switch (action) {
        case 'init':
          state.svgSize = 1
          break
        case 'expend':
          state.svgSize += 0.1
          break
        case 'shrink':
          state.svgSize -= 0.1
          break
        default: state.svgSize = state.svgSize
      }
      sessionStorage['svgScale'] = state.svgSize
    },
    UPDATE_DATA: (state, data) => {
      state.DataAll = data;
    },
    MOVE_NODE_DATA: (state, params) => {
      let _DataAll = state.DataAll
      _DataAll.nodes.forEach((item, i) => {
        if (item.id === params.id) {
          item.pos_x = params.pos_x
          item.pos_y = params.pos_y
        }
      })
    },
    ADD_EDGE_DATA: (state, desp) => {
      let _DataAll = state.DataAll
      _DataAll.edges.push({
        ...desp,
        id: state.DataAll.edges.length + 10
      })
      /**
       * 检测是否成环
       **/
      let isCircle = false
      const { dst_node_id } = desp // 出口 入口id
      const checkCircle = (dst_node_id, nth) => {
        if (nth > _DataAll.nodes.length) {
          isCircle = true
          return false
        } else {
          _DataAll.edges.forEach(item => {
            if (item.src_node_id === dst_node_id) {
              console.log('目标节点是', item.src_node_id, '次数为', nth)
              checkCircle(item.dst_node_id, ++nth)
            }
          })
        }
      }
      checkCircle(dst_node_id, 1)
      if (isCircle) {
        _DataAll.edges.pop()
        alert('禁止成环')
      }
    },
    DEL_EDGE_DATA: (state, id) => {
      let _edges = []
      state.DataAll.edges.forEach((item, i) => {
        if (item.id !== id) {
          _edges.push(item)
        }
      })
      state.DataAll.edges = _edges
    },
    DEL_NODE_DATA: (state, id) => {
      let _edges = []
      let _nodes = []
      state.DataAll.edges.forEach(item => {
        if (item.dst_node_id !== id && item.src_node_id !== id) {
          _edges.push(item)
        }
      })
      state.DataAll.nodes.forEach(item => {
        if (item.id !== id) {
          _nodes.push(item)
        }
      })
      state.DataAll.edges = _edges
      state.DataAll.nodes = _nodes
    },
    ADD_NODE_DATA: (state, params) => {
      let _nodes = state.DataAll.nodes
      _nodes.push({
        ...params.desp,
        id: state.DataAll.nodes.length + 100,
        in_ports: [0, 1, 2, 3, 4],
        out_ports: [0, 1, 2, 3, 4]
      })
    }
  }
};

export default dagStore;
