import React, { useState, useEffect } from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";
import Training from "./training";
import Logout from "./logout";
import Modal from "react-modal";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  Container,
  Row,
  Col,
  Card,
  Badge,
  Button,
  Navbar,
  Nav,
} from "react-bootstrap";
import "react-bootstrap/dist/react-bootstrap.min.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendarPlus } from "@fortawesome/free-solid-svg-icons";
import { faCalendarMinus } from "@fortawesome/free-solid-svg-icons";

function Day(props) {
  const [date, setDate] = useState(new Date());
  const [isDateChanged, setDateChanged] = useState(false);
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [training, setTraining] = useState({});
  const [isTrainingPresent, setTrainingPresent] = useState(false);
  const [isUpdated, setUpdated] = useState(false);
  let [dateChangeValue, setDateChangeValue] = useState(0);
  const [userRole, setUserRole] = useState([]);
  const [isUserAdmin, setUserAdmin] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
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
    const roles = response.data.user.roles.map((r) => userRole.push(r.name));
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
          console.log(response.data);
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

  return (
    <div
      style={{
        backgroundColor: "#64498c",
      }}
    >
      <Navbar bg="dark" variant="dark">
        <Nav className="container-fluid">
          <Nav.Item>{isUserAdmin && <button>admin</button>}</Nav.Item>
          <Nav.Item className="ml-auto">
            <Button
              onClick={() => setDateChangeValue((dateChangeValue -= 1))}
              variant={"secondary"}
              size={"md"}
              style={{ backgroundColor: "#302e39" }}
            >
              <FontAwesomeIcon icon={faCalendarMinus} /> {formatDate(-1)}
            </Button>
          </Nav.Item>
          <Button
            variant={"secondary"}
            size={"md"}
            style={{ backgroundColor: "#64498c" }}
          >
            {formatDate(0)}
          </Button>
          <Nav.Item>
            <Button
              onClick={() => setDateChangeValue((dateChangeValue += 1))}
              variant={"secondary"}
              size={"md"}
              style={{ backgroundColor: "#302e39" }}
            >
              {formatDate(1)} <FontAwesomeIcon icon={faCalendarPlus} />
            </Button>
          </Nav.Item>
          <Nav.Item className="ml-auto">
            <Button
              onClick={removeJWT}
              variant={"dark"}
              size={"sm"}
              style={{ backgroundColor: "#302e39" }}
            >
              logout
            </Button>
          </Nav.Item>
        </Nav>
      </Navbar>
      <br></br>
      <Container>
        <div>
          <Card bg={"dark"} text={"light"}>
            <Card.Title className="text-center">
              <Card bg={"secondary"}>{date.toString()}</Card>
            </Card.Title>
            <Card.Body>
              {isTrainingPresent && (
                <Training
                  id={training.id}
                  exercises={training.exercises}
                  desc={training.desc}
                  handler={handler}
                />
              )}
            </Card.Body>
          </Card>
        </div>
      </Container>
    </div>
  );
}
export default withRouter(Day);
