import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { CompetitiveOfferCardFull } from "./CompetitiveOffer";
import { InlineCalculator } from "./InlineCalculator";

import {
  fetchData,
  parseOffer,
  getApplicationsByOfferId,
} from "../utils/utils";

const data = [
  {
    id: 1,
    name: "Петренко П. О.",
    status: "До наказу (бюджет)",
    pr: 1,
    kb: 192,
    details: [
      "54=180*0.3 Українська мова",
      "54=180*0.2 Англійська мова",
      "54=180*0.5 Математика",
    ],
    color: "#d4edda",
  },
  {
    id: 2,
    name: "Петренко П. О.",
    status: "До наказу (бюджет)",
    pr: 1,
    kb: 192,
    details: [
      "54=180*0.3 Українська мова",
      "54=180*0.2 Англійська мова",
      "54=180*0.5 Математика",
    ],
    color: "#d4edda",
  },
  {
    id: 3,
    name: "Петренко П. О.",
    status: "До наказу (контракт)",
    pr: 1,
    kb: 192,
    details: [
      "54=180*0.3 Українська мова",
      "54=180*0.2 Англійська мова",
      "54=180*0.5 Математика",
    ],
    color: "#cce5ff",
  },
  {
    id: 4,
    name: "Петренко П. О.",
    status: "Відхилено (контракт)",
    pr: 1,
    kb: 192,
    details: [
      "54=180*0.3 Українська мова",
      "54=180*0.2 Англійська мова",
      "54=180*0.5 Математика",
    ],
    color: "#f8d7da",
  },
];

export function OfferPage() {
  const { id } = useParams();
  const [offer, setOffer] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOffer = async () => {
      try {
        const fetchedData = await fetchData(`/competitive-offers/${id}`);
        const parsedOffer = parseOffer(fetchedData);
        setOffer(parsedOffer);
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchOffer();
  }, [id]);

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
      <InlineCalculator offer={offer}></InlineCalculator>
      <h1>Конкурсні заявки</h1>
      <ApplicantsTable data={data}></ApplicantsTable>
    </div>
  );
}

const ApplicantsTable = () => {
  const { id } = useParams(); // Get the id parameter from the URL
  const [offer, setOffer] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    try {
      const applications = getApplicationsByOfferId(parseInt(id));
      console.log(applications);
      if (applications.length === 0) {
        throw new Error("No offer found for the specified id");
      }

      // Sort applications by totalScore in descending order
      applications.sort((a, b) => b.totalScore - a.totalScore);

      setOffer(applications); // Set the array of matching applications sorted by totalScore
      setLoading(false);
    } catch (error) {
      setError(error);
      setLoading(false);
    }
  }, [id]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (!offer) {
    return <div>No offer found.</div>;
  }

  return (
    <div>
      <table className="applicants-table">
        <thead>
          <tr>
            <th>#</th>
            <th>Ім'я студента</th>
            <th>Загальний бал</th>
            <th>Пріоритет</th>
          </tr>
        </thead>
        <tbody>
          {offer.map((applicant, index) => (
            <ApplicantRow key={index} applicant={applicant} index={index + 1} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

const ApplicantRow = ({ applicant, index }) => (
  <tr>
    <td>{index}</td>
    <td>{applicant.student.name}</td>
    <td>{applicant.totalScore}</td>
    <td>{applicant.priority}</td>
  </tr>
);

// export const fetchSpeciality = async (id) => {
//   const token =
//     "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzE4Mjg5MDcwLCJleHAiOjE3MTgzMjUwNzB9.44QIpQafoD89weWoB4_d3xitJknakEWQKKjj3GYIxlo";
//   if (!token) {
//     throw new Error("No JWT token found");
//   }

//   try {
//     const response = await fetch(`/specialities/${id}`, {
//       method: "GET",
//       headers: {
//         "Content-Type": "application/json",
//         Authorization: `Bearer ${token}`,
//       },
//     });

//     if (!response.ok) {
//       const errorDetails = await response.text();
//       throw new Error(
//         `Network response was not ok: ${response.status} ${response.statusText} - ${errorDetails}`
//       );
//     }

//     const data = await response.json();
//     return data;
//   } catch (error) {
//     console.error("Fetch operation failed:", error);
//     throw error;
//   }
// };
