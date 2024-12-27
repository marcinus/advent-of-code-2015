static long solve(String input) {
    long i = 0
    while(!((input + i).md5().startsWith('000000'))) i++
    i
}

println solve('ckczppom')