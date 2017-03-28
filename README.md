# SKStore​Review​Controller (​10.3+) in Appcelerator Titanium
[![Build Status](https://travis-ci.org/hansemannn/titanium-review-dialog?branch=master)](https://travis-ci.org/hansemannn/titanium-review-dialog) [![License](http://hans-knoechel.de/shields/shield-license.svg?v=1)](./LICENSE) [![Contact](http://hans-knoechel.de/shields/shield-twitter.svg?v=1)](http://twitter.com/hansemannnn)
   
 Summary
---------------
Use the `SKStore​Review​Controller` from iOS 10.3+ in your Ttanium application.

Requirements
---------------
  - Titanium Mobile SDK 6.0.0.GA or later
  - iOS 10.3 or later
  - Xcode 8.3 or later

Download + Setup
---------------

### Download
  * [Stable release](https://github.com/hansemannn/titanium-review-dialog/releases)
  * [![gitTio](http://hans-knoechel.de/shields/shield-gittio.svg)](http://gitt.io/component/titanium-review-dialog)

### Setup
Unpack the module and place it inside the `modules/iphone/` folder of your project.
Edit the modules section of your `tiapp.xml` file to include this module:
```xml
<modules>
    <module platform="iphone">ti.reviewdialog</module>
</modules>
```

Request a new review dialog:
```javascript
var ReviewDialog = require('ti.reviewdialog');
ReviewDialog.requestReview();
```
#### Methods
- [x] `requestReview`

### Example
For a full example, check the demo in `example/app.js`.

### Author
Hans Knoechel ([@hansemannnn](https://twitter.com/hansemannnn) / [Web](http://hans-knoechel.de))

### License
Apache 2.0

### Contributing
Code contributions are greatly appreciated, please submit a new [pull request](https://github.com/hansemannn/titanium-review-dialog/pull/new/master)!
