package com.lzl.restTemplate.Bean;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
@Builder
public class RestLog {

    private String reqUrl;
    private String method;
    private HttpHeaders headers;
    private String reqBody;
    private String resBody;
    private long costTime;
    private int resStatus;

}
