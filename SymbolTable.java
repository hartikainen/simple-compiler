// package mycompiler;

import java.util.*;
// import Parser;

public class SymbolTable {
    public static final int // types
        UNDEF=0, INTEGER=1, BOOLEAN=2;

    public static final int // object kinds
        VAR=0, PROC=1, SCOPE=2;

    // public static final Map<Integer, String> types_inverse;
    // static {
    //     Map<Integer, String> m = new HashMap<Integer, String>();
    //     m.put(0, "undefined");
    //     m.put(1, "int");
    //     m.put(2, "boolean");
    //     types_inverse = Collections.unmodifiableMap(m);
    // }

    int cur_level;	// nesting level of current scope
    Variable undef_var;	// object node for erroneous symbols
    Scope top_scope;	// topmost procedure scope

    private Parser parser;

    public SymbolTable(Parser parser) {
        this.parser = parser;
        this.top_scope = null;
        this.cur_level = -1;
        undef_var = new Variable("undef", UNDEF);
        undef_var.adr = 0;
        undef_var.level = 0;
    }

    public void printTable() {
        Scope scope = this.top_scope;
        while (scope != null) {
            System.out.println(scope.name);
            scope.printLocals();
            scope = scope.next;
        }
    }

    // open a new scope and make it the current scope (topScope)
    public void openScope() {
        Scope new_scope = new Scope();
        new_scope.next = top_scope;
        this.top_scope = new_scope;
        this.cur_level++;
    }

    // close the current scope
    public void closeScope() {
        this.top_scope = top_scope.next;
        this.cur_level--;
    }

    // TODO: should the store location come from the parser?
    public Function addFunction(String name, int type) {
        Function new_function = new Function(name, type);
        this.top_scope.addSymbol(new_function);
        this.printTable();
        return new_function;
    }

    public Variable addVariable(String name, int type) {
        Variable new_variable = new Variable(name, type);
        this.top_scope.addSymbol(new_variable);
        this.printTable();
        return new_variable;
    }

    // search the name in all open scopes and return its object node
    public Symbol findSymbol(String name) {
        Scope scope = this.top_scope;
        Symbol symbol;
        while (scope != null) {
            symbol = scope.findSymbol(name);
            if (symbol != null) {
                return symbol;
            }
            scope = scope.next;
        }

        parser.SemErr(name +" is undeclared");
        return undef_var;
    }

    private class Scope {
        public Scope next;
        public int return_type;

        private String name;
        private int next_adr;                // next free address in this scope
        private HashMap<String, Symbol> locals =
            new HashMap<String, Symbol>();   // to locally declared objects

        public Scope() {
            // this.return_type = type;
            // this.next_adr = 0;
        }

        public void printLocals() {
            for (Map.Entry<String, Symbol> symbol_entry : this.locals.entrySet()) {
                String name = symbol_entry.getKey();
                Variable var = (Variable) symbol_entry.getValue();
                for (int i=0; i<var.level; i++) {
                    System.out.print("  ");
                    System.out.println(name + ": "+ Integer.toString(var.adr));
                }
            }
        }

        public void checkUniqueSymbolName(String name) {
            for (String local_name : this.locals.keySet()) {
                if (local_name.equals(name)) {
                    parser.SemErr("name declared twice");
                }
            }
        }

        public void addSymbol(Function fn) {
            this.checkUniqueSymbolName(fn.name);
            this.locals.put(fn.name, fn);
        }

        public void addSymbol(Variable var) {
            this.checkUniqueSymbolName(var.name);
            this.locals.put(var.name, var);
            var.adr = this.next_adr++;
        }

        public Symbol findSymbol(String name) {
            for (Map.Entry<String, Symbol> symbol_entry : this.locals.entrySet()) {
                if (name == symbol_entry.getKey()) {
                    return symbol_entry.getValue();
                }
            }
            return null;
        }
    }

    private class Symbol {
        String name;
        int type;

        public Symbol(String name, int type) {
            this.name = name;
            this.type = type;
        }
    }

    private class Function extends Symbol {
        public int label;
        public ArrayList<Integer> arg_types = new ArrayList<Integer>();

        public Function(String name, int type) {
            super(name, type);
        }

    }

    public class Variable extends Symbol {
        public int adr;
        public int level;

        public Variable(String name, int type) {
            super(name, type);
        }
    }
}
