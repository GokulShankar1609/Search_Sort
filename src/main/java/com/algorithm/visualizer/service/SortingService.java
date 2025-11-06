package com.algorithm.visualizer.service;

import com.algorithm.visualizer.model.AlgorithmResult;
import com.algorithm.visualizer.model.AlgorithmStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SortingService {

    public AlgorithmResult bubbleSort(int[] inputArray) {
        long startTime = System.currentTimeMillis();
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] array = inputArray.clone();
        int iteration = 0;
        int n = array.length;

        steps.add(createStep(iteration++, array, "Starting Bubble Sort", null, null, new ArrayList<>()));

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                steps.add(createStep(iteration++, array, 
                    "Comparing elements at indices " + j + " and " + (j + 1) + " (values: " + array[j] + " and " + array[j + 1] + ")",
                    null, Arrays.asList(j, j + 1), getSortedIndices(array.length, n - i)));

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;

                    steps.add(createStep(iteration++, array, 
                        "Swapped elements at indices " + j + " and " + (j + 1),
                        Arrays.asList(j, j + 1), null, getSortedIndices(array.length, n - i)));
                }
            }
            if (!swapped) break;
        }

        steps.add(createStep(iteration++, array, "Array sorted successfully!", 
            null, null, getSortedIndices(array.length, 0)));

        long endTime = System.currentTimeMillis();

        AlgorithmResult result = new AlgorithmResult();
        result.setSteps(steps);
        result.setAlgorithmName("Bubble Sort");
        result.setExecutionTimeMs(endTime - startTime);
        result.setTimeComplexity("O(n²)");
        result.setSpaceComplexity("O(1)");
        result.setBestCase("O(n) - when array is already sorted");
        result.setAverageCase("O(n²) - typical case");
        result.setWorstCase("O(n²) - when array is reverse sorted");
        result.setDescription("Bubble sort repeatedly compares adjacent elements and swaps them if they're in wrong order.");
        result.setRecommendation(getRecommendation(array.length, "Bubble Sort"));
        
        return result;
    }

    public AlgorithmResult selectionSort(int[] inputArray) {
        long startTime = System.currentTimeMillis();
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] array = inputArray.clone();
        int iteration = 0;
        int n = array.length;

        steps.add(createStep(iteration++, array, "Starting Selection Sort", null, null, new ArrayList<>()));

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            
            steps.add(createStep(iteration++, array, 
                "Finding minimum element in unsorted portion starting at index " + i,
                Arrays.asList(i), null, getSortedIndices(array.length, i)));

            for (int j = i + 1; j < n; j++) {
                steps.add(createStep(iteration++, array, 
                    "Comparing elements at indices " + minIdx + " and " + j + " (values: " + array[minIdx] + " and " + array[j] + ")",
                    Arrays.asList(minIdx, j), null, getSortedIndices(array.length, i)));

                if (array[j] < array[minIdx]) {
                    minIdx = j;
                    steps.add(createStep(iteration++, array, 
                        "New minimum found at index " + minIdx + " (value: " + array[minIdx] + ")",
                        Arrays.asList(minIdx), null, getSortedIndices(array.length, i)));
                }
            }

            if (minIdx != i) {
                int temp = array[i];
                array[i] = array[minIdx];
                array[minIdx] = temp;

                steps.add(createStep(iteration++, array, 
                    "Swapped elements at indices " + i + " and " + minIdx,
                    Arrays.asList(i, minIdx), null, getSortedIndices(array.length, i + 1)));
            }
        }

        steps.add(createStep(iteration++, array, "Array sorted successfully!", 
            null, null, getSortedIndices(array.length, 0)));

        long endTime = System.currentTimeMillis();

        AlgorithmResult result = new AlgorithmResult();
        result.setSteps(steps);
        result.setAlgorithmName("Selection Sort");
        result.setExecutionTimeMs(endTime - startTime);
        result.setTimeComplexity("O(n²)");
        result.setSpaceComplexity("O(1)");
        result.setBestCase("O(n²) - always makes n² comparisons");
        result.setAverageCase("O(n²) - typical case");
        result.setWorstCase("O(n²) - all cases similar");
        result.setDescription("Selection sort finds the minimum element and places it at the beginning in each iteration.");
        result.setRecommendation(getRecommendation(array.length, "Selection Sort"));
        
        return result;
    }

    public AlgorithmResult insertionSort(int[] inputArray) {
        long startTime = System.currentTimeMillis();
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] array = inputArray.clone();
        int iteration = 0;
        int n = array.length;

        steps.add(createStep(iteration++, array, "Starting Insertion Sort", null, null, getSortedIndices(array.length, 1)));

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            steps.add(createStep(iteration++, array, 
                "Inserting element at index " + i + " (value: " + key + ") into sorted portion",
                Arrays.asList(i), null, getSortedIndices(array.length, i)));

            while (j >= 0 && array[j] > key) {
                steps.add(createStep(iteration++, array, 
                    "Shifting element at index " + j + " (value: " + array[j] + ") one position right",
                    Arrays.asList(j, j + 1), null, getSortedIndices(array.length, i)));

                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
            steps.add(createStep(iteration++, array, 
                "Placed element " + key + " at index " + (j + 1),
                Arrays.asList(j + 1), null, getSortedIndices(array.length, i + 1)));
        }

        steps.add(createStep(iteration++, array, "Array sorted successfully!", 
            null, null, getSortedIndices(array.length, 0)));

        long endTime = System.currentTimeMillis();

        AlgorithmResult result = new AlgorithmResult();
        result.setSteps(steps);
        result.setAlgorithmName("Insertion Sort");
        result.setExecutionTimeMs(endTime - startTime);
        result.setTimeComplexity("O(n²)");
        result.setSpaceComplexity("O(1)");
        result.setBestCase("O(n) - when array is already sorted");
        result.setAverageCase("O(n²) - typical case");
        result.setWorstCase("O(n²) - when array is reverse sorted");
        result.setDescription("Insertion sort builds the sorted array one element at a time by inserting each element into its correct position.");
        result.setRecommendation(getRecommendation(array.length, "Insertion Sort"));
        
        return result;
    }

    public AlgorithmResult mergeSort(int[] inputArray) {
        long startTime = System.currentTimeMillis();
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] array = inputArray.clone();
        
        steps.add(createStep(0, array, "Starting Merge Sort", null, null, new ArrayList<>()));
        
        mergeSortHelper(array, 0, array.length - 1, steps);
        
        steps.add(createStep(steps.size(), array, "Array sorted successfully!", 
            null, null, getSortedIndices(array.length, 0)));

        long endTime = System.currentTimeMillis();

        AlgorithmResult result = new AlgorithmResult();
        result.setSteps(steps);
        result.setAlgorithmName("Merge Sort");
        result.setExecutionTimeMs(endTime - startTime);
        result.setTimeComplexity("O(n log n)");
        result.setSpaceComplexity("O(n)");
        result.setBestCase("O(n log n) - all cases similar");
        result.setAverageCase("O(n log n) - typical case");
        result.setWorstCase("O(n log n) - all cases similar");
        result.setDescription("Merge sort divides the array into halves, sorts them recursively, and merges the sorted halves.");
        result.setRecommendation(getRecommendation(array.length, "Merge Sort"));
        
        return result;
    }

    private void mergeSortHelper(int[] array, int left, int right, List<AlgorithmStep> steps) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            steps.add(createStep(steps.size(), array, 
                "Dividing array: left=" + left + ", mid=" + mid + ", right=" + right,
                null, getRange(left, right), new ArrayList<>()));

            mergeSortHelper(array, left, mid, steps);
            mergeSortHelper(array, mid + 1, right, steps);
            merge(array, left, mid, right, steps);
        }
    }

    private void merge(int[] array, int left, int mid, int right, List<AlgorithmStep> steps) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        steps.add(createStep(steps.size(), array, 
            "Merging subarrays: [" + left + ".." + mid + "] and [" + (mid + 1) + ".." + right + "]",
            null, getRange(left, right), new ArrayList<>()));

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            
            steps.add(createStep(steps.size(), array, 
                "Merged element " + array[k] + " at position " + k,
                Arrays.asList(k), getRange(left, right), new ArrayList<>()));
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            steps.add(createStep(steps.size(), array, 
                "Copied remaining element " + array[k] + " at position " + k,
                Arrays.asList(k), null, new ArrayList<>()));
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            steps.add(createStep(steps.size(), array, 
                "Copied remaining element " + array[k] + " at position " + k,
                Arrays.asList(k), null, new ArrayList<>()));
            j++;
            k++;
        }
    }

    public AlgorithmResult quickSort(int[] inputArray) {
        long startTime = System.currentTimeMillis();
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] array = inputArray.clone();
        
        steps.add(createStep(0, array, "Starting Quick Sort", null, null, new ArrayList<>()));
        
        quickSortHelper(array, 0, array.length - 1, steps);
        
        steps.add(createStep(steps.size(), array, "Array sorted successfully!", 
            null, null, getSortedIndices(array.length, 0)));

        long endTime = System.currentTimeMillis();

        AlgorithmResult result = new AlgorithmResult();
        result.setSteps(steps);
        result.setAlgorithmName("Quick Sort");
        result.setExecutionTimeMs(endTime - startTime);
        result.setTimeComplexity("O(n log n)");
        result.setSpaceComplexity("O(log n)");
        result.setBestCase("O(n log n) - when pivot divides evenly");
        result.setAverageCase("O(n log n) - typical case");
        result.setWorstCase("O(n²) - when array is already sorted");
        result.setDescription("Quick sort picks a pivot element and partitions the array around it, then recursively sorts the partitions.");
        result.setRecommendation(getRecommendation(array.length, "Quick Sort"));
        
        return result;
    }

    private void quickSortHelper(int[] array, int low, int high, List<AlgorithmStep> steps) {
        if (low < high) {
            int pi = partition(array, low, high, steps);
            quickSortHelper(array, low, pi - 1, steps);
            quickSortHelper(array, pi + 1, high, steps);
        }
    }

    private int partition(int[] array, int low, int high, List<AlgorithmStep> steps) {
        int pivot = array[high];
        
        steps.add(createStep(steps.size(), array, 
            "Choosing pivot: " + pivot + " at index " + high,
            Arrays.asList(high), getRange(low, high), new ArrayList<>()));

        int i = low - 1;

        for (int j = low; j < high; j++) {
            steps.add(createStep(steps.size(), array, 
                "Comparing element " + array[j] + " at index " + j + " with pivot " + pivot,
                Arrays.asList(j, high), getRange(low, high), new ArrayList<>()));

            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                steps.add(createStep(steps.size(), array, 
                    "Swapped elements at indices " + i + " and " + j,
                    Arrays.asList(i, j), null, new ArrayList<>()));
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        steps.add(createStep(steps.size(), array, 
            "Placed pivot " + array[i + 1] + " at correct position " + (i + 1),
            Arrays.asList(i + 1), null, new ArrayList<>()));

        return i + 1;
    }

    private AlgorithmStep createStep(int iteration, int[] array, String action, 
                                     List<Integer> highlightIndices, 
                                     List<Integer> comparisonIndices,
                                     List<Integer> sortedIndices) {
        AlgorithmStep step = new AlgorithmStep();
        step.setIteration(iteration);
        step.setArray(Arrays.stream(array).boxed().toList());
        step.setAction(action);
        step.setHighlightIndices(highlightIndices);
        step.setComparisonIndices(comparisonIndices);
        step.setSortedIndices(sortedIndices);
        return step;
    }

    private List<Integer> getSortedIndices(int length, int startUnsorted) {
        List<Integer> sorted = new ArrayList<>();
        for (int i = startUnsorted; i < length; i++) {
            sorted.add(i);
        }
        return sorted;
    }

    private List<Integer> getRange(int start, int end) {
        List<Integer> range = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            range.add(i);
        }
        return range;
    }

    private String getRecommendation(int arraySize, String currentAlgorithm) {
        if (arraySize < 10) {
            return "For small arrays (< 10 elements), Insertion Sort is efficient and simple.";
        } else if (arraySize < 50) {
            return "For medium arrays (10-50 elements), Quick Sort or Merge Sort would be more efficient than " + currentAlgorithm + ".";
        } else {
            if (currentAlgorithm.equals("Bubble Sort") || currentAlgorithm.equals("Selection Sort") || currentAlgorithm.equals("Insertion Sort")) {
                return "For large arrays (" + arraySize + " elements), Quick Sort (O(n log n)) or Merge Sort (O(n log n)) would be significantly faster than " + currentAlgorithm + " (O(n²)).";
            } else {
                return "For large arrays (" + arraySize + " elements), " + currentAlgorithm + " is an excellent choice with O(n log n) complexity.";
            }
        }
    }
}
