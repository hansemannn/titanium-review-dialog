/**
 * ti.reviewdialog
 *
 * Created by Hans Knoechel
 * Copyright (c) 2017-present Hans Knoechel. All rights reserved.
 */

#import "TiReviewdialogModule.h"
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
    return @(YES); // The module's minimum target is iOS 11 now
}

- (void)requestReview:(id)unused
{
    ENSURE_UI_THREAD(requestReview, unused);    
    [SKStoreReviewController requestReview];
}

@end
