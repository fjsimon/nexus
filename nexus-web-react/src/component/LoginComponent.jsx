import React, { Component } from 'react'
import AuthenticationService from '../service/AuthenticationService';

class LoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }

        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    loginClicked() {
        //in28minutes,dummy
        // if(this.state.username==='in28minutes' && this.state.password==='dummy'){
        //     AuthenticationService.registerSuccessfulLogin(this.state.username,this.state.password)
        //     this.props.history.push(`/courses`)
        //     //this.setState({showSuccessMessage:true})
        //     //this.setState({hasLoginFailed:false})
        // }
        // else {
        //     this.setState({showSuccessMessage:false})
        //     this.setState({hasLoginFailed:true})
        // }

        AuthenticationService
            .executeJwtAuthenticationService(this.state.username, this.state.password)
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt(this.state.username, response.data)
                this.props.history.push(`/links`)
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasLoginFailed: true })
            })

    }

    render() {
        return (
            <div className="login">
                <h1 className="login-h1">Login</h1>
                <div className="container">
                    {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
                    {/*<ShowLoginSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}

                    {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}

                    {this.state.showSuccessMessage && <div>Login Sucessful</div>}

                    <input type="text"
                           className="text-input"
                           placeholder="Username"
                           name="username"
                           value={this.state.username}
                           onChange={this.handleChange} />

                    <input type="password"
                           className="text-input"
                           placeholder="Password"
                           name="password"
                           value={this.state.password}
                           onChange={this.handleChange} />

                    <button className="submit" onClick={this.loginClicked}>Login</button>
                </div>
            </div>
        )
    }
}

export default LoginComponent