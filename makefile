JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Command.java \
	CommandWords.java \
	Door.java \
	Exit.java \
	Game.java \
	Item.java \
	NPC.java \
	Parser.java \
	Player.java \
	Room.java \
	Zuul.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	java Zuul
