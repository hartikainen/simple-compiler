BIN_DIR = ./bin
SLX_DIR = ./slx
#EASY_TESTS := $(foreach example,$(EXAMPLES),$(example)_clean)
EASY_TESTS := $(shell find easy/tests -name '*.tst')

all:
	java -jar $(BIN_DIR)/Coco.jar -frames $(BIN_DIR) Compiler.atg
	javac *.java #"-Xlint:unchecked"

clean:
	rm *.class

coco-clean: clean
	rm Parser.java Scanner.java

easy-test:
	@echo
	@echo "**************************************************"
	@echo "Running easy tests:"
	@echo "**************************************************"
	make coco-clean;
	make;
	@$(foreach TEST_FILE, $(EASY_TESTS), test-file TEST_FILE=$(TEST_FILE);)

test-file:
	@echo "Testing file: " $(TEST_FILE)
	java Compiler $(TEST_FILE)
	java -jar $(BIN_DIR)/SlxInterpreter.jar $(SLX_DIR)/$(TEST_FILE).slx
