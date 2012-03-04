import java.awt.*;
import java.awt.event.*; 
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;



public class CalculatorOperations
{
	static ArrayList<String> memory = new ArrayList<String>();
	
	static class parseInput implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String text = CalculatorUI.input.getText();
			//try
			//{
				double eqnTotal = calculate(text);
			
				String display = "";
				if(eqnTotal < 0)
				{
					display = "(-)" + Math.abs(eqnTotal);
				}
				else
				{
					display = "" + eqnTotal;
				}
				
				
				for(int i = 0; i < display.length(); i ++)
				{
					if(display.length() - 1 >= i + 2 && display.substring(i, i + 2).equals("E-"))
					{
						display = display.substring(0, i + 1) + "(-)" + display.substring(i + 2);
					}
				}
				
				memory.add(text);
				CalculatorUI.memoryInput.setText(text);
				CalculatorUI.memoryOutput.setText(display);
				CalculatorUI.input.setText("");
			//}
			//catch(Exception r)
			//{
				//CalculatorUI.output.setText("Syntax Error");
			//}
			//CalculatorUI.output.selectAll();
			//CalculatorUI.output.replaceSelection(totalText);
			
		}
		
		static double calculate(String equation)
		{
			
			//parse calculus
			for(int i = 0; i < equation.length(); i++)
			{
				//parse integrals
				if(equation.length() - 1 >= i + 1 && equation.substring(i, i + 2).equals("¡ì("))
				{
					int end = findParenthesis(equation.substring(i + 1), i + 1);
					int firstComma = 0;
					for(int c = i + 2; c < end; c++)
					{
						if(equation.charAt(c) == ',')
						{
							firstComma = c;
							c = end;
						}
					}
					System.out.println(firstComma);
					String integram = equation.substring(i + 2, firstComma);
					
					int secondComma = 0;
					for(int c = firstComma + 1; c < end; c++)
					{
						if(equation.charAt(c) == ',')
						{
							secondComma = c;
							c = end;
						}
					}
					System.out.println(secondComma);
					String variable = equation.substring(firstComma + 1, secondComma);
					
					int thirdComma = 0;
					for(int c = secondComma + 1; c < end; c++)
					{
						if(equation.charAt(c) == ',')
						{
							thirdComma = c;
							c = end;
						}
					}
					System.out.println(thirdComma);
					String lowerLimit = equation.substring(secondComma + 1, thirdComma);
					String upperLimit = equation.substring(thirdComma + 1, end);
					
					double lowerLim = Double.parseDouble(lowerLimit);
					double upperLim = Double.parseDouble(upperLimit);
					
					double sum = 0;
					for(double inc = lowerLim; inc <= upperLim; inc+= CalculatorMenu.deltaX)
					{
						sum += parseEquation(integram, inc) * CalculatorMenu.deltaX;
					}
					System.out.println("sum: " + sum);
					
					String strSum = adjustNum(sum);
					
					equation = equation.substring(0, i) + strSum + equation.substring(end + 1);
				}
				//parse derivatives
				else if(equation.length() - 1 >= i + 4 && equation.substring(i, i + 5).equals("d/dx("))
				{
					int end = findParenthesis(equation.substring(i + 4), i + 4);
					int firstComma = 0;
					
					for(int c = i + 5; c < end; c++)
					{
						if(equation.charAt(c) == ',')
						{
							firstComma = c;
							c = end;
						}
					}
					String function = equation.substring(i + 5, firstComma);
					
					int secondComma = 0;
					for(int c = firstComma + 1; c < end; c++)
					{
						if(equation.charAt(c) == ',')
						{
							secondComma = c;
							c = end;
						}
					}
					String variable = equation.substring(firstComma + 1, secondComma);
					
					double xValue = Double.parseDouble(equation.substring(secondComma + 1, end));
					
					double fsub1 = xValue - CalculatorMenu.dX;
					String strfsub1 = adjustNum(fsub1);			
					double fsub2 = xValue + CalculatorMenu.dX;
					String strfsub2 = adjustNum(fsub2);
					
					String func1 = function.replace("X", "(" + strfsub1 + ")");
					String func2 = function.replace("X", "(" + strfsub2 + ")");
					double fLower = calculate(func1);
					double fUpper = calculate(func2);
					
					double derivative = (fLower - fUpper)/(fsub1 - fsub2);
					String strDerivative = adjustNum(derivative);
					
					equation = equation.substring(0, i) + strDerivative + equation.substring(end + 1);
				}
			}
			
			
			//parse logarithms
			for(int x = 0; x < equation.length(); x++)
			{
				if(equation.length() - 1 >= x + 4 && equation.substring(x, x + 4).equals("log("))
				{
					int end = findParenthesis(equation.substring(x + 3), x + 3);
					double arg = calculate(equation.substring(x + 4, end));
					double calc = Math.log10(arg);
					
					equation = adjustEqn(equation, calc, x, end);
				}
				else if(equation.length() - 1 >= x + 3 && equation.substring(x, x + 3).equals("ln("))
				{
					int end = findParenthesis(equation.substring(x + 2), x + 2);
					double arg = calculate(equation.substring(x + 3, end));
					double calc = Math.log(arg);
					
					equation = adjustEqn(equation, calc, x, end);
				}
			}
			
			
			//parse trig functions
			for(int t = 0; t < equation.length(); t++)
			{
				if(equation. length() - 1 >= t + 3 && equation.substring(t, t + 3).equals("sin"))
				{
					int end = findParenthesis(equation.substring(t + 3), t + 3);
					double argument = calculate(equation.substring(t + 4, end));
					double calc = 0;
					if(CalculatorMenu.radians)
					{
						calc = Math.sin(argument);
					}
					else if(!CalculatorMenu.radians)
					{
						calc = Math.sin(2 * Math.PI * argument / 360);
					}
					
					if(calc <= 1E-13 && calc >= -1E-13)
					{
						calc = 0;
					}
					
					equation = adjustEqn(equation, calc, t, end);
					
				}
				else if(equation. length() - 1 >= t + 3 && equation.substring(t, t + 3).equals("cos"))
				{
					int end = findParenthesis(equation.substring(t + 3), t + 3);
					double argument = calculate(equation.substring(t + 4, end));
					double calc = 0;
					if(CalculatorMenu.radians)
					{
						calc = Math.cos(argument);
					}
					else if(!CalculatorMenu.radians)
					{
						calc = Math.cos(2 * Math.PI * argument / 360);
					}
					
					if(calc <= 1E-13 && calc >= -1E-13)
					{
						calc = 0;
					}
					
					equation = adjustEqn(equation, calc, t, end);
					
				}
				else if(equation. length() - 1 >= t + 3 && equation.substring(t, t + 3).equals("tan"))
				{
					int end = findParenthesis(equation.substring(t + 3), t + 3);
					double argument = calculate(equation.substring(t + 4, end));
					double calc = 0;
					if(CalculatorMenu.radians)
					{
						calc = Math.tan(argument);
					}
					else if(!CalculatorMenu.radians)
					{
						calc = Math.tan(2 * Math.PI * argument / 360);
					}
					
					if(calc <= 1E-13 && calc >= -1E-13)
					{
						calc = 0;
					}
					
					equation = adjustEqn(equation, calc, t, end);
					
				}
				else if(equation. length() - 1 >= t + 6 && equation.substring(t, t + 6).equals("arcsin"))
				{
					int end = findParenthesis(equation.substring(t + 6), t + 6);
					double argument = calculate(equation.substring(t + 7, end));
					double calc = 0;
					if(CalculatorMenu.radians)
					{
						calc = Math.asin(argument);
					}
					else if(!CalculatorMenu.radians)
					{
						calc = Math.asin(argument) * 360 / (2 * Math.PI);
					}
					
					if(calc <= 1E-13 && calc >= -1E-13)
					{
						calc = 0;
					}
					
					equation = adjustEqn(equation, calc, t, end);
					
				}
				else if(equation. length() - 1 >= t + 6 && equation.substring(t, t + 6).equals("arccos"))
				{
					int end = findParenthesis(equation.substring(t + 6), t + 6);
					double argument = calculate(equation.substring(t + 7, end));
					double calc = 0;
					if(CalculatorMenu.radians)
					{
						calc = Math.acos(argument);
					}
					else if(!CalculatorMenu.radians)
					{
						calc = Math.acos(argument) * 360 / (2 * Math.PI);
					}
					
					if(calc <= 1E-13 && calc >= -1E-13)
					{
						calc = 0;
					}
					
					equation = adjustEqn(equation, calc, t, end);
					
				}
				else if(equation. length() - 1 >= t + 6 && equation.substring(t, t + 6).equals("arctan"))
				{
					int end = findParenthesis(equation.substring(t + 6), t + 6);
					double argument = calculate(equation.substring(t + 7, end));
					double calc = 0;
					if(CalculatorMenu.radians)
					{
						calc = Math.atan(argument);
					}
					else if(!CalculatorMenu.radians)
					{
						calc = Math.atan(argument) * 360 / (2 * Math.PI);
					}
					
					if(calc <= 1E-13 && calc >= -1E-13)
					{
						calc = 0;
					}
					
					equation = adjustEqn(equation, calc, t, end);
					
				}
			}
			
			
			//Parse constants
			for(int v = 0; v < equation.length(); v++)
			{
				
				if(equation.charAt(v) == '[')
				{
					if(v - 1 >= 0 && Character.isDigit(equation.charAt(v - 1)) )
					{
						equation = equation.substring(0, v) + "*" + equation.substring(v);
						v++;
					}
					if( equation.length() - 1 >= v + 3 && Character.isDigit(equation.charAt(v + 3)) )
					{
						equation = equation.substring(0, v + 3) + "*" + equation.substring(v + 3);
					}
					
					
					if(equation.charAt(v+1) == 'e')
					{
						equation = equation.substring(0, v) + Math.E 
							+ equation.substring(v + 3);
					}
					else if(equation.charAt(v+1) == 'g')
					{
						equation = equation.substring(0, v) + "9.80665" 
							+ equation.substring(v + 3);
					}
					else if(equation.charAt(v+1) == 'G')
					{
						equation = equation.substring(0, v) + "6.67428*10^(-)11" 
							+ equation.substring(v + 3);
					}
					else if(equation.charAt(v+1) == 'k')
					{
						equation = equation.substring(0, v) + "8.9875517873681764*10^9" 
							+ equation.substring(v + 3);
					}
					else if(equation.charAt(v+1) == 'K')
					{
						equation = equation.substring(0, v) + "1.3806504*10^(-)23" 
							+ equation.substring(v + 3);
					}
					else if(equation.charAt(v+1) == 'h')
					{
						equation = equation.substring(0, v) + "6.62606896*10^(-)34" 
							+ equation.substring(v + 3);
					}
					else if(equation.substring(v+1, v+2).equals("£k"))
					{
						equation = equation.substring(0, v) + Math.PI 
							+ equation.substring(v + 3);
					}
					else if(equation.substring(v+1, v+4).equals("phi"))
					{
						equation = equation.substring(0, v) + "1.618033988749895" 
							+ equation.substring(v + 5);
					}
				}
			}
			
			//Parse parenthesis
			for(int p = 0; p < equation.length(); p++)
			{
				if(equation.charAt(p) == '(' && equation.charAt(p + 1) != '-')
				{
					int end = findParenthesis(equation.substring(p), p);
					//System.out.println("end: " + end);
					double calc = calculate(equation.substring(p + 1, end));
					String beg = equation.substring(0, p);
					String endd = equation.substring(end + 1, equation.length());
					
					System.out.println("EQQN: " + equation);
					System.out.println("p: " + p);
					System.out.println("end: " + end);
					System.out.println("beg: " + beg);
					System.out.println("endd: " + endd);
					
					if(p - 1 >= 0 && Character.isDigit(equation.charAt(p - 1)) )
					{
						equation = beg + "*" + equation.substring(p, end + 1) + endd;
						p++;
						end++;
						beg = equation.substring(0, p);
					}
					if(equation.length() - 1 >= end + 1 && Character.isDigit(equation.charAt(end + 1)) )
					{
						equation = beg + equation.substring(p, end + 1) + "*" + endd;
					}
					
					System.out.println("EQQN " + equation);
					
					if(equation.length() - 1 >= end + 1 && equation.charAt(end + 1) == '^' && calc < 0)
					{
						beg = equation.substring(0, p + 1);
						endd = equation.substring(end, equation.length());
					}
					else
					{
						beg = equation.substring(0, p);
						endd = equation.substring(end + 1, equation.length());
					}
					//System.out.println("calc: " + calc);
					
					String strCalc = "";
					if(calc < 0)
					{
						strCalc = "(-)" + Math.abs(calc);
						
						//calc *= -1;
						//equation = beg + "(-)" + calc + endd;
					}
					else
					{
						strCalc = "" + calc;
						//equation = beg + calc + endd;
					}
					for(int d = 0; d < strCalc.length(); d ++)
					{
						if(strCalc.length() - 1 >= d + 2 && strCalc.substring(d, d + 2).equals("E-"))
						{
							strCalc = strCalc.substring(0, d + 1) + "(-)" + strCalc.substring(d + 2);
						}
					}
					
					equation = beg + strCalc + endd;
					
					
					
				}
			}
			System.out.println(equation);
			
			
			//parse carets
			for(int index = 0; index <= equation.length() - 1; index++)
			{
				if(equation.charAt(index) == '^')
				{
					//FIRST NUM
					String firstTerm = "";
					int first = index - 1;
					double firstNum = 0;
					boolean loop = true;
					boolean isNegative = false;
					boolean isNegExp = false;

					for(; first >= 0 && loop;)
					{
						if(!Character.isDigit(equation.charAt(first)) && equation.charAt(first) != '.')
						{
							if(first == index - 1 && equation.charAt(first) == ')')
							{
								System.out.println("fff");
								first--;
								System.out.println("first" + first);
							}
							//System.out.println(equation.charAt(first));
							else if(first >= 4 && equation.substring(first - 3, first + 1).equals("E(-)"))
							{
								first = first - 4;
								isNegExp = true;
							}
							else if(equation.charAt(first) == 'E')
							{
								first--;
							}
							else if(first >= 3 && equation.substring(first - 3, first + 1).equals("((-)") && equation.charAt(index - 1) == ')')
							{
								System.out.println("first " + first);
								isNegative = true;
								firstTerm = equation.substring(first - 2, index - 1);
								first = first - 3;
								loop = false;
							}
							else
							{
								firstTerm = equation.substring(first + 1, index);
								first = first + 1;
								loop = false;
							}
						}
						else if(first != 0)
						{
							first--;
						}
						
						if(first == 0 && !isNegative)
						{
							firstTerm = equation.substring(0, index);
							first = 0;
							loop = false;
						}
					}

					if(isNegExp)
					{
						int indexOfE = firstTerm.indexOf("E(-)");
						firstTerm = firstTerm.substring(0, indexOfE ) + "E-" + firstTerm.substring(indexOfE  + 4);
					}

					if(isNegative)
					{
						System.out.println("hh");
						System.out.println(firstTerm);
						System.out.println(first);
						firstTerm = "-" + firstTerm.substring(3, firstTerm.length());
						System.out.println(firstTerm);
					}
					
					firstNum = Double.parseDouble(firstTerm);
					
					isNegative = false;
					isNegExp = false;
					loop = true;
					
					//SECOND NUM
					
					String secondTerm = "";
					int second = index + 1;
					double secondNum = 0;
					
					if(equation.length() - 1 >= index + 4 && equation.substring(index + 1, index + 4).equals("(-)"))
					{
						second = second + 3;
						isNegative = true;
					}
					
					for(; second <= equation.length() - 1 && loop;)
					{
						if(!Character.isDigit(equation.charAt(second)) && equation.charAt(second) != '.')
						{
							if(equation.length() - 1 >= second + 3 && equation.substring(second, second + 4).equals("E(-)"))
							{
								second = second + 4;
								isNegExp = true;
							}
							else if(equation.charAt(second) == 'E')
							{
								second++;
							}
							else
							{
								secondTerm = equation.substring(index + 1, second);
								//second = second;
								loop = false;
							}
						}
						else if(second != equation.length() - 1)
						{
							second++;
						}
						
						if(second == equation.length() - 1)
						{
							secondTerm = equation.substring(index + 1, equation.length());
							second = equation.length();
							loop = false;
						}
					}
					
					if(isNegative)
					{
						secondTerm = "-" + secondTerm.substring(3);
					}
					
					if(isNegExp)
					{
						if(isNegative)
						{
							int indexOfE = secondTerm.indexOf("E(-)");
							secondTerm = secondTerm.substring(1, indexOfE ) + "E-" + equation.substring(indexOfE  + 4);
						}
						else
						{
							int indexOfE = secondTerm.indexOf("E(-)");
							secondTerm = secondTerm.substring(0, indexOfE ) + "E-" + secondTerm.substring(indexOfE  + 4);
						}
					}
					
					secondNum = Double.parseDouble(secondTerm);
					
					//RAISING THE POWER
					double answer = Math.pow(firstNum, secondNum);
					String ans = "";
					
					if(answer < 0)
					{
						ans = "(-)" + Math.abs(answer);
					}
					else
					{
						ans = "" + answer;
					}
					
					equation = equation.substring(0, first) + ans + equation.substring(second, equation.length());
					index = 0;
					
				}
			}
			equation = equation.replace("E-", "E(-)");
			
			
			System.out.println("EQ: " + equation);
			//parse Multiplication and Division
			for(int index = 0; index <= equation.length() - 1; index++)
			{
				//MULTIPLICATION
				if(equation.charAt(index) == '*')
				{
					double[] firstN = parseFirstNum(equation, index);
					double[] secondN = parseSecondNum(equation, index);
					
					double product = firstN[0] * secondN[0];
					
					String prd = "";
					if(product < 0)
					{
						prd = "(-)" + Math.abs(product);
					}
					else
					{
						prd = "" + product;
					}
					
					equation = equation.substring(0, (int)firstN[1]) + prd + equation.substring((int)secondN[1], equation.length());
					index = 0;
				}
				
				//DIVISION
				if(equation.charAt(index) == '/')
				{
					double[] firstN = parseFirstNum(equation, index);
					double[] secondN = parseSecondNum(equation, index);
					
					double quotient = firstN[0] / secondN[0];
					
					String quot = "";
					if(quotient < 0)
					{
						quot = "(-)" + Math.abs(quotient);
					}
					else
					{
						quot = "" + quotient;
					}
					
					equation = equation.substring(0, (int)firstN[1]) + quot + equation.substring((int)secondN[1], equation.length());
					index = 0;
				}
				
			}
			
			
			for(;equation.contains("(-)(-)");)
			{
				String p1 = equation.substring(0, equation.indexOf("(-)(-)"));
				String p2 = equation.substring(equation.indexOf("(-)(-)") + 6);
				equation = p1 + p2;
			}
			for(;equation.contains("-(-)");)
			{
				String p1 = equation.substring(0, equation.indexOf("-(-)"));
				String p2 = equation.substring(equation.indexOf("-(-)") + 4);
				equation = p1 + "+" + p2;
			}
			for(;equation.contains("+(-)");)
			{
				String p1 = equation.substring(0, equation.indexOf("+(-)"));
				String p2 = equation.substring(equation.indexOf("+(-)") + 4);
				equation = p1 + "-" + p2;
			}
			
			
			//parse Addition and Subtraction
			for(int index = 0; index <= equation.length() - 1; index++)
			{
				//ADDITION
				if(equation.charAt(index) == '+')
				{
					double[] firstN = parseFirstNum(equation, index);
					double[] secondN = parseSecondNum(equation, index);
					
					double sum = firstN[0] + secondN[0];
					
					String sm = "";
					if(sum < 0)
					{
						sm = "(-)" + Math.abs(sum);
					}
					else
					{
						sm = "" + sum;
					}
					
					equation = equation.substring(0, (int)firstN[1]) + sm + equation.substring((int)secondN[1], equation.length());
					index = 0;
				}
				
				//SUBTRACTION
				if(equation.charAt(index) == '-' 
					&& Character.isDigit(equation.charAt(index - 1)) && Character.isDigit(equation.charAt(index + 1)) )
				{
					double[] firstN = parseFirstNum(equation, index);
					double[] secondN = parseSecondNum(equation, index);
					System.out.println(firstN[0]);
					System.out.println(secondN[0]);
					
					double difference = firstN[0] - secondN[0];
					
					String diff = "";
					if(difference < 0)
					{
						diff = "(-)" + Math.abs(difference);
					}
					else
					{
						diff = "" + difference;
					}
					
					equation = equation.substring(0, (int)firstN[1]) + diff + equation.substring((int)secondN[1], equation.length());
					index = 0;
				}
			}
			
			
			System.out.println("EQN: " + equation);
			
			for(int i = 0; i < equation.length(); i ++)
			{
				if(i <= equation.length() - 5 && equation.substring(i, i + 4).equals("E(-)"))
				{
					equation = equation.substring(0, i + 1) + "-" + equation.substring(i + 4);
				}
			}
			
			if(equation.length() >= 4 && equation.substring(0, 3).equals("(-)"))
			{
				return Double.parseDouble("-" + equation.substring(3));
			}
			else
			{
				return Double.parseDouble(equation);
			}
			
		}
		
		static int findParenthesis(String str, int startIndex)
		{
			int endIndex = 0;
			int beginP = 0;
			for(int y = 1; y < str.length(); y++)
			{
				if(str.charAt(y) == '(')
				{
					beginP++;
				}
				if(str.charAt(y) == ')' && beginP == 0)
				{
					endIndex = y;
					return y + startIndex;
				}
				else if(str.charAt(y) == ')' && beginP > 0)
				{
					beginP--;
				}
			}
			
			return endIndex;
		}
		
		static double[] parseFirstNum(String term, int index)
		{
			String firstTerm = "";
			int first = index - 1;
			double firstNum = 0;
			boolean loop = true;
			boolean isNegative = false;
			boolean isNegExp = false;
			
			for(; first >= 0 && loop;)
			{
				if(!Character.isDigit(term.charAt(first)) && term.charAt(first) != '.')
				{
					if(first >= 4 && term.substring(first - 3, first + 1).equals("E(-)"))
					{
						first = first - 4;
						isNegExp = true;
					}
					else if(term.charAt(first) == 'E')
					{
						first--;
					}
					else if(first >= 2 && term.substring(first - 2, first + 1).equals("(-)"))
					{
						isNegative = true;
						firstTerm = term.substring(first - 2, index);
						first = first - 2;
						loop = false;
					}
					else
					{
						firstTerm = term.substring(first + 1, index);
						first = first + 1;
						loop = false;
					}
				}
				else if(first != 0)
				{
					first--;
				}
				
				if(first == 0)
				{
					firstTerm = term.substring(0, index);
					first = 0;
					loop = false;
				}
			}
			
			if(isNegExp)
			{
				int indexOfE = firstTerm.indexOf("E(-)");
				firstTerm = firstTerm.substring(0, indexOfE ) + "E-" + firstTerm.substring(indexOfE  + 4);
			}
			
			if(isNegative)
			{
				firstTerm = "-" + firstTerm.substring(3);
			}
			
			firstNum = Double.parseDouble(firstTerm);
			
			double[] arr1 = {firstNum, first};
			
			return arr1;
			
		}
		
		static double[] parseSecondNum(String term, int index)
		{
			String secondTerm = "";
			int second = index + 1;
			double secondNum = 0;
			boolean loop = true;
			boolean isNegative = false;
			boolean isNegExp = false;
			
			if(term.length() - 1 >= index + 4 && term.substring(index + 1, index + 4).equals("(-)"))
			{
				second = second + 3;
				isNegative = true;
			}
			
			for(; second <= term.length() - 1 && loop;)
			{
				if(!Character.isDigit(term.charAt(second)) && term.charAt(second) != '.')
				{
					if(term.length() - 1 >= second + 3 && term.substring(second, second + 4).equals("E(-)"))
					{
						second = second + 4;
						isNegExp = true;
					}
					else if(term.charAt(second) == 'E')
					{
						second++;
					}
					else
					{
						secondTerm = term.substring(index + 1, second);
						//second = second;
						loop = false;
					}
				}
				else if(second != term.length() - 1)
				{
					second++;
				}
				
				if(second == term.length() - 1)
				{
					secondTerm = term.substring(index + 1, term.length());
					second = term.length();
					loop = false;
				}
			}
			
			if(isNegative)
			{
				secondTerm = "-" + secondTerm.substring(3);
			}
			
			if(isNegExp)
			{
				if(isNegative)
				{
					int indexOfE = secondTerm.indexOf("E(-)");
					secondTerm = secondTerm.substring(1, indexOfE ) + "E-" + term.substring(indexOfE  + 4);
				}
				else
				{
					int indexOfE = secondTerm.indexOf("E(-)");
					secondTerm = secondTerm.substring(0, indexOfE ) + "E-" + secondTerm.substring(indexOfE  + 4);
				}
			}
			
			secondNum = Double.parseDouble(secondTerm);
			
			double[] arr2 = {secondNum, second};
			
			return arr2;
		}
		
		static String adjustEqn(String eqn, double num, int start, int ending)
		{

			if(start - 1 >= 0 && Character.isDigit(eqn.charAt(start - 1)) )
			{
				eqn = eqn.substring(0, start) + "*" + eqn.substring(start, eqn.length());
				start++;
				ending++;
			}
			if(eqn.length() - 1 >= ending + 1 && Character.isDigit(eqn.charAt(ending + 1)))
			{
				eqn = eqn.substring(0, ending + 1) + "*" + eqn.substring(ending + 1);
			}

			if(eqn.length() - 1 >= ending + 1 && eqn.charAt(ending + 1) == '^' && num < 0)
			{
				eqn = eqn.substring(0, start) + "(" + num + ")" + eqn.substring(ending + 1);
			}
			else if(num < 0)
			{
				eqn = eqn.substring(0, start) + "(-)" + Math.abs(num) + eqn.substring(ending + 1);
			}
			else
			{
				eqn = eqn.substring(0, start) + num + eqn.substring(ending + 1);
			}
			
			return eqn;
			
		}
		
		static String adjustNum(double num)
		{
			String number = "";
			
			if(num < 0)
			{
				number = "(-)" + Math.abs(num);
			}
			else
			{
				number = "" + num;
			}
			for(int d = 0; d < number.length(); d ++)
			{
				if(number.length() - 1 >= d + 2 && number.substring(d, d + 2).equals("E-"))
				{
					number = number.substring(0, d + 1) + "(-)" + number.substring(d + 2);
				}
			}
			
			return number;
		}
		
		static double parseEquation(String function, double inc )
		{
			String delta = "" + inc;
			for(int d = 0; d < delta.length(); d ++)
			{
				if(delta.length() - 1 >= d + 2 && delta.substring(d, d + 2).equals("E-"))
				{
					delta = delta.substring(0, d + 1) + "(-)" + delta.substring(d + 2);
				}
			}
			System.out.println("DELTA: " + delta);
			
			function = function.replace("X", "(" + delta + ")");
			
			return calculate(function);
		}
		
	}
	
	
	
	class caretListenerK implements CaretListener
	{
		public void caretUpdate(CaretEvent ce) {
			ce.getDot();
			ce.getMark();
		}
		
	}
		
	static class buttonPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String text = ((JButton) e.getSource()).getText();
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange(text, mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange(text, dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
			
		}
		
	}
	
	static class sine implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//CalculatorUI.input.append("sin(");
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("sin(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("sin(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	static class cosine implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//CalculatorUI.input.append("cos(");
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("cos(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("cos(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	static class tangent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//CalculatorUI.input.append("tan(");
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("tan(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("tan(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	static class arcSine implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("arcsin(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("arcsin(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
			
		}
		
	}
	static class arcCosine implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("arccos(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("arccos(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
			
		}
		
	}
	static class arcTangent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("arctan(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("arctan(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
			
		}
		
	}
	
	static class base10Button implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("E", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("E", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
			
		}
		
	}
	
	static class squareRootSign implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//CalculatorUI.input.append("sqrt(");
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("sqrt(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("sqrt(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	
	static class logarithmButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//CalculatorUI.input.append("log(");
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("log(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("log(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	static class naturalLogButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//CalculatorUI.input.append("ln(");
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("ln(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("ln(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	
	static class piButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("[£k]", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("[£k]", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
	}
	static class eulerButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("[e]", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("[e]", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
	}
	
	static class integralButton implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("¡ì(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("¡ì(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
	}
	static class derivativeButton implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("d/dx(", mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange("d/dx(", dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
	}
	
	static class clearScreen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CalculatorUI.input.selectAll();
			CalculatorUI.input.replaceSelection("");
			//CalculatorUI.output.selectAll();
			//CalculatorUI.output.replaceSelection("");
			
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	static class selectAnswer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//CalculatorUI.input.append(CalculatorUI.memoryOutput.getText());
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			String ans = CalculatorUI.memoryOutput.getText();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange(ans, mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange(ans, dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	static class deleteSelection implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//CalculatorUI.input.replaceRange("", CalculatorUI.input.getText().length() - 1, CalculatorUI.input.getText().length());
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange("", mark, dot);
			}
			else if(dot < mark)
			{
				CalculatorUI.input.replaceRange("", dot, mark);
			}
			else if(dot == mark)
			{
				CalculatorUI.input.replaceRange("", dot - 1, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	static class previousEquationButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String previousEqn = memory.get(memory.size() - 1);
			
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			if(dot > mark)
			{
				CalculatorUI.input.replaceRange(previousEqn, mark, dot);
			}
			else if(dot <= mark)
			{
				CalculatorUI.input.replaceRange(previousEqn, dot, mark);
			}
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	
	static class leftArrowButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			
			CalculatorUI.input.getCaret().setDot(dot - 1);
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
	static class rightArrowButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int dot = CalculatorUI.input.getCaret().getDot();
			int mark = CalculatorUI.input.getCaret().getMark();
			
			CalculatorUI.input.getCaret().setDot(dot + 1);
			CalculatorUI.input.requestFocusInWindow();
		}
		
	}
		
}