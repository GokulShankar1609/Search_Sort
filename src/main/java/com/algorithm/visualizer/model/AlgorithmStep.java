package com.algorithm.visualizer.model;

import java.util.List;

public class AlgorithmStep {
    private int iteration;
    private List<Integer> array;
    private String action;
    private List<Integer> highlightIndices;
    private List<Integer> comparisonIndices;
    private List<Integer> sortedIndices;
    private Integer currentIndex;
    private Integer targetIndex;
    
    public AlgorithmStep() {}
    
    public AlgorithmStep(int iteration, List<Integer> array, String action) {
        this.iteration = iteration;
        this.array = array;
        this.action = action;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public List<Integer> getArray() {
        return array;
    }

    public void setArray(List<Integer> array) {
        this.array = array;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Integer> getHighlightIndices() {
        return highlightIndices;
    }

    public void setHighlightIndices(List<Integer> highlightIndices) {
        this.highlightIndices = highlightIndices;
    }

    public List<Integer> getComparisonIndices() {
        return comparisonIndices;
    }

    public void setComparisonIndices(List<Integer> comparisonIndices) {
        this.comparisonIndices = comparisonIndices;
    }

    public List<Integer> getSortedIndices() {
        return sortedIndices;
    }

    public void setSortedIndices(List<Integer> sortedIndices) {
        this.sortedIndices = sortedIndices;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Integer getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(Integer targetIndex) {
        this.targetIndex = targetIndex;
    }
}
