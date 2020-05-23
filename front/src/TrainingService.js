// import React, { useState } from "react";
// import axios from "axios";
// import { Route, Link, BrowserRouter as Router, Switch } from "react-router-dom";
// import { withRouter } from "react-router-dom";

// const TrainingService = {
//   token: localStorage.getItem("token").toString(),
//   date: new Date().toISOString().slice(0, 10),

//   test: async (date, token) => {
//     const response = await axios.get(
//       "http://localhost:8080/api/training/all/" + date,
//       {
//         params: {},
//         headers: {
//           "Access-Control-Allow-Origin": "*",
//           "Content-type": "Application/json",
//           Authorization: `Bearer ${token}`,
//         },
//       }
//     );
//     //const data = await response.data;
//     const data = await Promise.all(response.data);
//     const trainings = [];
//     data.forEach((element) => {
//       const training = {
//         exercises: element.exercises,
//         desc: element.desc,
//       };
//       trainings.push(training);
//     });
//     return trainings;
//   },

//   formatResponse: (trainings) => {
//     trainings.forEach((training) => {
//       training.exercises = training.exercises.map((e) => <li>{e}</li>);
//     });

//     const mappedTrainings = trainings.map((t) => (
//       <li>
//         {t.exercises} {t.desc}
//       </li>
//     ));
//     return mappedTrainings;
//   },

//   getTrainings: async (date, token) => {
//     const response = await axios.get(
//       "http://localhost:8080/api/training/all/" + date,
//       {
//         params: {},
//         headers: {
//           "Access-Control-Allow-Origin": "*",
//           "Content-type": "Application/json",
//           Authorization: `Bearer ${token}`,
//         },
//       }
//     );
//     const data = await response.data;
//     console.log(data);
//     const trainings = [];
//     data.forEach((element) => {
//       const training = {
//         exercises: element.exercises,
//         desc: element.desc,
//       };
//       //console.log(training.exercises);

//       //TU SERIA CHYBA

//       training.exercises = training.exercises.map((e) => (
//         <li>{(e.id, e.name)}</li>
//       ));
//       trainings.push(training);
//     });
//     console.log(trainings);
//     const mappedTrainings = trainings.map((t) => (
//       <li>
//         {t.exercises} {t.desc}
//       </li>
//     ));
//     //console.log(mappedTrainings);
//     return mappedTrainings;
//   },
// };
// export default TrainingService;
