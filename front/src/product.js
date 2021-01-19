import React, { useState, useEffect } from "react";
import Table from 'react-bootstrap/Table'
import ProductAddButton from "./productAddButton"
import AddProduct from "./addProduct"
import {Button, Form, InputGroup,} from "react-bootstrap";

const Product = (props) => {
    
    return (
        <tr>
            <td>{props.productName}</td>               
            <td>{props.productBrands}</td> 
            <td>{props.productProteins}</td>
            <td>{props.productFats}</td>   
            <td>{props.productCarbs}</td>
            <td>{props.productKcal}</td>
            <td>
                {props.enableAddButton && (
                    <AddProduct 
                        productId = {props.productId}
                        mealId = {props.mealId}
                        updateMeal = {props.updateMeal}
                        hideTable = {props.hideTable}
                    />
                )}
            </td>
        </tr>
    )
}
export default Product;