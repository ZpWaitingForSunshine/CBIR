import React from 'react'
import { Row ,Col} from 'antd';
import Header from './component/Header'
import Footer from './component/Footer'
import Navleft from './component/Navleft'
import  './style/common.less'
export default class Admin extends React.Component{
    render(){
        return(
            <Row className="container">
                <Col span={4}  className="nav-left">
                <Navleft/>
                </Col>
                <Col span={20} className="main">
                    <Header/>
                     
                    <Row className="content">
                    Content
                    </Row>
                    <Footer/>
                     
                </Col>
                
                
            </Row>
        );

    }
    
}