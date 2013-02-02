package com.handfree.core.story;

public class StoryTextPresenter {
    public interface View {
	void setHasNext(boolean has);

	void setHasPrevious(boolean has);

	void setText(String text);
    }

    private final View view;

    public StoryTextPresenter(View view) {
	this.view = view;
    }
}
