package com.algorithm.visualizer.controller;

import com.algorithm.visualizer.model.AlgorithmResult;
import com.algorithm.visualizer.service.SearchingService;
import com.algorithm.visualizer.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class AlgorithmController {

    @Autowired
    private SearchingService searchingService;

    @Autowired
    private SortingService sortingService;

    @PostMapping("/search/linear")
    public AlgorithmResult linearSearch(@RequestBody SearchRequest request) {
        return searchingService.linearSearch(request.getArray(), request.getTarget());
    }

    @PostMapping("/search/binary")
    public AlgorithmResult binarySearch(@RequestBody SearchRequest request) {
        return searchingService.binarySearch(request.getArray(), request.getTarget());
    }

    @PostMapping("/sort/bubble")
    public AlgorithmResult bubbleSort(@RequestBody SortRequest request) {
        return sortingService.bubbleSort(request.getArray());
    }

    @PostMapping("/sort/selection")
    public AlgorithmResult selectionSort(@RequestBody SortRequest request) {
        return sortingService.selectionSort(request.getArray());
    }

    @PostMapping("/sort/insertion")
    public AlgorithmResult insertionSort(@RequestBody SortRequest request) {
        return sortingService.insertionSort(request.getArray());
    }

    @PostMapping("/sort/merge")
    public AlgorithmResult mergeSort(@RequestBody SortRequest request) {
        return sortingService.mergeSort(request.getArray());
    }

    @PostMapping("/sort/quick")
    public AlgorithmResult quickSort(@RequestBody SortRequest request) {
        return sortingService.quickSort(request.getArray());
    }

    @PostMapping("/uplaod/csv")
    public int[] uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = reader.readLine();
            reader.close();
            
            if (line != null) {
                String[] values = line.split(",");
                return Arrays.stream(values)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error parsing CSV file: " + e.getMessage());
        }
        return new int[0];
    }

    public static class SearchRequest {
        private int[] array;
        private int target;

        public int[] getArray() {
            return array;
        }

        public void setArray(int[] array) {
            this.array = array;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }
    }

    public static class SortRequest {
        private int[] array;

        public int[] getArray() {
            return array;
        }

        public void setArray(int[] array) {
            this.array = array;
        }
    }
}
