package guru.qa.geoservice.service;

import guru.qa.geoservice.data.GeoRepository;
import guru.qa.geoservice.data.entity.GeoEntity;
import guru.qa.geoservice.domain.GeoJson;
import jakarta.annotation.Nonnull;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GeoService {

    private final GeoRepository geoRepository;

    @Autowired
    public GeoService(GeoRepository geoRepository) {
        this.geoRepository = geoRepository;
    }

    public @Nonnull
    List<GeoJson> getAllGeo() {
        return IteratorUtils.toList(geoRepository.findAll().iterator())
                .stream()
                .map(GeoJson::fromEntity)
                .toList();
    }

    public @Nonnull
    GeoJson getGeoByCountryCode(@Nonnull String countryCode) {
        Optional<GeoEntity> countryEntity = geoRepository.findCountryByCountryCode(countryCode);
        if (countryEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Country with code %s not found.", countryCode));
        }
        return GeoJson.fromEntity(countryEntity.get());
    }

    public @Nonnull
    GeoJson editGeo(@Nonnull String countryCode, @Nonnull GeoJson geoJson) {
        Optional<GeoEntity> countryEntity = geoRepository.findCountryByCountryCode(countryCode);
        GeoEntity geoEntity = countryEntity.get();
        geoEntity.setCountryName(geoJson.countryName());
        geoEntity.setCountryCode(geoJson.countryCode());
        return GeoJson.fromEntity(geoRepository.save(geoEntity));
    }

    public @Nonnull
    GeoJson saveGeo(@Nonnull GeoJson geoJson) {
        final String countryName = geoJson.countryName();
        final String countryCode = geoJson.countryCode();
        Optional<GeoEntity> countryEntity = geoRepository.findCountryByCountryCode(countryCode);
        if (countryEntity.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Country with code %s already exists.", countryCode));
        }

        GeoEntity geoEntity = new GeoEntity();
        geoEntity.setCountryName(countryName);
        geoEntity.setCountryCode(countryCode);
        return GeoJson.fromEntity(geoRepository.save(geoEntity));
    }

    public void removeGeo(@Nonnull String countryCode) {
        Optional<GeoEntity> countryEntity = geoRepository.findCountryByCountryCode(countryCode);
        countryEntity.ifPresent(geoRepository::delete);
    }
}
