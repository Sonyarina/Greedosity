# Greedosity
ScoreKeeper Project (Lesson 9)

The way the game works is straightforward until the SURPRISE button is pressed. For example, when contestant Mike clicks the SURPRISE button, the method "surpriseMike" first checks Mike's current earnings (scoreMike). If scoreMike is 0, then clicking on the SURPRISE button will add a random amount between $10 and $50 to his earnings. If scoreMike is more than 0, the method checks to see how many times Mike clicked the $50 button since his last reset to 0. That information, which is stored in the global variable "numberOfTimesMikePicked50", determines whether Mike's current earnings will be doubled, tripled, or reset to zero. Mike's earnings are safe if numberOfTimesMikePicked50 is either 0 or 1. But if Mike picked the $50 button 2 or more times since his last reset, then he has a 50/50 chance of either tripling his earnings or being reset to 0. By the way, whenever Mike is reset to zero, the variable "numberOfTimesMikePicked50" is also reset to 0.

SURPRISE button functionality:

Contestants are not allowed to click the SURPRISE button consecutively.

If contestant score = $0, add random amount between $10 and $50

If not, then:

	If numberOfTimesPicked50 = 0, triple contestant score.
	
	If numberOfTimesPicked50 = 1, double contestant score.
	
	If numberOfTimesPicked50  >= 2, contest either resets to zero or triples score.
	
		If contestant loses all earnings, numberOfTimesPicked50 also resets to zero.
		

Screenshots: https://photos.app.goo.gl/TFKTpIgsqQ12fLep1

Video: https://youtu.be/5bq4Y-ncNSA
