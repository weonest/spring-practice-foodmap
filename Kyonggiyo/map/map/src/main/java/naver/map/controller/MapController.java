package naver.map.controller;

import naver.map.domain.Map;
import naver.map.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        model.addAttribute("maps", maps);
        model.addAttribute("suwons", suwons);
        model.addAttribute("seouls", seouls);

        System.out.println(maps.toString());
        return "mainMap";

    }

    @RequestMapping("/map/star")
    public String getStar(Model model) {

        List<Map> stars = mapService.starOrder();
        List<Map> suwons = mapService.starOrderSuwon();
        List<Map> seouls = mapService.starOrderSeoul();

        model.addAttribute("maps", stars);
        model.addAttribute("suwons", suwons);
        model.addAttribute("seouls", seouls);

        return "mainMap";
    }

    @PostMapping("/map")
    public String getKeyword(@RequestParam String keyword, Model model) {
        List<Map> lists = mapService.getSearchList(keyword);

        model.addAttribute("keywords", lists);

        return "mainMap;";
    }
}
