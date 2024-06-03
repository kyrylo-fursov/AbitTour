import { useState } from "react";

export function CalculatorPage() {
  const [result, setResult] = useState(null);
  const [isSubmitted, setIsSubmitted] = useState(false);

  return (
    <>
      <Calculator setResult={setResult} setIsSubmitted={setIsSubmitted} />
      {isSubmitted && <CalculatorResult result={result} />}
    </>
  );
}

function Calculator({ setResult, setIsSubmitted }) {
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
    regional: "1",
    industry: "1",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const {
      subj1,
      subj1_coeff,
      subj2,
      subj2_coeff,
      subj3,
      subj3_coeff,
      subj4,
      subj4_coeff,
      creative,
      creative_coeff,
      efvv,
      efvv_coeff,
      regional,
      industry,
    } = formValues;

    const values = [subj1, subj2, subj3, subj4, creative, efvv].map(parseFloat);
    const coeffs = [
      subj1_coeff,
      subj2_coeff,
      subj3_coeff,
      subj4_coeff,
      creative_coeff,
      efvv_coeff,
    ].map(parseFloat);

    const weightedSum = values.reduce(
      (sum, val, i) => sum + val * coeffs[i],
      0
    );
    const totalCoeff = coeffs.reduce((sum, coeff) => sum + coeff, 0);
    const total =
      (weightedSum / totalCoeff) * parseFloat(regional) * parseFloat(industry);

    setResult(Math.min(total, 200).toFixed(2));
    setIsSubmitted(true);
  };

  const inputs = [
    { label: "Бал ЗНО/НМТ 1", subj: "subj1", coeff: "subj1_coeff" },
    { label: "Бал ЗНО/НМТ 2", subj: "subj2", coeff: "subj2_coeff" },
    { label: "Бал ЗНО/НМТ 3", subj: "subj3", coeff: "subj3_coeff" },
    { label: "Бал ЗНО/НМТ 4", subj: "subj4", coeff: "subj4_coeff" },
    {
      label: "Бал за творчий конкурс",
      subj: "creative",
      coeff: "creative_coeff",
    },
    { label: "Бал ЄФВВ", subj: "efvv", coeff: "efvv_coeff" },
  ];

  return (
    <>
      <div className="section-wrapper search-form-wrapper calc-wrapper">
        <h1>Розрахунок конкурсного балу</h1>
        <div className="calc-header">
          <p>Складова</p>
          <div className="calc-header_right">
            <p>Бал</p>
            <p>Коефіцієнт</p>
          </div>
        </div>
        <hr></hr>
        <form className="calc-form" id="calculatorForm" onSubmit={handleSubmit}>
          {inputs.map(({ label, subj, coeff }) => (
            <div className="form_line" key={subj}>
              <label htmlFor={subj}>{label}</label>
              <div className="form_line_inputs">
                <input
                  className="calc_num-input"
                  type="number"
                  id={subj}
                  name={subj}
                  required
                  placeholder="150"
                  value={formValues[subj]}
                  onChange={handleChange}
                />
                <input
                  className="calc_num-input"
                  type="number"
                  id={coeff}
                  name={coeff}
                  step="0.1"
                  min="0"
                  max="1"
                  required
                  placeholder="0.5"
                  value={formValues[coeff]}
                  onChange={handleChange}
                />
              </div>
            </div>
          ))}
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
                value={formValues.regional}
                onChange={handleChange}
              />
            </div>
          </div>
          <div className="form_line">
            <label htmlFor="industry">Галузевий коефіцієнт</label>
            <div className="form_line_inputs">
              <input
                className="calc_num-input"
                type="number"
                id="industry"
                name="industry"
                step="0.1"
                min="0"
                max="1"
                required
                value={formValues.industry}
                onChange={handleChange}
              />
            </div>
          </div>
          <button className="button-default" type="submit">
            Розрахувати
          </button>
        </form>
      </div>
    </>
  );
}

function CalculatorResult({ result }) {
  return (
    <div
      className="section-wrapper score_calc_result search-form-wrapper"
      id="score_calc_result"
    >
      <h1>Результат</h1>
      {result !== null && <p>Ваш конкурсний бал: {result}</p>}
    </div>
  );
}
