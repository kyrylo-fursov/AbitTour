import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { CompetitiveOfferCardFull } from "./CompetitiveOffer";
import { InlineCalculator } from "./InlineCalculator";

import {
  fetchData,
  parseOffer,
  parseUni,
  parseJsonList,
  parseApplicant,
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
  const { id } = useParams();
  const [offer, setOffer] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOffer = async () => {
      try {
        const response = await fetchData(`/applications/offer/123`);
        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }
        const data = await response.json();
        console.log(data);
        const parsedOffer = parseJsonList(data, parseApplicant);
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
      <h2>Competitive Offer Details</h2>
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
          <CompetitiveOfferRow offer={offer} />
        </tbody>
      </table>
    </div>
  );
};

const CompetitiveOfferRow = ({ offer }) => (
  <tr>
    <td>{offer.id}</td>
    <td>{offer.student.name}</td>
    <td>{offer.totalScore}</td>
    <td>{offer.priority}</td>
  </tr>
);

export const fetchSpeciality = async (id) => {
  const token =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzE4Mjg5MDcwLCJleHAiOjE3MTgzMjUwNzB9.44QIpQafoD89weWoB4_d3xitJknakEWQKKjj3GYIxlo";
  if (!token) {
    throw new Error("No JWT token found");
  }

  try {
    const response = await fetch(`/specialities/${id}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      const errorDetails = await response.text();
      throw new Error(
        `Network response was not ok: ${response.status} ${response.statusText} - ${errorDetails}`
      );
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Fetch operation failed:", error);
    throw error;
  }
};
