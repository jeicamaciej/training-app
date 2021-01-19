import Axios from "axios";
import Table from 'react-bootstrap/Table'
import Product from "./product";
import ProductSearch from "./productSearch";
import ProductTable from "./productsTable";
import React, { useState, useEffect } from "react";


const Meal = (props) => {

    const [name, setName] = useState("");
    const [products, setProducts] = useState([]);
    const [token, setToken] = useState(localStorage.getItem("token").toString());
    const [mappedProducts, setMappedProducts] = useState([]);

    const getProducts = (mealId) => {
        Axios({
            method: "get",
            url: "http://localhost:8080/api/product/get/" + mealId,
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                Authorization: `Bearer ${token}`,
            }
        }).then((response) => {
            setMappedProducts(response.data);
        })
    }

    useEffect(() => {
        getProducts(props.id)
    },[props.products])

    return (
        <div>
            <div>
                {props.id}
            </div>
            <div>
                <ProductTable
                    products = {mappedProducts}
                    enableAddButton = {false}
                    enablePaging = {false}
                    mealId = {props.id}                                    
                />
            </div>
            <div>
                <ProductSearch
                    updateMeal = {props.updateMeal}
                    mealId = {props.id}
                />
            </div>
        </div>
    )
}

export default Meal;