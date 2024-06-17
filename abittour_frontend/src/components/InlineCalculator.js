import React, { useState, useEffect } from "react";
import {
  subjectNames,
  eduLvlNames,
  enrollmentBaseNamesm,
  eduFormNames,
  mapToNiceNames,
  mainSubjects,
} from "../utils/mappings";

export const InlineCalculator = ({ offer }) => {
  if (!offer) return null;

  // Map offer to nice names
  offer = mapToNiceNames(offer);

  return (
    <details className="calc_details details_inline_calc">
      <summary className="calc_details-summary inline_calc-summary">
        Розрахувати КБ для цієї пропозиції
      </summary>
      <Calc offer={offer} />
    </details>
  );
};

const Calc = ({ offer }) => {
  const [formData, setFormData] = useState({
    ukr: "",
    ukr_coeff: "1",
    math: "",
    math_coeff: "1",
    history: "",
    history_coeff: "1",
    optional_subj_select: "",
    optional_subj: "",
    optional_subj_coeff: "0",
    efvv: "0",
    efvv_coeff: "0",
    ou: "0",
  });

  const [subjects, setSubjects] = useState([]);

  const [error, setError] = useState(null);
  const [result, setResult] = useState(null);

  // Set initial state using the offer prop
  useEffect(() => {
    if (offer) {
      const processedSubjects = offer.speciality.subjectCoefs.map((coef) => ({
        id: coef.id,
        name: coef.subject,
        coefficient: coef.coefficient,
        subject: coef.subject,
      }));
      setSubjects(processedSubjects);

      // Find the coefficients for the specific subjects
      const ukrCoeff = processedSubjects.find(
        (subject) => subject.subject === "UKRAINIAN_LANGUAGE"
      )?.coefficient;
      const mathCoeff = processedSubjects.find(
        (subject) => subject.subject === "MATHEMATICS"
      )?.coefficient;
      const historyCoeff = processedSubjects.find(
        (subject) => subject.subject === "HISTORY_OF_UKRAINE"
      )?.coefficient;

      // Set the coefficients in formData state
      setFormData((prevFormData) => ({
        ...prevFormData,
        ukr_coeff: ukrCoeff || prevFormData.ukr_coeff,
        math_coeff: mathCoeff || prevFormData.math_coeff,
        history_coeff: historyCoeff || prevFormData.history_coeff,
        optional_subj_coeff: prevFormData.optional_subj_coeff,
      }));
    }
  }, [offer]);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "optional_subj_select") {
      const selectedSubject = subjects.find(
        (subject) => subject.subject === value
      );

      // Preserve the value of optional_subj when changing optional_subj_select
      const optionalSubjValue = formData.optional_subj || value;

      setFormData({
        ...formData,
        optional_subj_select: value,
        optional_subj: optionalSubjValue, // Preserve optional_subj value
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
      1.0 *
      1.0;

    const result = Math.min(total, 200).toFixed(2);
    setResult(result);
  };

  if (error) return <div>Error: {error.message}</div>;

  return (
    <div>
      {/* <p>Коефіцієнти предметів:</p>
      <ul>
        {offer.speciality.subjectCoefs.map((coef) => (
          <li key={coef.id}>
            {coef.subject}: {coef.coefficient}
          </li>
        ))}
      </ul> */}
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
            required
            value={formData.optional_subj_select}
            onChange={handleChange}
          >
            <option value="" disabled>
              Оберіть додатковий предмет
            </option>
            {subjects
              .filter(
                (subject) =>
                  !Object.keys(mainSubjects).includes(subject.subject)
              )
              .map((subject) => (
                <option key={subject.id} value={subject.subject}>
                  {subjectNames[subject.subject] || subject.subject}
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
