package com.maventest.service;

import com.maventest.model.InfoModel;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface InfosServices {

    void getAllInfos() throws IOException;

    String saveInfo(InfoModel infoModel) throws IOException;

    String updateInfo(InfoModel infoModel) throws IOException;

    String deleteInfo(Integer id) throws IOException;
}
