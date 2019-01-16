import React from 'react'
import About from './about'
import Main from './Main'
import Topic from './topic'
import{HashRouter,Route,Link} from 'react-router-dom'
export default class Home extends React.Component{
    render (){
        return(
           <HashRouter>
               <div>
                   <ul>
                        <li>
                            <Link to ="/">Home</Link>
                             
                            
                        </li>
                        <li>
                        <Link to ="/ab">about</Link>
                        </li>
                        <li>
                        <Link to ="/cs">topic</Link>
                        </li>
                        
                   </ul>
                   <hr/>
                   <Route path="/" component={Main}></Route>
                   <Route path="/ab " component={About}></Route>
                   <Route path="/cs" component={Topic}></Route>     
               </div>
               
            
           </HashRouter> 
            );
        
    }
}