import { useState, useEffect } from "react";
import { CompetitiveOffers } from "./CompetitiveOffer";

import { fetchData, parseUni, parseSpecialty } from "../utils/utils";

import {
  regionsDict,
  specialityDict,
  enrollmentBaseNames,
} from "../utils/mappings";

export function SearchPage() {
  const [filterParams, setFilterParams] = useState({});

  const handleFormSubmit = (formData) => {
    // Update filterParams with formData
    setFilterParams(formData);
  };
  return (
    <div className="search-page">
      <SearchForm onSubmit={handleFormSubmit} />
      <CompetitiveOffers filterParams={filterParams} />
    </div>
  );
}

export function SearchForm({ onSubmit }) {
  const [universities, setUniversities] = useState([]);
  const [specialties, setSpecialties] = useState([]);
  const [regions, setRegions] = useState([]);
  const [bases, setBases] = useState([]);
  const [selectedUniversity, setSelectedUniversity] = useState("");
  const [selectedSpecialty, setSelectedSpecialty] = useState("");
  const [selectedRegion, setSelectedRegion] = useState(""); // State for selected region
  const [selectedBase, setSelectedBase] = useState(""); // State for selected region

  useEffect(() => {
    const fetchUniversities = async () => {
      try {
        const fetchedData = await fetchData("/universities");
        const parsedUniversities = fetchedData.map(parseUni);
        setUniversities(
          parsedUniversities.map((uni) => ({
            value: uni.id,
            label: uni.name,
          }))
        );
      } catch (error) {
        console.error("Error fetching universities:", error);
      }
    };

    const fetchSpecialties = async () => {
      try {
        // const fetchedData = await fetchData("/specialities");
        const parsedSpecialties = specialityDict;
        console.log(parsedSpecialties);
        setSpecialties(
          Object.entries(specialityDict).map(([id, name]) => ({
            value: id,
            label: name,
          }))
        );
      } catch (error) {
        console.error("Error fetching specialties:", error);
      }
    };
    const fetchBases = async () => {
      try {
        // const fetchedData = await fetchData("/specialities");
        setBases(
          Object.entries(enrollmentBaseNames).map(([id, name]) => ({
            value: id,
            label: name,
          }))
        );
      } catch (error) {
        console.error("Error fetching enrollment bases:", error);
      }
    };

    const fetchRegions = async () => {
      try {
        setRegions(
          Object.entries(regionsDict).map(([id, name]) => ({
            value: id,
            label: name,
          }))
        );
      } catch (error) {
        console.error("Error fetching regions:", error);
      }
    };

    fetchUniversities();
    fetchBases();
    fetchSpecialties();
    fetchRegions();
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();

    onSubmit({
      university: selectedUniversity,
      specialty: selectedSpecialty,
      region: selectedRegion, // Include selected region in onSubmit callback
      base: selectedBase,
    });
  };

  return (
    <div className="section-wrapper search-form-wrapper">
      <h1>Пошук конкурсної пропозиції</h1>
      <form className="search-form" onSubmit={handleSubmit}>
        <label>
          <p>Основа для вступу</p>
          <select
            value={selectedBase}
            onChange={(e) => setSelectedBase(e.target.value)}
          >
            <option value="">Оберіть основу для вступу</option>
            {bases.map((base) => (
              <option key={base.value} value={base.value}>
                {base.label}
              </option>
            ))}
          </select>
        </label>
        <label>
          <p>Спеціальність</p>
          <select
            value={selectedSpecialty}
            onChange={(e) => setSelectedSpecialty(e.target.value)}
          >
            <option value="">Оберіть спеціальність</option>
            {specialties.map((specialty) => (
              <option key={specialty.value} value={specialty.label}>
                {specialty.label}
              </option>
            ))}
          </select>
        </label>
        <label>
          <p>Регіон</p>
          <select
            value={selectedRegion}
            onChange={(e) => setSelectedRegion(e.target.value)}
          >
            <option value="">Оберіть регіон</option>
            {regions.map((region) => (
              <option key={region.value} value={region.value}>
                {region.label}
              </option>
            ))}
          </select>
        </label>
        <label>
          <p>Заклад</p>
          <select
            value={selectedUniversity}
            onChange={(e) => setSelectedUniversity(e.target.value)}
          >
            <option value="">Оберіть заклад</option>
            {universities.map((uni) => (
              <option key={uni.value} value={uni.value}>
                {uni.value}- {uni.label}
              </option>
            ))}
          </select>
        </label>
        {/* <div className="search-form_twocolumns_wrapper">
          <label>
            <p>Форма навчання</p>
            <select>
              <option value="">Оберіть форму навчання</option>
            </select>
          </label>
          <label>
            <p>Курс</p>
            <select>
              <option value="">Оберіть курс</option>
            </select>
          </label>
        </div> */}
        <button className="button-default" type="submit">
          Знайти
        </button>
      </form>
    </div>
  );
}
