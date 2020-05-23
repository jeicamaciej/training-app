import React from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";
import TrainingService from "./TrainingService";
import Training from "./training";
import TrainingModal from "./trainingModal";

class Day extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      token: localStorage.getItem("token").toString(),
      date: new Date().toISOString().slice(0, 10),
      day: {},
      trainings: {},
      trainings1: [],
      data: {},
      isDataPresent: false,
    };
  }

  getDay = () => {
    axios({
      method: "get",
      url: "http://localhost:8080/api/day/date/" + this.state.date,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "Application/json",
        Authorization: `Bearer ${this.state.token}`,
      },
    }).then((response) => {
      const newDay = {
        trainings: response.data.training,
        date: response.data.date,
      };
      this.setState({ day: newDay });
    });
  };

  addEmptyTraining = () => {
    axios({
      method: "post",
      url: "http://localhost:8080/api/training/new/" + this.state.date,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "Application/json",
        Authorization: `Bearer ${this.state.token}`,
      },
    }).then((response) => console.log(response));
    window.location.reload();
  };

  componentDidMount() {
    this.getDay();
  }

  render() {
    return (
      <div>
        <div>{this.state.day.date}</div>
        <br></br>
        <div>{/* <a>{<ul>{this.state.trainings}</ul>}</a> */}</div>
        <br></br>
        <div>
          <Training />
        </div>
        <div>
          {/* <button onClick={() => this.addEmptyTraining()}>add training</button> */}
        </div>
        <div></div>
      </div>
    );
  }
}

export default withRouter(Day);
