package com.summonerswars.SW.repository;

import com.summonerswars.SW.model.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Summoners Repository
@Repository
public interface SummonerRepository extends JpaRepository<Summoner, Long> {
}
