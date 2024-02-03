package org.example.http;

import lombok.experimental.UtilityClass;
import org.example.exception.IllegalStatusCodeException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class HttpStatusImageDownloader {
    private static final String PATH_TEMPLATE = "src/main/resources/images/%d.jpg";

    private static final HttpClient client = HttpClient.newHttpClient();

    public static Path downloadStatusCodeImage(int statusCode) throws IllegalStatusCodeException,
            URISyntaxException, IOException, InterruptedException {

        String url = HttpStatusChecker.getStatusCodeUrl(statusCode);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        Path path = Paths.get(String.format(PATH_TEMPLATE, statusCode));
        client.send(request, HttpResponse.BodyHandlers.ofFile(path));
        return path;
    }
}
