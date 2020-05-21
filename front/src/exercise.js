import React, { useState, useEffect } from "react";
import axios from "axios";

function Exercise(props) {
  const [id, setId] = useState(0);
  const [name, setName] = useState("");
  const [series, setSeries] = useState([]);
  const [execiseList, setExerciseList] = useState([]);

  useEffect(() => {
    setExerciseList(props.exercises);
    console.log(props.exercises);
  }, []);

  return (
    <div>
      <li>
        {props.exercises.map((e) => (
          <li>
            {e.name}
            {e.series.map((s) => (
              <ol>
                <li>
                  reps {s.reps} <span></span>
                  weight {s.weight.toString().concat(" kg")}
                </li>
              </ol>
            ))}
          </li>
        ))}
      </li>
    </div>
  );
}

export default Exercise;
