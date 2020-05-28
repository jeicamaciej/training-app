import React from "react";
import axios from "axios";
import { Route, Link, BrowserRouter as Router, Switch } from "react-router-dom";
import { withRouter } from "react-router-dom";
import "./login.css";
import { Container, Card, Button, Form } from "react-bootstrap";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loginValue: "",
      passwordValue: "",
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

    const data = {
      usernameOrEmail: this.state.loginValue,
      password: this.state.passwordValue,
    };
    axios({
      method: "post",
      url: "http://localhost:8080/api/auth/signin",
      data: data,
    }).then((response) => {
      localStorage.setItem("token", response.data.accessToken);
      localStorage.setItem("usernameOrEmail", this.state.loginValue);
      //console.log(localStorage.getItem("token"));
      //console.log(localStorage.getItem("usernameOrEmail"));
      setTimeout(() => history.push("/home"), 250);
    });
  };

  render() {
    return (
      <div className="background">
        <Card bg="dark" border="secondary" className="main-card">
          <Card.Header className="main-card-header"> AAAAA </Card.Header>
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
                    <button type="submit" onClick={this.handleSubmit}>
                      sign in
                    </button>
                    <li>
                      <Link to="/register">sign up</Link>
                    </li>
                  </div>
                </Form>
              </div>
            </div>
          </Card.Body>
        </Card>
      </div>
    );
  }
}

export default withRouter(Login);
