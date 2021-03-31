package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    public GraphicsContext gc;

    public ArrayList<Float> downloadStockPrices(String stockTicker){
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<Float> stockValues = new ArrayList<Float>();
        String url = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true", stockTicker);
        try {
            InputStream input = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            reader.readLine();
            String line;
            while((line = reader.readLine()) != null) {
                String[] x = line.split(",");
                values.add(x[5]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String i: values) {
            stockValues.add(Float.parseFloat(i));
        }
        System.out.println(stockValues);
        return stockValues;
    }

    public void drawLinePlot(GraphicsContext gc, ArrayList<Float>stock1, ArrayList<Float>stock2){
        double width = 750;
        double height = 750;
        //Draw X and Y axis
        //X-AXIS
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(50,height-50,width-50,height-50);
        //Y-AXIS
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(50,height-50,50,50);

        //Function for plotting the lines
        plotLine(gc, stock1, Color.RED);
        plotLine(gc, stock2, Color.BLUE);

    }

    public void plotLine(GraphicsContext gc, ArrayList<Float> data, Color color){
        gc.setStroke(color);
        gc.setLineWidth(2);
        float height = 750f;
        float width = 750f;

        float maxVal = 758.88f;

        float xScale = ( width - 2 * 50) / (data.size() - 1);
        float yScale = (height - 2 * 50) / (maxVal - 1);

        ArrayList<Float> xPoints = new ArrayList<Float>();
        ArrayList<Float> yPoints = new ArrayList<Float>();
        for (int i = 0; i < data.size(); i++) {
            float x1 = (i * xScale + 50);
            float y1 = ((maxVal - data.get(i)) * yScale + 50);
            xPoints.add(x1);
            yPoints.add(y1);
        }

        for(int i = 0; i < xPoints.size() - 1 && i < yPoints.size() - 1; i++){
            float x1 = xPoints.get(i);
            float y1 = yPoints.get(i);
            float x2 = xPoints.get(i + 1);
            float y2 = yPoints.get(i + 1);
            gc.strokeLine(x1, y1, x2,y2);
        }
    }

    @FXML
    public void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawLinePlot(gc, downloadStockPrices("GOOG"), downloadStockPrices("AAPL"));
    }
}
