JAVAC = javac
JAVA = java
JAR = jar
JAVADOC = javadoc

SRC_DIR = src
BIN_DIR = bin
TEST_DIR = tests
DOCS_DIR = docs
JUNIT = junit-console.jar

SRC_FILES = $(shell find $(SRC_DIR) -name "*.java")
TEST_FILES = $(shell find $(TEST_DIR) -name "*.java")

# ─────────────────────────────────────────────
# docs : génère la javadoc
# ─────────────────────────────────────────────
docs:
	mkdir -p $(DOCS_DIR)
	$(JAVADOC) -d $(DOCS_DIR) -sourcepath $(SRC_DIR) \
		-subpackages game 2>/dev/null; true

# ─────────────────────────────────────────────
# classes : compile toutes les classes sources
# ─────────────────────────────────────────────
classes:
	mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SRC_FILES)

# ─────────────────────────────────────────────
# jar : crée livrable3a.jar et livrable3b.jar
# ─────────────────────────────────────────────
jar: classes
	$(JAR) cfe livrable3a.jar game.Livrable3a -C $(BIN_DIR) .
	$(JAR) cfe livrable3b.jar game.Livrable3b -C $(BIN_DIR) .

# ─────────────────────────────────────────────
# tests : compile les classes de tests
# ─────────────────────────────────────────────
tests: classes
	mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) -cp "$(BIN_DIR):$(JUNIT)" $(TEST_FILES)

# ─────────────────────────────────────────────
# runtests : lance tous les tests JUnit
# ─────────────────────────────────────────────
runtests: tests
	$(JAVA) -jar $(JUNIT) --class-path $(BIN_DIR) --scan-class-path

# ─────────────────────────────────────────────
# clean : supprime les fichiers compilés
# ─────────────────────────────────────────────
clean:
	rm -rf $(BIN_DIR)/* $(DOCS_DIR)/*

.PHONY: docs classes jar tests runtests clean
