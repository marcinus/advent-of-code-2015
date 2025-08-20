def sampleInputRules = """H => HO
H => OH
O => HH
"""

def sampleInputA = "HOH"
def sampleInputB = "HOHOHO"

static Long solve(List<String> compactProblem) {
    def rules = compactProblem.takeWhile { it != "" }
    def source = compactProblem.last()
    solve(rules, source)
}

static Long solve(List<String> rules, String source) {
    def rulesParsed = rules.collect {
        def entry = it.split(" => ")
        String before = entry[0]
        String after = entry[1]
        [before, after]
    }

    def distinct = [] as Set<String>

    rulesParsed.each { rule ->
        def (k, v) = rule
        def matcher = (~/$k/).matcher(source)
        while (matcher.find()) {
            def start = matcher.start()
            def end = matcher.end()
            String substituted = source.substring(0, start) + v + source.substring(end)
            distinct.add(substituted)
        }
    }
    distinct.size()
}


assert 4L == solve(sampleInputRules.split("\n") as List, sampleInputA)
assert 7L == solve(sampleInputRules.split("\n") as List, sampleInputB)
println solve(new File('input/19.txt').readLines())