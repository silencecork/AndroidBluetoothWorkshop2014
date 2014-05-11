package com.example.bluetootharminnograph;

import com.jjoe64.graphview.GraphViewDataInterface;

public class GraphViewData implements GraphViewDataInterface {

	private double mX, mY;
	
	public GraphViewData(double x, double y) {
		mX = x;
		mY = y;
	}

	@Override
	public double getX() {
		return mX;
	}

	@Override
	public double getY() {
		return mY;
	}

}
