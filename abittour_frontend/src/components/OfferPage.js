import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { CompetitiveOfferCardFull } from "./CompetitiveOffer";
import { InlineCalculator } from "./InlineCalculator";

import {
  fetchData,
  parseOffer,
  getApplicationsByOfferId,
} from "../utils/utils";

export const OfferPage = () => {
  const { id } = useParams();
  const [offer, setOffer] = useState(null);
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch offer details on component mount
  useEffect(() => {
    const fetchOffer = async () => {
      try {
        const fetchedData = await fetchData(`/competitive-offers/${id}`);
        const parsedOffer = parseOffer(fetchedData); // Ensure you have parseOffer function defined
        setOffer(parsedOffer);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchOffer();
  }, [id]);

  // Fetch applications associated with the offer on component mount
  useEffect(() => {
    const fetchApplications = async () => {
      try {
        const fetchedApplications = getApplicationsByOfferId(parseInt(id));

        const savedOffers =
          JSON.parse(localStorage.getItem("savedOffers")) || [];
        const savedOffer = savedOffers.find(
          (savedOffer) => savedOffer.id === parseInt(id)
        );

        if (savedOffer) {
          const userApplication = {
            student: { name: "Ви" }, // Adjust as necessary
            totalScore: savedOffer.totalScore,
            place: savedOffer.place,
          };
          fetchedApplications.push(userApplication);
          fetchedApplications.sort((a, b) => b.totalScore - a.totalScore);
        }

        setApplications(fetchedApplications);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchApplications();
  }, [id]);

  // Function to handle setting applications
  const handleSetApplications = (newApplications) => {
    setApplications(newApplications);
  };

  if (loading) {
    return <div className="loading-screen">Loading...</div>;
  }

  if (error) {
    return <div className="loading-screen">Error: {error.message}</div>;
  }

  return (
    <div className="section-wrapper">
      <h1>Конкурсна пропозиція</h1>
      {offer && <CompetitiveOfferCardFull offerToDisplay={offer} />}
      Integrate the Calc component with necessary props
      <InlineCalculator offer={offer} setApplications={setApplications} />
      <h1>Конкурсні заявки</h1>
      <ApplicantsTable applications={applications} />
    </div>
  );
};

export const ApplicantsTable = ({ applications }) => {
  if (!applications || applications.length === 0) {
    return <div>No applications found.</div>;
  }

  return (
    <div>
      <table className="applicants-table">
        <thead>
          <tr>
            <th>#</th>
            <th>ПІБ</th>
            <th>Конкурсний бал</th>
            <th>Пріоритет</th>
          </tr>
        </thead>
        <tbody>
          {applications.map((applicant, index) => (
            <ApplicantRow key={index} applicant={applicant} index={index + 1} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

const ApplicantRow = ({ applicant, index }) =>
  applicant.totalScore && (
    <tr
      className={applicant.student.name === "Ви" ? "applications-user-row" : ""}
    >
      <td>{index}</td>
      <td>{applicant.student.name}</td>
      <td>{applicant.totalScore}</td>
      {/* Display priority as blank if name is "В" */}
      <td>{applicant.student.name !== "Ви" ? applicant.priority : ""}</td>
    </tr>
  );
