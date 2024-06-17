import { jwt_token } from "../App";

export async function fetchData(path) {
  if (!jwt_token) {
    throw new Error("No JWT token found");
  }

  try {
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
        `Network response was not ok: ${response.status} ${response.statusText} - ${errorDetails}`
      );
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Fetch operation failed:", error);
    throw error;
  }
}

export function formatDate(inputDate) {
  // Split the input date into year, month, and day
  const parts = inputDate.split("-");

  // Rearrange the parts into DD.MM.YYYY format
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
