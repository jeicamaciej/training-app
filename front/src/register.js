import React from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";
import { Container, Card, Button, Form, Row } from "react-bootstrap";
import "./register.css";

class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
      username: "",
      email: "",
      password: "",
      message: "",
    };
    this.handleNameChange = this.handleNameChange.bind(this);
    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
  }

  handleNameChange(event) {
    this.setState({ name: event.target.value });
  }
  handleUsernameChange(event) {
    this.setState({ username: event.target.value });
  }
  handleEmailChange(event) {
    this.setState({ email: event.target.value });
  }
  handlePasswordChange(event) {
    this.setState({ password: event.target.value });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    const { history } = this.props;

    const data = {
      name: this.state.name,
      username: this.state.username,
      email: this.state.email,
      password: this.state.password,
    };

    console.log(data);

    axios({
      method: "post",
      url: "http://localhost:8080/api/auth/signup",
      data: data,
    })
      .then((response) => {
        // console.log(response);
        this.setState({ message: response.data.message + ", redirecting" });
        setTimeout(() => history.push("/login"), 500);
      })
      .catch((error) => {
        this.setState({ message: "Incorrect creditentials" });
      });
  };

  render() {
    return (
      <div className="background">
        <Card bg="dark" border="secondary" className="main-card">
          <Card.Header>
            {" "}
            <div className="main-card-header">TRAINING-APP </div>{" "}
          </Card.Header>
          <Card.Body className="form-card">
            <div>
              <div className="center-form">
                <Form onSubmit={this.handleSubmit}>
                  <Form.Control
                    className="input"
                    placeholder="name"
                    type="text"
                    value={this.state.name}
                    onChange={this.handleNameChange}
                  />
                  <br />
                  <Form.Control
                    className="input"
                    placeholder="username"
                    type="text"
                    value={this.state.username}
                    onChange={this.handleUsernameChange}
                  />
                  <br />
                  <Form.Control
                    className="input"
                    placeholder="email"
                    type="text"
                    value={this.state.email}
                    onChange={this.handleEmailChange}
                  />
                  <br />
                  <Form.Control
                    className="input"
                    placeholder="password"
                    type="text"
                    value={this.state.password}
                    onChange={this.handlePasswordChange}
                  />
                  <br></br>
                  <div>
                    <Row>
                      <div className="sign-in-button">
                        <Button
                          className="sign-in-button"
                          variant="secondary"
                          type="submit"
                          size="sm"
                          onClick={this.handleSubmit}
                        >
                          sign up
                        </Button>
                      </div>
                    </Row>
                  </div>
                </Form>
              </div>
            </div>
            <div className="error-message">{this.state.message}</div>
          </Card.Body>
        </Card>
      </div>
    );
  }
}
export default withRouter(Register);
