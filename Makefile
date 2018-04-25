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

TESTSCLASS = out/test/ITB-Java-ArkavQuarium/itb/arkavquarium/AquariumTest.class \
        out/test/ITB-Java-ArkavQuarium/itb/arkavquarium/CoinTest.class \
        out/test/ITB-Java-ArkavQuarium/itb/arkavquarium/FishTest.class \
        out/test/ITB-Java-ArkavQuarium/itb/arkavquarium/GuppyTest.class \
        out/test/ITB-Java-ArkavQuarium/itb/arkavquarium/LinkedListTest.class \
        out/test/ITB-Java-ArkavQuarium/itb/arkavquarium/PelletTest.class \
        out/test/ITB-Java-ArkavQuarium/itb/arkavquarium/PiranhaTest.class \
        out/test/ITB-Java-ArkavQuarium/itb/arkavquarium/SnailTest.class

TESTS = itb.arkavquarium.AquariumTest \
        itb.arkavquarium.CoinTest \
        itb.arkavquarium.FishTest \
        itb.arkavquarium.GuppyTest \
        itb.arkavquarium.LinkedListTest \
        itb.arkavquarium.PelletTest \
        itb.arkavquarium.PiranhaTest \
        itb.arkavquarium.SnailTest

TESTIN = test/itb/arkavquarium

TESTOUT = out/test/ITB-Java-ArkavQuarium/

COMPILECLASSFLAGS = -g -d $(CLASSOUT)
TESTCLASSFLAGS = -g -d $(TESTOUT) -cp "lib/*":"out/production/ITB-Java-ArkavQuarium/itb/arkavquarium":"test/itb/arkavquarium"

JC = javac
JVM= java

CLASSMAIN = itb.arkavquarium.GameController
TESTMAIN = itb.arkavquarium.TestAll

.SUFFIXES: .java .class

compile: $(CLASSESCOMPLETE) $(TESTSCOMPLETE)
	$(JC) $(COMPILECLASSFLAGS) $(CLASSESCOMPLETE) && $(JC) $(TESTCLASSFLAGS) $(TESTSCOMPLETE) $(CLASSESCOMPLETE)

default:
	compile

run: out/production/ITB-Java-ArkavQuarium/itb/arkavquarium/GameController.class
	$(JVM) -classpath out/production/ITB-Java-ArkavQuarium $(CLASSMAIN)

test: $(TESTSCLASS)
	$(JVM) -classpath lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar:out/test/ITB-Java-ArkavQuarium org.junit.runner.JUnitCore $(TESTS)

clean:
	find . -type f -name "*.class" -delete