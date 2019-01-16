import React from 'react'
import {HashRouter,Route,Switch} from 'react-router-dom'
import App from './App'
import Admin from './admin'
import Login from './pages/login'
export default class IRouter extends React.Component{

    render(){
        return(
            <HashRouter>
               <App>
               
                  
               </App>
            </HashRouter>
        );
    }
}