import React, {useEffect, useState} from "react";
import Table from 'react-bootstrap/Table'
import Product from "./product";
import TablePaging from "./tablePaging";
import Pagination from '@material-ui/lab/Pagination';

const ProductTable = (props) => {

return (
<div>
    <Table striped bordered hover variant="light" display="flex">
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Brand</th>
                <th>Proteins</th>
                <th>Fats</th>
                <th>Carbs</th>
                <th>Calories</th>
                {props.enableAddButton && (
                <th></th> )}
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
                        mealId = {props.mealId}
                        updateMeal = {props.updateMeal}
                        hideTable = {props.hideTable}
                    />
            ))} 
        </tbody>
    </Table>
    {props.enablePaging && (
    <div className="paging">
        <TablePaging 
                active = {props.active}
                totalPages = {props.totalPages}
                pageOnChangeEvent = {props.pageOnChangeEvent}
        />  
    </div>)}
</div>
)
}  

export default ProductTable;