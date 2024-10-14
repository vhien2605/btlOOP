package app.service;

import app.domain.Book;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleApiService {

    public List<Book> parseBookInfo(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray items = jsonObject.optJSONArray("items");
        List<Book> books = new ArrayList<>();

        if (items == null || items.length() == 0) {
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
}
