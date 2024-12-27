static long solve(String input, int iterations = 40) {
    iterations.times {
        def sb = new StringBuffer()
        int counter = 1
        for (int i = 1; i <= input.size(); i++) {
            while(i < input.size() && input[i] == input[i-1]) {
                i++
                counter++
            }
            sb.append(counter)
            sb.append(input[i-1])
            counter = 1
        }
        input = sb.toString()
    }
    input.size()
}

assert 6L == solve('1', 5)
println solve('3113322113', 50)