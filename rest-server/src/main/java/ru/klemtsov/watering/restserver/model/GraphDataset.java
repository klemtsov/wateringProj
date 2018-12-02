package ru.klemtsov.watering.restserver.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GraphDataset {
    private String label;
    private boolean fill;
    private Double lineTension;
    private String backgroundColor;
    private String borderColor;
    private String borderCapStyle;
    private List<String> borderDash;
    private Double borderDashOffset;
    private String borderJoinStyle;
    private String pointBorderColor;
    private String pointBackgroundColor;
    private int pointBorderWidth;
    private int pointHoverRadius;
    private String pointHoverBackgroundColor;
    private String pointHoverBorderColor;
    private int pointHoverBorderWidth;
    private int pointRadius;
    private int pointHitRadius;
    private List<BigDecimal> data;

    public GraphDataset() {
        label = "Влажность";
        fill = true;
        lineTension = 0.1;
        backgroundColor = "rgba(75,192,192,0.4)";
        borderColor = "rgba(75,192,192,1)";
        borderCapStyle = "butt";
        borderDash = new ArrayList<>();
        borderDashOffset = 0.0;
        borderJoinStyle = "miter";
        pointBorderColor = "rgba(75,192,192,1)";
        pointBackgroundColor = "#fff";
        pointBorderWidth = 1;
        pointHoverRadius = 10;
        pointHoverBackgroundColor = "rgba(75,192,192,1)";
        pointHoverBorderColor = "rgba(220,220,220,1)";
        pointHoverBorderWidth = 1;
        pointRadius = 1;
        pointHitRadius = 10;
        data = new ArrayList<>();
    }

    public GraphDataset(int size){
        this();
        data = new ArrayList<>(size);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Double getLineTension() {
        return lineTension;
    }

    public void setLineTension(Double lineTension) {
        this.lineTension = lineTension;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getBorderCapStyle() {
        return borderCapStyle;
    }

    public void setBorderCapStyle(String borderCapStyle) {
        this.borderCapStyle = borderCapStyle;
    }

    public List<String> getBorderDash() {
        return borderDash;
    }

    public void setBorderDash(List<String> borderDash) {
        this.borderDash = borderDash;
    }

    public Double getBorderDashOffset() {
        return borderDashOffset;
    }

    public void setBorderDashOffset(Double borderDashOffset) {
        this.borderDashOffset = borderDashOffset;
    }

    public String getBorderJoinStyle() {
        return borderJoinStyle;
    }

    public void setBorderJoinStyle(String borderJoinStyle) {
        this.borderJoinStyle = borderJoinStyle;
    }

    public String getPointBorderColor() {
        return pointBorderColor;
    }

    public void setPointBorderColor(String pointBorderColor) {
        this.pointBorderColor = pointBorderColor;
    }

    public String getPointBackgroundColor() {
        return pointBackgroundColor;
    }

    public void setPointBackgroundColor(String pointBackgroundColor) {
        this.pointBackgroundColor = pointBackgroundColor;
    }

    public int getPointBorderWidth() {
        return pointBorderWidth;
    }

    public void setPointBorderWidth(int pointBorderWidth) {
        this.pointBorderWidth = pointBorderWidth;
    }

    public int getPointHoverRadius() {
        return pointHoverRadius;
    }

    public void setPointHoverRadius(int pointHoverRadius) {
        this.pointHoverRadius = pointHoverRadius;
    }

    public String getPointHoverBackgroundColor() {
        return pointHoverBackgroundColor;
    }

    public void setPointHoverBackgroundColor(String pointHoverBackgroundColor) {
        this.pointHoverBackgroundColor = pointHoverBackgroundColor;
    }

    public String getPointHoverBorderColor() {
        return pointHoverBorderColor;
    }

    public void setPointHoverBorderColor(String pointHoverBorderColor) {
        this.pointHoverBorderColor = pointHoverBorderColor;
    }

    public int getPointHoverBorderWidth() {
        return pointHoverBorderWidth;
    }

    public void setPointHoverBorderWidth(int pointHoverBorderWidth) {
        this.pointHoverBorderWidth = pointHoverBorderWidth;
    }

    public int getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(int pointRadius) {
        this.pointRadius = pointRadius;
    }

    public int getPointHitRadius() {
        return pointHitRadius;
    }

    public void setPointHitRadius(int pointHitRadius) {
        this.pointHitRadius = pointHitRadius;
    }

    public List<BigDecimal> getData() {
        return data;
    }

    public void setData(List<BigDecimal> data) {
        this.data = data;
    }
}
