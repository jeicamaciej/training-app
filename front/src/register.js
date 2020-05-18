import React from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";

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

  formatResponse(response) {
    if (response.data.message === true) {
      this.setState({ message: response.data.message });
    }
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
        console.log(response);
        this.setState({ message: response.data.message + ", redirecting" });
        setTimeout(() => history.push("/login"), 2000);
      })
      .catch((error) => {
        this.setState({ message: error.response.data.message });
      });
  };

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <label>
            <input
              placeholder="name"
              type="text"
              value={this.state.name}
              onChange={this.handleNameChange}
            />
          </label>
          <br />
          <label>
            <input
              placeholder="username"
              type="text"
              value={this.state.username}
              onChange={this.handleUsernameChange}
            />
          </label>
          <br />
          <label>
            <input
              placeholder="email"
              type="text"
              value={this.state.email}
              onChange={this.handleEmailChange}
            />
          </label>
          <br />
          <label>
            <input
              placeholder="password"
              type="text"
              value={this.state.password}
              onChange={this.handlePasswordChange}
            />
          </label>
          <button type="submit" onClick={this.handleSubmit}>
            sign up
          </button>
        </form>
        <div>{this.state.message}</div>
      </div>
    );
  }
}
export default withRouter(Register);
