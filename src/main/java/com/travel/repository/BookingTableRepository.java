package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.Activity;

public interface BookingTableRepository extends JpaRepository<Activity, Long> {

}
