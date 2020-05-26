import React, { useState, useEffect } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMinusSquare } from "@fortawesome/free-solid-svg-icons";
import { Button } from "react-bootstrap";

function Set(props) {
  const [id, setId] = useState(0);
  const [reps, setReps] = useState(0);
  const [weight, setWeight] = useState(0);
  const [isSetRemoved, setSetRemoved] = useState(false);
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

  return (
    <div>
      <li>
        reps: {reps}
        weight: {weight}{" "}
        <Button
          onClick={remove}
          variant={"secondary"}
          size={"sm"}
          //</li>style={{ height: 20, width: 20 }}
        >
          <FontAwesomeIcon icon={faMinusSquare} />
        </Button>
      </li>
    </div>
  );
}
export default Set;
