import React, { useState, useEffect } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMinusSquare } from "@fortawesome/free-solid-svg-icons";
import { Button, CardColumns } from "react-bootstrap";
import { Table, Card, CardGroup } from "react-bootstrap";
import "./day.css";

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
        props.handler();
      });
    }
  }, [isSetRemoved]);

  const remove = () => {
    setSetRemoved(!isSetRemoved);
  };

  return (
    <div>
      <CardColumns>
        <Card bsPrefix="medium-exercise-card">
          <li>
            <CardGroup>
              <Card bg="secondary">
                {weight + " kg"}
                {" x "} {reps}
              </Card>
              <Button bsPrefix="remove-button" onClick={remove}>
                <FontAwesomeIcon icon={faMinusSquare} />
              </Button>
            </CardGroup>
          </li>
        </Card>
      </CardColumns>
    </div>
  );
}
export default Set;
