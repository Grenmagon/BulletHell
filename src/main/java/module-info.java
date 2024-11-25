module at.die4fragezeichen.bullethell {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.die4fragezeichen.bullethell to javafx.fxml;
    exports at.die4fragezeichen.bullethell;
}