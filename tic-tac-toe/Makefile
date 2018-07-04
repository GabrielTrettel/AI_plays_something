JC = javac
JFLAG = -cp .


BUILD := build
GUI := GUI
FRAMEWORK := framework
AI := IA


.SUFFIXES: .java .class

PHONY: all

all:
	@printf "\e[32mMaking build dir\e[0m\n"
	@mkdir $(BUILD)
	@printf "\e[32mBuilding...\e[0m\n"
	@$(MAKE) --no-print-directory -C $(GUI)
	@$(MAKE) --no-print-directory -C $(FRAMEWORK)
	@$(MAKE) --no-print-directory -C $(AI)
#	$(JC) $(JFLAG) *.java -d $(BUILD)
	@printf "\e[92mBuilt $@\e[0m\n"

clean:
	@printf "\e[32mCleaning\e[0m\n"
	@$(RM) -R $(BUILD)
	@printf "\e[92mClean done\e[0m\n"
