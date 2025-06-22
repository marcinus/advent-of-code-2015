

def sampleInput = """Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 79 happiness units by sitting next to Carol.
Alice would lose 2 happiness units by sitting next to David.
Bob would gain 83 happiness units by sitting next to Alice.
Bob would lose 7 happiness units by sitting next to Carol.
Bob would lose 63 happiness units by sitting next to David.
Carol would lose 62 happiness units by sitting next to Alice.
Carol would gain 60 happiness units by sitting next to Bob.
Carol would gain 55 happiness units by sitting next to David.
David would gain 46 happiness units by sitting next to Alice.
David would lose 7 happiness units by sitting next to Bob.
David would gain 41 happiness units by sitting next to Carol.
"""

GAIN_PATTERN = ~/^(\S+) would gain (\d+) happiness units by sitting next to (\S+).$/
LOSE_PATTERN = ~/^(\S+) would lose (\d+) happiness units by sitting next to (\S+).$/

def parse(List<String> lines) {
    def graph = [:].withDefault { [:].withDefault { 0 } }
    lines.each { line ->
        if (line ==~ GAIN_PATTERN) {
            def matcher = (line =~ GAIN_PATTERN)[0]
            String from = matcher[1]
            long volume = matcher[2] as long
            String to = matcher[3]
            graph[from][to] = volume
        } else if (line ==~ LOSE_PATTERN) {
            def matcher = (line =~ LOSE_PATTERN)[0]
            String from = matcher[1]
            long volume = matcher[2] as long
            String to = matcher[3]
            graph[from][to] = -volume
        } else {
            throw new RuntimeException("Neither gain nor lose in ${line}")
        }
    }
    graph
}

def unify(graph) {
    def result = [:].withDefault { [:].withDefault { 0 } }
    graph.each { from, edges ->
        edges.each { to, weight ->
            result[from][to] = weight + graph[to][from]
            result[to][from] = weight + graph[to][from]
        }
    }
    result
}

long solve(List<String> lines) {
    def partialGraph = parse(lines)
    partialGraph['Marcin'] = [:].withDefault { 0 }
    partialGraph.each { person, _ ->
        if (person != 'Marcin') {
            partialGraph[person]['Marcin'] = 0
            partialGraph['Marcin'][person] = 0
        }
    }
    def graph = unify(partialGraph)
    def people = graph.keySet() as List
    def arrangements = people.permutations()
    arrangements.collect { arrangement ->
        def total = 0
        arrangement.eachWithIndex { person, i ->
            def nextPerson = arrangement[(i + 1) % arrangement.size()]
            total += graph[person][nextPerson]
        }
        total
    }.max() as long
}

println solve(new File('input/13.txt').readLines())
