import { CompetitiveOfferFull, CompetitiveOfferCard } from "./CompetitiveOffer";
import React, { useState, useEffect } from "react";
import { InlineCalculator } from "./InlineCalculator";
import { useParams } from "react-router-dom";

import { fetchData, parseOffer } from "./utils";

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
  const { id } = useParams(); // Get the id parameter from the URL
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
  }, [id]); // Fetch offer whenever id changes

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <div className="section-wrapper">
      <h1>Конкурсна пропозиція</h1>

      {offer && (
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
            znoSubjectOptions={offer.znoSubjectOptions}
          />
          <hr />
        </>
      )}
      <InlineCalculator offer={offer}></InlineCalculator>
      {/* <h1>Конкурсні заявки</h1> */}
      {/* <ApplicantsTable data={data}></ApplicantsTable> */}
      {/* <SpecialityComponent></SpecialityComponent> */}
    </div>
  );
}

const SpecialityComponent = () => {
  const [specialityData, setSpecialityData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await fetchSpeciality(220); // Fetch speciality data for ID 1
        const processedData = processSpecialityData(result); // Process the fetched data
        setSpecialityData(processedData);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const processSpecialityData = (data) => {
    if (!data) return null;

    // Extract subject coefficients into an array of objects
    const subjectCoefficients = data.subjectCoefs.map((item) => ({
      id: item.id,
      subject: item.subject,
      coefficient: item.coefficient,
    }));

    // Construct the processed object
    const processedData = {
      id: data.id,
      code: data.code,
      name: data.name,
      specialization: data.specialization,
      subjectCoefficients: subjectCoefficients,
    };

    return processedData;
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div>
      <h1>Speciality Data</h1>
      {specialityData && (
        <div>
          <h2>{specialityData.name}</h2>
          <p>
            <strong>Code:</strong> {specialityData.code}
          </p>
          <p>
            <strong>Specialization:</strong> {specialityData.specialization}
          </p>
          <h3>Subject Coefficients:</h3>
          <ul>
            {specialityData.subjectCoefficients.map((subject) => (
              <li key={subject.id}>
                {subject.subject}: {subject.coefficient}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

const ApplicantsTable = ({ data }) => (
  <table className="applicants-table">
    <thead>
      <tr>
        <th>#</th>
        <th>ПІБ</th>
        <th>Статус</th>
        <th>ПР</th>
        <th>КБ</th>
        <th>Складові КБ</th>
      </tr>
    </thead>
    <tbody>
      {data.map((item) => (
        <ApplicantRow key={item.id} item={item} />
      ))}
    </tbody>
  </table>
);

const ApplicantRow = ({ item }) => (
  <tr style={{ backgroundColor: item.color }}>
    <td>{item.id}</td>
    <td>{item.name}</td>
    <td>{item.status}</td>
    <td>{item.pr}</td>
    <td>{item.kb}</td>
    <td>
      {item.details.map((detail, index) => (
        <div key={index}>{detail}</div>
      ))}
    </td>
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
