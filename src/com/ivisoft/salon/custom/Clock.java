package com.ivisoft.salon.custom;

import java.time.LocalTime;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Clock extends Text {
    
    public Clock() {
        bindToTime();
    }

    private void bindToTime() {
        Timeline timeline = new Timeline(
          new KeyFrame(Duration.seconds(0),
            new EventHandler<ActionEvent>() {
              @Override public void handle(ActionEvent actionEvent) {
                LocalTime time = LocalTime.now();
                String hour = time.getHour() < 10 ? "0" + time.getHour() : time.getHour() + "";
                String minute = time.getMinute() < 10 ? "0" + time.getMinute() : time.getMinute() + "";
                setText(hour + ":" + minute);
              }
            }
          ),
          new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
      }
}