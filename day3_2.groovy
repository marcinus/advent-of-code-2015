static long solve(String input) {
    def santaPos = [0, 0]
    def robotPos = santaPos
    def visited = [santaPos] as Set
    input.eachWithIndex { dir, i ->
        def pos = (i % 2 == 0 ? santaPos : robotPos)
        pos = switch(dir) {
            case '^' -> [pos[0]+1, pos[1]]
            case 'v' -> [pos[0]-1, pos[1]]
            case '<' -> [pos[0], pos[1]-1]
            case '>' -> [pos[0], pos[1]+1]
            default -> throw new IllegalStateException('Invalid operation! ' + dir)
        }
        visited << pos
        if (i % 2 == 0) {
            santaPos = pos
        } else {
            robotPos = pos
        }
    }
    visited.size()
}

assert 3L == solve('^v')
assert 3L == solve('^>v<')
assert 11L == solve('^v^v^v^v^v')
println solve(new File('input/3.txt').text)