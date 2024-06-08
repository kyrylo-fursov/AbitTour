import { Route, Router, Link, Routes, Outlet } from "react-router-dom";
import { SearchPage } from "./Search";
import { CalculatorPage } from "./Calculator";
import { OfferPage } from "./OfferPage";

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
            <Link to="/calculator">Калькулятор КБ</Link>
          </li>
        </ul>
      </nav>
      <Routes>
        <Route path="/" element={<SearchPage />} />
        <Route path="/calculator" element={<CalculatorPage />} />
        <Route path="/offer" element={<OfferPage />} />
      </Routes>
    </header>
  );
}