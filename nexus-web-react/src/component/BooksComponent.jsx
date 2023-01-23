import React, { Component } from 'react'
import BookDataService from '../service/BookDataService.js';
import DataTable from 'react-data-table-component';

class BooksComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            books: [],
            checkedBoxes: [],
            value: '',
            selectedItem: 'http://localhost:8080/books/resource?path=/home/r00t/books/DevOps/dockerupandrunning.pdf',
            isVisible: false
        }

        this.refreshBooks = this.refreshBooks.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.refreshBooks();
    }

    refreshBooks() {
        BookDataService.retrieveBooks()
            .then(
                response => {
                    this.setState({ books: response.data })
                }
            )
    }

    handleCheckbox = (selected_item) => {

        console.log(selected_item);

        if ( selected_item.selectedCount ) {
            this.setState({isVisible: true});
        } else {
            this.setState({isVisible: false});
        }
    }

    handleChange(event) {

        console.log(event);
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {

        console.log(event);
    }

    render() {

        const columns = [
            {
                name: 'Item Name',
                selector: row => row.name,
            },
            {
                name: 'Local link',
                cell: row => <a data-path={row.path}
                    href={"http://localhost:8080/books/resource?path=" + row.path}
                    target="_blank">{row.name}</a>,
                ignoreRowClick: true,
                allowOverflow: true,
                button: false,
            }
        ];

        return (
            <div className="books">

{/*                 <object type="application/pdf" data={this.state.selectedItem}> */}
{/*                   <p>Insert your error message here, if the PDF cannot be displayed.</p> */}
{/*                 </object> */}

{/*                 <ul className="container">{this.listItems()}</ul> */}

                <DataTable
                    title="Books"
                    columns={columns}
                    data={this.state.books}
                    pagination
                    selectableRows
                    selectableRowsSingle
                    onSelectedRowsChange={this.handleCheckbox}
                />

                <fieldset className={this.state.isVisible ? undefined : 'hidden'}>
                    <form onSubmit={this.handleSubmit}>
                        <input type="text"
                            placeholder="Copy ISBN ..."
                            value={this.state.value}
                            onChange={this.handleChange} />

                        <input type="submit"
                            value="search"
                            disabled={!this.state.value.trim().length}/>
                    </form>
                </fieldset>

            </div>
       );
    }
}

export default BooksComponent