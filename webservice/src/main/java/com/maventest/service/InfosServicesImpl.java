package com.maventest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maventest.model.InfoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InfosServicesImpl implements InfosServices {

    private final HttpServletRequest request;

    public InfosServicesImpl(HttpServletRequest request) {
        this.request = request;
    }

    public void getAllInfos() throws IOException {
        List<InfoModel> infos = (List<InfoModel>) request.getSession().getAttribute("MY_SESSION_INFOS");
        if (infos == null) {
            log.info("info session is null read json file");
            request.getSession().setAttribute("MY_SESSION_INFOS", readJson());
            log.info("read json and stored in the session");
        }
    }

    static List<InfoModel> readJson() throws IOException {
        File file = new ClassPathResource("json/test.json").getFile();
        ObjectMapper objectMapper = new ObjectMapper();
        List<InfoModel> list = Arrays.asList(objectMapper.readValue(file, InfoModel[].class));
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public String saveInfo(InfoModel infoModel) throws IOException {
        List<InfoModel> infos = getInfoModels();

        Optional<InfoModel> info = infos.stream()
                .filter(obj -> obj.getId() == infoModel.getId())
                .findFirst();
        if(info.isPresent()){
            log.info("info exist");
            return "not saved";
        }
        infos.add(infoModel);
        request.getSession().setAttribute("MY_SESSION_INFOS", infos);
        return "saved";
    }

    public String updateInfo(InfoModel infoModel) throws IOException {
        List<InfoModel> infos = getInfoModels();

        infos.stream()
                .filter(obj -> obj.getId() == infoModel.getId())
                .findFirst()
                .ifPresent(o -> {
                    o.setDate(infoModel.getDate());
                    o.setLabel(infoModel.getLabel());
                    log.info("info updated");
                });
        request.getSession().setAttribute("MY_SESSION_INFOS", infos);
        return "saved";
    }


    public String deleteInfo(Integer id) throws IOException {
        List<InfoModel> infos = getInfoModels();

        Optional<InfoModel> info = infos.stream()
                .filter(obj -> obj.getId() == id)
                .findFirst();
        if (info.isPresent()) {
            infos.remove(info.get());
            log.info("info removed");
        }
        request.getSession().setAttribute("MY_SESSION_INFOS", infos);
        return "saved";
    }

    private List<InfoModel> getInfoModels() throws IOException {

        List<InfoModel> infos = (List<InfoModel>) request.getSession().getAttribute("MY_SESSION_INFOS");


        if (infos == null) {
            log.info("info session is null read json file");
            request.getSession().setAttribute("MY_SESSION_INFOS", readJson());
            log.info("read json and stored in the session");
        }
        return new ArrayList((List<InfoModel>) request.getSession().getAttribute("MY_SESSION_INFOS"));
    }
}
