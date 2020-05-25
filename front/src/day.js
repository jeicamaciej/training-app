import React, { useState, useEffect } from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";
import Training from "./trainingg";
import Logout from "./logout";

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

  return (
    <div>
      <div>
        <button onClick={removeJWT}>logout</button>
      </div>
      <div>{isUserAdmin && <button>admin</button>}</div>
      <button onClick={() => setDateChangeValue((dateChangeValue += 1))}>
        +
      </button>
      <button onClick={() => setDateChangeValue((dateChangeValue -= 1))}>
        -
      </button>
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
    </div>
  );
}

export default withRouter(Day);
