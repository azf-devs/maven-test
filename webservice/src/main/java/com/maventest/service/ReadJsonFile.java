package com.maventest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maventest.model.InfoModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class ReadJsonFile implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        InfosServicesImpl.readJson();
    }
}
