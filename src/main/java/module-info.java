module com.grabba.clickd {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.grabba.clickd to javafx.fxml;
    exports com.grabba.clickd;
}