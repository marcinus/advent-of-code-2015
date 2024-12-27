static long solve(String input) {
    long level = 0
    for(int i = 0; i < input.size(); i++) {
        level += (input[i] == '(' ? 1 : -1)
        if(level < 0) {
            return i+1
        }
    }
    throw new IllegalStateException("Never gone to basement!")
}

assert 5L == solve('()())')
println solve(new File('input/1.txt').text)