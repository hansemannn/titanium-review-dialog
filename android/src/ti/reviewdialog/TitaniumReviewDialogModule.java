/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2018 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.reviewdialog;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiFileHelper;
import org.appcelerator.titanium.util.TiUrl;

@Kroll.module(name="TitaniumReviewDialog", id="ti.reviewdialog")
public class TitaniumReviewDialogModule extends KrollModule {

	// Methods
	@Kroll.method
	public void requestReview() {
		Activity currentActivity = TiApplication.getAppRootOrCurrentActivity();
		ReviewManager manager = ReviewManagerFactory.create(currentActivity);

		Task<ReviewInfo> request = manager.requestReviewFlow();
		request.addOnCompleteListener(task -> {
			if (!task.isSuccessful()) {
				return;
			}

			ReviewInfo reviewInfo = task.getResult();

			Task<Void> flow = manager.launchReviewFlow(currentActivity, reviewInfo);
			flow.addOnCompleteListener(onCompleteTask -> {
				KrollDict event = new KrollDict();
				event.put("success", true);
				fireEvent("complete", event);
			});
		});
	}

	// Keeping the old code for legacy reasons
	@Kroll.method
	private void showFeedbackDialog(KrollDict configuration) throws PackageManager.NameNotFoundException {
		Activity currentActivity = TiApplication.getAppRootOrCurrentActivity();

		KrollFunction callback = (KrollFunction) configuration.get("onFeedback");
		String storeURL = configuration.getString("storeURL");
		String icon = configuration.getString("icon");

		Drawable appIcon = appIcon = currentActivity
				.getApplication()
				.getPackageManager()
				.getApplicationIcon(TiApplication.getInstance().getAppInfo().getId());

		// Configure the base rating dialog
		RatingDialog.Builder ratingDialogBuilder = new RatingDialog.Builder(currentActivity)
				.threshold(4)
				.session(1)
				.icon(appIcon)
				.onRatingBarFormSubmit(feedback -> {
					KrollDict event = new KrollDict();
					event.put("value", feedback);

					if (callback != null) {
						callback.callAsync(getKrollObject(), event);
					}
					return null;
				});

		// Set store URL if available
		if (storeURL != null) {
			ratingDialogBuilder = ratingDialogBuilder.playstoreUrl(storeURL);
		}

		// Set custom icon if available
		if (icon != null) {
			TiUrl iconUrl = new TiUrl(icon);
			TiFileHelper fileHelper = new TiFileHelper(TiApplication.getInstance());
			Drawable iconDrawable = fileHelper.loadDrawable(iconUrl.resolve(), false);

			if (iconDrawable != null) {
				ratingDialogBuilder = ratingDialogBuilder.icon(iconDrawable);
			}
		}

		// Show the dialog
		RatingDialog ratingDialog = ratingDialogBuilder.build();

		// Perform some custom overrides
		ratingDialog.setCancelable(true);

		ratingDialog.show();
	}

	@Kroll.method
	public boolean isSupported() {
		return true; // Always supported on Android, keeping for parity with iOS
	}
}
