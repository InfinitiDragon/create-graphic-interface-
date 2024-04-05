import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
public class CounterHandler implements Initializable {
    @FXML
    private Label totalWorkTime;
    @FXML
    private Label totalRestTime;
    @FXML
    private Label curentDuration;
    @FXML
    private ChoiceBox<String> dropButtonID;
    @FXML
    private ListView<String> timeList;

    private int listNumber=1;
    private Timeline timeline;
    private String activities[] = {"Робота","Відпочинок"};
    private int seconds=0;
    private int totalWT=0;
    private int totalRT=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropButtonID.getItems().addAll(activities);
        dropButtonID.setValue("Робота");
    }

    public  void startHandler(ActionEvent action){
        System.out.println("startHandler");
        if (timeline == null) {
            seconds = 0;
            curentDuration.setText(seconds + " seconds");
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    seconds++;
                    curentDuration.setText(seconds + " seconds");
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }else {
            timeline.play();
        }
    }
    public  void pauseHandler(ActionEvent action){
        System.out.println("pauseHandler");
        if (timeline != null) {
            timeline.pause();
        }
    }
    public  void stopHandler(ActionEvent action){
        System.out.println("stopHandler");
        if(dropButtonID.getValue().equals("Робота")){
            totalWT+=seconds;
            totalWorkTime.setText(totalWT+"");
            timeList.getItems().add(listNumber+" Тривалість роботи - "+seconds);
            listNumber++;
        }else {
            totalRT+=seconds;
            totalRestTime.setText(totalRT+"");
            timeList.getItems().add(listNumber + " Тривалість відпочинку - "+seconds);
            listNumber++;
        }
        if (timeline != null) {
            timeline.stop();
            seconds=0;
            curentDuration.setText(seconds+"");
        }
    }
}
