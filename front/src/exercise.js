import React, {useState, useEffect} from "react";
import axios from "axios";
import Set from "./set";
import SetModal from "./setModal";
import {Card, Button, ButtonGroup, Table} from "react-bootstrap";
import "./day.css";
import {CardTitle} from "react-bootstrap/Card";
import styled from "styled-components";

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
    });

    useEffect(() => {
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
        setExerciseRemoved(!isExerciseRemoved);
    };

    return (
        <div>
            {/* <div className="center-exercise-card"> */}
            <Card bsPrefix="exerciseCard">
                <Card.Title>{name}</Card.Title>
                <div>
                    {isDataPresent && (
                        <Table striped bordered hover variant="light" display="flex">
                            <thead>
                            <tr>
                                <th>Weight</th>
                                <th>Reps</th>
                                <th>{}</th>
                            </tr>
                            </thead>
                            <tbody>{sets.map((s) => (
                                <Set key={s.id}
                                     setsId={s.id}
                                     setsReps={s.reps}
                                     setsWeight={s.weight}
                                     exerciseId={id} // TU BLAD
                                     handler={props.exerciseHandler}
                                />
                            ))}
                            </tbody>
                        </Table>
                    )}
                    <SetModal
                        exerciseId={id}
                        token={token}
                        handler={props.exerciseHandler}
                    />
                    <Button
                        className="mt-2"
                        // variant={"secondary"}
                        onClick={remove}
                        size="md"
                        // style={{ backgroundColor: "#302e39" }}
                    >
                        Remove exercise
                    </Button>
                </div>
            </Card>
        </div>
        // </div>
    );
}

const StyledTable = styled(Table)`
`;

export default Exercisee;
