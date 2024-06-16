import "./index.css";
import { Header } from "./components/Header";

// TODO: need to create custom Selects with/without input, that are not OS dependant (default <select> is and is difficult to style)
// TODO: fix popup position
// TODO: dont show offers until the form is submited, show relevant message if no offers are found
// TODO: fix attendants button
// TODO: fix offer right column positioning

export const jwt_token =
  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlckBnbWFpbC5jb20iLCJpYXQiOjE3MTg0NTQyNDcsImV4cCI6NDg3MjA1NDI0N30.sSKjAW2IJRjKJLc3-D4ISI3lx2hl-xdyo-WhV1Y4dGo";

function App() {
  return (
    <>
      <Header />
    </>
  );
}

export default App;
