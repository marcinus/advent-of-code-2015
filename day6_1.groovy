static void turnOn(boolean[][] grid, List coordinates) {
    def (n, m, nn, mm) = coordinates
    (n..nn).each { i ->
        (m..mm).each { j ->
            grid[i][j] = true
        }
    }
}

static void turnOff(boolean[][] grid, List coordinates) {
    def (n, m, nn, mm) = coordinates
    (n..nn).each { i ->
        (m..mm).each { j ->
            grid[i][j] = false
        }
    }
}

static void toggle(boolean[][] grid, List coordinates) {
    def (n, m, nn, mm) = coordinates
    (n..nn).each { i ->
        (m..mm).each { j ->
            grid[i][j] = !grid[i][j]
        }
    }
}

static void apply(boolean[][] grid, String line) {
    def PATTERN = ~/^(turn on|toggle|turn off) (\d+),(\d+) through (\d+),(\d+)$/
    def match = line =~ PATTERN
    def operation = match[0][1]
    def coordinates = match[0].drop(2).collect { it as int }
    println "$operation, $coordinates"
    switch (operation) {
        case 'turn on' -> turnOn(grid, coordinates)
        case 'turn off' -> turnOff(grid, coordinates)
        case 'toggle' -> toggle(grid, coordinates)
        default -> throw new IllegalStateException("Invalid operation $operation in line $line")
    }
}

static long solve(String input) {
    boolean[][] grid = new boolean[1000][1000]
    input.split('\n').each {
        apply(grid, it)
    }
    (0..999).sum {i ->
        (0..999).count { j ->
            grid[i][j]
        }
    } as long
}

def sampleInput = '''turn on 0,0 through 999,999
toggle 0,0 through 999,0
turn off 499,499 through 500,500'''

assert (1_000_000L - 1000L - 4L) == solve(sampleInput)

println solve(new File('input/6.txt').text)