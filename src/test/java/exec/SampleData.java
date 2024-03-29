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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;


/**
 * @param <T> Type of data
 * @param <R> Result type of data
 *
 * @author Vijay Shanker Dubey
 */
public class SampleData<T, R> {

    private Path folder;

    public SampleData(Path folder) {
        this.folder = folder;
    }

    public T prepareArgument(Function<Path, T> function) {
        return function.apply(Paths.get(folder.toString(), "input"));
    }

    public R prepareResult(Function<Path, R> function) {
        return function.apply(Paths.get(folder.toString(), "output"));
    }

    public boolean validate(R expectedResult, R result) {
        return false;
    }
}
