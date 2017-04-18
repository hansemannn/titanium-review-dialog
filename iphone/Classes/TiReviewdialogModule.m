/**
 * ti.reviewdialog
 *
 * Created by Hans Knoechel
 * Copyright (c) 2017 Your Company. All rights reserved.
 */

#import "TiReviewdialogModule.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"
#import <StoreKit/StoreKit.h>

@implementation TiReviewdialogModule

#pragma mark Internal

// this is generated for your module, please do not change it
-(id)moduleGUID
{
	return @"6f1b3b5d-db05-47da-9e7d-ebb4124cdd8d";
}

// this is generated for your module, please do not change it
-(NSString*)moduleId
{
	return @"ti.reviewdialog";
}

#pragma mark Lifecycle

-(void)startup
{
	// this method is called when the module is first loaded
	// you *must* call the superclass
	[super startup];

	NSLog(@"[DEBUG] %@ loaded",self);
}

#pragma Public APIs


- (id)isSupported:(id)unused
{
#ifdef __IPHONE_10_3
    return NUMBOOL([[[UIDevice currentDevice] systemVersion] compare:@"10.3" options:NSNumericSearch] != NSOrderedAscending);
#else
    return NUMBOOL(NO);
#endif
}

- (void)requestReview:(id)unused
{
    ENSURE_UI_THREAD(requestReview, unused);
    ENSURE_ARG_COUNT(unused, 0);
    
#ifdef __IPHONE_10_3
    [SKStoreReviewController requestReview];
#else
    NSLog(@"[ERROR] Ti.ReviewDialog: This feature is only available on iOS 10.3 and later.");
#endif
}

@end
