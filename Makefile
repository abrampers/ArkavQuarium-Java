# makkefile begins
# define a variable for compiler flags (JFLAGS)
# define a variable for the compiler (JC)
# define a variable for the Java Virtual Machine (JVM)
# define a variable for a parameter. When you run make, you could use:
# make run FILE="Algo.csv" para sobre escribir el valor de FILE.

#
# CLASSES is a macro consisting of N words (one for each java source file)
# When a single line is too long, use \<return> to split lines that then will be
# considered as a single line. For example:

CLASSESCOMPLETE = src/itb/arkavquarium/Aquarium.java \
        src/itb/arkavquarium/Aquatic.java \
        src/itb/arkavquarium/Coin.java \
        src/itb/arkavquarium/Constants.java \
        src/itb/arkavquarium/Fish.java \
        src/itb/arkavquarium/GameController.java \
        src/itb/arkavquarium/GameState.java \
        src/itb/arkavquarium/GameView.java \
        src/itb/arkavquarium/Guppy.java \
        src/itb/arkavquarium/LinkedList.java \
        src/itb/arkavquarium/Pellet.java \
        src/itb/arkavquarium/Piranha.java \
        src/itb/arkavquarium/Snail.java \
        src/itb/arkavquarium/State.java

CLASSES = Aquarium.java \
        Aquatic.java \
        Coin.java \
        Constants.java \
        Fish.java \
        GameController.java \
        GameState.java \
        GameView.java \
        Guppy.java \
        LinkedList.java \
        Pellet.java \
        Piranha.java \
        Snail.java \
        State.java

CLASSIN = src/itb/arkavquarium

CLASSOUT = out/production/ITB-Java-ArkavQuarium/

TESTSCOMPLETE = test/itb/arkavquarium/AquariumTest.java \
        test/itb/arkavquarium/CoinTest.java \
        test/itb/arkavquarium/FishTest.java \
        test/itb/arkavquarium/GuppyTest.java \
        test/itb/arkavquarium/LinkedListTest.java \
        test/itb/arkavquarium/PelletTest.java \
        test/itb/arkavquarium/PiranhaTest.java \
        test/itb/arkavquarium/SnailTest.java 
        # test/itb/arkavquarium/TestAll.java

TESTS = AquariumTest.java \
        CoinTest.java \
        FishTest.java \
        GuppyTest.java \
        LinkedListTest.java \
        PelletTest.java \
        PiranhaTest.java \
        SnailTest.java \
        TestAll.java

TESTIN = test/itb/arkavquarium

TESTOUT = out/test/ITB-Java-ArkavQuarium/itb/arkavquarium

COMPILECLASSFLAGS = -g -d $(CLASSOUT)
TESTCLASSFLAGS = -g -d $(TESTOUT) -cp "lib/*:out/production/ITB-Java-ArkavQuarium/itb/arkavquarium/*" 

JC = javac
JVM= java

#
# MAIN is a variable with the name of the file containing the main method
#

CLASSMAIN = itb.arkavquarium.GameController
TESTMAIN = itb.arkavquarium.TestAll

#
# Clear any default targets for building .class files from .java files; we
# will provide our own target entry to do this in this makefile.
# make has a set of default targets for different suffixes (like .c.o)
# Currently, clearing the default for .java.class is not necessary since
# make does not have a definition for this target, but later versions of
# make may, so it doesn't hurt to make sure that we clear any default
# definitions for these
#

.SUFFIXES: .java .class


#
# Here is our target entry for creating .class files from .java files
# This is a target entry that uses the suffix rule syntax:
#	DSTS:
#		rule
# DSTS (Dependency Suffix     Target Suffix)
# 'TS' is the suffix of the target file, 'DS' is the suffix of the dependency
#  file, and 'rule'  is the rule for building a target
# '$*' is a built-in macro that gets the basename of the current target
# Remember that there must be a < tab > before the command line ('rule')
#

compileclass: $(CLASSESCOMPLETE)
	$(JC) $(COMPILECLASSFLAGS) $(CLASSESCOMPLETE)

compiletest: $(TESTSCOMPLETE)
	$(JC) $(TESTCLASSFLAGS) $(TESTSCOMPLETE)

#
# the default make target entry
# for this example it is the target classes

default:
	compileclass && compiletest


# Next line is a target dependency line
# This target entry uses Suffix Replacement within a macro:
# $(macroname:string1=string2)
# In the words in the macro named 'macroname' replace 'string1' with 'string2'
# Below we are replacing the suffix .java of all words in the macro CLASSES
# with the .class suffix
#

#classes: $(CLASSES:.java=.class)


# Next two lines contain a target for running the program
# Remember the tab in the second line.
# $(JMV) y $(MAIN) are replaced by their values

run: out/production/ITB-Java-ArkavQuarium/itb/arkavquarium/GameController.class
	$(JVM) -classpath out/production/ITB-Java-ArkavQuarium $(CLASSMAIN)

test: out/production/ITB-Java-ArkavQuarium/itb/arkavquarium/TestAll.class
	$(JVM) -classpath out/test/ITB-Java-ArkavQuarium org.junit.runner.JUnitCore $(TESTS)

# this line is to remove all unneeded files from
# the directory when we are finished executing(saves space)
# and "cleans up" the directory of unneeded .class files
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	find . -type f -name "*.class" -delete