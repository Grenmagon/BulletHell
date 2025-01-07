package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EnterPlayerNameWindow extends Window {

    private TextField playerNameField;  // Поле для ввода имени игрока
    private Button confirmButton;
    private Label feedbackLabel;

    public EnterPlayerNameWindow(Stage stage, BaseWindow base, String name) {
        super(stage, base, name);

        // Создаем вертикальный контейнер
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);// Выравниваем содержимое по центру
        layout.setPrefSize(GameInformations.WINDOWSIZEX, GameInformations.WINDOWSIZEY); // Используем константы для размеров окна


        Label instructionLabel = new Label("Enter your name:");// Добавляем заголовок
        instructionLabel.setStyle("-fx-font-family: 'Horizon'; -fx-font-size: 20px;");

        playerNameField = new TextField();
        playerNameField.setPromptText("Your name here");// подсказка игроку, чтобы ввел имя
        playerNameField.setMaxWidth(200);


        confirmButton = new Button("Confirm");// Кнопка подтверждения
        confirmButton.setStyle("-fx-font-family: 'Horizon'; -fx-font-size: 16px; -fx-border-color: black; -fx-border-width: 2px;");
        confirmButton.setOnMouseClicked(mouseEvent -> savePlayerName());


        layout.getChildren().addAll(instructionLabel, playerNameField, confirmButton);// Добавляем элементы в контейнер

        feedbackLabel = new Label();
        feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;"); // Красный текст для ошибок
        layout.getChildren().add(feedbackLabel);

        this.getChildren().add(layout);// Добавляем контейнер в окно

    }

    private void savePlayerName() {
        String playerName = playerNameField.getText(); // Получаем текст из поля
        if (playerName.isEmpty()) {
            feedbackLabel.setText("Player name cannot be empty!"); // Сообщение о пустом поле
            System.out.println("Player name is empty."); // Отладочный вывод
        } else if (playerName.length() < 3) {
            feedbackLabel.setText("Player name must be at least 3 characters long!"); // Сообщение о коротком имени
            System.out.println("Player name is too short: " + playerName); // Отладочный вывод
        } else {
            feedbackLabel.setText(""); // Очищаем метку при успешном вводе
            System.out.println("Player name is valid: " + playerName); // Отладочный вывод

            // Переход на стартовое окно
            try {
                Window.setActive("StartWindow"); // Переход на стартовое окно
                System.out.println("Switched back to StartWindow successfully.");
            } catch (Exception e) {
                System.err.println("Error switching to StartWindow: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

        @Override
        protected void doActive () {

        }

        @Override
        public void checkActive () {

        }

        @Override
        protected void doNotActive () {

        }
    }

