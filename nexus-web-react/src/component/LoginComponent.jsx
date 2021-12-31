import React, { Component } from 'react'
import AuthenticationService from '../service/AuthenticationService';

class LoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            hasLoginFailed: false,
            responseStatusCode: ''
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

        AuthenticationService
            .executeJwtAuthenticationService(this.state.username, this.state.password)
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt(this.state.username, response.data)
                this.props.history.push(`/links`)
            }).catch((error) => {
                this.setState({ hasLoginFailed: true, responseStatusCode: error.response.status })
            })

    }

    render() {
        return (
            <div className="login">
                <h1 className="login-h1">Login</h1>
                <div className="container">

                    {this.state.hasLoginFailed && <div className="alert alert-warning">Error Response Code {this.state.responseStatusCode} </div>}

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