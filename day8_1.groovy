static long solve(String input) {
    (input.split('\n') as List).sum {
        def pureSlashes = it.findAll(~/(?<!\\)(?:\\{2})+/).sum { it.size() / 2 } ?: 0
        def quotes = it.findAll(~/(?<!\\)(\\{2})*\\"/)
        def encoded = it.findAll(~/(?<!\\)(\\{2})*\\x[0-9a-f]{2}/)
        pureSlashes + quotes.size() + encoded.size()*3 + 2
    } as long
}

def sampleInput = /""
"abc"
"aaa\"aaa"
"\x27"/

assert 12L == solve(sampleInput)
println solve(new File('input/8.txt').text)