import React, {useEffect, useState} from "react";
import axios from "axios";
import {Button} from "react-bootstrap";
import "./day.css";
import styled from "styled-components";

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
    // <div>
    //   <CardColumns>
    //     <Card bsPrefix="medium-exercise-card">
    //       <li>
    //         <CardGroup>
    //           <Card bg="secondary">
    //             {weight + " kg"}
    //             {" x "} {reps}
    //           </Card>
    //           <Button onClick={remove}>
    //             {/* <FontAwesomeIcon icon={faMinusSquare} /> */}
    //             remove
    //           </Button>
    //         </CardGroup>
    //       </li>
    //     </Card>
    //   </CardColumns>
    // </div>
    <StyledRow>

      <StyledWeightColumn>
        {weight + " kg"}
      </StyledWeightColumn>

      <StyledRepsColumn>
        {reps}
      </StyledRepsColumn>
      

      <StyledButtonColumn>
        <Button onClick={remove}>
          remove
        </Button>
      </StyledButtonColumn>

    </StyledRow>
  );
}

const StyledRow = styled.tr`
  // border: 1px solid grey;
`;

const StyledWeightColumn = styled.td`
  width: 200px;
`;

const StyledRepsColumn = styled.td`
width: 200px;

`;

const StyledButtonColumn = styled.td`
  width: 100px;
`;

export default Set;
