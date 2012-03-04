import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CalculatorUI
{
	static JTextArea input;
	//static JTextArea output;
	static JTextArea memoryInput;
	static JTextArea memoryOutput;
	
	public static void startUI()
	{
		JFrame frame = new JFrame("JCalculator");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(500, 600);
		
		JMenu mode = new JMenu("Mode");
		ButtonGroup modeGroup = new ButtonGroup();
		mode.setMnemonic(KeyEvent.VK_M);
		JRadioButtonMenuItem scientific = new JRadioButtonMenuItem("Scientific");
		scientific.setSelected(true);
		JRadioButtonMenuItem graphing = new JRadioButtonMenuItem("Graphing");
		modeGroup.add(scientific);
		modeGroup.add(graphing);
		mode.add(scientific);
		mode.add(graphing);
		
		JMenu advancedMenu = new JMenu("Advanced");
		advancedMenu.setMnemonic(KeyEvent.VK_A);
		JMenu trig = new JMenu("Trig Functions");
		JMenuItem trigWindowMenuItem = new JMenuItem("Window");
		trigWindowMenuItem.addActionListener(new CalculatorMenu.trigFunctionsMenuOpened());
		JMenu functionMenuItem = new JMenu("Function");
		JMenu inverseFunctionMenuItem = new JMenu("Inverse");
		JMenuItem arcSinMenuItem = new JMenuItem("arcsin");
		JMenuItem arcCosMenuItem = new JMenuItem("arccos");
		JMenuItem arcTanMenuItem = new JMenuItem("arctan");
		inverseFunctionMenuItem.add(arcSinMenuItem);
		inverseFunctionMenuItem.add(arcCosMenuItem);
		inverseFunctionMenuItem.add(arcTanMenuItem);
		trig.add(trigWindowMenuItem);
		trig.addSeparator();
		trig.add(functionMenuItem);
		trig.add(inverseFunctionMenuItem);
		//trig.addActionListener(new CalculatorMenu.trigFunctionsMenuOpened());
		//probability
		JMenu probability = new JMenu("Probability");
		JMenuItem permutationMenuItem = new JMenuItem("nPr");
		JMenuItem combinationMenuItem = new JMenuItem("nCr");
		JMenuItem factorialMenuItem = new JMenuItem("!");
		probability.add(permutationMenuItem);
		probability.add(combinationMenuItem);
		probability.add(factorialMenuItem);
		//calculus
		JMenu calculus = new JMenu("Calculus");
		JMenu constants = new JMenu("Constants");
		JMenuItem constantsWindowMenuItem = new JMenuItem("Window");
		constantsWindowMenuItem.addActionListener(new CalculatorMenu.constantsMenuPressed());
		JMenuItem piMenuItem = new JMenuItem("£k");
		JMenuItem eMenuItem = new JMenuItem("e");
		JMenuItem phiMenuItem = new JMenuItem("£p");
		JMenuItem gMenuItem = new JMenuItem("g");
		JMenuItem gConstantMenuItem = new JMenuItem("G");
		constants.add(constantsWindowMenuItem);
		constants.addSeparator();
		constants.add(piMenuItem);
		constants.add(eMenuItem);
		constants.add(phiMenuItem);
		constants.add(gMenuItem);
		constants.add(gConstantMenuItem);
		//constants.addActionListener(new CalculatorMenu.constantsMenuPressed());
		
		advancedMenu.add(trig);
		advancedMenu.add(probability);
		advancedMenu.add(calculus);
		advancedMenu.add(constants);
		
		JMenu systemMenu = new JMenu("System");
		systemMenu.setMnemonic(KeyEvent.VK_S);
		JMenuItem memoryItem = new JMenuItem("Memory");
		memoryItem.addActionListener(new CalculatorMenu.memoryMenuSelected());
		JMenuItem settingsItem = new JMenuItem("Settings");
		systemMenu.add(memoryItem);
		systemMenu.add(settingsItem);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		JMenuItem help = new JMenuItem("Calc Help");
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new CalculatorMenu.aboutMenuSelected());
		helpMenu.add(help);
		helpMenu.add(about);
		
		JMenuBar menu = new JMenuBar();
		menu.add(mode);
		menu.add(advancedMenu);
		menu.add(systemMenu);
		menu.add(helpMenu);
		
		
		JButton num1 = new JButton("1");
		num1.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num2 = new JButton("2");
		num2.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num3 = new JButton("3");
		num3.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num4 = new JButton("4");
		num4.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num5 = new JButton("5");
		num5.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num6 = new JButton("6");
		num6.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num7 = new JButton("7");
		num7.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num8 = new JButton("8");
		num8.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num9 = new JButton("9");
		num9.addActionListener(new CalculatorOperations.buttonPressed());
		JButton num0 = new JButton("0");
		num0.addActionListener(new CalculatorOperations.buttonPressed());
		JButton addition = new JButton("+");
		addition.addActionListener(new CalculatorOperations.buttonPressed());
		JButton subtraction = new JButton("-");
		subtraction.addActionListener(new CalculatorOperations.buttonPressed());
		JButton multiplication = new JButton("*");
		multiplication.addActionListener(new CalculatorOperations.buttonPressed());
		JButton division = new JButton("/");
		division.addActionListener(new CalculatorOperations.buttonPressed());
		JButton equal = new JButton("=");
		equal.addActionListener(new CalculatorOperations.parseInput());
		JButton decimal = new JButton(".");
		decimal.addActionListener(new CalculatorOperations.buttonPressed());
		JButton negative = new JButton("(-)");
		negative.setMargin(new Insets(0, 6, 0, 6));
		negative.addActionListener(new CalculatorOperations.buttonPressed());
		JButton clear = new JButton("CLR");
		clear.setMargin(new Insets(2, 6, 2, 6));
		clear.addActionListener(new CalculatorOperations.clearScreen());
		JButton answer = new JButton("ANS");
		answer.setMargin(new Insets(2, 3, 2, 3));
		answer.addActionListener(new CalculatorOperations.selectAnswer());
		JButton delete = new JButton("DEL");
		delete.setMargin(new Insets(2, 6, 2, 6));
		delete.addActionListener(new CalculatorOperations.deleteSelection());
		JButton sin = new JButton("SIN");
		sin.setMargin(new Insets(2, 6, 2, 6));
		sin.addActionListener(new CalculatorOperations.sine());
		JButton cos = new JButton("COS");
		cos.setMargin(new Insets(2, 3, 2, 3));
		cos.addActionListener(new CalculatorOperations.cosine());
		JButton tan = new JButton("TAN");
		tan.setMargin(new Insets(2, 6, 2, 6));
		tan.addActionListener(new CalculatorOperations.tangent());
		JButton arcSin = new JButton("ASIN");
		arcSin.setMargin(new Insets(0, 5, 0, 5));
		arcSin.addActionListener(new CalculatorOperations.arcSine());
		JButton arcCos = new JButton("ACOS");
		arcCos.setMargin(new Insets(0, 0, 0, 0));
		arcCos.addActionListener(new CalculatorOperations.arcCosine());
		JButton arcTan = new JButton("ATAN");
		arcTan.setMargin(new Insets(0, 2, 0, 2));
		arcTan.addActionListener(new CalculatorOperations.arcTangent());
		JButton caret = new JButton("^");
		caret.addActionListener(new CalculatorOperations.buttonPressed());
		JButton squareRoot = new JButton("¡Ô");
		squareRoot.addActionListener(new CalculatorOperations.squareRootSign());
		JButton openParenthesis = new JButton("(");
		openParenthesis.addActionListener(new CalculatorOperations.buttonPressed());
		JButton closeParenthesis = new JButton(")");
		closeParenthesis.addActionListener(new CalculatorOperations.buttonPressed());
		JButton logarithm = new JButton("log");
		logarithm.setMargin(new Insets(0, 6, 0, 6));
		logarithm.addActionListener(new CalculatorOperations.logarithmButton());
		JButton naturalLog = new JButton("ln");
		naturalLog.setMargin(new Insets(0, 6, 0, 6));
		naturalLog.addActionListener(new CalculatorOperations.naturalLogButton());
		JButton base10 = new JButton("EE");
		base10.addActionListener(new CalculatorOperations.base10Button());
		JButton previousEquation = new JButton("EQN");
		previousEquation.setMargin(new Insets(2, 8, 2, 8));
		previousEquation.addActionListener(new CalculatorOperations.previousEquationButton());
		JButton pi = new JButton("£k");
		pi.addActionListener(new CalculatorOperations.piButton());
		JButton euler = new JButton("e");
		euler.addActionListener(new CalculatorOperations.eulerButton());
		JButton xVariable = new JButton("X");
		xVariable.addActionListener(new CalculatorOperations.buttonPressed());
		JButton comma = new JButton(",");
		comma.addActionListener(new CalculatorOperations.buttonPressed());
		JButton integrate = new JButton("¡ì");
		integrate.addActionListener(new CalculatorOperations.integralButton());
		JButton derive = new JButton("d/dx");
		derive.setMargin(new Insets(0, 0, 0, 0));
		derive.addActionListener(new CalculatorOperations.derivativeButton());
		JButton leftArrow = new JButton("¡ö");
		leftArrow.addActionListener(new CalculatorOperations.leftArrowButton());
		JButton rightArrow = new JButton("¡÷");
		rightArrow.addActionListener(new CalculatorOperations.rightArrowButton());
		
		
		
		input = new JTextArea(5, 25);
		input.setLineWrap(true);
		
		memoryInput = new JTextArea(3, 25);
		memoryInput.setLineWrap(true);
		memoryOutput = new JTextArea(1, 25);
		memoryOutput.setLineWrap(true);
		
		JPanel memoryPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		c1.insets = new Insets(2, 2, 2, 2);
		c1.gridy = 0;
		c1.gridx = 0;
		memoryPanel.add(memoryInput, c1);
		c1.gridy = 1;
		c1.gridx = 0;
		memoryPanel.add(memoryOutput, c1);
		memoryPanel.setBorder(BorderFactory.createTitledBorder("Output"));
		
		JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.add(input);
		inputPanel.setBorder(BorderFactory.createTitledBorder("Input"));
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(2, 2, 2, 2);
		c.gridx = 2;
		c.gridy = 6;
		panel.add(num1, c);
		c.gridx = 3;
		c.gridy = 6;
		panel.add(num2, c);
		c.gridx = 4;
		c.gridy = 6;
		panel.add(num3, c);
		c.gridx = 2;
		c.gridy = 7;
		panel.add(num4, c);
		c.gridx = 3;
		c.gridy = 7;
		panel.add(num5, c);
		c.gridx = 4;
		c.gridy = 7;
		panel.add(num6, c);
		c.gridx = 2;
		c.gridy = 8;
		panel.add(num7, c);
		c.gridx = 3;
		c.gridy = 8;
		panel.add(num8, c);
		c.gridx = 4;
		c.gridy = 8;
		panel.add(num9, c);
		c.gridx = 3;
		c.gridy = 9;
		panel.add(num0, c);
		c.gridx = 5;
		c.gridy = 5;
		panel.add(addition, c);
		c.gridx = 5;
		c.gridy = 6;
		panel.add(subtraction, c);
		c.gridx = 5;
		c.gridy = 7;
		panel.add(multiplication, c);
		c.gridx = 5;
		c.gridy = 8;
		panel.add(division, c);
		c.gridx = 5;
		c.gridy = 9;
		panel.add(equal, c);
		c.gridx = 4;
		c.gridy = 9;
		panel.add(decimal, c);
		c.gridx = 2;
		c.gridy = 9;
		panel.add(negative, c);
		c.gridx = 0;
		c.gridy = 3;
		panel.add(clear, c);
		c.gridx = 1;
		c.gridy = 3;
		panel.add(answer, c);
		c.gridx = 2;
		c.gridy = 3;
		panel.add(previousEquation, c);
		c.gridx = 3;
		c.gridy = 5;
		panel.add(openParenthesis, c);
		c.gridx = 4;
		c.gridy = 5;
		panel.add(closeParenthesis, c);
		c.gridx = 0;
		c.gridy = 4;
		panel.add(sin, c);
		c.gridx = 1;
		c.gridy = 4;
		panel.add(cos, c);
		c.gridx = 2;
		c.gridy = 4;
		panel.add(tan, c);
		c.gridx = 3;
		c.gridy = 4;
		panel.add(arcSin, c);
		c.gridx = 4;
		c.gridy = 4;
		panel.add(arcCos, c);
		c.gridx = 5;
		c.gridy = 4;
		panel.add(arcTan, c);
		c.gridx = 3;
		c.gridy = 3;
		panel.add(delete, c);
		c.gridx = 1;
		c.gridy = 5;
		panel.add(xVariable, c);
		c.gridx = 2;
		c.gridy = 5;
		panel.add(comma, c);
		c.gridx = 0;
		c.gridy = 6;
		panel.add(pi, c);
		c.gridx = 1;
		c.gridy = 6;
		panel.add(euler, c);
		c.gridx = 0;
		c.gridy = 7;
		panel.add(caret, c);
		c.gridx = 1;
		c.gridy = 7;
		panel.add(squareRoot, c);
		c.gridx = 0;
		c.gridy = 8;
		panel.add(logarithm, c);
		c.gridx = 1;
		c.gridy = 8;
		panel.add(naturalLog, c);
		c.gridx = 0;
		c.gridy = 5;
		panel.add(base10, c);
		c.gridx = 0;
		c.gridy = 9;
		panel.add(integrate, c);
		c.gridx = 1;
		c.gridy = 9;
		panel.add(derive, c);
		c.gridx = 4;
		c.gridy = 3;
		panel.add(leftArrow, c);
		c.gridx = 5;
		c.gridy = 3;
		panel.add(rightArrow, c);
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 8;
		panel.add(memoryPanel, c);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 8;
		panel.add(inputPanel, c);
		
		
		frame.setContentPane(panel);
		frame.setJMenuBar(menu);
		frame.pack();
	}
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
            public void run()
            {
            	startUI();
            }
        });

		//startUI();
		//System.out.println(new String(new int[]{8210, 51}, 0, 2));
		//System.out.println("¡X -");
		//System.out.println(0.1 + 0.2);
		//System.out.println("£k");
		String p = "asdxdsfjhkXsdf+/x";
		p = p.replace("x", "G");
		//System.out.println(p);

	}

	
	
}