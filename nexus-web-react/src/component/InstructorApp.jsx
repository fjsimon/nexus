import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import LinksComponent  from './LinksComponent.jsx';
import BooksComponent  from './BooksComponent.jsx';
import LoginComponent  from './LoginComponent.jsx';
import LogoutComponent from './LogoutComponent.jsx';
import MenuComponent   from './MenuComponent.jsx';
import FooterComponent   from './FooterComponent.jsx';
import AuthenticationService from '../service/AuthenticationService.js';
import AuthenticatedRoute    from './AuthenticatedRoute.jsx';

class InstructorApp extends Component {


    render() {
        return (
            <>
                <Router>
                    <>
                        <MenuComponent />


                          <ul>
                            <li>
                              <Link to="/">Home</Link>
                            </li>
                            <li>
                              <Link to="/links">Links</Link>
                            </li>
                            <li>
                              <Link to="/books">Books</Link>
                            </li>
                            <li>
                              <Link to="/vue">Vue</Link>
                            </li>
                          </ul>

                        <Switch>
{/*                             <Route path="/" exact component={LoginComponent} /> */}
{/*                             <Route path="/login" exact component={LoginComponent} /> */}
{/*                             <AuthenticatedRoute path="/logout" exact component={LogoutComponent} /> */}
                            <Route path="/" exact component={LinksComponent} />
                            <Route path="/links" exact component={LinksComponent} />
                            <Route path="/books" exact component={BooksComponent} />
                        </Switch>
                        <FooterComponent />
                    </>
                </Router>
            </>
        )
    }
}

export default InstructorApp