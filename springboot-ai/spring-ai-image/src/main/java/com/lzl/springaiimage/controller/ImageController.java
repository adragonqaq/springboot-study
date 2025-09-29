package com.lzl.springaiimage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/images")
@Slf4j
public class ImageController {
    private final ImageModel imageModel;

    private static final String DEFAULT_PROMPT = "一个优雅的现代中国美女，有着平静的表情。她乌黑的头发梳成简单的发髻，头上戴着精致的玉发夹。她穿着一件当代汉服，用淡青色丝绸面料制成，上面有微妙的花卉刺绣。她手持一把圆形的绢扇，站在一个宁静的竹园里，背后是一座雾蒙蒙的山。柔和，漫射灯光，水墨画美学，构图优雅，4K，细节度高.";

    public ImageController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    /**
     * 测试图片生成
     *
     * @param response
     */
    @GetMapping(value = "/test")
    public void imageTest(HttpServletResponse response) {
        ImageResponse imageResponse = imageModel.call(new ImagePrompt(DEFAULT_PROMPT));
        String url = imageResponse.getResult().getOutput().getUrl();
        log.info("url: {}", url);
        return;
    }

    /**
     * 多条件生成图
     *
     * @param subject
     * @param environment
     * @param height
     * @param width
     * @param style
     */
    @GetMapping(value = "/multipleConditions")
    public void multipleConditions(@RequestParam(value = "subject") String subject,
                                   @RequestParam(value = "environment") String environment,
                                   @RequestParam(value = "height") Integer height,
                                   @RequestParam(value = "width") Integer width,
                                   @RequestParam(value = "style") String style) {

        String prompt = String.format("一个%s,置身于%s的环境中,使用%s的艺术风格,高清4k画质,细节精致",
                subject, environment, style);

        ImageOptions options = ImageOptionsBuilder.builder()
                .height(height)
                .width(width)
                .build();

        ImageResponse response = imageModel.call(new ImagePrompt(prompt, options));
        String url = response.getResult().getOutput().getUrl();
        log.info("url: {}", url);
        return;
    }
}

    
    
    