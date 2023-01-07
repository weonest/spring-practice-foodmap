package naver.map.controller;

import naver.map.domain.Map;
import naver.map.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MapController {


    private final MapService mapService;


    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @RequestMapping("/map")
    public String list(Model model) {
        List<Map> maps = mapService.findMaps();
        List<Map> suwons = mapService.findSuwon();
        List<Map> seouls = mapService.findSeoul();
        List<Map> stars = mapService.starOrder();

        model.addAttribute("maps", maps);
        model.addAttribute("suwons", suwons);
        model.addAttribute("seouls", seouls);
        model.addAttribute("stars", stars);

        System.out.println(maps.toString());
        return "mainMap";

    }

}
