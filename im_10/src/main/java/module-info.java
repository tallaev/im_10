module ru.vorotov.simulationslab10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vorotov.simulationslab10 to javafx.fxml;
    exports ru.vorotov.simulationslab10;
}