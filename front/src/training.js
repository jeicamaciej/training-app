import React, { useState, useEffect } from "react";
import axios from "axios";
import TrainingModal from "./trainingModal";
import Exercise from "./exercise";
import Exercisee from "./exercisev2";
import ExerciseModal from "./exerciseModal";
function Training(props) {
  const [id, setId] = useState(0);
  const [exercises, setExercises] = useState();
  const [desc, setDesc] = useState("");
  const [token, setToken] = useState(localStorage.getItem("token").toString());
  const [descHandler, setDescHandler] = useState("");
  const [isResponsePresent, setResponsePresent] = useState(false);
  const [removedExercise, setRemovedExercise] = useState(false);

  useEffect(() => {
    setDesc(props.desc);
    setExercises(props.exercises);
    setId(props.id);
    setResponsePresent(true);
  });

  useEffect(() => {
    setDesc(descHandler);
    props.handler();
  }, [descHandler]);

  function modalHandler(val) {
    setDescHandler(val);
    props.handler();
  }

  function exerciseHandler() {
    setRemovedExercise(!removedExercise);
    props.handler();
  }

  return (
    <div>
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
                  exerciseSets={e.series.slice().reverse()} // ??????
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
        <br></br>
      </div>
      <div>{desc}</div>
      <div>
        <TrainingModal id={id} token={token} handler={modalHandler} />
      </div>
    </div>
  );
}

export default Training;
