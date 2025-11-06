package com.algorithm.visualizer.service;

import com.algorithm.visualizer.model.AlgorithmResult;
import com.algorithm.visualizer.model.AlgorithmStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchingService {

    public AlgorithmResult linearSearch(int[] array, int target) {
        long startTime = System.currentTimeMillis();
        List<AlgorithmStep> steps = new ArrayList<>();
        int iteration = 0;
        boolean found = false;
        int foundIndex = -1;

        steps.add(createStep(iteration++, array, "Starting Linear Search for target: " + target, 
            null, null, null, null));

        for (int i = 0; i < array.length; i++) {
            String action = "Comparing element at index " + i + " (value: " + array[i] + ") with target " + target;
            
            if (array[i] == target) {
                action = "Found target " + target + " at index " + i + "!";
                found = true;
                foundIndex = i;
                steps.add(createStep(iteration++, array, action, 
                    Arrays.asList(i), null, null, i));
                break;
            } else {
                steps.add(createStep(iteration++, array, action, 
                    Arrays.asList(i), null, null, i));
            }
        }

        if (!found) {
            steps.add(createStep(iteration++, array, "Target " + target + " not found in array", 
                null, null, null, null));
        }

        long endTime = System.currentTimeMillis();

        AlgorithmResult result = new AlgorithmResult();
        result.setSteps(steps);
        result.setAlgorithmName("Linear Search");
        result.setExecutionTimeMs(endTime - startTime);
        result.setTimeComplexity("O(n)");
        result.setSpaceComplexity("O(1)");
        result.setBestCase("O(1) - when element is at first position");
        result.setAverageCase("O(n) - when element is in middle");
        result.setWorstCase("O(n) - when element is at last position or not present");
        result.setDescription("Linear search checks each element sequentially until the target is found or the array ends.");
        result.setFound(found);
        result.setFoundIndex(foundIndex);
        
        return result;
    }

    public AlgorithmResult binarySearch(int[] array, int target) {
        long startTime = System.currentTimeMillis();
        List<AlgorithmStep> steps = new ArrayList<>();
        int iteration = 0;
        boolean found = false;
        int foundIndex = -1;

        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);

        steps.add(createStep(iteration++, sortedArray, 
            "Starting Binary Search for target: " + target + " (array sorted first)", 
            null, null, null, null));

        int left = 0;
        int right = sortedArray.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            String action = "Checking middle element at index " + mid + " (value: " + sortedArray[mid] + ")";
            
            if (sortedArray[mid] == target) {
                action = "Found target " + target + " at index " + mid + "!";
                found = true;
                foundIndex = mid;
                steps.add(createStep(iteration++, sortedArray, action, 
                    Arrays.asList(mid), Arrays.asList(left, right), null, mid));
                break;
            } else if (sortedArray[mid] < target) {
                action += ". Target is greater, searching right half";
                steps.add(createStep(iteration++, sortedArray, action, 
                    Arrays.asList(mid), Arrays.asList(left, right), null, mid));
                left = mid + 1;
            } else {
                action += ". Target is smaller, searching left half";
                steps.add(createStep(iteration++, sortedArray, action, 
                    Arrays.asList(mid), Arrays.asList(left, right), null, mid));
                right = mid - 1;
            }
        }

        if (!found) {
            steps.add(createStep(iteration++, sortedArray, "Target " + target + " not found in array", 
                null, null, null, null));
        }

        long endTime = System.currentTimeMillis();

        AlgorithmResult result = new AlgorithmResult();
        result.setSteps(steps);
        result.setAlgorithmName("Binary Search");
        result.setExecutionTimeMs(endTime - startTime);
        result.setTimeComplexity("O(log n)");
        result.setSpaceComplexity("O(1)");
        result.setBestCase("O(1) - when element is at middle");
        result.setAverageCase("O(log n) - typical case");
        result.setWorstCase("O(log n) - when element is not present");
        result.setDescription("Binary search repeatedly divides the sorted array in half, comparing the target with the middle element.");
        result.setRecommendation("Binary Search is much faster than Linear Search for large sorted arrays (O(log n) vs O(n))");
        result.setFound(found);
        result.setFoundIndex(foundIndex);
        
        return result;
    }

    private AlgorithmStep createStep(int iteration, int[] array, String action, 
                                     List<Integer> highlightIndices, 
                                     List<Integer> comparisonIndices,
                                     List<Integer> sortedIndices,
                                     Integer currentIndex) {
        AlgorithmStep step = new AlgorithmStep();
        step.setIteration(iteration);
        step.setArray(Arrays.stream(array).boxed().toList());
        step.setAction(action);
        step.setHighlightIndices(highlightIndices);
        step.setComparisonIndices(comparisonIndices);
        step.setSortedIndices(sortedIndices);
        step.setCurrentIndex(currentIndex);
        return step;
    }
}
