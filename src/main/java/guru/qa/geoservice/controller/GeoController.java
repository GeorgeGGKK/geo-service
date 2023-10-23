package guru.qa.geoservice.controller;

import guru.qa.geoservice.domain.GeoJson;
import guru.qa.geoservice.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/geo")
public class GeoController {
    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/countries")
    public List<GeoJson> getAllGeo() {
        return geoService.getAllGeo();
    }

    @GetMapping("/country/{countryCode}")
    public GeoJson getGeo(@PathVariable String countryCode) {
        return geoService.getGeoByCountryCode(countryCode);
    }

    @PostMapping("/country")
    public GeoJson addGeo(@RequestBody GeoJson geoJson) {
        return geoService.saveGeo(geoJson);
    }

    @PatchMapping("/country/{countryCode}")
    public GeoJson updateGeo(@PathVariable String countryCode,
                             @RequestBody GeoJson geoJson) {
        return geoService.editGeo(countryCode, geoJson);
    }

    @DeleteMapping("/country/{countryCode}")
    public void deletedGeo(@PathVariable String countryCode) {
        geoService.removeGeo(countryCode);
    }
}
