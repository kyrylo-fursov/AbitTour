import { useState } from "react";
import { CompetitiveOffers } from "./CompetitiveOffer";

export function SearchPage() {
  return (
    <div className="search-page">
      <SearchForm></SearchForm>
      {/* <CompetitiveOffers></CompetitiveOffers> */}
    </div>
  );
}

function SearchForm() {
  return (
    <div className="section-wrapper search-form-wrapper">
      <h1>Пошук конкурсної пропозиції</h1>
      <form className="search-form">
        <label>
          <p>Основа для вступу</p>
          <Dropdown placeholder="Оберіть основу для вступу" />
        </label>
        <label>
          <p>Спеціальність</p>
          <Dropdown placeholder="Оберіть спеціальність" />
        </label>
        <label>
          <p>Регіон</p>
          <Dropdown placeholder="Оберіть регіон" />
        </label>
        <label>
          <p>Назва закладу або код ЄДЕБО</p>
          <Dropdown placeholder="Оберіть заклад або введіть код ЄДЕБО" />
        </label>
        <div className="search-form_twocolumns_wrapper">
          <label>
            <p>Форма навчання</p>
            <Dropdown placeholder="Оберіть форму навчання" />
          </label>
          <label>
            <p>Курс</p>
            <Dropdown placeholder="Оберіть курс" />
          </label>
        </div>
        <button className="button-default" type="submit">
          Знайти
        </button>
      </form>
    </div>
  );
}

function Dropdown({ placeholder }) {
  const [value, setValue] = useState("");

  const handleChange = (e) => {
    setValue(e.target.value);
  };

  const options = [
    { value: "option1", label: "Option 1" },
    { value: "option2", label: "Option 2" },
    { value: "option3", label: "Option 3" },
    { value: "option4", label: "Option 4" },
    // Add more options as needed
  ];

  return (
    <select value={value} onChange={handleChange}>
      <option value="">{placeholder}</option>
      {options.map((option, index) => (
        <option key={index} value={option.value}>
          {option.label}
        </option>
      ))}
    </select>
  );
}
