import React, {createRef, useEffect, useState} from "react";
import axios from "axios";
import {withRouter} from "react-router-dom";
import Training from "./training";
import "bootstrap/dist/css/bootstrap.min.css";
import {Button, Container, Nav, Navbar, Row,} from "react-bootstrap";
import "react-bootstrap/dist/react-bootstrap.min.js";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCalendarMinus, faCalendarPlus} from "@fortawesome/free-solid-svg-icons";
import "./day.css";
import MealContainer from "./mealContainer";
import styled from "styled-components";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css'

function Day(props) {

    const [id, setId] = useState(0);
    const [date, setDate] = useState(new Date());
    const [isDateChanged, setDateChanged] = useState(false);
    const [token, setToken] = useState(localStorage.getItem("token"));
    const [training, setTraining] = useState({});
    const [isTrainingPresent, setTrainingPresent] = useState(false);
    const [isUpdated, setUpdated] = useState(false);
    const [userRole, setUserRole] = useState([]);
    const [isUserAdmin, setUserAdmin] = useState(false);
    let [dateChangeValue, setDateChangeValue] = useState(0);
    const [renderTraining, setRenderTraining] = useState(true);
    const [renderDiet, setRenderDiet] = useState(false);
    const [isCalendarOpen, setCalendarOpen] = useState(false);

    useEffect(() => {
        console.log(token);
        if (token === null) {
            props.history.push("/login");
        }
    }, [token]);

    useEffect(() => {
        if (isTrainingPresent) {
            if (userRole.includes("ROLE_ADMIN")) {
                setUserAdmin(true);
            }
        }
    });

    useEffect(() => {
        let d = new Date();
        d.setDate(d.getDate() + dateChangeValue);
        setDate(d.toISOString().slice(0, 10));
        setDateChanged(true);
    }, [dateChangeValue]);

    function getUserRolesFromResponse(response) {
        const roles = response.data.user.roles.map(
            (r) => userRole.push(r.name));
        setUserRole([...new Set([userRole])]);
    }

    useEffect(() => {
        if (isDateChanged || isUpdated) {
            console.log(date);
            axios({
                method: "get",
                url: "http://localhost:8080/api/day/get/" + date,
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Content-type": "Application/json",
                    Authorization: `Bearer ${token}`,
                },
            })
                .then((response) => {
                    // console.log(response.data);
                    setTraining(response.data.training);
                    getUserRolesFromResponse(response);
                    setTrainingPresent(true);
                })
                .then(() => {
                    setDateChanged(false);
                    setUpdated(false);
                });
        }
    }, [isDateChanged, isUpdated]);

    function handler() {
        setUpdated(!isUpdated);
    }

    function removeJWT() {
        localStorage.removeItem("token");
        setTimeout(() => props.history.push("/login"), 250);
    }

    function formatDate(value) {
        let newDate = new Date(date);
        newDate.setDate(newDate.getDate() + value);
        return newDate.toISOString().slice(0, 10);
    }

    const toggleTraining = () => {
        if (!renderTraining && renderDiet) {
            setRenderTraining(true);
            setRenderDiet(false);
        }
    }

    const toggleDiet = () => {
        if (renderTraining && !renderDiet) {
            setRenderTraining(false);
            setRenderDiet(true);
        }
    }

    const onCalendarChange = (value) => {

        let newDate = new Date(value);
        newDate.setDate(newDate.getDate() + 1);
        setDate(newDate.toISOString().slice(0, 10));
        handler();
    }

    return (
        <div className="background">
            <StyledNav bg="dark" variant="dark">

                <StyledTrainingDietButtonsDiv>
                    <StyledTrainingButton onClick={toggleTraining}>
                        Training
                    </StyledTrainingButton>
                    <StyledDietButton onClick={toggleDiet}>
                        Diet
                    </StyledDietButton>
                </StyledTrainingDietButtonsDiv>

                <StyledDateButtonsDiv>
                    <Button
                        className="change-day-button"
                        onClick={() => setDateChangeValue((dateChangeValue -= 1))}
                        variant={"secondary"}
                        size={"md"}
                    >
                        <FontAwesomeIcon icon={faCalendarMinus}/> {formatDate(-1)}
                    </Button>
                    <Button
                        className="middle-date-button"
                        // variant={"secondary"}
                        // size={"md"}
                        // style={{backgroundColor: "#64498c"}}
                        onClick={() => setCalendarOpen((prev) => !prev)}
                    >
                        {formatDate(0)}
                    </Button>
                    <Button
                        className="change-day-button"
                        onClick={() => setDateChangeValue((dateChangeValue += 1))}
                        variant={"secondary"}
                        size={"md"}
                    >
                        {formatDate(1)} <FontAwesomeIcon icon={faCalendarPlus}/>
                    </Button>
                </StyledDateButtonsDiv>

                {/*<div>*/}
                {/*    {isUserAdmin && (*/}
                {/*        <div className="admin-button">*/}
                {/*            <Button*/}
                {/*                onClick={() => props.history.push("/admin")}*/}
                {/*                variant={"secondary"}*/}
                {/*                size="md"*/}
                {/*                style={{backgroundColor: "#302e39"}}*/}
                {/*            >*/}
                {/*                admin*/}
                {/*            </Button>{" "}*/}
                {/*        </div>*/}
                {/*    )}*/}
                {/*</div>*/}

                <StyledLogoutButtonDiv>
                    <StyledLogoutButton
                        onClick={removeJWT}
                        size={"md"}
                    >
                        Logout
                    </StyledLogoutButton>
                </StyledLogoutButtonDiv>

            </StyledNav>
            {isCalendarOpen && (
                <StyledCalendar
                    onChange={onCalendarChange}
                    value={new Date()}
                    locale={"en-GB"}
                >
                </StyledCalendar>
            )}
            {renderTraining && (<Container className="training-container">
                <div>
                    {isTrainingPresent && (
                        <Training
                            id={training.id}
                            exercises={training.exercises}
                            desc={training.desc}
                            handler={handler}
                        />
                    )}
                </div>
            </Container>)}
            {renderDiet && (<MealContainer
                date={formatDate(0)}
            />)}
        </div>
    );

}

const StyledCalendar = styled(Calendar)`
  max-width: 400px;
  margin: 0 auto;
  padding: 10px;
  box-shadow: 0 2px 8px 0 rgba(0, 0, 0, 0.2);
  border-radius: 10px;
`;

const StyledTrainingButton = styled(Button)`
  //margin-left: 10px;
`;

const StyledDietButton = styled(Button)`
  margin-left: 10px;
`;

const StyledLogoutButton = styled(Button)`
  //margin-right: 20px;
`;

const StyledDateButtonsDiv = styled.div`
  margin: 0 auto;
`;

const StyledNav = styled(Navbar)`
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const StyledTrainingDietButtonsDiv = styled.div`
  position: absolute;
  left: 20px;
`;

const StyledLogoutButtonDiv = styled.div`
  position: absolute;
  right: 20px;
`;

export default withRouter(Day);
