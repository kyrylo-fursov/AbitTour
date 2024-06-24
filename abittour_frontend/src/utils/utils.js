import { jwt_token } from "../App";
import applicationsData from "./applications.json";

export async function fetchData(path) {
  if (!jwt_token) {
    throw new Error("No JWT token found");
  }

  try {
    console.log(path);
    const response = await fetch(`${path}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwt_token}`,
      },
    });

    if (!response.ok) {
      const errorDetails = await response.text();
      throw new Error(
        `(fetchData)Network response was not ok: ${response.status} ${response.statusText} - ${errorDetails}`
      );
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Fetch operation failed:", error);
    throw error;
  }
}

// Rearrange the parts into from YYYY-MM-DD to DD.MM.YYYY format
export function formatDate(inputDate) {
  const parts = inputDate.split("-");

  const formattedDate = `${parts[2]}.${parts[1]}.${parts[0]}`;

  return formattedDate;
}

function mapJsonToObject(json, mappingFunction) {
  return mappingFunction(json);
}

export function parseJsonList(jsonArray, mappingFunction) {
  return jsonArray.map((item) => mapJsonToObject(item, mappingFunction));
}

export function parseOffer(json) {
  return {
    id: json.id,
    programName: json.programName,
    offerCode: json.offerCode,
    enrolmentBase: json.enrolmentBase,
    educationalLevel: json.educationalLevel,
    speciality: {
      id: json.speciality.id,
      code: json.speciality.code,
      name: json.speciality.name,
      specialization: json.speciality.specialization,
      isInDemand: json.speciality.isInDemand,
      subjectCoefs: json.speciality.subjectCoefs.map((coef) => ({
        id: coef.id,
        subject: coef.subject,
        coefficient: coef.coefficient,
      })),
    },
    university: {
      id: json.university.id,
      code: json.university.code,
      name: json.university.name,
      region: {
        id: json.university.region.id,
        name: json.university.region.name,
      },
    },
    faculty: json.faculty,
    typeOfOffer: json.typeOfOffer,
    formOfEducation: json.formOfEducation,
    enrolledCourse: json.enrolledCourse,
    startOfStudies: json.startOfStudies,
    endOfStudies: json.endOfStudies,
    startOfApplication: json.startOfApplication,
    endOfApplication: json.endOfApplication,
    licenceAmount: json.licenceAmount,
    maxVolumeOfTheStateOrder: json.maxVolumeOfTheStateOrder,
    priceForYear: json.priceForYear,
    totalPrice: json.totalPrice,
    regionalCoefficient: json.regionalCoefficient,
    znoSubjectOptions: json.znoSubjectOptions.map((option) => ({
      coefficient: option.coefficient,
      subject: option.subject,
    })),
  };
}

export function parseApplication(json) {
  return {
    id: json.id,
    student: {
      id: json.student.id,
      name: json.student.name,
    },
    competitiveOffer: {
      id: json.competitiveOffer.id,
      programName: json.competitiveOffer.programName,
      offerCode: json.competitiveOffer.offerCode,
      enrolmentBase: json.competitiveOffer.enrolmentBase,
      educationalLevel: json.competitiveOffer.educationalLevel,
      speciality: {
        id: json.competitiveOffer.speciality.id,
        code: json.competitiveOffer.speciality.code,
        name: json.competitiveOffer.speciality.name,
        specialization: json.competitiveOffer.speciality.specialization,
        isInDemand: json.competitiveOffer.speciality.isInDemand,
        subjectCoefs: json.competitiveOffer.speciality.subjectCoefs.map(
          (coef) => ({
            id: coef.id,
            subject: coef.subject,
            coefficient: coef.coefficient,
          })
        ),
      },
      university: {
        id: json.competitiveOffer.university.id,
        code: json.competitiveOffer.university.code,
        name: json.competitiveOffer.university.name,
        region: {
          id: json.competitiveOffer.university.region.id,
          name: json.competitiveOffer.university.region.name,
        },
      },
      faculty: json.competitiveOffer.faculty,
      typeOfOffer: json.competitiveOffer.typeOfOffer,
      formOfEducation: json.competitiveOffer.formOfEducation,
      enrolledCourse: json.competitiveOffer.enrolledCourse,
      startOfStudies: json.competitiveOffer.startOfStudies,
      endOfStudies: json.competitiveOffer.endOfStudies,
      startOfApplication: json.competitiveOffer.startOfApplication,
      endOfApplication: json.competitiveOffer.endOfApplication,
      licenceAmount: json.competitiveOffer.licenceAmount,
      maxVolumeOfTheStateOrder: json.competitiveOffer.maxVolumeOfTheStateOrder,
      priceForYear: json.competitiveOffer.priceForYear,
      totalPrice: json.competitiveOffer.totalPrice,
      regionalCoefficient: json.competitiveOffer.regionalCoefficient,
      znoSubjectOptions: json.competitiveOffer.znoSubjectOptions.map(
        (option) => ({
          coefficient: option.coefficient,
          subject: option.subject,
        })
      ),
    },
    totalScore: json.totalScore,
    priority: json.priority,
  };
}

export function getApplicationsByOfferId(offer_id) {
  try {
    const matchingApplications = applicationsData
      .filter((app) => app.competitiveOffer.id === offer_id)
      .map((app) => parseApplication(app));

    return matchingApplications;
  } catch (error) {
    throw new Error(`Error parsing applications data: ${error.message}`);
  }
}

// Example usage:
// (async () => {
//   try {
//     const offerId = 1;
//     const applications = await getApplicationsByOfferId(offerId);

//     if (applications.length > 0) {
//       console.log('Applications found:', applications);
//     } else {
//       console.log('No applications found for the offer_id.');
//     }
//   } catch (error) {
//     console.error('Error:', error.message);
//   }
// })();

export function filterOffers(offers, filters) {
  let filteredOffers = [...offers];

  console.log("Original offers:", filteredOffers);
  console.log("Filters:", filters);

  if (filters.university) {
    filteredOffers = filteredOffers.filter(
      (offer) => offer.university.id.toString() === filters.university
    );
  }

  if (filters.region) {
    filteredOffers = filteredOffers.filter(
      (offer) => offer.university.region.id.toString() === filters.region
    );
  }

  if (filters.specialty) {
    filteredOffers = filteredOffers.filter(
      (offer) => offer.speciality.name.toString() === filters.specialty
    );
  }

  if (filters.base) {
    filteredOffers = filteredOffers.filter(
      (offer) => offer.enrolmentBase === filters.base
    );
  }

  console.log("Filtered offers:", filteredOffers);
  return filteredOffers;
}

export function parseUni(json) {
  return {
    id: json.id,
    name: json.name,
    code: json.code,
    websiteUrl: json.websiteUrl,
    region: {
      id: json.region.id,
      name: json.region.name,
    },
  };
}

export function parseSpecialty(json) {
  return {
    id: json.id,
    code: json.code,
    name: json.name,
    specialization: json.specialization,
    isInDemand: json.isInDemand,
  };
}
