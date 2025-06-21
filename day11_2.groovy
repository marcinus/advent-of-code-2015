static String nextPassword(String eightDigitPassword) {
    def sb = new StringBuffer()
    boolean carry = true
    for (int i = 7; i >= 0; i--) {
        if (carry) {
            char digit = eightDigitPassword.charAt(i)
            if (digit != 'z' as char) {
                carry = false
                sb.append((digit + 1) as char)
            } else {
                sb.append('a' as char)
            }
        } else {
            sb.append(eightDigitPassword.charAt(i))
        }
    }
    sb.reverse()
    sb.toString()
}

static boolean twoRepeating(String eightDigitPassword) {
    int firstRepeating = -1
    for (int i = 0; i < 8 - 1; i++) {
        int x = eightDigitPassword.charAt(i)
        int y = eightDigitPassword.charAt(i + 1)
        if (x == y) {
            if (firstRepeating == -1) {
                firstRepeating = x
            } else if (x != firstRepeating) {
                return true
            }
            i++
        }
    }
    false
}

static boolean increasingStraightSequence(String eightDigitPassword) {
    for (int i = 0; i < 8 - 2; i++) {
        int x = eightDigitPassword.charAt(i)
        int y = eightDigitPassword.charAt(i + 1)
        int z = eightDigitPassword.charAt(i + 2)
        if (y == x + 1 && z == x + 2) return true
    }
    false
}

static boolean noForbiddenLetters(String eightDigitPassword) {
    !'iol'.any { eightDigitPassword.contains(it) }
}

static boolean isValid(String eightDigitPassword) {
    twoRepeating(eightDigitPassword)
            && increasingStraightSequence(eightDigitPassword)
            && noForbiddenLetters(eightDigitPassword)
}

static String solve(String input) {
    assert input.size() == 8
    input = nextPassword(input)
    while (!isValid(input)) {
        input = nextPassword(input)
    }
    input
}

println solve(solve('hepxcrrq'))