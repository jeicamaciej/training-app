import Axios from "axios";
import Table from 'react-bootstrap/Table'
import Product from "./product";
import ProductSearch from "./productSearch";
import ProductTable from "./productsTable";
import React, { useState, useEffect } from "react";

function MealContainer(props){
    const [id, setId] = useState(0);
    const [token, setToken] = useState(localStorage.getItem("token").toString());
    const [products, setProducts] = useState([]);

    useEffect(() => {
        console.log(props.date)
        Axios({
            method: "get",
            url: "http://localhost:8080/api/meal/all/" + props.date,
            headers: {
                // "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                Authorization: `Bearer ${token}`,
            },    
        }).then((response) => {
            console.log("MEAL");
            console.log(response.data);
        });            
    },[products])

    return (
        <div>
            <div>
                <ProductTable
                    products = {products}
                    enableAddButton = {false}
                    enablePaging = {false}                                    
                />
            </div>
            <div>
                <ProductSearch/>
            </div> 
        </div>
    )
}
export default MealContainer;       
