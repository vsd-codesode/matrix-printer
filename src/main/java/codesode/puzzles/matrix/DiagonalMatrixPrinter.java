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


/**
 * @author Vijay Shanker Dubey
 */
public class DiagonalMatrixPrinter {

    public int[] print(int[][] matrix) {

        int[] flatArray = new int[matrix.length * matrix.length];

        boolean moreItems = true;

        int i = 0;
        int row = 0;
        int column;

        int rowPointer = 0;
        int columnPointer = 0;

        while (moreItems) {

            if (i == flatArray.length) {
                //all items are flatten now
                moreItems = false;
            }

            if (matrix.length > row) {
                rowPointer = row;
                //restart at zero index.
                column = 0;
            } else {
                column = columnPointer + 1;
                columnPointer = column;
            }


            for (int j = rowPointer; j >= 0 && column < matrix.length; j--) {
                flatArray[i] = matrix[j][column];
                i = i + 1;

                row = row - 1;
                column = column + 1;
            }

            row = rowPointer + 1;
        }

        return flatArray;

    }
}
