import java.util.Map;
import java.util.List;

import soot.Local;
import soot.Unit;
import soot.Body;
import soot.BodyTransformer;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.options.Options;
import soot.toolkits.graph.BlockGraph;
import soot.toolkits.graph.ClassicCompleteBlockGraph;
import soot.toolkits.graph.ClassicCompleteUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.SimpleLiveLocals;


public class Main {
	private static String _className = "Test1";
	
	public static void main(String[] args) {
		
		//Static Analysis (Retrieve Flow Graph)
		staticAnalysis();

		//Dynamic Analysis (Instrumentation) 
		dynamicAnalysis();
 
//		soot.Main.main(args);

	}

	private static void staticAnalysis(){
		configure(".:/home/fanquan/Desktop/CS201Fall18_FQ/Analysis"); //Change this path to your Analysis folder path in your project directory
		SootClass sootClass = Scene.v().loadClassAndSupport(_className);
	    sootClass.setApplicationClass();
	    //Static Analysis code
	    
	    List<SootMethod> methods = sootClass.getMethods();
	    int methodsNum = sootClass.getMethodCount();
	    System.out.println("Detected " + methodsNum + " methods in class \" " + _className + "\": " + methods);
	    
	    // For each method, do analysis
	    /**
    	 * CFG
    	 */
	    System.out.println("+----------------------------------");
	    System.out.println("| Control Flow Graphs");
	    System.out.println("+----------------------------------");
	    for (int i = 0; i < methodsNum-1; i++) {
	    	SootMethod currentMethod = methods.get(i);
	    	Body currentBody = currentMethod.retrieveActiveBody();
	    	
	    	UnitGraph unitGraph = new ClassicCompleteUnitGraph(currentBody);
	    	BlockGraph blockGraph = new ClassicCompleteBlockGraph(currentBody);
	    	System.out.println("Method: " + currentMethod.toString());
	    	System.out.println(blockGraph.toString());
	    }
	    
	    /**
    	 * Live Variables
    	 */
	    System.out.println("+----------------------------------");
	    System.out.println("| Live Variable Analysis");
	    System.out.println("+----------------------------------");
	    for (int i = 0; i < methodsNum-1; i++) {
	    	SootMethod currentMethod = methods.get(i);
	    	Body currentBody = currentMethod.retrieveActiveBody();
	    	
	    	UnitGraph unitGraph = new ClassicCompleteUnitGraph(currentBody);
	    	BlockGraph blockGraph = new ClassicCompleteBlockGraph(currentBody);
	    	
	    	
	    	// Do analysis: SimpleLiveVariable
	    	SimpleLiveLocals simpleLiveLocals = new SimpleLiveLocals(unitGraph);
	    	
	    	// Prepare for outputs of Live Variables: alignment
	    	int maxLength = 0;
	    	for (Unit u : unitGraph) {
	    		int l = u.toString().length();
	    		if (l > maxLength)
	    			maxLength = l;
	    	}
	    	
	    	// Output
	    	System.out.println("Method: " + currentMethod.toString());
	    	for (Unit u : unitGraph) {
	    		List<Local> before = simpleLiveLocals.getLiveLocalsBefore(u);
	    		List<Local> after = simpleLiveLocals.getLiveLocalsAfter(u);
	    		
	    		String s = u.toString();
	    		int length = maxLength - s.length();
	    		System.out.print(s);	// Jimple stmt
	    		for (int j = 0; j < length; j++) {
	    			System.out.print(".");	// alignment
	    		}
	    		System.out.print("    [entry: ");	// entry
	    		for (Local _local : before) {
	    			System.out.print(_local.getName() + " ");
	    		}
	    		System.out.print("]\t\t[exit: ");	// exit
	    		for (Local _local : after) {
	    			System.out.print(_local.getName() + " ");
	    		}
	    		System.out.println("]");
	    	}
	    	System.out.println();
	    }
	}

	private static void dynamicAnalysis(){
		PackManager.v().getPack("jtp").add(new Transform("jtp.myInstrumenter", new BodyTransformer() {

			@Override
			protected void internalTransform(Body arg0, String arg1, Map arg2) {
				//Dynamic Analysis (Instrumentation) code				
			}			
	   }));
	}
	
	public static void configure(String classpath) {		
        Options.v().set_whole_program(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_src_prec(Options.src_prec_java);
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_soot_classpath(classpath);
        Options.v().set_prepend_classpath(true);
        Options.v().setPhaseOption("cg.spark", "on");        
    }
}

