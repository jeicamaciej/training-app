import Axios from "axios";
import Table from 'react-bootstrap/Table'
import Product from "./product";
import ProductSearch from "./productSearch";
import ProductTable from "./productsTable";
import React, { useState, useEffect } from "react";
import Card from '@material-ui/core/Card';
import Divider from "@material-ui/core/Divider";

import { makeStyles } from '@material-ui/core/styles';
import { CardContent } from "@material-ui/core";

const Meal = (props) => {

    const classes = useStyles();


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
        <div className={classes.root}>
            <Card variant="outlined">
                <CardContent>
                    <div>
                        <ProductTable
                            products = {mappedProducts}
                            enableAddButton = {false}
                            enablePaging = {false}
                            mealId = {props.id}
                        />
                    </div>
                    <Divider className={classes.divider} light />
                    <div>
                        <ProductSearch
                            updateMeal = {props.updateMeal}
                            mealId = {props.id}
                        />
                    </div>
            </CardContent>
            </Card>
        </div>
    )
}

const useStyles = makeStyles((theme) => ({
    root: {
      '& > *': {
        marginTop: theme.spacing(2),
        color: theme.color,
        backgroundColor: 'white',
        margin: "auto",
        width: "70%",
        boxShadow: "0 8px 40px -12px rgba(0,0,0,0.3)",
    },
    ' & >nav > ul > li > button:focus': {
        outline: 'none',
      }    
    },
  }));

export default Meal;