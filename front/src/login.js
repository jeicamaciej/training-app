import React from "react";
import axios from "axios";
import { Route, Link, BrowserRouter as Router, Switch } from "react-router-dom";
import Register from "./register";
import { Redirect } from "react-router-dom";
import { browserHistory } from "react-router";
import { withRouter } from "react-router-dom";

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
    });
  };

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <label>
            <input
              placeholder="username or email"
              type="text"
              value={this.state.loginValue}
              onChange={this.handleLoginChange}
            />
          </label>
          <br />
          <label>
            <input
              placeholder="password"
              type="text"
              value={this.state.passwordValue}
              onChange={this.handlePasswordChange}
            />
          </label>
          <button type="submit" onClick={this.handleSubmit}>
            sign in
          </button>
          <div>
            <li>
              <Link to="/register">sign up</Link>
            </li>
          </div>
        </form>
      </div>
    );
  }
}

export default withRouter(Login);
