import { useState } from "react";

export function CalculatorPage() {
  const [formValues, setFormValues] = useState({
    subj1: "",
    subj1_coeff: "",
    subj2: "",
    subj2_coeff: "",
    subj3: "",
    subj3_coeff: "",
    subj4: "",
    subj4_coeff: "",
    creative: "",
    creative_coeff: "",
    efvv: "",
    efvv_coeff: "",
    regional: 1.0,
    industry: 1.0,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({
      ...formValues,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Calculation logic goes here
    console.log(formValues);
  };

  return (
    <div className="section-wrapper">
      <h1>Розрахунок конкурсного балу</h1>
      <form className="calc-form" id="calculatorForm" onSubmit={handleSubmit}>
        <div className="form_line">
          <label htmlFor="subj1">Бал ЗНО/НМТ 1:</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="subj1"
              name="subj1"
              required
              placeholder="150"
              value={formValues.subj1}
              onChange={handleChange}
            />
            <p>x</p>
            <input
              className="calc_num-input"
              type="number"
              id="subj1_coeff"
              name="subj1_coeff"
              step="0.1"
              min="0"
              max="1"
              required
              placeholder="0.5"
              value={formValues.subj1_coeff}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="form_line">
          <label htmlFor="subj2">Бал ЗНО/НМТ 2:</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="subj2"
              name="subj2"
              required
              placeholder="150"
              value={formValues.subj2}
              onChange={handleChange}
            />
            <p>x</p>
            <input
              className="calc_num-input"
              type="number"
              id="subj2_coeff"
              name="subj2_coeff"
              step="0.1"
              min="0"
              max="1"
              required
              placeholder="0.3"
              value={formValues.subj2_coeff}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="form_line">
          <label htmlFor="subj3">Бал ЗНО/НМТ 3:</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="subj3"
              name="subj3"
              required
              placeholder="150"
              value={formValues.subj3}
              onChange={handleChange}
            />
            <p>x</p>
            <input
              className="calc_num-input"
              type="number"
              id="subj3_coeff"
              name="subj3_coeff"
              step="0.1"
              min="0"
              max="1"
              required
              placeholder="0.2"
              value={formValues.subj3_coeff}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="form_line">
          <label htmlFor="subj4">Бал ЗНО/НМТ 4:</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="subj4"
              name="subj4"
              required
              placeholder="150"
              value={formValues.subj4}
              onChange={handleChange}
            />
            <p>x</p>
            <input
              className="calc_num-input"
              type="number"
              id="subj4_coeff"
              name="subj4_coeff"
              step="0.1"
              min="0"
              max="1"
              required
              placeholder="0.1"
              value={formValues.subj4_coeff}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="form_line">
          <label htmlFor="creative">Бал за творчий конкурс:</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="creative"
              name="creative"
              required
              placeholder="150"
              value={formValues.creative}
              onChange={handleChange}
            />
            <p>x</p>
            <input
              className="calc_num-input"
              type="number"
              id="creative_coeff"
              name="creative_coeff"
              step="0.1"
              min="0"
              max="1"
              required
              placeholder="0"
              value={formValues.creative_coeff}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="form_line">
          <label htmlFor="efvv">Бал ЄФВВ:</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="efvv"
              name="efvv"
              required
              placeholder="150"
              value={formValues.efvv}
              onChange={handleChange}
            />
            <p>x</p>
            <input
              className="calc_num-input"
              type="number"
              id="efvv_coeff"
              name="efvv_coeff"
              step="0.1"
              min="0"
              max="1"
              required
              placeholder="0.1"
              value={formValues.efvv_coeff}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="form_line">
          <label htmlFor="regional">Регіональний коефіцієнт:</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="regional"
              name="regional"
              step="0.1"
              min="0"
              max="1"
              value={formValues.regional}
              required
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="form_line">
          <label htmlFor="industry">Галузевий коефіцієнт:</label>
          <div className="form_line_inputs">
            <input
              className="calc_num-input"
              type="number"
              id="industry"
              name="industry"
              step="0.1"
              min="0"
              max="1"
              value={formValues.industry}
              required
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="submit_container">
          <button className="button-default" type="submit">
            Розрахувати
          </button>
        </div>
      </form>
      <div className="score_calc_result" id="score_calc_result">
        {/* Calculation result will be shown here */}
      </div>
    </div>
  );
}
