import React, { Component } from 'react';

import './App.css';
import Table from './Components/Table';
import Repository from "./Repository/repository";
import {HashRouter as Router, Route, Routes} from "react-router-dom";
import Categories from "./Components/Categories";
import NavbarComponent from "./Components/NavbarComponent";
// import Categories from './Components/Categories';

class App extends Component {
  constructor(props){
    super(props);
    this.state={
      books: [],
      authors:[]
    }
  }


  componentDidMount() {
    this.loadBooks();
  }

  loadBooks = () =>{
    Repository.findAll().then((data)=>{
      this.setState({
        books:data.data
      })
    })
  }
  onRent = (id) =>{
    Repository.rentBook(id).then(()=>{this.loadBooks()});

  }

  onDelete = (id) =>{
    Repository.deleteBook(id).then(()=>{this.loadBooks()});
    this.loadBooks();
  }

  addBook = (name, category, author, available)=>{
    Repository.addBook(name,category,author,available).then(()=>{this.loadBooks()})
  }
  editBook=(id, name, category, author, available)=>{
    Repository.update(id,name,category,author,available).then(()=>{this.loadBooks()})
  }

  render() {

    // const bookTable = <Table authors={this.state.authors}
    //                          books={this.state.books}
    //                          onRent={this.onRent}
    //                          onReturn={this.onReturn}
    //                          addBook={this.addBook}
    //                          editBook={this.editBook}
    //                          onDelete={this.onDelete}/>;

    return (

        <div className="App">
          <Router>
            <NavbarComponent/>

            <Routes>
              <Route path={"/books"} exact element={<Table authors={this.state.authors}
                                                           books={this.state.books}
                                                           onRent={this.onRent}
                                                           onReturn={this.onReturn}
                                                           addBook={this.addBook}
                                                           editBook={this.editBook}
                                                           onDelete={this.onDelete}/>}/>
              <Route path={"/"} exact element={<Table authors={this.state.authors}
                                                      books={this.state.books}
                                                      onRent={this.onRent}
                                                      onReturn={this.onReturn}
                                                      addBook={this.addBook}
                                                      editBook={this.editBook}
                                                      onDelete={this.onDelete}/>}/>
              <Route path={"/categories"} exact element={<Categories/>}/>

            </Routes>
          </Router>


        </div>
    );
  }
}

export default App;
