import React, {useEffect, useState} from "react";
import Table from 'react-bootstrap/Table'
import Product from "./product";
import TablePaging from "./tablePaging";
import tablePaging from "./tablePaging";

const ProductTable = (props) => (
<div>
    <Table striped bordered hover>
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Brand</th>
                <th>Proteins</th>
                <th>Fats</th>
                <th>Carbs</th>
                <th>Calories</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            {props.products.map((p) => (                     
                    <Product key = {p.id}
                        productId = {p.id}
                        productName = {p.productName}           
                        productBrands = {p.brands}              
                        productKcal = {p.kcal}
                        productCarbs = {p.carbs}
                        productProteins = {p.proteins}
                        productFats = {p.fats}
                        enableAddButton = {props.enableAddButton}
                    />
            ))} 
        </tbody>
    </Table>
    {props.enablePaging && (
    <TablePaging 
            active = {props.active}
            totalPages = {props.totalPages}
            pageOnChangeEvent = {props.pageOnChangeEvent}
    />     )}
</div>
)   

export default ProductTable;