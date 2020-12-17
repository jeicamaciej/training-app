import React, { useState, useEffect } from "react";
import Table from 'react-bootstrap/Table'
import ProductAddButton from "./productAddButton"

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
                    <ProductAddButton
                        productId = {props.productId}
                    />
                )}
            </td>
        </tr>
    )
}
export default Product;