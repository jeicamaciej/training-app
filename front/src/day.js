import React from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";
import TrainingService from "./TrainingService";

class Day extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      token: localStorage.getItem("token").toString(),
      date: new Date().toISOString().slice(0, 10),
      day: {},
      trainings: [],
      trainings1: [],
      data: {},
      isDataPresent: false,
    };
  }

  getDay = () => {
    function getDate() {
      return new Date().toISOString().slice(0, 10);
    }
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
        trainings: response.data.trainings,
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
    TrainingService.getTrainings(this.state.date, this.state.token).then(
      (t) => {
        this.setState({ trainings: t });
      }
    );
  }

  componentWillMount() {
    this.getDay();

    TrainingService.test(this.state.date, this.state.token).then((t) => {
      this.setState({ trainings1: t });
      //this.setState(() => ({ isDataPresent: true }));
    });
  }

  render() {
    if (this.state.data === false) {
      return (
        <div>
          <div>{this.state.day.date}</div>
          <br></br>
          <div>
            <a>{<ul>{this.state.trainings}</ul>}</a>
          </div>
          <br></br>
          <div>
            <button onClick={() => this.addEmptyTraining()}>
              add training
            </button>
          </div>
        </div>
      );
    }
    return (
      <div>
        <div>{this.state.day.date}</div>
        <br></br>
        <div>
          <a>
            {
              <ul>
                {() => TrainingService.formatResponse(this.state.trainings1)}
              </ul>
            }
          </a>
        </div>
        <br></br>
        <div>
          <button onClick={() => this.addEmptyTraining()}>add training</button>
        </div>
      </div>
    );
  }
}

export default withRouter(Day);
