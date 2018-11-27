import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import soot.ArrayType;
import soot.FloatType;
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
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.FloatConstant;
import soot.jimple.IntConstant;
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
	private static List<UnitGraph> unitGraphs = new ArrayList<UnitGraph>();
	private static List<BlockGraph> blockGraphs = new ArrayList<BlockGraph>();
	private static List<List<Integer>> liveLocals = new ArrayList<List<Integer>>();
	
	public static void main(String[] args) {
		
		//Static Analysis (Retrieve Flow Graph)
		staticAnalysis();

		//Dynamic Analysis (Instrumentation) 
		dynamicAnalysis();
 
		Scene.v().addBasicClass("java.io.PrintStream",SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.lang.System",SootClass.SIGNATURES);
        
        // Overwrite program arguments to allow me to designate which test file should be analyzed.
        List<String> _args = new ArrayList<String>();
        _args.add("-allow-phantom-refs");
        _args.add(_className);
        String[] args1 = (String[]) _args.toArray(new String[_args.size()]);
		soot.Main.main(args1);
		//-allow-phantom-refs -process-dir /home/fanquan/Desktop/CS201Fall18_FQ/Analysis/

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
	    	Body _copy = (Body) currentBody.clone();
	    	
	    	BlockGraph blockGraph = new ClassicCompleteBlockGraph(currentBody);
	    	blockGraphs.add(new ClassicCompleteBlockGraph(_copy));
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
	    	Body _copy = (Body) currentBody.clone();
	    	liveLocals.add(new ArrayList<Integer>());
	    	
	    	UnitGraph unitGraph = new ClassicCompleteUnitGraph(currentBody);
	    	unitGraphs.add(new ClassicCompleteUnitGraph(_copy));
	    	
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
	    		liveLocals.get(i).add(before.size());
	    		
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
				
				SootField blockExeNumField = null;
				SootField edgeExeNumField = null;
				
				// Prepare
				// Add tmpLocal
				Local tmpLocal = Jimple.v().newLocal("tmpLocal", LongType.v());
				arg0.getLocals().add(tmpLocal);
				Local ttmpLocal = Jimple.v().newLocal("ttmpLocal", LongType.v());
				arg0.getLocals().add(ttmpLocal);
				Local tttmpLocal = Jimple.v().newLocal("tttmpLocal", LongType.v());
				arg0.getLocals().add(tttmpLocal);
				Local tmpArrayRef = Jimple.v().newLocal("tmpArrayRef", ArrayType.v(LongType.v(), 1));
				arg0.getLocals().add(tmpArrayRef);
				Local tmpFloat = Jimple.v().newLocal("tmpFloat", FloatType.v());
				arg0.getLocals().add(tmpFloat);
				
				// If this is the main function
				boolean isMainMethod = arg0.getMethod().getSubSignature().equals("void main(java.lang.String[])");
				
				// Load Print Stream
				Local tmpRef = Jimple.v().newLocal("tmpRef", RefType.v("java.io.PrintStream"));
		        arg0.getLocals().add(tmpRef);
		        SootMethod printIntCall = Scene.v().getSootClass("java.io.PrintStream").getMethod("void println(int)");
		        SootMethod printStringCall = Scene.v().getSootClass("java.io.PrintStream").getMethod("void print(java.lang.String)");
		        SootMethod printFloatCall = Scene.v().getSootClass("java.io.PrintStream").getMethod("void println(float)");
				
		        /**
				 * BB Profiling
				 * Declare a field for each block in each function with field name "[funcName]BB[block index]ExeNum"
				 */
				for (Block b : blocks) {	// For each block
					// Add Fields
					blockExeNumField = new SootField(currentMethodName + "BB" + b.getIndexInMethod() + "ExeNum", LongType.v(), Modifier.STATIC);
					currentSootClass.addField(blockExeNumField);
					
					// Increment instructions
					Unit headUnit = b.getHead();
					Unit tailUnit = b.getTail();
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
				        
				        b.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("\n"))), tailUnit);
						b.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("+----------------------------------\n"))), tailUnit);
						b.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("| Basic Block Profiling\n"))), tailUnit);
						b.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("+----------------------------------\n"))), tailUnit);
				        List<SootMethod> methods = currentSootClass.getMethods();
				        for (SootMethod m : methods) {
				        	if (m.getName().equalsIgnoreCase("<init>"))
				        		continue;
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
				
				/**
				 * Edge Profiling
				 * Declare a field for each edge with name "[funcName]BB[index1]toBB[index2]"
				 * Only profile functions that contain 2 or more blocks
				 */
				if (blockNum >= 2) {
					// Add Field
					SootField lastBlockField = new SootField(currentMethodName + "LastBlock", LongType.v(), Modifier.STATIC);
					currentSootClass.addField(lastBlockField);
					// Add Matrix Field
					edgeExeNumField = new SootField(currentMethodName + "EdgeExeNumMatrix", ArrayType.v(LongType.v(), 1), Modifier.STATIC);
					currentSootClass.addField(edgeExeNumField);
					
					// Increment Instructions
					for (Block b : blocks) {
						Unit headUnit = b.getHead();
						Unit tailUnit = b.getTail();
						int index = b.getIndexInMethod();
						if (index != 0) {	// skip head block
							// ...EdgeExeNumMatrix[lastBlock][index]++;
							//				|
							//				V
							// ...EdgeExeNumMatrix[lastBlock * blockNum + index]++;
							// (1) tmpLocal = lastBlock
							b.insertBefore(Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newStaticFieldRef(lastBlockField.makeRef())), tailUnit);
							// (2) tmpLocal = tmpLocal * blockNum
							b.insertBefore(Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newMulExpr(tmpLocal, LongConstant.v(blockNum))), tailUnit);
							// (3) tmpLocal = tmpLocal + index
							b.insertBefore(Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newAddExpr(tmpLocal, LongConstant.v(index))), tailUnit);
							// (4) tmpArrayRef = Matrix
							b.insertBefore(Jimple.v().newAssignStmt(tmpArrayRef, Jimple.v().newStaticFieldRef(edgeExeNumField.makeRef())), tailUnit);
							// (5) ttmpLoacl = Matrix[tmpLocal]
							b.insertBefore(Jimple.v().newAssignStmt(ttmpLocal, Jimple.v().newArrayRef(tmpArrayRef, tmpLocal)), tailUnit);
							// (6) ttmpLocal = ttmpLocal + 1
							b.insertBefore(Jimple.v().newAssignStmt(ttmpLocal, Jimple.v().newAddExpr(ttmpLocal, LongConstant.v(1))), tailUnit);
							// (7) Matrix[tmpLocal] = ttmpLocal
							b.insertBefore(Jimple.v().newAssignStmt(Jimple.v().newArrayRef(tmpArrayRef, tmpLocal), ttmpLocal), tailUnit);
						}
						
						// update lastBlock
						// lastBlock = index
						b.insertBefore(Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(lastBlockField.makeRef()), LongConstant.v(index)), tailUnit);
					}
				}

				if (isMainMethod) {		// Something to be done in main function
					// Allocate matrixes for every method
					Block headBlock = blocks.get(0);	// Insert to theE beginning of main
					List<SootMethod> methods = currentSootClass.getMethods();
					for (SootMethod m : methods) {
						int _blockNum = new ClassicCompleteBlockGraph(m.retrieveActiveBody()).getBlocks().size();
						if (_blockNum >= 2) {
							Unit _u = headBlock.getHead();
							headBlock.insertBefore(Jimple.v().newAssignStmt(tmpArrayRef, Jimple.v().newNewArrayExpr(LongType.v(), IntConstant.v(_blockNum * _blockNum))), _u);
							headBlock.insertBefore(Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(currentSootClass.getFieldByName(m.getName() + "EdgeExeNumMatrix").makeRef()), tmpArrayRef), _u);
						}
					}
					
					// Output
					Block tailBlock = blockGraph.getTails().get(0);		// We only have one exit in main function for this project
					Unit tailUnit = tailBlock.getTail();				// Insert to the end of main
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("\n"))), tailUnit);
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("+----------------------------------\n"))), tailUnit);
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("| Edge Profiling\n"))), tailUnit);
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("+----------------------------------\n"))), tailUnit);
					for (SootMethod m : methods) {
						ClassicCompleteBlockGraph mBlockGraph = new ClassicCompleteBlockGraph(m.retrieveActiveBody());
						List<Block> mBlocks = mBlockGraph.getBlocks();
						int _blockNum = mBlocks.size();
						if (_blockNum >= 2) {
							// Print "Method: <...>"
							tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("Method: " + m.toString() + "\n"))), tailUnit);
							
							SootField _edgeExeNumField = currentSootClass.getFieldByName(m.getName() + "EdgeExeNumMatrix");
							// (0.1) tmpArrayRef = Matrix
							tailBlock.insertBefore(Jimple.v().newAssignStmt(tmpArrayRef, Jimple.v().newStaticFieldRef(_edgeExeNumField.makeRef())), tailUnit);
							for (Block mb : mBlocks) {
								int predIndex = mb.getIndexInMethod();
								List<Block> mbSuccs = mb.getSuccs();
								for (Block mbs : mbSuccs) {
									// (1) print "BB[predIndex] -> BB[mbs.index]:  "
									tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("BB" + predIndex + " -> BB" + mbs.getIndexInMethod() + ":  "))), tailUnit);
									// (2) tmpLocal = tmpArrayRef[X] (X = predIndex * _blockNum + mbs.index]
									tailBlock.insertBefore(Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newArrayRef(tmpArrayRef, LongConstant.v(predIndex * _blockNum + mbs.getIndexInMethod()))), tailUnit);
									// (3) Print tmpLocal
									tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printIntCall.makeRef(), tmpLocal)), tailUnit);
								}
							}
						}
						
					}	// end of foreach m in methods
					
					/**
					 * Average Number Of Variables Live At An Executed Instruction
					 */
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("\n"))), tailUnit);
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("+----------------------------------\n"))), tailUnit);
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("| Average Number Of Variables Live\n"))), tailUnit);
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("| At An Executed Instruction\n"))), tailUnit);
					tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("+----------------------------------\n"))), tailUnit);
					
					int methodNum = methods.size();
					for (int i = 0; i < methodNum-1; i++) {
						SootMethod m = methods.get(i);
						BlockGraph mBlockGraph = blockGraphs.get(i);
						List<Block> mBlocks = mBlockGraph.getBlocks();
						UnitGraph mUnitGraph = unitGraphs.get(i);
						List<Integer> mLiveLocals = liveLocals.get(i);
						
						tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printStringCall.makeRef(), StringConstant.v("Method: " + m.toString() + "\n"))), tailUnit);
						
						// (0.1) tmpFloat = 0.0f;
						tailBlock.insertBefore(Jimple.v().newAssignStmt(tmpFloat, FloatConstant.v(0.0f)), tailUnit);
						// (0.2) tttmpLocal = 0;
						tailBlock.insertBefore(Jimple.v().newAssignStmt(tttmpLocal, LongConstant.v(0)), tailUnit);
						int j = 0;
						for (Block mb : mBlocks) {
							SootField mbExeNumField = currentSootClass.getFieldByName(m.getName() + "BB" + mb.getIndexInMethod() + "ExeNum");
							// (0.3) tmpLocal = ExeNumField
							tailBlock.insertBefore(Jimple.v().newAssignStmt(tmpLocal, Jimple.v().newStaticFieldRef(mbExeNumField.makeRef())), tailUnit);
							
							Iterator<Unit> _uIterator = mb.iterator();
							synchronized (_uIterator) {
								while (_uIterator.hasNext()) {
									Unit _u = _uIterator.next();
									// (1) ttmpLocal = tmpLocal * LongConstant(lives)
									tailBlock.insertBefore(Jimple.v().newAssignStmt(ttmpLocal, Jimple.v().newMulExpr(tmpLocal, LongConstant.v(mLiveLocals.get(j)))), tailUnit);
									// (2) tmpFloat = tmpFloat + ttmpLocal
									tailBlock.insertBefore(Jimple.v().newAssignStmt(tmpFloat, Jimple.v().newAddExpr(tmpFloat, ttmpLocal)), tailUnit);
									// (3) tttmpLocal = tttmpLocal + tmpLocal
									tailBlock.insertBefore(Jimple.v().newAssignStmt(tttmpLocal, Jimple.v().newAddExpr(tttmpLocal, tmpLocal)), tailUnit);
									j++;
								}
							}
						}	// end of for j blocks
						// (4) tmpFloat = tmpFloat / tttmpLocal
						tailBlock.insertBefore(Jimple.v().newAssignStmt(tmpFloat, Jimple.v().newDivExpr(tmpFloat, tttmpLocal)), tailUnit);
						// (5) Print result tmpFloat
						tailBlock.insertBefore(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(tmpRef, printFloatCall.makeRef(), tmpFloat)), tailUnit);
					}	// end of for i methods
				}	// end of if isMainMethod
			}	// end of profile()
			
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
    }
}

