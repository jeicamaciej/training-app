import Axios from "axios";
import Table from 'react-bootstrap/Table'
import Product from "./product";
import ProductSearch from "./productSearch";
import ProductTable from "./productsTable";
import React, { useState, useEffect } from "react";
import Meal from "./meal.js"
import AddMeal from "./addMeal";
//  TODO: ADD MEAL CONTAINER FOR MEALCONTAINER xD + RENAME

function MealContainer(props){
    const [id, setId] = useState(0);
    const [products, setProducts] = useState([]);
    const [isProductListUpdated, setProductListUpdated] = useState(false);
    
    const [token, setToken] = useState(localStorage.getItem("token").toString());
    const [meals, setMeals] = useState([]);
    const [viewState, setViewState] = useState(0); // 0 = loading | 1 = success | 2 = error 
    const [date, setDate] = useState("");
    
    useEffect(() => {
        Axios({
            method: "get",
            url: "http://localhost:8080/api/meal/all/" + props.date,
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                Authorization: `Bearer ${token}`,
            },    
        }).then((response) => {
            console.log(response.data)
            setMeals(response.data);   
            setViewState(1);
            setDate(props.date)
        });
    },[isProductListUpdated, date])

    useEffect(() => {
        setDate(props.date);
    },[props.date])

    // const updateMeal = (mealIndex, updatedMeal) => {
    //     setMeals(
    //         prev => {
    //             const mealsToUpdate = [...prev];
    //             mealsToUpdate[mealIndex] = updateMeal;
    //             return mealsToUpdate;
    //         }
    //     )
    // }

    const updateMeal = () => {
        setProductListUpdated(!isProductListUpdated);    
    }

    return (
        <div>
            <div>
                {
                meals.map((m) => (
                    <Meal key = {m.id}
                        name = {"test"}
                        products = {m.products}
                        id = {m.id}
                        updateMeal = {updateMeal}
                    /> 
                )) 
                }
            </div>
                <div>
                    <AddMeal
                        date = {props.date}
                        updateMeal = {updateMeal}
                    />
            </div>
        </div>
    )
}
export default MealContainer;       
