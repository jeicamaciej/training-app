import React, { useState, useEffect } from "react";
import axios from "axios";
import TrainingModal from "./trainingModal";
import Exercise from "./exercise";

function Training() {
  const [id, setId] = useState(0);
  const [exercises, setExercises] = useState();
  const [desc, setDesc] = useState("");
  const [token, setToken] = useState(localStorage.getItem("token").toString());
  const [date, setDate] = useState(new Date().toISOString().slice(0, 10));
  const [descHandler, setDescHandler] = useState("");
  const [isResponsePresent, setResponsePresent] = useState(false);

  useEffect(() => {
    axios({
      method: "get",
      url: "http://localhost:8080/api/training/get/" + date,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "Application/json",
        Authorization: `Bearer ${token}`,
      },
    }).then((response) => {
      setId(response.data.id);
      setDesc(response.data.desc);
      setExercises(response.data.exercises);
      //setExercises([1, 2, 3]);
      setResponsePresent(true);
    });
  }, []);

  useEffect(() => {
    setDesc(descHandler);
  }, [descHandler]);

  function a(val) {
    setDescHandler(val);
  }

  return (
    <div>
      <div>id {id}</div>
      <div>description {desc}</div>
      <div>{/*<li>{exercises}</li>*/}</div>

      <div>
        {isResponsePresent && (
          <ul>
            <Exercise exercises={exercises} />
          </ul>
        )}
      </div>

      <div>
        <TrainingModal id={id} token={token} handler={a} />
      </div>
    </div>
  );
}

export default Training;
