package org.example.http;

import org.example.util.UserInput;
import org.example.exception.IllegalStatusCodeException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class HttpImageStatusCli {
    public static void main(String[] args) {
        askStatus();
    }

    private static void askStatus() {
        System.out.print("Enter HTTP status code: ");
        int statusCode = UserInput.readStatusCode();

        try {
            Path path = HttpStatusImageDownloader.downloadStatusCodeImage(statusCode);
            System.out.println("Image downloaded to file: " + path);
        } catch (IllegalStatusCodeException e) {
            System.out.println(e.getMessage());
            askStatus();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}
