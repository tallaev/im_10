package ru.vorotov.simulationslab10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
    @FXML
    private Label label9;
    @FXML
    private Label label10;
    @FXML
    private Label label11;
    @FXML
    private Label label12;
    @FXML
    private Label label13;
    @FXML
    private Label label14;
    @FXML
    private Label winnerLabel;
    @FXML
    private Label winnerTextLabel;
    @FXML
    private Label score1Label;
    @FXML
    private Label score2Label;
    @FXML
    private Label team1Label;
    @FXML
    private Label team2Label;

    List<Label> labels;

    Random random = new Random();
    List<Team> teams = new ArrayList<>();
    List<Team> firstRoundWinners = new ArrayList<>();
    List<Team> secondRoundWinners = new ArrayList<>();
    int roundCounter = 0;
    int gameCounter = 0;
    int[] scoreCurrentGame = new int[2];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labels = new ArrayList<>(List.of(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14));

        teams.add(new Team("Томь", 100));
        teams.add(new Team("Краснодар", random.nextInt(10) + 1));
        teams.add(new Team("Спартак", random.nextInt(10) + 1));
        teams.add(new Team("Реал Мадрид", random.nextInt(10) + 1));
        teams.add(new Team("ПСЖ", random.nextInt(10) + 1));
        teams.add(new Team("Барселона", random.nextInt(10) + 1));
        teams.add(new Team("Ман Сити", random.nextInt(10) + 1));
        teams.add(new Team("Црвена Звезда", random.nextInt(10) + 1));

        Collections.shuffle(teams);

        for (int i = 0; i < teams.size(); i++) {
            labels.get(i).setText(teams.get(i).getName());
        }

        winnerTextLabel.setVisible(false);
    }

    public void onPlayButtonClick(ActionEvent actionEvent) {
        scoreCurrentGame[0] = 0;
        scoreCurrentGame[1] = 0;

        switch (roundCounter) {
            case 0:
                playFirstRound();
                break;
            case 1:
                playSecondRound();
                break;
            case 2:
                playThirdRound();
                break;
        }
    }

    private void playFirstRound() {
        Team team1 = teams.get(0 + gameCounter * 2);
        Team team2 = teams.get(1 + gameCounter * 2);

        while (scoreCurrentGame[0] == scoreCurrentGame[1]) {
            scoreCurrentGame[0] = PoissonDistribution(team1);
            scoreCurrentGame[1] = PoissonDistribution(team2);
        }

        if (scoreCurrentGame[0] < scoreCurrentGame[1]) {
            labels.get(8 + gameCounter).setText(team2.getName());
            firstRoundWinners.add(team2);
        } else if (scoreCurrentGame[0] > scoreCurrentGame[1]) {
            labels.get(8 + gameCounter).setText(team1.getName());
            firstRoundWinners.add(team1);
        }

        team1Label.setText(team1.getName());
        team2Label.setText(team2.getName());

        score1Label.setText(String.valueOf(scoreCurrentGame[0]));
        score2Label.setText(String.valueOf(scoreCurrentGame[1]));

        if (gameCounter == 3) {
            gameCounter = 0;
            roundCounter++;
            System.out.println(firstRoundWinners);
        } else {
            gameCounter++;
        }
    }

    private void playSecondRound() {
        Team team1 = firstRoundWinners.get(0 + gameCounter * 2);
        Team team2 = firstRoundWinners.get(1 + gameCounter * 2);

        while (scoreCurrentGame[0] == scoreCurrentGame[1]) {
            scoreCurrentGame[0] = PoissonDistribution(team1);
            scoreCurrentGame[1] = PoissonDistribution(team2);
        }

        if (scoreCurrentGame[0] < scoreCurrentGame[1]) {
            labels.get(12 + gameCounter).setText(team2.getName());
            secondRoundWinners.add(team2);
        } else if (scoreCurrentGame[0] > scoreCurrentGame[1]) {
            labels.get(12 + gameCounter).setText(team1.getName());
            secondRoundWinners.add(team1);
        }

        team1Label.setText(team1.getName());
        team2Label.setText(team2.getName());

        score1Label.setText(String.valueOf(scoreCurrentGame[0]));
        score2Label.setText(String.valueOf(scoreCurrentGame[1]));

        if (gameCounter == 1) {
            gameCounter = 0;
            roundCounter++;
            System.out.println(secondRoundWinners);
        } else {
            gameCounter++;
        }
    }

    private void playThirdRound() {
        Team team1 = secondRoundWinners.getFirst();
        Team team2 = secondRoundWinners.getLast();

        while (scoreCurrentGame[0] == scoreCurrentGame[1]) {
            scoreCurrentGame[0] = PoissonDistribution(team1);
            scoreCurrentGame[1] = PoissonDistribution(team2);
        }

        if (scoreCurrentGame[0] < scoreCurrentGame[1]) {
            winnerLabel.setText(team2.getName());
            secondRoundWinners.add(team2);
        } else if (scoreCurrentGame[0] > scoreCurrentGame[1]) {
            winnerLabel.setText(team1.getName());
            secondRoundWinners.add(team1);
        }

        team1Label.setText(team1.getName());
        team2Label.setText(team2.getName());

        score1Label.setText(String.valueOf(scoreCurrentGame[0]));
        score2Label.setText(String.valueOf(scoreCurrentGame[1]));

        winnerTextLabel.setVisible(true);
        roundCounter++;
    }

    private int PoissonDistribution(Team team) {
        int m = 0;
        double S = Math.log(random.nextDouble());
        double lambda = team.getLambda();

        while (S > -lambda) {
            S += Math.log(random.nextDouble());
            m++;
        }

        return m;
    }
}