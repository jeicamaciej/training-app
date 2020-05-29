import { render } from "react-dom";
import React, { useState, useEffect } from "react";
import { Route, Link, BrowserRouter as Router, Switch } from "react-router-dom";
import { withRouter } from "react-router-dom";
import { Container, Card, Button, Form, Row, ListGroup } from "react-bootstrap";
import axios from "axios";
import User from "./user";

function Admin(props) {
  const [users, setUsers] = useState([]);
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [message, setMessage] = useState("not authorized");
  const [token, setToken] = useState(localStorage.getItem("token"));

  useEffect(() => {
    if (token === null) {
      props.history.push("/login");
    }
  }, [token]);

  useEffect(() => {
    axios({
      method: "get",
      url: "http://localhost:8080/api/user/all/",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "Application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        setUsers(response.data);
        setAuthenticated(true);
      })
      .catch((e) => {
        console.log(e.message);
        props.history.push("/home");
        alert("unauthenticated");
      });
  }, []);

  return (
    <div className="background">
      <Card bg="dark">
        {isAuthenticated && (
          <div>
            <ListGroup>
              {users.map((u) => (
                <ListGroup.Item
                  variant="dark"
                  action
                  style={{ width: "50%", textAlign: "center", margin: "auto" }}
                >
                  <div key={u.id}>
                    <User id={u.id} username={u.username} email={u.email} />
                  </div>
                </ListGroup.Item>
              ))}
            </ListGroup>
            <div style={{ textAlign: "center" }}>
              <Link to="/home" style={{ fontSize: "20px", color: "white" }}>
                back
              </Link>
            </div>
          </div>
        )}
      </Card>
    </div>
  );
}

export default withRouter(Admin);
