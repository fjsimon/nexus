import React, { Component } from 'react'
import LinkDataService from '../service/LinkDataService.js';
import Pagination from "react-js-pagination";

class LinksComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            links: [],
            value: '',
            activePage: 1,
            totalPages: null,
            itemsCountPerPage:null,
            totalItemsCount:null
        }
        this.refreshLinks = this.refreshLinks.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.refreshLinks(this.state.activePage);
    }

    handleChange(pageNumber) {
        this.setState({value: event.target.value});
    }

    handlePageChange(pageNumber) {
        console.log('active page is ${pageNumber}');
        this.setState({activePage: pageNumber});
        this.refreshLinks(pageNumber);
    }

    handleSubmit(event) {
        console.log('A name was submitted: ' + this.state.value);
        LinkDataService.saveLink(this.state.value);
        event.preventDefault();
    }

    refreshLinks(page) {
        LinkDataService.retrieveLinks(page-1)
            .then(
                response => {
                    this.setState({totalPages: response.data.totalPages})
                    this.setState({totalItemsCount: response.data.totalElements})
                    this.setState({itemsCountPerPage: response.data.size})
                    this.setState({links: response.data.content})
                }
            )
    }

    listItems = () => this.state.links.map(item => (
        <li key={item.id}>
            <a href={item.link}>{item.link}</a>
        </li>
    ));

    render() {
        return (
            <div className="App">
                <fieldset>
                <form onSubmit={this.handleSubmit}>
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                    <input type="submit" value="+" />
                </form>
                </fieldset>
                <ul className="container">{this.listItems()}</ul>


                  <div className="d-flex justify-content-center">
                    <Pagination
                     hideNavigation
                     activePage={this.state.activePage}
                     itemsCountPerPage={this.state.itemsCountPerPage}
                     totalItemsCount={this.state.totalItemsCount}
                     pageRangeDisplayed={10}
                     itemClass='page-item'
                     linkClass='btn btn-light'
                     onChange={this.handlePageChange.bind(this)}
                     />
                   </div>

            </div>
       );
    }
}

export default LinksComponent