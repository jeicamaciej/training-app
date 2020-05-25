import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Register from "./register";
import Login from "./login";
import Day from "./day";
import Dayy from "./dayy";

function App() {
  return (
    <div>
      <Router>
        <Switch>
          <Route path="/home">
            <Day />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          {/* <Route path="/">
            <Day />
          </Route> */}
        </Switch>
      </Router>
    </div>
  );
}

export default App;
