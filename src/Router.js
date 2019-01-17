import React from 'react'
import { HashRouter, Route, Switch, Redirect} from 'react-router-dom'
import App from './App'
import Login from './pages/login'
import Admin from './admin'
import Home from './pages/home';

import FormLogin from './pages/form/login'
import FormRegister from './pages/form/register'
import BasicTable from './pages/table/basicTable'
import HighTable from './pages/table/highTable'

import Common from './common'


export default class ERouter extends React.Component{

    render(){
        return (
            <HashRouter>
                <App>
                    <Switch>
                        <Route path="/login" component={Login}/>
                        <Route path="/common" render={() =>
                            <Common>
                               
                            </Common>
                        }
                        />
                        <Route path="/" render={()=>
                            <Admin>
                                <Switch>
                                    <Route path='/home' component={Home} />
                                    
                                    <Route path="/form/login" component={FormLogin} />
                                    <Route path="/form/reg" component={FormRegister} />
                                    <Route path="/table/basic" component={BasicTable} />
                                    <Route path="/table/high" component={HighTable} />
                                    
                                    
                                    <Redirect to="/home" />
                                    {/* <Route component={NoMatch} /> */}
                                </Switch>
                            </Admin>         
                        } />
                    </Switch>
                </App>
            </HashRouter>
        );
    }
}