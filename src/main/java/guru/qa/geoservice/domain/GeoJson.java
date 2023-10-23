package guru.qa.geoservice.domain;

import guru.qa.geoservice.data.entity.GeoEntity;

import java.util.Date;

public record GeoJson(String countryName, String countryCode){

    public static GeoJson fromEntity(GeoEntity entity){
        return new GeoJson(entity.getCountryName(), entity.getCountryCode());
    }
}
