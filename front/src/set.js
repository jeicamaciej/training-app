import React, { useState, useEffect } from "react";
import axios from "axios";

function Set(props) {
  const [id, setId] = useState(0);
  const [reps, setReps] = useState(0);
  const [weight, setWeight] = useState(0);
  const [isSetRemoved, setSetRemoved] = useState(false);
  const [isSetAdded, setSetAdded] = useState(false);
  const [token, setToken] = useState(localStorage.getItem("token").toString());

  useEffect(() => {
    setId(props.setsId);
    setReps(props.setsReps);
    setWeight(props.setsWeight);
  });

  useEffect(() => {
    if (isSetRemoved) {
      axios({
        method: "post",
        url:
          "http://localhost:8080/api/exercise/remove/" +
          props.exerciseId +
          "/" +
          id,
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-type": "Application/json",
          Authorization: `Bearer ${token}`,
        },
      }).then(() => {
        console.log(id);
        props.handler();
      });
    }
  }, [isSetRemoved]);

  const remove = () => {
    setSetRemoved(!isSetRemoved);
  };

  const add = () => {};

  return (
    <div>
      <li>
        reps: {reps}
        weight: {weight}
      </li>
      <button onClick={remove}>remove set</button>
    </div>
  );
}
export default Set;
