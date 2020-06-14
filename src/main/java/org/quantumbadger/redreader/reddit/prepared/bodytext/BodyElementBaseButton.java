package org.quantumbadger.redreader.reddit.prepared.bodytext;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import org.quantumbadger.redreader.common.General;
import org.quantumbadger.redreader.views.LinkDetailsView;

public abstract class BodyElementBaseButton extends BodyElement {

	@NonNull private final String mText;
	@Nullable private final String mSubtitle;

	@NonNull
	protected abstract View.OnClickListener generateOnClickListener(
			@NonNull final AppCompatActivity activity,
			@Nullable final Integer textColor,
			@Nullable final Float textSize,
			final boolean showLinkButtons);

	@Nullable
	protected abstract View.OnLongClickListener generateOnLongClickListener(
			@NonNull final AppCompatActivity activity,
			@Nullable final Integer textColor,
			@Nullable final Float textSize,
			final boolean showLinkButtons);

	public BodyElementBaseButton(
			@NonNull final String text,
			@Nullable final String subtitle) {

		super(BlockType.BUTTON);
		mText = text;
		mSubtitle = subtitle;
	}

	@Override
	public final View generateView(
			@NonNull final AppCompatActivity activity,
			@Nullable final Integer textColor,
			@Nullable final Float textSize,
			final boolean showLinkButtons) {

		final LinkDetailsView ldv = new LinkDetailsView(
				activity,
				mText,
				mSubtitle);

		final int linkMarginPx = General.dpToPixels(activity, 8);

		final ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		// TODO gets partially overwritten by vertical sequence
		layoutParams.setMargins(0, linkMarginPx, 0, linkMarginPx);
		ldv.setLayoutParams(layoutParams);

		ldv.setOnClickListener(
				generateOnClickListener(activity, textColor, textSize, showLinkButtons));

		final View.OnLongClickListener longClickListener
				= generateOnLongClickListener(activity, textColor, textSize, showLinkButtons);

		if(longClickListener != null) {
			ldv.setOnLongClickListener(longClickListener);
		}

		return ldv;
	}
}
