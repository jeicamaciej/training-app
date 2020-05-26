import React, { useState, useEffect } from "react";
import { withRouter, Link } from "react-router-dom";

class PageNotFound extends React.Component {
  render() {
    console.log("a");
    return (
      <div>
        404
        <br></br>
        <Link to="/login">back to sign in</Link>
      </div>
    );
  }
}

export default withRouter(PageNotFound);
