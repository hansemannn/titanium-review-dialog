//
//  TiReviewdialogModule.swift
//  titanium-review-dialog
//
//  Created by Hans Knoechel
//  Copyright (c) 2017-present. All rights reserved.
//

import UIKit
import TitaniumKit
import StoreKit

@objc(TiReviewdialogModule)
class TiReviewdialogModule: TiModule {
  
  public let testProperty: String = "Hello World"
  
  func moduleGUID() -> String {
    return "977d9a13-62a1-4d3a-b907-131240f744e6"
  }
  
  override func moduleId() -> String! {
    return "ti.reviewdialog"
  }
  
  @objc(isSupported:)
  func isSupported(unused: [Any]?) -> Bool {
    return true
  }
  
  @objc(requestReview:)
  func requestReview(unused: [Any]?) {
    let window = UIApplication.shared.connectedScenes.compactMap { ($0 as? UIWindowScene)?.keyWindow }.last
    guard let scene = window?.windowScene else {
      return
    }
    
    if #available(iOS 16.0, *) {
      Task {
        await AppStore.requestReview(in: scene)
      }
    } else {
      SKStoreReviewController.requestReview(in: scene)
    }
  }
}
