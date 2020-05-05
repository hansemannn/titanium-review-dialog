/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2019 by Axway, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 * Warning: This file is GENERATED, and should not be modified
 */
package ti.reviewdialog;

import org.appcelerator.kroll.common.KrollSourceCodeProvider;
import org.appcelerator.kroll.util.KrollAssetHelper;

public class CommonJsSourceProvider implements KrollSourceCodeProvider
{
	public String getSourceCode()
	{
		return getSourceCode("ti.reviewdialog");
	}

	public String getSourceCode(String module)
	{
		if (module.equals("${moduleid}/ti.reviewdialog")) {
			module = "ti.reviewdialog";
		}

		return KrollAssetHelper.readAsset("Resources/ti.reviewdialog/" + module + ".js");
	}
}
