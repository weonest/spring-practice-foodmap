package naver.map.controller;

import naver.map.domain.Map;
import naver.map.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

}
