package com.lzqstudy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzqstudy.bean.Chat;
import com.lzqstudy.utils.Result;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.util.List;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author 李正强
 * @version 1.0
 */
@RestController
public class ChatController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/api")
    public Result Api(@RequestBody Chat chat) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .requestFactory(() -> {
                    SSLContext sslContext = null;
                    try {
                        sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
                    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
                    return new HttpComponentsClientHttpRequestFactory(httpClient);
                })
                .build();
        String url = "https://api.cxhao.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer sk-FO4cpEVkcB3Jc1cX61Fb1c69F9064913973e6f794355770c"); // 替换为你的API密钥

        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(chat);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        return Result.success(response.getBody());
    }
}
