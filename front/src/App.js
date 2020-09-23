import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Register from "./register";
import Login from "./login";
import Day from "./day";
import PageNotFound from "./pagenotfound";
import Admin from "./admin";

function App() {
  return (
    <div>
      <Router>
        <Switch>
          <Route path="/home">
            <Day />
          </Route>
          <Route path="/admin">
            <Admin />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="*">
            <PageNotFound />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
