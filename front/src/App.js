import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Register from "./register";
import Login from "./login";

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
        </Switch>
      </Router>
    </div>
  );
}

export default App;
