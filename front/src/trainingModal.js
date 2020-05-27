import React, { useState, useEffect } from "react";
import axios from "axios";
import Modal from "react-modal";
import Training from "./training";
import { Button } from "react-bootstrap";

function TrainingModal(props) {
  //const [id, setId] = useState(props.id);
  const [desc, setDesc] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isDescChanged, setIsDescChanged] = useState(false);
  const [isDescConfirmed, setIsDescConfirmed] = useState(false);

  useEffect(() => {
    if (props.id) {
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
    }
  }, [desc]); //TU SPRAWDZIC Z IS CONFIRMED !!!!!!

  return (
    <div>
      <Button
        onClick={() => setIsModalOpen(true)}
        variant={"secondary"}
        size="sm"
        style={{ backgroundColor: "#302e39" }}
      >
        edit description
      </Button>
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
