package cn.smartrick.metaverse.utils;

import cn.hutool.http.Header;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * [ HttpUtils 基于HttpClient的简单http请求封装]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2019 1024lab.netInc. All rights reserved.
 * @date 2021年11月24日10:31:57
 * @since JDK1.8
 */
public class SmartHttpUtil {

    private static String defaultUserAgent = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";

    /**
     * 发送Get请求
     *
     * @return HttpRespWarp
     * @throws Exception
     */
    public static HttpRespWarp get(String url) throws Exception {
        return get(url, null, null, ContentType.DEFAULT_TEXT);
    }

    public static HttpRespWarp get(String url, Map<String, String> params) throws Exception {
        return get(url, params, null, ContentType.DEFAULT_TEXT);
    }

    public static HttpRespWarp get(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        return get(url, params, headers, ContentType.DEFAULT_TEXT);
    }

    public static HttpRespWarp get(String url, Map<String, String> params, Map<String, String> header, ContentType contentType) throws Exception {
        HttpGet httpGet = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            List<String> paramsList = new ArrayList<>();
            if (params != null) {
                for (Entry<String, String> entry : params.entrySet()) {
                    paramsList.add(entry.getKey() + "=" + entry.getValue());
                }
            }
            if (CollectionUtils.isNotEmpty(paramsList)) {
                String paramsStr = StringUtils.join(paramsList, "&");
                url = url + "?" + paramsStr;
            }
            httpGet = new HttpGet(url);
            httpGet.setHeader(Header.CONTENT_TYPE.toString(), contentType.toString());
            httpGet.setHeader(Header.USER_AGENT.toString(), defaultUserAgent);
            if (header != null) {
                for (Entry<String, String> entry : header.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            return new HttpRespWarp(httpClient.execute(httpGet));
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    public static HttpRespWarp postJson(String url, String json) throws Exception {
        return postJson(url, json, null);
    }

    public static HttpRespWarp postJson(String url, String json, Map<String, String> header) throws Exception {
        StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
        entity.setContentEncoding(StandardCharsets.UTF_8.name());
        entity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        return post(url, entity, header, ContentType.APPLICATION_JSON);
    }

    public static HttpRespWarp postForm(String url, Map<String, String> params) throws Exception {
        return postForm(url, params, null);
    }

    public static HttpRespWarp postForm(String url, Map<String, String> params, Map<String, String> header) throws Exception {
        List<NameValuePair> nvps = new ArrayList<>();
        if (params != null) {
            for (Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
        return post(url, urlEncodedFormEntity, header, ContentType.APPLICATION_FORM_URLENCODED);
    }

    public static HttpRespWarp post(String url, HttpEntity httpEntity, Map<String, String> header, ContentType contentType) throws Exception {
        HttpPost httpPost = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.setHeader(Header.CONTENT_TYPE.toString(), contentType.toString());
            httpPost.setHeader(Header.USER_AGENT.toString(), defaultUserAgent);
            if (header != null) {
                for (Entry<String, String> entry : header.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            //设置参数到请求对象中
            httpPost.setEntity(httpEntity);
            return new HttpRespWarp(httpClient.execute(httpPost));
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    public static class HttpRespWarp {
        private HttpResponse resp;

        public HttpRespWarp(HttpResponse resp) {
            this.resp = resp;
        }

        public HttpResponse getResp() {
            return resp;
        }

        public boolean isSuccess() {
            int statusCode = this.resp.getStatusLine().getStatusCode();
            return statusCode == HttpStatus.SC_OK;
        }

        public boolean isFailed() {
            return !isSuccess();
        }

        public String toBodyString() {
            try {
                return EntityUtils.toString(this.resp.getEntity(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        public String toBodyString(String charset) {
            try {
                return EntityUtils.toString(this.resp.getEntity(), charset);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        public InputStream toBodyInputStream() throws IOException {
            return this.resp.getEntity().getContent();
        }

        public byte[] toBodyByte() throws IOException {
            InputStream bodyInputStream = toBodyInputStream();
            return IOUtils.toByteArray(bodyInputStream);
        }
    }
}
