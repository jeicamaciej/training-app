import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from 'react-bootstrap/Table'
import Product from "./product";

function Meal(props){
    const [id, setId] = useState(0);
    const [token, setToken] = useState(localStorage.getItem("token").toString());
    const [products, setProducts] = useState();

    return (
        <div>
            <Table striped bordered hover>
                <tbody>
                   <Product
                    productId = {0}
                    productName = {"jedzonko"}           
                    productBrands = {"kfc"}              
                    productKcal = {88}
                    productCarbs = {88}
                    productProteins = {"55"}
                    productFats = {"23"}
                   /> 
                </tbody>   
            </Table> 
        </div>
    )
}
export default Meal;       
