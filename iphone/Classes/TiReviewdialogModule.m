/**
 * ti.reviewdialog
 *
 * Created by Hans Knoechel
 * Copyright (c) 2017-present Hans Knoechel. All rights reserved.
 */

#import "TiReviewdialogModule.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"
#import <StoreKit/StoreKit.h>

@implementation TiReviewdialogModule

#pragma mark Internal

-(id)moduleGUID
{
	return @"6f1b3b5d-db05-47da-9e7d-ebb4124cdd8d";
}

-(NSString*)moduleId
{
	return @"ti.reviewdialog";
}

#pragma Public APIs

- (id)isSupported:(id)unused
{
    return @([TiUtils isIOSVersionOrGreater:@"10.3"]);
}

- (void)requestReview:(id)unused
{
    ENSURE_UI_THREAD(requestReview, unused);
    ENSURE_ARG_COUNT(unused, 0);

    if (![TiUtils isIOSVersionOrGreater:@"10.3"]) {
        NSLog(@"[ERROR] Ti.ReviewDialog: This feature is only available on iOS 10.3 and later.");
        return;
    }
    
    [SKStoreReviewController requestReview];
}

@end
