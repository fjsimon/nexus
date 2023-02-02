import React, { Component } from 'react'
import AuthenticationService from '../service/AuthenticationService';
import SyncLoader from "react-spinners/SyncLoader";

class LoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            hasLoginFailed: false,
            responseStatusCode: '',
            loading: false,
            color: '#007FFF',
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

        this.setState({ loading: true });
        AuthenticationService
            .executeJwtAuthenticationService(this.state.username, this.state.password)
            .then((response) => {
                this.setState({ loading: false });
                AuthenticationService.registerSuccessfulLoginForJwt(this.state.username, response.data);
                this.props.history.push(`/links`);
            }).catch((error) => {
                this.setState({ loading: false, hasLoginFailed: true, responseStatusCode: error.response.status })
            })

    }

    render() {
        return (
            <div className="login">
{/*                 <h1 className="login-h1">Login</h1> */}
                <div className="container">

                    <SyncLoader
                        className="loader"
                        color={this.state.color}
                        loading={this.state.loading}
                        size={10}
                        aria-label="Loading Spinner"
                        data-testid="loader"
                    />

                    {this.state.hasLoginFailed && <div className="alert alert-warning">Error Response Code {this.state.responseStatusCode} </div>}

                    {!this.state.loading && <input type="text"
                           className="text-input"
                           placeholder="Username"
                           name="username"
                           value={this.state.username}
                           onChange={this.handleChange} />}

                    {!this.state.loading && <input type="password"
                           className="text-input"
                           placeholder="Password"
                           name="password"
                           value={this.state.password}
                           onChange={this.handleChange} />}

                    {!this.state.loading && <button className="submit" onClick={this.loginClicked}>Login</button>}
                </div>
            </div>
        )
    }
}

export default LoginComponent