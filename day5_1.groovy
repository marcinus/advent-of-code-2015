static boolean has3Vowels(String text) {
    'aeiou'.collect { text.count(it) }.sum() >= 3
}

static boolean hasAtLeastOneLetterTwiceInARow(String text) {
    for (int i = 1; i < text.size(); i++) {
        if (text[i] == text[i-1]) {
            return true
        }
    }
    false
}

static boolean notContainsDisallowedSubstrings(String text) {
    ['ab', 'cd', 'pq', 'xy'].stream().noneMatch { text.contains(it) }
}

static boolean isNice(String text) {
    has3Vowels(text)
            && hasAtLeastOneLetterTwiceInARow(text)
            && notContainsDisallowedSubstrings(text)
}

static long solve(String input) {
    input.split('\n').count { isNice(it) }
}

assert 1L == solve('ugknbfddgicrmopn')
assert 1L == solve('aaa')
assert 0L == solve('jchzalrnumimnmhp')
assert 0L == solve('haegwjzuvuyypxyu')
assert 0L == solve('dvszwmarrgswjxmb')

println solve(new File('input/5.txt').text)