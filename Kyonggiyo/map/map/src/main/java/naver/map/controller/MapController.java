package naver.map.controller;

import naver.map.domain.Map;
import naver.map.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MapController {


    @Autowired
    private MapService mapService;

    @GetMapping("/map")
    public String getKeyword(@RequestParam(value = "keyword", required = false) String keyword, Model model) {

        List<Map> maps = mapService.findMap();
        List<Map> suwons = mapService.findSuwon();
        List<Map> seouls = mapService.findSeoul();
        List<Map> lists = mapService.getSearchList(keyword);

        model.addAttribute("maps", maps);
        model.addAttribute("suwons", suwons);
        model.addAttribute("seouls", seouls);
        model.addAttribute("list", lists);

        System.out.println("lists = " + lists);

        return "mainMap";
    }

    @RequestMapping("/map/star")
    public String getStar(Model model) {

        List<Map> maps = mapService.starOrder();
        List<Map> suwons = mapService.starOrderSuwon();
        List<Map> seouls = mapService.starOrderSeoul();

        model.addAttribute("maps", maps);
        model.addAttribute("suwons", suwons);
        model.addAttribute("seouls", seouls);

        return "mainMap";
    }

}
