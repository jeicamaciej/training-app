import React, {useEffect, useRef, useState} from "react";
import axios from "axios";
import {Button, Form, InputGroup,} from "react-bootstrap";
const AddMeal = (props) => {

    const [token, setToken] = useState(localStorage.getItem("token").toString());

    const onClickAddMeal = () => {
        axios({
            method: "post",
            url: "http://localhost:8080/api/meal/new/" + props.date,
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                Authorization: `Bearer ${token}`,
            }
        }).then((response) => {
            console.log(response.data);
            props.updateMeal();
        })
    }

    return (
        <div>
            <Button onClick={() => {onClickAddMeal();}}>
                    add meal
            </Button>
        </div>
    )
}
export default AddMeal;