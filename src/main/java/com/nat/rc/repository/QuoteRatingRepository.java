package com.nat.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nat.rc.model.UserQuoteRatingId;
import com.nat.rc.model.UserRatingModel;

public interface QuoteRatingRepository extends JpaRepository<UserRatingModel, UserQuoteRatingId>{

}
