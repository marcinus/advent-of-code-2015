static long solve(String input) {
    long i = 0
    while(!((input + i).md5().startsWith('00000'))) i++
    i
}

assert 609043L == solve('abcdef')
assert 1048970L == solve('pqrstuv')
println solve('ckczppom')