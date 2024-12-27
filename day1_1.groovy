static long solve(String input) {
    input.count('(') - input.count(')')
}

assert 3L == solve('(((')
println solve(new File('input/1.txt').text)