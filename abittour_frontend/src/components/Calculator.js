import { useState } from "react";

export function CalculatorPage() {
  const [result, setResult] = useState(null);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [formula, setFormula] = useState("");

  return (
    <>
      <Calculator
        setResult={setResult}
        setIsSubmitted={setIsSubmitted}
        setFormula={setFormula}
      />
      {isSubmitted && <CalculatorResult result={result} formula={formula} />}
    </>
  );
}

function Calculator({ setResult, setIsSubmitted, setFormula }) {
  const [formValues, setFormValues] = useState({
    subj1: "",
    subj1_coeff: "",
    subj2: "",
    subj2_coeff: "",
    subj3: "",
    subj3_coeff: "",
    subj4: "",
    subj4_coeff: "",
    creative: "0",
    creative_coeff: "0",
    efvv: "0",
    efvv_coeff: "0",
    ou: "0",
    regional: "1",
    industry: "1",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    const updatedFormValues = { ...formValues, [name]: value };
    if (name === "efvv" && (value === "" || value === "0")) {
      updatedFormValues.efvv_coeff = "0";
    } else {
      updatedFormValues.efvv_coeff = "1";
    }
    setFormValues(updatedFormValues);
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
      ou,
      regional,
      industry,
    } = Object.fromEntries(
      Object.entries(formValues).map(([key, value]) => [
        key,
        isNaN(parseFloat(value)) ? 0 : parseFloat(value),
      ])
    );

    const total =
      ((subj1 * subj1_coeff +
        subj2 * subj2_coeff +
        subj3 * subj3_coeff +
        subj4 * subj4_coeff +
        creative * creative_coeff +
        efvv * efvv_coeff) /
        (subj1_coeff +
          subj2_coeff +
          subj3_coeff +
          subj4_coeff +
          creative_coeff +
          efvv_coeff) +
        ou) *
      regional *
      industry;

    const res = Math.min(total, 200).toFixed(2);

    const formula = `((${subj1_coeff}×${subj1} + ${subj2_coeff}×${subj2} + ${subj3_coeff}×${subj3} + ${subj4_coeff}×${subj4} + ${creative_coeff}×${creative} + ${efvv_coeff}×${efvv}) / (${subj1_coeff}+${subj2_coeff}+${subj3_coeff}+${subj4_coeff}+${creative_coeff}+${efvv_coeff}) + ${ou}) × ${regional} × ${industry} = ${res}`;

    setResult(res);
    setFormula(formula);
    setIsSubmitted(true);
  };

  const inputs = [
    {
      label: "Бал ЗНО/НМТ 1",
      subj: "subj1",
      coeff: "subj1_coeff",
      min: "0",
      max: "200",
      required: true,
      classNames: "calc_num-input",
    },
    {
      label: "Бал ЗНО/НМТ 2",
      subj: "subj2",
      coeff: "subj2_coeff",
      min: "0",
      max: "200",
      required: true,
      classNames: "calc_num-input",
    },
    {
      label: "Бал ЗНО/НМТ 3",
      subj: "subj3",
      coeff: "subj3_coeff",
      min: "0",
      max: "200",
      required: true,
      classNames: "calc_num-input",
    },
    {
      label: "Бал ЗНО/НМТ 4",
      subj: "subj4",
      coeff: "subj4_coeff",
      min: "0",
      max: "200",
      required: true,
      classNames: "calc_num-input",
    },
    {
      label: "Бал за творчий конкурс",
      subj: "creative",
      coeff: "creative_coeff",
      min: "0",
      max: "200",
      required: false,
      classNames: "calc_num-input",
    },
    {
      label: "Бал ЄФВВ",
      subj: "efvv",
      coeff: "efvv_coeff",
      min: "0",
      max: "200",
      required: false,
      classNames: "calc_num-input",
    },
    {
      label: "Бал ОУ",
      subj: "ou",
      coeff: "none",
      min: "0",
      max: "10",
      required: false,
      classNames: "calc_num-input calc_num-input_ou",
    },
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
          {inputs.map(
            ({ label, subj, coeff, min, max, required, classNames }) => (
              <div className="form_line" key={subj}>
                <label htmlFor={subj}>{label}</label>
                <div className="form_line_inputs">
                  <input
                    className={classNames}
                    type="number"
                    id={subj}
                    name={subj}
                    min={min}
                    max={max}
                    required={required}
                    placeholder="150"
                    value={formValues[subj]}
                    onChange={handleChange}
                  />
                  {coeff !== "none" && (
                    <input
                      className={classNames}
                      type="number"
                      id={coeff}
                      name={coeff}
                      step="0.1"
                      min="0"
                      max="1"
                      required={required}
                      placeholder="0.5"
                      value={formValues[coeff]}
                      readOnly={coeff === "efvv_coeff"}
                      onChange={handleChange}
                    />
                  )}
                </div>
              </div>
            )
          )}
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
              <select
                className="calc_num-input"
                id="industry"
                name="industry"
                required
                value={formValues.industry}
                onChange={handleChange}
              >
                <option value="1">1</option>
                <option value="1.02">1.02</option>
              </select>
            </div>
          </div>
          <button className="button-default calc_button" type="submit">
            Розрахувати
          </button>
        </form>
        <ClaculatorDetails></ClaculatorDetails>
      </div>
    </>
  );
}

function ClaculatorDetails() {
  return (
    <details className="calc_details">
      <summary className="calc_details-summary">
        Як розраховується конкурсний бал?
      </summary>
      <p>
        Конкурсний бал на бакалавра (магістра медичного, фармацевтичного або
        ветеринарного спрямування) за результатами <strong>НМТ 2024</strong>,
        матурального іспиту, творчого конкурсу або ЄФВВ розраховується за такою
        формулою
      </p>
      <p>
        Конкурсний бал (КБ) ={" "}
        <strong>
          (К1 × П1 + К2 × П2 + К3 × П3 + К4 × П4 + КТ × TK + КЄФВВ × БЄФВВ) /
          (К1 + К2 + К3+ К4 + КТ + КЄФВВ) + ОУ
        </strong>
        , <br />
        де:
      </p>
      <ul>
        <li>
          <strong>П1, П2, П3, П4 </strong>– оцінки з першого, другого, третього
          та четвертого предметів НМТ;
        </li>
        <li>
          <strong>ТК</strong> – оцінка творчого конкурсу (у передбачених
          випадках); · БЄФВВ – оцінка єдиного фахового вступного випробування (у
          передбачених випадках);
        </li>
        <li>
          <strong>ОУ </strong>– бал за успішне закінчення у рік вступу
          підготовчих курсів закладу вищої освіти для вступу до нього за шкалою
          від 0 до 10 балів – у разі вступу на спеціальності (спеціалізації,
          предметні спеціальності), зазначені в{" "}
          <a href="https://osvita.ua/consultations/bachelor/68927/">
            Переліку спеціальностей
          </a>
          , яким надається особлива підтримка.
        </li>
      </ul>
      <p>
        Якщо конкурсний бал вступника при цьому перевищує 200, він
        встановлюється таким, що дорівнює 200.
      </p>
      <p>
        <a href="https://osvita.ua/consultations/bachelor/10025/">
          Вагові коефіцієнти
        </a>{" "}
        К1, К2, К3, К4 оцінок предметів для кожної спеціальності (спеціалізації,
        предметної спеціальності) та ваговий коефіцієнт КТ оцінки творчого
        конкурсу визначені в додатку 7 до{" "}
        <a href="https://osvita.ua/legislation/Vishya_osvita/9990/">
          Порядку прийому
        </a>
        .
      </p>
      <p>
        Ваговий коефіцієнт КЄФВВ оцінки ЄФВВ дорівнює 1 для тих вступників, хто
        складав єдине фахове вступне випробування, та дорівнює 0 в інших
        випадках.
      </p>
      <p>
        Детальніше про{" "}
        <a href="https://osvita.ua/consultations/bachelor/44528/">
          Розрахунок конкурсного бала при вступі до вишу
        </a>
        .
      </p>
    </details>
  );
}

function CalculatorResult({ result, formula }) {
  return (
    <div
      className="section-wrapper score_calc_result search-form-wrapper"
      id="score_calc_result"
    >
      <h1>Результат</h1>
      {result !== null && (
        <>
          <p>
            Ваш конкурсний бал: <strong>{result}</strong>
          </p>
          <p>Формула розрахунку:</p>
          <p>{formula}</p>
        </>
      )}
    </div>
  );
}
