package ru.klemtsov.watering.restserver.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GraphData {
    private List<String> labels;
    private List<GraphDataset> datasets;

    public GraphData() {
        labels = new ArrayList<>();
        datasets = new ArrayList<>();
    }
    public GraphData(int size) {
        this();
        labels = new ArrayList<>(size);
        datasets = new ArrayList<>();
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<GraphDataset> getDatasets() {
        return datasets;
    }
}
