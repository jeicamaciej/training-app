import React, {useEffect, useRef, useState} from "react";
import axios from "axios";
// import { FormControl } from '@material-ui/core';
import {Button, Form, InputGroup,} from "react-bootstrap";
import ProductTable from "./productsTable"

const ProductSearch = (props) => {
    const [isSearchPressed, setSearchPressed] = useState(false);
    const [token, setToken] = useState(localStorage.getItem("token").toString());
    const [products, setProducts] = useState([]);
    const [isResponsePresent, setResponsePresent] = useState(false);
    const [pageIndex, setPageIndex] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [productName, setProductName] = useState("")

    const pageSize = 5;

    const pageOnChangeEvent = (event, value) => {
        setPageIndex(value);
    };

    const firstUpdate = useRef(true);

    useEffect(() => {
        if (firstUpdate.current) {
            firstUpdate.current = false;
            return;
        }
        axios({
            method: "get",
            url: "http://localhost:8080/api/product/searchp/",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                Authorization: `Bearer ${token}`,
            },
            params: {
                "productName": productName,
                "page": pageIndex,
                "size": pageSize,
            }
        }).then((response) => {
            setResponsePresent(true)
            setProducts(response.data.content)
            setTotalPages(response.data.totalPages)
            console.log(response.data)
        });
    }, [isSearchPressed, pageIndex])

    return (
        <div>
            <div>
                <InputGroup className="newProduct">
                    <Form.Control
                        size="sm"
                        placeholder="product name"
                        type="text"
                        onInput={(e) => setProductName(e.target.value)}
                        onKeyDown={(e) => {
                            if (e.keyCode === 13) setSearchPressed(!isSearchPressed)
                        }}
                    />
                </InputGroup>
            </div>
            <Button
                onClick={() => {
                    setSearchPressed(!isSearchPressed);
                    console.log(productName)
                }}
            >
                search
            </Button>
            <div>
                {isResponsePresent && (
                    <ProductTable
                        products={products}
                        active={pageIndex}
                        totalPages={totalPages}
                        pageOnChangeEvent={pageOnChangeEvent}
                        enableAddButton={true}
                        enablePaging={true}
                        mealId = {props.mealId}
                        updateMeal = {props.updateMeal}
                        hideTable = {setResponsePresent}
                    />
                )}
            </div>
        </div>
    )
}
export default ProductSearch;