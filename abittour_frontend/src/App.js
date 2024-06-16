import "./index.css";
import { Header } from "./components/Header";

// TODO: need to create custom Selects with/without input, that are not OS dependant (default <select> is and is difficult to style)
// TODO: fix popup position
// TODO: dont show offers until the form is submited, show relevant message if no offers are found
// TODO: fix attendants button
// TODO: fix offer right column positioning

export const jwt_token =
  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlckBnbWFpbC5jb20iLCJpYXQiOjE3MTg1Mzg4NzIsImV4cCI6MTcxODU3NDg3Mn0.dZfCkA8CtOzGywiZQT075LVLOap5zLGDdLJVdgrYDSA";

function App() {
  return (
    <>
      <Header />
    </>
  );
}

export default App;
