import React, { useState, useEffect, useRef } from "react";
import { Link } from "react-router-dom";

import {
  fetchData,
  parseOffer,
  parseJsonList,
  formatDate,
  filterOffers,
  starOffer,
  removeStarredOffer,
} from "../utils/utils";
import { subjectNames, mapToNiceNames, mainSubjects } from "../utils/mappings";

export function CompetitiveOffers({ filterParams }) {
  const [offers, setOffers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(5);
  const [inputValue, setInputValue] = useState(5);
  const sectionRef = useRef(null);

  useEffect(() => {
    const fetchOffers = async () => {
      try {
        const fetchedData = await fetchData(`/competitive-offers`);
        const parsedOffers = parseJsonList(fetchedData, parseOffer);

        const filteredOffers = filterOffers(parsedOffers, filterParams);

        setOffers(filteredOffers);
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchOffers();
  }, [filterParams]);

  const handleNextPage = () => {
    setCurrentPage((prevPage) => prevPage + 1);
    if (sectionRef.current) {
      sectionRef.current.scrollIntoView({ behavior: "smooth" });
    }
  };

  const handlePreviousPage = () => {
    setCurrentPage((prevPage) => Math.max(prevPage - 1, 1));
    if (sectionRef.current) {
      sectionRef.current.scrollIntoView({ behavior: "smooth" });
    }
  };

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  const handleInputKeyPress = (event) => {
    if (event.key === "Enter") {
      const value = Math.min(Math.max(Number(inputValue), 5), 100);
      setItemsPerPage(value);
      setInputValue(value);
      setCurrentPage(1);
    }
  };

  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedOffers = offers.slice(startIndex, startIndex + itemsPerPage);
  const totalPages = Math.ceil(offers.length / itemsPerPage);

  if (loading) {
    return <div className="loading-screen">Loading...</div>;
  }

  if (error) {
    return <div className="loading-screen">Error: {error.message}</div>;
  }

  return (
    <>
      {offers.length === 0 ? (
        <div
          className="competitive_offers section-wrapper dynamic-section"
          ref={sectionRef}
        >
          <h1>Пропозицій не знайдено</h1>
        </div>
      ) : (
        <div
          className="competitive_offers section-wrapper dynamic-section"
          ref={sectionRef}
        >
          <h1>Конкурсні пропозиції</h1>
          <div className="offers-list">
            {paginatedOffers.map((offer) => (
              <React.Fragment key={offer.id}>
                <CompetitiveOfferCard offerToDisplay={offer} />
                <hr />
              </React.Fragment>
            ))}
          </div>
          <div className="pagination-controls">
            <button
              className="pagination-button prev-button"
              onClick={handlePreviousPage}
              disabled={currentPage === 1}
            >
              Попередня
            </button>
            <span className="pagination-info">
              Сторінка {currentPage} з {totalPages}
            </span>
            <button
              className="pagination-button next-button"
              onClick={handleNextPage}
              disabled={currentPage === totalPages}
            >
              Наступна
            </button>
            <input
              type="number"
              className="items-per-page-input"
              value={inputValue}
              onChange={handleInputChange}
              onKeyPress={handleInputKeyPress}
              min="5"
              max="100"
            />
          </div>
        </div>
      )}
    </>
  );
}

export function CompetitiveOfferCard({ offerToDisplay }) {
  const offer = mapToNiceNames(offerToDisplay);
  const [isSaved, setIsSaved] = useState(false);

  useEffect(() => {
    const savedOffers = JSON.parse(localStorage.getItem("savedOffers")) || [];
    setIsSaved(savedOffers.some((savedOffer) => savedOffer.id === offer.id));
  }, [offer.id]);

  const toggleSaveOffer = () => {
    let savedOffers = JSON.parse(localStorage.getItem("savedOffers")) || [];

    if (isSaved) {
      // Remove offer
      savedOffers = savedOffers.filter(
        (savedOffer) => savedOffer.id !== offer.id
      );
    } else {
      // Save offer
      savedOffers.push({ id: offer.id, grades: {} }); // Save with empty grades initially
    }

    localStorage.setItem("savedOffers", JSON.stringify(savedOffers));
    setIsSaved(!isSaved);
  };

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
          <span>2024</span>
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
        <p>Кількість бюджетних місць: {offer.maxVolumeOfTheStateOrder}</p>
        <p>Загальна кількість місць: {offer.licenceAmount}</p>
        <ToggleableSubjectList buttonText={"Складові конкурсного балу"} />
        <Link to={`/${offer.id}`} className="button-default a_button">
          Детальніше
        </Link>
        <button
          className={`button-default ${
            isSaved ? "button_nofill button_nofill_active" : "button_nofill"
          } button_saveoffer`}
          onClick={toggleSaveOffer}
        >
          {isSaved ? "Збережено" : "Зберегти"}
        </button>
      </div>
    </div>
  );
}

export function CompetitiveOfferCardFull({ offerToDisplay }) {
  const offer = mapToNiceNames(offerToDisplay);
  const [isSaved, setIsSaved] = useState(false);

  useEffect(() => {
    const savedOffers = JSON.parse(localStorage.getItem("savedOffers")) || [];
    setIsSaved(savedOffers.some((savedOffer) => savedOffer.id === offer.id));
  }, [offer.id]);

  const toggleSaveOffer = () => {
    if (isSaved) {
      // Remove offer from localStorage
      removeStarredOffer(offer.id);
    } else {
      // Save or update offer in localStorage
      starOffer({
        id: offer.id,
        subjects: {}, // Replace with actual form values as needed
        totalScore: 999, // Replace with actual calculation result
        place: 999, // Replace with actual place value
      });
    }

    setIsSaved(!isSaved); // Toggle the saved state after saving or removing
  };

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
          <span>2024</span>
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
          <p>
            Термін навчання: {formatDate(offer.startOfStudies)} -{" "}
            {formatDate(offer.endOfStudies)}
          </p>
          <p>
            Термін подачі заяв: {formatDate(offer.startOfApplication)} -{" "}
            {formatDate(offer.endOfApplication)}
          </p>
          <p>Вартість навчання за рік: {offer.priceForYear}</p>
          <p>Загальна вартість навчання: {offer.totalPrice}</p>
          <p>Курс зарахування: {offer.enrolledCourse}</p>
          <p>
            Макс. кількість бюджетних місць: {offer.maxVolumeOfTheStateOrder}
          </p>
          <p>Загальна кількість місць: {offer.licenceAmount}</p>
          <div></div>
        </div>
      </div>

      <div className="competitive-offer_right">
        <p className="subj-coeff_title">Складові КБ</p>
        <SubjectCoefs offer={offer}></SubjectCoefs>
        <button
          className={`button-default ${
            isSaved ? "button_nofill button_nofill_active" : "button_nofill"
          } button_saveoffer`}
          onClick={toggleSaveOffer}
        >
          {isSaved ? "Збережено" : "Зберегти"}
        </button>
      </div>
    </div>
  );
}
export function StarredCompetitiveOfferCard({ offerToDisplay }) {
  const offer = mapToNiceNames(offerToDisplay);
  const [isSaved, setIsSaved] = useState(false);
  const [savedOfferDetails, setSavedOfferDetails] = useState({
    totalScore: null,
    place: null,
  });

  useEffect(() => {
    const savedOffers = JSON.parse(localStorage.getItem("savedOffers")) || [];
    const savedOffer = savedOffers.find(
      (savedOffer) => savedOffer.id === offer.id
    );
    setIsSaved(!!savedOffer);

    if (savedOffer) {
      setSavedOfferDetails({
        totalScore: savedOffer.totalScore || null,
        place: savedOffer.place || null,
      });
    }
  }, [offer.id]);

  const toggleSaveOffer = () => {
    if (isSaved) {
      // Remove offer from localStorage
      removeStarredOffer(offer.id);
      setSavedOfferDetails({
        totalScore: null,
        place: null,
      });
    } else {
      // Save or update offer in localStorage
      starOffer({
        id: offer.id,
        subjects: {}, // Replace with actual form values as needed
        totalScore: 999, // Replace with actual calculation result
        place: 999, // Replace with actual place value
      });
      setSavedOfferDetails({
        totalScore: 999,
        place: 999,
      });
    }

    setIsSaved(!isSaved); // Toggle the saved state after saving or removing
  };

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
          <span>2024</span>
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
        {savedOfferDetails.totalScore && (
          <p>
            <strong>Ваш КБ: {savedOfferDetails.totalScore}</strong>
          </p>
        )}
        {savedOfferDetails.place && (
          <p>
            <strong>Місце в рейтингу: {savedOfferDetails.place}</strong>
          </p>
        )}
        <ToggleableSubjectList buttonText={"Складові конкурсного балу"} />
        <Link to={`/${offer.id}`} className="button-default a_button">
          Детальніше
        </Link>
        <button
          className={`button-default ${
            isSaved ? "button_nofill button_nofill_active" : "button_nofill"
          } button_saveoffer`}
          onClick={toggleSaveOffer}
        >
          {isSaved ? "Збережено" : "Зберегти"}
        </button>
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
    <div className="subject-coeffs-container">
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
