import React, { useState, useEffect } from "react";

import { StarredCompetitiveOfferCard } from "./CompetitiveOffer";
import { fetchData, parseOffer, parseJsonList } from "../utils/utils";

export const StarredPage = () => {
  const [offers, setOffers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchSavedOffers = async () => {
      try {
        const savedOffers =
          JSON.parse(localStorage.getItem("savedOffers")) || [];
        const offerIds = savedOffers.map((offer) => offer.id);

        const fetchedData = await fetchData(`/competitive-offers`);
        const parsedOffers = parseJsonList(fetchedData, parseOffer);

        const filteredOffers = parsedOffers.filter((offer) =>
          offerIds.includes(offer.id)
        );

        setOffers(filteredOffers);
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchSavedOffers();
  }, []);

  const handleRemoveOffer = (offerId) => {
    let savedOffers = JSON.parse(localStorage.getItem("savedOffers")) || [];
    savedOffers = savedOffers.filter((savedOffer) => savedOffer.id !== offerId);
    localStorage.setItem("savedOffers", JSON.stringify(savedOffers));
    window.location.reload(); // Reload the page
  };

  if (loading) {
    return <div className="loading-screen">Loading...</div>;
  }

  if (error) {
    return <div className="loading-screen">Error: {error.message}</div>;
  }

  if (offers.length === 0) {
    return <div className="loading-screen">No saved offers found.</div>;
  }

  return (
    <div className="starred-page section-wrapper">
      <h1>Збережені пропозиції</h1>
      <div className="offers-list">
        {offers.map((offer) => (
          <React.Fragment key={offer.id}>
            <StarredCompetitiveOfferCard
              key={offer.id}
              offerToDisplay={offer}
              onRemoveOffer={handleRemoveOffer}
            />
            <hr />
          </React.Fragment>
        ))}
      </div>
    </div>
  );
};
