import React, { useState, useEffect } from "react";
import axios from "axios";
import Modal from "react-modal";
import {
  Button,
  Form,
  Row,
  Col,
  InputGroup,
  ButtonGroup,
  Container,
} from "react-bootstrap";
import styled from "styled-components";

const StyledDiv = styled.div`
  margin-top: 10px;  
`;

function ExerciseModal(props) {
  const [name, setName] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isNameConfirmed, setNameConfirmed] = useState(false);

  useEffect(() => {
    if (isNameConfirmed) {
      axios({
        method: "post",
        url: "http://localhost:8080/api/exercise/" + props.trainingId + "/add",
        params: { name },
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-type": "Application/json",
          Authorization: `Bearer ${props.token}`,
        },
      }).then(() => {
        props.handler();
        setNameConfirmed(false);
      });
    }
  }, [isNameConfirmed]);

  return (
    <StyledDiv>
      {!isModalOpen && (
        <Button
          onClick={() => setIsModalOpen(!isModalOpen)}
          className="new-exercise-button"
          // size="sm"
          // variant="secondary"
        >
          Add new exericse
        </Button>
      )}
      {isModalOpen && (
        <div className="new-exercise-form">
          <div>
            <Row>
              <Form>
                <InputGroup className="new-exercise-form">
                  <Form.Control
                    // size="sm"
                    placeholder="new exercise"
                    type="text"
                    onInput={(e) => {
                      e.preventDefault();
                      setName(e.target.value);
                    }}
                  />
                  <InputGroup.Append>
                    <ButtonGroup>
                      <Button
                        type="submit"
                        className="add-exercise-button"
                        // size="sm"
                        // variant="secondary"
                        onClick={(e) => {
                          setIsModalOpen(!isModalOpen);
                          setNameConfirmed(!isNameConfirmed);
                          e.preventDefault();
                        }}
                      >
                        add
                      </Button>
                      <Button
                        type="submit"
                        className="add-exercise-button"
                        // size="sm"
                        // variant="secondary"
                        onClick={(e) => {
                          setIsModalOpen(!isModalOpen);
                          e.preventDefault();
                        }}
                      >
                        cancel
                      </Button>
                    </ButtonGroup>
                  </InputGroup.Append>
                </InputGroup>
              </Form>
            </Row>
          </div>
        </div>
      )}
    </StyledDiv>
  );
}

export default ExerciseModal;
