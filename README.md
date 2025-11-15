## Review Dialogs in Titanium SDK

<img src="example/example-screen.png" width="320" alt="Example" />

### Summary
Use the iOS StoreKit API in your Ttanium application. Also use a native Android dialog
for best parity.

### Requirements
  - Titanium SDK 12.0.0+ (iOS) / 12.8.0+ (Android)
  - iOS 15+
  - Android 5+

### Android Usage

```js
import Review from 'ti.reviewdialog';

if (!Review.isSupported()) { return; }

Review.requestReview();

// when review is done
Review.addEventListener("complete", function(e){
	console.log(e.success);
});
```

### iOS Usage

There are some important notes on when to show the rating dialog (thanks to [@skypanther](https://github.com/skypanther):

* The internal API itself will decide when, or if, to show the prompt
* You should not show it more than once for a given version of your app
* You should not show the dialog in response to user actions (like after a user clicks a button)
* You should not show it on first app launch (even after an update) but should wait until the user has used your app for a "meaningful" amount.

The following (ES6+ based) example shows how to use it in a real world example. It relies on the [semver module](https://github.com/smclab/ti-semver) / http://gitt.io/component/semver to compare app versions.
Apple's own example uses a simple launch count to determine "meaningful" amount of use, which is also used
in this example. You should use something appropriate for your app (amount of time, completion of an
action N times, etc.):

```js
import semver from 'semver';
import Review from 'ti.reviewdialog';

// first, check that the user has used your app for a "meaningful" amount
// here, a simple count of some action
let countOfSomeUserAction = Ti.App.Properties.getInt('countOfSomeUserAction', 0);
countOfSomeUserAction += 1;

Ti.App.Properties.setInt('countOfSomeUserAction', countOfSomeUserAction);

if (countOfSomeUserAction >= 4) {
  // if they've done whatever action 4 or more times, check the app version
  const lastVersionPromptedForReview = Ti.App.Properties.getString('lastVersionPromptedForReview', '1.0.0');
  let appVersion = Ti.App.version;
  const appVersionAsArray = appVersion.split('.');
  if (appVersionAsArray.length > 3) {
    // the semver library supports max 3-part version numbers (per semver rules)
    appVersion = appVersionAsArray[0] + '.' + appVersionAsArray[1] + '.' + appVersionAsArray[2];
  }
  if (semver.gt(appVersion, lastVersionPromptedForReview)) {
    // if the current app version is greater-than the last time you prompted them
    // prompt them with the titanium-review-dialog alert
    Ti.App.Properties.setString('lastVersionPromptedForReview', appVersion);
      if (Review.isSupported()) {
          Review.requestReview();
      }
  }
}
```

### Download
  * [Stable release](https://github.com/hansemannn/titanium-review-dialog/releases)
  * [![gitTio](http://hans-knoechel.de/shields/shield-gittio.svg)](http://gitt.io/component/ti.reviewdialog)

### Setup
Unpack the modules and place them inside the `modules/iphone/` and `modules/android/` folder of your project.
Edit the modules section of your `tiapp.xml` file to include this module:
```xml
<modules>
    <module platform="iphone">ti.reviewdialog</module>
    <module platform="android">ti.reviewdialog</module>
</modules>
```

### Example
Request a new review dialog:
```javascript
import Review from 'ti.reviewdialog';

if (Review.isSupported()) {
    Review.requestReview();
}
```
#### Methods
- [x] `isSupported`
- [x] `requestReview`

### Events
- [x] `complete` (Android only) **Note:** The API does not indicate whether the user reviewed or not, or even whether the review dialog was shown.

### Author
Hans Knoechel ([@hansemannnn](https://twitter.com/hansemannnn) / [Web](https://hans-knoechel.de))

### License
Apache 2.0

### Contributing
Code contributions are greatly appreciated, please submit a new [pull request](https://github.com/hansemannn/titanium-review-dialog/pull/new/master)!
