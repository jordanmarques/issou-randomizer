package com.issouRandom.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/issou")
public class IssouController {



    @ResponseBody
    @RequestMapping(value = "/wallpaper/random",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getRandomWallpaper() throws IOException {

        Integer randomImageNumber = randomBetween(1, 14);
        return getImages("images/wallpaper/" + randomImageNumber + ".png");

    }

    @ResponseBody
    @RequestMapping(value = "/wallpaper/{id}",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getWallpaperById(@PathVariable("id") String id) throws IOException {
        return getImages("images/wallpaper/" + id + ".png");

    }

    @ResponseBody
    @RequestMapping(value = "/icon/random",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getRandomIcon() throws IOException {

        Integer randomImageNumber = randomBetween(1, 341);
        return getImages("images/icon/" + randomImageNumber + ".png");

    }

    @ResponseBody
    @RequestMapping(value = "/icon/{id}",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getById(@PathVariable("id")String id) throws IOException {
        return getImages("images/icon/" + id + ".png");

    }



    private Integer getDirectorySize(String path){

        Integer size = 0;

        try {
            List<String> files = IOUtils.readLines(IssouController.class.getClassLoader()
                    .getResourceAsStream(path));
            size = files.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    private Integer randomBetween(Integer min, Integer max){
        return  (int)(Math.random() * (max-min)) + min;
    }

    private byte[] getImages(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        InputStream in = resource.getInputStream();

        return IOUtils.toByteArray(in);
    }

}
