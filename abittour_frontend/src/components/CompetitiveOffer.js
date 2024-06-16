import React, { useState, useEffect, useRef } from "react";
import ReactDOM from "react-dom";
import { fetchData, parseOffer, parseUni, parseJsonList } from "./utils";
import { Link } from "react-router-dom"; // Import Link for navigation

export function CompetitiveOffers() {
  const [offers, setOffers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOffers = async () => {
      try {
        const fetchedData = await fetchData(`/competitive-offers`);
        const parsedOffers = parseJsonList(fetchedData, parseOffer);
        // Set only the first 10 offers
        setOffers(parsedOffers.slice(0, 10));
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchOffers();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }
  return (
    <div className="cometitive_offers section-wrapper">
      <h1>Знайдені пропозиції</h1>
      {offers.map((offer) => (
        <>
          <CompetitiveOfferCard
            id={offer.id}
            programName={offer.programName}
            offerCode={offer.offerCode}
            enrolmentBase={offer.enrolmentBase}
            educationalLevel={offer.educationalLevel}
            speciality={offer.speciality}
            university={offer.university}
            faculty={offer.faculty}
            typeOfOffer={offer.typeOfOffer}
            formOfEducation={offer.formOfEducation}
            enrolledCourse={offer.enrolledCourse}
            startOfStudies={offer.startOfStudies}
            endOfStudies={offer.endOfStudies}
            startOfApplication={offer.startOfApplication}
            endOfApplication={offer.endOfApplication}
            licenceAmount={offer.licenceAmount}
            maxVolumeOfTheStateOrder={offer.maxVolumeOfTheStateOrder}
            priceForYear={offer.priceForYear}
            totalPrice={offer.totalPrice}
            regionalCoefficient={offer.regionalCoefficient}
          />
          <hr></hr>
        </>
      ))}
    </div>
  );
}

export function CompetitiveOfferCard({
  id,
  programName,
  offerCode,
  enrolmentBase,
  educationalLevel,
  speciality,
  university,
  faculty,
  typeOfOffer,
  formOfEducation,
  enrolledCourse,
  startOfStudies,
  endOfStudies,
  startOfApplication,
  endOfApplication,
  licenceAmount,
  maxVolumeOfTheStateOrder,
  priceForYear,
  totalPrice,
  regionalCoefficient,
}) {
  return (
    <div className="competitive-offer competitive-offer_compact">
      <div className="competitive-offer_left">
        <div className="competitive-offer_specs">
          <span>{educationalLevel}</span>
          <span>|</span>
          <span>{formOfEducation}</span>
          <span>|</span>
          <span>{enrolmentBase}</span>
          <span>|</span>
          <span>{startOfStudies.slice(0, 4)}</span>
        </div>
        <div className="competitive-offer_desc">
          <div>
            <span className="competitive-offer_spec-code">
              {speciality.code}
            </span>
            <span className="competitive-offer_spec-name">
              {speciality.name}
            </span>
          </div>
          <p className="competitive-offer_program">{programName}</p>
          <p className="competitive-offer_inst">{university.name}</p>
          <p className="competitive-offer_faculty">{faculty}</p>
        </div>
      </div>

      <div className="competitive-offer_right">
        <p>Макс. кількість бюджетних місць: {maxVolumeOfTheStateOrder}</p>
        <p>Загальна кількість місць: {licenceAmount}</p>
        <ToggleableSubjectList
          buttonText={"Складові конкурсного балу"}
        ></ToggleableSubjectList>
        <Link to={`/offers/${id}`} className="button-default a_button">
          Детальніше
        </Link>
      </div>
    </div>
  );
}

function FetchtestComponent() {
  const uri = "/competitive-offers";

  const [offersData, setOffersData] = useState(null);
  const [institutionsData, setInstitutionsData] = useState(null);
  const [loadingOffers, setLoadingOffers] = useState(true);
  const [loadingInstitutions, setLoadingInstitutions] = useState(true);
  const [errorOffers, setErrorOffers] = useState(null);
  const [errorInstitutions, setErrorInstitutions] = useState(null);

  useEffect(() => {
    fetchData(`/competitive-offers`)
      .then((fetchedData) => {
        const parsedOffers = parseJsonList(fetchedData, parseOffer);
        setOffersData(parsedOffers);
        setLoadingOffers(false);
      })
      .catch((error) => {
        setErrorOffers(error);
        setLoadingOffers(false);
      });
  }, []);

  useEffect(() => {
    fetchData(`universities`)
      .then((fetchedData) => {
        const parsedInstitutions = parseJsonList(fetchedData, parseUni);
        setInstitutionsData(parsedInstitutions);
        setLoadingInstitutions(false);
      })
      .catch((error) => {
        setErrorInstitutions(error);
        setLoadingInstitutions(false);
      });
  }, []);

  if (loadingOffers || loadingInstitutions) {
    return <div>Loading...</div>;
  }

  if (errorOffers) {
    return <div>Error fetching offers: {errorOffers.message}</div>;
  }

  if (errorInstitutions) {
    return <div>Error fetching institutions: {errorInstitutions.message}</div>;
  }

  return (
    <div>
      <div>
        <h2>Offers</h2>
      </div>

      <div>
        <h2>Institutions</h2>
        {institutionsData.map((institution) => (
          <div key={institution.id}>
            <p>ID: {institution.id}</p>
            <p>Name: {institution.name}</p>
            <p>Code: {institution.code}</p>
            <p>Region: {institution.region.name}</p>
            <hr />
          </div>
        ))}
      </div>
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
