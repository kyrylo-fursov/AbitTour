import "./index.css";

import React, { useState, useEffect, useRef } from "react";

import { Header } from "./components/Header";

// TODO: need to create custom Selects with/without input, that are not OS dependant (default <select> is and is difficult to style)
// TODO: fix popup position
// TODO: dont show offers until the form is submited, show relevant message if no offers are found
// TODO: fix attendants button
// TODO: fix offer right column positioning

function App() {
  return (
    <>
      <Header />
    </>
  );
}

export default App;
