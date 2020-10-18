package com.sportify.webapp.sportifyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.webapp.sportifyshoes.entity.PurchaceDetails;

@Repository
public interface PurchaseDetailsRepository extends JpaRepository<PurchaceDetails, Long> {

}
