import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function User(props) {
  const [id, setId] = useState(0);
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [isPropsPresent, setPropsPresent] = useState(false);

  useEffect(() => {
    setId(props.id);
    setUsername(props.username);
    setEmail(props.email);
    setPropsPresent(true);
    console.log(id, username, email);
  });

  return (
    <div>
      <div>
        {/* {id} */}
        username: {username} email: {email}
      </div>
    </div>
  );
}
export default User;
