import React, { Component } from 'react'
import BookDataService from '../service/BookDataService.js';
import DataTable from 'react-data-table-component';

class BooksComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            books: [],
            checkedBoxes: [],
            isbn: '',
            description: 'description',
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
        this.setState({isbn: event.target.value});
    }

    handleSubmit(event) {

        console.log(event);

        BookDataService.getBookInfo(this.state.isbn.trim()).then((response) => {
            this.setState({description: JSON.stringify(response.data)});
        });

        event.preventDefault();
    }

    render() {

        const columns = [
            { name: 'Item Name', selector: row => row.name },
            { name: 'Local path', cell: row => (<span style={{ fontFamily: 'monospace' }}>{row.path}</span>) }
        ];

        const parsed = (() => {
            try { return JSON.parse(this.state.description); }
            catch { return null; }
        })();

        let book = null;
        if (parsed) {
            const key = Object.keys(parsed)[0];
            book = parsed[key];
        }

        return (
            <div className="books">

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
                               value={this.state.isbn}
                               onChange={this.handleChange} />

                        <input type="submit"
                               value="search"
                               disabled={!this.state.isbn.trim().length}/>
                    </form>
                </fieldset>

                {book && (
                    <div className="book-card">
                        <img className="cover" src={book.cover?.medium} alt={book.title} />

                        <h2>{book.title}</h2>

                        <p><strong>Author:</strong> {book.authors?.map(a => a.name).join(", ")}</p>

                        <p><strong>Published:</strong> {book.publish_date}</p>

                        {book.notes && (
                            <p className="notes">
                                <strong>Notes:</strong> {book.notes}
                            </p>
                        )}

                        <h3>Subjects</h3>
                        <ul>
                            {book.subjects?.map(s => (
                                <li key={s.name}>{s.name}</li>
                            ))}
                        </ul>

                        <a href={book.url} target="_blank" rel="noopener noreferrer">
                            View on OpenLibrary
                        </a>
                    </div>
                )}

            </div>
        );
    }

}

export default BooksComponent