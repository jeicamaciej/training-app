import React, {useEffect, useState} from "react";
import {
    Button,
    Form,
    Row,
    Col,
    InputGroup,
    ButtonGroup,
    Container,
  } from "react-bootstrap";
  

function ProductAddButton(props) {
    return (
        <div>
            <Button
                onClick = { () => console.log(props.productId) }
                className = "productAddButton"
                size = "sm"
            >
                add
            </Button>
        </div>
    )
}

export default ProductAddButton;