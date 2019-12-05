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

package exec;

import codesode.puzzles.matrix.DiagonalMatrixPrinter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Vijay Shanker Dubey
 */
public class ResourceLoader {

    private String locationInClassPath;

    public ResourceLoader(String locationInClassPath) {
        this.locationInClassPath = locationInClassPath;
    }

    public List<Path> loadTestDirectory() throws URISyntaxException, IOException {

        Class<DiagonalMatrixPrinter> clazz = DiagonalMatrixPrinter.class;

        URL testDataPath = clazz.getClassLoader().getResource(locationInClassPath);

        Path path = Paths.get(Objects.requireNonNull(testDataPath).toURI());

        return Files.isDirectory(path) ? Files.list(path).collect(Collectors.toList()) : Collections.emptyList();
    }

}
