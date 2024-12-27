static long solve(String input) {
    (input.split('\n') as List).sum {
        it.count('\\') + it.count('"') + 2
    } as long
}

def sampleInput = /""
"abc"
"aaa\"aaa"
"\x27"/

assert 19L == solve(sampleInput)
println solve(new File('input/8.txt').text)