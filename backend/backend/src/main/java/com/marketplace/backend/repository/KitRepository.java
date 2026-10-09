package com.marketplace.backend.repository;

import com.marketplace.backend.entity.FestivalKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KitRepository extends JpaRepository<FestivalKit, Long> {

    // Festival kits ko religion ke hisaab se filter karne ke liye
    List<FestivalKit> findByReligion(String religion);
}