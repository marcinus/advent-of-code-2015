static long eval(Map<String, Map<String, Long>> d, List<String> cycle) {
    def prev = cycle.first()
    cycle.drop(1).sum {
        def segment = d[prev][it]
        prev = it
        segment
    } as long
}

static long shortestHamiltonCycle(Map<String, Map<String, Long>> d, List<String> current) {
    if (current.size() == d.size()) {
            return eval(d, current)
    }
    def visited = current as Set
    d[current.last()].keySet().collect {i ->
        if(!visited.contains(i)) {
            return shortestHamiltonCycle(d, current + i)
        }
        Long.MAX_VALUE
    }.min() as long ?: Long.MAX_VALUE as long
}

static long solve(String input) {
    Map<String, Map<String, Long>> d = [:].withDefault { [:].withDefault { Long.MAX_VALUE } }
    (input.split('\n') as List).each {
        def (ij, delta) = it.split(' = ') as List
        def (i, j) = ij.split(' to ') as List
        d[i][j] = delta as long
        d[j][i] = delta as long
    }
    d.keySet().collect {
        shortestHamiltonCycle(d, [it])
    }.min() as long
}

def sampleInput = '''London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141'''

assert 605L == solve(sampleInput)
println solve(new File('input/9.txt').text)