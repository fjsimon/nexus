import React, { Component } from 'react'
import LinkDataService from '../service/LinkDataService.js';
import Pagination from "react-js-pagination";

class LinksComponent extends Component {

    constructor(props) {

        super(props)
        this.state = {
            links: [],
            checkedBoxes:[],
            value: '',
            activePage: 1,
            totalPages: null,
            itemsCountPerPage:0,
            totalItemsCount:0
        }
        this.refreshLinks = this.refreshLinks.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
    }

    componentDidMount() {

        this.refreshLinks(this.state.activePage);
    }

    handleChange(pageNumber) {

        this.setState({value: event.target.value});
    }

    handlePageChange(pageNumber) {

        this.setState({activePage: pageNumber});
        this.refreshLinks(pageNumber);
    }

    handleSubmit(event) {

        LinkDataService.saveLink(this.state.value).then((response) => {
            this.refreshLinks(this.state.activePage);
        });

        this.setState({value: ''});
        event.preventDefault();
    }

    handleDelete = (e, s) => {

        const checkedBoxes = [...this.state.checkedBoxes];
        let checkedOptions = checkedBoxes.map(s => s.id);
        LinkDataService.deleteLinks(checkedOptions).then((response) => {
            this.refreshLinks(this.state.activePage);
        });
        e.preventDefault();
    }

    handleCheckbox = (e, s) => {

        const checkedBoxes = [...this.state.checkedBoxes];
        if(e.target.checked) {
          checkedBoxes.push(s);
        } else {
          const index = checkedBoxes.findIndex((ch) => ch.id === s.id);
          checkedBoxes.splice(index, 1);
        }
        this.setState({checkedBoxes});
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

    listItems = () => !!this.state.links && this.state.links.map(item => (
        <li key={item.id}>
            <input type="checkbox"
                   value={item.id}

                   onChange = {(e) => this.handleCheckbox(e, item)}
            />
            <a href={item.link}>{item.link}</a>
        </li>
    ));

    render() {
        return (
            <div className="app">
                <fieldset>
                <form onSubmit={this.handleSubmit}>
                    <input type="text"
                        placeholder="Copy a link ..."
                        value={this.state.value}
                        onChange={this.handleChange} />

                    <input type="submit"
                        value="add"
                        disabled={!this.state.value.trim().length}/>

                    <input type="button"
                        value="delete"
                        onClick={this.handleDelete}
                        disabled={!this.state.checkedBoxes.length}/>
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