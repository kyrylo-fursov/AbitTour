import { Route, Router, Link, Routes, Outlet } from "react-router-dom";
import { SearchPage } from "./SearchPage";
import { CalculatorPage } from "./Calculator";
import { OfferPage } from "./OfferPage";
import { StarredPage } from "./Starred";

export function Header() {
  return (
    <header>
      <nav className="header">
        <Link className="abit_logo" to="/">
          AbitTour
        </Link>
        <ul>
          <li>
            <Link to="/">Пошук КП</Link>
          </li>
          <li>
            <Link to="/starred">Збережені КП</Link>
          </li>
          <li>
            <Link to="/calculator">Калькулятор КБ</Link>
          </li>
        </ul>
      </nav>
      <Routes>
        <Route path="/" element={<SearchPage />} />
        <Route path="/calculator" element={<CalculatorPage />} />
        <Route path="/:id" element={<OfferPage />} />
        <Route path="/starred" element={<StarredPage />} />
      </Routes>
    </header>
  );
}
