import React, { useState, useEffect } from "react";
import axios from "axios";
import Modal from "react-modal";

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
        <button onClick={() => setIsModalOpen(!isModalOpen)}>add set</button>
        <Modal isOpen={isModalOpen}>
          <p>add set</p>
          <form>
            <label>
              <input
                placeholder="weight"
                type="text"
                onChange={(e) => setWeight(e.target.value)}
              />
            </label>
            <label>
              <input
                placeholder="reps"
                type="text"
                onChange={(e) => setReps(e.target.value)}
              />
            </label>
          </form>
          <button
            onClick={() => {
              setIsModalOpen(!isModalOpen);
              setInputConfirmed(!isInputConfirmed);
            }}
          >
            confirm
          </button>
          <button onClick={() => setIsModalOpen(!isModalOpen)}>cancel</button>
        </Modal>
      </div>
    </div>
  );
}

export default SetModal;
