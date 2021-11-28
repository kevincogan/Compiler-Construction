package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.ExprLexer;
import antlr.ExprParser;
import expression.EvalVisitor;
import expression.IRGeneratorVisitor;

public class App {
	
	public static void writeToFile(String fileName, String value) throws IOException {
		String fn = Paths.get(fileName).getFileName().toString().split("\\.")[0] + ".ir";
        Files.write(Paths.get(fn), Collections.singleton(value));
        }
	
	public static void main(String[] args) throws Exception {
		
		
		String inputFile = null;
    	
    	//If a file is inputed save the file name.
        if (args.length > 0) {
        	inputFile = args [0];
        }
        InputStream is = System.in;

        //If there is a file name stored execute FileInputStream.
        if (inputFile != null) {
            is = new FileInputStream (inputFile);
        }
      
//////////////////////////////////////////////////////////////////////////////
//        String inputFile = (args.length > 0) ? args[0] : null;
//
//        if (inputFile == null) {
//            System.out.println("Please provide an input file");
//            System.exit(0);
//        }
//////////////////////////////////////////////////////////////////////////////

        is = new FileInputStream(inputFile);
        ExprLexer lexer = new ExprLexer(CharStreams.fromStream(is));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);

        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
        

        IRGeneratorVisitor ir = new IRGeneratorVisitor();
        String irCode = ir.visit(tree);
        
        System.out.println("\n" + irCode);

        writeToFile(inputFile, irCode);
        }
}