import React, { useState, useEffect } from "react";
import axios from "axios";
import TrainingModal from "./trainingModal";
import Exercise from "./exercise";
import ExerciseModal from "./exerciseModal";
import "./day.css";

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
            <div>
              {exercises.map((e) => (
                  <Exercise key={e.id}
                    exerciseId={e.id}
                    exerciseName={e.name}
                    exerciseDesc={e.desc}
                    exerciseSets={e.series} // ??????.slice().reverse()
                    trainingId={id}
                    exerciseHandler={exerciseHandler}
                  />
              ))}
            </div>
          )}
        </div>
        <div>
          <ExerciseModal
            trainingId={id}
            token={token}
            handler={exerciseHandler}
          />
        </div>
      </div>
  );
}

export default Training;
