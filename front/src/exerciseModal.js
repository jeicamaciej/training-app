import React, { useState, useEffect } from "react";
import axios from "axios";
import Modal from "react-modal";

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
    <div>
      <button onClick={() => setIsModalOpen(!isModalOpen)}>add exercise</button>
      <Modal isOpen={isModalOpen}>
        {/* <p>add</p> */}
        <form>
          <label>
            <input
              placeholder="name"
              type="text"
              onChange={(e) => setName(e.target.value)}
            />
          </label>
        </form>
        <button
          onClick={() => {
            setIsModalOpen(!isModalOpen);
            setNameConfirmed(!isNameConfirmed);
          }}
        >
          confirm
        </button>
        <button onClick={() => setIsModalOpen(!isModalOpen)}>cancel</button>
      </Modal>
    </div>
  );
}

export default ExerciseModal;
