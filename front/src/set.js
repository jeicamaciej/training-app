import React, { useState, useEffect } from "react";
import axios from "axios";
import Exercise from "./exercise";

function Set(props) {
  const [id, setId] = useState(0);
  const [reps, setReps] = useState(0);
  const [weight, setWeight] = useState(0);
  const [isSetRemoved, setSetRemoved] = useState(false);

  useEffect(() => {
    setId(props.setsId);
    setReps(props.setsReps);
    setWeight(props.setsWeight);
  }, []);

  useEffect(() => {
    if (isSetRemoved) {
      console.log("removed");
    }
  }, [isSetRemoved]);

  const remove = () => {
    setSetRemoved(!false);
  };

  return (
    <div>
      <li>
        {/* id: {id} */}
        reps: {reps}
        weight: {weight}
      </li>
      <button onClick={remove}>remove set</button>
    </div>
  );
}
export default Set;
