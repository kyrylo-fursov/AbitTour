import React, { useState, useEffect, useRef } from "react";
import ReactDOM from "react-dom";

export function CompetitiveOffers() {
  return (
    <div className="cometitive_offers section-wrapper">
      <h1>Знайдені пропозиції</h1>
      <CompetitiveOfferCompact
        num="122"
        faculty="Комп’ютерні науки"
        specialization="Системи і методи штучного інтелекту, Інтелектуальні сервіс-орієнтовані розподілені обчислювання"
        institution="Національний технічний університет україни «Київський політехнічний інститут імені Ігоря Сікорського»"
        type="Навчально-науковий інститут прикладного системного аналізу"
        maxBudgetPlaces={120}
        totalPlaces={200}
      />
      <hr></hr>
      <CompetitiveOfferCompact
        num="122"
        faculty="Комп’ютерні науки"
        specialization="Системи і методи штучного інтелекту, Інтелектуальні сервіс-орієнтовані розподілені обчислювання"
        institution="Національний технічний університет україни «Київський політехнічний інститут імені Ігоря Сікорського»"
        type="Навчально-науковий інститут прикладного системного аналізу"
        maxBudgetPlaces={120}
        totalPlaces={200}
      />
      <hr></hr>
      <CompetitiveOfferCompact
        num="122"
        faculty="Комп’ютерні науки"
        specialization="Системи і методи штучного інтелекту, Інтелектуальні сервіс-орієнтовані розподілені обчислювання"
        institution="Національний технічний університет україни «Київський політехнічний інститут імені Ігоря Сікорського»"
        type="Навчально-науковий інститут прикладного системного аналізу"
        maxBudgetPlaces={120}
        totalPlaces={200}
      />
    </div>
  );
}

function CompetitiveOfferCompact({
  num,
  faculty,
  specialization,
  institution,
  type,
  maxBudgetPlaces,
  totalPlaces,
}) {
  return (
    <div className="competitive-offer competitive-offer_compact">
      <div className="competitive-offer_left">
        <div className="competitive-offer_specs">
          <span>Бакалавр</span>
          <span>|</span>
          <span>Денна</span>
          <span>|</span>
          <span>На основі ПЗСО</span>
          <span>|</span>
          <span>2023</span>
        </div>
        <div className="competitive-offer_desc">
          <div>
            <span className="competitive-offer_num">{num}</span>
            <span className="competitive-offer_fac">{faculty}</span>
          </div>
          <p className="competitive-offer_special">{specialization}</p>
          <p className="competitive-offer_inst">{institution}</p>
          <p className="competitive-offer_type">{type}</p>
        </div>
      </div>

      <div className="competitive-offer_right">
        <p>Макс. кількість бюджетних місць: {maxBudgetPlaces}</p>
        <p>Загальна кількість місць: {totalPlaces}</p>
        <ToggleableSubjectList
          buttonText={"Складові конкурсного балу"}
        ></ToggleableSubjectList>
        <a href="/offer" className="button-default button_smaller">
          Список Вступників
        </a>
      </div>
    </div>
  );
}

export function CompetitiveOfferFull({
  num,
  faculty,
  specialization,
  institution,
  type,
  maxBudgetPlaces,
  totalPlaces,
}) {
  return (
    <>
      <div className="competitive-offer competitive-offer_full">
        <div className="competitive-offer_left">
          <div className="competitive-offer_specs">
            <span>Бакалавр</span>
            <span>|</span>
            <span>Денна</span>
            <span>|</span>
            <span>На основі ПЗСО</span>
            <span>|</span>
            <span>2023</span>
          </div>
          <div className="competitive-offer_desc">
            <div>
              <span className="competitive-offer_num">{num}</span>
              <span className="competitive-offer_fac">{faculty}</span>
            </div>
            <p className="competitive-offer_special">{specialization}</p>
            <p className="competitive-offer_inst">{institution}</p>
            <p className="competitive-offer_type">{type}</p>
          </div>
          <p>Макс. кількість бюджетних місць: {maxBudgetPlaces}</p>
          <p>Загальна кількість місць: {totalPlaces}</p>
        </div>

        <div className="competitive-offer_right">
          <p>Складові КБ</p>
          <SubjectList />
        </div>
      </div>
    </>
  );
}

const SubjectList = () => {
  const subjects = [
    { weight: 0.3, type: "НМТ/ЗНО", name: "Українська Мова" },
    { weight: 0.5, type: "НМТ/ЗНО", name: "Математика" },
    { weight: 0.3, type: "НМТ/ЗНО", name: "Іноземна мова" },
    { weight: 0.4, type: "НМТ/ЗНО", name: "Фізика" },
    { weight: 0.2, type: "НМТ/ЗНО", name: "Географія" },
  ];

  return (
    <div className="subject-list">
      {subjects.map((subject, index) => (
        <div key={index} className="subject-item">
          <div className="subject-item_indicator"></div>
          <p className="subject-item_weight">{subject.weight}</p>
          <p className="subject-item_type">{subject.type}</p>
          <p className="subject-item_name">{subject.name}</p>
        </div>
      ))}
    </div>
  );
};

const useClickOutside = (handler) => {
  const ref = useRef(null);
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (ref.current && !ref.current.contains(event.target)) {
        handler();
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [handler]);

  return ref;
};

const ToggleableSubjectList = ({ buttonText }) => {
  const [showSubjectList, setShowSubjectList] = useState(false);
  const subjectRef = useClickOutside(() => setShowSubjectList(false));

  return (
    <>
      <p onClick={() => setShowSubjectList(!showSubjectList)}>{buttonText}</p>
      {showSubjectList &&
        ReactDOM.createPortal(
          <div ref={subjectRef} className="subject-list-container">
            <SubjectList />
          </div>,
          document.body
        )}
    </>
  );
};
