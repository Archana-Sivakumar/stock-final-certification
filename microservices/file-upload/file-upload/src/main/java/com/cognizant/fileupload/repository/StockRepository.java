package com.cognizant.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.fileupload.model.StockPrice;


public interface StockRepository extends JpaRepository<StockPrice, Integer> {

}
