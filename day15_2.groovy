def sampleInput = """Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
"""

PATTERN = ~/^\S+: capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)$/

def parse(List<String> lines) {
    def ingrediens = []
    lines.each { line ->
        if (line ==~ PATTERN) {
            def matcher = (line =~ PATTERN)[0]
            ingrediens << [
                    matcher[1] as long,
                    matcher[2] as long,
                    matcher[3] as long,
                    matcher[4] as long,
                    matcher[5] as long
            ]
        } else {
            throw new RuntimeException("Invalid regex match in ${line}")
        }
    }
    ingrediens
}

long calculateCombination(ingredients, List vector) {
    assert (vector.sum() ?: 0) == 100
    def rows = ingredients.size()
    def cols = ingredients[0].size()
    def transposed = (0..<cols).collect { col ->
        (0..<rows).collect { row ->
            ingredients[row][col]
        }
    }

    def multipliers = (0..<cols).collect { i ->
        long sum = 0
        transposed[i].eachWithIndex { long value, int j ->
            sum += value * vector[j]
        }
        return sum >= 0 ? sum : 0
    } // So we get the set of numbers
    // Assert that calories is 500
    if(multipliers[-1] != 500) {
        return 0
    }
    long score = multipliers.dropRight(1).inject(1) { total, value ->
        total * value
    } as long
    println "Evaluated $vector with score $score"
    score
}

long bruteForce(ingredients, List soFar) {
    if (soFar.size() == ingredients.size()) {
        return calculateCombination(ingredients, soFar)
    } else if (soFar.size() == ingredients.size() - 1) {
        return bruteForce(ingredients, soFar + [100 - (soFar.sum() ?: 0)])
    } else {
        int remaining = 100 - (soFar.sum() ?: 0 as int)
        long best = 0
        for (int i = 0; i <= remaining; i++) {
            best = Math.max(best, bruteForce(ingredients, soFar + [i]))
        }
        return best
    }
}

long solve(input) {
    bruteForce(parse(input), [])
}

assert 57600000L == solve(sampleInput.split('\n') as List<String>)

println solve(new File('input/15.txt').readLines())