package com.ihr360.applet.customization.qys.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ihr360.applet.customization.qys.consts.Constants;
import com.ihr360.applet.customization.qys.utils.Base64Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author wyl
 * @date  2024/4/15 16:52
 * @desc
 */
@Component
public class WebClientUtil {
 
    @Resource
    WebClient ihrWebClient;

    @Value("${spring.security.oauth2.client.registration.ihrOAuth2Client.client-id}")
    private String AppID;
    @Value("${spring.security.oauth2.client.registration.ihrOAuth2Client.client-secret}")
    private String AppSecret;
 

    /**
     * get方法
     * @param url
     * @param responseType
     * @return
     * @param
     */
    public <T> T get(String url, Class<T> responseType) {
        return get(url, null,responseType);
    }

    public <T> T get(String url, ParameterizedTypeReference<T> elementTypeRef) {
        return get(url, null,elementTypeRef);
    }

    public <T> T get(String url, MultiValueMap<String, String> requestParams, Class<T> responseType) {
        return ihrWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParams(requestParams)
                        .build())
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(Constants.OAUTH2_REGISTRY_ID_IHR))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <T> T getData(String url, MultiValueMap<String, String> requestParams, Class<T> responseType) {
        String response = get(url, requestParams, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONObject data = jsonObject.getJSONObject("data");
        return data == null ? null : data.toJavaObject(responseType);
    }

    public <T> List<T> getListData(String url, MultiValueMap<String, String> requestParams, Class<T> responseType) {
        String response = get(url, requestParams, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        return dataArray == null ? null : dataArray.toJavaList(responseType);
    }

    public <T> T get(String url , MultiValueMap<String, String> requestParams, ParameterizedTypeReference<T> elementTypeRef) {
        return ihrWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParams(requestParams)
                        .build())
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(Constants.OAUTH2_REGISTRY_ID_IHR))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(elementTypeRef)
                .block();
    }

    public <T> T post(String url, Object requestBody, Class<T> responseType) {
        return post(url, null,requestBody,responseType);
    }
 
    /**
     * post方法
     * @param url
     * @param requestBody
     * @param responseType
     * @return
     * @param
     */
    public <T> T post(String url, MultiValueMap<String, String> requestParams,Object requestBody, Class<T> responseType) {
        return ihrWebClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParams(requestParams)
                        .build())
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(Constants.OAUTH2_REGISTRY_ID_IHR))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <T> T postData(String url, MultiValueMap<String, String> requestParams,Object requestBody, Class<T> responseType) {
        String response = post(url, requestParams,requestBody,String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONObject data = jsonObject.getJSONObject("data");
        return data == null ? null : data.toJavaObject(responseType);
    }

    public <T> List<T> postListData(String url, MultiValueMap<String, String> requestParams, Object requestBody, Class<T> responseType) {
        String response = post(url, requestParams,requestBody,String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        return dataArray == null ? null : dataArray.toJavaList(responseType);
    }



    public <T> T post(String url, Object requestBody, ParameterizedTypeReference<T> elementTypeRef) {
        return post(url, null,requestBody,elementTypeRef);
    }

    public <T> T post(String url, MultiValueMap<String, String> requestParams,Object requestBody, ParameterizedTypeReference<T> elementTypeRef) {
        return ihrWebClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParams(requestParams)
                        .build())
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(Constants.OAUTH2_REGISTRY_ID_IHR))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(elementTypeRef)
                .block();
    }

    public <T> T postToken(String url, MultiValueMap<String, String> requestParams,Object requestBody, Class<T> responseType) throws UnsupportedEncodingException {
        return ihrWebClient.post()
                .uri(url)
                .header("Authorization", "Basic " + Base64Utility.encoder(AppID+":"+AppSecret))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
 
    /**
     * put方法
     * @param url
     * @param requestBody
     * @param responseType
     * @return
     * @param
     */
    public <T> T put(String url, Object requestBody, Class<T> responseType){
        return ihrWebClient.put()
                .uri(url)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(Constants.OAUTH2_REGISTRY_ID_IHR))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
 
    /**
     * 删除方法
     * @param url
     * @param responseType
     * @return
     * @param
     */
    public <T> T delete(String url, Class<T> responseType){
        return delete(url, null, null, responseType);
    }

    public <T> T delete(String url, MultiValueMap<String, String> requestParams, Object requestBody, Class<T> responseType) {
        return ihrWebClient
                .method(HttpMethod.DELETE)
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParams(requestParams)
                        .build())
                .bodyValue(requestBody)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(Constants.OAUTH2_REGISTRY_ID_IHR))
                .retrieve()
                .bodyToMono(responseType)
                .block();

    }

    public String delete(String url, String name, String value, List<String> idList) {
        return ihrWebClient
                .method(HttpMethod.DELETE)
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParam(name, value)
                        .build())
                .bodyValue(idList)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(Constants.OAUTH2_REGISTRY_ID_IHR))
                .retrieve()
                .bodyToMono(String.class)
                .map(string -> string)
                .block();

    }
}
