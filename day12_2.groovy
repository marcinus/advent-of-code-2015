
import groovy.json.JsonSlurper

static def solve(String json) {
    def data = new JsonSlurper().parseText(json)
    sumOf(data)
}

static def sumOf(data) {
    def stack = [data]
    def total = 0

    while (!stack.empty) {
        def current = stack.pop()

        if (current instanceof List) {
            stack.addAll(current)
        } else if (current instanceof Map) {
            if (!current.values().contains("red")) {
                stack.addAll(current.values())
            }
        } else if (current instanceof Number) {
            total += current
        }
    }

    return total
}

assert 6 == solve(/[1,2,3]/)
assert 4 == solve(/[1,{"c":"red","b":2},3]/)

assert 0 == solve(/{"d":"red","e":[1,2,3,4],"f":5}/)
assert 6 == solve(/[1,"red",5]/)

println solve(new File('input/12.txt').text)