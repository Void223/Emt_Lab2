import React, { Component } from 'react';

import './Table.css';

import ReactPaginate from "react-paginate";
class Table extends Component {

    constructor(props){
        super(props);
        this.state={
            size:5,
            page:0,
            oldId:0,
            isEditing:false,
            name:"",
            category:"NOVEL",
            author:1,
            available:""
        }
    }
    delete =(id) =>{
        this.props.onDelete(id);
    }
    edit =(id) =>{
        this.setState({isEditing:true});
    }

    rent=(id)=>{
        console.log("asd");
        this.props.rent(id);

    }

    handleNameChange = (event)=>{
        this.setState({name:event.target.value});
        console.log(this.state);
    }
    handleCategoryChange = (event)=>{

        this.setState({category:event.target.value});
    }
    handleAuthorChange = (event)=>{

        this.setState({author:event.target.value});
    }
    handleAvailableChange = (event)=>{

        this.setState({available:event.target.value});
    }

    savedNew = (event)=>{
        event.preventDefault()
        this.props.addBook(this.state.name,this.state.category,this.state.author,this.state.available)
        this.setState({
            oldId:0,
            isEditing:false,
            name:"",
            category:"NOVEL",
            author:1,
            available:""
        })
    }
    editedOld = (event) =>{
        event.preventDefault()
        this.props.update(this.state.oldId,this.state.name,this.state.category,this.state.author,this.state.available)
        this.setState({
            oldId:0,
            isEditing:false,
            name:"",
            category:"NOVEL",
            author:1,
            available:""
        })
    }
    getButton = ()=>{

        if(this.state.isEditing){

            return (<button class="btn btn-warning" onClick={this.editedOld}>Save</button>)
        }else{
            return (<button class="btn btn-success" onClick={this.savedNew}>Submit</button>)
        }

    }
    handlePageClick = (data)=>{
        let selected = data.selected;
        this.setState({
            page:selected
        })
    };

    getTableContent = (offset, nextPageOffset)=>{

        // this.setState({
        //     author: this.props.author[0].id
        // })
        return  this.props.books.map((book)=>{
            // console.log(book)
            return(
                <tr>
                    <td>{book.name}</td>
                    <td>{book.category}</td>
                    <td>{book.author.name+" "+book.author.surname}</td>
                    <td>{book.availableCopies}</td>
                    <td><button onClick={()=>{
                        this.edit(book.id);
                        this.setState({
                            name:book.name,
                            category:book.category,
                            author:book.author.id,
                            available:book.availableCopies,
                            oldId:book.id,
                        })
                    }

                    } class="btn btn-warning">Edit</button></td>
                    <td><button class="btn btn-danger" onClick={()=>{this.delete(book.id)}}>Delete</button></td>
                    <td><button class="btn btn-primary" onClick={()=>{this.rent(book.id)}}>Mark as taken</button></td>
                    <td><button class="btn btn-primary" onClick={()=>{this.addBook(book.id)}}>Add a new book</button></td>
                </tr>
            )
        }).filter((product, index)=>{
            return index >= offset && index < nextPageOffset
        })
    }
    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length/this.state.size);
        const content = this.getTableContent(offset, nextPageOffset);

        return (
            <div>
                <table class={"table"} border={"solid"}>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Author</th>
                        <th>AvailableCopies</th>
                        <th>Edit</th>
                        <th>Delete</th>
                        <th>Rent</th>
                        <th>Return</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        content
                    }

                    </tbody>
                </table>
                <ReactPaginate
                    nextLabel="next >"
                    onPageChange={this.handlePageClick}
                    pageRangeDisplayed={5}
                    pageCount={pageCount}
                    previousLabel="< previous"
                    renderOnZeroPageCount={null}
                    containerClassName="pagination"
                    breakClassName="page-item"
                    breakLabel={<a className="page-link">...</a>}
                    pageClassName="page-item"
                    previousClassName="page-item"
                    nextClassName="page-item"
                    pageLinkClassName="page-link"
                    previousLinkClassName="page-link"
                    nextLinkClassName="page-link"
                    activeClassName="active"
                />
                <br/><br/>
                <div className="form-group">
                    <form>
                        <div class={"input-grpup-prepend"}>
                            <label htmlFor="name">Name</label>
                            <input id={"name"} class="form-control" name="name" type="text"  value={this.state.name} onChange={this.handleNameChange}/>
                            <label htmlFor="category">Category</label>
                            <select class="form-control" id={"category"}  value={this.state.category} onChange={this.handleCategoryChange}>
                                <option value="NOVEL">Novel</option>
                                <option value="THRILER">Thriler</option>
                                <option value="HISTORY">History</option>
                                <option value="FANTASY">Fantasy</option>
                                <option value="BIOGRAPHY">Biography</option>
                                <option value="CLASSICS">Classics</option>
                                <option value="DRAMA">Drama</option>
                            </select>
                            <label htmlFor="author">Author</label>
                            <select class="form-control" id={"author"} value={this.state.author} onChange={this.handleAuthorChange}>
                                {

                                    this.props.authors.map((author, index) => {

                                        return (<option value={author.id}>{author.name + " " + author.surname}</option>)
                                    })
                                }
                            </select>
                            <label htmlFor="copies">Available copies</label>
                            <input class="form-control" id={"copies"}  name="availableCopies" type="text" value={this.state.available} onChange={this.handleAvailableChange} />
                            <br/><br/>
                            {this.getButton()}
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default Table;