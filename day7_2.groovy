static short eval(vars, rules, String target) {
    if (target.isNumber()) return (target as short)
    if (vars.containsKey(target)) return vars[target] as short
    String condition = rules[target]
    if (condition.contains('AND')) {
        def (A, B) = condition.split(' AND ').collect { eval(vars, rules, it) }
        vars[target] = A & B
    } else if (condition.contains(' OR ')) {
        def (A, B) = condition.split(' OR ').collect { eval(vars, rules, it) }
        vars[target] = A | B
    } else if (condition.contains(' LSHIFT ')) {
        def (A, B) = condition.split(' LSHIFT ').collect { eval(vars, rules, it) }
        vars[target] = A << B
    } else if (condition.contains(' RSHIFT ')) {
        def (A, B) = condition.split(' RSHIFT ').collect { eval(vars, rules, it) }
        vars[target] = A >> B
    } else if (condition.startsWith('NOT ')) {
        short A = eval(vars, rules, condition.split('NOT ')[1])
        vars[target] = ~A
    } else {
        vars[target] = eval(vars, rules, condition)
    }
    vars[target] as short
}

static short solve(String input) {
    def rules = (input.split('\n') as List).collectEntries { rule ->
        def (LHS, target) = rule.split(' -> ')
        [(target): LHS]
    }
    rules['b'] = eval([:], rules, 'a')
    eval([:], rules, 'a')
}

println solve(new File('input/7.txt').text)