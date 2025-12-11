import React, { Component } from 'react';
import InstructorApp from './InstructorApp.jsx';
import AuthenticationService from '../service/AuthenticationService';

class App extends Component {

  componentDidMount() {
    // IMPORTANT: install axios request interceptor once
    AuthenticationService.setupAxiosInterceptors();
  }

  render() {
    return (
      <div className="container">
        <InstructorApp />
      </div>
    );
  }
}

export default App;