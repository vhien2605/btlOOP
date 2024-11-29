package app.controller.helper;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class EditImageView {
    public static void border(ImageView image, int px) {
        Rectangle clip = new Rectangle();
        clip.setWidth(image.getFitWidth()); // Đặt chiều rộng bằng chiều rộng của ImageView
        clip.setHeight(image.getFitHeight()); // Đặt chiều cao bằng chiều cao của ImageView
        clip.setArcWidth(10); // Độ bo góc ngang
        clip.setArcHeight(10);// Độ bo góc dọc
        image.setClip(clip);
    }
}
