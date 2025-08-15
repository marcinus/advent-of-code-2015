def sampleInput = """.#.#.#
...##.
#....#
..#...
#.#..#
####..
"""

static int[][] parse(List<String> input) {
    def result = new int[input.size()][input[0].length()]
    input.eachWithIndex { String line, int i ->
        line.eachWithIndex { String ch, int j ->
            result[i][j] = ch == '#' ? 1 : 0
        }
    }
    result
}

static long solve(List<String> input, int steps = 100) {
    def directions = [
            [-1, -1],
            [-1, 0],
            [-1, 1],
            [0, -1],
            [0, 1],
            [1, -1],
            [1, 0],
            [1, 1]
    ]


    def grid = parse(input)

    int n = grid.length
    int m = grid[0].length

    def newGrid = new int[n][m]

    steps.times {
        n.times { i ->
            m.times { j ->
                def neighbours = directions.count { dir ->
                    def newI = i + dir[0]
                    def newJ = j + dir[1]
                    return newI >= 0 && newI < n && newJ >= 0 && newJ < m && grid[newI][newJ] > 0
                }

                if(grid[i][j] > 0) {
                    newGrid[i][j] = (neighbours == 2 || neighbours == 3) ? 1 : 0
                } else {
                    newGrid[i][j] = (neighbours == 3) ? 1 : 0
                }
            }
        }
        def temp = newGrid
        newGrid = grid
        grid = temp
    }

    return grid.sum { it.sum() }
}

assert 4L == solve(sampleInput.split('\n') as List, 4)
println solve(new File('input/18.txt').readLines())