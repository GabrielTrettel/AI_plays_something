JC = javac
JFLAG = -cp .

BUILD := ../build
CONSOLE := console


.SUFFIXES: .java .class

PHONY: all

all:
	@$(JC) $(JFLAG) $(CONSOLE)/*.java -d $(BUILD)
