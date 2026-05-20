import React, { Component } from 'react'
import BookDataService from '../service/BookDataService.js';
import DataTable from 'react-data-table-component';

const ExpandedComponent = ({ data, isbn, onIsbnChange, onSubmit, book }) => (
    <div style={{ padding: "1rem" }}>
        <form onSubmit={onSubmit}>
            <input
                type="text"
                placeholder="Copy ISBN ..."
                value={isbn}
                onChange={onIsbnChange}
            />
            <input
                type="submit"
                value="search"
                disabled={!isbn.trim().length}
            />
        </form>

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


class BooksComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            books: [],
            checkedBoxes: [],
            isbn: '',
            description: 'description',
            selectedItem: 'http://localhost:8080/books/resource?path=/home/r00t/books/DevOps/dockerupandrunning.pdf'
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

    handleCheckbox = ({ selectedRows }) => {
        this.setState({ selectedRow: selectedRows[0] || null });
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
                    expandableRows
                    c={(props) => (
                        <ExpandedComponent
                            data={props.data}
                            isbn={this.state.isbn}
                            onIsbnChange={this.handleChange}
                            onSubmit={this.handleSubmit}
                            book={book}
                        />
                    )}
                    onSelectedRowsChange={this.handleCheckbox}
                />

            </div>
        );
    }

}

export default BooksComponent