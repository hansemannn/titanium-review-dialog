var win = Ti.UI.createWindow({
    backgroundColor: '#fff'
});

var btn = Ti.UI.createButton({
    title: 'Open Review Dialog!'
});

btn.addEventListener('click', function() {
    var ReviewDialog = require('ti.reviewdialog');
    ReviewDialog.requestReview();
});

win.add(btn);
win.open();
