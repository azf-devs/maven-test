package com.maventest.controller;

import com.maventest.model.InfoModel;
import com.maventest.service.InfosServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MavenTestController {

    private final InfosServices infosServices;


    @GetMapping("/getInfos")
    public List<InfoModel> getInfos(HttpServletRequest request) throws IOException {
        infosServices.getAllInfos();
        List<InfoModel> infosModel = (List<InfoModel>) request.getSession().getAttribute("MY_SESSION_INFOS");
        return infosModel;

    }

    @PostMapping("/saveInfo")
    public String saveInfos(@RequestBody InfoModel infoModel) throws IOException {
        infosServices.saveInfo(infoModel);
        return "saved";
    }


    @PutMapping("/updateInfos")
    public String updateInfos(@RequestBody InfoModel infoModel) throws IOException {
        infosServices.updateInfo(infoModel);
        return "updated";
    }

    @DeleteMapping("/deleteInfos/{id}")
    public String deleteInfos(@PathVariable Integer id) throws IOException {
        infosServices.deleteInfo(id);
        return "deleted";
    }
}
