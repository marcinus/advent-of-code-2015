def sampleInput = """20
15
10
5
5
"""

static List<Integer> parse(List<String> lines) {
    lines.collect { it as int } as List
}

static long countSolutions(List containers, List fulfillment, int volume) {
    int currentFulfillment = (fulfillment
            .withIndex()
            .sum { item, index ->
        item * containers[index]
    } ?: 0) as int
    if (fulfillment.size() == containers.size()) {
        return currentFulfillment == volume ? 1 : 0
    } else if(currentFulfillment > volume) {
        return 0
    } else {
        return (0..1).collect {
            countSolutions(containers, fulfillment + [it], volume)
        }.sum() as long
    }
}

static long solve(List<String> input, volume = 150) {
    def containers = parse(input)
    countSolutions(containers, [], volume)
}

assert 4L == solve(sampleInput.split('\n') as List, 25)
println solve(new File('input/17.txt').readLines())