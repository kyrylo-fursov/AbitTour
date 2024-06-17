import React, { useState, useEffect, useRef } from "react";
import ReactDOM from "react-dom";
import { Link } from "react-router-dom";

import {
  fetchData,
  parseOffer,
  parseUni,
  parseJsonList,
  formatDate,
} from "../utils/utils";
import {
  eduLvlNames,
  subjectNames,
  enrollmentBaseNames,
  eduFormNames,
  mapToNiceNames,
  mainSubjects,
} from "../utils/mappings";

export function CompetitiveOffers() {
  const [offers, setOffers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOffers = async () => {
      try {
        const fetchedData = await fetchData(`/competitive-offers`);
        const parsedOffers = parseJsonList(fetchedData, parseOffer);
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
    return <div className="loading-screen">Loading...</div>;
  }

  if (error) {
    return <div className="loading-screen">Error: {error.message}</div>;
  }
  return (
    <div className="cometitive_offers section-wrapper dynamic-section">
      <h1>Знайдені пропозиції</h1>
      {offers.map((offer) => {
        return (
          <>
            <CompetitiveOfferCard offerToDisplay={offer} />
            <hr></hr>
          </>
        );
      })}
    </div>
  );
}

export function CompetitiveOfferCard({ offerToDisplay }) {
  const offer = mapToNiceNames(offerToDisplay);

  return (
    <div className="competitive-offer competitive-offer_compact dynamic-section">
      <div className="competitive-offer_left">
        <div className="competitive-offer_specs">
          <span>{offer.educationalLevel}</span>
          <span>|</span>
          <span>{offer.formOfEducation}</span>
          <span>|</span>
          <span>{offer.enrolmentBase}</span>
          <span>|</span>
          <span>{offer.startOfStudies.slice(0, 4)}</span>
        </div>
        <div className="competitive-offer_desc">
          <div>
            <span className="competitive-offer_spec-code">
              {offer.speciality.code}
            </span>
            <span className="competitive-offer_spec-name">
              {offer.speciality.name}
            </span>
          </div>
          <p className="competitive-offer_program">{offer.programName}</p>
          <p className="competitive-offer_inst">{offer.university.name}</p>
          <p className="competitive-offer_faculty">{offer.faculty}</p>
        </div>
      </div>

      <div className="competitive-offer_right">
        <p>Макс. кількість бюджетних місць: {offer.maxVolumeOfTheStateOrder}</p>
        <p>Загальна кількість місць: {offer.licenceAmount}</p>
        <ToggleableSubjectList buttonText={"Складові конкурсного балу"} />
        <Link to={`/${offer.id}`} className="button-default a_button">
          Детальніше
        </Link>
      </div>
    </div>
  );
}

export function CompetitiveOfferCardFull({ offerToDisplay }) {
  const offer = mapToNiceNames(offerToDisplay);
  return (
    <div className="competitive-offer competitive-offer_compact dynamic-section">
      <div className="competitive-offer_left">
        <div className="competitive-offer_specs">
          <span>{offer.educationalLevel}</span>
          <span>|</span>
          <span>{offer.formOfEducation}</span>
          <span>|</span>
          <span>{offer.enrolmentBase}</span>
          <span>|</span>
          <span>{offer.startOfStudies.slice(0, 4)}</span>
        </div>
        <div className="competitive-offer_desc">
          <div>
            <span className="competitive-offer_spec-code">
              {offer.speciality.code}
            </span>
            <span className="competitive-offer_spec-name">
              {offer.speciality.name}
            </span>
          </div>
          <p className="competitive-offer_program">{offer.programName}</p>
          <p className="competitive-offer_inst">{offer.university.name}</p>
          <p className="competitive-offer_faculty">{offer.faculty}</p>
          <br></br>
          <hr></hr>
          <p>
            Термін навчання: {formatDate(offer.startOfStudies)} -{" "}
            {formatDate(offer.endOfStudies)}
          </p>
          <p>
            Термін подачі заяв: {formatDate(offer.startOfApplication)} -{" "}
            {formatDate(offer.endOfApplication)}
          </p>
          <br></br>
          <p>Вартість навчання за рік: {offer.priceForYear}</p>
          <p>Загальна вартість навчання: {offer.totalPrice}</p>
          <br></br>
          <p>Курс зарахування: {offer.enrolledCourse}</p>
          <p>
            Макс. кількість бюджетних місць: {offer.maxVolumeOfTheStateOrder}
          </p>
          <p>Загальна кількість місць: {offer.licenceAmount}</p>
          <div></div>
        </div>
      </div>

      <div className="competitive-offer_right">
        <p>Складові КБ</p>
        <SubjectCoefs offer={offer}></SubjectCoefs>
      </div>
    </div>
  );
}

const SubjectCoefs = ({ offer }) => {
  if (!offer || !offer.subjectCoefs) {
    return null; // Handle case where offer or subjectCoefs is undefined/null
  }

  // Map the subject coefficients and translate their names
  const mappedCoefs = offer.subjectCoefs.map((coef) => ({
    subject: subjectNames[coef.subject] || coef.subject,
    coefficient: coef.coefficient,
  }));

  // Convert mainSubjects object keys into an array
  const mainSubjectsKeys = Object.keys(mainSubjects);

  // Separate subjects into main and optional based on mainSubjects keys
  const mainCoefs = [];
  const optionalCoefs = [];

  mappedCoefs.forEach((coef) => {
    if (mainSubjectsKeys.includes(coef.subject)) {
      mainCoefs.push(coef);
    } else {
      optionalCoefs.push(coef);
    }
  });

  return (
    <div>
      <div className="subject-coeffs-main">
        {mainCoefs.map((coef, index) => (
          <p key={index}>
            {coef.subject} - {coef.coefficient}
          </p>
        ))}
      </div>
      <div className="subject-coeffs-optional">
        {optionalCoefs.map((coef, index) => (
          <p key={index}>
            {coef.subject} - {coef.coefficient}
          </p>
        ))}
      </div>
    </div>
  );
};
const ToggleableSubjectList = ({ buttonText }) => {
  // const [showSubjectList, setShowSubjectList] = useState(false);
  // const subjectRef = useClickOutside(() => setShowSubjectList(false));
  // return (
  //   <>
  //     <p onClick={() => setShowSubjectList(!showSubjectList)}>{buttonText}</p>
  //     {showSubjectList &&
  //       ReactDOM.createPortal(
  //         <div ref={subjectRef} className="subject-list-container">
  //           <SubjectList />
  //         </div>,
  //         document.body
  //       )}
  //   </>
  // );
};

// const useClickOutside = (handler) => {
//   const ref = useRef(null);
//   useEffect(() => {
//     const handleClickOutside = (event) => {
//       if (ref.current && !ref.current.contains(event.target)) {
//         handler();
//       }
//     };
//     document.addEventListener("mousedown", handleClickOutside);
//     return () => {
//       document.removeEventListener("mousedown", handleClickOutside);
//     };
//   }, [handler]);

//   return ref;
// };
