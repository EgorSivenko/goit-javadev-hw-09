package org.example.http;

import org.example.exception.IllegalStatusCodeException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {
    private static final String URL_TEMPLATE = "https://http.cat/%d.jpg";

    private static final HttpClient client = HttpClient.newHttpClient();

    public static String getStatusUrl(int code) throws URISyntaxException, IOException, InterruptedException, IllegalStatusCodeException {
        String url = String.format(URL_TEMPLATE, code);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == HttpURLConnection.HTTP_NOT_FOUND) {
            throw new IllegalStatusCodeException("Invalid status code: " + code);
        }
        return url;
    }
}
