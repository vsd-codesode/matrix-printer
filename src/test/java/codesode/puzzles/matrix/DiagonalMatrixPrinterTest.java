/*
 * Copyright 2019 @ www.codesode.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package codesode.puzzles.matrix;

import exec.ResourceLoader;
import exec.SampleData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Vijay Shanker Dubey
 */
public class DiagonalMatrixPrinterTest {

    private DiagonalMatrixPrinter matrixPrinter;

    @BeforeEach
    public void beforeAll() {
        matrixPrinter = new DiagonalMatrixPrinter();
    }

    @Test
    public void testPrinting() throws IOException, URISyntaxException {

        List<Path> paths = new ResourceLoader("dmp-test").loadTestDirectory();

        paths.forEach(this::deflateArray);

        Class<DiagonalMatrixPrinter> clazz = DiagonalMatrixPrinter.class;

        URL testDataPath = clazz.getClassLoader().getResource("dmp-test");

        Path path = Paths.get(Objects.requireNonNull(testDataPath).toURI());
        if (Files.isDirectory(path)) {
            Files.list(path).forEach(folder -> {
            });
        }
    }

    private void deflateArray(Path folder) {

        SampleData<int[][], String> sampleData = new SampleData<>(folder);

        Function<Path, int[][]> argumentPreparatoryFunction = inputFile -> {
            int[][] matrix = null;
            try {
                List<String> lines = Files.readAllLines(inputFile);
                int matrixSize = lines.size();
                matrix = new int[matrixSize][matrixSize];

                for (int i = 0; i < lines.size(); i++) {

                    String line = lines.get(i);
                    String[] parts = line.split(" ");

                    for (int j = 0; j < parts.length; j++) {
                        String part = parts[j];
                        matrix[i][j] = Integer.parseInt(part);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return matrix;
        };

        Function<Path, String> resultPreparatoryFunction = outputFile -> {
            String expectedResult = null;
            try {
                String resultLine = Files.readAllLines(outputFile).get(0);

                expectedResult = Arrays.toString(resultLine.split(" "));

            } catch (IOException e) {
                e.printStackTrace();
            }

            return expectedResult;
        };

        int[][] matrix = sampleData.prepareArgument(argumentPreparatoryFunction);

        int[] flatArray = matrixPrinter.print(matrix);
        String resultArray = Arrays.toString(flatArray);

        String expectedResult = sampleData.prepareResult(resultPreparatoryFunction);

        sampleData.validate(expectedResult, resultArray);

        System.out.println(expectedResult);
        System.out.println(resultArray);
    }
}