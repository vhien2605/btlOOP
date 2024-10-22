package app.service.subService;

import app.domain.Book;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GoogleApiService {
    private static String API_KEY;

    /**
     * Constructor get API_KEY from file database.properties
     */
    public GoogleApiService() {
        getAPI_KEY();
    }

    /**
     * Parses the JSON response from the Google Books API and converts it into a
     * list of Book objects.
     *
     * @param responseBody The JSON response string from the Google Books API
     *                     containing book information.
     * @return List<Book> A list of Book objects created from the response.
     * Returns an empty list if no books are found in the response.
     * @throws JSONException If the response JSON is improperly formatted.
     */
    public List<Book> parseBookInfo(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray items = jsonObject.optJSONArray("items");
        List<Book> books = new ArrayList<>();

        if (items == null || items.isEmpty()) {
            return books; // Trả về danh sách rỗng
        }

        for (int i = 0; i < items.length(); i++) {
            JSONObject bookJson = items.getJSONObject(i).getJSONObject("volumeInfo");

            String title = bookJson.optString("title", "No Title");
            String authors = bookJson.optJSONArray("authors") != null
                    ? bookJson.getJSONArray("authors").join(", ").replace("\"", "")
                    : "No Authors";
            String description = bookJson.optString("description", "No Description");
            String isbn = bookJson.optJSONArray("industryIdentifiers") != null
                    ? bookJson.getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier")
                    : "No ISBN";
            String category = bookJson.optJSONArray("categories") != null
                    ? bookJson.getJSONArray("categories").join(", ").replace("\"", "")
                    : "No Categories";
            String publisher = bookJson.optString("publisher", "No Publisher");

            books.add(new Book(isbn, title, authors, description, category, publisher));
        }

        return books;
    }

    /**
     * Searches for books from the Google Books API based on the provided search
     * query.
     *
     * @param query The search keyword for {@link Book}, which can be a {@code title}, {@code author},
     *              {@code ISBN}, or any related information.
     * @return List<Book> A list of Book objects found from the Google Books API.
     * Returns {@code null} if no books are found or if an error occurs.
     */
    public List<Book> searchBooks(String query) {
        if (query.isEmpty()) {
            return null;
        }

        String formattedQuery = query.trim().replace(" ", "+");

        // API URL với từ khóa tìm kiếm và API Key
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + query + "&maxResults=1&key=" + API_KEY;

        // Sử dụng HttpClient để gửi yêu cầu
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            // Gửi yêu cầu và chặn cho đến khi có phản hồi
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Book> books = parseBookInfo(response.body());
            return books;
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            // làm hiện thông báo ....
            return null;
        }
    }

    /**
     * getApiKey method to access Google Api
     * <p>
     * Load ApiKey from server properties
     * </p>
     */
    private void getAPI_KEY() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return;
            }
            prop.load(input);
            API_KEY = prop.getProperty("API_KEY");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
