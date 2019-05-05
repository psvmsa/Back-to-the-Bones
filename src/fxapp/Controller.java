package fxapp;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Die currentDie = new Die(6);
    private ObservableList<Integer> rolls = FXCollections.observableArrayList();
    private ObservableList<Die> dice = FXCollections.observableArrayList();

    public Button clearButton;
    public Button rollButton;
    public Label rollCountLabel;
    public Label statusLabel;
    public Label sumLabel;
    public ListView<Die> diceListView;
    public ListView<Integer> rollsListView;
    public Label bigRollLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dice.add(new Die(4));
        dice.add(new Die(6));
        dice.add(new Die(8));
        dice.add(new Die(10));
        dice.add(new Die(12));
        dice.add(new Die(20));

        diceListView.setItems(dice);
        rollsListView.setItems(rolls);
        rollsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        diceListViewListener();
        rollsListViewListener();
    }

    public void roll() {
        int currentRoll = currentDie.roll();
        rolls.add(0, currentRoll);

        rollCountLabel.setText("Rolls: " + rolls.size());
        sumLabel.setText("Sum: " + rolls.stream().mapToInt(roll -> roll).sum());
        if (rolls.size() > 1) statusLabel.setText("Rolled " + rolls.size() + " times.");
        else statusLabel.setText("Rolled " + rolls.size() + " time.");
        bigRollLabel.setText(String.valueOf(currentRoll));
    }

    public void clear() {
        rolls.clear();

        rollCountLabel.setText("Rolls: " + 0);
        sumLabel.setText("Sum: " + 0);
        statusLabel.setText("Cleared!");
        bigRollLabel.setText("\uD83D\uDE0E");
    }

    public void sort() {
        FXCollections.sort(rolls);
        FXCollections.reverse(rolls);
        statusLabel.setText("Sorted!");
    }

    private void diceListViewListener() {
        diceListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentDie = newValue;
            rollButton.setText("Roll " + newValue);
        });
    }

    private void rollsListViewListener() {
        rollsListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Integer>) c -> {
            int sumOfSelected = c.getList().stream().mapToInt(i -> i).sum();
            int sumOfRolls = rolls.stream().mapToInt(i -> i).sum();

            rollCountLabel.setText("Rolls: " + rolls.size() + " (" + c.getList().size() + ")");
            sumLabel.setText("Sum: " + sumOfRolls + " (" + sumOfSelected + ")");
        });
    }
}
