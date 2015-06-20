BIN_DIR = ./bin
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
	@$(foreach test_file, $(EASY_TESTS), echo testing file $(test_file);)
