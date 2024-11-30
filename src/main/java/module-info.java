module at.die4fragezeichen.bullethell {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens at.die4fragezeichen.bullethell to javafx.fxml;
    exports at.die4fragezeichen.bullethell;
    exports at.die4fragezeichen.bullethell.Windows;
    opens at.die4fragezeichen.bullethell.Windows to javafx.fxml;
}