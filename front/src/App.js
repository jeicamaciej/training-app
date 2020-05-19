import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Register from "./register";
import Login from "./login";
import Day from "./day";

function App() {
  return (
    <div>
      <Router>
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/">
            <Day />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
