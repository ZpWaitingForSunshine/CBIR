import React from 'react'
import {Form, Icon,Input, Button,Checkbox} from 'antd'
import axios from '../../axios/index'
import Footer from '../../components/Footer'
import Utils from '../../utils/utils'
import './index.less'
const FormItem = Form.Item;

export default class Login extends React.Component {
    state = {};

    componentDidMount() {//每次进入登录页清除之前的登录信息
        
    }

    loginReq = (params) => {
        window.location.href = '/#/';
    };

    render() {
        return (
            <div className="login-page">
                <div className="login-header">
                    <div className="logo">
                        <img src="/assets/logo-ant.svg" />
                        多光谱遥感图像融合系统
                    </div>
                </div>
                <div className="login-content-wrap">
                    <div className="login-content">
                        <div className="word"> </div>
                        <div className="login-box">
                            <div className="error-msg-wrap">
                                <div
                                    className={this.state.errorMsg?"show":""}>
                                    {this.state.errorMsg}
                                </div>
                            </div>
                            <div className="title"></div>
                            <LoginForm ref="login" loginSubmit={this.loginReq}/>
                        </div>
                    </div>
                </div>
                <Footer/>
            </div>
        )
    }
}

class LoginForm extends React.Component {
    state = {};

    loginSubmit = (e)=> {
        e && e.preventDefault();
        const _this = this;
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                var formValue = _this.props.form.getFieldsValue();
                _this.props.loginSubmit({
                    username: formValue.username,
                    password: formValue.password
                });
            }
        });
    };

    checkUsername = (rule, value, callback) => {
        var reg = /^\w+$/;
        if (!value) {
            callback('请输入用户名!');
        } else if (!reg.test(value)) {
            callback('用户名只允许输入英文字母');
        } else {
            callback();
        }
    };

    checkPassword = (rule, value, callback) => {
        if (!value) {
            callback('请输入密码!');
        } else {
            callback();
        }
    };

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <Form className="login-form">
                <FormItem>
                    {getFieldDecorator('username', {
                        initialValue:'',
                        rules: [{validator: this.checkUsername}]
                    })(
                        <Input placeholder="用户名" prefix={<Icon type="user" style={{ fontSize: 13 }} />}/>
                    )}
                </FormItem>
                <FormItem>
                    {getFieldDecorator('password', {
                        initialValue:'',
                        rules: [{validator: this.checkPassword}]
                    })(
                        <Input  prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="密码" wrappedcomponentref={(inst) => this.pwd = inst } />
                    )}
                </FormItem>
                <FormItem>
                {getFieldDecorator('remember', {
                                valuePropName: 'checked',
                                initialValue: true,
                            })(
                                <Checkbox>记住我</Checkbox>
                            )}
                </FormItem>
                <FormItem>
                    <Button type="primary" onClick={this.loginSubmit} className="login-form-button">
                        登录
                    </Button>
                    <p style={{display: 'flex', justifyContent: 'space-between'}}>
                               <a href="/form/reg#/form/reg"><span > 现在就去注册!</span></a> 
                                
                            </p>
                </FormItem>
            </Form>
        )
    }
}
LoginForm = Form.create({})(LoginForm);
