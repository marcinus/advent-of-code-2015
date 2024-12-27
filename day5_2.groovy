static boolean hasADoublePairNotOverlapping(String text) {
    for (int i = 0; i < text.size()-3; i++) {
        String pair = text.substring(i, i+2)
        String remainder = text.substring(i+2)
        if(remainder.contains(pair)) {
            return true
        }
    }
    false
}

static boolean hasA3LetterPalindrome(String text) {
    for (int i = 2; i < text.size(); i++) {
        if (text[i] == text[i-2]) {
            return true
        }
    }
    false
}

static boolean isNice(String text) {
    hasADoublePairNotOverlapping(text) && hasA3LetterPalindrome(text)
}

static long solve(String input) {
    input.split('\n').count { isNice(it) }
}

assert 1L == solve('qjhvhtzxzqqjkmpb')
assert 1L == solve('xxyxx')
assert 0L == solve('uurcxstgmygtbstg')
assert 0L == solve('ieodomkazucvgmuy')

println solve(new File('input/5.txt').text)