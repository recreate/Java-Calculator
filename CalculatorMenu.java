import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorMenu
{
	static boolean radians = true;
	static double deltaX = 1E-4;
	static double dX = 1E-10;
	
	
	static class memoryMenuSelected implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFrame memoryFrame = new JFrame("Memory");
			memoryFrame.setVisible(true);
			memoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			memoryFrame.setSize(320, 200);
			
			JPanel memoryPane = new JPanel(new GridBagLayout());
			
			JScrollPane scrollPane = new JScrollPane(memoryPane);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 0, 2, 0);
			
			for(int g = 0; g < CalculatorOperations.memory.size(); g++)
			{
				c.gridy = g;
				memoryPane.add(new JTextArea(CalculatorOperations.memory.get(g), 1, 25), c);
			}
			
			memoryFrame.setContentPane(scrollPane);
		}
	}
	
	static class aboutMenuSelected implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame aboutFrame = new JFrame("About");
			aboutFrame.setVisible(true);
			aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			JPanel aboutPanel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 2, 2, 2);
			
			JLabel info1 = new JLabel("JCalculator");
			c.gridy = 0;
			aboutPanel.add(info1, c);
			JLabel info2 = new JLabel("V1.2.1");
			c.gridy = 1;
			aboutPanel.add(info2, c);
			JLabel info3 = new JLabel("Created by William Tam");
			c.gridy = 2;
			aboutPanel.add(info3, c);
			JLabel info4 = new JLabel("2010");
			c.gridy = 3;
			aboutPanel.add(info4, c);
			
			aboutFrame.add(aboutPanel);
			aboutFrame.pack();
			
		}
	}
	
	static class constantsMenuPressed implements ActionListener{
		static ButtonGroup bgroup = new ButtonGroup();
		public void actionPerformed(ActionEvent e){
			JFrame constantsFrame = new JFrame("Constants");
			constantsFrame.setVisible(true);
			constantsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			constantsFrame.setLocation(500, 0);
			
			JRadioButton selectNumerical = new JRadioButton("Num");
			selectNumerical.setMnemonic(KeyEvent.VK_N);
			JRadioButton selectVariable = new JRadioButton("Var");
			selectVariable.setMnemonic(KeyEvent.VK_V);
			
			bgroup.add(selectNumerical);
			bgroup.add(selectVariable);
			selectVariable.setSelected(true);
			
			
			//selectNumerical.addActionListener(this);
			//selectVariable.addActionListener(this);
			
			JLabel mathematicalLabel = new JLabel("Math");
			JLabel physicsLabel = new JLabel("Physics");
			
			JButton piButton = new JButton("£k");
			piButton.setMargin(new Insets(2, 12, 2, 12));
			piButton.addActionListener(new piConstant());
			JButton eButton = new JButton("e");
			eButton.setMargin(new Insets(2, 12, 2, 12));
			eButton.addActionListener(new eConstant());
			JButton phiButton = new JButton("£p");
			phiButton.setMargin(new Insets(2, 12, 2, 12));
			phiButton.addActionListener(new phiConstant());
			JButton gravityButton = new JButton("g");
			gravityButton.addActionListener(new gConstant());
			JButton gravitationalConstantButton = new JButton("G");
			gravitationalConstantButton.addActionListener(new bigGConstant());
			JButton coulumbConstantButton = new JButton("k");
			coulumbConstantButton.addActionListener(new kConstant());
			JButton boltzmannConstantButton = new JButton("K");
			boltzmannConstantButton.addActionListener(new bigKConstant());
			JButton planckConstantButton = new JButton("h");
			planckConstantButton.addActionListener(new hConstant());
			
			JPanel constantsPane = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 2, 2, 2);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			constantsPane.add(selectVariable, c);
			c.gridx = 1;
			c.gridy = 0;
			constantsPane.add(selectNumerical, c);
			c.gridx = 0;
			c.gridy = 2;
			constantsPane.add(mathematicalLabel, c);
			c.gridx = 2;
			c.gridy = 2;
			constantsPane.add(piButton, c);
			c.gridx = 3;
			c.gridy = 2;
			constantsPane.add(eButton, c);
			c.gridx = 4;
			c.gridy = 2;
			constantsPane.add(phiButton, c);
			c.gridx = 0;
			c.gridy = 4;
			constantsPane.add(physicsLabel, c);
			c.gridx = 2;
			c.gridy = 4;
			constantsPane.add(gravityButton, c);
			c.gridx = 3;
			c.gridy = 4;
			constantsPane.add(gravitationalConstantButton, c);
			c.gridx = 4;
			c.gridy = 4;
			constantsPane.add(coulumbConstantButton, c);
			c.gridx = 5;
			c.gridy = 4;
			constantsPane.add(boltzmannConstantButton, c);
			c.gridx = 6;
			c.gridy = 4;
			constantsPane.add(planckConstantButton, c);
			
			constantsFrame.setContentPane(constantsPane);
			constantsFrame.pack();
		}
		
		static class piConstant implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.out.println(bgroup.getSelection().getMnemonic());
				if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_N)
				{
					CalculatorUI.input.append("" + Math.PI);
				}
				else if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_V)
				{
					CalculatorUI.input.append("[pi]");
				}

			}
			
		}
		static class eConstant implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_N)
				{
					CalculatorUI.input.append("" + Math.E);
				}
				else if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_V)
				{
					CalculatorUI.input.append("[e]");
				}

			}
			
		}
		static class phiConstant implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_N)
				{
					CalculatorUI.input.append("1.618033988749895");
				}
				else if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_V)
				{
					CalculatorUI.input.append("[phi]");
				}

			}
			
		}
		static class gConstant implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_N)
				{
					CalculatorUI.input.append("9.80665");
				}
				else if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_V)
				{
					CalculatorUI.input.append("[g]");
				}

			}
			
		}
		static class bigGConstant implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_N)
				{
					CalculatorUI.input.append("6.67428*10^(-)11");
				}
				else if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_V)
				{
					CalculatorUI.input.append("[G]");
				}

			}
			
		}
		static class kConstant implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_N)
				{
					CalculatorUI.input.append("8.9875517873681764*10^9");
				}
				else if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_V)
				{
					CalculatorUI.input.append("[k]");
				}

			}
			
		}
		static class bigKConstant implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_N)
				{
					CalculatorUI.input.append("1.3806504*10^(-)23");
				}
				else if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_V)
				{
					CalculatorUI.input.append("[K]");
				}

			}
			
		}
		static class hConstant implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_N)
				{
					CalculatorUI.input.append("6.62606896*10^(-)34");
				}
				else if(bgroup.getSelection().getMnemonic() == KeyEvent.VK_V)
				{
					CalculatorUI.input.append("[h]");
				}

			}
			
		}
		
	}
	
	static class trigFunctionsMenuOpened implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFrame trigFunc = new JFrame("Trig Functions");
			trigFunc.setVisible(true);
			trigFunc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JPanel trigFuncPanel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2, 2, 2, 2);
			
			/*
			c.gridx = 0;
			c.gridy = 0;
			JButton sineB = new JButton("SIN");
			c.gridx = 0;
			c.gridy = 0;
			JButton cosineB = new JButton("COS");
			c.gridx = 0;
			c.gridy = 0;
			JButton cosecant = new JButton("TAN");
			*/
			c.gridx = 0;
			c.gridy = 0;
			JButton cosecant = new JButton("CSC");
			cosecant.setMargin(new Insets(0, 6, 0, 6));
			trigFuncPanel.add(cosecant, c);
			c.gridx = 1;
			c.gridy = 0;
			JButton secant = new JButton("SEC");
			secant.setMargin(new Insets(0, 6, 0, 6));
			trigFuncPanel.add(secant, c);
			c.gridx = 2;
			c.gridy = 0;
			JButton cotangent = new JButton("COT");
			cotangent.setMargin(new Insets(0, 6, 0, 6));
			trigFuncPanel.add(cotangent, c);
			c.gridx = 0;
			c.gridy = 1;
			JButton arcSine = new JButton("ASIN");
			arcSine.setMargin(new Insets(0, 6, 0, 6));
			trigFuncPanel.add(arcSine, c);
			c.gridx = 1;
			c.gridy = 1;
			JButton arcCosine = new JButton("ACOS");
			arcCosine.setMargin(new Insets(0, 6, 0, 6));
			trigFuncPanel.add(arcCosine, c);
			c.gridx = 2;
			c.gridy = 1;
			JButton arcTangent = new JButton("ATAN");
			arcTangent.setMargin(new Insets(0, 6, 0, 6));
			trigFuncPanel.add(arcTangent, c);
			
			
			trigFunc.setContentPane(trigFuncPanel);
			trigFunc.pack();
		}
		
	}
}