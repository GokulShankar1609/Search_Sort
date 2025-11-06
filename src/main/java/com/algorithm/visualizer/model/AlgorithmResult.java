package com.algorithm.visualizer.model;

import java.util.List;

public class AlgorithmResult {
    private List<AlgorithmStep> steps;
    private String algorithmName;
    private long executionTimeMs;
    private String timeComplexity;
    private String spaceComplexity;
    private String bestCase;
    private String averageCase;
    private String worstCase;
    private String description;
    private String recommendation;
    private Integer foundIndex;
    private boolean found;
    
    public AlgorithmResult() {}

    public List<AlgorithmStep> getSteps() {
        return steps;
    }

    public void setSteps(List<AlgorithmStep> steps) {
        this.steps = steps;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }

    public String getTimeComplexity() {
        return timeComplexity;
    }

    public void setTimeComplexity(String timeComplexity) {
        this.timeComplexity = timeComplexity;
    }

    public String getSpaceComplexity() {
        return spaceComplexity;
    }

    public void setSpaceComplexity(String spaceComplexity) {
        this.spaceComplexity = spaceComplexity;
    }

    public String getBestCase() {
        return bestCase;
    }

    public void setBestCase(String bestCase) {
        this.bestCase = bestCase;
    }

    public String getAverageCase() {
        return averageCase;
    }

    public void setAverageCase(String averageCase) {
        this.averageCase = averageCase;
    }

    public String getWorstCase() {
        return worstCase;
    }

    public void setWorstCase(String worstCase) {
        this.worstCase = worstCase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public Integer getFoundIndex() {
        return foundIndex;
    }

    public void setFoundIndex(Integer foundIndex) {
        this.foundIndex = foundIndex;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
