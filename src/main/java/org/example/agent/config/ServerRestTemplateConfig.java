package org.example.agent.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.example.agent.util.SSLUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.*;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContexts;


import java.io.IOException;

/**
 * rest template config
 *
 * @author hantong6
 */
@Configuration
public class ServerRestTemplateConfig {

    @Bean("serverGatewayRestTemplateNoSsl")
    public RestTemplate serverGatewayRestTemplateNoSsl() throws Exception {
        try {
            SSLUtil.turnOffSslChecking();

            TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);

            return new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Bean("serverGatewayRestTemplate")
    public RestTemplate serverGatewayRestTemplate() throws Exception {
        //request config
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(3000)
                .setSocketTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .build();
        //connection pool
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(50);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(5);
        //ssl
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, (hostname, session) -> true);
        //http client
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, false))
                .build();
        //request factory
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);
        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(httpComponentsClientHttpRequestFactory);
        //rest template
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(bufferingClientHttpRequestFactory);
        restTemplate.setErrorHandler(new GatewayHttpErrorHandler());
        return restTemplate;
    }


    static class GatewayHttpErrorHandler extends DefaultResponseErrorHandler {
        @Override
        protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
            //do nothing
        }
    }

}
