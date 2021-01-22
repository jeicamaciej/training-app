import React, {useState} from "react";
import axios from "axios";
import {Button,} from "react-bootstrap";
import styled from "styled-components";

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
        <StyledAddDiv>
            <StyledAddButton onClick={() => {
                onClickAddMeal();
            }}>
                Add meal
            </StyledAddButton>
        </StyledAddDiv>
    )
}

const StyledAddButton = styled(Button)`
  //margin: 0 auto;
  //margin-top: 1px;
  //padding-top: 10px;
`;

const StyledAddDiv = styled.div`
  justify-content: center;
  align-items: center;
  display: flex;
`;

export default AddMeal;