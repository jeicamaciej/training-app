import React, {useEffect, useRef, useState} from "react";
import axios from "axios";
import {Button, Form, InputGroup,} from "react-bootstrap";
import Axios from "axios";

const AddProduct = (props) => {
    
    const [token, setToken] = useState(localStorage.getItem("token").toString());
    //todo: use cookie istead of storage

    const assignProduct = () => {
        console.log(props.mealId, props.productId)
        Axios({
            method: "post",
            url: "http://localhost:8080/api/product/" + props.mealId + "/add/" + props.productId,
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                Authorization: `Bearer ${token}`,
            }
        }).then((response) => {
            console.log(response.data);
            props.updateMeal();
            props.hideTable(false);
        })    
    }

    return (
        <Button onClick={assignProduct}>
            add
        </Button>
    )
}

export default AddProduct;