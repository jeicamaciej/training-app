import React, { useState, useEffect } from "react";
import Table from 'react-bootstrap/Table'

const Product = (props) => {

    return (
        <tr>
            <td>{props.productId}</td>               
            <td>{props.productName}</td>               
            <td>{props.productBrands}</td>               
            <td>{props.productKcal}</td>
            <td>{props.productCarbs}</td>
            <td>{props.productProteins}</td>
            <td>{props.productFats}</td>               
        </tr>
    )

}
export default Product;