import React, { useState, useEffect } from "react";
import axios from "axios";
import Modal from "react-modal";
import Training from "./training";

function TrainingModal(props) {
  //const [id, setId] = useState(props.id);
  const [desc, setDesc] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isDescChanged, setIsDescChanged] = useState(false);
  const [isDescConfirmed, setIsDescConfirmed] = useState(false);

  useEffect(() => {
    console.log(props.id);
    axios({
      method: "post",
      url: "http://localhost:8080/api/training/edit/" + props.id,
      params: { desc },
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "Application/json",
        Authorization: `Bearer ${props.token}`,
      },
    }).then(() => {
      props.handler(desc);
    });
  }, [desc]);

  return (
    <div>
      <button onClick={() => setIsModalOpen(true)}>edit</button>
      <Modal isOpen={isModalOpen}>
        <p>edit</p>
        <form>
          <label>
            <input
              placeholder="desciption"
              type="text"
              value={desc}
              onChange={(e) => setDesc(e.target.value)}
            />
          </label>
        </form>
        <button onClick={() => setIsModalOpen(false)}>cancel</button>
        <button
          onClick={() => {
            setIsModalOpen(false);
            setIsDescConfirmed(true);
          }}
        >
          confirm
        </button>
      </Modal>
    </div>
  );
}

export default TrainingModal;
