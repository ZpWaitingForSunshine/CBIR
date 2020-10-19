<template>

  <div class="components-container board">
    <Kanban :key="1" :list="list1" :group="group" class="kanban todo" stepnum="2" header-text="组件列表" />
    <div class="addmodel">
    <Kanban :key="2" :list="list2" :group="group" class="kanban working" header-text="The 1 step" />
    <Kanban :key="3" :list="list3" :group="group" v-if="stepnum >= 2" class="kanban working" header-text="The 2 step" />
    <Kanban :key="4" :list="list4" :group="group" v-if="stepnum >= 3" class="kanban working" header-text="The 3 step" />
    <Kanban :key="5" :list="list5" :group="group" v-if="stepnum >= 4" class="kanban working" header-text="The 4 step" />
    <Kanban :key="6" :list="list6" :group="group" v-if="stepnum >= 5" class="kanban working" header-text="The 5 step" />
    <Kanban :key="7" :list="list7" :group="group" v-if="stepnum >= 6" class="kanban working" header-text="The 6 step" />
    <Kanban :key="8" :list="list8" :group="group" v-if="stepnum >= 7" class="kanban working" header-text="The 7 step" />
    <Kanban :key="9" :list="list9" :group="group" v-if="stepnum >= 8" class="kanban working" header-text="The 8 step" />
    <Kanban :key="10" :list="list10" :group="group" v-if="stepnum >= 9" class="kanban working" header-text="The 9 step" />
    </div>
    <div class="funbutton">
    <button class="funbutton2" type="button" @click="add()" >add step</button>
    <button class="funbutton3" type="button" @click="remove()" >remove step</button>
    <button class="funbutton4" type="button" @click="makesure()" >makesure</button>
 </div>
  </div>
</template>


<script>
import Kanban from '@/components/Kanban'
import {fetchdag} from '@/api/components'
//axios
//import http from '@/http.js'

export default {
  name: 'DragKanbanDemo',
  components: {
    Kanban
  },
  data() {
    return {
      group: 'mission',
      stepnum: 1,
      list1: [
        { id: 1 ,name: 'PCA', step:''},
        { id: 2 ,name: 'NMF', step: ''},
        { id: 3 ,name: 'SVD', step: ''},
        { id: 4 ,name: 'PPI', step: ''},
        { id: 5 ,name: 'K-Means',step: ''},
        { id: 6 ,name: 'Liner Regression', step: ''},
        { id: 7 ,name: 'SRC', step: ''},
        { id: 8 ,name: 'NFINDR',step: ''},
        { id: 9 ,name: 'EEI', step: ''}
      ],
      list2: [],
      list3: [],
      list4: [],
      list5: [],
      list6: [],
      list7: [],
      list8: [],
      list9: [],
      list10: [],
      list:[]
    }
    excutorsteps:""
  },
    methods:{
      add(){
        this.stepnum = this.stepnum + 1;
        if(this.stepnum>9){
          alert('Has gone the max stepnum!')
          this.stepnum = this.stepnum - 1;
        }
      },
      remove(){
       var d = this.stepnum;
        switch(d){
          case 1:
             alert('Has gone the min stepnum!');
            break;
          case 2:
            if(this.list3.length > 0)
              alert('You can not remove this step because you have choose at least one module!')
            else
              this.stepnum = this.stepnum - 1;
            break;
          case 3:
            if(this.list4.length > 0)
              alert('You can not remove this step because you have choose at least one module!')
            else
              this.stepnum = this.stepnum - 1;
            break;
          case 4:
            if(this.list5.length > 0)
              alert('You can not remove this step because you have choose at least one module!')
            else
              this.stepnum = this.stepnum - 1;
             break;
          case 5:
            if(this.list6.length > 0)
              alert('You can not remove this step because you have choose at least one module!')
            else
              this.stepnum = this.stepnum - 1;
          break;
          case 6:
            if(this.list7.length > 0)
              alert('You can not remove this step because you have choose at least one module!')
            else
              this.stepnum = this.stepnum - 1;
            break;
          case 7:
            if(this.list8.length > 0)
              alert('You can not remove this step because you have choose at least one module!')
            else
              this.stepnum = this.stepnum - 1;
            break;
          case 8:
            if(this.list9.length > 0)
              alert('You can not remove this step because you have choose at least one module!')
            else
              this.stepnum = this.stepnum - 1;
             break;
          case 9:
            if(this.list10.length > 0)
              alert('You can not remove this step because you have choose at least one module!')
            else
              this.stepnum = this.stepnum - 1;
          break;

        }
      },
      makesure(){
       
        
        for(var i = 0;i < this.list2.length;i++){
              this.list2[i].step = 1;
        }
        for(var i = 0;i < this.list3.length;i++){
              this.list3[i].step = 2;
        }        
        for(var i = 0;i < this.list4.length;i++){
              this.list4[i].step = 3;
        }
        for(var i = 0;i < this.list5.length;i++){
              this.list5[i].step = 4;
        }
        for(var i = 0;i < this.list6.length;i++){
              this.list6[i].step = 5;
        }
        for(var i = 0;i < this.list7.length;i++){
              this.list7[i].step = 6;
        }
        for(var i = 0;i < this.list8.length;i++){
              this.list8[i].step = 7;
        }
        for(var i = 0;i < this.list9.length;i++){
              this.list9[i].step = 8;
        }
        for(var i = 0;i < this.list10.length;i++){
              this.list10[i].step = 9;
        }
        this.list = this.list2.concat(this.list3).concat(this.list4).concat(this.list5).concat(this.list6).concat(this.list7).concat(this.list8).concat(this.list9).concat(this.list10) ;
        
      //   let params={
      //     "list":JSON.stringify(this.list)
      //   }
      //   fetchdag(params).then(response => {
      //     console.log(response)
         
      //  })
      //   this.$router.push({path:'/algorithms/choosedata',query: {methods: this.list}})
      }
         
    }
}
</script>
<style lang="scss">
.board {
  width: 1000px;
  margin-left: 20px;
  display: flex;
  justify-content: space-around;
  flex-direction: row;
  align-items: flex-start;
}
.funbutton{
   display:flex;
   flex-direction: row; 
   //    margin: 700px 0px 0px 0px;
   position:relative;	/*设置相对定位*/

}
.funbutton2{
      width:100px;
      height:50px; 
      border-radius:20px; 
      position:absolute;
      left:100px;
      top:700px;
      //flex-direction: row; 
      background-color: white;
      color: black;
      border: 2px solid rgb(21, 111, 228); 
      -webkit-transition-duration: 0.4s; 
      transition-duration: 0.4s;
}
.funbutton2:hover {
    background-color: rgb(34, 83, 219); 
    color: white;
}
.funbutton3{
      width:100px;
      height:50px; 
      border-radius:20px; 
      position:absolute;
      left:220px;
      top:700px;
      //flex-direction: row; 
      background-color: white;
      color: black;
      border: 2px solid rgb(21, 111, 228); 
      -webkit-transition-duration: 0.4s; 
      transition-duration: 0.4s;
}
.funbutton3:hover {
    background-color: rgb(34, 83, 219); 
    color: white;
}
.funbutton4{
      width:100px;
      height:50px; 
      border-radius:20px; 
      position:absolute;
      left:400px;
      top:700px;
      //flex-direction: row; 
      background-color: white;
      color: black;
      border: 2px solid rgb(21, 111, 228); 
      -webkit-transition-duration: 0.4s; 
      transition-duration: 0.4s;
}
.funbutton4:hover {
    background-color: rgb(34, 83, 219); 
    color: white;
}
.addmodel{
  flex-direction: column;
}
.kanban {
  &.todo {
    .board-column-header {
      background: #4A9FF9;
    }
  }
  &.working {
    .board-column-header {
      background: #f9944a;
    }
  }
  &.done {
    .board-column-header {
      background: #2ac06d;
    }
  }
}
</style>

