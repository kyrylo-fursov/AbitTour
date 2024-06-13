import "./index.css";
import { Header } from "./components/Header";

// TODO: need to create custom Selects with/without input, that are not OS dependant (default <select> is and is difficult to style)
// TODO: fix popup position
// TODO: dont show offers until the form is submited, show relevant message if no offers are found
// TODO: fix attendants button
// TODO: fix offer right column positioning

export const jwt_token =
  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzE4Mjg5MDcwLCJleHAiOjE3MTgzMjUwNzB9.44QIpQafoD89weWoB4_d3xitJknakEWQKKjj3GYIxlo";

function App() {
  return (
    <>
      <Header />
    </>
  );
}

export default App;
