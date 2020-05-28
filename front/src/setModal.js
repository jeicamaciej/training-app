import React, { useState, useEffect } from "react";
import axios from "axios";
//import Modal from "react-modal";
import { Button, Form, Col, Row, ButtonGroup } from "react-bootstrap";
import "./day.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlusSquare, faWindowClose } from "@fortawesome/free-solid-svg-icons";

function SetModal(props) {
  const [weight, setWeight] = useState(0);
  const [reps, setReps] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isInputConfirmed, setInputConfirmed] = useState(false);

  useEffect(() => {
    if (isInputConfirmed) {
      axios({
        method: "post",
        url: "http://localhost:8080/api/series/" + props.exerciseId + "/add",
        params: { reps, weight },
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-type": "Application/json",
          Authorization: `Bearer ${props.token}`,
        },
      }).then(() => {
        props.handler();
        setInputConfirmed(false);
      });
    }
  }, [isInputConfirmed]);

  return (
    <div>
      <div>
        {!isModalOpen && (
          <Button
            className="add-set-button"
            onClick={() => setIsModalOpen(!isModalOpen)}
            variant={"secondary"}
            size="sm"
          >
            add set
          </Button>
        )}
        {isModalOpen && (
          <div>
            <Form>
              <Form.Row>
                <Col>
                  <Form.Control
                    size="sm"
                    placeholder="weight"
                    type="text"
                    onChange={(e) => setWeight(e.target.value)}
                  />
                </Col>
                <Col>
                  <Form.Control
                    size="sm"
                    placeholder="reps"
                    type="text"
                    onChange={(e) => setReps(e.target.value)}
                  />
                </Col>
                <Col>
                  <Button
                    variant="secondary"
                    size="sm"
                    className="confirm-button"
                    onClick={() => {
                      setIsModalOpen(!isModalOpen);
                      setInputConfirmed(!isInputConfirmed);
                    }}
                  >
                    <div className="font-size">
                      <FontAwesomeIcon icon={faPlusSquare} />
                      {/* confirm */}
                    </div>
                  </Button>
                  <Button
                    variant="secondary"
                    className="cancel-button"
                    size="sm"
                    //className="ml-2"
                    onClick={() => setIsModalOpen(!isModalOpen)}
                  >
                    <FontAwesomeIcon icon={faWindowClose} />
                    {/* cancel */}
                  </Button>
                </Col>
              </Form.Row>
            </Form>
          </div>
        )}
      </div>
    </div>
  );
}

export default SetModal;
