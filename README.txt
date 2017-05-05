
					ACONEX Coding Challenge
					--------------------------

Why GEDCOM Parser Challenge?
-----------------------------
GEDCOM parser problem statement was also applicable to other domains making it an interesting usecase for extension. 
The challenge also provided with interesting and challenging opportunities to demonstrate Test Driven Development 
preseting several corner cases to be tackled and validated. The project could showcase Java SDK(1.8)'s rich 
parsing /file handling library rather than relying on other third party libraries. 

Design and Approach:
---------------------
Designed based on single responsibility and open/closed principles. TDD approach followed. 
OOP concepts applied as and when required.  

Src: 
-----
com.aconex.entity: Contains Node class represents the basic unit of the tree
com.aconex.exception: Contains GEDCOMException to capture domain specific exceptions
com.aconex.parser: Contains BaseParser interface implemented by NodeParser
com.aconex.transformer: Contains BaseTrasformer abstraction extended by GEDCOMToXMLTrannsformer
com.aconex.utils: Contains BaseWriter interface implemented by XMLWriter
GEDCOMToXMLApp: Main application that converts the given sample input to XML output 

How to run the App?
--------------------
Provide 2 arguments to the main app GEDCOMToXMLApp to specify the GEDCOM input TXT file and convert it to output XML file. 
Sample input and expected files are included in topmost dir.

Compilation: javac GEDCOMToXMLApp.java
Usage: java GEDCOMToXMLApp GEDCOMParserChallengeSampleData.txt 	GEDCOMParserChallengeOutputData.xml

Test:
------
Mimics the structure of the src for readibility and easy maintainability, with corresponding unit and acceptance tests 
that add to a total of 30 testcases. Junit4 library used for testing. It also contains the test suite and runner. 

com.aconex.GEDCOMTestSuite 
com.aconex.GEDCOMTestSuiteRunner

Total Tests Run = 30
Total Run Time = 348ms
Total Failures = 0

Shortcomings/known issues:
-----------------------------
Not checking if IDs across the input file are unique
Please note: For isValidTag implemention, tags of only length 3 or 4 (after trimming) are considered as valid and allows mixed cases since eventually it converts to lowercase 

