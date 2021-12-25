import React, { Component } from 'react'
import { Link, withRouter } from 'react-router-dom'
import AuthenticationService from '../service/AuthenticationService';
import path from 'path';

class MenuComponent extends Component {

    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();

        return (
            <header>
                 <nav className="navbar navbar-expand-md">

                       {!isUserLoggedIn &&
                       <ul className="navbar-nav">
                           <li><Link className="nav-link" to="/login">Login</Link></li>
                       </ul>
                       }
                       {isUserLoggedIn &&
                       <ul className="navbar-nav">
                           <li><Link className="nav-link" to="/links">Links</Link></li>
                           <li><Link className="nav-link" to="/books">Books</Link></li>
                           <li><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link></li>
                       </ul>
                       }

                </nav>
            </header>
        )
    }
}

export default withRouter(MenuComponent)