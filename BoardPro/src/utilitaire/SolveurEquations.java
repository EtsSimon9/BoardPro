package utilitaire;

import java.util.*;
import java.io.IOException;
import java.text.*;

/**
 *
 * @author lx_user
 */
public class SolveurEquations {

	static HashMap<String, Integer> opPrecedence = new HashMap<String, Integer>();

	public static void initPrecedence() {
		opPrecedence.put("^", 4);
		opPrecedence.put("*", 3);
		opPrecedence.put("/", 3);
		opPrecedence.put("+", 2);
		opPrecedence.put("-", 2);
		opPrecedence.put(")", 1);
		opPrecedence.put("(", 1);
	}

	private static String getTokenString(String expression) {
		String[] symbole = convvertirSymboles(expression);
		String ret = "[ ";
		for (String s : symbole)
			ret += s + ", ";
		return ret.substring(0, ret.length() - 2) + " ]";
	}

	private static String[] convvertirSymboles(String expression) {
		ArrayList<String> symboles = new ArrayList<String>();
		String[] output;
		int outputSize = 0;
		boolean prevWasOperator = true; // use this to check for unary operators (-)
		for (int i = 0; i < expression.length(); i++) {
			switch (expression.charAt(i)) {
			case '^':
				symboles.add("^");
				symboles.add("");
				prevWasOperator = true;
				break;
			case '*':
				symboles.add("*");
				symboles.add("");
				prevWasOperator = true;
				break;
			case '/':
				symboles.add("/");
				symboles.add("");
				prevWasOperator = true;
				break;
			case '+':
				symboles.add("+");
				symboles.add("");
				prevWasOperator = true;
				break;
			case '-':
				if (prevWasOperator && expression.charAt(i + 1) != '(') {
					symboles.add("-");
				} else if (prevWasOperator && expression.charAt(i + 1) == '(') {
					symboles.add("-1");
					symboles.add("*");
				} else {
					symboles.add("-");
					symboles.add("");
					prevWasOperator = true;
				}
				break;
			case '(':
				symboles.add("(");
				symboles.add("");
				prevWasOperator = true;
				break;
			case ')':
				symboles.add(")");
				symboles.add("");
				prevWasOperator = false;
				break;
			case ' ':
				// remove whitespace
				break;
			case '[':
				String scientificNotation = "";
				for (int j = i + 1; j < expression.length(); j++) {
					if (expression.charAt(j) == ']') {
						i = j;
						break;
					}
					scientificNotation += expression.charAt(j);
				}
				symboles.add(scientificNotation);
				prevWasOperator = false;
				break;
			default: // alphanumeric characters
				if (symboles.size() < 1)
					symboles.add("");
				String item = symboles.get(symboles.size() - 1) + expression.charAt(i);
				symboles.remove(symboles.size() - 1);
				symboles.add(item);
				prevWasOperator = false;
			}
		}
		for (int i = 0; i < symboles.size(); i++) {
			if (symboles.get(i).length() > 0)
				outputSize++;
		}
		output = new String[outputSize];
		int j = 0;
		for (int i = 0; i < symboles.size(); i++) {
			if (symboles.get(i).length() > 0) {
				output[j] = symboles.get(i);
				j++;
			}
		}
		return output;
	}

	private static String infixToPostfix(String expression) {
		if (expression == "") // empty string
			return "";
		Stack<String> operationStack = new Stack<String>();
		String[] lettres = convvertirSymboles(expression);
		String ret = "";
		for (String let : lettres) {
			if (let.equals("(")) {
				operationStack.push(let);
			} else if (let.equals(")")) {
				while (!operationStack.peek().equals("("))
					ret += operationStack.pop() + " ";
				operationStack.pop();
			} else if (opPrecedence.containsKey(let)) {
				while (!operationStack.isEmpty() && opPrecedence.get(operationStack.peek()) >= opPrecedence.get(let))
					ret += operationStack.pop() + " ";
				operationStack.push(let);
			} else {
				ret += let + " ";
			}
		}
		while (!operationStack.isEmpty())
			ret += operationStack.pop() + " ";
		return ret;
	}

	private static double evaluaterPostfix(String expression) {
		if (expression == "")
			return 0;
		Stack<Double> operandStack = new Stack<Double>();
		String[] tokens = expression.split(" ");
		for (String tok : tokens) {
			double num1, num2;
			switch (tok) {
			case "+":
				num2 = (double) operandStack.pop();
				num1 = (double) operandStack.pop();
				operandStack.push(num1 + num2);
				break;
			case "-":
				num2 = (double) operandStack.pop();
				num1 = (double) operandStack.pop();
				operandStack.push(num1 - num2);
				break;
			case "*":
				num2 = (double) operandStack.pop();
				num1 = (double) operandStack.pop();
				operandStack.push(num1 * num2);
				break;
			case "/":
				num2 = (double) operandStack.pop();
				num1 = (double) operandStack.pop();
				operandStack.push(num1 / num2);
				break;
			case "^":
				num2 = (double) operandStack.pop();
				num1 = (double) operandStack.pop();
				operandStack.push(Math.pow(num1, num2));
				break;
			default:
				operandStack.push(Double.parseDouble(tok));
			}
		}
		return (double) operandStack.pop();
	}

	private static boolean possedeFonction(String expression) {
		return expression.contains("log") || expression.contains("sin") || expression.contains("cos")
				|| expression.contains("tan") || expression.contains("abs")
		
		;
	}

	private static boolean isOperator(char c) {
		return c == '^' || c == '*' || c == '/' || c == '-' || c == '+';
	}

	private static boolean isTerm2(char c) {
		return c == '(' || c == '[' || (c - 'a' < 26 && c - 'a' >= 0);
	}

	private static boolean isTerm1(char c) {
		return c == ')' || c == ']' || (c - '0' <= 9 && c - '0' >= 0);
	}

	private static String getMultiplicationImplicite(String expression) {
		String ret = expression;
		boolean scanned = false;
		do {
			scanned = false;
			for (int i = 1; i < ret.length(); i++) {
				if (isTerm2(ret.charAt(i)) && isTerm1(ret.charAt(i - 1))
						|| isTerm1(ret.charAt(i - 1)) && isTerm2(ret.charAt(i))) {
					ret = ret.substring(0, i) + "*" + ret.substring(i);
					scanned = true;
					break;
				}
			}
		} while (scanned);
		return ret;
	}

	public static double evaluer(String expression) {
		return evaluaterUtile(getMultiplicationImplicite(expression));
	}

	private static double evaluaterUtile(String expression) {
		while (possedeFonction(expression)) {
			String expressionDouble = expression;
			int start = 0;
			Stack<Integer> bracketStack = new Stack<Integer>();
			if (expression.indexOf("log") != -1)
				start = expression.indexOf("log");
			else if (expression.indexOf("sin") != -1)
				start = expression.indexOf("sin");
			else if (expression.indexOf("cos") != -1)
				start = expression.indexOf("cos");
			else if (expression.indexOf("tan") != -1)
				start = expression.indexOf("tan");
			else if (expression.indexOf("abs") != -1)
				start = expression.indexOf("abs");
			for (int i = start + 3; i < expression.length(); i++) {
				if (expression.charAt(i) == '(')
					bracketStack.push(i);
				else if (expression.charAt(i) == ')')
					bracketStack.pop();
				if (bracketStack.isEmpty()) {
					String expressionSimplifie = "";
					String function = expression.substring(start, start + 3);
					expressionSimplifie += expression.substring(0, start);
					switch (function) {
					case "sin":
						expressionSimplifie += Math.sin(evaluer(expression.substring(start + 4, i)));
						break;
					case "log":
						expressionSimplifie += Math.log10(evaluer(expression.substring(start + 4, i)));
						break;
					case "cos":
						expressionSimplifie += Math.cos(evaluer(expression.substring(start + 4, i)));
						break;
					case "tan":
						expressionSimplifie += Math.tan(evaluer(expression.substring(start + 4, i)));
						break;
					case "abs":
						expressionSimplifie += Math.abs(evaluer(expression.substring(start + 4, i)));
						break;
					}
					expressionSimplifie += expression.substring(i + 1);
					return evaluer(expressionSimplifie);
				}
			}
			if(expressionDouble.equals(expression)) {
				throw new NumberFormatException();
			}
		}
		return evaluaterPostfix(infixToPostfix(expression));
	}

}