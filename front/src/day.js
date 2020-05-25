import React, { useState, useEffect } from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";
import Training from "./trainingg";

function Day(props) {
  const [date, setDate] = useState(new Date());
  const [isDateChanged, setDateChanged] = useState(false);
  const [token, setToken] = useState(localStorage.getItem("token").toString());
  const [training, setTraining] = useState({});
  const [isTrainingPresent, setTrainingPresent] = useState(false);
  const [isUpdated, setUpdated] = useState(false);
  let [dateChangeValue, setDateChangeValue] = useState(0);

  useEffect(() => {
    let d = new Date();
    d.setDate(d.getDate() + dateChangeValue);
    setDate(d.toISOString().slice(0, 10));
    setDateChanged(true);
  }, [dateChangeValue]);

  useEffect(() => {
    if (isDateChanged || isUpdated) {
      console.log(date);
      axios({
        method: "get",
        url: "http://localhost:8080/api/day/a/" + date,
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-type": "Application/json",
          Authorization: `Bearer ${token}`,
        },
      })
        .then((response) => {
          console.log(response.data);
          console.log(response.data.training);
          setTraining(response.data.training);
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

  return (
    <div>
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
            //date={training.date}
            desc={training.desc}
            handler={handler}
          />
        )}
      </div>
    </div>
  );
}

export default withRouter(Day);
