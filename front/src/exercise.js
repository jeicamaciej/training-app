import React, { useState, useEffect } from "react";
import axios from "axios";
import Set from "./set";

function Exercisee(props) {
  const [id, setId] = useState(0);
  const [name, setName] = useState("");
  const [sets, setSets] = useState([]);
  const [isDataPresent, setDataPresent] = useState(false);
  const [isExerciseRemoved, setExerciseRemoved] = useState(false);
  const [token, setToken] = useState(localStorage.getItem("token").toString());

  useEffect(() => {
    setName(props.exerciseName);
    setId(props.exerciseId);
    setSets(props.exerciseSets);
    setDataPresent(true);
  }, []);

  useEffect(() => {
    console.log(id);
    if (isExerciseRemoved) {
      axios({
        method: "post",
        url:
          "http://localhost:8080/api/training/remove/" +
          id +
          "/" +
          props.trainingId,
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-type": "Application/json",
          Authorization: `Bearer ${token}`,
        },
      }).then(() => {
        props.exerciseHandler();
      });
    }
  }, [isExerciseRemoved]);

  const remove = () => {
    setExerciseRemoved(true);
  };

  return (
    <div>
      {/* <div>id: {id}</div> */}
      <div>name: {name}</div>
      <div>
        {isDataPresent && (
          <ol>
            {sets.map((s) => (
              <div key={s.id}>
                <Set setsId={s.id} setsReps={s.reps} setsWeight={s.weight} />
              </div>
            ))}
          </ol>
        )}
        <button onClick={remove}>remove exercise</button>
      </div>
    </div>
  );
}
export default Exercisee;
