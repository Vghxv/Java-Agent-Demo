JC = javac
JVM = java
SRC_DIR = ./src
OUT_DIR = ./out
SOURCES = $(shell find $(SRC_DIR) -name '*.java' -print)
CLASSES = $(patsubst $(SRC_DIR)/%.java, $(OUT_DIR)/%.class, $(SOURCES))
JCFLAGS = -d $(OUT_DIR) -sourcepath $(SRC_DIR) -cp $(SRC_DIR)
JVMFLAGS = -cp $(OUT_DIR)
MAINCLASS = theapp.HelloWorld
JARFILENAME = helloworld.jar
MANIFEST = $(SRC_DIR)/manifest.mf

default: run

run: build
	$(JVM) $(JVMFLAGS) $(MAINCLASS)

$(CLASSES): $(OUT_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) $(JCFLAGS) $<

build: $(CLASSES)

jar: build
	jar -c -f $(JARFILENAME) -m $(MANIFEST) -C $(OUT_DIR) .

clean:
	rm -rf $(OUT_DIR)
	rm -f $(JARFILENAME)
