import java.util.Map;
import java.util.List;

import soot.ArrayType;
import soot.LongType;
import soot.Local;
import soot.Modifier;
import soot.RefType;
import soot.SootField;
import soot.Unit;
import soot.Body;
import soot.BodyTransformer;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.jimple.AssignStmt;
import soot.jimple.LongConstant;
import soot.jimple.Jimple;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.StringConstant;
import soot.options.Options;
import soot.toolkits.graph.Block;
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
 
		Scene.v().addBasicClass("java.io.PrintStream",SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.lang.System",SootClass.SIGNATURES);
		soot.Main.main(args);	//TODO: uncomment this

	}

	private static void staticAnalysis(){
		configure(".:/home/fanquan/Desktop/CS201Fall18_FQ/Analysis"); //Change this path to your Analysis folder path in your project directory
		SootClass sootClass = Scene.v().loadClassAndSupport(_className);
	    sootClass.setApplicationClass();
	    //Static Analysis code
	    
	    List<SootMethod> methods = sootClass.getMethods();
	    int methodsNum = sootClass.getMethodCount();
	    System.out.println("Detected " + methodsNum + " methods in class \" " + _className + "\": " + methods);
	    
	    /**
    	 * CFG
    	 */
	    System.out.println("+----------------------------------");
	    System.out.println("| Control Flow Graphs");
	    System.out.println("+----------------------------------");
	    for (int i = 0; i < methodsNum-1; i++) {
	    	SootMethod currentMethod = methods.get(i);
	    	Body currentBody = currentMethod.retrieveActiveBody();
	    	
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
	    	
	    	// Do analysis: SimpleLiveVariable
	    	// References: soot/RunLiveAnalysis.java, soot/SimpleLiveLocals.java
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

			private void profile(Body arg0, String arg1, Map arg2) {
				SootClass currentSootClass = arg0.getMethod().getDeclaringClass();
				ClassicCompleteBlockGraph blockGraph = new ClassicCompleteBlockGraph(arg0);
				List<Block> blocks = blockGraph.getBlocks();
				int blockNum = blocks.size();
				String currentMethodName = arg0.getMethod().getName();
				
				/**
				 * BB Profiling
				 * Declare integer field for each block in each function with field name "[funcName]BB[block index]ExeNum"
				 */
				SootField blockExeNumField = null;
				
				// Prepare
				// Add tmpLocal
				Local tmpLocal = Jimple.v().newLocal("tmpLocal", LongType.v());
				arg0.getLocals().add(tmpLocal);
				// If this is the main function
				boolean isMainMethod = arg0.getMethod().getSubSignature().equals("void main(java.lang.String[])");
				// Print
				Local tmpRef = Jimple.v().newLocal("tmpRef", RefType.v("java.io.PrintStream"));
		        arg0.getLocals().add(tmpRef);
		        SootMethod printIntCall = Scene.v().getSootClass("java.io.PrintStream").getMethod("void println(int)");
		        SootMethod printStringCall = Scene.v().getSootClass("java.io.PrintStream").getMethod("void print(java.lang.String)");
				
				for (Block b : blocks) {	// For each block
					// Add Fields
					blockExeNumField = new SootField(currentMethodName + "BB" + b.getIndexInMethod() + "ExeNum", LongType.v(), Modifier.STATIC);
					currentSootClass.addField(blockExeNumField);
					
					// Increment instructions
					Unit headUnit = b.getHead();
					Unit tailUnit = b.getTail();
					// Construct Jimple stmt
					// (1): tmpLocal = SootField
					AssignStmt blockExeNumStmt1 = Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newStaticFieldRef(blockExeNumField.makeRef()));
					// (2): tmpLocal = tmpLocal + 1
					AssignStmt blockExeNumStmt2 = Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newAddExpr(tmpLocal, LongConstant.v(1)));
					// (3): SootField = tmpLocal
					AssignStmt blockExeNumStmt3 = Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(blockExeNumField.makeRef()), tmpLocal);
					// Do insert
					b.insertBefore(blockExeNumStmt1, headUnit);
					b.insertBefore(blockExeNumStmt2, headUnit);
					b.insertBefore(blockExeNumStmt3, headUnit);
					
					// insert output instructions before main returns
					if (isMainMethod && tailUnit instanceof ReturnVoidStmt) {	// Only run once
						// Insert "tmpRef = java.lang.System.out;"
				        b.insertBefore(Jimple.v().newAssignStmt(tmpRef, Jimple.v().newStaticFieldRef(Scene.v().getField("<java.lang.System: java.io.PrintStream out>").makeRef())), tailUnit);
				        
				        List<SootMethod> methods = currentSootClass.getMethods();
				        for (SootMethod m : methods) {
				        	if (m.getName().equalsIgnoreCase("<init>"))
				        		continue;
//				        	System.out.println("Method: " + m.toString());
				        	b.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("Method: " + m.toString() + "\n"))), tailUnit);
				        	
				        	List<Block> mBlocks = new ClassicCompleteBlockGraph(m.retrieveActiveBody()).getBlocks();
				        	for (Block mb : mBlocks) {
				        		b.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("BB" + mb.getIndexInMethod() + ": "))), tailUnit);
				        		SootField mbf = currentSootClass.getFieldByName(m.getName() + "BB" + mb.getIndexInMethod() + "ExeNum");
				        		b.insertBefore(Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newStaticFieldRef(mbf.makeRef())), tailUnit);
				        		b.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printIntCall.makeRef(), tmpLocal)), tailUnit);
				        	}
				        }
					}
				}
//				arg0.validate();
			}
			
			@Override
			protected void internalTransform(Body arg0, String arg1, Map arg2) {
				//Dynamic Analysis (Instrumentation) code
				boolean isMainMethod = arg0.getMethod().getSubSignature().equals("void main(java.lang.String[])");
				if (isMainMethod) {
					// for each method in this class
					List<SootMethod> methods = arg0.getMethod().getDeclaringClass().getMethods();
					for (SootMethod m : methods) {
						boolean _isMainMethod = m.getSubSignature().equals("void main(java.lang.String[])");
						if (_isMainMethod)
							continue;
						if (m.getName().equalsIgnoreCase("<init>"))
							continue;
						this.profile(m.retrieveActiveBody(), arg1, arg2);
					}
					profile(arg0, arg1, arg2);
				}	// else do nothing
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
        Options.v().set_validate(false);
    }
}

