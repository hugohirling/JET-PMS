import './App.css';
import Layout from './containers/Layout';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Home from "./pages/Home";
import Participants from "./pages/Participants";
import Equipment from "./pages/Equipment";
import Events from "./pages/Events";
import NoPage from "./pages/NoPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="participants" element={<Participants />} />
          <Route path="equipment" element={<Equipment />} />
          <Route path="events" element={<Events />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
