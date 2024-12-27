static long solve(String input) {
    def pos = [0, 0]
    def visited = [pos] as Set
    input.each {
        pos = switch(it) {
            case '^' -> [pos[0]+1, pos[1]]
            case 'v' -> [pos[0]-1, pos[1]]
            case '<' -> [pos[0], pos[1]-1]
            case '>' -> [pos[0], pos[1]+1]
            default -> throw new IllegalStateException('Invalid operation! ' + it)
        }
        visited << pos
    }
    visited.size()
}

assert 2L == solve('>')
assert 4L == solve('^>v<')
assert 2L == solve('^v^v^v^v^v')
println solve(new File('input/3.txt').text)