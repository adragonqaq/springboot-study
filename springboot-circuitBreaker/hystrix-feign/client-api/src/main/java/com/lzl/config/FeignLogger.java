package com.lzl.config;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FeignLogger extends Logger {

    @Override
    protected void log(String s, String s1, Object... objects) {
        log.info(String.format(methodTag(s) + s1, objects));
    }

    protected void logRequest(String configKey, Logger.Level logLevel, Request request) {
        // log.info("局部GlobalLogFeignLogger");
        this.log(configKey, "---> %s %s HTTP/1.1", request.httpMethod().name(), request.url());
        if (logLevel.ordinal() >= Logger.Level.BASIC.ordinal()) {
            String bodyText;
            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
                Map<String, Object> headMap = new HashMap<>();
                for (String s : request.headers().keySet()) {
                    bodyText = s;
                    for (String value : Util.valuesOrEmpty(request.headers(), bodyText)) {
                        headMap.put(bodyText, value);
                    }
                }
                this.log(configKey, "---> 头信息 %s", headMap);
            }
            int bodyLength = 0;
            if (request.body() != null) {
                bodyLength = request.length();
                if (logLevel.ordinal() >= Logger.Level.BASIC.ordinal()) {
                    bodyText = request.charset() != null ? new String(request.body(), request.charset()) : null;
                    this.log(configKey, "入参---> %s", bodyText != null ? bodyText : "Binary data");
                }
            }
            if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                this.log(configKey, "---> END HTTP (%s-byte body)", bodyLength);
            }
        }
    }

    protected Response logAndRebufferResponse(String configKey, Logger.Level logLevel, Response response, long elapsedTime) throws IOException {
        int status = response.status();
        if (logLevel.ordinal() >= Logger.Level.BASIC.ordinal()) {
            if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                for (String field : response.headers().keySet()) {
                    for (String value : Util.valuesOrEmpty(response.headers(), field)) {
                        this.log(configKey, "<--- %s: %s", field, value);
                    }
                }
            }

            int bodyLength = 0;
            if (response.body() != null && status != 204 && status != 205) {
                if (logLevel.ordinal() >= Logger.Level.FULL.ordinal()) {
                    this.log(configKey, "<---");
                }

                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                bodyLength = bodyData.length;
                if (logLevel.ordinal() >= Level.BASIC.ordinal() && bodyLength > 0) {
                    this.log(configKey, "出参<--- %s", Util.decodeOrDefault(bodyData, Util.UTF_8, "Binary data"));
                }
                if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                    this.log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
                }
                this.log(configKey, "<--- 接口耗时 -- %sms", elapsedTime);
                return response.toBuilder().body(bodyData).build();
            }
            if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                this.log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
            }
        }
        this.log(configKey, "<--- 接口耗时--%sms", elapsedTime);
        return response;
    }

    protected IOException logIOException(String configKey, Logger.Level logLevel, IOException ioe, long elapsedTime) {
        this.log(configKey, "<--- ERROR %s: %s (%sms)", ioe.getClass().getSimpleName(), ioe.getMessage(), elapsedTime);
        log.error("<---", ioe);
        if (logLevel.ordinal() >= Level.BASIC.ordinal()) {
            StringWriter sw = new StringWriter();
            ioe.printStackTrace(new PrintWriter(sw));
            this.log(configKey, "%s", sw.toString());
        }
        return ioe;
    }

}
