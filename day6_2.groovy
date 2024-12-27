static void turnOn(long[][] grid, List coordinates) {
    def (n, m, nn, mm) = coordinates
    (n..nn).each { i ->
        (m..mm).each { j ->
            grid[i][j]++
        }
    }
}

static void turnOff(long[][] grid, List coordinates) {
    def (n, m, nn, mm) = coordinates
    (n..nn).each { i ->
        (m..mm).each { j ->
            if(grid[i][j] > 0) {
                grid[i][j]--
            }
        }
    }
}

static void toggle(long[][] grid, List coordinates) {
    def (n, m, nn, mm) = coordinates
    (n..nn).each { i ->
        (m..mm).each { j ->
            grid[i][j] += 2
        }
    }
}

static void apply(long[][] grid, String line) {
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
    long[][] grid = new long[1000][1000]
    input.split('\n').each {
        apply(grid, it)
    }
    (0..999).sum {i ->
        (0..999).sum { j ->
            grid[i][j]
        } as long
    } as long
}

def sampleInput = '''turn on 0,0 through 0,0
toggle 0,0 through 999,999'''

assert 2_000_001L == solve(sampleInput)

println solve(new File('input/6.txt').text)