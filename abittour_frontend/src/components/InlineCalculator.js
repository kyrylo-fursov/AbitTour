import React, { useState, useEffect } from "react";
import { jwt_token } from "../App";

export function InlineCalculator() {
  return (
    <details className="calc_details details_inline_calc">
      <summary className="calc_details-summary inline_calc-summary">
        Розрахувати КБ для цієї пропозиції
      </summary>
      <Calc />
    </details>
  );
}

const Calc = () => {
  const [formData, setFormData] = useState({
    ukr: "",
    ukr_coeff: "1",
    math: "",
    math_coeff: "1",
    history: "",
    history_coeff: "1",
    optional_subj_select: "",
    optional_subj: "",
    optional_subj_coeff: "1",
    efvv: "0",
    efvv_coeff: "0",
    ou: "0",
    regional: "1",
    industry: "1",
  });

  const subjectNames = {
    FOREIGN_LANGUAGE: "Іноземна мова",
    UKRAINIAN_LITERATURE: "Українська література",
    GEOGRAPHY: "Географія",
    HISTORY_OF_UKRAINE: "Історія України",
    CHEMISTRY: "Хімія",
    CREATIVE_COMPETITION: "Творчий конкурс",
    PHYSICS: "Фізика",
    MATHEMATICS: "Математика",
    BIOLOGY: "Біологія",
    UKRAINIAN_LANGUAGE: "Українська мова",
  };

  const excludedSubjects = [
    "HISTORY_OF_UKRAINE",
    "UKRAINIAN_LANGUAGE",
    "MATHEMATICS",
  ];

  const [subjects, setSubjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [result, setResult] = useState(null);

  useEffect(() => {
    const fetchSpecialityData = async () => {
      try {
        const result = await fetchSpeciality(220);
        console.log(result);
        if (result && result.subjectCoefs) {
          const processedSubjects = result.subjectCoefs.map((subject) => ({
            id: subject.id,
            name: subjectNames[subject.subject] || subject.subject,
            coefficient: subject.coefficient,
            subject: subject.subject,
          }));
          setSubjects(processedSubjects);
        } else {
          throw new Error("Invalid response structure");
        }
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchSpecialityData();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "optional_subj_select") {
      const selectedSubject = subjects.find(
        (subject) => subject.subject === value
      );

      setFormData({
        ...formData,
        optional_subj_select: value, // Update optional_subj_select with the selected value
        optional_subj: value, // Also update optional_subj to keep track of the selected subject
        optional_subj_coeff: selectedSubject
          ? selectedSubject.coefficient
          : formData.optional_subj_coeff,
      });
    } else if (name === "optional_subj_coeff") {
      setFormData({
        ...formData,
        optional_subj_coeff: value,
      });
    } else if (name === "efvv") {
      setFormData({
        ...formData,
        efvv: value,
        efvv_coeff: value !== "" && parseFloat(value) !== 0 ? "1" : "0",
      });
    } else {
      setFormData({
        ...formData,
        [name]: value,
      });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const {
      ukr,
      ukr_coeff,
      math,
      math_coeff,
      history,
      history_coeff,
      optional_subj,
      optional_subj_coeff,
      efvv,
      efvv_coeff,
      ou,
      regional,
      industry,
    } = Object.fromEntries(
      Object.entries(formData).map(([key, value]) => [
        key,
        isNaN(parseFloat(value)) ? 0 : parseFloat(value),
      ])
    );

    const total =
      ((ukr * parseFloat(ukr_coeff) +
        math * parseFloat(math_coeff) +
        history * parseFloat(history_coeff) +
        optional_subj * parseFloat(optional_subj_coeff) +
        efvv * parseFloat(efvv_coeff)) /
        (parseFloat(ukr_coeff) +
          parseFloat(math_coeff) +
          parseFloat(history_coeff) +
          parseFloat(optional_subj_coeff) +
          parseFloat(efvv_coeff)) +
        parseFloat(ou)) *
      parseFloat(regional) *
      parseFloat(industry);

    const result = Math.min(total, 200).toFixed(2);
    setResult(result);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div>
      <form onSubmit={handleSubmit} className="calc-form inline-calc-form">
        <div className="form_line">
          <label htmlFor="ukr">Українська мова</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              placeholder="200"
              min="0"
              max="200"
              required
              type="number"
              name="ukr"
              id="ukr"
              value={formData.ukr}
              onChange={handleChange}
            />
            <input
              type="number"
              className="calc_num-input"
              name="ukr_coeff"
              id="ukr_coeff"
              readOnly
              value={formData.ukr_coeff}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form_line">
          <label htmlFor="ukr">Математика</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              placeholder="200"
              min="0"
              max="200"
              required
              type="number"
              name="math"
              id="math"
              value={formData.math}
              onChange={handleChange}
            />
            <input
              className="calc_num-input"
              type="number"
              name="math_coeff"
              id="math_coeff"
              readOnly
              value={formData.math_coeff}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form_line">
          <label htmlFor="ukr">Історія України</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              placeholder="200"
              min="0"
              max="200"
              required
              type="number"
              name="history"
              id="history"
              value={formData.history}
              onChange={handleChange}
            />
            <input
              className="calc_num-input"
              type="number"
              name="history_coeff"
              id="history_coeff"
              readOnly
              value={formData.history_coeff}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form_line">
          <select
            className="select_optional_subj"
            name="optional_subj_select"
            id="optional_subj_select"
            value={formData.optional_subj_select}
            onChange={handleChange}
          >
            <option value="" disabled>
              Оберіть додатковий предмет
            </option>
            {subjects
              .filter((subject) => !excludedSubjects.includes(subject.subject))
              .map((subject) => (
                <option key={subject.id} value={subject.subject}>
                  {subject.name}
                </option>
              ))}
          </select>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              placeholder="200"
              min="0"
              max="200"
              required
              type="number"
              name="optional_subj"
              id="optional_subj"
              value={formData.optional_subj}
              onChange={handleChange}
            />
            <input
              className="calc_num-input"
              type="number"
              name="optional_subj_coeff"
              id="optional_subj_coeff"
              readOnly
              value={formData.optional_subj_coeff}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form_line">
          <label htmlFor="efvv">Бал ЄФВВ</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="efvv"
              name="efvv"
              min="0"
              max="200"
              placeholder="150"
              value={formData.efvv}
              onChange={handleChange}
            />
            <input
              className="calc_num-input"
              type="number"
              id="efvv_coeff"
              name="efvv_coeff"
              step="0.1"
              min="0"
              max="1"
              required
              placeholder="0.5"
              value={formData.efvv_coeff}
              readOnly
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form_line">
          <label htmlFor="efvv">Бал ОУ</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input calc_num-input_ou"
              type="number"
              id="ou"
              name="ou"
              min="0"
              max="200"
              placeholder="150"
              value={formData.ou}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form_line">
          <label htmlFor="regional">Регіональний коефіцієнт</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="regional"
              name="regional"
              step="0.1"
              min="0"
              max="1"
              required
              value={formData.regional}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="form_line">
          <label htmlFor="industry">Галузевий коефіцієнт</label>
          <div className="form_line_inputs">
            <select
              className="calc_num-input"
              id="industry"
              name="industry"
              required
              value={formData.industry}
              onChange={handleChange}
            >
              <option value="1">1</option>
              <option value="1.02">1.02</option>
            </select>
          </div>
        </div>
        <div>
          <button className="button-default calc_button" type="submit">
            Розрахувати
          </button>
        </div>
      </form>
      {result && (
        <div className="calc-result">
          <h2>Ваш КБ:</h2>
          <p>{result}</p>
        </div>
      )}
    </div>
  );
};

export const fetchSpeciality = async (id) => {
  if (!jwt_token) {
    throw new Error("No JWT token found");
  }

  try {
    const response = await fetch(`/specialities/${id}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwt_token}`,
      },
    });

    if (!response.ok) {
      const errorDetails = await response.text();
      throw new Error(
        `Network response was not ok: ${response.status} ${response.statusText} - ${errorDetails}`
      );
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Fetch operation failed:", error);
    throw error;
  }
};
