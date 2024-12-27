static long solve(String input) {
    def lines = input.split('\n') as List
    lines.sum { line ->
        def d = line.split('x').collect { it as long }.sort()
        assert 3 == d.size()
        2*d[0]*d[1] + 2*d[1]*d[2] + 2*d[2]*d[0] + d[0]*d[1]
    } as long
}

assert 58L == solve('2x3x4')
assert 43L == solve('1x1x10')
println solve(new File('input/2.txt').text)