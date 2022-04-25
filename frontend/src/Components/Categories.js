import React, { Component } from 'react';
import axios from "../customAxios/axios";
class Table extends Component {

    constructor(props) {
        super(props);
        this.state={
            categories:[]
        }
    }

    componentDidMount() {
        this.getData();
    }

    getData(){
        axios.get("https://emtlab173148.herokuapp.com/api/book/categories").then((data)=>{
            this.setState({
                categories:data.data
            })
            console.log(data.data)
        })
    }

    render(){

        return(
            <table border={"solid"} align={"center"}>
                <thead>
                <tr>
                    <td>Category</td>
                </tr>
                </thead>
                <tbody>
                {
                    this.state.categories.map((category)=>{
                        return(
                            <tr>
                                <td>{category[0]+category.substring(1).toLowerCase()}</td>
                            </tr>
                        )
                    })
                }

                </tbody>

            </table>





        );
    }
}

export default Table;