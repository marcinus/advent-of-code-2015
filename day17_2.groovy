def sampleInput = """20
15
10
5
5
"""

static List<Integer> parse(List<String> lines) {
    lines.collect { it as int } as List
}

static void countSolutions(List containers, List fulfillment, int volume, Map<Integer, Integer> counter) {
    int currentFulfillment = (fulfillment.withIndex().sum { item, index ->
        item * containers[index]
    } ?: 0) as int
    int currentContainersFilled = fulfillment.count { it == 1 }
    if (fulfillment.size() == containers.size()) {
        if(currentFulfillment == volume) {
            counter[currentContainersFilled]++
        }
    } else if(fulfillment.size() < containers.size() && volume >= currentFulfillment) {
        (0..1).each {
            countSolutions(containers, fulfillment + [it], volume, counter)
        }.sum() as long
    }
}

static long countSolutions(List containers, List fulfillment, int volume) {
    def result = [:].withDefault { 0 }
    countSolutions(containers, fulfillment, volume, result)
    println result
    result.min {it.key }.value
}

static long solve(List<String> input, volume = 150) {
    def containers = parse(input)
    countSolutions(containers, [], volume)
}

assert 3L == solve(sampleInput.split('\n') as List, 25)
println solve(new File('input/17.txt').readLines())