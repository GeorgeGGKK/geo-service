package guru.qa.geoservice.data;

import guru.qa.geoservice.data.entity.GeoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GeoRepository extends JpaRepository<GeoEntity, UUID> {

    Optional<GeoEntity> findCountryByCountryCode(String countryCode);
}
