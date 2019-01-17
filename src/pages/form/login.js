import React from "react";
import { Card, Form, Input, Button, message, Icon, Checkbox } from "antd";
import './form.less'
const FormItem = Form.Item;
class FormLogin extends React.Component{

    handleSubmit = ()=>{
        let userInfo = this.props.form.getFieldsValue();
        this.props.form.validateFields((err,values)=>{
            if(!err){
                message.success(`${userInfo.userName} 恭喜你，登录成功！当前密码为：${userInfo.userPwd}`)
            }
        })
    }

    render(){
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="login">
                 
                <div className="login-form" >
                    <div className="login-logo">
                        <span style={{fontSize:20}}>多光谱遥感图像融合系统</span>
                        
                    </div>
                    <Form  style={{maxWidth: '300px'}}>
                        <FormItem>
                            {getFieldDecorator('userName', {
                                rules: [{ required: true, message: '请输入用户名!' }],
                            })(
                                <Input prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('password', {
                                rules: [{ required: true, message: '请输入密码!' }],
                            })(
                                <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('remember', {
                                valuePropName: 'checked',
                                initialValue: true,
                            })(
                                <Checkbox>记住我</Checkbox>
                            )}
                            <span className="login-form-forgot" href="" style={{float: 'right'}}>忘记密码</span>
                            <Button type="primary" htmlType="submit" className="login-form-button" style={{width: '100%'}} onSubmit={this.handleSubmit}>
                                登录
                            </Button>
                            <p style={{display: 'flex', justifyContent: 'space-between'}}>
                               <a href="/form/reg#/form/reg"><span >或 现在就去注册!</span></a> 
                                
                            </p>
                        </FormItem>
                    </Form>
                </div>
            </div>
        );
    }
}
export default Form.create()(FormLogin);