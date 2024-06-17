export const subjectNames = {
  FOREIGN_LANGUAGE: "Іноземна мова",
  UKRAINIAN_LITERATURE: "Українська література",
  GEOGRAPHY: "Географія",
  HISTORY_OF_UKRAINE: "Історія України",
  CHEMISTRY: "Хімія",
  CREATIVE_COMPETITION: "Творчий конкурс",
  PHYSICS: "Фізика",
  MATHEMATICS: "Математика",
  BIOLOGY: "Біологія",
  UKRAINIAN_LANGUAGE: "Українська мова",
};

export const mainSubjects = {
  UKRAINIAN_LANGUAGE: "Українська мова",
  MATHEMATICS: "Математика",
  HISTORY_OF_UKRAINE: "Історія України",
};

export const enrollmentBaseNames = {
  COMPLETE_SECONDARY_EDUCATION: "ПЗСО",
  JUNIOR_BACHELOR: "Молодший бакалавр",
  PROFESSIONAL_JUNIOR_BACHELOR: "Фаховий молодший бакалавр",
  JUNIOR_SPECIALIST: "Молодший спеціаліст",
  BACHELOR: "Бакалавр",
};

export const eduLvlNames = {
  BACHELOR: "Бакалавр",
  JUNIOR_BACHELOR: "Молодший бакалавр",
  PROFESSIONAL_JUNIOR_BACHELOR: "Фаховий молодший бакалавр",
  MASTER: "Магістр",
};

export const enrolledCourseNames = {
  COURSE_1: "1 курс",
};

export const eduFormNames = {
  FULL_TIME: "Денна",
};

// Helper function to map object properties to nice names
export function mapToNiceNames(offer) {
  return {
    ...offer,
    educationalLevel:
      eduLvlNames[offer.educationalLevel] || offer.educationalLevel,
    formOfEducation:
      eduFormNames[offer.formOfEducation] || offer.formOfEducation,
    enrolmentBase:
      enrollmentBaseNames[offer.enrolmentBase] || offer.enrolmentBase,
    enrolledCourse:
      enrolledCourseNames[offer.enrolledCourse] || offer.enrolledCourse,
    subjectCoefs: offer.speciality.subjectCoefs.map((coef) => ({
      ...coef,
      subject: subjectNames[coef.subject] || coef.subject,
    })),
  };
}
