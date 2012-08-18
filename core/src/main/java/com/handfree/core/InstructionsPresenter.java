package com.handfree.core;

public class InstructionsPresenter {
    public interface View {
	void setText(String in);
    }

    private final View view;

    public InstructionsPresenter(View view) {
	super();
	this.view = view;
    }

    public void setText(String in) {
	view.setText(in);
    }
}
