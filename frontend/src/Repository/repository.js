import axios from '../customAxios/axios';
const Repository = {
    findAll: () => {
        return axios.get("/book");
    },
    rentBook: (id) => {
        return axios.get("/book/rent/"+id);
    },

    deleteBook: (id) => {
        return axios.delete("/book/"+id);
    },

    addBook: (name, category, authorId, availableCopies) => {
        return axios.post("/book/",{
            name,category,authorId,availableCopies
        });
    },

    update: (id, name, category, authorId, availableCopies) => {
        console.log("edit book")
        return axios.post("/book/"+id,{
            name,category,authorId,availableCopies
        });
    },

}
export default  Repository;