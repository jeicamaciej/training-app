import React, { useState, useEffect } from "react";
import axios from "axios";
import TrainingModal from "./trainingModal";
import Exercise from "./exercise";
import Exercisee from "./exercisev2";
import ExerciseModal from "./exerciseModal";

function Training() {
  const [id, setId] = useState(0);
  const [exercises, setExercises] = useState();
  const [desc, setDesc] = useState("");
  const [token, setToken] = useState(localStorage.getItem("token").toString());
  const [date, setDate] = useState(new Date().toISOString().slice(0, 10));
  const [descHandler, setDescHandler] = useState("");
  const [isResponsePresent, setResponsePresent] = useState(false);
  const [removedExercise, setRemovedExercise] = useState(false);

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
      setResponsePresent(true);
    });
  }, [descHandler, removedExercise]);

  useEffect(() => {
    setDesc(descHandler);
  }, [descHandler]);

  function modalHandler(val) {
    setDescHandler(val);
  }

  function exerciseHandler() {
    setRemovedExercise(!removedExercise);
  }

  return (
    <div>
      {/* <div>id {id}</div> */}
      <div>description {desc}</div>
      <div>
        {isResponsePresent && (
          <ul>
            {exercises.map((e) => (
              <div key={e.id}>
                <Exercise
                  key
                  exerciseId={e.id}
                  exerciseName={e.name}
                  exerciseDesc={e.desc}
                  exerciseSets={e.series}
                  trainingId={id}
                  exerciseHandler={exerciseHandler}
                />
              </div>
            ))}
          </ul>
        )}
      </div>
      <div>
        <ExerciseModal
          trainingId={id}
          token={token}
          handler={exerciseHandler}
        />
      </div>
      <div>
        <TrainingModal id={id} token={token} handler={modalHandler} />
      </div>
    </div>
  );
}

export default Training;
