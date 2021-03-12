package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.awt.*;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    public GraphicsContext gc;

    //Data for bar chart
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7
    };

    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3
    };

    //Data for pie chart
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };

    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };

    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM,
    };

    @FXML
    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawBarChart(100,400, avgHousingPricesByYear, Color.RED,0, gc);
        drawBarChart(100,400, avgCommercialPricesByYear,Color.BLUE,100/avgCommercialPricesByYear.length, gc);
        drawPieChart(gc);
    }

    public void drawGraph(int w, int h, double[] data, Color color, GraphicsContext gc){
        gc.setFill(color);

        double maxVal = Double.NEGATIVE_INFINITY, minVal = Double.MAX_VALUE;
        for (double val : data) {
            if (val > maxVal)
                maxVal = val;
            if (val < minVal)
                minVal = val;
        }

        double barWidth = w / data.length;
        double x = 0;
        for (double val : data) {
            double barHeight = ((val - minVal) / (maxVal - minVal)) * h;
            gc.fillRect(x, (h - barHeight), barWidth, barHeight);
            x += barWidth;
        }
    }

    public void drawBarChart(int w, int h, double[] data, Color colour, int startX, GraphicsContext gc){
        gc.setFill(colour);
        double barWidth = (w/data.length);

        double maxVal = 1613246.3;
        double minVal = 0;
        //finds the max and min for scaled height calculation
        for(double val: data){
            if(val > maxVal){
                maxVal = val;
            } if(val < minVal){
                minVal = val;
            }
        }
//        System.out.println(maxVal);
//        System.out.println(minVal);
        double x = startX;

        x += 100;
        for(double val: data){
            //scaled height calculation
            double barHeight = ((val-minVal) / (maxVal - minVal)) * h;
            gc.fillRect(x,(h-barHeight),barWidth,barHeight);
            //added "2 *" to fit another graph between the graph later
            x += 2 * barWidth;

            x += 5;
        }

    }

    public void drawPieChart(GraphicsContext gc) {
        int numOfPurchases = 0;
        for (int purchasesForAgeGroup: purchasesByAgeGroup) {
            numOfPurchases += purchasesForAgeGroup;
        }

        double startAngle = 0.0;
        for (int i = 0; i < purchasesByAgeGroup.length; i++) {
            double slicePercentage = (double) purchasesByAgeGroup[i] / (double) numOfPurchases;
            double sweepAngle = slicePercentage * 360.0;

            gc.setFill(pieColours[i]);
            gc.fillArc(500, 100, 300, 300, startAngle, sweepAngle, ArcType.ROUND);

            startAngle += sweepAngle;
        }
    }

}
