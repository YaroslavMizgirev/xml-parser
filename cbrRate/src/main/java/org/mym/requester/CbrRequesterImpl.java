package org.mym.requester;

import lombok.extern.slf4j.Slf4j;
import org.mym.exceptions.RequesterException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class CbrRequesterImpl implements CbrRequester {
    @Override
    public String getRatesAsXml(String url) {
        try {
            log.info("request for url:{}", url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception exception) {
            if (exception instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            log.error("cbr.ru request error, url:{}", url, exception);
            throw new RequesterException(exception);
        }
    }
}
