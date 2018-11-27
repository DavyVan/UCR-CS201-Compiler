NOTE: Open this file with a Markdown enabled editor to gain a better experience.

# Group Member
__Name:__ Quan Fan  
__Email:__ qfan005@ucr.edu  
__Student ID:__ 862099688

# How To Run
1. My project need __*both*__ JDK7 (for dava) and JDK8 (for static and dynamic analysis), so be sure to have them installed.  
    * When the JRE is changed, the soot library must be changed as well.
2. Import the project into eclipse.
3. You need to designate which .java file will be analyzed by modifying _Main.\_className_ (e.g. "Test1").
4. Run the program under JRE8 to do static analysis and produce .jimple file for designated test case.
5. Change JRE to 7, and run dava on the .jimple file.
6. Manually compile and run the new .java file to see results of dynamic analysis.
7. To test other test cases, go back to step 3.

# Notes
* If soot cannot find classes to be analyzed, please delete line 58 - 62 in Main.java and recover the program arguments.
* Although I used LongConstant to do profiling, there still will be some SocketFlow.XXXX in the final .java files due to the integers exist in the test cases. You can either add a import statement or modify them to integer to mitigate this problem.
* Current version of Test2 will stuck in a infinite loop, even without my profiling code.