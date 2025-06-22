def sampleInput = """Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
"""

PATTERN = ~/^\S+ can fly (\d+) km\/s for (\d+) seconds, but then must rest for (\d+) seconds.$/

def parse(List<String> lines) {
    def reindeer = []
    lines.each { line ->
        if (line ==~ PATTERN) {
            def matcher = (line =~ PATTERN)[0]
            reindeer << [
                    velocity: matcher[1] as long,
                    timeFly: matcher[2] as long,
                    timeRest: matcher[3] as long
            ]
        } else {
            throw new RuntimeException("Invalid regex match in ${line}")
        }
    }
    reindeer
}

long solve(List<String> lines, long time = 1000) {
    def reindeer = parse(lines)
    reindeer.collect { r ->
        long totalTime = r.timeFly + r.timeRest
        long fullCycles = (time / totalTime) as long
        long partial = time % totalTime
        if (partial > r.timeFly) {
            partial = r.timeFly
        }
        (fullCycles * r.timeFly + partial) * r.velocity
    }.max()
}


assert 1120L == solve(sampleInput.split('\n') as List<String>)

println solve(new File('input/14.txt').readLines(), 2503)