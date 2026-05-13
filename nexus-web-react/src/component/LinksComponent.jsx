import React, { Component } from 'react'
import LinkDataService from '../service/LinkDataService.js';
import DataTable from "react-data-table-component";

class LinksComponent extends Component {

    constructor(props) {

        super(props)
        this.state = {
            links: [],
            checkedBoxes:[],
            value: '',
            activePage: 1,
            totalPages: null,
            itemsCountPerPage:10,
            totalItemsCount:0
        }
    }

    componentDidMount() {

        this.refreshLinks(this.state.activePage, this.state.itemsCountPerPage);
    }

    handleChange = (event) => {

        this.setState({value: event.target.value});
    }

    handleSubmit = (event) => {

        LinkDataService.saveLink(this.state.value).then((response) => {
            this.refreshLinks(this.state.activePage, this.state.itemsCountPerPage);
        });

        this.setState({value: ''});
        event.preventDefault();
    }

    handleDelete = (event) => {

        event.preventDefault();

        const checkedOptions = this.state.checkedBoxes.map(s => s.id);
        LinkDataService
            .deleteLinks(checkedOptions)
            .then(() => {
                this.refreshLinks(this.state.activePage, this.state.itemsCountPerPage);
                this.setState({checkedBoxes: []});
            });
    }

    handleDownload = (e, s) => {

        LinkDataService.downloadLinks().then((response) => {

            console.log(response);

            // create file in browser
            const json = JSON.stringify(response.data, null, 2);
            const blob = new Blob([json], { type: "application/json" });
            const href = URL.createObjectURL(blob);

            // create "a" HTLM element with href to file
            const link = document.createElement("a");
            link.href = href;
            link.download = "links.json";
            document.body.appendChild(link);
            link.click();

            // clean up "a" element & remove ObjectURL
            document.body.removeChild(link);
            URL.revokeObjectURL(href);

        });

         e.preventDefault();
    }

    handleCheckbox = ({ selectedRows }) => {

        this.setState({checkedBoxes: selectedRows});
    }

    handlePageChange = (page) => {

        this.setState(
            {
                activePage: page
            },
            () => {
                this.refreshLinks(
                    this.state.activePage,
                    this.state.itemsCountPerPage
                );
            }
        );
    };

    handleRowsPerPageChange = (newPerPage) => {

        this.setState(
            {
                itemsCountPerPage: newPerPage,
                activePage: 1
            },
            () => {
                this.refreshLinks(
                    this.state.activePage,
                    this.state.itemsCountPerPage
                );
            }
        );
    };

    refreshLinks = (
        page = 1,
        rowPerPage = this.state.itemsCountPerPage
    ) => {

        LinkDataService.retrieveLinks(page - 1, rowPerPage)
            .then((response) => {

                this.setState({
                    totalPages: response.data.totalPages,
                    totalItemsCount: response.data.totalElements,
                    itemsCountPerPage: response.data.size,
                    links: response.data.content
                });

            })
            .catch(error => {
                console.log(error);
            });
    };

    render() {

        const columns = [
            {
                name: 'ID',
                selector: row => row.id,
                sortable: true,
                width: '100px'
            },
            {
                name: 'Link',
                selector: row => row.link,
                sortable: true,
                cell: row => (
                    <a
                        href={row.link}
                        target="_blank"
                        rel="noreferrer"
                    >
                        {row.link}
                    </a>
                )
            }
        ];

        return (

            <div className="app">

                <fieldset>

                    <form onSubmit={this.handleSubmit}>

                        <input
                            type="text"
                            placeholder="Copy a link ..."
                            value={this.state.value}
                            onChange={this.handleChange}
                        />

                        <input
                            type="submit"
                            value="add"
                            disabled={!this.state.value.trim().length}
                        />

                        <input
                            type="button"
                            value="download"
                            onClick={this.handleDownload}
                        />

                        <input
                            type="button"
                            value="delete"
                            onClick={this.handleDelete}
                            disabled={!this.state.checkedBoxes.length}
                        />

                    </form>

                </fieldset>

                <DataTable
                    title="Links"
                    columns={columns}
                    data={this.state.links}
                    pagination
                    paginationServer
                    paginationPerPage={this.state.itemsCountPerPage}
                    paginationTotalRows={this.state.totalItemsCount}
                    paginationDefaultPage={this.state.activePage}
                    onChangePage={this.handlePageChange}
                    onChangeRowsPerPage={this.handleRowsPerPageChange}
                    onSelectedRowsChange={this.handleCheckbox}
                    selectableRows
                    highlightOnHover
                    pointerOnHover
                    responsive
                />

            </div>
        );
    }
}

export default LinksComponent