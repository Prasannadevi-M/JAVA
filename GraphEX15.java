import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class RealTimeChartApp extends Application {
    private static final int MAX_POINTS = 20;
    private int time = 0;
    private final Random random = new Random();
    private XYChart.Series<Number, Number> series = new XYChart.Series<>();
    private Timeline timeline;

    @Override
    public void start(Stage stage) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis(0, 100, 10);
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Live Sensor Data");
        chart.setLegendVisible(false);
        chart.getData().add(series);

        Button start = new Button("Start");
        Button stop = new Button("Stop");
        stop.setDisable(true);

        start.setOnAction(e -> { timeline.play(); start.setDisable(true); stop.setDisable(false); });
        stop.setOnAction(e -> { timeline.stop(); start.setDisable(false); stop.setDisable(true); });

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> addPoint(xAxis)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        HBox controls = new HBox(10, start, stop);
        controls.setStyle("-fx-alignment: center; -fx-padding: 10;");

        stage.setScene(new Scene(new BorderPane(chart, null, null, controls, null), 700, 500));
        stage.setTitle("Real-Time Chart");
        stage.show();
    }

    private void addPoint(NumberAxis xAxis) {
        time++;
        double value = 30 + random.nextDouble() * 40;
        series.getData().add(new XYChart.Data<>(time, value));
        if (series.getData().size() > MAX_POINTS) series.getData().remove(0);
        if (time > MAX_POINTS) {
            xAxis.setLowerBound(time - MAX_POINTS);
            xAxis.setUpperBound(time);
        }
        System.out.printf("t=%d, value=%.2f%n", time, value);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
