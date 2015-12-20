package com.gmail.raynlegends.adventofcode;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import com.gmail.raynlegends.adventofcode.puzzles.*;

public class GUI {

	public static final Dimension MAIN_DIMENSION = new Dimension(550, 400);
	public static final Color MAIN_BACKGROUND = new Color(66, 66, 66);
	public static final Color MAIN_TEXT = new Color(250, 250, 250);

	private JFrame frame;

	private JComboBox<Puzzle> puzzle;
	private JTextField input;
	private JTextField output;
	private JButton calculate;

	private JLabel selectedPuzzleLabel;
	private JLabel selectPuzzle;

	public GUI() {
		frame = new JFrame("AdventOfCode - Puzzle solver");
		frame.setTitle("AdventOfCode - Puzzle solver");
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.setSize(new Dimension(550, 300));
		frame.setPreferredSize(new Dimension(550, 300));
		frame.setMinimumSize(new Dimension(550, 300));
		frame.getContentPane().setPreferredSize(new Dimension(550, 300));
		frame.getContentPane().setSize(new Dimension(550, 300));
		frame.getContentPane().setMinimumSize(new Dimension(550, 300));
		frame.setResizable(false);
		frame.getContentPane().setBackground(MAIN_BACKGROUND);
		frame.setBackground(MAIN_BACKGROUND);

		frame.pack();
		frame.getContentPane().setLayout(null);

		input = new JTextField();
		input.setHorizontalAlignment(SwingConstants.CENTER);
		input.setText("Input");
		input.setBounds(10, 11, 264, 85);
		input.setColumns(10);
		frame.getContentPane().add(input);

		output = new JTextField();
		output.setHorizontalAlignment(SwingConstants.CENTER);
		output.setEditable(false);
		output.setText("Output");
		output.setToolTipText("Output");
		output.setColumns(10);
		output.setBounds(10, 172, 524, 85);
		frame.getContentPane().add(output);

		calculate = new JButton("Calculate!");
		calculate.setBounds(10, 107, 524, 54);
		frame.getContentPane().add(calculate);

		puzzle = new JComboBox<Puzzle>();
		puzzle.setToolTipText("Select a puzzle");
		puzzle.setBounds(415, 70, 119, 20);
		frame.getContentPane().add(puzzle);

		selectedPuzzleLabel = new JLabel("Select puzzle:");
		selectedPuzzleLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedPuzzleLabel.setForeground(Color.WHITE);
		selectedPuzzleLabel.setBounds(284, 58, 121, 38);
		frame.getContentPane().add(selectedPuzzleLabel);

		selectPuzzle = new JLabel("Day 1, Part 1");
		selectPuzzle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		selectPuzzle.setForeground(Color.WHITE);
		selectPuzzle.setHorizontalAlignment(SwingConstants.CENTER);
		selectPuzzle.setBounds(284, 11, 250, 38);
		frame.getContentPane().add(selectPuzzle);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dimension.width/2 - frame.getSize().width/2, dimension.height/2 - frame.getSize().height/2);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				toggleEnabled(false);
				
				new SwingWorker<Object, Object>() {

					@Override
					protected Object doInBackground() {
						try {
							return Main.calculate(puzzle.getSelectedItem(), input.getText().trim());
						} catch (Exception e) {
							return e;
						}
					}
					
					@Override
					protected void done() {
						Object raw;
						try {
							raw = this.get();
							
							if (raw instanceof Exception) {
								Exception e = (Exception) raw;
								
								JOptionPane.showMessageDialog(frame,
										e.getMessage(),
										"Warning",
										JOptionPane.WARNING_MESSAGE);
								e.printStackTrace();
								
								output.setText("An error has occurred while calculating");
							} else if (raw instanceof String) {
								output.setText((String) raw);
							} else {
								output.setText("Default output");
							}
							
							frame.toFront();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
						
						toggleEnabled(true);
					}
					
				}.execute();
			}

		});

		puzzle.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					selectPuzzle.setText(event.getItem().toString());
				}
			}

		});

		addItem(new _01_1_Puzzle());
		addItem(new _01_2_Puzzle());
		addItem(new _02_1_Puzzle());
		addItem(new _02_2_Puzzle());
		addItem(new _03_1_Puzzle());
		addItem(new _03_2_Puzzle());
		addItem(new _04_1_Puzzle());
		addItem(new _04_2_Puzzle());
		addItem(new _05_1_Puzzle());
		addItem(new _05_2_Puzzle());
		addItem(new _06_1_Puzzle());
		addItem(new _06_2_Puzzle());
		addItem(new _07_1_Puzzle());
		addItem(new _07_2_Puzzle());
		addItem(new _08_1_Puzzle());
		addItem(new _08_2_Puzzle());
		addItem(new _09_1_Puzzle());
		addItem(new _09_2_Puzzle());
		addItem(new _10_1_Puzzle());
		addItem(new _10_2_Puzzle());
		addItem(new _11_1_Puzzle());
		addItem(new _11_2_Puzzle());
		addItem(new _12_1_Puzzle());
		addItem(new _12_2_Puzzle());
		addItem(new _13_1_Puzzle());
		addItem(new _13_2_Puzzle());
		addItem(new _14_1_Puzzle());
		addItem(new _14_2_Puzzle());
		addItem(new _15_1_Puzzle());
		addItem(new _15_2_Puzzle());
		addItem(new _16_1_Puzzle());
		addItem(new _16_2_Puzzle());
		addItem(new _17_1_Puzzle());
		addItem(new _17_2_Puzzle());
	}

	private void addItem(Puzzle puzzle) {
		this.puzzle.addItem(puzzle);
	}
	
	private void toggleEnabled(boolean enabled) {
		input.setEnabled(enabled);
		input.setEditable(enabled);
		puzzle.setEnabled(enabled);
		calculate.setEnabled(enabled);
		calculate.setText(enabled ? "Calculate!" : "Calculating...");
	}
}
