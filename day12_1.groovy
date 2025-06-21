static def solve(json) {
    def numbers = (json =~ /-?\d+/).collect { it as int }
    numbers ? numbers.sum() : 0
}

assert 6 == solve(/[1,2,3]/)
assert 6 == solve(/{"a":2,"b":4}/)

assert 3 == solve(/[[[3]]]/)
assert 3 == solve(/{"a":{"b":4},"c":-1}/)

assert 0 == solve(/{"a":[-1,1]}/)
assert 0 == solve(/[-1,{"a":1}]/)

assert 0 == solve(/[]/)
assert 0 == solve(/{}/)

println solve(new File('input/12.txt').text)