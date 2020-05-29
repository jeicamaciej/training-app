import React from "react";
import axios from "axios";
import { Route, Link, BrowserRouter as Router, Switch } from "react-router-dom";
import { withRouter } from "react-router-dom";
import "./login.css";
import { Container, Card, Button, Form, Row } from "react-bootstrap";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loginValue: "",
      passwordValue: "",
      message: "",
    };
    this.handleLoginChange = this.handleLoginChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleLoginChange(event) {
    this.setState({ loginValue: event.target.value });
  }

  handlePasswordChange(event) {
    this.setState({ passwordValue: event.target.value });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    const { history } = this.props;

    if (this.state.passwordValue == "" || this.state.usernameOrEmail == "") {
      this.setState({ message: "Username or password must not be empty" });
      return false;
    }

    const data = {
      usernameOrEmail: this.state.loginValue,
      password: this.state.passwordValue,
    };

    axios({
      method: "post",
      url: "http://localhost:8080/api/auth/signin",
      data: data,
    })
      .then((response) => {
        localStorage.setItem("token", response.data.accessToken);
        localStorage.setItem("usernameOrEmail", this.state.loginValue);
        setTimeout(() => history.push("/home"), 250);
      })
      .catch((e) => this.setState({ message: e.response.data.message }));
  };

  render() {
    return (
      <div className="background">
        <Card bg="dark" border="secondary" className="main-card">
          <Card.Header>
            {" "}
            <div className="main-card-header">TRAINING-APP</div>{" "}
          </Card.Header>
          <Card.Body className="form-card">
            <div>
              <div className="center-form">
                <Form onSubmit={this.handleSubmit} className="form-input">
                  <label>
                    <Form.Control
                      className="input"
                      placeholder="username or email"
                      type="text"
                      value={this.state.loginValue}
                      onChange={this.handleLoginChange}
                    />
                  </label>
                  <br />
                  <label>
                    <Form.Control
                      className="input"
                      placeholder="password"
                      type="text"
                      value={this.state.passwordValue}
                      onChange={this.handlePasswordChange}
                    />
                  </label>
                  <br></br>
                  <div>
                    <Row>
                      <div className="bottom-buttons">
                        <Button
                          className="sign-in-button"
                          variant="secondary"
                          type="submit"
                          size="sm"
                          onClick={this.handleSubmit}
                        >
                          sign in
                        </Button>
                        <Link to="/register" className="ml-2">
                          sign up
                        </Link>
                      </div>
                    </Row>
                  </div>
                </Form>
              </div>
            </div>
            <Row>
              <div className="error-message">{this.state.message}</div>
            </Row>
          </Card.Body>
        </Card>
      </div>
    );
  }
}

export default withRouter(Login);
