import React, { Component } from 'react'
import BookDataService from '../service/BookDataService.js';

class BooksComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            books: [],
            checkedBoxes: [],
            value: '',
            selectedItem: 'http://localhost:8080/books/resource?path=/home/r00t/books/DevOps/dockerupandrunning.pdf'
        }

        this.refreshBooks = this.refreshBooks.bind(this);
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


    handleCheckbox = (e, s) => {

        const checkedBoxes = [...this.state.checkedBoxes];

        if(e.target.checked) {
          checkedBoxes.push(s);

          this.state.selectedItem = 'http://localhost:8080/books/resource?path=' + s.path;

        } else {
          const index = checkedBoxes.findIndex((ch) => ch.name === s.name);
          checkedBoxes.splice(index, 1);
        }
        this.setState({checkedBoxes});
    }


    listItems = () => this.state.books.map(item => (

        <li key={item.name + Math.random().toString(10)}>
                <input type="checkbox"
                       value={item.path}
                       checked = {this.state.checkedBoxes.find((ch) => ch.name === item.name)}
                       onChange = {(e) => this.handleCheckbox(e, item)}
                />
                <a data-path={item.path}
                    href={"http://localhost:8080/books/resource?path=" + item.path}
                    target="_blank">{item.name}
                </a>
        </li>
    ));

    render() {

        return (
            <div className="App">

{/*                 <object type="application/pdf" data={this.state.selectedItem}> */}
{/*                   <p>Insert your error message here, if the PDF cannot be displayed.</p> */}
{/*                 </object> */}

                <ul className="container">{this.listItems()}</ul>
            </div>
       );
    }
}

export default BooksComponent