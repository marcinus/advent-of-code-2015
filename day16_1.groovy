def targetSue = """children: 3
cats: 7
samoyeds: 2
pomeranians: 3
akitas: 0
vizslas: 0
goldfish: 5
trees: 3
cars: 2
perfumes: 1"""

PATTERN = ~/^Sue \d+: (.*)$/

def parse(List<String> lines) {
    def sues = []
    lines.each { line ->
        if (line ==~ PATTERN) {
            String definition = (line =~ PATTERN)[0][1]
            sues += (definition.split(', ') as List).collectEntries { entry ->
                def element = entry.split(': ') as List
                [(element[0]): element[1] as int]
            }
        } else {
            throw new RuntimeException("Invalid regex match in ${line}")
        }
    }
    sues
}

def parseTarget(String lines) {
    (lines.split('\n') as List).collectEntries { line ->
        def element = line.split(': ')
        [(element[0]): (element[1] as int)]
    }
}

long solve(input, targetSue) {
    def target = parseTarget(targetSue)
    def sues = parse(input)

    sues.findIndexOf { sue ->
        sue.every { property, value ->
            target[property] == value
        }
    } + 1
}

println solve(new File('input/16.txt').readLines(), targetSue)