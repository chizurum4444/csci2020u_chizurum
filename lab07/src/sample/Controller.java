package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Controller {
    @FXML
    private BorderPane bp;
    @FXML
    private Canvas canvas;

    private TreeMap<String, Integer> wordCounts;



        private void setCsvMap() {
            // set each string data and initial count
            wordCounts = new TreeMap<>();
            wordCounts.put("FLASH FLOOD", 0);
            wordCounts.put("SEVERE THUNDERSTORM", 0);
            wordCounts.put("SPECIAL MARINE", 0);
            wordCounts.put("TORNADO", 0);
        }

    @FXML
    private GraphicsContext gc;


    @FXML
     private void initialize() throws IOException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        setCsvMap();
        parseFile(wordCounts,"src/sample/weatherwarnings-2015.csv");
        drawPieChart(gc);
    }


    private void parseFile(TreeMap x, String file) throws IOException {

        System.out.println("Starting parsing the file:" + file);

        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(",");
        // scanning token by token
        while (scanner.hasNext()){
            String  token = scanner.next();
            if (x.containsKey(token)) {
                int previous = (int) x.get(token);
                previous += 1;
                x.put(token, previous);
            }
        }
    }

    public void drawPieChart(GraphicsContext gc) {

        Color[] pieColours = {
                Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON
        };

        gc.setFill(Color.AQUA);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(30, 210, 45, 25);
        gc.fillRect(30, 210, 45, 25);
        gc.setFill(Color.BLACK);
        gc.fillText("FLASH FLOOD", 80, 228);

        gc.setFill(Color.GOLD);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(30, 250, 45, 25);
        gc.fillRect(30, 250, 45, 25);
        gc.setFill(Color.BLACK);
        gc.fillText("SEVERE THUNDERSTORM", 80, 268);

        gc.setFill(Color.DARKORANGE);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(30, 290, 45, 25);
        gc.fillRect(30, 290, 45, 25);
        gc.setFill(Color.BLACK);
        gc.fillText("SPECIAL MARINE", 80, 308);

        gc.setFill(Color.DARKSALMON);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(30, 330, 45, 25);
        gc.fillRect(30, 330, 45, 25);
        gc.setFill(Color.BLACK);
        gc.fillText("TORONTO", 80, 348);

        int sum = 0;
        Set<String> keys = wordCounts.keySet();
        for (String key : keys) {
            sum += wordCounts.get(key);
        }

        double startAngle = 0.0;
        int i = 0;
        for (String key : keys) {
            double slicePercentage = (double) wordCounts.get(key) / (double) sum;
            double sweepAngle = slicePercentage * 360.0;

            gc.setFill(pieColours[i]);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(350, 150, 400, 400, startAngle, sweepAngle, ArcType.ROUND);
            gc.fillArc(350, 150, 400, 400, startAngle, sweepAngle, ArcType.ROUND);

            startAngle += sweepAngle;
            i++;
        }
    }
}
