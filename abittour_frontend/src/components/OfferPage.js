import { CompetitiveOfferFull } from "./CompetitiveOffer";

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
  return (
    <div className="section-wrapper">
      <h1>Конкурсна пропозиція</h1>
      <CompetitiveOfferFull
        num="122"
        faculty="Комп’ютерні науки"
        specialization="Системи і методи штучного інтелекту, Інтелектуальні сервіс-орієнтовані розподілені обчислювання"
        institution="Національний технічний університет україни «Київський політехнічний інститут імені Ігоря Сікорського»"
        type="Навчально-науковий інститут прикладного системного аналізу"
        maxBudgetPlaces={120}
        totalPlaces={200}
      />
      <h1>Конкурсні заявки</h1>
      <ApplicantsTable data={data}></ApplicantsTable>
    </div>
  );
}

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
