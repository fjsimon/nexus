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
                        <AuthenticatedRoute />
                        <Switch>
                            <Route path="/" exact component={LoginComponent} />
                            <Route path="/login" exact component={LoginComponent} />
                            <Route path="/logout" exact component={LogoutComponent} />
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